package com.hans.akka.obj.oriented.max.greet.functional

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object MaxGreeter {

  case class Greet(whom: String)


  def apply(greets: Int, maxGreets: Int = 3): Behavior[Greet] = Behaviors.setup {
    context => {
      Behaviors.receiveMessage {
        case Greet(whom) =>
          if (greets < maxGreets) {
            val greetsCount  = greets + 1
            context.log.info(s"Hello $whom! Greets $greetsCount")
            MaxGreeter(greetsCount, maxGreets)
          } else {
            context.log.info(s"Sorry $whom, I am tired! No more greets for you! Greets $greets")
            MaxGreeter()
          }
      }
    }
  }

  private def apply(): Behavior[Greet] = Behaviors.receive {
    (context, message) =>
      message match {
        case Greet(whom) =>
          context.log.info(s"zZZZ..., Sorry $whom I am tired! No more greets for you!")
          Behaviors.same
      }
  }
}
