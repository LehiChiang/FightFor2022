## 第6章 接口，lambda表达式与内部类

### 6.1 接口

#### 6.1.1 接口的概念

接口不是类，而是对希望符合这个接口的类的一组需求。

“如果你的类符合某个特定的接口，我就会履行这项服务。” 比如`Arrays`类中的`sort`方法承诺可以对对象数组排序，但要求对象所属的类必须实现`Comparable`接口。

```java
public interface Comparable<T> {
    int compareTo(T other);
}
```

**接口中的所有方法都自动是`public abstract`方法。因此，在接口声明方法时，不必提供关键字`public`。**

**接口可以定义常量；**

**接口绝对不能用实例字段。**

**可以将接口看成没有实例字段的抽象类。**



#### 6.1.2 接口的属性

接口变量必须引用实现了这个接口的类对象：

可以使用`instanceof`检查一个对象是否实现了某个特定的接口：

```java
if (object instanceof Comparable) {...}
```

接口中的字段总是`public static final`修饰的。

接口可以有多条接口链



#### 6.1.3 接口与抽象类

与接口相比，抽象类通常是对同类事物相对具体的抽象，通常包含抽象方法，实体方法，属性变量。如果一个抽象类只有一个抽象方法，那么它就等同于一个接口。抽象类在被继承时体现的是`is-a`的关系，接口在被实现时体现的是`can-do`的关系。实现类要有能力去实现并执行接口中定义的行为。

接口是顶级的“类”，虽然关键字是`interface`，但是编译之后的字节码扩展名还是`.class`。抽象类是二当家，接口位于顶层，而抽象类对各个接口进行了组合，然后实现部分接口行为。例如下面的最典型的抽象类：

```java
public abstract class AbstractCollection<E> implements Collection<E>{
    // Collection中定义的抽象方法，但本类中没有实现
    // size()这个方法对于链表和顺序表有不同的实现方式
    public abstract int size();
    
    // 实现了Collection中定义的抽象方法，因为对于所有AbstractCollection子类，它们判空的方式是一致的。
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
```

当纠结定义接口还是方法时，推荐定义接口，遵循接口隔离原则，这样做可方便后续的扩展和重构。



#### 6.1.4 静态和私有方法

在Java 8中，允许在接口中增加静态方法。目前为止，通常的做法都是将静态方法放在伴随类里。在Java 11中，`Path`接口提供了静态方法：

```java
public interface Path extends Comparable<Path>, Iterable<Path>, Watchable {
    static Path of(String first, String... more) {...}
    static Path of(URI uri) {...}
}
```

这样的好处就是不用再实现伴随类了！

在Java 9中，接口中的方法可以是`private`。`private`方法可以是静态方法或实例方法。由于私有方法只能在接口本身的方法中使用，所有它们只能作为接口中其他方法的辅助方法。



#### 6.1.5 默认方法

可以为接口方法提供默认实现。必须用`default`修饰符标记这样一个方法。默认方法也可以调用其他方法。如下所示：

```java
public interface Path extends Comparable<Path>, Iterable<Path>, Watchable {
    
    ...

    boolean startsWith(Path var1);

    default boolean startsWith(String other) {
        return this.startsWith(this.getFileSystem().getPath(other));
    }

    boolean endsWith(Path var1);

    default boolean endsWith(String other) {
        return this.endsWith(this.getFileSystem().getPath(other));
    }
}

```

默认方法的一个重要作用是“接口演化”。例如存在以下实现关系：

```java
public class Bag implements Collection
```

后来，在Java 8中，又为这个接口增加了一个`stream`方法。假设`stream`方法不是一个默认方法，那么`Bag`类将无法编译成功，因为没有实现这个新的方法。为接口增加一个默认方法能够保证代码的兼容。另外使用了默认方法的话，如果没有重新编译而直接加载这个类，并在一个`Bag`实例上调用了`stream`方法，将调用`Collection.stream`方法。

如果先在一个接口中将一个方法定义为默认方法，然后又在超类或者另一个接口中定义同样的方法，Java的解决方法如下：

