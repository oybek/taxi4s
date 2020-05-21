package io.github.oybek.taxi4s.api
import io.github.oybek.taxi4s.api.domain._

class YandexTaxiApiHttp4s[F[_]] extends YandexTaxiApi[F] {

  override def taxiInfo(taxiInfoReq: TaxiInfoReq): F[TaxiInfoResp] = ???

  override def zoneInfo(zoneInfoReq: ZoneInfoReq): F[ZoneInfoResp] = ???
}
