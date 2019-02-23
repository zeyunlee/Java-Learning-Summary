### Scala版Akka基础

    Gradle依赖版本 compile "com.typesafe.akka:akka-actor_2.11:2.4.4"
    
    
    
[源代码库 - scala-akka-learn](https://github.com/jxnu-liguobin/Java-Learning-Summary/tree/master/scala-akka-learn)

#### 入门实例（helloworld）

```scala
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Test {

    /**
     * actor1
     */
    class Actor1 extends Actor with ActorLogging {

        //创建子Actor，每个对象都有ActorContext对象，可通过context方法获取
        val actor2 = context.actorOf(Props(new Actor2("actor2")), "actor2")

        //重写接收方法
        override def receive: Actor.Receive = {
            //只接收，并打印日志，ActorLogging接口
            case "test" => log.info("received test!")
            //接收，并发送给子Actor tell与!等同，tell有两个参数，使用!时第二个参数会成为隐式参数
            //sender是函数而不是val实例，使用它可以获取当前消息的发送者，该发送者与收到的消息处于同一个上下文中
            //最好使用额外的val变量保存sender方法的值。
            case msg => actor2.tell(msg, sender())
        }
    }

    /**
     * actor2
     *
     * @param name
     */
    class Actor2(name: String) extends Actor with ActorLogging {
    
        override def receive = {
            //打印消息+发送者
            case msg => log.info(name + " received message [{}] from sender of [{}]", msg, sender)
        }
    }

    def main(args: Array[String]): Unit = {
        //1.创建守护对象
        val demo = ActorSystem("TestActorSystem")
        //2.在守护对象下方创建Actor对象并获取该对象的ActorRef引用
        val actor1 = demo.actorOf(Props[Actor1], name = "actor1")
        //3.使用ActorRef引用向Actor对象发送消息
        actor1 ! "test"
        actor1 ! "你好"
    }

}
```

#### Actor生命周期

```scala
import akka.actor.Actor

/**
 * actor四大默认存在的方法
 * 类似Java Servlet
 * @author 梦境迷离
 * @time 2019-02-11
 */
class ShoppingCart extends Actor {
    //必须拓展（混入）Actor特质
    //按照逻辑排序的四个方法
    //开始之前，大多数情况下需要重写
    //代码块的代码被删除了
    override def preStart(): Unit = {}

    //终止之后，大多数情况下需要重写
    override def postStop(): Unit = {}

    //重启之前，通常不需要重写
    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {}

    //重启之后，默认调用preStart，通常不需要重写
    /** Actor源码该方法中
     * def postRestart(reason: Throwable): Unit = {
     * preStart()
     * }
     */
    override def postRestart(reason: Throwable): Unit = {}

    //用户编写的代码至少支持receive代码块（重写）
    override def receive = {
        //处理所有类型消息并不作处理
        case _ =>
    }

}

```
![生命周期](https://github.com/jxnu-liguobin/Java-Learning-Summary/blob/master/scala-akka-learn/src/main/scala/cn/edu/jxnu/akka/pictures/actor1.png)

[超详细Java版Actor的讲解](http://ifeve.com/akka-doc-java-untyped-actors/)