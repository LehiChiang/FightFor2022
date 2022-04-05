## 初始准备

表结构:

```mysql
//id是自增主键，name是非唯一索引，balance普通字段
CREATE TABLE `account`  (  
   `id`  int(11) NOT NULL AUTO_INCREMENT,  
   `name` varchar(255) DEFAULT NULL,  
   `balance`  int(11) DEFAULT NULL, 
    PRIMARY KEY (`id`),   
    KEY `idx_name`  (`name`) USING BTREE  
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
```

表中的数据：

|  id  |  name   | balance |
| :--: | :-----: | :-----: |
|  1   |  Eason  |   100   |
|  2   | William |   100   |



## 模拟并发

开启两个终端模拟事务并发情况，执行顺序以及实验现象如下：

![图片](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGAfRLlzb3wnOaibJ5uRRiadqewTkGADPDibicWuyFVhB4vruyyz3AdEv8rqJStnMzxVVic6d0EA8do3DA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

1）事务A执行更新操作，更新成功

```mysql
mysql> update  account set balance =1000  where name ='William';
Query OK,  1 row affected (0.01 sec)
```

2）事务B执行更新操作，更新成功

```mysql
mysql> update  account set balance =1000  where name ='Eason';
Query OK,  1 row affected (0.01 sec)
```

3）事务A执行插入操作，陷入阻塞~

```mysql
mysql> insert into account values(null,'Juliet',100);
```

**这个时候该操作被阻塞，无法执行。**

4）事务B执行插入操作，插入成功，同时事务A的插入由阻塞变为死锁error。

```mysql
mysql> insert into account values(null,'Zack',100);
Query OK,  1 row affected (0.01 sec)
```

<img src="https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGAfRLlzb3wnOaibJ5uRRiadqpxaiaaicA1gsKvHrGmQicLThR9w9l70DC1ySBSy8qicdrruicqobbc6OiajQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片"  />



## 分析加锁情况

### 事务A中update语句的加锁情况

```mysql
update account set balance =1000 where name ='William';
```

**事务A执行完update更新语句，会持有锁：**

*   Next-key Lock：`(E, W]`
    
*   Gap Lock ：`(W, +∞)`
    

### 事务A中insert语句的加锁情况

```mysql
insert into account values(null,'Juliet',100);
```

**间隙锁：**

*   因为Juliet(J在E和W之间)，所以需要请求加`(E,W)`的间隙锁
    

**插入意向锁（Insert Intention）**

*   插入意向锁是在插入一行记录操作之前设置的一种间隙锁，这个锁释放了一种插入方式的信号，即事务A需要插入意向锁`(E,W)`
    

**因此**，事务A的update语句和insert语句执行完，它是持有了 **`(E, W]`的 Next-Key锁**，**(W, +∞)的Gap锁**，想拿到 **(E,W)的插入意向排它锁**



### 事务B中update语句的加锁情况

```mysql
update account set balance =1000 where name ='Eason';
```

**事务B执行完update更新语句，会持有锁：**

*   Next-key Lock：`(-∞, E]`
    
*   Gap Lock ：`(E, W)`
    



### 事务B中insert语句的加锁情况

```mysql
insert into account values(null,'Zack',100);
```

**间隙锁：**

*   因为Zack(Y在W之后)，所以需要请求加`(W,+∞)`的间隙锁
    

**插入意向锁（Insert Intention）**

*   插入意向锁是在插入一行记录操作之前设置的一种间隙锁，这个锁释放了一种插入方式的信号，即事务A需要插入意向锁`(W,+∞)`
    

**所以**，事务B的update语句和insert语句执行完，它是持有了 **（-∞，E\]的 Next-Key锁**，**（E，W）的Gap锁**，想拿到 **(W,+∞)的间隙锁，即插入意向排它锁**



## 总结

接下来呢，让我们一起还原死锁真相吧![图片](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGAfRLlzb3wnOaibJ5uRRiadqfDfGhBVQejHd7GRuwN0wjUmf51S5Kw6JXlhSQ8QGr1gibicWFkea4pKQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

*   事务A执行完Update William的语句，持有（E，W\]的Next-key Lock，（W，+∞）的Gap Lock ，插入成功~
    
*   事务B执行完Update Eason语句，持有（-∞，E\]的 Next-Key Lock，（E，W）的Gap Lock，插入成功~
    
*   事务A执行Insert Juliet的语句时，因为需要（E，W）的插入意向锁，但是（E，W）在事务B怀里，所以它陷入心塞~
    
*   事务B执行Insert Zack的语句时，因为需要(W,+∞) 的插入意向锁，但是(W,+∞) 在事务A怀里，所以它也陷入心塞。
    
*   事务A持有（W，+∞）的Gap Lock，在等待（E，W）的插入意向锁，事务B持有（E，W）的Gap锁，在等待(W,+∞) 的插入意向锁，所以形成了死锁的闭环（Gap锁与插入意向锁会冲突的）
    
*   事务A,B形成了死锁闭环后，因为InnoDB的底层机制，它会让其中一个事务让出资源，另外的事务执行成功，这就是为什么你最后看到事务B插入成功了，但是事务A的插入显示了Deadlock found ~
    
