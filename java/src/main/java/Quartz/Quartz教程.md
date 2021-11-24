# ch1 : 你了解 Quartz 吗？

Quartz 是一个完全由 Java 编写的开源作业调度框架，为在 Java 应用程序中进行作业调度提供了简单却强大的机制。

Quartz 可以与[ J2EE](https://www.w3cschool.cn/java_interview_question/java_interview_question-wvr326ra.html) 与 J2SE
应用程序相结合也可以单独使用。

Quartz 允许程序开发人员根据时间的间隔来调度作业。

Quartz 实现了作业和触发器的多对多的关系，还能把多个作业与不同的触发器关联。

## **Quartz 核心概念**

我们需要明白 Quartz 的几个核心概念，这样理解起 Quartz 的原理就会变得简单了。

1. Job

   表示一个工作，要执行的具体内容。此接口中只有一个方法，如下：

   ```java
   void execute(JobExecutionContext context) 
   ```

2. **JobDetail** 表示一个具体的可执行的调度程序，Job 是这个可执行程调度程序所要执行的内容，另外 JobDetail 还包含了这个任务调度的方案和策略。

3. **Trigger** 代表一个调度参数的配置，什么时候去调。

4. **Scheduler** 代表一个调度容器，一个调度容器中可以注册多个 JobDetail 和 Trigger。当 Trigger 与 JobDetail 组合，就可以被 Scheduler 容器调度了。

## Quartz的运行环境

- Quartz 可以运行嵌入在另一个独立式应用程序。
- Quartz 可以在应用程序服务器(
  或 [servlet 容器](https://www.w3cschool.cn/spring_mvc_documentation_linesh_translation/spring_mvc_documentation_linesh_translation-it8y27sq.html))内被实例化，并且参与
  XA 事务。
- Quartz 可以作为一个独立的程序运行(其自己的 Java 虚拟机内)，可以通过 RMI 使用。
- Quartz 可以被实例化，作为独立的项目集群(负载平衡和故障转移功能)，用于作业的执行。

# ch2 : Quartz API，Jobs和Triggers

## Quartz API

Quartz API的关键接口是：

- Scheduler - 与调度程序交互的主要API。
- Job - 你想要调度器执行的任务组件需要实现的接口
- JobDetail - 用于定义作业的实例。
- Trigger（即触发器） - 定义执行给定作业的计划的组件。
- JobBuilder - 用于定义/构建 JobDetail 实例，用于定义作业的实例。
- TriggerBuilder - 用于定义/构建触发器实例。
- Scheduler 的生命期，从 SchedulerFactory 创建它时开始，到 Scheduler 调用shutdown() 方法时结束；Scheduler 被创建后，可以增加、删除和列举 Job 和
  Trigger，以及执行其它与调度相关的操作（如暂停 Trigger）。但是，Scheduler 只有在调用 start() 方法后，才会真正地触发 trigger（即执行
  job），见[教程一](https://www.w3cschool.cn/quartz_doc/quartz_doc-1xbu2clr.html)。

Quartz 提供的“builder”类，可以认为是一种领域特定语言（DSL，Domain Specific Language)。教程一中有相关示例，这里是其中的代码片段：（校对注：这种级联的 API
非常方便用户使用，大家以后写对外接口时也可以使用这种方式）

```java
// define the job and tie it to our HelloJob class
JobDetail job = newJob(HelloJob.class)
    .withIdentity("myJob", "group1") // name "myJob", group "group1"
    .build();

// Trigger the job to run now, and then every 40 seconds
Trigger trigger = newTrigger()
    .withIdentity("myTrigger", "group1")
    .startNow()
    .withSchedule(simpleSchedule()
        .withIntervalInSeconds(40)
        .repeatForever())            
    .build();

// Tell quartz to schedule the job using our trigger
sched.scheduleJob(job, trigger);
```

DSL 的静态导入可以通过以下导入语句来实现：

```java
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;
```

SchedulerBuilder 接口的各种实现类，可以定义不同类型的调度计划 （schedule）；

DateBuilder 类包含很多方法，可以很方便地构造表示不同时间点的 java.util.Date 实例（如定义下一个小时为偶数的时间点，如果当前时间为 9:43:27，则定义的时间为10:00:00）。

## Job 和 Trigger

一个 job 就是一个实现了 Job 接口的类，该接口只有一个方法：

Job 接口：

```java
  package org.quartz;

  public interface Job {

    public void execute(JobExecutionContext context)
      throws JobExecutionException;
  }
```

job的一个 trigger 被触发后（稍后会讲到），execute() 方法会被 scheduler 的一个工作线程调用；传递给 execute() 方法的 JobExecutionContext 对象中保存着该 job 运行时的一些信息
，执行 job 的 scheduler 的引用，触发 job 的 trigger 的引用，JobDetail 对象引用，以及一些其它信息。

JobDetail 对象是在将 job 加入 scheduler 时，由客户端程序（你的程序）创建的。它包含 job 的各种属性设置，以及用于存储 job 实例状态信息的 JobDataMap。本节是对 job
实例的简单介绍，更多的细节将在下一节讲到。

Trigger 用于触发 Job 的执行。当你准备调度一个 job 时，你创建一个 Trigger 的实例，然后设置调度相关的属性。Trigger 也有一个相关联的 JobDataMap，用于给 Job 传递一些触发相关的参数。Quartz
自带了各种不同类型的 Trigger，最常用的主要是 SimpleTrigger 和 CronTrigger。

SimpleTrigger 主要用于一次性执行的 Job（只在某个特定的时间点执行一次），或者 Job 在特定的时间点执行，重复执行 N 次，每次执行间隔T个时间单位。CronTrigger
在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午 10:15”等。

为什么既有 Job，又有 Trigger 呢？很多任务调度器并不区分 Job 和 Trigger。有些调度器只是简单地通过一个执行时间和一些 job 标识符来定义一个 Job；其它的一些调度器将 Quartz 的 Job 和 Trigger
对象合二为一。在开发 Quartz 的时候，我们认为将调度和要调度的任务分离是合理的。在我们看来，这可以带来很多好处。

例如，Job 被创建后，可以保存在 Scheduler 中，与 Trigger 是独立的，同一个 Job可以有多个 Trigger；这种松耦合的另一个好处是，当与 Scheduler 中的 Job 关联的 trigger 都过期时，可以配置
Job 稍后被重新调度，而不用重新定义 Job；还有，可以修改或者替换 Trigger，而不用重新定义与之关联的 Job。

## Key

将 Job 和 Trigger 注册到 Scheduler 时，可以为它们设置 key，配置其身份属性。 Job 和 Trigger 的 key（JobKey 和 TriggerKey）可以用于将 Job 和 Trigger
放到不同的分组（group）里，然后基于分组进行操作。同一个分组下的 Job 或 Trigger 的名称必须唯一，即一个 Job 或 Trigger 的 key 由名称（name）和分组（group）组成。

您现在有一个关于什么 Job
和触发器的一般概念，您可以在[第3课中](http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-03.html)了解更多信息[有关作业和作业详细信息](http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-03.html)以及[第4课：有关触发器的更多信息](http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-03.html)。

# ch3 : Job与JobDetail介绍

正如你在[第2课](https://www.w3cschool.cn/project/quartz_doc/quartz_doc-kixe2cq3.html)中看到的，Jobs很容易实现，在接口中只有一个“execute”方法。本节主要关注：Job的特点、Job接口的execute方法以及JobDetail。

你定义了一个实现Job接口的类，这个类仅仅表明该job需要完成什么类型的任务，除此之外，Quartz还需要知道该Job实例所包含的属性；这将由JobDetail类来完成。

JobDetail实例是通过JobBuilder类创建的，导入该类下的所有静态方法，会让你编码时有DSL的感觉：

```java
    import static org.quartz.JobBuilder.*;
```

让我们先看看Job的特征（nature）以及Job实例的生命期。不妨先回头看看[第1课](https://www.w3cschool.cn/quartz_doc/quartz_doc-1xbu2clr.html)中的代码片段：

```java
  // define the job and tie it to our HelloJob class
  JobDetail job = newJob(HelloJob.class)
      .withIdentity("myJob", "group1") // name "myJob", group "group1"
      .build();

  // Trigger the job to run now, and then every 40 seconds
  Trigger trigger = newTrigger()
      .withIdentity("myTrigger", "group1")
      .startNow()
      .withSchedule(simpleSchedule()
          .withIntervalInSeconds(40)
          .repeatForever())            
      .build();

  // Tell quartz to schedule the job using our trigger
  sched.scheduleJob(job, trigger);
```

现在考虑这样定义的作业类“HelloJob”：

```java
  public class HelloJob implements Job {

    public HelloJob() {
    }

    public void execute(JobExecutionContext context)
      throws JobExecutionException
    {
      System.err.println("Hello!  HelloJob is executing.");
    }
  }
```

可以看到，我们传给scheduler一个JobDetail实例，因为我们在创建JobDetail时，将要执行的job的类名传给了JobDetail，所以scheduler就知道了要执行何种类型的job；每次当scheduler执行job时，在调用其execute(
…)
方法之前会创建该类的一个新的实例；执行完毕，对该实例的引用就被丢弃了，实例会被垃圾回收；这种执行策略带来的一个后果是，job必须有一个无参的构造函数（当使用默认的JobFactory时）；另一个后果是，在job类中，不应该定义有状态的数据属性，因为在job的多次执行中，这些属性的值不会保留。

那么如何给job实例增加属性或配置呢？如何在job的多次执行中，跟踪job的状态呢？答案就是:JobDataMap，JobDetail对象的一部分。

## JobDataMap

JobDataMap中可以包含不限量的（序列化的）数据对象，在job实例执行的时候，可以使用其中的数据；JobDataMap是Java Map接口的一个实现，额外增加了一些便于存取基本类型的数据的方法。

将job加入到scheduler之前，在构建JobDetail时，可以将数据放入JobDataMap，如下示例：

```java
  // define the job and tie it to our DumbJob class
  JobDetail job = newJob(DumbJob.class)
      .withIdentity("myJob", "group1") // name "myJob", group "group1"
      .usingJobData("jobSays", "Hello World!")
      .usingJobData("myFloatValue", 3.141f)
      .build();
```

在job的执行过程中，可以从JobDataMap中取出数据，如下示例：

```java
public class DumbJob implements Job {

    public DumbJob() {
    }

    public void execute(JobExecutionContext context)
      throws JobExecutionException
    {
      JobKey key = context.getJobDetail().getKey();

      JobDataMap dataMap = context.getJobDetail().getJobDataMap();

      String jobSays = dataMap.getString("jobSays");
      float myFloatValue = dataMap.getFloat("myFloatValue");

      System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }
  }
```

如果你使用的是持久化的存储机制（本教程的JobStore部分会讲到），在决定JobDataMap中存放什么数据的时候需要小心，因为JobDataMap中存储的对象都会被序列化，因此很可能会导致类的版本不一致的问题；Java的标准类型都很安全，如果你已经有了一个类的序列化后的实例，某个时候，别人修改了该类的定义，此时你需要确保对类的修改没有破坏兼容性；更多细节，参考[现实中的序列化问题](http://java.sun.com/developer/TechTips/2000/tt0229.html)。另外，你也可以配置JDBC-JobStore和JobDataMap，使得map中仅允许存储基本类型和String类型的数据，这样可以避免后续的序列化问题。

如果你在job类中，为JobDataMap中存储的数据的key增加set方法（如在上面示例中，增加setJobSays(String val)
方法），那么Quartz的默认JobFactory实现在job被实例化的时候会自动调用这些set方法，这样你就不需要在execute()方法中显式地从map中取数据了。

在Job执行时，JobExecutionContext中的JobDataMap为我们提供了很多的便利。它是JobDetail中的JobDataMap和Trigger中的JobDataMap的并集，但是如果存在相同的数据，则后者会覆盖前者的值。

下面的示例，在job执行时，从JobExecutionContext中获取合并后的JobDataMap：

```java
    public class DumbJob implements Job {

        public DumbJob() {
        }

        public void execute(JobExecutionContext context)
          throws JobExecutionException
        {
            JobKey key = context.getJobDetail().getKey();

            JobDataMap dataMap = context.getMergedJobDataMap();  // Note the difference from the previous example

            String jobSays = dataMap.getString("jobSays");
            float myFloatValue = dataMap.getFloat("myFloatValue");
            ArrayList state = (ArrayList)dataMap.get("myStateData");
            state.add(new Date());

            System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
        }
    }
```

如果你希望使用JobFactory实现数据的自动“注入”，则示例代码为：

```java
  public class DumbJob implements Job {


    String jobSays;
    float myFloatValue;
    ArrayList state;

    public DumbJob() {
    }

    public void execute(JobExecutionContext context)
      throws JobExecutionException
    {
      JobKey key = context.getJobDetail().getKey();

      JobDataMap dataMap = context.getMergedJobDataMap();  // Note the difference from the previous example

      state.add(new Date());

      System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }

    public void setJobSays(String jobSays) {
      this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
      myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
      state = state;
    }

  }
```

你也许发现，整体上看代码更多了，但是execute()
方法中的代码更简洁了。而且，虽然代码更多了，但如果你的IDE可以自动生成setter方法，你就不需要写代码调用相应的方法从JobDataMap中获取数据了，所以你实际需要编写的代码更少了。当前，如何选择，由你决定。

## Job实例

很多用户对于Job实例到底由什么构成感到很迷惑。我们在这里解释一下，并在接下来的小节介绍job状态和并发。

你可以只创建一个job类，然后创建多个与该job关联的JobDetail实例，每一个实例都有自己的属性集和JobDataMap，最后，将所有的实例都加到scheduler中。

比如，你创建了一个实现Job接口的类“SalesReportJob”。该job需要一个参数（通过JobdataMap传入），表示负责该销售报告的销售员的名字。因此，你可以创建该job的多个实例（JobDetail），比如“SalesReportForJoe”、“SalesReportForMike”，将“joe”和“mike”作为JobDataMap的数据传给对应的job实例。

当一个trigger被触发时，与之关联的JobDetail实例会被加载，JobDetail引用的job类通过配置在Scheduler上的JobFactory进行初始化。默认的JobFactory实现，仅仅是调用job类的newInstance()
方法，然后尝试调用JobDataMap中的key的setter方法。你也可以创建自己的JobFactory实现，比如让你的IOC或DI容器可以创建/初始化job实例。

在Quartz的描述语言中，我们将保存后的JobDetail称为“job定义”或者“JobDetail实例”,将一个正在执行的job称为“job实例”或者“job定义的实例”。当我们使用“job”时，一般指代的是job定义，或者JobDetail；当我们提到实现Job接口的类时，通常使用“job类”。

## Job状态与并发

关于job的状态数据（即JobDataMap）和并发性，还有一些地方需要注意。在job类上可以加入一些注解，这些注解会影响job的状态和并发性。

@DisallowConcurrentExecution：将该注解加到job类上，告诉Quartz不要并发地执行同一个job定义（这里指特定的job类）的多个实例。请注意这里的用词。拿前一小节的例子来说，如果“SalesReportJob”类上有该注解，则同一时刻仅允许执行一个“SalesReportForJoe”实例，但可以并发地执行“SalesReportForMike”类的一个实例。所以该限制是针对JobDetail的，而不是job类的。但是我们认为（在设计Quartz的时候）应该将该注解放在job类上，因为job类的改变经常会导致其行为发生变化。

@PersistJobDataAfterExecution：将该注解加在job类上，告诉Quartz在成功执行了job类的execute方法后（没有发生任何异常），更新JobDetail中JobDataMap的数据，使得该job（即JobDetail）在下一次执行的时候，JobDataMap中是更新后的数据，而不是更新前的旧数据。和
@DisallowConcurrentExecution注解一样，尽管注解是加在job类上的，但其限制作用是针对job实例的，而不是job类的。由job类来承载注解，是因为job类的内容经常会影响其行为状态（比如，job类的execute方法需要显式地“理解”其”状态“）。

如果你使用了@PersistJobDataAfterExecution注解，我们强烈建议你同时使用@DisallowConcurrentExecution注解，因为当同一个job（JobDetail）的两个实例被并发执行时，由于竞争，JobDataMap中存储的数据很可能是不确定的。

## Job的其它特性

通过JobDetail对象，可以给job实例配置的其它属性有：

- Durability：如果一个job是非持久的，当没有活跃的trigger与之关联的时候，会被自动地从scheduler中删除。也就是说，非持久的job的生命期是由trigger的存在与否决定的；
- RequestsRecovery：如果一个job是可恢复的，并且在其执行的时候，scheduler发生硬关闭（hard shutdown)
  （比如运行的进程崩溃了，或者关机了），则当scheduler重新启动的时候，该job会被重新执行。此时，该job的JobExecutionContext.isRecovering() 返回true。

## JobExecutionException

最后，是关于Job.execute(..)
方法的一些额外细节。execute方法中仅允许抛出一种类型的异常（包括RuntimeExceptions），即JobExecutionException。因此，你应该将execute方法中的所有内容都放到一个”try-catch”块中。你也应该花点时间看看JobExecutionException的文档，因为你的job可以使用该异常告诉scheduler，你希望如何来处理发生的异常。

# ch4 : Quartz中Triggers介绍

与[job](https://www.w3cschool.cn/quartz_doc/quartz_doc-h4ux2cq6.html)一样，trigger也很容易使用，但是还有一些扩展选项需要理解，以便更好地使用quartz。trigger也有很多类型，我们可以根据实际需要来选择。

最常用的两种trigger会分别在第5课：SimpleTrigger和第6课：CronTrigger中讲到。

## Trigger的公共属性

所有类型的trigger都有TriggerKey这个属性，表示trigger的身份；除此之外，trigger还有很多其它的公共属性。这些属性，在构建trigger的时候可以通过TriggerBuilder设置。

trigger的公共属性有：

- jobKey属性：当trigger触发时被执行的job的身份；
-
startTime属性：设置trigger第一次触发的时间；该属性的值是java.util.Date类型，表示某个指定的时间点；有些类型的trigger，会在设置的startTime时立即触发，有些类型的trigger，表示其触发是在startTime之后开始生效。比如，现在是1月份，你设置了一个trigger–“在每个月的第5天执行”，然后你将startTime属性设置为4月1号，则该trigger第一次触发会是在几个月以后了(
即4月5号)。
- endTime属性：表示trigger失效的时间点。比如，”每月第5天执行”的trigger，如果其endTime是7月1号，则其最后一次执行时间是6月5号。

其它的属性，会在下文中解释。

## 优先级(priority)

如果你的trigger很多(或者Quartz线程池的工作线程太少)
，Quartz可能没有足够的资源同时触发所有的trigger；这种情况下，你可能希望控制哪些trigger优先使用Quartz的工作线程，要达到该目的，可以在trigger上设置priority属性。比如，你有N个trigger需要同时触发，但只有Z个工作线程，优先级最高的Z个trigger会被首先触发。如果没有为trigger设置优先级，trigger使用默认优先级，值为5；priority属性的值可以是任意整数，正数、负数都可以。

注意：只有同时触发的trigger之间才会比较优先级。10:59触发的trigger总是在11:00触发的trigger之前执行。

注意：如果trigger是可恢复的，在恢复后再调度时，优先级与原trigger是一样的。

## 错过触发(misfire Instructions)

trigger还有一个重要的属性misfire；如果scheduler关闭了，或者Quartz线程池中没有可用的线程来执行job，此时持久性的trigger就会错过(miss)其触发时间，即错过触发(misfire)
。不同类型的trigger，有不同的misfire机制。它们默认都使用“智能机制(smart policy)”，即根据trigger的类型和配置动态调整行为。当scheduler启动的时候，查询所有错过触发(misfire)
的持久性trigger。然后根据它们各自的misfire机制更新trigger的信息。当你在项目中使用Quartz时，你应该对各种类型的trigger的misfire机制都比较熟悉，这些misfire机制在JavaDoc中有说明。关于misfire机制的细节，会在讲到具体的trigger时作介绍。

## 日历示例(calendar)

Quartz的Calendar对象(不是java.util.Calendar对象)
可以在定义和存储trigger的时候与trigger进行关联。Calendar用于从trigger的调度计划中排除时间段。比如，可以创建一个trigger，每个工作日的上午9:30执行，然后增加一个Calendar，排除掉所有的商业节日。

任何实现了Calendar接口的可序列化对象都可以作为Calendar对象，Calendar接口如下：

```java
package org.quartz;

public interface Calendar {

  public boolean isTimeIncluded(long timeStamp);

  public long getNextIncludedTime(long timeStamp);

}
```

注意到这些方法的参数类型为long。你也许猜到了，他们就是毫秒单位的时间戳。即Calendar排除时间段的单位可以精确到毫秒。你也许对“排除一整天”的Calendar比较感兴趣。Quartz提供的org.quartz.impl.HolidayCalendar类可以很方便地实现。

Calendar必须先实例化，然后通过addCalendar()方法注册到scheduler。如果使用HolidayCalendar，实例化后，需要调用addExcludedDate(Date date)
方法从调度计划中排除时间段。以下示例是将同一个Calendar实例用于多个trigger：

```java
HolidayCalendar cal = new HolidayCalendar();
cal.addExcludedDate( someDate );
cal.addExcludedDate( someOtherDate );

sched.addCalendar("myHolidays", cal, false);


Trigger t = newTrigger()
    .withIdentity("myTrigger")
    .forJob("myJob")
    .withSchedule(dailyAtHourAndMinute(9, 30)) // execute job daily at 9:30
    .modifiedByCalendar("myHolidays") // but not on holidays
    .build();

// .. schedule job with trigger

Trigger t2 = newTrigger()
    .withIdentity("myTrigger2")
    .forJob("myJob2")
    .withSchedule(dailyAtHourAndMinute(11, 30)) // execute job daily at 11:30
    .modifiedByCalendar("myHolidays") // but not on holidays
    .build();

// .. schedule job with trigger2
```

接下来的几个课程将介绍触发器的施工/建造细节。现在，只要认为上面的代码创建了两个触发器，每个触发器都计划每天触发。然而，在日历所排除的期间内发生的任何发射都将被跳过。

# ch5 : Simple Trigger

SimpleTrigger可以满足的调度需求是：在具体的时间点执行一次，或者在具体的时间点执行，并且以指定的间隔重复执行若干次。比如，你有一个trigger，你可以设置它在2015年1月13日的上午11:23:
54准时触发，或者在这个时间点触发，并且每隔2秒触发一次，一共重复5次。

根据描述，你可能已经发现了，SimpleTrigger的属性包括：开始时间、结束时间、重复次数以及重复的间隔。这些属性的含义与你所期望的是一致的，只是关于结束时间有一些地方需要注意。

重复次数，可以是0、正整数，以及常量SimpleTrigger.REPEAT_INDEFINITELY。重复的间隔，必须是0，或者long型的正数，表示毫秒。注意，如果重复间隔为0，trigger将会以重复次数并发执行(
或者以scheduler可以处理的近似并发数)。

如果你还不熟悉DateBuilder，了解后你会发现使用它可以非常方便地构造基于开始时间(或终止时间)的调度策略。

endTime属性的值会覆盖设置重复次数的属性值；比如，你可以创建一个trigger，在终止时间之前每隔10秒执行一次，你不需要去计算在开始时间和终止时间之间的重复次数，只需要设置终止时间并将重复次数设置为REPEAT_INDEFINITELY(
当然，你也可以将重复次数设置为一个很大的值，并保证该值比trigger在终止时间之前实际触发的次数要大即可)。

SimpleTrigger实例通过TriggerBuilder设置主要的属性，通过SimpleScheduleBuilder设置与SimpleTrigger相关的属性。要使用这些builder的静态方法，需要静态导入：

```java
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*:
```

下面的例子，是基于简单调度(simple schedule)创建的trigger。建议都看一下，因为每个例子都包含一个不同的实现点：

指定时间开始触发，不重复：

```java
    SimpleTrigger trigger = (SimpleTrigger) newTrigger() 
        .withIdentity("trigger1", "group1")
        .startAt(myStartTime)                     // some Date 
        .forJob("job1", "group1")                 // identify job with name, group strings
        .build();
```

指定时间触发，每隔10秒执行一次，重复10次：

```java
    trigger = newTrigger()
        .withIdentity("trigger3", "group1")
        .startAt(myTimeToStartFiring)  // if a start time is not given (if this line were omitted), "now" is implied
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(10)
            .withRepeatCount(10)) // note that 10 repeats will give a total of 11 firings
        .forJob(myJob) // identify job with handle to its JobDetail itself                   
        .build();
```

5分钟以后开始触发，仅执行一次：

```java
    trigger = (SimpleTrigger) newTrigger() 
        .withIdentity("trigger5", "group1")
        .startAt(futureDate(5, IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
        .forJob(myJobKey) // identify job with its JobKey
        .build();
```

立即触发，每个5分钟执行一次，直到22:00：

```java
    trigger = newTrigger()
        .withIdentity("trigger7", "group1")
        .withSchedule(simpleSchedule()
            .withIntervalInMinutes(5)
            .repeatForever())
        .endAt(dateOf(22, 0, 0))
        .build();
```

建立一个触发器，将在下一个小时的整点触发，然后每2小时重复一次：

```java
    trigger = newTrigger()
        .withIdentity("trigger8") // because group is not specified, "trigger8" will be in the default group
        .startAt(evenHourDate(null)) // get the next even-hour (minutes and seconds zero ("00:00"))
        .withSchedule(simpleSchedule()
            .withIntervalInHours(2)
            .repeatForever())
        // note that in this example, 'forJob(..)' is not called which is valid 
        // if the trigger is passed to the scheduler along with the job  
        .build();

    scheduler.scheduleJob(trigger, job);
```

请查阅TriggerBuilder和SimpleScheduleBuilder提供的方法，以便对上述示例中未提到的选项有所了解。

```java
TriggerBuilder(以及Quartz的其它builder)会为那些没有被显式设置的属性选择合理的默认值。比如：如果你没有调用withIdentity(..)方法，TriggerBuilder会为trigger生成一个随机的名称；如果没有调用startAt(..)方法，则默认使用当前时间，即trigger立即生效。
```

## SimpleTrigger Misfire策略

SimpleTrigger有几个misfire相关的策略，告诉quartz当misfire发生的时候应该如何处理。(
Misfire策略参考[教程四：Trigger介绍](http://nkcoder.github.io/blog/20140716/quartz-tutorial-04-trigger/))。这些策略以常量的形式在SimpleTrigger中定义(JavaDoc中介绍了它们的功能)。这些策略包括：

SimpleTrigger的Misfire策略常量：

```java
MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
MISFIRE_INSTRUCTION_FIRE_NOW
MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT
MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT
MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT
MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT
```

回顾一下，所有的trigger都有一个Trigger.MISFIRE_INSTRUCTION_SMART_POLICY策略可以使用，该策略也是所有trigger的默认策略。

如果使用smart policy，SimpleTrigger会根据实例的配置及状态，在所有MISFIRE策略中动态选择一种Misfire策略。SimpleTrigger.updateAfterMisfire()
的JavaDoc中解释了该动态行为的具体细节。

在使用SimpleTrigger构造trigger时，misfire策略作为基本调度(simple schedule)的一部分进行配置(通过SimpleSchedulerBuilder设置)：

```java
    trigger = newTrigger()
        .withIdentity("trigger7", "group1")
        .withSchedule(simpleSchedule()
            .withIntervalInMinutes(5)
            .repeatForever()
            .withMisfireHandlingInstructionNextWithExistingCount())
        .build();
```