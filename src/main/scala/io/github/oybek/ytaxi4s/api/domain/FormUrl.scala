package io.github.oybek.ytaxi4s.api.domain

import io.github.oybek.ytaxi4s.domain._

case class FormUrlReq(appCode: AppCode,
                      start: Option[Coord],
                      end: Option[Coord],
                      level: Option[Int],
                      ref: Option[String],
                      appmetricaTrackingId: Option[AppmetricaTrackingId])

case class FormUrlResp(url: String)
