package io.github.oybek.ytaxi4s.domain

sealed trait AppCode

case object YandexTaxi extends AppCode {
  override def toString: String = "3"
}

case object Yango extends AppCode {
  override def toString: String = "2187871"
}
