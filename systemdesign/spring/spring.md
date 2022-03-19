### 原理

Spring是一个轻量级Java开发框架，为了解决企业级应用开发的业务逻辑层和其他各层的耦合问题。Spring是一个全面的、企业应用开发一站式的解决方案，贯穿表现层、业务层、持久层。但是它仍然可以和其他的框架无缝整合。

### 特点

**控制反转：** Spring通过控制反转（IOC）技术实现解耦。一个对象依赖的其他对象会通过被动的方式传递进来，而不需要对象自己创建或者查找依赖。

**面向切面：** 支持切面（AOP）编程，并且把应用业务逻辑和系统服务区分开。

**容器：** Spring包含并管理应用对象的配置和生命周期，在这个意义上它是一种容器。可以配置每个bean如何被创建、销毁，bean的作用范围是单例还是每次都生成一个新的实例，以及他们是如何相互关联。


### 用到了哪些设计模式？

Spring 框架中使用到了大量的设计模式，下面列举了比较有代表性的：

*   `代理模式`—在 AOP中被用的比较多。

*   `单例模式`—在 spring 配置文件中定义的 bean 默认为单例模式。

*   `工厂模式`—BeanFactory 用来创建对象的实例


### 核心组件

<img src="https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0gzlqhXoYGN7kvLWLI4B0CpvtLZE9cok7EePH6S4L7B4WusQVqp8c5g/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1" alt="图片" style="zoom: 33%;" />

*   `spring core`：提供了框架的基本组成部分，包括控制反转（Inversion of Control，IOC）和依赖注入（Dependency Injection，DI）功能。

*   `spring beans`：提供了BeanFactory，是工厂模式的一个经典实现，Spring将管理对象称为Bean。关注Java项目分享

*   `spring context`：构建于 core 封装包基础上的 context 封装包，提供了一种框架式的对象访问方法。

*   `spring jdbc`：提供了一个JDBC的抽象层，消除了烦琐的JDBC编码和数据库厂商特有的错误代码解析， 用于简化JDBC。

*   `spring aop`：提供了面向切面的编程实现，让你可以自定义拦截器、切点等。

*   `spring Web`：提供了针对 Web 开发的集成特性，例如文件上传，利用 servlet listeners 进行 ioc 容器初始化和针对 Web 的 ApplicationContext。

*   `spring test`：主要为测试提供支持的，支持使用JUnit或TestNG对Spring组件进行单元测试和集成测试。


### 控制反转（IOC）

控制反转（IoC）是一个比较抽象的概念，是Spring框架的核心，用来消减计算机程序的耦合问题。依赖注入（DI）是IoC的另外一种说法，只是从不同的角度，描述相同的概念。

当Spring框架出现之后，对象的实例化不再由调用者来创建，而是由Spring容器来创建。Spring容器会负责控制程序之间的关系，而不是由调用者的程序代码直接控制。这样，控制权由调用者转移到Spring容器，控制权发生了反转，这就是Spring的控制反转。

从spring容器角度来看，spring容器负责将被依赖对象复制给调用者的成员变量，相当于为调用者注入它所依赖的实例，这就是spring的依赖注入，主要目的是为了解耦，体现一种“组合”的理念。

综上所述，控制反转是一种通过描述文件（XML或者注解）并通过第三方产生或获取特定对象的方法，在spring中实现控制反转的是IOC容器，其实现方法是依赖注入。

### IOC 容器实现

BeanFactory 是 Spring 框架的基础设施，面向 Spring 本身；

ApplicationContext 面向使用Spring 框架的开发者，几乎所有的应用场合我们都直接使用 ApplicationContext 而非底层的 BeanFactory。


### ApplicationContext 面向开发应用

ApplicationContext 由 BeanFactory 派生而来，提供了更多面向实际应用的功能。

ApplicationContext 继承了 HierarchicalBeanFactory 和 ListableBeanFactory 接口，在此基础上，还通过多个其他的接口扩展了 BeanFactory 的功能：


### BeanFactory 和 ApplicationContext有什么区别？

BeanFactory和ApplicationContext是Spring的两大核心接口，都可以当做Spring的容器。其中ApplicationContext是BeanFactory的子接口。

#### 依赖关系

BeanFactory：是Spring里面最底层的接口，包含了各种Bean的定义，读取bean配置文档，管理bean的加载、实例化，控制bean的生命周期，维护bean之间的依赖关系。

ApplicationContext：接口作为BeanFactory的派生，除了提供BeanFactory所具有的功能外，还提供了更完整的框架功能：

*   继承MessageSource，因此支持国际化。

*   统一的资源文件访问方式。

*   提供在监听器中注册bean的事件。

*   同时加载多个配置文件。

*   载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层。


#### 加载方式

