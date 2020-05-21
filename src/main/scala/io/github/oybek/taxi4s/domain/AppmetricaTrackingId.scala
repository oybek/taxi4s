package io.github.oybek.taxi4s.domain

sealed trait AppmetricaTrackingId
case object AppPage extends AppmetricaTrackingId
case object Site extends AppmetricaTrackingId
