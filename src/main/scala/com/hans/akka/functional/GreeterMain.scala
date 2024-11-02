package com.hans.akka.functional

import akka.actor.typed.ActorSystem

import java.io.IOException
import scala.io.StdIn
import scala.util.control

object GreeterMain {


  def main(args: Array[String]): Unit = {
    val greeter: ActorSystem[GreetCommand] = ActorSystem(Greeter("hello"), "greeter")
    val attendanceGreeter: ActorSystem[GreetCommand] = ActorSystem(Greeter(0), "attendance-greeter")

    greeter ! Greet("Harish")

    attendanceGreeter ! Greet("Harish Gurram")
    attendanceGreeter ! Greet("Sai Gurram")
    attendanceGreeter ! Goodbye("Sai Gurram")

    println(">>>> Press Enter <<<<<")
    StdIn.readLine()
    control.Exception.ignoring(classOf[IOException])

    greeter ! Goodbye("Harish")
    greeter.terminate()
    attendanceGreeter.terminate()
    print("Done!!")
  }
}
