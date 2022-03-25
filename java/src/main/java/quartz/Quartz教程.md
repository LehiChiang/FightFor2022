# ch 1 : 你了解 quartz 吗？

quartz 是一个完全由 Java 编写的开源作业调度框架，为在 Java 应用程序中进行作业调度提供了简单却强大的机制。

quartz 可以与[ J2EE](https://www.w3cschool.cn/java_interview_question/java_interview_question-wvr326ra.html) 与 J2SE
应用程序相结合也可以单独使用。

quartz 允许程序开发人员根据时间的间隔来调度作业。

quartz 实现了作业和触发器的多对多的关系，还能把多个作业与不同的触发器关联。

## **quartz 核心概念**

我们需要明白 quartz 的几个核心概念，这样理解起 quartz 的原理就会变得简单了。

1. Job

   表示一个工作，要执行的具体内容。此接口中只有一个方法，如下：

   ```java
   void execute(JobExecutionContext context) 
   ```

2. **JobDetail** 表示一个具体的可执行的调度程序，Job 是这个可执行程调度程序所要执行的内容，另外 JobDetail 还包含了这个任务调度的方案和策略。

3. **Trigger** 代表一个调度参数的配置，什么时候去调。

4. **Scheduler** 代表一个调度容器，一个调度容器中可以注册多个 JobDetail 和 Trigger。当 Trigger 与 JobDetail 组合，就可以被 Scheduler 容器调度了。

## Quartz的运行环境

- quartz 可以运行嵌入在另一个独立式应用程序。
- quartz 可以在应用程序服务器(
  或 [servlet 容器](https://www.w3cschool.cn/spring_mvc_documentation_linesh_translation/spring_mvc_documentation_linesh_translation-it8y27sq.html))内被实例化，并且参与
  XA 事务。
- quartz 可以作为一个独立的程序运行(其自己的 Java 虚拟机内)，可以通过 RMI 使用。
- quartz 可以被实例化，作为独立的项目集群(负载平衡和故障转移功能)，用于作业的执行。

# ch 2 : quartz API，Jobs和Triggers

## quartz API

quartz API的关键接口是：

- Scheduler - 与调度程序交互的主要API。
- Job - 你想要调度器执行的任务组件需要实现的接口
- JobDetail - 用于定义作业的实例。
- Trigger（即触发器） - 定义执行给定作业的计划的组件。
- JobBuilder - 用于定义/构建 JobDetail 实例，用于定义作业的实例。
- TriggerBuilder - 用于定义/构建触发器实例。
- Scheduler 的生命期，从 SchedulerFactory 创建它时开始，到 Scheduler 调用shutdown() 方法时结束；Scheduler 被创建后，可以增加、删除和列举 Job 和
  Trigger，以及执行其它与调度相关的操作（如暂停 Trigger）。但是，Scheduler 只有在调用 start() 方法后，才会真正地触发 trigger（即执行
  job），见[教程一](https://www.w3cschool.cn/quartz_doc/quartz_doc-1xbu2clr.html)。

quartz 提供的“builder”类，可以认为是一种领域特定语言（DSL，Domain Specific Language)。教程一中有相关示例，这里是其中的代码片段：（校对注：这种级联的 API
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

Trigger 用于触发 Job 的执行。当你准备调度一个 job 时，你创建一个 Trigger 的实例，然后设置调度相关的属性。Trigger 也有一个相关联的 JobDataMap，用于给 Job 传递一些触发相关的参数。quartz
自带了各种不同类型的 Trigger，最常用的主要是 SimpleTrigger 和 CronTrigger。

SimpleTrigger 主要用于一次性执行的 Job（只在某个特定的时间点执行一次），或者 Job 在特定的时间点执行，重复执行 N 次，每次执行间隔T个时间单位。CronTrigger
在基于日历的调度上非常有用，如“每个星期五的正午”，或者“每月的第十天的上午 10:15”等。

为什么既有 Job，又有 Trigger 呢？很多任务调度器并不区分 Job 和 Trigger。有些调度器只是简单地通过一个执行时间和一些 job 标识符来定义一个 Job；其它的一些调度器将 quartz 的 Job 和 Trigger
对象合二为一。在开发 quartz 的时候，我们认为将调度和要调度的任务分离是合理的。在我们看来，这可以带来很多好处。

例如，Job 被创建后，可以保存在 Scheduler 中，与 Trigger 是独立的，同一个 Job可以有多个 Trigger；这种松耦合的另一个好处是，当与 Scheduler 中的 Job 关联的 trigger 都过期时，可以配置
Job 稍后被重新调度，而不用重新定义 Job；还有，可以修改或者替换 Trigger，而不用重新定义与之关联的 Job。

## Key

将 Job 和 Trigger 注册到 Scheduler 时，可以为它们设置 key，配置其身份属性。 Job 和 Trigger 的 key（JobKey 和 TriggerKey）可以用于将 Job 和 Trigger
放到不同的分组（group）里，然后基于分组进行操作。同一个分组下的 Job 或 Trigger 的名称必须唯一，即一个 Job 或 Trigger 的 key 由名称（name）和分组（group）组成。

您现在有一个关于什么 Job
和触发器的一般概念，您可以在[第3课中](http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-03.html)了解更多信息[有关作业和作业详细信息](http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-03.html)以及[第4课：有关触发器的更多信息](http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-03.html)。

# ch 3 : Job与JobDetail介绍

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

# ch 4 : Quartz中Triggers介绍

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

# ch 5 : Simple Trigger

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
    trigger=newTrigger()
        .withIdentity("trigger7","group1")
        .withSchedule(simpleSchedule()
        .withIntervalInMinutes(5)
        .repeatForever()
        .withMisfireHandlingInstructionNextWithExistingCount())
        .build();
```

# ch 6 : CronTrigger

<p>格式：<strong>[秒] [分] [小时] [日] [月] [周] [年]</strong></p>

<div class="table-box"><table border="0" cellpadding="2" cellspacing="2"><thead><tr><th>序号</th><th>说明</th><th>是否必填</th><th>允许填写的值</th><th>允许的通配符</th></tr></thead><tbody><tr><td>1</td><td>秒</td><td>是</td><td>0-59</td><td>, - * /</td></tr><tr><td>2</td><td>分</td><td>是</td><td>0-59</td><td>, - * /</td></tr><tr><td>3</td><td>小时</td><td>是</td><td>0-23</td><td>, - * /</td></tr><tr><td>4</td><td>日</td><td>是</td><td>1-31</td><td>, - * ? / L W</td></tr><tr><td>5</td><td>月</td><td>是</td><td>1-12 or JAN-DEC</td><td>, - * /</td></tr><tr><td>6</td><td>周</td><td>是</td><td>1-7 or SUN-SAT</td><td>, - * ? / L #</td></tr><tr><td>7</td><td>年</td><td>否</td><td>empty 或 1970-2099</td><td>, - * /</td></tr></tbody></table></div>
<p><strong>通配符说明:</strong></p>

<p><span style="color:#f33b45;"><strong>*&nbsp;</strong></span>表示所有值. 例如:在分的字段上设置 "*",表示每一分钟都会触发。</p>

<p><span style="color:#f33b45;"><strong><strong>?</strong></strong>&nbsp;</span>表示不指定值。使用的场景为不需要关心当前设置这个字段的值。例如:要在每月的10号触发一个操作，但不关心是周几，所以需要周位置的那个字段设置为"?" 具体设置为 0 0 0 10&nbsp;*&nbsp;?</p>

<p><span style="color:#f33b45;"><strong><strong>-</strong></strong></span>&nbsp;表示区间。例如 在小时上设置 "10-12",表示 10,11,12点都会触发。</p>

<p><span style="color:#f33b45;"><strong>,</strong>&nbsp;</span>表示指定多个值，例如在周字段上设置 "MON,WED,FRI" 表示周一，周三和周五触发</p>

<p><span style="color:#f33b45;"><strong><strong><strong>/</strong></strong></strong></span>&nbsp;用于递增触发。如在秒上面设置"5/15" 表示从5秒开始，每增15秒触发(5,20,35,50)。在月字段上设置'1/3'所示每月1号开始，每隔三天触发一次。</p>

<p><span style="color:#f33b45;"><strong>L</strong></span>&nbsp;表示最后的意思。在日字段设置上，表示当月的最后一天(依据当前月份，如果是二月还会依据是否是润年[leap]), 在周字段上表示星期六，相当于"7"或"SAT"。如果在"L"前加上数字，则表示该数据的最后一个。例如在周字段上设置"6L"这样的格式,则表示“本月最后一个星期五"</p>

<p><span style="color:#f33b45;"><strong><strong>W</strong></strong></span>&nbsp;表示离指定日期的最近那个工作日(周一至周五). 例如在日字段上设置"15W"，表示离每月15号最近的那个工作日触发。如果15号正好是周六，则找最近的周五(14号)触发, 如果15号是周未，则找最近的下周一(16号)触发.如果15号正好在工作日(周一至周五)，则就在该天触发。如果指定格式为 "1W",它则表示每月1号往后最近的工作日触发。如果1号正是周六，则将在3号下周一触发。(注，"W"前只能设置具体的数字,不允许区间"-").</p>

<blockquote> 
 <p><strong>小提示：</strong>'L'和 'W'可以一组合使用。如果在日字段上设置"LW",则表示在本月的最后一个工作日触发（一般指发工资）&nbsp;。</p> 
</blockquote>

<p><span style="color:#f33b45;"><strong>#</strong></span>&nbsp;序号(表示每月的第几个周几)，例如在周字段上设置"6#3"表示在每月的第三个周六.注意如果指定"#5",正好第五周没有周六，则不会触发该配置(用在母亲节和父亲节再合适不过了)</p>

<blockquote> 
 <p><strong>小提示：</strong>周字段的设置，若使用英文字母是不区分大小写的&nbsp;MON&nbsp;与mon相同。</p> 
</blockquote>

<p>可通过在线生成Cron表达式的工具：<a href="http://cron.qqe2.com/">http://cron.qqe2.com/</a>&nbsp;来生成自己想要的表达式。</p>

<p><strong>常用示例：</strong></p>

<div class="table-box"><table><tbody><tr><td>0 0 12 * * ?</td><td>每天12点触发</td></tr><tr><td>0 15 10 ? * *</td><td>每天10点15分触发</td></tr><tr><td>0 15 10 * * ?</td><td>每天10点15分触发</td></tr><tr><td>0 15 10 * * ? *</td><td>每天10点15分触发</td></tr><tr><td>0 15 10 * * ? 2005</td><td>2005年每天10点15分触发</td></tr><tr><td>0 * 14 * * ?</td><td>每天下午的 2点到2点59分每分触发</td></tr><tr><td>0 0/5 14 * * ?</td><td>每天下午的 2点到2点59分(整点开始，每隔5分触发)</td></tr><tr><td>0 0/5 14,18 * * ?</td><td>每天下午的 2点到2点59分(整点开始，每隔5分触发)每天下午的 18点到18点59分(整点开始，每隔5分触发)</td></tr><tr><td>0 0-5 14 * * ?</td><td>每天下午的 2点到2点05分每分触发</td></tr><tr><td>0 10,44 14 ? 3 WED</td><td>3月分每周三下午的 2点10分和2点44分触发</td></tr><tr><td>0 15 10 ? * MON-FRI</td><td>从周一到周五每天上午的10点15分触发</td></tr><tr><td>0 15 10 15 * ?</td><td>每月15号上午10点15分触发</td></tr><tr><td>0 15 10 L * ?</td><td>每月最后一天的10点15分触发</td></tr><tr><td>0 15 10 ? * 6L</td><td>每月最后一周的星期五的10点15分触发</td></tr><tr><td>0 15 10 ? * 6L 2002-2005</td><td>从2002年到2005年每月最后一周的星期五的10点15分触发</td></tr><tr><td>0 15 10 ? * 6#3</td><td>每月的第三周的星期五开始触发</td></tr><tr><td>0 0 12 1/5 * ?</td><td>每月的第一个中午开始每隔5天触发一次</td></tr><tr><td>0 11 11 11 11 ?</td><td>每年的11月11号 11点11分触发(光棍节)</td></tr></tbody></table></div>

# ch 7 : TriggerListeners和JobListeners

**Listeners**是您创建的对象，用于根据调度程序中发生的事件执行操作。您可能猜到，TriggerListeners接收到与触发器（trigger）相关的事件，JobListeners 接收与jobs相关的事件。

与触发相关的事件包括：触发器触发，触发失灵（在本文档的“触发器”部分中讨论），并触发完成（触发器关闭的作业完成）。

org.quartz.TriggerListener接口

```java
public interface TriggerListener {

    public String getName();

    public void triggerFired(Trigger trigger, JobExecutionContext context);

    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context);

    public void triggerMisfired(Trigger trigger);

    public void triggerComplete(Trigger trigger, JobExecutionContext context,
                                int triggerInstructionCode);
}
```

job相关事件包括：job即将执行的通知，以及job完成执行时的通知。

org.quartz.JobListener接口

```java
public interface JobListener {

    public String getName();

    public void jobToBeExecuted(JobExecutionContext context);

    public void jobExecutionVetoed(JobExecutionContext context);

    public void jobWasExecuted(JobExecutionContext context,
                               JobExecutionException jobException);

}
```

### 使用自己的Listeners

要创建一个listener，只需创建一个实现org.quartz.TriggerListener和/或org.quartz.JobListener接口的对象。然后，listener在运行时会向调度程序注册，并且必须给出一个名称（或者，他们必须通过他们的getName（）方法来宣传自己的名字）。

为了方便起见，实现这些接口，您的类也可以扩展JobListenerSupport类或TriggerListenerSupport类，并且只需覆盖您感兴趣的事件。

listener与调度程序的ListenerManager一起注册，并配有描述listener希望接收事件的job/触发器的Matcher。

```java
Listener在运行时间内与调度程序一起注册，并且不与jobs和触发器一起存储在JobStore中。这是因为听众通常是与应用程序的集成点。因此，每次运行应用程序时，都需要重新注册该调度程序。
```

**添加对特定job感兴趣的JobListener：**

```java
scheduler.getListenerManager().addJobListener(myJobListener，KeyMatcher.jobKeyEquals(new JobKey("myJobName"，"myJobGroup")));
```

您可能需要为匹配器和关键类使用静态导入，这将使您定义匹配器更简洁：

```java
import static org.quartz.JobKey.*;
import static org.quartz.impl.matchers.KeyMatcher.*;
import static org.quartz.impl.matchers.GroupMatcher.*;
import static org.quartz.impl.matchers.AndMatcher.*;
import static org.quartz.impl.matchers.OrMatcher.*;
import static org.quartz.impl.matchers.EverythingMatcher.*;
...etc.
```

这将上面的例子变成这样：

```java
scheduler.getListenerManager().addJobListener(myJobListener,jobKeyEquals(jobKey("myJobName","myJobGroup")));
```

添加对特定组的所有job感兴趣的JobListener：

```java
scheduler.getListenerManager().addJobListener(myJobListener,jobGroupEquals("myJobGroup"));
```

添加对两个特定组的所有job感兴趣的JobListener：

```java
scheduler.getListenerManager().addJobListener(myJobListener,or(jobGroupEquals("myJobGroup"),jobGroupEquals("yourGroup")));
```

添加对所有job感兴趣的JobListener：

```java
scheduler.getListenerManager().addJobListener(myJobListener,allJobs());
```

注册TriggerListeners的工作原理相同。

Quartz的大多数用户并不使用Listeners，但是当应用程序需求创建需要事件通知时不需要Job本身就必须明确地通知应用程序，这些用户就很方便。

# ch 8 : SchedulerListeners

2018-09-15 11:02 更新

SchedulerListeners非常类似于TriggerListeners和JobListeners，除了它们在Scheduler本身中接收到事件的通知 - 不一定与特定触发器（trigger）或job相关的事件。

与计划程序相关的事件包括：添加job/触发器，删除job/触发器，调度程序中的严重错误，关闭调度程序的通知等。

org.quartz.SchedulerListener接口

```java
public interface SchedulerListener {

    public void jobScheduled(Trigger trigger);

    public void jobUnscheduled(String triggerName, String triggerGroup);

    public void triggerFinalized(Trigger trigger);

    public void triggersPaused(String triggerName, String triggerGroup);

    public void triggersResumed(String triggerName, String triggerGroup);

    public void jobsPaused(String jobName, String jobGroup);

    public void jobsResumed(String jobName, String jobGroup);

    public void schedulerError(String msg, SchedulerException cause);

    public void schedulerStarted();

    public void schedulerInStandbyMode();

    public void schedulerShutdown();

    public void schedulingDataCleared();
}
```

SchedulerListeners注册到调度程序的ListenerManager。SchedulerListeners几乎可以实现任何实现org.quartz.SchedulerListener接口的对象。

添加SchedulerListener：

```java
scheduler.getListenerManager().addSchedulerListener(mySchedListener);
```

删除SchedulerListener：

```java
scheduler.getListenerManager().removeSchedulerListener(mySchedListener);
```

# ch 9 : SpringBoot整合Quartz

在SpringBoot中，我们需要引入quartz的依赖。

```pom
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!--quartz定时调度依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```

首先定义定时具体执行逻辑Job，创建类QuartzJob1，这里集继承QuartzJobBean实现executeInternal即可，该方法即定时执行任务逻辑，这里简单打印了下当前时间。

```java
public class QuartzJob1 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("QuartzJob1----" + sdf.format(new Date()));
    }

}
```

然后创建QuartzConfig，接着定义JobDetail，JobDetail由JobBuilder构建，同时关联了任务QuartzJob1。

```java

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail1() {
        return JobBuilder.newJob(QuartzJob1.class).storeDurably().build();
    }

}
```

最后我们需要定义定时调度Trigger，简单实现类SimpleScheduleBuilder用于构建Scheduler，TriggerBuilder则用于构建Trigger，

```java

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail1() {
        return JobBuilder.newJob(QuartzJob1.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger1() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                                                                     .withIntervalInSeconds(1) //每一秒执行一次
                                                                     .repeatForever(); //永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
                             .forJob(jobDetail1())
                             .withSchedule(scheduleBuilder)
                             .build();
    }

}
```

这样一个Quartz定时任务就配置完成了。

其实Job的定义也可以使用内部类，这样可以省去Job类的创建，例如下面定时任务2 jobDetail2和trigger2。

```java
@Bean
public JobDetail jobDetail2(){
        QuartzJobBean quartzJob2=new QuartzJobBean(){
@Override
protected void executeInternal(JobExecutionContext jobExecutionContext)throws JobExecutionException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("内部类quartzJob2----"+sdf.format(new Date()));
        }
        };
        return JobBuilder.newJob(quartzJob2.getClass()).storeDurably().build();
        }

