package io.github.oybek.taxi4s.api

import io.github.oybek.taxi4s.api.domain._

trait YandexTaxiApi[F[_]] {

  def zoneInfo(zoneInfoReq: ZoneInfoReq): F[ZoneInfoResp]
  def taxiInfo(taxiInfoReq: TaxiInfoReq): F[TaxiInfoResp]
}
