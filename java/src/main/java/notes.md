## 1. Object类的常见方法总结

Object类是一个特殊的类，是所有类的父类。它主要提供了以下11个方法：

```java
public final native Class<?> getClass()
//native方法，用于返回当前运行时对象的Class对象，使用了final关键字修饰，故不允许子类重写。
public native int hashCode() 
//native方法，用于返回对象的哈希码，主要使用在哈希表中，比如JDK中的HashMap。
public boolean equals(Object obj)
//用于比较2个对象的内存地址是否相等，String类对该方法进行了重写用户比较字符串的值是否相等。
protected native Object clone() throws CloneNotSupportedException
//naitive方法，用于创建并返回当前对象的一份拷贝。一般情况下，对于任何对象 x，表达式 x.clone() != x 为true，x.clone().getClass()== x.getClass() 为true。Object本身没有实现Cloneable接口，所以不重写clone方法并且进行调用的话会发生CloneNotSupportedException异常。
public String toString()
//返回类的名字@实例的哈希码的16进制的字符串。建议Object所有的子类都重写这个方法。
public final native void notify()
//native方法，并且不能重写。唤醒一个在此对象监视器上等待的线程(监视器相当于就是锁的概念)。如果有多个线程在等待只会任意唤醒一个。
public final native void notifyAll()
//native方法，并且不能重写。跟notify一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程。
public final native void wait(long timeout) throws InterruptedException
//native方法，并且不能重写。暂停线程的执行。注意：sleep方法没有释放锁，而wait方法释放了锁 。timeout是等待时间。
public final void wait(long timeout, int nanos) throws InterruptedException
//多了nanos参数，这个参数表示额外时间（以毫微秒为单位，范围是 0-999999）。 所以超时的时间还需要加上nanos毫秒。
public final void wait() throws InterruptedException
//跟之前的2个wait方法一样，只不过该方法一直等待，没有超时时间这个概念
protected void finalize() throws Throwable { }
//实例被垃圾回收器回收的时候触发的操作
```



## 2. 接口和抽象类的区别是什么

1. 接口的方法默认是 public，所有方法在接口中不能有实现(Java 8 开始接口方法可以有默认实现），抽象类可以有非抽象的方法
2. 接口中的实例变量默认是 final 类型的，而抽象类中则不一定
3. 一个类可以实现多个接口，但最多只能实现一个抽象类
4. 一个类实现接口的话要实现接口的所有方法，而抽象类不一定
5. 接口不能用 new 实例化，但可以声明，但是必须引用一个实现该接口的对象
6. 从设计层面来说，抽象是对类的抽象，是一种模板设计，接口是行为的抽象，是一种行为的规范。

在JDK8中，接口也可以定义静态方法，可以直接用接口名调用。实现类和实现是不可以调用的。如果同时实现两个接口，接口中定义了一样的默认方法，必须重写，不然会报错。



## 3. ArrayList 与 LinkedList 异同

1. 是否保证线程安全： ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
2. 底层数据结构： ArrayList 底层使用的是Object数组；LinkedList 底层使用的是双向链表数据结构（JDK1.6之
前为循环链表，JDK1.7取消了循环。）
3. 插入和删除是否受元素位置的影响： ① ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素
位置的影响。 ② LinkedList 采用链表存储，所以插入，删除元素时间复杂度不受元素位置的影响，都是
近似 O（1）而数组为近似 O（n）。
4. 是否支持快速随机访问： LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。
5. 内存空间占用： ArrayList的空 间浪费主要体现在在list列表的结尾会预留一定的容量空间，而LinkedList的空
间花费则体现在它的每一个元素都需要消耗比ArrayList更多的空间（因为要存放直接后继和直接前驱以及数
据）。



## 4. HashMap的底层实现

**JDK1.8之前**

JDK1.8 之前 HashMap 底层是 数组和链表 结合在一起使用也就是链表散列。HashMap 通过 key 的 hashCode 经过扰动函数处理过后得到 hash 值`int hash = hash(key.hashCode())`，然后通过 `(n - 1) & hash` 判断当前元素存放的位置（这里的 n 指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。

所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的
hashCode() 方法 换句话说使用扰动函数之后可以减少碰撞。也就是说hashCode()有时并不十分完美，比如只和高位有关等等，因此需要再次hash()一下。
**JDK 1.8 HashMap 的 hash 方法源码，通过 hashCode() 的高 16 位异或低 16 位实现的：(h = k.hashCode()) ^ (h >>> 16)，主要是从速度，功效和质量来考虑的，减少系统的开销，也不会造成因为高位没有参与下标的计算，从而引起的碰撞。**
JDK 1.8 的 hash方法 相比于 JDK 1.7 hash 方法更加简化，但是原理不变。

```java
static final int hash(Object key) {
int h;
// key.hashCode()：返回散列值也就是hashcode
// ^ ：按位异或
// >>>:无符号右移，忽略符号位，空位都以0补齐
return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

对比一下 JDK1.7的 HashMap 的 hash 方法源码.

```java
static int hash(int h) {
// This function ensures that hashCodes that differ only by
// constant multiples at each bit position have a bounded
// number of collisions (approximately 8 at default load factor).
h ^= (h >>> 20) ^ (h >>> 12);
return h ^ (h >>> 7) ^ (h >>> 4);
}
```

相比于 JDK1.8 的 hash 方法 ，JDK 1.7 的 hash 方法的性能会稍差一点点，因为毕竟扰动了 4 次。



**JDK1.8之后**

相比于之前的版本， JDK1.8之后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。

> TreeMap、TreeSet以及JDK1.8之后的HashMap底层都用到了红黑树。红黑树就是为了解决二叉查找树的缺
> 陷，因为二叉查找树在某些情况下会退化成一个线性结构。



## 5. HashMap 和 Hashtable 的区别

1. 线程是否安全： HashMap 是非线程安全的，Hashtable 是线程安全的；Hashtable 内部的方法基本都经过
   synchronized 修饰。
2. 效率： 因为线程安全的问题，HashMap 要比 Hashtable 效率高一点。另外，Hashtable 基本被淘汰，不要在代码中使用它；
3. 对Null key 和Null value的支持： HashMap 中，null 可以作为键，这样的键只有一个，可以有一个或多个键
所对应的值为 null。但是在 Hashtable 中 put 进的键值只要有一个 null，直接抛出 NullPointerException。
4. HashMap 需要重新计算 hash 值，而 Hashtable 直接使用对象的 hashCode
5. 初始容量大小和每次扩充容量大小的不同 ： ①创建时如果不指定容量初始值，Hashtable 默认的初始大小为
    11，之后每次扩充，容量变为原来的2n+1。**HashMap 默认的初始化大小为16。之后每次扩充，容量变为原来的2倍**。②创建时如果给定了容量初始值，那么 Hashtable 会直接使用你给定的大小，而 **HashMap 会将其扩充为2的幂次方大小。也就是说 HashMap 总是使用2的幂作为哈希表的大小**。
6. 底层数据结构： JDK1.8 以后的 HashMap 在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为
    8）时，将链表转化为红黑树，以减少搜索时间。Hashtable 没有这样的机制。

HashMap 中带有初始容量的构造函数：

```java
public HashMap(int initialCapacity, float loadFactor) {
	if (initialCapacity < 0)
		throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
	if (initialCapacity > MAXIMUM_CAPACITY)
		initialCapacity = MAXIMUM_CAPACITY;
	if (loadFactor <= 0 || Float.isNaN(loadFactor))
		throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
	this.loadFactor = loadFactor;
	this.threshold = tableSizeFor(initialCapacity);
}
public HashMap(int initialCapacity) {
	this(initialCapacity, DEFAULT_LOAD_FACTOR);
}
```

下面这个方法保证了 HashMap 总是使用2的幂作为哈希表的大小。

```java
/**
* Returns a power of two size for the given target capacity.
*/
static final int tableSizeFor(int cap) {
	int n = cap - 1;
	n |= n >>> 1;
	n |= n >>> 2;
	n |= n >>> 4;
	n |= n >>> 8;
	n |= n >>> 16;
	return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```



## 6. HashMap的长度为什么要是2的n次方

HashMap存取时，都需要计算当前key应该对应Entry[]数组哪个元素，即计算数组下标hashCode % table.length取模

HashMap为了存取高效，要尽量较少碰撞，就是要尽量把数据分配均匀，每个链表长度大致相同，这个实现就在把数据存到哪个链表中的算法；
这个算法实际就是取模，hash%length，计算机中直接求余效率不如位移运算，源码中做了优化hash&(length-1)，
**`hash%length==hash&(length-1)`的前提是length是2的n次方；**
为什么这样能均匀分布减少碰撞呢？2的n次方实际就是1后面n个0，2的n次方-1 实际就是n个1；
例如长度为9时候，`3&(9-1)=0 2&(9-1)=0` ，都在0上，碰撞了；
例如长度为8时候，`3&(8-1)=3 2&(8-1)=2` ，不同位置上，不碰撞；



## 7. HashMap 多线程操作导致死循环问题

在多线程下，进行 put 操作会导致 HashMap 死循环，原因在于 HashMap 的扩容 resize()方法。由于扩容是新建一个数组，复制原数据到数组。由于数组下标挂有链表，所以需要复制链表，但是多线程操作有可能导致环形链表。复制链表过程如下:

以下模拟2个线程同时扩容。假设，当前 HashMap 的空间为2（临界值为1），hashCode 分别为 0 和 1，在散列地址 0 处有元素 A 和 B，这时候要添加元素 C，C 经过 hash 运算，得到散列地址为 1，这时候由于超过了临界值，空间不够，需要调用 resize 方法进行扩容，那么在多线程条件下，会出现条件竞争，模拟过程如下：

线程一：读取到当前的 HashMap 情况，在准备扩容时，线程二介入

<img src="..\..\..\..\pics\屏幕截图 2022-03-13 134605.png" alt="屏幕截图 2022-03-13 134605" style="zoom:50%;" />

线程二：读取 HashMap，进行扩容

<img src="..\..\..\..\pics\屏幕截图 2022-03-13 134615.png" alt="屏幕截图 2022-03-13 134615" style="zoom:50%;" />

线程一：继续执行

<img src="..\..\..\..\pics\屏幕截图 2022-03-13 134625.png" alt="屏幕截图 2022-03-13 134625" style="zoom:50%;" />

这个过程为，先将 A 复制到新的 hash 表中，然后接着复制 B 到链头（A 的前边：B.next=A），本来B.next=null，到此也就结束了（跟线程二一样的过程），但是，由于线程二扩容的原因，将 B.next=A，所以，这里继续复制A，让A.next=B，由此，环形链表出现：B.next=A; A.next=B
**注意：jdk1.8已经解决了死循环的问题。**



## 8. HashSet 和 HashMap 区别

1. HashSet实现了Set接口, 仅存储对象; HashMap实现了 Map接口, 存储的是键值对。
2. HashSet 集合不允许存储相同的元素, 它底层实际上使用 HashMap 来存储元素的, 不过关注的只是key元素, 所有 value元素默认为 Object类对象。
3. 判断标准和HashMap判断标准相同, 两个元素的hashCode相等并且通过equals()方法返回true。那么在HashSet中两个对象的hashCode可能相同，所以还得用equals()方法来判断这两个对象是否真的相等。如果两个对象不同的话，那么返回false。

```java
//HashSet底层用来存储元素的结构,实际上使用HashMap来存储
private transient HashMap<E,Object> map;
 
//HashMap中的value值,HashSet只关注key值,所以所有的value值都为Object对象
private static final Object PRESENT = new Object();
 
 
//HashSet的无参构造,直接创建了一个HashMap对象
public HashSet() {
        map = new HashMap<>();
}
 
//指定初始化容量和负载因子
public HashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
}
 
//给定初始化容量
public HashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
}
 
public HashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
}

// 可以看到 HashSet 的 add() 方法底层实际也是调用了 HashMap 的 put() 方法, 这里的key为我们传入的将要添加到 set集合中的元素, 而value值则为 PERSENT,其实就是上面分析的 HashSet类中的一个静态字段, 默认为 Object对象.
public boolean add(E e) {
        return map.put(e, PRESENT)==null;
}
```

当使用无参构造创建 HashSet对象时, 其实调用了 HashMap的无参构造创建了一个 HashMap对象, 所以 HashSet 的初始化容量也为16, 负载因子也为 0.75。



## 9. ConcurrentHashMap和 Hashtable的区别

区别主要体现在实现线程安全的方式上不同

- **底层数据结构：** JDK1.7的 ConcurrentHashMap 底层采用 **分段的数组+链表** 实现，JDK1.8 采用的数据结构跟

  HashMap1.8的结构一样，**数组+链表/红黑二叉树**。Hashtable 和 JDK1.8 之前的 HashMap 的底层数据结构

  类似都是采用 **数组+链表** 的形式。

- **实现线程安全的方式（重要）：**

   ① **ConcurrentHashMap（分段锁）**

  - 在JDK1.7的时候，锁粒度基于 Segment，对整个桶数组进行了分割分段(Segment)，首先将数据分为一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据时，其他段的数据也能被其他线程访问，就不会存在锁竞争，提高并发访问率。**ConcurrentHashMap是由Segment数组结构和HashEntry数组结构组成**。 Segment 实现了 ReentrantLock,所以 Segment 是一种可重入锁，扮演锁的角色。HashEntry 用于存储键值对数据。

    ```java
    static class Segment<K,V> extends ReentrantLock implements Serializable {}
    ```

    一个 ConcurrentHashMap 里包含一个 Segment 数组。Segment 的结构和HashMap类似，是一种数组和链表结构，一个 Segment 包含一个 HashEntry 数组，每个 HashEntry 是一个链表结构的元素，每个 Segment 守护着一个HashEntry数组里的元素，当对 HashEntry 数组的数据进行修改时，必须首先获得对应的 Segment的锁。

    <img src="..\..\..\..\pics\屏幕截图 2022-03-13 111441.png" alt="屏幕截图 2022-03-13 111441" style="zoom:50%;" />

  - 在JDK1.8的时候，锁粒度：Node（首结点），已经摒弃了Segment的概念，而是直接用**Node 数组+链表+红黑树**的数据结构来实现，并发控制使用**synchronized和 CAS** 来操作。**synchronized只锁定当前链表或红黑二叉树的首节点**，这样只要hash不冲突，就不会产生并发，效率又提升N倍。整个看起来就像是优化过且线程安全的 HashMap，虽然在JDK1.8中还能看到 Segment 的数据结构，但是已经简化了属性，只是为了兼容旧版本；

  <img src="..\..\..\..\pics\屏幕截图 2022-03-13 111518.png" alt="屏幕截图 2022-03-13 111518" style="zoom:50%;" />

  

  ② **Hashtable(同一把锁)** :使用 synchronized 来保证线程安全，效率非常低下。当一个线程访问同步方法时，其他线程也访问同步方法，可能会进入阻塞或轮询状态，如使用put 添加元素，另一个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低。

  <img src="..\..\..\..\pics\屏幕截图 2022-03-13 111350.png" alt="屏幕截图 2022-03-13 111350" style="zoom: 50%;" />



## 10. ConcurrentHashMap 在 JDK 1.8 中，为什么synchronized 代替ReentrantLock？ 

①、粒度降低了；

②、JVM 开发团队没有放弃 synchronized，而且基于 JVM 的 synchronized 优化空间更大，更加自然。

③、在大量的数据操作下，对于 JVM 的内存压力，基于 API 的 ReentrantLock 会开销更多的内存。



## 11. HashMap和ConcurrentHashMap的区别？

除了加锁，原理上无太大区别。另外，HashMap 的键值对允许有null，但是ConcurrentHashMap 都不允许。



##  12. synchronized关键字

synchronized关键字解决的是多个线程之间访问资源的同步性，synchronized关键字可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行。synchronized 使用的是非公平锁，并且是不可设置的。这是因为非公平锁的吞吐量大于公平锁，并且是主流操作系统线程调度的基本选择，所以这也是 synchronized 使用非公平锁原由。

另外，在 Java 早期版本中，synchronized属于重量级锁，效率低下，因为监视器锁（monitor）是依赖于底层的操作系统的 Mutex Lock 来实现的，Java 的线程是映射到操作系统的原生线程之上的。如果要挂起或者唤醒一个线程，都需要操作系统帮忙完成，而操作系统实现线程之间的切换时需要从用户态转换到内核态，这个状态之间的转换需要相对比较长的时间，时间成本相对较高，这也是为什么早期的 synchronized 效率低的原因。

在 Java 6 之后Java 官方对从 JVM 层面对synchronized 较大优化，所以现在的 synchronized 锁效率也优化得很不错了。JDK1.6对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销。



## 13. synchronized使用方法

- **修饰实例方法，作用于当前对象实例加锁，进入同步代码前要获得当前对象实例的锁**
- **修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁** 。也就是给当前类加锁，会作用于类的所有对象实例，因为静态成员不属于任何一个实例对象，是类成员（ static 表明这是该类的一个静态资源，不管new了多少个对象，只有一份，所以对该类的所有对象都加了锁）。所以如果一个线程A调用一个实例对象的非静态 synchronized 方法，而线程B需要调用这个实例对象所属类的静态 synchronized 方法，是允许的，不会发生互斥现象，**因为访问静态synchronized方法占用的锁是当前类的锁，而访问非静态synchronized方法占用的锁是当前实例对象锁**。

- **修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。** 和 synchronized 方法一样，synchronized(this)代码块也是锁定当前对象的。**synchronized 关键字加到 static 静态方法和synchronized(class)代码块上都是是给 Class 类上锁**。另外需要注意的是：尽量不要使用 synchronized (String a) 因为JVM中，字符串常量池具有缓冲功能！



## 14. 双重校验锁实现对象单例

```java
public class Singleton {
    
