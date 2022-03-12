## 幻读是怎么被解决的？

`InnoDB` 存储引擎的默认事务隔离级别是「可重复读」，但是在这个隔离级别下，在多个事务并发的时候，会出现幻读的问题，所谓的幻读是指在同一事务下，连续执行两次同样的查询语句，第二次的查询语句可能会返回之前不存在的行。

因此 `InnoDB` 存储引擎自己实现了行锁，通过 `next-key` 锁（记录锁和间隙锁的组合）来锁住记录本身和记录之间的“间隙”，防止其他事务在这个记录之间插入新的记录，从而避免了幻读现象。

`MySQL` 里除了普通`select`查询是快照读，其他都是**当前读**，比如`update、insert、delete`，这些语句执行前都会查询最新版本的数据，然后再做进一步的操作。

`select ... for update` 这种查询语句是当前读，每次执行的时候都是读取最新的数据。 

**因此，要讨论「可重复读」隔离级别的幻读现象，是要建立在「当前读」的情况下。**

接下来，我们假设`select ... for update`当前读是不会加锁的（实际上是会加锁的）

比如，下面事务 A 查询语句会锁住`(2, +∞]`范围的记录，然后期间如果有其他事务在这个锁住的范围插入数据就会被阻塞。

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZe5TGuDMrO7fs2wdwaI9eON0cvibtVEiaJ4rF1zJ7OeYibTKZdVdAVuibwTMEoGphOI49uG1wS3TiapjYg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:50%;" />