@Bean
public Trigger trigger2(){
        //JobDetail的bean注入不能省略
        //JobDetail jobDetail3 = JobBuilder.newJob(QuartzJob2.class).storeDurably().build();
        SimpleScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.simpleSchedule()
        .withIntervalInSeconds(2) //每2秒执行一次
        .repeatForever(); //永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
        .forJob(jobDetail2())
        .withSchedule(scheduleBuilder).build();
        }
```

启动程序，我们就可以看到控制台的时间输出了。

![img](https://img2018.cnblogs.com/blog/1669484/201911/1669484-20191109230147899-858887690.png)

同时Quartz是支持数据持久化的，可以将定时调度信息持久化到数据库。

选择持久化到数据库，我们需要创建对应的表，建表语句可以在[Quartz官网](http://www.quartz-scheduler.org/downloads/)进行下载，解压后在docs\dbTables目录下寻找对应数据库的SQL脚本。

为了方便，我也将该文件放在了项目源码resources里。

操作数据库，我们引入相关的依赖。若有ORM框架，例如mybatis，hibernate或者jpa，则无需再引入jdbc依赖。

```java
<!--mysql连接-->
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<scope>runtime</scope>
</dependency>

<!--druid连接池-->
<dependency>
<groupId>com.alibaba</groupId>
<artifactId>druid-spring-boot-starter</artifactId>
<version>1.1.10</version>
</dependency>

