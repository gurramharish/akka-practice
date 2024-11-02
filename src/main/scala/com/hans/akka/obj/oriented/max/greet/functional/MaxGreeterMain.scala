package com.hans.akka.obj.oriented.max.greet.functional

import akka.actor.typed.ActorSystem

object MaxGreeterMain {

  def main(args: Array[String]): Unit = {
    val maxGreeter = ActorSystem(MaxGreeter(0), "maxGreeter")

    maxGreeter ! MaxGreeter.Greet("Harish")
    maxGreeter ! MaxGreeter.Greet("Harish")
    maxGreeter ! MaxGreeter.Greet("Harish")
    maxGreeter ! MaxGreeter.Greet("Harish")
    maxGreeter ! MaxGreeter.Greet("Harish")

    println(">>>> Press Enter <<<<<")
    scala.io.StdIn.readLine()
    scala.util.control.Exception.ignoring(classOf[java.io.IOException])

    maxGreeter.terminate()
    print("Done!!")
  }
}
