package com.hans.akka.obj.oriented.barista

sealed trait Coffee
final case object Akkaccino extends Coffee
final case object CaffeJava extends Coffee
final case object MochaPlay extends Coffee
