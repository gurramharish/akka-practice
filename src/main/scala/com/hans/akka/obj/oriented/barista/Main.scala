package com.hans.akka.obj.oriented.barista

import java.io.IOException
import scala.io.StdIn.readLine
import scala.util.control.Exception
import akka.actor.typed.ActorSystem
import Barista.OrderCoffee

object Main {

  def main(args: Array[String]): Unit = {
    val barista: ActorSystem[OrderCoffee] = ActorSystem(Barista(), "barista")

    barista ! OrderCoffee("Bart", Akkaccino)
    barista ! OrderCoffee("Lisa", MochaPlay)
    barista ! OrderCoffee("Homer", Akkaccino)

    println(">>> Press ENTER to exit <<<")
    readLine()
    Exception.ignoring(classOf[IOException])

    barista.terminate()
  }
}
