package io.github.oybek.taxi4s.api.domain

import cats.data.Ior
import cats.syntax.all._
import io.circe.Json
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

  "ZoneInfoResp" should "be parsed correct" in {
    Json.obj(
      "tariffs" -> Json.arr(
        Json.obj(
          "name" -> Json.fromString("econom"),
          "supported_requirements" -> Json.arr(
            Json.obj("name" -> Json.fromString("animaltransport")),
            Json.obj("name" -> Json.fromString("ski"))
          )
        ),
        Json.obj(
          "name" -> Json.fromString("business"),
          "supported_requirements" -> Json.arr(
            Json.obj("name" -> Json.fromString("animaltransport")),
            Json.obj("name" -> Json.fromString("ski"))
          )
        ),
        Json.obj(
          "name" -> Json.fromString("comfortplus"),
          "supported_requirements" -> Json.arr(
            Json.obj("name" -> Json.fromString("ski"))
          )
        ),
        Json.obj(
          "name" -> Json.fromString("vip"),
          "supported_requirements" -> Json.arr()
        ),
      )
    ).as[ZoneInfoResp] should be (
      Right(
        ZoneInfoResp(
          tariffs = List(
            Tariff(
              name = Econom,
              supportedRequirements = List(AnimalTransport, Ski)
            ),
            Tariff(
              name = Business,
              supportedRequirements = List(AnimalTransport, Ski)
            ),
            Tariff(
              name = ComfortPlus,
              supportedRequirements = List(Ski)
            ),
            Tariff(
              name = VIP,
              supportedRequirements = List()
            )
          )
        )
      )
    )
  }
}
