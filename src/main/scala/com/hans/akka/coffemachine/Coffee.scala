package com.hans.akka.coffemachine

sealed trait Coffee
final case object Akkaccino extends Coffee
final case object CaffeJava extends Coffee
final case object MochaPlay extends Coffee