1. **超类优先**。如果超类提供了一个具体方法，同名而且有相同参数的默认方法会被忽略。

2. 接口冲突。如果实现的两个或多个接口中都提供了同名同参数的默认方法，那么必须手动重写这个方法来解决问题。

   ```java
   interface Person {
       default String getName(){...}
   }
   
   interface Named {
       default String getName(){...}
   }
   
   class Student implements Person, Named {
       
       // 重写冲突方法，让程序员来解决冲突
       public String getName() {
           return Person.super.getName();
       }
   }
   ```

   如果`Named`接口没有为`getName`提供默认实现。那么Java同样规定，如果至少有一个接口提供了一个实现，编译器就会报告错误，程序员同样也必须解决这个冲突。



#### 6.1.7 接口与回调

回调（callback）是一种常见的程序设计模式。可以指定某个特定事件发生时应该采取的动作。用传入实现了接口的对象来代替传入方法这样比较灵活。因为这个对象的类实现了某个接口，所以这样回调就与接口结合了起来。例如：

```java
public class code_6_1 {
    public static void main(String[] args) {
        var printer = new TimePrinter();
        // 将采取的动作放入方法中，这个方法在接口里面。传入的时候要传入实现类的对象。
        var timer = new Timer(1000, printer);
        timer.start();
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getWhen());
    }
}
```



#### 6.1.8 两种比较器

分别是`Comparable<T>`和`Comparator<T>`两个，具体的方法如下：

```java
public interface Comparable<T> {
    int compareTo(T var1);
}
```



```Java
public interface Comparator<T> {
    int compare(T var1, T var2);
}
```



### 6.2 Lambda表达式

因为Java语言和其他语言的不同，在Java中传递一个代码段不容易，你不能直接传递代码段。Java是面向对象的语言，所以必须构造一个对象，这个对象的类需要有一个方法包含所需的代码。这样写起来比较反锁，为了简化这一过程，Java引入`lambda`表达式。

#### 6.2.2 Lambda表达式的语法

**`lambda`表达式就是一个代码块**，代码块语法如下：

```java
() -> {}
```



#### 6.2.3 函数式接口

对于**只有一个抽象方法**的接口，需要这种接口的对象时，就可以提供一个`lambda`表达式。这种接口称为函数式接口。

**注意：只要这个接口中有一个抽象的方法就可以提供`lambda`表达式，不管这个接口中有没有默认方法！**

`Java`中重要的函数接口：

| 接口                 | 参数     | 返回类型    | 抽象方法名 | 示例                                     |
| -------------------- | -------- | ----------- | ---------- | ---------------------------------------- |
| `Predicate<T>`       | **T**    | **boolean** | test       | 用来判断真假的函数接口                   |
| `Consumer<T>`        | **T**    | **void**    | accept     | 用于处理参数的函数接口                   |
| `Runnable`           | **None** | **void**    | run        | 用于多线程中啊                           |
| `Supplier<T>`        | **None** | **T**       | get        | 用于提供一个类型为T的函数接口            |
| `Function<T, R>`     | **T**    | **R**       | apply      | 接受一个T类型的变量，返回一个R类型的变量 |
| `UnaryOperator<T>`   | **T**    | **T**       | apply      | 类型T上的一元操作符                      |
| `BinaryOperation<T>` | **T,T**  | **T**       | apply      | 类型T上的二元操作符                      |

```java
public static void main(String[] args) {
    BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
    processBinaryOperator(binaryOperator);
}

private static void processBinaryOperator(BinaryOperator<Integer> binaryOperator) {
    System.out.println(binaryOperator.apply(3, 5));
}
```



#### 6.2.4 方法引用

有时，`lambda`表达式只涉及一个方法。那我们可以使用方法引用来赋值给函数式接口！！类似于`lambda`表达式，方法引用也不是一个对象。不过，为一个类型为函数式接口的变量赋值时会生成一个对象。例如：

```java
var timer = new Timer(1000, event -> System.out.println(event));
```

可以写成

```java
var timer = new Timer(1000, System.out::println);
```

