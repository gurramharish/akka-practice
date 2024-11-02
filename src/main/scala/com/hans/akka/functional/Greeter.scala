package com.hans.akka.functional

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration

sealed trait GreetCommand
final case class Greet(whom: String) extends GreetCommand
final case class Goodbye(whom: String) extends GreetCommand

object Greeter {

  def apply(attendance: Int): Behavior[GreetCommand] = Behaviors.receive {
    (context, message) =>
      message match {
        case Greet(whom) =>
          val currentAttendance = attendance + 1
          context.log.info(s"Hello ${whom}! Attendance [$currentAttendance]")
          apply(currentAttendance)
        case Goodbye(whom) =>
          val currentAttendance = attendance - 1
          context.log.info(s"Goodbye ${whom}! Attendance [$currentAttendance]")
          apply(currentAttendance)
      }
  }

  // alternate way of creating behaviour
  def apply(v1: String): Behavior[GreetCommand] = Behaviors.setup {
    context => {
      context.log.info("{}", context.executionContext)
      context.scheduleOnce(FiniteDuration(1, TimeUnit.SECONDS), context.self, Greet("Sai"))
      Behaviors.receiveMessage[GreetCommand] {
        case Greet(whom) =>
          context.log.info(s"Hello ${whom}")
          Behaviors.same
        case Goodbye(whom) =>
          context.log.info(s"Goodbye ${whom}")
          Behaviors.same
      }
    }
  }

}
