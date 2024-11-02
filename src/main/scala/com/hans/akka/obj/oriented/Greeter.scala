package com.hans.akka.obj.oriented

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext}
import com.hans.akka.functional.{Goodbye, Greet, GreetCommand}

object Greeter {

  def apply(): Behavior[GreetCommand] = Behaviors.setup(context => new GreetBehavior(context))

  private class GreetBehavior(context: ActorContext[GreetCommand]) extends AbstractBehavior[GreetCommand](context) {

    private var attendance: Int = 0

    override def onMessage(msg: GreetCommand): Behavior[GreetCommand] = {
      msg match {
        case Greet(whom) =>
          attendance = attendance + 1
          context.log.info(s"Hello $whom! Attendance [$attendance]")
          this
        case Goodbye(whom) =>
          attendance = attendance - 1
          context.log.info(s"Goodbye $whom! Attendance [$attendance]")
          this
      }
    }
  }
}
