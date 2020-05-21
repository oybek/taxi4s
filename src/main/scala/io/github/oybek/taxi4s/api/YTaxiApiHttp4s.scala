package io.github.oybek.taxi4s.api
import io.github.oybek.taxi4s.api.domain.{FormUrl, FormUrlResp, TaxiInfoReq, TaxiInfoResp, ZoneInfoReq, ZoneInfoResp}

class YTaxiApiHttp4s[F[_]] extends YTaxiApi[F] {

  override def taxiInfo(taxiInfoReq: TaxiInfoReq): F[TaxiInfoResp] = ???

  override def zoneInfo(zoneInfoReq: ZoneInfoReq): F[ZoneInfoResp] = ???
}
