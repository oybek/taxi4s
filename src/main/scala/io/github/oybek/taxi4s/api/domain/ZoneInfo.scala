package io.github.oybek.taxi4s.api.domain

import io.github.oybek.taxi4s.domain.{Coord, Tariff}

case class ZoneInfoReq(clid: String, apikey: String, coord: Coord)

case class ZoneInfoResp(tariffs: List[Tariff])