BeanFactroy：采用的是延迟加载形式来注入Bean的，即只有在使用到某个Bean时(调getBean())，才对该Bean进行加载实例化。这样，我们就不能发现一些存在的Spring的配置问题。如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常。

ApplicationContext：它是在容器启动时，一次性创建了所有的Bean。这样，在容器启动时，我们就可以发现Spring中存在的配置错误，这样有利于检查所依赖属性是否注入。ApplicationContext启动后预载入所有的单实例Bean，通过预载入单实例bean ,确保当你需要的时候，你就不用等待，因为它们已经创建好了。

相对于基本的BeanFactory，ApplicationContext 唯一的不足是占用内存空间。当应用程序配置Bean较多时，程序启动较慢。

#### 创建方式

BeanFactory通常以编程的方式被创建，ApplicationContext还能以声明的方式创建，如使用ContextLoader。

#### 注册方式

BeanFactory和ApplicationContext都支持BeanPostProcessor、BeanFactoryPostProcessor的使用，但两者之间的区别是：BeanFactory需要手动注册，而ApplicationContext则是自动注册。

#### ApplicationContext通常的实现

*   FileSystemXmlApplicationContext ：此容器从一个XML文件中加载beans的定义，XML Bean 配置文件的全路径名必须提供给它的构造函数。

*   ClassPathXmlApplicationContext：此容器也从一个XML文件中加载beans的定义，这里，你需要正确设置classpath因为这个容器将在classpath里找bean配置。

*   WebXmlApplicationContext：此容器加载一个XML文件，此文件定义了一个WEB应用的所有bean。


### Spring Bean 作用域

Spring 3 中为 Bean 定义了 5 中作用域，分别为 singleton（单例）、prototype（原型）、request、session 和 global session，5 种作用域说明如下：

*   **singleton**：单例模式（多线程下不安全）。Spring IoC 容器中只会存在一个共享的 Bean 实例，无论有多少个Bean 引用它，始终指向同一对象。该模式在多线程下是不安全的。Singleton 作用域是Spring 中的缺省作用域，也可以显示的将 Bean 定义为 singleton 模式，配置为：

*   **prototype**:原型模式每次使用时创建。每次通过 Spring 容器获取 prototype 定义的 bean 时，容器都将创建一个新的 Bean 实例，每个 Bean 实例都有自己的属性和状态，而 singleton 全局只有一个对象。根据经验，对有状态的bean使用prototype作用域，而对无状态的bean使用singleton 作用域。

*   **Request**：一次 request 一个实例。在一次 Http 请求中，容器会返回该 Bean 的同一实例。而对不同的 Http 请求则会产生新的 Bean，而且该 bean 仅在当前 Http Request 内有效,当前 Http 请求结束，该 bean实例也将会被销毁。

*   **session**：在一次 Http Session 中，容器会返回该 Bean 的同一实例。而对不同的 Session 请求则会创建新的实例，该 bean 实例仅在当前 Session 内有效。同 Http 请求相同，每一次session 请求创建新的实例，而不同的实例之间不共享属性，且实例仅在自己的 session 请求内有效，请求结束，则实例将被销毁。

*   **global Session**：在一个全局的 Http Session 中，容器会返回该 Bean 的同一个实例，仅在使用 portlet context 时有效。

### AOP原理

OOP(Object-Oriented Programming)面向对象编程，允许开发者定义纵向的关系，但并适用于定义横向的关系，导致了大量代码的重复，而不利于各个模块的重用。

AOP(Aspect-Oriented Programming)，一般称为面向切面编程，作为面向对象的一种补充，用于将那些与业务无关，但却对多个对象产生影响的公共行为和逻辑，抽取并封装为一个可重用的模块，这个模块被命名为“切面”（Aspect），减少系统中的重复代码，降低了模块间的耦合度，同时提高了系统的可维护性。

AOP 主要应用场景有

*   Authentication 权限

*   Caching 缓存

*   Context passing 内容传递

*   Error handling 错误处理

*   Lazy loading 懒加载

*   Debugging 调试

*   logging, tracing, profiling and monitoring 记录跟踪 优化 校准

*   Performance optimization 性能优化

*   Persistence 持久化

*   Resource pooling 资源池

*   Synchronization 同步

*   Transactions 事务


### AOP 核心概念

*   切面（aspect）：类是对物体特征的抽象，切面就是对横切关注点的抽象

*   横切关注点：对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点

*   连接点（joinpoint）：被拦截到的点，因为 Spring 只支持方法类型的连接点，所以在 Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器

*   切入点（pointcut）：对连接点进行拦截的定义

*   通知（advice）：所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类

*   目标对象：代理的目标对象

*   织入（weave）：将切面应用到目标对象并导致代理对象创建的过程