`System.out::println`这就是一个方法的引用。编译器会从10个重载的`println()`中选出与`ActionEvent`最匹配的方法来执行。现在把同样的方法赋值给一个不同的函数式接口：

```java
Runnable task = System.out::println;
task.run();
```

这个`Runnable`接口只有一个无参数的抽象方法`run()`，所有会选择无参数的`println()`方法执行，打印一个空行。

注意，只有当lambda表达式的体只调用一个方法而不做其他操作时，才能把lambda表达式写成方法的引用。

```java
s -> s.length() == 0
```

这里就不能用方法引用。

可以在方法的引用中使用`this`参数。例如，`this::equals`等同于`x -> this.equals(x)`。使用`super`也是合法的，这会调用给定方法的超类版本：

```java
class Greeter {
    public void greet() {...}
}

class RepeatedGreeter extends Greeter {
    public void greet() {
        var time = new Timer(1000, super::greet);
        time.start();
    }
}
```



#### 6.2.6 变量作用域

**lambda表达式中捕获的变量必须是不变的变量，这个变量初始化之后就不会再为它赋新值。**例如：

```java
public class code_6_2_6 {
    public static void main(String[] args) {
        repeatMessage("hello", 1000);
    }

    private static void repeatMessage(String hello, int delay) {
        new Timer(delay, event -> {
            System.out.println(event);
            Toolkit.getDefaultToolkit().beep();
        }).start();
    }
}
```

在这个例子中，这个`lambda`表达式有一个自由变量`text`。表示`lambda`表达式的数据结构必须存储自由变量的值，在这里字符串就是`"hello"`。我们说它被`lambda`表达式捕获。这就是把一个`lambda`表达式转换为包含一个方法的对象，这样自由变量的值就会复制到这个对象的实例变量中。

可以看到，`lambda`表达式可以捕获外围作用域中变量的值。另外如果在表达式中引用一个变量，而这个变量在外部可能改变，这也是不合法的。

`lambda`表达式的体与嵌套块有相同的作用域。`lambda`表达式中同样也不能有同名的局部变量。

在`lambda`表达式中使用`this`关键字，是指创建这个`lambda`表达式的方法的`this`参数。而不是接口的实例方法的`this`参数。



#### 6.2.7 处理lambda表达式

前面已经了解了如何生成`lambda`表达式，以及如何把`lambda`表达式传递到需要一个函数式接口的方法。下面看看如何编写方法处理`lambda`表达式。

下面看个简单的例子。假设你想要重复一个动作`n`次。那么可以有以下几种写法：

第一种写法：

```java
interface MyInterface {
    void print(String s);
}

class MyClass implements MyInterface {
    @Override
    public void print(String s) {
        System.out.println(s);
    }
}

public class code_6_2_7 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        repeat(10, myClass, "hello");
    }

    private static void repeat(int time, MyInterface myInterface, String s) {
        for (int i = 0; i < time; i++)
            myInterface.print(s);
    }
}
```

第二种方法：

```java
interface MyInterface {
    void print();
}

public class code_6_2_7 {
    public static void main(String[] args) {
        repeat(10, new MyInterface() {
            @Override
            public void print() {
                System.out.println("hello");
            }
        });
    }

    private static void repeat(int time, MyInterface myInterface) {
        for (int i = 0; i < time; i++)
            myInterface.print();
    }
}
```

第三种方法：

```java
interface MyInterface {
    void print();
}

public class code_6_2_7 {
    public static void main(String[] args) {
        repeat(10, () -> System.out.println("hello"));
    }

    private static void repeat(int time, MyInterface myInterface) {
        for (int i = 0; i < time; i++)
            myInterface.print();
    }
}
```

第四种方法：

```java
interface MyInterface {
    void print(String s);
}

public class code_6_2_7 {
    public static void main(String[] args) {
        repeat(10, System.out::println, "hello");
    }

    private static void repeat(int time, MyInterface myInterface, String s) {
        for (int i = 0; i < time; i++)
            myInterface.print(s);
    }
}
```

