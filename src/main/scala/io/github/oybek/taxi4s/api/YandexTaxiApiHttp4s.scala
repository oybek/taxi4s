package io.github.oybek.taxi4s.api

import cats.syntax.all._
import cats.effect.Sync
import io.github.oybek.taxi4s.api.domain._
import io.github.oybek.taxi4s.util.Url
import org.http4s.{Request, Uri}
import org.http4s.dsl.io._
import org.http4s.circe._
import org.http4s.client.Client
import io.github.oybek.taxi4s.domain._

class YandexTaxiApiHttp4s[F[_]: Sync](http: Client[F]) extends YandexTaxiApi[F] {

  override def taxiInfo(taxiInfoReq: TaxiInfoReq): F[TaxiInfoResp] =
    for {
      uri <- Sync[F].fromEither[Uri](Uri.fromString(Url.from(taxiInfoReq)))
      req = Request[F](method = GET, uri = uri)
      res <- http.expect(req)(jsonOf[F, TaxiInfoResp])
    } yield res

  override def zoneInfo(zoneInfoReq: ZoneInfoReq): F[ZoneInfoResp] = ???
}
