package com.hans.akka.obj.oriented

import akka.actor.typed.ActorSystem
import com.hans.akka.functional.{Goodbye, Greet, GreetCommand}

import java.io.IOException
import scala.io.StdIn
import scala.util.control

object GreeterMain {

  def main(args: Array[String]): Unit = {


    val greeter: ActorSystem[GreetCommand] = ActorSystem(Greeter(), "greeter")

    greeter ! Greet("Harish")

    println(">>>> Press Enter <<<<<")
    StdIn.readLine()
    control.Exception.ignoring(classOf[IOException])

    greeter ! Goodbye("Harish")
    greeter.terminate()
    print("Done!!")
  }
}
