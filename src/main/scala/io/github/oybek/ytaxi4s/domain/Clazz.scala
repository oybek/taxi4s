package io.github.oybek.ytaxi4s.domain

sealed trait Clazz
case object Econom extends Clazz
case object Business extends Clazz
case object ComfortPlus extends Clazz
case object Minivan extends Clazz
case object VIP extends Clazz