   private volatile static Singleton instance;
    
   private Singleton() {}
    
   public static Singleton getInstance() {
      //先判断对象是否已经实例过，没有实例化过才进入加锁代码
       if (instance == null) { 
           //类对象加锁 
           synchronized (Singleton.class) { 
               if (instance == null) { 
                   instance = new Singleton(); 
               } 
           } 
       }return instance; 
   } 
}
```

需要注意 instance 采用 volatile 关键字修饰也是很有必要。instance = new Singleton(); 这段代码其实是分为三步执行：

1. 为 instance 分配内存空间

2. 初始化 instance

3. 将 instance 指向分配的内存地址

但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出先问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用`getInstance()` 后发现 instance 不为空，因此返回 instance ，但此时 instance 还未被初始化。使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。



## 15. synchronized底层原理

**synchronized关键字底层原理属于JVM层面。**

**①synchronized同步语句块的情况**

```java
public class SynchronizedDemo { 
    public void method() { 
        synchronized (this) { 
            System.out.println("synchronized 代码块"); 
        } 
    } 
}
```

下图展示的是该类的字节码信息：

<img src="..\..\..\..\pics\屏幕截图 2022-03-13 114624.png" alt="屏幕截图 2022-03-13 114624" style="zoom:75%;" />

**synchronized同步语句块的实现使用的是monitorenter和monitorexit指令，其中monitorenter 指令指向同步代码块的开始位置，monitorexit指令则指明同步代码块的结束位置。** 当执行 monitorenter 指令时，线程试图获取锁也就是获取 monitor(monitor对象存在于每个Java对象的对象头中，synchronized 锁便是通过这种方式获取锁的，也是为什么Java中任意对象可以作为锁的原因) 的持有权.当计数器为0则可以成功获取，获取后将锁计数器设为1也就是加1。相应的在执行 monitorexit 指令后，将锁计数器设为0，表明锁被释放。如果获取对象锁失败，那当前线程就要阻塞等待，直到锁被另外一个线程释放为止。



**②synchronized修饰方法的的情况**

```java
public class SynchronizedDemo2 {
	public synchronized void method() {
		System.out.println("synchronized 方法");
    }
}
```

<img src="..\..\..\..\pics\屏幕截图 2022-03-13 115036.png" alt="屏幕截图 2022-03-13 115036" style="zoom:75%;" />

synchronized 修饰的方法并没有 monitorenter 指令和 monitorexit 指令，取得代之的确实是ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法，JVM 通过该 ACC_SYNCHRONIZED 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。



## 16. synchronized底层优化

https://blog.csdn.net/qq_32534441/article/details/88386536



## 17. synchronized和ReentrantLock的区别

- **两者都是可重入锁**

  两者都是可重入锁。“可重入锁”概念是：自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候还是可以获取的，如果不可锁重入的话，就会造成死锁。同一个线程每次获取锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁。

- **synchronized依赖于JVM而ReentrantLock依赖于API**

  synchronized 是依赖于 JVM 实现的，前面我们也讲到了 虚拟机团队在 JDK1.6 为 synchronized 关键字进行了很多优化，但是这些优化都是在虚拟机层面实现的，并没有直接暴露给我们。ReentrantLock 是 JDK 层面实现的，也就是API 层面，需要 lock() 和 unlock 方法配合 try/finally 语句块来完成。

- **synchronized在发生异常时候会自动释放占有的锁**，因此不会出现死锁；而**lock发生异常时候，不会主动释放占有的锁，必须手动unlock来释放锁**，可能引起死锁的发生。（所以最好将同步代码块用try catch包起来，finally中写入unlock，避免死锁的发生。）

- lock等待锁过程中可以用interrupt来中断等待，而synchronized只能等待锁的释放，不能响应中断；

- **ReentrantLock比synchronized增加了一些高级功能**

  相比synchronized，ReentrantLock增加了一些高级功能。主要来说主要有三点：**①等待可中断；②可实现公平锁；③可实现选择性通知（锁可以绑定多个条件）**

  ① **ReentrantLock提供了一种能够中断等待锁的线程的机制**，通过`lock.lockInterruptibly()`来实现这个机制。也就是说正在等待的线程可以选择放弃等待，改为处理其他事情。

  ② **ReentrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。** ReentrantLock默认情况是非公平的，可以通过 ReentrantLock类的

  `ReentrantLock(boolean fair)` 构造方法来制定是否是公平的。

  ③ synchronized关键字与`wait()`和`notify/notifyAll()`方法相结合可以实现等待/通知机制，ReentrantLock类当然也可以实现，但是需要借助于Condition接口与`newCondition()` 方法。Condition是JDK1.5之后才有的，它具有很好的灵活性，比如可以实现多路通知功能也就是在一个Lock对象中可以创建多个Condition实例（即对象监视器），**线程对象可以注册在指定的Condition中，从而可以有选择性的进行线程通知，在调度线程上更加灵活。 在使用`notify/notifyAll()`方法进行通知时，被通知的线程是由 JVM选择的，用ReentrantLock类结合Condition实例可以实现“选择性通知”** ，这个功能非常重要，而且是Condition接口默认提供的。而synchronized关键字就相当于整个Lock对象中只有一个Condition实例，所有的线程都注册在它一个身上。如果执行`notifyAll()`方法的话就会通知所有处于等待状态的线程这样会造成很大的效率问题，而Condition实例的`signalAll()`方法 只会唤醒注册在该Condition实例中的所有等待线程。



## 18. 为什么非公平锁吞吐量大于公平锁？

比如 A 占用锁的时候，B 请求获取锁，发现被 A 占用之后，堵塞等待被唤醒，这个时候 C 同时来获取 A 占用的锁，如果是公平锁 C 后来者发现不可用之后一定排在 B 之后等待被唤醒，而非公平锁则可以让 C 先用，在 B 被唤醒之前 C 已经使用完成，从而节省了 C 等待和唤醒之间的性能消耗，这就是非公平锁比公平锁吞吐量大的原因。



## 19. volatile的作用

volatile是Java虚拟机提供的最轻量级的同步机制。当变量被定义成 volatile 之后，具备两种特性：

- **volatile关键字能保证数据的可见性**，当一条线程修改了这个变量的值，修改的新值对于其他线程是可见的(可以立即得知的)。这是因为在当前的 Java 内存模型下，线程可以把变量保存**本地内存**（比如机器的寄存器）中，而不是直接在主存中进行读写。这就可能造成一个线程在主存中修改了一个变量的值，而另外一个线程还继续使用它在寄存器中的变量值的拷贝，造成**数据的不一致**。

  <img src="D:\shared\Java Program\LeetcodeChallenge\pics\屏幕截图 2022-03-13 133713.png" alt="屏幕截图 2022-03-13 133713" style="zoom:75%;" />

  要解决这个问题，就需要把变量声明为 **volatile**，这就指示 JVM，这个变量是不稳定的，每次使用它都到主存中进行读取。

  <img src="D:\shared\Java Program\LeetcodeChallenge\pics\屏幕截图 2022-03-13 133800.png" alt="屏幕截图 2022-03-13 133800" style="zoom:50%;" />

- **禁止指令重排序优化**，普通变量仅仅能保证在该方法执行过程中，得到正确结果，但是不保证程序代码的执行顺序。



## 20. synchronized和volatile的区别

- volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好。但volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块。synchronized关键字在JavaSE1.6之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的偏向锁和轻量级锁以及其它各种优化之后执行效率有了显著提升，实际开发中使用synchronized关键字的场景还是更多一些。

- 多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞。

- volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证。
- volatile关键字主要用于解决变量在多个线程之间的可见性，而synchronized关键字解决的是多个线程之间访问资源的同步性。



## 21. sleep()和wait()的区别？如何唤醒？

1. sleep是Thread类的方法，wait是Object类的方法。
2. sleep可以用在任意方法中，wait只能用在同步方法或同步块中。
3. sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
4. sleep不出让系统资源。wait是进入线程等待池等待，出让系统资源，其他线程可以占用CPU。
5. wait()使用其他线程调用锁对象的notify()/notifyAll()来唤醒，sleep()调用interrupt()方法或等待超时唤醒。



## 22. ReentrantReadWriteLock

### 1、初识读写锁

　　a）[Java中的锁——Lock和synchronized](https://www.cnblogs.com/fsmly/p/10703804.html)中介绍的ReentrantLock和synchronized基本上都是排它锁，意味着这些锁在同一时刻只允许一个线程进行访问，而读写锁在同一时刻可以允许多个读线程访问，在写线程访问的时候其他的读线程和写线程都会被阻塞。读写锁维护一对锁(读锁和写锁)，通过锁的分离，使得并发性提高。

　　b）关于读写锁的基本使用：在不使用读写锁的时候，一般情况下我们需要使用synchronized搭配等待通知机制完成并发控制（写操作开始的时候，所有晚于写操作的读操作都会进入等待状态），只有写操作完成并通知后才会将等待的线程唤醒继续执行。

　　如果改用读写锁实现，只需要在读操作的时候获取读锁，写操作的时候获取写锁。当写锁被获取到的时候，后续操作（读写）都会被阻塞，只有在写锁释放之后才会执行后续操作。并发包中对ReadWriteLock接口的实现类是ReentrantReadWriteLock，这个实现类具有下面三个特点

　　①具有与ReentrantLock类似的**公平锁和非公平锁**的实现：默认的支持非公平锁，对于二者而言，非公平锁的吞吐量由于公平锁；

　　②支持重入：读线程获取读锁之后能够再次获取读锁，写线程获取写锁之后能再次获取写锁，也可以获取读锁。

　　③锁能降级：遵循获取写锁、获取读锁在释放写锁的顺序，即写锁能够降级为读锁

### 2、读写锁源码分析

#### **a）ReadWriteLock接口中只有两个方法，分别是readLock和writeLock**

```java
public interface ReadWriteLock {
    /**
     * 返回读锁
     */
    Lock readLock();

