# Redis面试笔记



## Redis的好处、特点

- 既可以做持久化存储，也可以当作缓存。
- 作为持久层，它存储的数据是半结构化的，这意味着计算机读入内存中有更少的规则，读入速度更快。对于结构化，多范式规则的数据库而言，它更具有优势。
- 作为缓存，它支持大数据存入内存中，只要命中率高，它就能快速响应。响应速度每秒可达到大约10万次的读写操作。
- 支持5种数据类型可以满足存储各种数据结构体的需要，另一方面数据类型少，使得规则少，判断的逻辑就少，这样读写速度就更快。
- **支持分布式锁，集群，主从复制等配置。还支持一定的事务能力，在高并发场景下保证数据安全和一致性。**
- **数据的相关操作使用单线程的工作方式**，并且使用I/O多路复用的方式进行通信



## Redis的应用

- 热点数据加速查询（主要场景），如热点商品、热点新闻、热点资讯、推广类等高访问量信息等
- 实时性信息查询，如各位排行榜、各类网站访问统计等
- 时效性信息控制，如验证码控制等
- 分布式数据共享，如分布式集群架构中的 session 分离
- 分布式锁



## Redis通用操作

| 基本操作                      | 时效性控制                                                   | 其他操作                                              |
| ----------------------------- | ------------------------------------------------------------ | ----------------------------------------------------- |
| 删除指定key：`del key`        | 为指定key设置有效期：`expire key seconds`、`expireat key timestamp` | 为key改名：`rename key newkey`，`renamenx key newkey` |
| 获取key是否存在：`exists key` | 获取key的有效时间：`ttl key`                                 | 对所有key排序：`sort`                                 |
| 获取key的类型：`type key`     | 切换key从时效性转换为永久性：`persist key`                   |                                                       |



## Redis数据类型与数据结构

详见Xmind思维导图



## 单机的Redis存在四大问题

<img src="imgs\image-20210725144240631.png" alt="image-20210725144240631" style="zoom:67%;" />



## RDB原理

bgsave开始时会fork主进程得到子进程，子进程共享主进程的内存数据。完成fork后读取内存数据并写入 RDB 文件。

fork采用的是copy-on-write技术：

- 当主进程执行读操作时，访问共享内存；
- 当主进程执行写操作时，则会拷贝一份数据，执行写操作。

<img src="imgs/image-20210725151319695.png" alt="image-20210725151319695" style="zoom:60%;" />



## master如何判断slave节点是不是第一次来做数据同步？

有几个概念，可以作为判断依据：

- **Replication Id**：简称replid，是数据集的标记，id一致则说明是同一数据集。每一个master都有唯一的replid，slave则会继承master节点的replid
- **offset**：偏移量，随着记录在repl_baklog中的数据增多而逐渐增大。slave完成同步时也会记录当前同步的offset。如果slave的offset小于master的offset，说明slave数据落后于master，需要更新。

因此slave做数据同步，必须向master声明自己的replication id 和offset，master才可以判断到底需要同步哪些数据。



因为slave原本也是一个master，有自己的replid和offset，当第一次变成slave，与master建立连接时，发送的replid和offset是自己的replid和offset。

master判断发现slave发送来的replid与自己的不一致，说明这是一个全新的slave，就知道要做全量同步了。

master会将自己的replid和offset都发送给这个slave，slave保存这些信息。以后slave的replid就与master一致了。

因此，**master判断一个节点是否是第一次同步的依据，就是看replid是否一致**。



## 主从复制的作用

- 读写分离：master写、slave读，提高服务器的读写负载能力
- 负载均衡：基于主从结构，配合读写分离，由slave分担master负载，并根据需求的变化，改变slave的数量，通过多个从节点分担数据读取负载，大大提高Redis服务器并发量与数据吞吐量
- 故障恢复：当master出现问题时，由slave提供服务，实现快速的故障恢复
- 数据冗余：实现数据热备份，是持久化之外的一种数据冗余方式
- 高可用基石：基于主从复制，构建哨兵模式与集群，实现Redis的高可用方案



## Redis的高可用指的是什么？

两个层面：

- 数据不能丢失，或者说尽量减少丢失，可以通过AOF和RDB保证。
- 保证Redis服务不中断，不能单点部署了，选择主从复制



## Redis实现分布式锁的四种方案

### 什么是分布式锁

