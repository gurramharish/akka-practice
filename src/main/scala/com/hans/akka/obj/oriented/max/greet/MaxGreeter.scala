package com.hans.akka.obj.oriented.max.greet

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

object MaxGreeter {

  case class Greet(whom: String)

  def apply(maxGreets: Int): Behavior[Greet] = Behaviors.setup(new MaxGreeterBehavior(_, maxGreets))

  private class MaxGreeterBehavior(context: ActorContext[Greet], maxGreets: Int) extends AbstractBehavior[Greet](context) {
    private var greets: Int = 0


    override def onMessage(msg: Greet): Behavior[Greet] = msg match {
      case Greet(whom) =>
        if (greets < maxGreets) {
          greets = greets + 1
          context.log.info(s"Hello $whom! Greets $greets")
        } else {
          context.log.info(s"Sorry $whom, I am tired! No more greets for you! Greets $greets")
        }
        this
    }
  }
}
