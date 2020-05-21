package io.github.oybek.ytaxi4s.api

import io.github.oybek.ytaxi4s.api.domain._

trait YTaxiApi[F[_]] {

  def formUrl(formUrlReq: FormUrlReq): F[FormUrlResp]
  def zoneInfo(zoneInfoReq: ZoneInfoReq): F[ZoneInfoResp]
  def taxiInfo(taxiInfoReq: TaxiInfoReq): F[TaxiInfoResp]
}