> 分布式锁其实就是，控制分布式系统不同进程共同访问共享资源的一种锁的实现。如果不同的系统或同一个系统的不同主机之间共享了某个临界资源，往往需要互斥来防止彼此干扰，以保证一致性。

我们先来看下，一把靠谱的分布式锁应该有哪些特征：

- **「互斥性」**: 任意时刻，只有一个客户端能持有锁。
- **「锁超时释放」**：持有锁超时，可以释放，防止不必要的资源浪费，也可以防止死锁。
- **「可重入性」**:一个线程如果获取了锁之后,可以再次对其请求加锁。
- **「高性能和高可用」**：加锁和解锁需要开销尽可能低，同时也要保证高可用，避免分布式锁失效。
- **「安全性」**：锁只能被持有的客户端删除，不能被其他客户端删除



Redis为单进程单线程模式，采用队列模式将并发访问变为串行访问。Redis本身没有锁的概念，Redis对于多个客户端连接并不存在竞争，但是在Jedis客户端对Redis进行并发访问时会发生连接超时、数据转换错误、阻塞、客户端关闭连接等问题，这些问题均是由于客户端连接混乱造成。



### 方案一：SETNX + EXPIRE

提到Redis的分布式锁，就会想到`setnx`+ `expire`命令。即先用`setnx`来抢锁，如果抢到之后，再用`expire`给锁设置一个过期时间，防止锁忘记了释放。

> SETNX 是SET IF NOT EXISTS的简写.日常命令格式是SETNX key value，如果 key不存在，则SETNX成功返回1，如果这个key已经存在了，则返回0。

假设某电商网站的某商品做秒杀活动，key可以设置为key_resource_id,value设置任意值，伪代码如下：

```java
if(jedis.setnx(key_resource_id,lock_value) == 1) { //加锁
    expire（key_resource_id，100）; //设置过期时间
    try {
        do something  //业务请求
    }catch(){
  }
  finally {
       jedis.del(key_resource_id); //释放锁
    }
}
```

但是这个方案中，`setnx`和`expire`两个命令分开了，**「不是原子操作」**。如果执行完`setnx`加锁，正要执行`expire`设置过期时间时，进程crash或者要重启维护了，那么这个锁就“长生不老”了，**「别的线程永远获取不到锁啦」**。

### 方案二：使用Lua脚本(包含SETNX + EXPIRE两条指令)

实际上，我们还可以使用Lua脚本来保证原子性（包含`setnx`和`expire`两条指令），lua脚本如下：

```java
if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then
   redis.call('expire',KEYS[1],ARGV[2])
else
   return 0
end;
```

加锁代码如下

```java
 String lua_scripts = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then" +
            " redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";   
Object result = jedis.eval(lua_scripts, Collections.singletonList(key_resource_id), Collections.singletonList(values));
//判断是否成功
return result.equals(1L);
```

这个方案，跟方案二对比，你觉得哪个更好呢？

### 方案三：SET EX PX NX

除了使用，使用Lua脚本，保证`SETNX + EXPIRE`两条指令的原子性，我们还可以巧用Redis的SET指令扩展参数！（`SET key value[EX seconds][PX milliseconds][NX|XX]`），它也是原子性的！

> SET key value [EX seconds] [PX milliseconds] [NX|XX]
>
> - NX :表示key不存在的时候，才能set成功，也即保证只有第一个客户端请求才能获得锁，而其他客户端请求只能等其释放锁，才能获取。
> - EX seconds :设定key的过期时间，时间单位是秒。
> - PX milliseconds: 设定key的过期时间，单位为毫秒
> - XX: 仅当key存在时设置值

伪代码demo如下：

```java
if（jedis.set(key_resource_id, lock_value, "NX", "EX", 100s) == 1）{ //加锁
    try {
        do something  //业务处理
    }catch(){
  }
  finally {
       jedis.del(key_resource_id); //释放锁
    }
}
```

但是呢，这个方案还是可能存在问题

- 问题一：**「锁过期释放了，业务还没执行完」**。假设线程a获取锁成功，一直在执行临界区的代码。但是100s过去后，它还没执行完。但是，这时候锁已经过期了，此时线程b又请求过来。显然线程b就可以获得锁成功，也开始执行临界区的代码。那么问题就来了，临界区的业务代码都不是严格串行执行的啦。
- 问题二：**「锁被别的线程误删」**。假设线程a执行完后，去释放锁。但是它不知道当前的锁可能是线程b持有的（线程a去释放锁时，有可能过期时间已经到了，此时线程b进来占有了锁）。那线程a就把线程b的锁释放掉了，但是线程b临界区业务代码可能都还没执行完呢。