除了自定义的接口实现方法，也可以用现成的接口来处理`lambda`表达式。要接受这个`lambda`表达式，需要选择（偶尔需要提供）一个函数式接口。表中列出了`Java API`中的最重要的函数式接口。在这里我们使用`Runnable`接口，第五种写法如下：

```java
public class code_6_2_7 {
    public static void main(String[] args) {
        repeat(10, () -> System.out.println("hello"));
    }

    private static void repeat(int time, Runnable action) {
        for (int i = 0; i < time; i++)
            action.run();
    }
}
```

再来看下面这个例子：

```java
public class code_6_2_7 {
    public static void main(String[] args) {
        repeat(() -> {
            Random n = new Random();
            int num = n.nextInt(10);
            System.out.println(num < 5 ? num * 100 : num);
        });
    }

    private static void repeat(Runnable myInterface) {
        for (int i = 0; i < 10; i++)
            myInterface.run();
    }
}
```

**总结：**可以看到`repeat()`接收的是一个函数，这个函数要通过包装在对象里，并且对象继承相应的接口来实现。使用`lambda`表达式的重点是”延迟执行“。比如在某种情况下，再执行代码，那么就需要使用`lambda`表达式。



### 6.3 内部类

- 内部类可以对同一个包中的其他类隐藏。
- 内部类方法可以访问定义这个类的作用域中的数据，包括原本的私有的数据。



#### 6.3.1 使用内部类访问对象状态

**虽然内部类写在外部类中，但这不意味着每个外部的类对象都有一个内部类的实例字段。内部类对象是由外部类的方法构造的。**

一个内部类方法可以自由地访问自身的数据字段，也可以访问创建它的外围类对象的数据字段。

```java
class TalkingClock {
    private final int interval;
    private final boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {}

    public class TimePrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getWhen());
            if (beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}
```

因此，内部类的对象总有一个隐式引用，指向创建它的外部类对象。内部类可以私有！

```java
public class code_6_3_1 {
    public static void main(String[] args) {
        var clock = new TalkingClock(1000, true);
        clock.start();
        JOptionPane.showMessageDialog(null, "Quit?");
        System.exit(0);
    }
}

class TalkingClock {
    private final int interval;
    private final boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        // 这个时候将外部类和内部类进行了关联。这个时候编译器就会将当前TalkingClock的this引用传递给这个构造器
        var listener = new TimePrinter(this);
        var time = new Timer(interval, listener);
        time.start();
    }

    private class TimePrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getWhen());
            if (beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}
```



#### 6.3.2 内部类的特殊语法规则

内部类还可以使用这样的方法访问外部的变量

```java
    private class TimePrinter implements ActionListener {
        ...
        if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
       ...
    }
```

同时外部类在创建内部类对象时，也可以这么写

```java
    public void start() {
        var listener = this.new TimePrinter();
        ...
    }
```

这样可以通过显示地命名将外围类的引用设置为其他的对象。

如果`TimePrinter`是一个公共内部类，对于任意的`TalkingClock`都可以这样构造一个`TimePrinter`：

```java
var jabberer = new TalkingClock(100, true);
TalkingClock.TimePrinter listener = jabberer.new TimePrinter();
```

**内部类中声明的所有静态字段都必须是`final`的，并初始化为一个编译时常量。**

**内部类不能有`static`方法。**



#### 6.3.3 内部类是否有用，必要和安全

内部类是一个编译器现象，与虚拟机无关。编译器会将内部类转换为常规的类文件，用`$`分割外部类名与内部类名，而虚拟机对此一无所知。

例如，`TalkingClock`类内部的`TimePrinter`类将被转化成常规的单独的类文件`TalkingClock$TimePrinter.class`。

通过反射机制可以看到，在经过编译之后，`TimePrinter`会生成这样的字段：

```java
final innerClass.TalkingClock this$0;
```

编译器额外生成了实例字段`this$0`，对应外围类的引用。

同理，通过反射可以看到编译后的`TalkingClock`会增加一个方法

```java
static boolean access$0(TalkingClock)
```

`boolean`类型对应返回的`beep`字段，内部类调用的方法

```java
if (beep)
```

实际会产生这样的调用

```java
if (TalkingClock.access$0(outer))
```