    /**
     * 返回写锁
     */
    Lock writeLock();
}
```

#### **b）关于读写读写状态的设计**

　　①作为已经实现的同步组件，读写锁同样是需要实现同步器来实现同步功能，同步器的同步状态就是读写锁的读写状态，只是读写锁的同步器需要在同步状态上维护多个读线程和写线程的状态。使用按位切割的方式将一个整形变量按照高低16位切割成两个部分。对比下图，低位值表示当前获取写锁的线程重入两次，高位的值表示当前获取读锁的线程重入一次。读写锁的获取伴随着读写状态值的更新。当低位为0000_0000_0000_0000的时候表示写锁已经释放，当高位为0000_0000_0000_0000的时候表示读锁已经释放。

　　②从下面的划分得到：当**state值不等于0**的时候，如果写状态(state & 0x0000FFFF)等于0的话，读状态是大于0的，表示读锁被获取；如果写状态不等于0的话，读锁没有被获取。这个特点也在源码中实现。

![img](https://img2018.cnblogs.com/blog/1368768/201904/1368768-20190415191724996-535147814.png)

#### **c）写锁writeLock**

　　①上面说到过，读写锁是支持重入的锁，而对于写锁而言还是排他的，这样避免多个线程同时去修改临界资源导致程序出现错误。如果当前线程已经获取了写锁，则按照上面读写状态的设计增加写锁状态的值；如果当前线程在获取写锁的时候，读锁已经被获取或者该线程之前已经有别的线程获取到写锁，当前线程就会进入等待状态。

```java
static final int SHARED_SHIFT   = 16;
static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1; //1左移16位减1=>0000_0000_0000_0000_1111_1111_1111_1111
static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; } //返回读状态的值
protected final boolean tryAcquire(int acquires) {
        /*
         * Walkthrough:
         * 1. 如果读状态不为0或者写状态不为0并且写线程不是自己，返回false
         * 2. 如果已经超过了可重入的计数值MAX_COUNT，就会返回false
         * 3. 如果该线程是可重入获取或队列策略允许，则该线程有资格获得锁定;同时更新所有者和写锁状态值
         */
        Thread current = Thread.currentThread(); //获取当前线程
        int c = getState(); //获取当前写锁状态值
        int w = exclusiveCount(c); //获取写状态的值
        //当同步状态state值不等于0的时候，如果写状态(state & 0x0000FFFF)等于0的话，读状态是大于0的，表示读锁被获取
        if (c != 0) {
            if (w == 0 || current != getExclusiveOwnerThread())
                return false;
            if (w + exclusiveCount(acquires) > MAX_COUNT) //如果已经超过了可重入的计数值MAX_COUNT，就会返回false
                throw new Error("Maximum lock count exceeded");
            // 重入锁：更新状态值
            setState(c + acquires);
            return true;
        }
        if (writerShouldBlock() ||
            !compareAndSetState(c, c + acquires))
            return false;
        setExclusiveOwnerThread(current);
        return true;
    }
```

　　②分析一下上面的写锁获取源码

　　tryAcquire中线程获取写锁的条件：读锁没有线程获取，写锁被获取并且被获取的线程是自己，那么该线程可以重入的获取锁，而判断读锁是否被获取的条件就是（**当同步状态state值不等于0的时候，如果写状态(state & 0x0000FFFF)等于0的话，读状态是大于0的，表示读锁被获取**）。对于读写锁而言，需要保证写锁的更新结果操作对读操作是可见的，这样的话写锁的获取就需要保证其他的读线程没有获取到读锁。

　　③写锁的释放源码

　　写锁的释放和ReentrantLock的锁释放思路基本相同，从源码中可以看出来，每次释放都是减少写状态，直到写状态值为0(**exclusiveCount(nextc) == 0**)的时候释放写锁，后续阻塞等待的读写线程可以继续竞争锁。

```java
static final int SHARED_SHIFT   = 16;
static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1; //1左移16位减1=>0000_0000_0000_0000_1111_1111_1111_1111
static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; } //返回读状态的值
protected final boolean tryRelease(int releases) {
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    int nextc = getState() - releases;
    boolean free = exclusiveCount(nextc) == 0; //写状态值为0，就释放写锁，并将同步状态的线程持有者置为null，然后更新状态值
    if (free)
        setExclusiveOwnerThread(null);
    setState(nextc);
    return free;
}
```

#### d）读锁readLock

　　①读锁是同样是支持重入的，除此之外也是共享式的，能够被多个线程获取。在同一时刻的竞争队列中，如果没有写线程想要获取读写锁，那么读锁总会被读线程获取到(然后更新读状态的值)。每个读线程都可以重入的获取读锁，而对应的获取次数保存在本地线程中，由线程自身维护该值。

　　②获取读锁的条件：其他线程已经获取了写锁，则当前线程获取读锁会失败而进入等待状态；如果当前线程获取了写锁或者写锁没有被获取，那么就可以获取到读锁，并更细同步状态（读状态值）。

　　③读锁的每次释放都是减少读状态，

#### f）锁的降级

　　锁降级的概念：如果当先线程是写锁的持有者，并保持获得写锁的状态，同时又获取到读锁，然后释放写锁的过程。（注意不同于这样的分段过程：当前线程拥有写锁，释放掉写锁之后再获取读锁的过程，这种分段过程不能称为锁降级）。

```java
class CachedData {
  Object data;
  volatile boolean cacheValid;
  final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