### 方案四：SET EX PX NX  + 校验唯一随机值,再删除

既然锁可能被别的线程误删，那我们给value值设置一个标记当前线程唯一的随机数，在删除的时候，校验一下，不就OK了嘛。伪代码如下：

```csharp
if（jedis.set(key_resource_id, uni_request_id, "NX", "EX", 100s) == 1）{ //加锁
    try {
        do something  //业务处理
    }catch(){
  }
  finally {
       //判断是不是当前线程加的锁,是才释放
       if (uni_request_id.equals(jedis.get(key_resource_id))) {
        jedis.del(lockKey); //释放锁
        }
    }
}
```

在这里，**「判断是不是当前线程加的锁」**和**「释放锁」**不是一个原子操作。如果调用jedis.del()释放锁的时候，可能这把锁已经不属于当前客户端，会解除他人加的锁。

<img src="https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGxM0ZotibjMv7bw8KMNT5buv3sEKqlzF69a5d8OibovIwAWdNwD7O55zTMDWySkF4HpHB2vJjDIbXw/640?wx_fmt=png" alt="图片" style="zoom: 67%;" />

为了更严谨，一般也是用lua脚本代替。lua脚本如下：

```java
if redis.call('get',KEYS[1]) == ARGV[1] then 
   return redis.call('del',KEYS[1]) 
else
   return 0
end;
```



## Redis事务

### 概述

Redis为单进程单线程模式，采用队列模式将并发访问变为串行访问。Redis本身没有锁的概念，Redis对于多个客户端连接并不存在竞争，但是在Jedis客户端对Redis进行并发访问时会发生连接超时、数据转换错误、阻塞、客户端关闭连接等问题，这些问题均是由于客户端连接混乱造成。对此有2种解决方法：

1.客户端角度，为保证每个客户端间正常有序与Redis进行通信，对连接进行池化，同时对客户端读写Redis操作采用内部锁synchronized。

2.服务器角度，利用setnx实现锁。

**MULTI，EXEC，DISCARD，WATCH 四个命令是 Redis 事务的四个基础命令**。其中：

MULTI，告诉 Redis 服务器开启一个事务。注意，只是开启，而不是执行 。 
EXEC，告诉 Redis 开始执行事务 。 
DISCARD，告诉 Redis 取消事务。 
WATCH，监视某一个键值对，它的作用是在事务执行之前如果监视的键值被修改，事务会被取消。

**可以利用watch实现cas乐观锁**



### Redis事务命令

| 命令                    | 说明                                                         | 备注                                                         |
| ----------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| multi                   | 开启事务命令，之后的命令就进入队列，而不会马上被执行         | 在事务生存期间，所有的 Redis 关于数据结构的命令都会入队      |
| watch key1 \[key2 …\]   | 监听某些键，当被监听的键在事务执行前被修改，则事务会被回滚   | 使用乐观锁                                                   |
| unwatch key1 \[key2 …\] | 取消监听某些键                                               | –                                                            |
| exec                    | 执行事务，如果被监听的键没有被修改，则采用执行命令，否则就回滚命令 | 在执行事务队列存储的命令前， Redis 会检测被监听的键值对有没有发生变化，如果没有则执行命令 ，否则就回滚事务 |
| discard                 | 回滚事务                                                     | 回滚进入队列的事务命令，之后就不能再用 exec命令提交了        |



### 事务基本使用

**Redis 中的事务从开始到结束也是要经历三个阶段**：

*   开启事务
*   命令入列
*   执行事务/放弃事务

其中，开启事务使用 **multi命令**，事务执行使用 **exec命令**，放弃事务使用 **discard命令**。



### 开启事务

**multi 命令**用于开启事务，实现代码如下：

```redis
> multi
OK
```

multi 命令可以让客户端从非事务模式状态，变为事务模式状态。
**注意**：multi 命令不能嵌套使用，如果已经开启了事务的情况下，再执行 multi 命令，会提示如下错误：

当客户端是非事务状态时，使用 multi 命令，客户端会返回结果 OK ，如果客户端已经是事务状态，再执行 multi 命令会 multi 命令不能嵌套的错误，但不会终止客户端为事务的状态，



### 命令入列

