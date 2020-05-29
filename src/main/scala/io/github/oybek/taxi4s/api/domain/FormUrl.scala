package io.github.oybek.taxi4s.api.domain

import io.github.oybek.taxi4s.domain._

case class FormUrl(appCode: AppCode,
                   start: Option[Coord] = None,
                   end: Option[Coord] = None,
                   level: Option[Int] = None,
                   ref: Option[String] = None,
                   appmetricaTrackingId: Option[AppmetricaTrackingId] = None)