*   编译期：切面在目标类编译时被织入。AspectJ的织入编译器是以这种方式织入切面的；

*   类加载期：切面在目标类加载到JVM时被织入。需要特殊的类加载器，它可以在目标类被引入应用之前增强该目标类的字节码。AspectJ5的加载时织入就支持以这种方式织入切面；

*   运行期：切面在应用运行的某个时刻被织入。一般情况下，在织入切面时，AOP容器会为目标对象动态地创建一个代理对象。SpringAOP就是以这种方式织入切面。


*   引入（introduction）：在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段

### AOP 实现方式

AOP实现的关键在于代理模式，AOP代理主要分为静态代理和动态代理。

*   AspectJ 静态代理的增强，所谓静态代理，就是AOP框架会在编译阶段生成AOP代理类，因此也称为编译时增强，他会在编译阶段将AspectJ(切面)织入到Java字节码中，运行的时候就是增强之后的AOP对象。

*   Spring AOP使用的动态代理，所谓的动态代理就是说AOP框架不会去修改字节码，而是每次运行时在内存中临时为方法生成一个AOP对象，这个AOP对象包含了目标对象的全部方法，并且在特定的切点做了增强处理，并回调原对象的方法。


### AOP 两种代理方式

Spring 提供了两种方式来生成代理对象: JDK Proxy 和 Cglib，具体使用哪种方式生成由AopProxyFactory 根据 AdvisedSupport 对象的配置来决定。默认的策略是如果目标类是接口，则使用 JDK 动态代理技术，否则使用 Cglib 来生成代理。

### JDK 动态接口代理

JDK 动态代理主要涉及到 java.lang.reflect 包中的两个类：Proxy 和 InvocationHandler。

InvocationHandler是一个接口，通过实现该接口定义横切逻辑，并通过反射机制调用目标类的代码，动态将横切逻辑和业务逻辑编制在一起。

Proxy 利用 InvocationHandler 动态创建一个符合某一接口的实例，生成目标类的代理对象。

### Spring MVC 原理

Spring 的模型-视图-控制器（MVC）框架是围绕一个 DispatcherServlet 来设计的，这个 Servlet会把请求分发给各个处理器，并支持可配置的处理器映射、视图渲染、本地化、时区与主题渲染等，甚至还能支持文件上传。

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0mdRvhAbtrBTz9qp4vbE0AZ40sYukU75NJjJPicfp4EicAu1ibSsgAy95g/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

**Http 请求到 DispatcherServlet**

(1) 客户端请求提交到 DispatcherServlet。

**HandlerMapping 寻找处理器**

(2) 由 DispatcherServlet 控制器查询一个或多个 HandlerMapping，找到处理请求的Controller。

**调用处理器 Controller**

(3) DispatcherServlet 将请求提交到 Controller。

**Controller 调用业务逻辑处理后，返回 ModelAndView**

(4)(5)调用业务处理和返回结果：Controller 调用业务逻辑处理后，返回 ModelAndView。

**DispatcherServlet 查询 ModelAndView**

(6)(7)处理视图映射并返回模型：DispatcherServlet 查询一个或多个 ViewResoler 视图解析器，找到 ModelAndView 指定的视图。

**ModelAndView 反馈浏览器 HTTP**

(8) Http 响应：视图负责将结果显示到客户端。

### Spring常用注解

#### 声明bean的注解

*   `@Component` ：组件，没有明确的角色

*   `@Service` ：在业务逻辑层使用

*   `@Repository` ：在数据访问层使用

*   `@Controller` ：在展现层使用，控制层的声明

*   `@RestController` ：`@Controller`和`@ResponseBody`组合，用于返回JSON格式数据


#### 注入bean的注解

*   `@Autowired`：

该注解可以对类成员变量，方法，及构造方法，进行标注，完成动装配的工作。通过`@Autowired`的使用来消除`setter`和`getter`的方法，默认按照`Bean`的`类型`进行装配。

*   `@Inject`：


JSR330 (Dependency Injection for Java)中的规范，需要导入javax.inject.Inject jar包 ，才能实现注入 作用CONSTRUCTOR、METHOD、FIELD上

根据类型进行自动装配的，如果需要按名称进行装配，则需要配合@Named

*   `@Resource`：

该注解与`@Autowired`的功能一样，区别在于，该注解默认是按照名称来装配注入的，只有当找不到与名称匹配的`Bean`时，才会按照类型来装配注入；而`@Autowired`默认按照`Bean`
的类型进行装配，如果想按照名称来装配注入，而需要结合`@Qualifier`注解一起使用。

`@Resource`注解有两个属性：`name`和`type`。`name`属性是指定`Bean`实例名称，即按照名称来装配注入；`type`属性指定`Bean`类型，即按照`Bean`
的类型进行装配。如`@Resource("testDAO")`，名称就是`testDAO`。