客户端进入事务状态之后，执行的所有常规 Redis 操作命令（非触发事务执行或放弃和导致入列异常的命令）会依次入列，命令入列成功后会返回 QUEUED
**注意**：命令会按照先进先出（FIFO）的顺序出入列，也就是说事务会按照命令的入列顺序，从前往后依次执行。



### 执行事务/放弃事务

**执行事务的命令是 exec ，放弃事务的命令是 discard** 。 执行事务示例代码如下：

```
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set aa bb
QUEUED
127.0.0.1:6379> exec
1) OK
127.0.0.1:6379> get aa
"bb"
127.0.0.1:6379>
```

**放弃事务示例代码如下**：

```
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set cc dd
QUEUED
127.0.0.1:6379> discard
OK
127.0.0.1:6379> get cc
(nil)
127.0.0.1:6379>
```



### 事务错误&回滚

事务执行中的错误分为以下三类：

*   执行时才会出现的错误（简称：执行时错误）；
*   入列时错误，不会终止整个事务；
*   入列时错误，会终止整个事务。



#### 执行时错误

<img src="https://img-blog.csdnimg.cn/2021063011023210.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxOTYwNjIz,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom: 50%;" />

从以上结果可以看出，即使事务队列中某个命令在执行期间发生了错误，事务也会继续执行，直到事务队列中所有命令执行完成。



#### 入列错误不影响事务

<img src="https://img-blog.csdnimg.cn/20210630110420344.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxOTYwNjIz,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:50%;" />  
可以看出，重复执行 multi 会导致入列错误，但不会终止事务，最终查询的结果是事务执行成功了。除了重复执行 multi 命令，还有在事务状态下执行 watch 也是同样的效果，下文会详细讲解关于 watch 的内容。



#### 入列错误导致事务结束

<img src="https://img-blog.csdnimg.cn/2021063011072949.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxOTYwNjIz,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:50%;" />



### 为什么不支持事务回滚

**Redis 官方文档的解释大概的意思是，不支持事务回滚的原因有以下两个**：

*   他认为 Redis 事务的执行时，错误通常都是编程错误造成的，这种错误通常只会出现在开发环境中，而很少会在实际的生产环境中出现，所以他认为没有必要为 Redis 开发事务回滚功能；
*   不支持事务回滚是因为这种复杂的功能和 Redis 追求的简单高效的设计主旨不符合。

**这里不支持事务回滚，指的是不支持运行时错误的事务回滚。**



### 监控

watch 命令用于客户端并发情况下，为事务提供一个乐观锁（CAS，Check And Set），也就是可以用 watch 命令来监控一个或多个变量，如果在事务的过程中，**某个监控项被修改了，那么整个事务就会终止执行**。 watch 基本语法如下：

> watch key \[key …\]

**watch 示例代码如下**：

```
> watch k
OK
> multi
OK
> set k v2
QUEUED
> exec
(nil)
> get k
"v"
```

**注意**： watch 命令只能在客户端开启事务之前执行，在事务中执行 watch 命令会引发错误，但不会造成整个事务失败，如下代码所示：

```
> multi
OK
> set k v3
QUEUED
> watch k
(error) ERR WATCH inside MULTI is not allowed
> exec
1) OK
> get k
"v3"
```

  unwatch 命令用于清除所有之前监控的所有对象（键值对）。 unwatch 示例如下所示：

```
> set k v
OK
> watch k
OK
> multi
OK
> unwatch
QUEUED
> set k v2
QUEUED
> exec
1) OK
2) OK
> get k
"v2"
```

可以看出，即使在事务的执行过程中，k 值被修改了，因为调用了 unwatch 命令，整个事务依然会顺利执行。



## Redis是否支持ACID

**在学习数据库原理的时候有提到过事务的 ACID，即原子性、一致性、隔离性、持久性。接下来，看看 Redis 事务是否支持 ACID**。

原子性，即一个事务中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。Redis 事务不支持原子性，最明显的是 Redis 不支持回滚操作。一致性，在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这一点，Redis 事务能够保证。

隔离性，当两个或者多个事务并发访问（此处访问指查询和修改的操作）数据库的同一数据时所表现出的相互关系。Redis 不存在多个事务的问题，因为 Redis 是单进程单线程的工作模式。