  void processCachedData() {
    // 获取读锁
    rwl.readLock().lock();
    if (!cacheValid) {
      // 在获取写锁之前必须释放读锁，不释放的话下面写锁会获取不成功，造成死锁
      rwl.readLock().unlock();
     // 获取写锁
      rwl.writeLock().lock();
      try {
        // 重新检查state，因为在获取写锁之前其他线程可能已经获取写锁并且更改了state
        if (!cacheValid) {
          data = ...
          cacheValid = true;
        }
        // 通过在释放写锁定之前获取读锁定来降级
        // 这里再次获取读锁，如果不获取，那么当写锁释放后可能其他写线程再次获得写锁，导致下方`use(data)`时出现不一致的现象
        // 这个操作就是降级
        rwl.readLock().lock();
      } finally {
        rwl.writeLock().unlock(); // 释放写锁，由于在释放之前读锁已经被获取，所以现在是读锁获取状态
      }
    }

    try {
    // 使用完后释放读锁
      use(data);
    } finally {
      rwl.readLock().unlock(); //释放读锁
    }
  }
 }}
```

 

## 23. java多线程的实现方式有4种

1. 继承Thread类，重写run方法；

2. 实现Runnable接口，重写run方法；

3. 实现Callable接口通过FutureTask包装器来创建Thread线程；

4. 通过线程池创建线程；

   

## 24. 为什么要用线程池

线程池主要用来解决线程生命周期开销问题和资源不足问题。通过对多个任务重复使用线程，线程创建的开销就被分摊到多个任务上，而且由于在请求到达时线程已经存在，所以消除线程创建所带来的延迟。这样，就可以立即为请求服务，使应用程序响应更快。另外，通过适当的调整线程中的线程数目可以防止出现资源不足。

- **降低资源消耗。** 通过重复利用已创建的线程降低线程创建和销毁造成的消耗。

- **提高响应速度。** 当任务到达时，任务可以不需要的等到线程创建就能立即执行。

- **提高线程的可管理性。** 线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。



## 25. 如何创建线程池

参考文章：[Java并发编程：线程池的使用 - Matrix海子 - 博客园 (cnblogs.com)](https://www.cnblogs.com/dolphin0520/p/3932921.html)

在`java.util.concurrent`包中我们能找到线程池的定义，其中ThreadPoolExecutor是我们线程池核心类，首先看看线程池类的主要参数有哪些。

#### 核心类介绍

```java
/**
 * Creates a new {@code ThreadPoolExecutor} with the given initial
 * parameters and default thread factory.
 */
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
}

```

- corePoolSize：线程池的核心大小，**也可以理解为最小的线程池大小**。
- maximumPoolSize：最大线程池大小。
- keepAliveTime：空余线程存活时间，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。
- unit：销毁时间单位。
- workQueue：存储等**待执行线程的工作队列**。这里的阻塞队列有以下几种选择：`ArrayBlockingQueue`，`LinkedBlockingQueue`， `SynchronousQueue`; `ArrayBlockingQueue`和`PriorityBlockingQueue`使用较少，一般使用`LinkedBlockingQueue`和`SynchronousQueue`。线程池的排队策略与`BlockingQueue`有关。
- threadFactory：创建线程的工厂，一般用默认即可。
- handler：拒绝策略，当工作队列、线程池全已满时如何拒绝新任务，默认抛出异常。

其他的一些核心API如下：

```java
private final ReentrantLock mainLock = new ReentrantLock();   //线程池的主要状态锁，对线程池状态（比如线程池大小、runState等）的改变都要使用这个锁
private final HashSet<Worker> workers = new HashSet<Worker>();  //用来存放工作集

private volatile boolean allowCoreThreadTimeOut;   //是否允许为核心线程设置存活时间
 
private volatile int   poolSize;       //线程池中当前的线程数
 
private int largestPoolSize;   //用来记录线程池中曾经出现过的最大线程数
 
private long completedTaskCount;   //用来记录已经执行完毕的任务个数
```



#### **线程池状态**

　在ThreadPoolExecutor中定义了一个volatile变量，另外定义了几个static final变量表示线程池的各个状态：

```java
volatile int runState;
static final int RUNNING    = 0;
static final int SHUTDOWN   = 1;
static final int STOP       = 2;
static final int TERMINATED = 3;
```

 runState表示当前线程池的状态，它是一个volatile变量用来保证线程之间的可见性；下面的几个static final变量表示runState可能的几个取值。

- 当创建线程池后，初始时，线程池处于RUNNING状态；
- 如果调用了shutdown()方法，则线程池处于SHUTDOWN状态，此时线程池不能够接受新的任务，它会等待所有任务执行完毕；
- 如果调用了shutdownNow()方法，则线程池处于STOP状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务；
- 当线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为TERMINATED状态。



#### **线程池工作流程**

1. 如果当前线程池中的线程数目<corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
2. 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
3. 如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
4. 如果工作队列workQueue也满时，当线程数小于最大线程池数maximumPoolSize时就会创建新线程来处理，而线程数大于等于最大线程池数maximumPoolSize时就会执行拒绝策略。



#### **线程池分类**

Executors是jdk里面提供的创建线程池的工厂类，它默认提供了4种常用的线程池应用，而不必我们去重复构造。

- `newFixedThreadPool`
  固定线程池，核心线程数和最大线程数固定相等，而空闲存活时间为0毫秒，说明此参数也无意义，工作队列为最大为`Integer.MAX_VALUE`大小的阻塞队列。当执行任务时，如果线程都很忙，就会丢到工作队列等有空闲线程时再执行，队列满就执行默认的拒绝策略。

  ```java
  public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
          return new ThreadPoolExecutor(nThreads, nThreads,
                                        0L, TimeUnit.MILLISECONDS,
                                        new LinkedBlockingQueue<Runnable>(),
                                        threadFactory);
  }
  
  ```

  

- `newCachedThreadPool`

  带缓冲线程池，从构造看核心线程数为0，最大线程数为Integer最大值大小，超过0个的空闲线程在60秒后销毁，`SynchronousQueue`这是一个直接提交的队列，意味着每个新任务都会有线程来执行，如果线程池有可用线程则执行任务，没有的话就创建一个来执行，线程池中的线程数不确定，一般建议执行速度较快较小的线程，不然这个最大线程池边界过大容易造成内存溢出。

  ```java
  public static ExecutorService newCachedThreadPool() {
          return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                        60L, TimeUnit.SECONDS,
                                        new SynchronousQueue<Runnable>());
  }
  
  ```

  

- `newSingleThreadExecutor`

  单线程线程池，核心线程数和最大线程数均为1，空闲线程存活0毫秒同样无意思，意味着每次只执行一个线程，多余的先存储到工作队列，一个一个执行，保证了线程的顺序执行。

  ```java
  public static ExecutorService newSingleThreadExecutor() {
      return new FinalizableDelegatedExecutorService
          (new ThreadPoolExecutor(1, 1,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>()));
  }
  
  ```

  

- `newScheduledThreadPool`

  调度线程池，即按一定的周期执行任务，即定时任务，对ThreadPoolExecutor进行了包装而已。

  ```java
  public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
      return new ScheduledThreadPoolExecutor(corePoolSize);
  }
  
  ```

  

#### **线程池中的线程初始化**

默认情况下，创建线程池之后，线程池中是没有线程的，需要提交任务之后才会创建线程。

在实际中如果需要线程池创建之后立即创建线程，可以通过以下两个方法办到：

- prestartCoreThread()：初始化一个核心线程；
- prestartAllCoreThreads()：初始化所有核心线程



#### **拒绝策略**

- `ThreadPoolExecutor.AbortPolicy`
  简单粗暴，直接抛出拒绝异常，这也是默认的拒绝策略。
- `ThreadPoolExecutor.CallerRunsPolicy`
  如果线程池未关闭，则会在调用者线程中直接执行新任务，这会导致主线程提交线程性能变慢。
- `ThreadPoolExecutor.DiscardPolicy`
  从方法看没做任务操作，即表示不处理新任务，即丢弃。
- `ThreadPoolExecutor.DiscardOldestPolicy`
  抛弃最老的任务，就是从队列取出最老的任务然后放入新的任务进行执行。



#### **线程池容量的动态调整**

ThreadPoolExecutor提供了动态调整线程池容量大小的方法：setCorePoolSize()和setMaximumPoolSize()，

- setCorePoolSize：设置核心池大小
- setMaximumPoolSize：设置线程池最大能创建的线程数目大小

当上述参数从小变大时，ThreadPoolExecutor进行线程赋值，还可能立即创建新的线程来执行任务。



#### **如何提交线程**

可以先随便定义一个固定大小的线程池

```java
ExecutorService es = Executors.newFixedThreadPool(3);
```

提交一个线程

```java
es.submit(xxRunnble);
es.execute(xxRunnble);
```



#### **submit和execute分别有什么区别呢？**

1. execute没有返回值，如果不需要知道线程的结果就使用execute方法，性能会好很多。
2. submit返回一个Future对象，如果想知道线程结果就使用submit提交，而且它能在主线程中通过Future的get方法捕获线程中的异常。



#### **execute()到底方法是如何处理的？**

1. 获取当前线程池的状态。
2. 当前线程数量小于coreSize时创建一个新的线程运行。
3. 如果当前线程处于运行状态，并且写入阻塞队列成功。
4. 双重检查，再次获取线程状态；如果线程状态变了（非运行状态）就需要从阻塞队列移除任务，并尝试判断线程是否全部执行完毕，同时执行拒绝策略。
5. 如果当前线程池为空就新创建一个线程并执行。
6. 如果在第三步的判断为非运行状态，尝试新建线程，如果失败则执行拒绝策略。



#### **如何关闭线程池**

- `es.shutdown()`; 不再接受新的任务，之前提交的任务等执行结束再关闭线程池。

- `es.shutdownNow()`；不再接受新的任务，试图停止池中的任务再关闭线程池，返回所有未处理的线程list列表。



#### 使用案例

```java
public class Test {
     public static void main(String[] args) {   
         ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                 new ArrayBlockingQueue<Runnable>(5));
          