`next-key` 锁的加锁规则其实挺复杂的，在一些场景下会退化成记录锁或间隙锁，我之前也写一篇加锁规则，详细可以看这篇「[我做了一天的实验！](https://mp.weixin.qq.com/s?__biz=MzUxODAzNDg4NQ==&mid=2247497197&idx=1&sn=9f82f73d876636944fb75348ef568c01&scene=21#wechat_redirect)」

需要注意的是，`next-key lock` 锁的是索引，而不是数据本身，所以如果 `update` 语句的 `where` 条件没有用到索引列，那么就会全表扫描，在一行行扫描的过程中，不仅给行加上了行锁，还给行两边的空隙也加上了间隙锁，相当于锁住整个表，然后直到事务结束才会释放锁。

所以在线上千万不要执行没有带索引条件的 `update` 语句，不然会造成业务停滞，我有个读者就因为干了这个事情，然后被老板教育了一波，详细可以看这篇「[完蛋，公司被一条 update 语句干趴了！](https://mp.weixin.qq.com/s?__biz=MzUxODAzNDg4NQ==&mid=2247497844&idx=1&sn=256a70fb347ed23b0e116d7cc208d426&scene=21#wechat_redirect)」



## 执行 update 语句时，where 条件没有带上索引，会发生什么？

当我们执行 `update` 语句时，实际上是会对记录加独占锁（X 锁）的，如果其他事务对持有独占锁的记录进行修改时是会被阻塞的。另外，**这个锁并不是执行完 `update` 语句就会释放的，而是会等事务结束时才会释放。**

在 `InnoDB` 事务中，对记录加锁带基本单位是 `next-key` 锁，但是会因为一些条件会退化成间隙锁，或者记录锁。加锁的位置准确的说，锁是加在索引上的而非行上。

比如，在 `update` 语句的 `where` 条件使用了唯一索引，那么 `next-key` 锁会退化成记录锁，也就是只会给一行记录加锁。

假设有两个事务的执行顺序如下：

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcprWcdNCqepBuhL2hhIpN7wYhqufiaL88AFOmKBLOWNEvrDc6JroCk0p56Ma622bVGibjFtdZFfOuQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom: 60%;" />

可以看到，事务 A 的 update 语句中 where 是等值查询，并且 id 是唯一索引，所以只会对 id = 1 这条记录加锁，因此，事务 B 的更新操作并不会阻塞。

但是，**在 update 语句的 where 条件没有使用索引，就会全表扫描，于是就会对所有记录加上 next-key 锁（记录锁 + 间隙锁），相当于把整个表锁住了**。

假设有两个事务的执行顺序如下：

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcprWcdNCqepBuhL2hhIpN7yKN0Q5VQjmB91ZRemuPibTEMhvqtYUXepoIrO8cmnVPBV7btTByWiayQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:50%;" />

可以看到，这次事务 B 的 update 语句被阻塞了。

**这是因为事务 A的 update 语句中 where 条件没有索引列，所有记录都会被加锁，也就是这条 update 语句产生了 4 个记录锁和 5 个间隙锁，相当于锁住了全表。**

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcprWcdNCqepBuhL2hhIpN748D9hD2URxqEoibLbicic5YybSsZp9yrgtKBgeItPfbyBITZbBP8R9FWQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom: 50%;" />

因此，当在数据量非常大的数据库表执行 update 语句时，如果没有使用索引，就会给全表的加上 next-key 锁， 那么锁就会持续很长一段时间，直到事务结束。



## 唯一索引和非唯一索引的行级锁的加锁规则next-key lock

**加锁的基本单位是 next-key lock**，它是由记录锁和间隙锁组合而成的，**next-key lock 是前开后闭区间，而间隙锁是前开后开区间**。

但是，next-key lock 在一些场景下会退化成记录锁或间隙锁。**我的 MySQL 的版本是 8.0.26，不同版本的加锁规则可能是不同的**

### 唯一索引等值查询

当我们用唯一索引进行等值查询的时候，查询的记录存不存在，加 锁的规则也会不同：

- **当查询的记录是存在的，在用「唯一索引进行等值查询」时，next-key lock 会退化成「记录锁」**。
- **当查询的记录是不存在的，在用「唯一索引进行等值查询」时，next-key lock 会退化成「间隙锁」**。

### 唯一索引范围查询

举个例子，下面这两条查询语句，查询的结果虽然是一样的，但是加锁的范围是不一样的。

```
select * from t_test where id=8 for update;
select * from t_test where id>=8 and id<9 for update;
```

做个实验就知道了。

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZdb4JUy0KOBia9GP5ibiaAskIdxpgnmN3SGCA3emZNG6WicHLLPKAMLjrrF13s6dP1KgoGDtB0Ny8YCLw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:50%;" />

会话 1 加锁变化过程如下：

1. 最开始要找的第一行是 id = 8，因此 next-key lock(4,8]，但是由于 id 是唯一索引，且该记录是存在的，因此会退化成记录锁，也就是只会对 id = 8 这一行加锁；
2. 由于是范围查找，就会继续往后找存在的记录，也就是会找到 id = 16 这一行停下来，然后加 next-key lock (8, 16]，但由于 id = 16 不满足 id < 9，所以会退化成间隙锁，加锁范围变为 (8, 16)。

所以，会话 1 这时候主键索引的锁是记录锁 id=8 和间隙锁(8, 16)。

会话 2 由于往间隙锁里插入了 id = 9 的记录，所以会被锁住了，而 id = 8 是被加锁的，因此会话 3 的语句也会被阻塞。

由于 id = 16 并没有加锁，所以会话 4 是可以正常被执行。

### 非唯一索引等值查询

-  当查询的记录存在时，除了会加 next-key lock 外，还额外加间隙锁，规则是向下遍历到第一个不符合条件的值才能停止，也就是会加两把锁。
- 当查询的记录不存在时，只会加 next-key lock，然后会退化为间隙锁，也就是只会加一把锁。

### 非唯一索引范围查询

非唯一索引和主键索引的范围查询的加锁也有所不同，不同之处在于**普通索引范围查询，next-key lock 不会退化为间隙锁和记录锁，所以只保留next-key lock的锁不变**。

来看下面这个案例：

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZdb4JUy0KOBia9GP5ibiaAskIddqvnAIKrx53VV58QPYHyDlNg9W3lDVpb09CCAe9ibzATyvGoVWibkvAg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom: 50%;" />

会话 1 加锁变化过程如下：

1. 最开始要找的第一行是 b = 8，因此 next-key lock(4,8]，但是由于 b 不是唯一索引，并不会退化成记录锁。
2. 但是由于是范围查找，就会继续往后找存在的记录，也就是会找到 b = 16 这一行停下来，然后加 next-key lock (8, 16]，因为是普通索引查询，所以并不会退化成间隙锁。

所以，会话 1 的普通索引 b 有两个 next-key lock，分别是 (4,8] 和(8, 16]。这样，你就明白为什么会话 2 、会话 3 、会话 4 的语句都会被锁住了。



## SELECT FOR UPDATE

`FOR UPDATE` 是一种行级锁，又叫排它锁。仅适用于 `InnoDB`，并且必须开启事务，在 `BEGIN` 与 `COMMIT` 之间才生效。

开启两个 `MySQL` 事务

- 事务1

```sql
BEGIN
SELECT id, user_id, goods_id, state FROM goods_order WHERE id = 2 FOR UPDATE
UPDATE goods_order SET state = 2 WHERE id=2
COMMIT
```

- 事务2

```sql
BEGIN
SELECT id, user_id, goods_id, state FROM goods_order WHERE id = 2 FOR UPDATE
UPDATE goods_order SET state = 3 WHERE id=2
COMMIT
```

当 `事务1` 执行完 `SELECT ... FOR UPDATE` 后（此时事务还未结束）， `事务2` 执行 `SELECT ... FOR UPDATE` 语句时将会阻塞在那，直到 `事务1` 中的事务结束（执行完 `COMMIT`）。

其中一个使用场景是用于修改订单状态，修改订单状态往往需要两个步骤：

1. 查询订单状态。
2. 修改订单状态。

当有两个任务同时请求时，有可能出现如下情况：

1. 任务A查询到订单状态为1。
2. 任务B查询到订单状态为1。
3. 任务A修改订单状态为2。
4. 任务B修改订单状态为3。

其中，任务B将订单状态改为3的前提是订单状态为1，但是上述情况下任务B修改订单时订单状态已变成2了，并不符合预期，通过 `SELECT ... FRO UPDATE` 就可以解决上述问题。



## MySQL死锁

有个业务主要逻辑就是新增订单、修改订单、查询订单等操作。然后因为订单是不能重复的，所以当时在新增订单的时候做了幂等性校验，做法就是在新增订单记录之前，先通过 `select ... for update` 语句查询订单是否存在，如果不存在才插入订单记录。

而正是因为这样的操作，当业务量很大的时候，就可能会出现死锁。

接下来跟大家聊下**为什么会发生死锁，以及怎么避免死锁**。

### 死锁的发生

我建了一张订单表，其中 id 字段为主键索引，order_no 字段普通索引，也就是非唯一索引：

```
CREATE TABLE `t_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_no` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_order` (`order_no`) USING BTREE
) ENGINE=InnoDB ;
```

然后，先 `t_order` 表里现在已经有了 6 条记录：

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcXFD2G3C5wxBspogWP1KYPva24AUvkePMzTvlPiaTX2F64r5VPIJKs8I5XbQ7MMAoepnNdwic1m1WA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:67%;" />

假设这时有两事务，一个事务要插入订单 1007 ，另外一个事务要插入订单 1008，因为需要对订单做幂等性校验，所以两个事务先要查询该订单是否存在，不存在才插入记录，过程如下：

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcXFD2G3C5wxBspogWP1KYPiaibWcP3lq8icH0ibicgQjd83fPYsL1ic8r6K0G8I8OJ7JCRa3lS9GoE6xGQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:67%;" />

可以看到，**两个事务都陷入了等待状态（前提没有打开死锁检测），也就是发生了死锁，因为都在相互等待对方释放锁。**

**这里在查询记录是否存在的时候，使用了 `select ... for update` 语句，目的为了防止事务执行的过程中，有其他事务插入了记录，而出现幻读的问题。**

如果没有使用 `select ... for update` 语句，而使用了单纯的 `select` 语句，如果是两个订单号一样的请求同时进来，就会出现两个重复的订单，有可能出现幻读，如下图：

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcXFD2G3C5wxBspogWP1KYP2GnLx8I29iaCajGhP5M2kjICd3nic8vm1ZJU8npia8dDoiaib6kYWhoRzjA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:67%;" />



### 为什么会产生死锁？

可重复读隔离级别下，是存在幻读的问题。

**Innodb 引擎为了解决「可重复读」隔离级别下的幻读问题，就引出了 next-key 锁**

**普通的 select 语句是不会对记录加锁的，因为它是通过 MVCC 的机制实现的快照读，如果要在查询时对记录加行锁，可以使用下面这两个方式：**

```sql
begin;
//对读取的记录加共享锁
select ... lock in share mode;
commit; //锁释放

begin;
//对读取的记录加排他锁
select ... for update;
commit; //锁释放
```

**行锁的释放时机是在事务提交（commit）后，锁就会被释放，并不是一条语句执行完就释放行锁。**

回到前面死锁的例子，在执行下面这条语句的时候：

```sql
select id from t_order where order_no = 1008 for update;
```

因为 `order_no` 不是唯一索引，所以行锁的类型是间隙锁，于是间隙锁的范围是`（1006, +∞）`。那么，当事务 B 往间隙锁里插入 `id = 1008` 的记录就会被锁住。

**因为当我们执行以下插入语句时，会在插入间隙上再次获取插入意向锁。**

```sql
insert into t_order (order_no, create_date) values (1008, now());
```

**插入意向锁与间隙锁是冲突的，所以当其它事务持有该间隙的间隙锁时，需要等待其它事务释放间隙锁之后，才能获取到插入意向锁。而间隙锁与间隙锁之间是兼容的，所以所以两个事务中 `select ... for update` 语句并不会相互影响。**

**案例中的事务 A 和事务 B 在执行完后 `select ... for update` 语句后都持有范围为`(1006,+∞）`的间隙锁，而接下来的插入操作为了获取到插入意向锁，都在等待对方事务的间隙锁释放，于是就造成了循环等待，导致死锁。**

### 如何避免死锁？

死锁的四个必要条件：**互斥、占有且等待、不可强占用、循环等待**。只要系统发生死锁，这些条件必然成立，但是只要破坏任意一个条件就死锁就不会成立。

在数据库层面，有两种策略通过「打破循环等待条件」来解除死锁状态：

- **设置事务等待锁的超时时间**。当一个事务的等待时间超过该值后，就对这个事务进行回滚，于是锁就释放了，另一个事务就可以继续执行了。在 InnoDB 中，参数 `innodb_lock_wait_timeout` 是用来设置超时时间的，默认值时 50 秒。

  当发生超时后，就出现下面这个提示：

![图片](https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcXFD2G3C5wxBspogWP1KYPlJ8zQOFbXRMjgLYs0WKtj2BfJkoAFV9odOCV1lYaFjSNRZHG2sobew/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

- **开启主动死锁检测**。主动死锁检测在发现死锁后，主动回滚死锁链条中的某一个事务，让其他事务得以继续执行。将参数 `innodb_deadlock_detect` 设置为 on，表示开启这个逻辑，默认就开启。

  当检测到死锁后，就会出现下面这个提示：

![图片](https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZcXFD2G3C5wxBspogWP1KYPKUkgzbZ17ibBooha1VQV2fTLrszvvKBbgpGDP2Xn8LuErHwQ73UTtmQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

上面这个两种策略是「当有死锁发生时」的避免方式。

我们可以回归业务的角度来预防死锁，对订单做幂等性校验的目的是为了保证不会出现重复的订单，那我们可以直接将 order_no 字段设置为唯一索引列，利用它的唯一下来保证订单表不会出现重复的订单，不过有一点不好的地方就是在我们插入一个已经存在的订单记录时就会抛出异常。



## 哪些操作会导致索引失效？

- 对索引使用左或者左右模糊匹配，也就是 like %xx 或者 like %xx% 这两种方式都会造成索引失效。原因在于查询的结果可能是多个，不知道从哪个索引值开始比较，于是就只能通过全表扫描的方式来查询。
- 对索引进行函数/对索引进行表达式计算，因为索引保持的是索引字段的原始值，而不是经过函数计算的值，自然就没办法走索引。
- 对索引进行隐式转换相当于使用了新函数。
- WHERE 子句中的 OR语句，只要有条件列不是索引列，就会进行全表扫描。



## `Redis`的好处、特点

- 既可以做持久化存储，也可以当作缓存。
- 作为持久层，它存储的数据是半结构化的，这意味着计算机读入内存中有更少的规则，读入速度更快。对于结构化，多范式规则的数据库而言，它更具有优势。
- 作为缓存，它支持大数据存入内存中，只要命中率高，它就能快速响应。响应速度每秒可达到大约10万次的读写操作。
- 支持6种数据类型可以满足存储各种数据结构体的需要，另一方面数据类型少，使得规则少，判断的逻辑就少，这样读写速度就更快。
- 支持分布式，集群，主从同步等配置。还支持一定的事务能力，在高并发场景下保证数据安全和一致性。
