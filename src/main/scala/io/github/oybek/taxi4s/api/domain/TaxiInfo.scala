package io.github.oybek.taxi4s.api.domain

import cats.data.Ior
import io.github.oybek.taxi4s.domain._

case class TaxiInfoReq(clid: String,
                       apikey: String,
                       path: Ior[Coord, Coord],
                       clazz: Clazz)

case class TaxiInfoResp(currency: Currency,
                        distance: Float,
                        options: List[RideOption],
                        time: Option[Float],
                        timeText: Option[String])