         for(int i=0;i<15;i++){
             MyTask myTask = new MyTask(i);
             executor.execute(myTask);
             System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
             executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
         }
         executor.shutdown();
     }
}
 
 
class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
```

运行结果：

```
正在执行task 0
线程池中线程数目：1，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
线程池中线程数目：2，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
正在执行task 1
线程池中线程数目：3，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
正在执行task 2
线程池中线程数目：4，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
正在执行task 3
线程池中线程数目：5，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
正在执行task 4
线程池中线程数目：5，队列中等待执行的任务数目：1，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：2，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：3，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：4，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
线程池中线程数目：6，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行task 10
线程池中线程数目：7，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行task 11
线程池中线程数目：8，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行task 12
线程池中线程数目：9，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行task 13
线程池中线程数目：10，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行task 14
task 3执行完毕
task 0执行完毕
task 2执行完毕
task 1执行完毕
正在执行task 8
正在执行task 7
正在执行task 6
正在执行task 5
task 4执行完毕
task 10执行完毕
task 11执行完毕
task 13执行完毕
task 12执行完毕
正在执行task 9
task 14执行完毕
task 8执行完毕
task 5执行完毕
task 7执行完毕
task 6执行完毕
task 9执行完毕
```

**从执行结果可以看出，当线程池中线程的数目大于5时，便将任务放入任务缓存队列里面，当任务缓存队列满了之后，便创建新的线程。如果上面程序中，将for循环中改成执行20个任务，就会抛出任务拒绝异常了。**

《阿里巴巴Java开发手册》中强制线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。

>  Executors 返回线程池对象的弊端如下：
>
> - **FixedThreadPool** **和** **SingleThreadExecutor** ： 允许请求的队列长度为 Integer.MAX_VALUE,可能堆积大量的请求，从而导致OOM。
> - **CachedThreadPool** **和** **ScheduledThreadPool** ： 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM。



#### 如何合理配置线程池的大小

一般需要根据任务的类型来配置线程池大小：

- 如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 *N*CPU+1
- 如果是IO密集型任务，参考值可以设置为2**N*CPU

当然，这只是一个参考值，具体的设置还需要根据实际情况进行调整，比如可以先将线程池大小设置为参考值，再观察任务运行情况和系统负载、资源利用率来进行适当调整。



## 26. 线程池中为什么要使用阻塞队列？

当阻塞队列为空的时候，从队列中取元素的操作就会被阻塞。当阻塞队列满的时候，往队列中放入元素的操作就会被阻塞。而后，一旦空队列有数据了，或者满队列有空余位置时，被阻塞的线程就会被自动唤醒。这就是阻塞队列的好处，你不需要关心线程何时被阻塞，也不需要关心线程何时被唤醒，一切都由阻塞队列自动帮我们完成。我们只需要关注具体的业务逻辑就可以了。而这种阻塞队列经常用在生产者消费者模式中。



## 27. Runnable接口和Callable接口的区别

如果想让线程池执行任务的话需要实现的Runnable接口或Callable接口。 Runnable接口或Callable接口实现类都可以被ThreadPoolExecutor或ScheduledThreadPoolExecutor执行。两者的区别在于 

- Runnable 接口不会返回结果但是 Callable 接口可以返回结果。
- Callable规定的方法是`call()`，而Runnable规定的方法是`run()`。
- `call()`方法可抛出异常，而`run()`方法是不能抛出异常的。 



## 28. Callable如何使用

Callable任务返回Future对象。即：Callable和Future一个产生结果，一个拿到结果。

Future 表示异步计算的结果。Future接口中有如下方法：

```java
boolean cancel(boolean mayInterruptIfRunning)
```

取消任务的执行。参数指定是否立即中断任务执行，或者等等任务结束

```java
boolean isCancelled() 
```

任务是否已经取消，任务正常完成前将其取消，则返回 true

```java
boolean isDone()
```

任务是否已经完成。需要注意的是如果任务正常终止、异常或取消，都将返回true

```java
V get()
```

等待任务执行结束，然后获得V类型的结果。InterruptedException 线程被中断异常， ExecutionException任务执行异常，如果任务被取消，还会抛出CancellationException

```java
V get(long timeout, TimeUnit unit) 
```

同上面的get功能一样，多了设置超时时间。参数timeout指定超时时间，uint指定时间的单位，在枚举类TimeUnit中有相关的定义。如果计算超时，将抛出TimeoutException

Future接口提供方法来检测任务是否被执行完，等待任务执行完获得结果。也可以设置任务执行的超时时间，这个设置超时的方法就是实现Java程序执行超时的关键。

所以，如果需要设定代码执行的最长时间，即超时，可以用Java线程池ExecutorService类配合Future接口来实现。

```java
import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new TaskThread(0, 1, 150));
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {

        }finally{
            executor.shutdown();
        }
    }

    static class TaskThread implements Callable<Integer> {

        private int step, times;
        private int res;

        public TaskThread(int start, int step, int times) {
            this.step = step;
            this.times = times;
            this.res = start;
        }

        @Override
        public Integer call() {
            for (int i = 0; i < times; i++) {
                res += step;
            }
            return res;
        }
    }
}

