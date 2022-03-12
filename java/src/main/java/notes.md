## Object类的常见方法总结

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



## 接口和抽象类的区别是什么

1. 接口的方法默认是 public，所有方法在接口中不能有实现(Java 8 开始接口方法可以有默认实现），抽象类可以有非抽象的方法
2. 接口中的实例变量默认是 final 类型的，而抽象类中则不一定
3. 一个类可以实现多个接口，但最多只能实现一个抽象类
4. 一个类实现接口的话要实现接口的所有方法，而抽象类不一定
5. 接口不能用 new 实例化，但可以声明，但是必须引用一个实现该接口的对象
6. 从设计层面来说，抽象是对类的抽象，是一种模板设计，接口是行为的抽象，是一种行为的规范。

在JDK8中，接口也可以定义静态方法，可以直接用接口名调用。实现类和实现是不可以调用的。如果同时实现两个接口，接口中定义了一样的默认方法，必须重写，不然会报错。



## ArrayList 与 LinkedList 异同

1. 是否保证线程安全： ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
2. 底层数据结构： Arraylist 底层使用的是Object数组；LinkedList 底层使用的是双向链表数据结构（JDK1.6之
前为循环链表，JDK1.7取消了循环。）
3. 插入和删除是否受元素位置的影响： ① ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素
位置的影响。 ② LinkedList 采用链表存储，所以插入，删除元素时间复杂度不受元素位置的影响，都是
近似 O（1）而数组为近似 O（n）。
4. 是否支持快速随机访问： LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。
5. 内存空间占用： ArrayList的空 间浪费主要体现在在list列表的结尾会预留一定的容量空间，而LinkedList的空
间花费则体现在它的每一个元素都需要消耗比ArrayList更多的空间（因为要存放直接后继和直接前驱以及数
据）。



## HashMap的底层实现

**JDK1.8之前**

JDK1.8 之前 HashMap 底层是 数组和链表 结合在一起使用也就是链表散列。HashMap 通过 key 的 hashCode 经
过扰动函数处理过后得到 hash 值`int hash = hash(key.hashCode())`，然后通过 `(n - 1) & hash` 判断当前元素存放的位置（这里的 n 指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。

所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的
hashCode() 方法 换句话说使用扰动函数之后可以减少碰撞。也就是说hashCode()有时并不十分完美，比如只和高位有关等等，因此需要再次hash()一下。
JDK 1.8 HashMap 的 hash 方法源码:
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



## HashMap 和 Hashtable 的区别

1. 线程是否安全： HashMap 是非线程安全的，Hashtable 是线程安全的；Hashtable 内部的方法基本都经过
   synchronized 修饰。

2. 效率： 因为线程安全的问题，HashMap 要比 Hashtable 效率高一点。另外，Hashtable 基本被淘汰，不要在代码中使用它；
3. 对Null key 和Null value的支持： HashMap 中，null 可以作为键，这样的键只有一个，可以有一个或多个键
所对应的值为 null。但是在 Hashtable 中 put 进的键值只要有一个 null，直接抛出 NullPointerException。
4. 初始容量大小和每次扩充容量大小的不同 ： ①创建时如果不指定容量初始值，Hashtable 默认的初始大小为
11，之后每次扩充，容量变为原来的2n+1。**HashMap 默认的初始化大小为16。之后每次扩充，容量变为原来的2倍**。②创建时如果给定了容量初始值，那么 Hashtable 会直接使用你给定的大小，而 **HashMap 会将其扩充为2的幂次方大小。也就是说 HashMap 总是使用2的幂作为哈希表的大小**。
5. 底层数据结构： JDK1.8 以后的 HashMap 在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为
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



## HashMap的长度为什么要是2的n次方

HashMap存取时，都需要计算当前key应该对应Entry[]数组哪个元素，即计算数组下标hashcode % table.length取模

HashMap为了存取高效，要尽量较少碰撞，就是要尽量把数据分配均匀，每个链表长度大致相同，这个实现就在把数据存到哪个链表中的算法；
这个算法实际就是取模，hash%length，计算机中直接求余效率不如位移运算，源码中做了优化hash&(length-1)，
**`hash%length==hash&(length-1)`的前提是length是2的n次方；**
为什么这样能均匀分布减少碰撞呢？2的n次方实际就是1后面n个0，2的n次方-1 实际就是n个1；
例如长度为9时候，`3&(9-1)=0 2&(9-1)=0` ，都在0上，碰撞了；
例如长度为8时候，`3&(8-1)=3 2&(8-1)=2` ，不同位置上，不碰撞；



## HashMap 多线程操作导致死循环问题

在多线程下，进行 put 操作会导致 HashMap 死循环，原因在于 HashMap 的扩容 resize()方法。由于扩容是新建一个数组，复制原数据到数组。由于数组下标挂有链表，所以需要复制链表，但是多线程操作有可能导致环形链表。复制链表过程如下:

以下模拟2个线程同时扩容。假设，当前 HashMap 的空间为2（临界值为1），hashcode 分别为 0 和 1，在散列地址 0 处有元素 A 和 B，这时候要添加元素 C，C 经过 hash 运算，得到散列地址为 1，这时候由于超过了临界值，空间不够，需要调用 resize 方法进行扩容，那么在多线程条件下，会出现条件竞争，模拟过程如下：

线程一：读取到当前的 HashMap 情况，在准备扩容时，线程二介入

![image-20220312235623313](C:\Users\LehiChiang\AppData\Roaming\Typora\typora-user-images\image-20220312235623313.png)

线程二：读取 HashMap，进行扩容

![image-20220312235830245](C:\Users\LehiChiang\AppData\Roaming\Typora\typora-user-images\image-20220312235830245.png)

线程一：继续执行

![image-20220312235858879](C:\Users\LehiChiang\AppData\Roaming\Typora\typora-user-images\image-20220312235858879.png)

这个过程为，先将 A 复制到新的 hash 表中，然后接着复制 B 到链头（A 的前边：B.next=A），本来B.next=null，到此也就结束了（跟线程二一样的过程），但是，由于线程二扩容的原因，将 B.next=A，所以，这里继续复制A，让A.next=B，由此，环形链表出现：B.next=A; A.next=B
**注意：jdk1.8已经解决了死循环的问题。**



## HashSet 和 HashMap 区别

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