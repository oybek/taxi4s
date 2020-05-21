package io.github.oybek.taxi4s.api.domain

import cats.data.Ior
import cats.syntax.all._
import io.github.oybek.taxi4s.domain._
import io.github.oybek.taxi4s.util.Url
import org.scalatest.{FlatSpec, Matchers}

class TaxiInfoSpec extends FlatSpec with Matchers {
  "formUrl method" should "form correct urls" in {
    Url.from(
      TaxiInfoReq(
        clid = "hi",
        apikey = "123",
        path = Ior.both(Coord(0.1f, 1.0f), Coord(0.2f, 2.0f)),
        clazz = Econom
      )
    ) should be (
      "https://taxi-routeinfo.taxi.yandex.net/taxi_info" +
        "?clid=hi" +
        "&apikey=123" +
        "&rll=1.0,0.1~2.0,0.2" +
        "&class=econom"
    )

    Url.from(
      TaxiInfoReq(
        clid = "hi",
        apikey = "123",
        path = Ior.left(Coord(0.1f, 1.0f)),
        clazz = Business
      )
    ) should be (
      "https://taxi-routeinfo.taxi.yandex.net/taxi_info" +
        "?clid=hi" +
        "&apikey=123" +
        "&rll=1.0,0.1" +
        "&class=business"
    )
  }
}