```



## 29. 介绍一下Atomic 原子类

所谓原子类说简单点就是具有原子/原子操作特征的类。并发包 `java.util.concurrent` 的原子类都存放在`java.util.concurrent.atomic` 下。



## 30. 讲讲 AtomicInteger 的使用

AtomicInteger 类常用方法

```java
public final int get() //获取当前的值
public final int getAndSet(int newValue)//获取当前的值，并设置新的值
public final int getAndIncrement()//获取当前的值，并自增
public final int getAndDecrement() //获取当前的值，并自减
public final int getAndAdd(int delta) //获取当前的值，并加上预期的值
boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置为输
入值（update）
public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能导致其他线程
在之后的一小段时间内还是可以读到旧的值。
```

AtomicInteger 类的使用示例
使用 AtomicInteger 之后，不用对 increment() 方法加锁也可以保证线程安全。

AtomicInteger 类主要利用 CAS (compare and swap) + volatile 和 native 方法来保证原子操作，从而避免synchronized 的高开销，执行效率大为提升。



乐观锁是一种非阻塞的同步机制。在并发环境下，某个线程对共享变量先进行操作，如果没有其他线程争用共享数据那操作就成功；如果存在数据的争用冲突，那就才去补偿措施，比如不断的重试机制，直到成功为止，因为这种乐观的并发策略不需要把线程挂起，也就把这种同步操作称为非阻塞同步（操作和冲突检测具备原子性）



`AtomicInteger.incrementAndGet()`方法，它的实现也比较简单

```java
public final int incrementAndGet() {
    for (;;) {
        int current = get();
        int next = current + 1;
        if (compareAndSet(current, next))
            return next;
    }
}
```

`incrementAndGet()`方法在一个无限循环体内，不断尝试将一个比当前值大1的新值赋给自己，如果失败则说明在执行"获取-设置"操作的时已经被其它线程修改过了，于是便再次进入循环下一次操作，直到成功为止。这个便是AtomicInteger原子性的"诀窍"了，继续进源码看它的`compareAndSet`方法:

```java
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
```

可以看到，`compareAndSet()`调用的就是`Unsafe.compareAndSwapInt()`方法，即`Unsafe`类的`CAS`操作。



## 31. 什么是CAS机制

CAS机制是一种数据更新的方式。同时指令层面上提供的原子操作。CAS机制可以以非阻塞同步的方式解决多线程并发编程对共享变量读写的原子性问题。其中乐观锁的实现就是基于CAS的。

乐观锁更新方式认为:在更新数据的时候其他线程争抢这个共享变量的概率非常小，所以更新数据的时候不会对共享数据加锁。但是在正式更新数据之前会检查数据是否被其他线程改变过，如果未被其他线程改变过就将共享变量更新成最新值，如果发现共享变量已经被其他线程更新过了，就重试，直到成功为止。

CAS，是Compare and Swap的简称，在这个机制中有三个核心的参数：

- 主内存中存放的共享变量的值：V（一般情况下这个V是内存的地址值，通过这个地址可以获得内存中的值）
- 工作内存中共享变量的副本值，也叫预期值：A
- 需要将共享变量更新到的最新值：B

![img](https://pic1.zhimg.com/80/v2-f32df3fde2dc0132c05533572e2b659c_720w.jpg)

如上图中，主存中保存V值，线程中要使用V值要先从主存中读取V值到线程的工作内存A中，然后计算后变成B值，最后再把B值写回到内存V值中。多个线程共用V值都是如此操作。CAS的核心是在将B值写入到V之前要比较A值和V值是否相同，如果不相同证明此时V值已经被其他线程改变，重新将V值赋给A，并重新计算得到B，如果相同，则将B值赋给V。



## 32. CAS使用场景

- 使用一个变量统计网站的访问量；
- Atomic类操作；
- 自旋锁
- 数据库乐观锁更新。



## 33. CAS机制优缺点

### 缺点

**1. ABA问题**
ABA问题：CAS在操作的时候会检查变量的值是否被更改过，如果没有则更新值，但是带来一个问题，最开始的值是A，接着变成B，最后又变成了A。经过检查这个值确实没有修改过，因为最后的值还是A，但是实际上这个值确实已经被修改过了。为了解决这个问题，在每次进行操作的时候加上一个版本号，每次操作的就是两个值，一个版本号和某个值，A——>B——>A问题就变成了1A——>2B——>3A。在jdk中提供了AtomicStampedReference类解决ABA问题，用Pair这个内部类实现，包含两个属性，分别代表版本号和引用，在compareAndSet中先对当前引用进行检查，再对版本号标志进行检查，只有全部相等才更新值。

**2. 可能会消耗较高的CPU**
看起来CAS比锁的效率高，从阻塞机制变成了非阻塞机制，减少了线程之间等待的时间。每个方法不能绝对的比另一个好，在线程之间竞争程度大的时候，如果使用CAS，每次都有很多的线程在竞争，也就是说CAS机制不能更新成功。这种情况下CAS机制会一直重试，这样就会比较耗费CPU。因此可以看出，如果线程之间竞争程度小，使用CAS是一个很好的选择；但是如果竞争很大，使用锁可能是个更好的选择。在并发量非常高的环境中，如果仍然想通过原子类来更新的话，可以使用AtomicLong的替代类：LongAdder。

**3. 不能保证代码块的原子性**
Java中的CAS机制只能保证共享变量操作的原子性，而不能保证代码块的原子性。

### 优点

- 可以保证变量操作的原子性；
- 并发量不是很高的情况下，使用CAS机制比使用锁机制效率更高；
- 在线程对共享资源占用时间较短的情况下，使用CAS机制效率也会较高。



## 34. 什么是AQS

`AQS`的全称为（`AbstractQueuedSynchronizer`），这个类在`java.util.concurrent.locks`包下面。

`AQS`是一个用来构建锁和同步器的框架，使用`AQS`能简单且高效地构造出应用广泛的大量的同步器，比如我们提到的`ReentrantLock`，`Semaphore`，`ReentrantReadWriteLock`等等皆是基于`AQS`的。当然，我们自己也能利用`AQS`非常轻松容易地构造出符合我们自己需求的同步器。



### AQS的实现方式

AQS使用一个`int`成员变量来表示同步状态，通过内置的FIFO队列来完成获取资源线程的排队工作。AQS使用CAS对该同步状态进行原子操作实现对其值的修改。如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制AQS是用CLH队列锁实现的，即将暂时获取不到锁的线程加入到队列中。

> CLH(Craig,Landin,and Hagersten)队列是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）。AQS是将每条请求共享资源的线程封装成一个CLH锁队列的一个结点（Node）来实现锁的分配。

下面是AQS(AbstractQueuedSynchronizer)原理图：

<img src="..\..\..\..\pics\屏幕截图2022-03-22.PNG" alt="屏幕截图2022-03-22" style="zoom:60%;" />

```java
private volatile int state;//共享变量，使用volatile修饰保证线程可见性
```

状态信息通过protected类型的getState，setState，compareAndSetState进行操作

```java
//返回同步状态的当前值
protected final int getState() {
    return state;
}
// 设置同步状态的值
protected final void setState(int newState) {
    state = newState;
}
//原子地（CAS操作）将同步状态值设置为给定值update如果当前同步状态的值等于expect（期望值）
protected final boolean compareAndSetState(int expect, int update) {
    return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}
