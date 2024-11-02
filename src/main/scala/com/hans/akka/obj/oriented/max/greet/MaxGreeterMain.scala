package com.hans.akka.obj.oriented.max.greet

import akka.actor.typed.ActorSystem
import com.hans.akka.obj.oriented.max.greet.MaxGreeter.Greet

import java.io.IOException
import scala.util.control

object MaxGreeterMain {

  def main(args: Array[String]): Unit = {
    val maxGreeter = ActorSystem(MaxGreeter(3), "maxGreeter")

    maxGreeter ! Greet("Harish")
    maxGreeter ! Greet("Harish")
    maxGreeter ! Greet("Harish")
    maxGreeter ! Greet("Harish")
    maxGreeter ! Greet("Harish")

    println(">>>> Press Enter <<<<<")
    scala.io.StdIn.readLine()
    control.Exception.ignoring(classOf[IOException])

    maxGreeter.terminate()
    print("Done!!")
  }
}
