package io.github.oybek.taxi4s.api.domain

import cats.data.Ior
import cats.syntax.all._
import io.github.oybek.taxi4s.domain._
import io.github.oybek.taxi4s.util.Url
import org.scalatest.{FlatSpec, Matchers}

class ZoneInfoSpec extends FlatSpec with Matchers {
  "zoneInfo method" should "form correct urls" in {
    Url.from(
      ZoneInfoReq(
        clid = "hi",
        apikey = "123",
        coord = Coord(0.1f, 1.0f)
      )
    ) should be (
      "https://taxi-routeinfo.taxi.yandex.net/zone_info" +
        "?clid=hi" +
        "&apikey=123" +
        "&ll=1.0,0.1"
    )
  }
}
