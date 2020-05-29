package io.github.oybek.taxi4s

import cats.data.Ior
import cats.data.Ior.Both
import io.github.oybek.taxi4s.api.YandexTaxiApi
import io.github.oybek.taxi4s.api.domain.{FormUrl, TaxiInfoReq, TaxiInfoResp, ZoneInfoReq, ZoneInfoResp}
import io.github.oybek.taxi4s.domain.{AppCode, AppmetricaTrackingId, Clazz, Coord}
import io.github.oybek.taxi4s.util.Url

package object syntax {

  object YandexTaxiSyntax {
    case class Cfg(clid: String, apiKey: String)

    implicit class Start(val a: Coord) extends AnyVal {
      def to(b: Coord): (Coord, Coord) = (a, b)

      def zoneInfo[F[_]](implicit cfg: Cfg, yandexTaxi: YandexTaxiApi[F]): F[ZoneInfoResp] =
        yandexTaxi.zoneInfo(
          ZoneInfoReq(
            clid = cfg.clid,
            apikey = cfg.apiKey,
            coord = a
          )
        )
    }

    implicit class Path(val ab: (Coord, Coord)) extends AnyVal {
      def request[F[_]](clazz: Clazz)(implicit cfg: Cfg, yandexTaxi: YandexTaxiApi[F]): F[TaxiInfoResp] =
        yandexTaxi.taxiInfo(
          TaxiInfoReq(
            clid = cfg.clid,
            apikey = cfg.apiKey,
            path = Both(ab._1, ab._2),
            clazz = clazz
          )
        )

      def by(appCode: AppCode, redirect: AppmetricaTrackingId, ref: String): String =
        Url.from(
          FormUrl(
            appCode = appCode,
            start = Some(ab._1),
            end = Some(ab._2),
            appmetricaTrackingId = Some(redirect),
            ref = Some(ref)
          )
        )
    }
  }
}