```



### AQS 对资源的共享方式

- Exclusive（独占）：只有一个线程能执行，如ReentrantLock。又可分为公平锁和非公平锁：

  - 公平锁：按照线程在队列中的排队顺序，先到者先拿到锁

  - 非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的

- Share（共享）：多个线程可同时执行，如Semaphore/CountDownLatch。Semaphore、ReadWriteLock 我们都会在后面讲到。
      

ReentrantReadWriteLock 可以看成是组合式，因为ReentrantReadWriteLock也就是读写锁允许多个线程同时对某一资源进行读。

不同的自定义同步器争用共享资源的方式也不同。**自定义同步器在实现时只需要实现共享资源 state 的获取与释放方式即可，至于具体线程等待队列的维护（如获取资源失败入队/唤醒出队等），AQS已经在顶层实现好了。**



### AQS的模板方法

同步器的设计是基于模板方法模式的，如果需要自定义同步器一般的方式是：

- 使用者继承AbstractQueuedSynchronizer并重写指定的方法。（这些重写方法很简单，无非是对于共享资源state的获取和释放）

- 将AQS组合在自定义同步组件的实现中，并调用其模板方法，而这些模板方法会调用使用者重写的方法。

自定义同步器时需要重写下面几个AQS提供的模板方法：

```java
isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
```

默认情况下，每个方法都抛出 `UnsupportedOperationException` 。 这些方法的实现必须是内部线程安全的，并且通常应该简短而不是阻塞。**AQS类中的其他方法都是final ，所以无法被其他类使用，只有这几个方法可以被其他类使用。**

**ReentrantLock为例**，state初始化为0，表示未锁定状态。A线程lock()时，会调用tryAcquire()独占该锁并将state+1。此后，其他线程再tryAcquire()时就会失败，直到A线程unlock()到state=0（即释放锁）为止，其它线程才有机会获取该锁。当然，释放锁之前，A线程自己是可以重复获取此锁的（state会累加），这就是可重入的概念。但要注意，获取多少次就要释放多么次，这样才能保证state是能回到零态的。

**Semaphore(信号量)**-允许多个线程同时访问： synchronized 和 ReentrantLock 都是一次只允许一个线程访问某个资源，Semaphore(信号量)可以指定多个线程同时访问某个资源。

**CountDownLatch （倒计时器）**： CountDownLatch是一个同步工具类，用来协调多个线程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。

**一般来说，自定义同步器要么是独占方法，要么是共享方式，他们也只需实现tryAcquire-tryRelease 、tryAcquireShared-tryReleaseShared 中的一种即可。但AQS也支持自定义同步器同时实现独占和共享两种方式，如ReentrantReadWriteLock 。**



### AQS实现案例

```java
package juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex implements java.io.Serializable {
    //静态内部类，继承AQS
    private static class Sync extends AbstractQueuedSynchronizer {
        //是否处于占用状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        //当状态为0的时候获取锁，CAS操作成功，则state状态为1，
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        //释放锁，将同步状态置为0
        protected boolean tryRelease(int releases) {
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }
        //同步对象完成一系列复杂的操作，我们仅需指向它即可
        private final Sync sync = new Sync();
        //加锁操作，代理到acquire（模板方法）上就行，acquire会调用我们重写的tryAcquire方法
        public void lock() {
            sync.acquire(1);
        }
        public boolean tryLock() {
            return sync.tryAcquire(1);
        }
        //释放锁，代理到release（模板方法）上就行，release会调用我们重写的tryRelease方法。
        public void unlock() {
            sync.release(1);
        }
        public boolean isLocked() {
            return sync.isHeldExclusively();
        }
}
```



## 35. ThreadLocal讲解

### 什么是ThreadLocal

```java
private ThreadLocal myThreadLocal = new ThreadLocal();
```

ThreadLoal 变量，线程局部变量，同一个 ThreadLocal 所包含的对象，在不同的 Thread 中有不同的副本。这里有几点需要注意：

- 因为每个 Thread 内有自己的实例副本，且该副本只能由当前 Thread 使用。这是也是 ThreadLocal 命名的由来。
- 既然每个 Thread 有自己的实例副本，且其它 Thread 不可访问，那就不存在多线程间共享的问题。

ThreadLocal 提供了线程本地的实例。它与普通变量的区别在于，每个使用该变量的线程都会初始化一个完全独立的实例副本。ThreadLocal 变量通常被private static修饰。当一个线程结束时，它所使用的所有 ThreadLocal 相对的实例副本都可被回收。

总的来说，ThreadLocal 适用于每个线程需要自己独立的实例且该实例需要在多个方法中被使用，也即变量在线程间隔离而在方法或类间共享的场景。



### 怎么使用ThreadLocal

下面是一个完整的可执行的ThreadLocal例子：

```java
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            try {
            Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) {
         MyRunnable sharedRunnableInstance = new MyRunnable();
         Thread thread1 = new Thread(sharedRunnableInstance);
         Thread thread2 = new Thread(sharedRunnableInstance);
         thread1.start();
         thread2.start();
    }

}
```



### 关于InheritableThreadLocal

InheritableThreadLocal类是ThreadLocal类的子类。ThreadLocal中每个线程拥有它自己的值，与ThreadLocal不同的是，InheritableThreadLocal允许一个线程以及该线程创建的所有子线程都可以访问它保存的值。



### ThreadLocal实现原理

因为一个线程内可以存在多个 ThreadLocal 对象，所以其实是 ThreadLocal 内部维护了一个 Map ，这个 Map 不是直接使用的 HashMap ，而是 ThreadLocal 实现的一个叫做 ThreadLocalMap 的静态内部类。而我们使用的 get()、set() 方法其实都是调用了这个ThreadLocalMap类对应的 get()、set() 方法。

例如下面的 set 方法：

```java
    public void set(T value) {
        Thread t = Thread.currentThread();
        // 通过当前线程来获取ThreadLocalMap，然后再在Map里面根据不同的ThreadLocal设置不同的值
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }

    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }
```

get方法

```java
    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }

    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }
```

可以看到getMap(Thread)方法直接返回Thread实例的成员变量threadLocals。它的定义在Thread内部，访问级别为package级别：

```java

public class Thread implements Runnable {
    /* Make sure registerNatives is the first thing <clinit> does. */
    private static native void registerNatives();
    static {
        registerNatives();
    }

    /* ThreadLocal values pertaining to this thread. This map is maintained
     * by the ThreadLocal class. */
    ThreadLocal.ThreadLocalMap threadLocals = null;
    
}
```

到了这里，我们可以看出，每个Thread里面都有一个ThreadLocal.ThreadLocalMap成员变量，也就是说每个线程通过ThreadLocal.ThreadLocalMap与ThreadLocal相绑定，这样可以确保每个线程访问到的thread-local variable都是本线程的。

createMap方法：

```java
    void createMap(Thread t, T firstValue) {   
        t.threadLocals = new ThreadLocalMap(this, firstValue);   
    } 
```

initialValue设置初值

```java
    protected T initialValue() {
        return null;
    }
```

ThreadLocalMap是个静态的内部类：

```java
public class ThreadLocal<T> {

    static class ThreadLocalMap {

        static class Entry extends WeakReference<ThreadLocal> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal k, Object v) {
                super(k);
                value = v;
            }
        }

        /**
         * The initial capacity -- MUST be a power of two.
         */
        private static final int INITIAL_CAPACITY = 16;

        /**
         * The table, resized as necessary.
         * table.length MUST always be a power of two.
         */
        private Entry[] table;

        /**
         * The number of entries in the table.
         */
        private int size = 0;

        /**
         * The next size value at which to resize.
         */
        private int threshold; // Default to 0

        ThreadLocalMap(ThreadLocal firstKey, Object firstValue) {
            table = new Entry[INITIAL_CAPACITY];
            int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
            table[i] = new Entry(firstKey, firstValue);
            size = 1;
            setThreshold(INITIAL_CAPACITY);
        }
    }
    
}
```

最终的变量是放在了当前线程的 `ThreadLocalMap` 中，并不是存在 ThreadLocal 上，ThreadLocal 可以理解为只是ThreadLocalMap的封装，传递了变量值。

接下来我们来看ThreadLocalMap 的set方法的实现：

```java
private void set(ThreadLocal key, Object value) {
    Entry[] tab = table;
    int len = tab.length;
    int i = key.threadLocalHashCode & (len-1);

    for (Entry e = tab[i];
         e != null;
         e = tab[i = nextIndex(i, len)]) {
        ThreadLocal k = e.get();

        if (k == key) {
            e.value = value;
            return;
        }

        if (k == null) {
            replaceStaleEntry(key, value, i);
            return;
        }
    }

    tab[i] = new Entry(key, value);
    int sz = ++size;
    if (!cleanSomeSlots(i, sz) && sz >= threshold)
        rehash();
}
```

ThreadLocal 的get方法会调用 ThreadLocalMap 的 getEntry(ThreadLocal key) ，其源码如下：

```java
private Entry getEntry(ThreadLocal key) {
    int i = key.threadLocalHashCode & (table.length - 1);
    Entry e = table[i];
    if (e != null && e.get() == key)
        return e;
    else
        return getEntryAfterMiss(key, i, e);
}

private Entry getEntryAfterMiss(ThreadLocal key, int i, Entry e) {
    Entry[] tab = table;
    int len = tab.length;

    while (e != null) {
        ThreadLocal k = e.get();
        if (k == key)
            return e;
        if (k == null)
            expungeStaleEntry(i);
        else
            i = nextIndex(i, len);
        e = tab[i];
    }
    return null;
}
```

