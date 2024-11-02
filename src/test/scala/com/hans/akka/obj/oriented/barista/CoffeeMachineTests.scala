package com.hans.akka.obj.oriented.barista

import akka.actor.testkit.typed.scaladsl._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import TestUtils._
import com.hans.akka.coffemachine
import com.hans.akka.coffemachine.{CoffeeMachine, MochaPlay}
import com.hans.akka.coffemachine.CoffeeMachine.{BrewCoffee, CoffeeMachineCommand, PickupCoffee}

class CoffeeMachineTests extends AnyWordSpecLike with Matchers with LogCapturing {

  "CoffeeMachine" should {
    "transition from IDLE to Brewing on receiving BrewCoffee message" in {
      val testKit: BehaviorTestKit[CoffeeMachineCommand] =
        BehaviorTestKit(CoffeeMachine())

      expectedInfoLog("CoffeeMachine: IDLE") shouldBe testKit.logEntries().last
      testKit.run(BrewCoffee(coffemachine.Akkaccino))
      expectedInfoLog("CoffeeMachine: Brewing 1 Akkaccino") shouldBe testKit.logEntries()(1)
    }

    "transition between its states along the full cycle" in {
      val testKit: BehaviorTestKit[CoffeeMachineCommand] =
        BehaviorTestKit(CoffeeMachine())

      expectedInfoLog("CoffeeMachine: IDLE") shouldBe testKit.logEntries().last
      testKit.run(BrewCoffee(coffemachine.Akkaccino))
      expectedInfoLog("CoffeeMachine: Brewing 1 Akkaccino") shouldBe testKit.logEntries().reverse(1)
      expectedInfoLog("CoffeeMachine: Coffee is ready") shouldBe testKit.logEntries().last

      testKit.run(PickupCoffee)
      expectedInfoLog("CoffeeMachine: IDLE") shouldBe testKit.logEntries().last
    }

    "be able to start a new cycle after completing one" in {
      val testKit: BehaviorTestKit[CoffeeMachineCommand] =
        BehaviorTestKit(CoffeeMachine())

      testKit.run(BrewCoffee(coffemachine.Akkaccino))
      testKit.run(PickupCoffee)
      expectedInfoLog("CoffeeMachine: IDLE") shouldBe testKit.logEntries().last
      testKit.run(BrewCoffee(MochaPlay))
      expectedInfoLog("CoffeeMachine: Brewing 1 MochaPlay") shouldBe testKit.logEntries().reverse(1)
      expectedInfoLog("CoffeeMachine: Coffee is ready") shouldBe testKit.logEntries().last
    }
  }
}
