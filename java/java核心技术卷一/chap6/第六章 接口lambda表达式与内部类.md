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

**接口中的所有方法都自动是`public`方法。因此，在接口声明方法时，不必提供关键字`public`。**

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

因此，内部类的对象总有一个隐式引用，指向创建它的外部类对象。
