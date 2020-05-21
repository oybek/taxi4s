package io.github.oybek.ytaxi4s.api.domain

import cats.data.Ior
import io.github.oybek.ytaxi4s.domain.{Clazz, Coord, Currency, RideOption}

import scala.concurrent.duration.FiniteDuration

case class TaxiInfoReq(clid: String,
                       apikey: String,
                       path: Ior[Coord, Coord],
                       clazz: Clazz)

case class TaxiInfoResp(currency: Currency,
                        distance: Int,
                        options: List[RideOption],
                        time: Option[FiniteDuration],
                        timeText: Option[String])
