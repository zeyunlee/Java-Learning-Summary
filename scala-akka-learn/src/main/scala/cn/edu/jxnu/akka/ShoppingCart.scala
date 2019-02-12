package cn.edu.jxnu.akka

import akka.actor.Actor

/**
 * actor四大默认存在的方法
 *
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
