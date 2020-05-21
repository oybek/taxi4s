package io.github.oybek.taxi4s.api.domain

import cats.effect.IO
import cats.syntax.all._
import io.github.oybek.taxi4s.api.YTaxiApiHttp4s
import io.github.oybek.taxi4s.domain._
import io.github.oybek.taxi4s.util.Url
import org.scalatest.{FlatSpec, Matchers}

class FormUrlSpec extends FlatSpec with Matchers {
  "formUrl method" should "form correct urls" in {
    val yTaxiApiHttp4s = new YTaxiApiHttp4s[IO]

    val formUrlReq = FormUrlReq(
      appCode = YandexTaxi,
      start = Coord(0.1f, 1.0f).some,
      end = Coord(0.2f, 2.0f).some,
      level = 1.some,
      ref = "123".some,
      appmetricaTrackingId = AppPage.some
    )

    def formUrlResp(s1: String, s2: String) =
      s"https://$s1.redirect.appmetrica.yandex.com/route" +
        "?start-lat=0.1" +
        "&start-lon=1.0" +
        "&end-lat=0.2" +
        "&end-lon=2.0" +
        "&level=1" +
        "&ref=123" +
        s"&appmetrica_tracking_id=$s2"


    Url.from(
      formUrlReq.copy(
        appCode = YandexTaxi,
        appmetricaTrackingId = AppPage.some
      )
    ) should be (formUrlResp("3", "1178268795219780156"))

    Url.from(
      formUrlReq.copy(
        appCode = YandexTaxi,
        appmetricaTrackingId = Site.some
      )
    ) should be (formUrlResp("3", "25395763362139037"))

    Url.from(
      formUrlReq.copy(
        appCode = Yango,
        appmetricaTrackingId = AppPage.some
      )
    ) should be (formUrlResp("2187871", "1179283486394045767"))

    Url.from(
      formUrlReq.copy(
        appCode = Yango,
        appmetricaTrackingId = Site.some
      )
    ) should be (formUrlResp("2187871", "386659076819479524"))
  }
}