<!--jdbc依赖-->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

在application.yml配置文件中，我们对quartz持久化方式进行声明。

```yml
server:
  port: 10900

spring:
  profiles:
    active: dev
  quartz:
    job-store-type: jdbc #持久化到数据库
    properties:
      org:
        quartz:
          datasource:
            # 新版驱动从com.mysql.jdbc.Driver变更为com.mysql.cj.jdbc.Driver
            driver-class-name: com.mysql.cj.jdbc.Driver
            # 数据源需要添加时间标准和指定编码格式解决乱码 You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
            url: jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
            username: root
            password: 1234
          scheduler:
            instancName: clusteredScheduler
            instanceId: AUTO
          jobStore:
            class: org.quartz.impl.jdbcjobstore.JobStoreTX
            driverDelegateClass: org.quartz.impl.jdbcjobstore.StdJDBCDelegate #StdJDBCDelegate说明支持集群
            tablePrefix: QRTZ_
            isClustered: true
            clusterCheckinInterval: 1000
            useProperties: false
          threadPool:
            class: org.quartz.simpl.SimpleThreadPool
            threadCount: 20
            threadPriority: 5
```

这里主要就是job-store-type:
jdbc，表示持久化到数据库，然后就是数据源，由于该演示项目没有其他ORM的数据源，所以这里将数据源信息定义在了quartz节点下的datasource节点，如果已经存在，可使用同一个属性配置，当然最关键的是QuartzDataSource声明。

这里关键的是@QuartzDataSource，这个要和项目中已经存在的数据源区分开。

```java
//Error:EmbeddedDatabaseType class not found，Druid数据源初始化需要引入spring-jdbc依赖，JPA或mybatis依赖已经包含该依赖
@Bean
@QuartzDataSource
@ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
DataSource quartzDataSource(){
        return DruidDataSourceBuilder.create().build();
        }
```

这样持久化就已经配置好了，我们执行sql，再启动项目，启动完成后，我们可以看到数据库中已经有我们的定时调度数据了。

![img](https://img2018.cnblogs.com/blog/1669484/201911/1669484-20191109230148272-1932709898.png)

源码地址：https://github.com/imyanger/springboot-project/tree/master/p25-springboot-quartz
