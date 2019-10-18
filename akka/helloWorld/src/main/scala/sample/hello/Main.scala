package sample.hello

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props, Terminated}
import com.typesafe.config._

object Main {

  /*
  def main(args: Array[String]): Unit = {
    akka.Main.main(Array(classOf[HelloWorld].getName))
  }
  */

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    config.checkValid(ConfigFactory.defaultReference(), "simple-lib")

    val foo = config.getString("simple-lib.foo")

    val system = ActorSystem("Hello", config.getConfig("myapp1").withFallback(config))
    val a = system.actorOf(Props[HelloWorld], "helloWorld")
    system.actorOf(Props(classOf[Terminator], a), "terminator")
  }

  class Terminator(ref: ActorRef) extends Actor with ActorLogging {
    context watch ref
    def receive = {
      case Terminated(_) =>
        log.info("{} has terminated, shutting down system", ref.path)
        context.system.terminate()
    }
  }

}