## 第3章 Java的基本程序设计结构

### 3.3 数据类型

在Java中共有8种基本类型，其中包括4种整形，2种浮点类型，1种字符类型`char`，和一种`boolean`类型。

#### 3.3.1 整型

| 类型    | 存储需求  | 取值范围 |
|-------|-------| ------ |
| int   | 4字节   | -2^32 - 1 ~ 2^32 |
| short | 2字节   | -2^16 - 1 ~ 2^16 |
| char  | 2/4字节 | -2^16 - 1 ~ 2^16 |
| long  | 8字节   | -2^64 - 1 ~ 2^64 |
| byte  | 1字节   | -2^8 - 1 ~ 2^8 |

从Java7开始，加上前缀`0b`或`0B`就可以写二进制数。例如，`0b1001`就是`9`。另外，同样也是从Java 7开始，还可以为数字字面量加下划线，如用`1_000_000`
表示100万。这些下划线只是为了让人更容易阅读。Java编译器会去除这些下划线。

#### 3.3.2 浮点类型

float类型的数值有一个后缀`F`或`f`（例如，`3.14F`）。没有后缀`F`的浮点值总默认是`double`类型的。当然也可以在浮点值后缀加`D`或`d`。

#### 3.3.3 char类型

`char`类型原本用于表示单个字符。不过，现在有些`Unicode`字符可以用一个`char`描述，另外一些`Unicode`字符则需要两个`char`值。

`char`类型的值可以表示为十六进制值，其范围从`\u0000`到`\uFFFF`。 例如，`\u2122`或者`"Hello\n"`。

#### 3.3.5 boolean类型

在C/C++种，数值指针可以代替`boolean`值。值`0`可以相当于布尔值`false`，非`0`相当于布尔值`true`。在Java中则**不是**这样！不能通过编译。数字`int`和`boolean`
是两个类型。

### 3.4 变量与常量

#### 3.4.3 常量

关键字`final`表示这个变量只能被赋值一次。一旦被赋值之后，就不能再更改了。**习惯上，常量名使用全大写。** 可以使用`static final`设置一个类常量，下面是示例：

```java
public class Person {
    public static String NATIONALITY = "China";
    public static void main(String[] args) {
        ...
    }
}
```

### 3.6 字符串

从概念上讲，java字符串就是Unicode字符序列。例如，字符串`"Java\u2111"`由5个Unicode字符`J`,`a`,`v`,`a`,`TM`组成。

#### 3.6.2 字符串拼接

需要把很多字符串拼接到一起，而可以使用静态方法`join`。

```java
String all = String.join(",", "hello", "world", "haha");
```

输出：

```java
hello,world,haha
```

Java 11还提供了新方法，可以拼接重复字符串。

```java
String all = "java".repeat(5);
```

输出：

```java
javajavajavajavajava
```

Java字符串的某一位值是不能改变的。所以在java文档中将`String`类对象成为是不可变的`(immutable)`。不可变字符串有一个优点：**编译器可以让字符串共享**
。如果复制一个字符串变量，原始字符串与复制的字符串共享相同的字符。

#### 3.6.4 检测字符串是否相等

如果想检测两个字符串是否相等，不区分大小写，可以使用`equalsIgnoreCase`方法。

#### 3.6.7 String API

- 如果字符串为空或者由空格组成，返回`true`

```java
boolean blank() [Java 11]
```

#### 3.6.9 构建字符串

`StringBuilder`在Java 5中引入的。这个类的前身是`StringBuffer`
，它的效率稍微有点低，但允许采用多线程的方式添加或删除字符。如果字符串的编辑操作都在一个单线程上执行（通常是这样），则应该使用`StringBuilder`。这两个类的`API`是一样的。

**`StringBuilder`是线程不安全的，`StirngBuffer`是线程安全的**!!!

`StringBuffer`和`StringBuilder`的实现内部是和`String`内部一样的，都是通过`char[]`数组的方式；不同的是`String`的`char[]`数组是通过`final`
关键字修饰的是不可变的，而`StringBuffer`和`StringBuilder`的`char[]`数组是可变的。

`StringBuilder`和`StringBuffer`都继承了`AbstractStringBuilder`

`StringBuilder`的`append`方法:

```java
    public StringBuilder append(String str) {
        super.append(str);
        return this;
    }
```

`StringBuilder`的`append`方法调用了父类的`append`方法

```java
1   public AbstractStringBuilder append(String str) {
2       if (str == null) {
3           return appendNull();
4       }
5       int len = str.length();
6       ensureCapacityInternal(count + len);
7       putStringAt(count, str);
8       count += len;
9       return this;
10  }
```

我们直接看第八行代码，`count += len;`不是一个原子操作，所以线程不安全。

```java
public StringBuilder delete(int start, int end)
```

删除偏移量从`start`到`end-1`的代码单元并返回当前对象。

### 3.10 数组

#### 3.10.4 数组拷贝

在Java中允许将一个数组变量拷贝到另外一个数组变量中。这时，两个变量将引用同一个数组：

```java
int[] numbers = oldNumbers;
numbers[0]=12; // oldNumbers[0]也等于12
```

如果希望将一个数组的所有值拷贝到一个新的数组中去，就需要使用`Arrays`类的`copyOf`方法。

`java.util.Arrays`的一些其他的`API`：

```java
// 二分搜索
public static int binarySearch(int[] a, int key)

// 将数组的所有数据元素都设置为val
public static void fill(int[] a, int val)

// 如果两个数组大小相同，并且下标相同的元素都对应相等，返回true
public static boolean equals(int[] a, int[] a2)
```

想快速打印数组，不管一维还是二维的。可以调用`Arrays.deepToString(object)`方法：

```java
Arrays.deepToString(new int[][]{{1,2},{3,4}})
```

输出：

```java
[[1, 2], [3, 4]]
```