- @Qualifier

该注解与`@Autowired`的注解配合使用，当`@Autowired`的注解需要按照名称来装配注入时，需要结合该注解一起使用，Bean的实例名称，由@Qualifier注解的参数指定。

#### 配置类相关注解

*   `@Configuration` ：声明当前类为配置类，相当于xml形式的Spring配置（类上），声明当前类为配置类，其中内部组合了@Component注解，表明这个类是一个bean（类上）

*   `@Bean` ：注解在方法上，声明当前方法的返回值为一个bean，替代xml中的方式（方法上）

*   `@ComponentScan` ：用于对Component进行扫描，相当于xml中的（类上）



#### AOP相关注解

Spring支持AspectJ的注解式切面编程

*   `@Aspect`：声明一个切面（类上），使用@After、@Before、@Around定义建言（advice），可直接将拦截规则（切点）作为参数。

*   `@After` ：在方法执行之后执行（方法上）

*   `@Before` ：在方法执行之前执行（方法上）

*   `@Around` ：在方法执行之前与之后执行（方法上）

*   `@PointCut` ：声明切点在java配置类中使用@EnableAspectJAutoProxy注解开启Spring对AspectJ代理的支持（类上）



#### SpringMVC注解

*   `@RequestMapping` ：用于映射Web请求，包括访问路径和参数（类或方法上）

*   `@ResponseBody` ：支持将返回值放在response内，而不是一个页面，通常用户返回json数据（返回值旁或方法上）

*   `@RequestBody` ：允许request的参数在request体中，而不是在直接连接在地址后面。（放在参数前）

*   `@PathVariable` ：用于接收路径参数，比如@RequestMapping(“/hello/{name}”)申明的路径，将注解放在参数中前，即可获取该值，通常作为Restful的接口实现方法。

*   `@RestController` ：该注解为一个组合注解，相当于@Controller和@ResponseBody的组合，注解在类上，意味着，该Controller的所有方法都默认加上了@ResponseBody。

*   `@ControllerAdvice` ：通过该注解，我们可以将对于控制器的全局配置放置在同一个位置，注解了@Controller的类的方法可使用@ExceptionHandler、@InitBinder、@ModelAttribute注解到方法上，这对所有注解了 @RequestMapping的控制器内的方法有效。

*   `@ExceptionHandler` ：用于全局处理控制器里的异常

*   `@ModelAttribute` ：本来的作用是绑定键值对到Model里，在@ControllerAdvice中是让全局的@RequestMapping：都能获得在此处设置的键值对。



### @Bean的属性支持

@Scope 设置Spring容器如何新建Bean实例（方法上，得有@Bean），其设置类型包括：

*   `Singleton`：单例,一个Spring容器中只有一个bean实例，默认模式

*   `Protetype`：每次调用新建一个bean

*   `Request`：web项目中，给每个http request新建一个bean

*   `Session` ：web项目中，给每个http session新建一个bean

*   `Global`：Session给每一个 global http session新建一个Bean实例

*   `@StepScope`：在Spring Batch中还有涉及(Spring Batch 之 背景框架简介\_vincent-CSDN博客)

*   `@PostConstruct` ：由JSR-250提供，在构造函数执行完之后执行，等价于xml配置文件中bean的initMethod

*   `@PreDestory` ：由JSR-250提供，在Bean销毁之前执行，等价于xml配置文件中bean的destroyMethod


### @Value注解

为属性注入值,支持如下方式的注入：

注入普通字符

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0TGXBNM94XZrbaQswhsD4gpZtHkSz7J3cDUXRHkmm7uWXJCVlt2xiafw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

注入操作系统属性

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0vgNFBShprngOeIOGRzAwB9c0yabT5ANH8vemFZrtPxSzJVL0Ox5qoQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

注入表达式结果

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs00kfjJT0DaibiatmySaRu9XstMSSnMs9BMRzj1gA8nX0t8q9SpAtnA6Gg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

注入其它bean属性

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0wRawW1KEduGGdTL5CcrjtcOUurRQmRPAUwCLNE5D75WEmgOViacneEw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

注入文件资源

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0KvjXt7iaxpwaEF3eYSq9SCp8quzYBWibb3uOVwwI5gnEyl1VZ1ia4w1mA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

注入网站资源

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0O9W5icQUjVKtARtl0Easy7pAHbnib07bfSncdicdMUne92KNJotQ58qOA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

注入配置文件

![图片](https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbud8CU3hg3Mg9dvdxcasogs0d8icjekqCaN1KSDJ3gibibjoU97naELI2PicicydsDZ4wkMg6Xj8j5kUZxw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

`@PropertySource` 加载配置文件(类上)，还需配置一个`PropertySourcesPlaceholderConfigurer`的bean。