持久性，在事务完成以后，该事务对数据库所作的更改便持久地保存在数据库之中，并且是完全的。Redis 提供两种持久化的方式，即 RDB 和 AOF。RDB 持久化只备份当前内存中的数据集，事务执行完毕时，其数据还在内存中，并未立即写入到磁盘，所以 RDB 持久化不能保证 Redis 事务的持久性。再来讨论 AOF 持久化，我在《深入剖析 Redis AOF 持久化策略》中讨论过：Redis AOF 有后台执行和边服务边备份两种方式。后台执行和 RDB 持久化类似，只能保存当前内存中的数据集；边备份边服务的方式中，因为 Redis 只是每间隔 2s 才进行一次备份，因此它的持久性也是不完整的！

当然，我们可以自己修改源码保证 Redis 事务的持久性，这不难。

还有一个亮点，就是 check-and-set CAS。一个修改操作不断的判断X 值是否已经被修改，直到 X 值没有被其他操作修改，才设置新的值。Redis 借助 WATCH/MULTI 命令来实现 CAS 操作的。

实际操作中，多个线程尝试修改一个全局变量，通常我们会用锁，从读取这个变量的时候就开始锁住这个资源从而阻挡其他线程的修改，修改完毕后才释放锁，这是悲观锁的做法。相对应的有一种乐观锁，乐观锁假定其他用户企图修改你正在修改的对象的概率很小，直到提交变更的时候才加锁，读取和修改的情况都不加锁。一般情况下，不同客户端会访问修改不同的键值对，因此一般 check 一次就可以 set 了，而不需要重复 check 多次。



## Redis缓存与数据库同步

1. 先更新数据库，再更新缓存带来的问题：

   - 两个请求A,B同时更改数据库，A先改完，B后改完，但是B先去修改的缓存，之后A再去修改缓存，这样就导致缓存的是A修改的数据，不是B修改的数据。
   - 两个请求A,B。A请求先更改数据库，还没等修改缓存。B请求先放问到缓存，然后读取了旧数据。

2. 先更新缓存，再更新数据库带来的问题：

   - 和上一个第一条一样
   - 先更新缓存，异步更新数据库会导致数据的丢失，如果持久化做的不好的话。

3. 先删除缓存，后更新数据库带来的问题

   - 请求A先删除缓存，这时候请求B查看缓存，发现没有，先去查询数据库，然后更新缓存，这个时候请求A的更新数据库操作才到。导致缓存里又拿到了旧数据。在「读 + 写」并发的时候，还是会出现缓存和数据库的数据不一致的问题。



**解决方案：先更新数据库，再删除缓存**

   **失效**：应用程序先从cache取数据，没有得到，则从数据库中取数据，成功后，放到缓存中。
   **命中**：应用程序从cache中取数据，取到后返回。
   **更新**：先把数据存到数据库中，成功后，再让缓存失效。

先更新数据库，再删除缓存也是会出现数据不一致性的问题，

<img src="https://mmbiz.qpic.cn/mmbiz_png/J0g14CUwaZed1sKhdbWWdHmnetCfhPiaSbCpD93HnyVTmDnibia5draqMTpRKfsSRndjcf1iaDK2wqqKYnFwwVCanw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom:50%;" />

**但是在实际中，这个问题出现的概率并不高。因为缓存的写入通常要远远快于数据库的写入**，所以在实际中很难出现请求 B 已经更新了数据库并且删除了缓存，请求 A 才更新完缓存的情况。而一旦请求 A 早于请求 B 删除缓存之前更新了缓存，那么接下来的请求就会因为缓存不命中而从数据库中重新读取数据，所以不会出现这种不一致的情况。

   所以从理论上来说，给缓存设置过期时间，是保证最终一致性的解决方案。这种方案下，我们可以对存入缓存的数据设置过期时间，所有的写操作以数据库为准，对缓存操作只是尽最大努力即可。也就是说如果数据库写成功，缓存更新失败，那么只要到达过期时间，则后面的读请求自然会从数据库中读取新值然后回填缓存。



**还有个问题**

如果缓存删除失败的话，那就会频繁访问旧的数据。



**该怎么解决呢？**

有两种方法：重试机制和订阅 `MySQL binlog`，再操作缓存



重试机制

<img src="https://img-blog.csdnimg.cn/20200115174505427.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzNDQ5MzA3,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:50%;" />

`MySQL binlog`

「**先更新数据库，再删缓存**」的策略的第一步是更新数据库，那么更新数据库成功，就会产生一条变更日志，记录在 `binlog` 里。于是我们就可以通过订阅 `binlog` 日志，拿到具体要操作的数据，然后再执行缓存删除。

<img src="https://img-blog.csdnimg.cn/20200115174533970.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMzNDQ5MzA3,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:50%;" />
