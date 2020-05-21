package io.github.oybek.taxi4s.api.domain

import io.github.oybek.taxi4s.domain._

case class FormUrlReq(appCode: AppCode,
                      start: Option[Coord] = None,
                      end: Option[Coord] = None,
                      level: Option[Int] = None,
                      ref: Option[String],
                      appmetricaTrackingId: Option[AppmetricaTrackingId])

