package io.github.oybek.taxi4s.domain

sealed trait AppCode
case object YandexTaxi extends AppCode
case object Yango extends AppCode