#### 6.3.4 局部内部类

```java
    public void start() {
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getWhen());
                if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
            }
        }
        
        var listener = new TimePrinter();
        var time = new Timer(interval, listener);
        time.start();
    }
```

**可以在一个方法中局部定义这个类！局部内部类不能有访问权限符。作用域也只限定在这个`start`的代码块中。**



#### 6.3.6 匿名内部类

匿名内部类使用数字进行编号，而局部内部类在类名前面还有一个编号来标识是哪个类。

```java
public class OuterClass {
    public static void main(String[] args) {
        // 两个匿名内部类，编译后的class文件名称为OuterClass$1和OuterClass$2
        (new Thread(){}).start();
        (new Thread(){}).start();
        
        // 两个局部内部类，编译后的class文件名称为OuterClass$1MethodClass1和OuterClass$1MethodClass2
        class MethodClass1 {}
        class MethodClass2 {}
    }
}
```

匿名内部类的语法如下：

```java
new SuperType(construction parameters) {
	inner class methods and data
}
```

其中，

- `SuperType`是个接口，那么内部类在编译时要实现这个接口。并且不能有任何构造参数！内部类也不能有构造器。但仍然需要提供小括号。
- `SuperType`是个类，内部类在编译时要扩展这个类。这个情况要注意！如下：

```java
var queen = new Person("Marry");
// a Person object
var king = new Person("David") {...};
// an object of an inner class extending Person
```

尽管匿名类不能有构造器，但可以提供一个对象初始化块：

```java
var king = new Person("David") {
    {initialization}
};
```



#### 6.3.7 静态内部类

如果不需要内部类有外部类对象的一个引用，那么可以将内部类声明为`static`，这样就不会生成那个引用。

在静态方法中构建的内部类必须是静态的内部类。比如：

```java
public class code_6_3_7 {
    public static void main(String[] args) {
        var array = new int[20];
        for (int i = 0; i < 20; i++)
            array[i] = (int) (100 * Math.random());
        ArrayAlg.Pair pair = ArrayAlg.minmax(array);
        System.out.println(pair.getFirstNum());
        System.out.println(pair.getSecondNum());
    }
}

class ArrayAlg {

    public static class Pair {
        private final int firstNum;
        private final int secondNum;
        public Pair(int firstNum, int secondNum) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
        }

        public int getFirstNum() {
            return firstNum;
        }

        public int getSecondNum() {
            return secondNum;
        }
    }

    public static Pair minmax(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int v : array) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
```



### 6.5 代理

三种代理方式：**静态代理，动态代理，`CGlib`代理**。

#### **静态代理**

```java
import java.util.Date;

interface PrintService {
    void print();
}

/**
 * 原始的PrintService接口实现类，但我们想对这里的功能进行扩展，并且想保持
 * 这个实现类的封装性，所以我们使用静态代理类来扩展这个方法。
 */
class PrintServiceImpl implements PrintService {

    @Override
    public void print() {
        System.out.println("Start printing!");
    }
}

/**
 * 代理类，对PrintServiceImpl类的封装和增强。代理类是对外开放的
 */
class PrintServiceProxy implements PrintService {

    private final PrintService printService;

    public PrintServiceProxy() {
        this.printService = new PrintServiceImpl();
    }

    @Override
    public void print() {
        // 在输入打印开始之前，增加日志输出时间
        System.out.print(new Date() + ": ");
        printService.print();
    }
}

public class code_6_5 {
    public static void main(String[] args) {
        PrintServiceProxy printServiceProxy = new PrintServiceProxy();
        printServiceProxy.print();
    }
}
```

静态代理模式在不改变目标对象的前提下，实现了对目标对象的功能扩展。
不足：静态代理实现了目标对象的所有方法，一旦目标接口增加方法，代理对象和目标对象都要进行相应的修改，增加维护成本。



#### **动态代理**

代理类包含以下方法：

- 指定接口的全部方法
- `Object`类中的全部方法，如`toString`, `equals`方法。

要想不重复写记录日志的功能，**针对每一个接口实现一个代理类的做法肯定不可行了**，可不可以让这些代理类的对象**自动生成**呢？

