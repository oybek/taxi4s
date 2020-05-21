package io.github.oybek.ytaxi4s.api.domain

import io.github.oybek.ytaxi4s.domain.{Coord, Tariff}

case class ZoneInfoReq(clid: String, apikey: String, coord: Coord)

case class ZoneInfoResp(tariffs: List[Tariff])
