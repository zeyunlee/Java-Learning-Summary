package cn.edu.jxnu.test

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Test {

    /**
     * actor1
     */
    class Actor1 extends Actor with ActorLogging {

        //创建子Actor，每个对象都有ActorContext对象，通过context方法获取
        val actor2 = context.actorOf(Props(new Actor2("actor2")), "actor2")

        //重写接收方法
        override def receive: Actor.Receive = {
            //只接收，并打印日志，ActorLogging接口
            case "test" => log.info("received test!")
            //接收，并发送给子Actor tell与!等同，tell有两个参数，使用!时第二个参数会成为隐式参数
            //sender是函数而不是val实例，使用它可以获取当前消息的发送者，该发送者与收到的消息处于同一个上下文中
            //最好使用额外的val变量保存sender函数的值。
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