`JDK`提供了**`InvocationHandler`**接口和**`Proxy`**类，借助这两个工具可以达到我们想要的效果。

 `InvocationHandler`接口上场：

```java
public interface InvocationHandler {
    /**
    * proxy:代理类代理的真实代理对象com.sun.proxy.$Proxy0
    * method:我们所要调用某个对象真实的方法的Method对象
    * args:指代代理对象方法传递的参数
    */
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```

`Proxy`类上场，它里面有一个很重要的方法 `newProxyInstance`：

```java
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
```

**调用`Proxy`的`newProxyInstance`方法可以生成代理对象**

接口`PrintService`和 该接口的实现类`PrintServiceImpl`的代码同前。

实现一个类，该类用来创建代理对象，它实现了`InvocationHandler`接口：!!!

```java
class PrintServiceProxyHandler implements InvocationHandler {
    // 被代理的对象
    private final Object proxyObject;

    public PrintServiceProxyHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(new Date() + ": ");
        return method.invoke(proxyObject);
    }
}
```

整体代码如下：

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

class PrintServiceProxyHandler implements InvocationHandler {
    private final Object proxyObject;

    public PrintServiceProxyHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(new Date() + ": ");
        return method.invoke(proxyObject);
    }
}

/**
 * 动态代理示例，自动生成代理类！
 */
public class code_6_5_2 {
    public static void main(String[] args) {
        // 被代理对象
        PrintService printService = new PrintServiceImpl();
        // 该类用来创建代理对象
        PrintServiceProxyHandler proxyServiceHandler = new PrintServiceProxyHandler(printService);
        // 返回一个代理对象
        PrintService proxyService = (PrintService) Proxy.newProxyInstance(printService.getClass().getClassLoader(),
                printService.getClass().getInterfaces(),
                proxyServiceHandler);
        proxyService.print();
    }
}
```

上面的代码还能简化成以下写法：



**代理模式的定义：**代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用，通俗的来讲代理模式就是我们生活中常见的中介**，动态代理和静态代理的区别在于静态代理我们需要手动的去实现目标对象的代理类，而动态代理可以在运行期间动态的生成代理类。**



再看一个示例，跟踪方法调用：

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

class TraceHandler implements InvocationHandler {

    private final Object proxyObject;

    public TraceHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(proxyObject);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[0]);
                if (i < args.length - 1)
                    System.out.print(",");
            }
        }
        System.out.println(")");
        return method.invoke(proxyObject, args);
    }
}

public class code_6_5_3 {
    public static void main(String[] args) {
        var array = new Object[100];
        for (int i = 0; i < array.length; i++) {
            Integer number = i + 1;
            TraceHandler proxyHandler = new TraceHandler(number);
            array[i] = Proxy.newProxyInstance(number.getClass().getClassLoader(), number.getClass().getInterfaces(), proxyHandler);
        }

        Integer key = new Random().nextInt(array.length) + 1;
        int result = Arrays.binarySearch(array, key);
        if (result >= 0)
            System.out.println(array[result]);
    }
}
```



#### 6.5.3 代理类的特性

所有的代理类都扩展`Proxy`类。一个代理类只有一个实例字段——即调用处理器。它在`Proxy`超类中定义。完成代理对象任务所需要的任何额外数据都必须存储在调用处理器中。

所有的代理类都要覆盖`Object`类中的`toString`，`equals`，`hashCode`方法。如同其他方法一样，这些方法只是在调用处理器上调用`invoke`。`Object`类中其他方法（如`clone`和`getClass`）没有重新定义。

对于一个特定的类加载器和预设的一组接口来说，只能有一个代理类。也就是说，如果使用同一个类加载器和接口数组调用两次`newProxyInstance`方法，将得到同一个类的两个对象。也可以使用`getProxyClass`方法获得这个类：

```java
Class proxyClass = Proxy.getProxyClass(null, interfaces);
```

检测一个特定的`Class`对象是不是表示一个代理类：

```java
public static boolean isProxyClass(Class<?> cl);
```

