package io.github.oybek.taxi4s.api.domain

import cats.data.Ior
import io.circe.{Json, JsonNumber}
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

  "TaxiInfoResp" should "be parsed correct" in {
    Json.obj(
      "currency" -> Json.fromString("RUB"),
      "distance" -> Json.fromFloatOrString(4806.4010953903198f),
      "options" -> Json.arr(
        Json.obj(
          "class_level" -> Json.fromInt(50),
          "class_name" -> Json.fromString("econom"),
          "class_text" -> Json.fromString("Эконом"),
          "min_price" -> Json.fromFloatOrString(69.0f),
          "price" -> Json.fromFloatOrString(116.0f),
          "price_text" -> Json.fromString("~116 руб."),
          "waiting_time" -> Json.fromFloatOrString(335.0f)
        )
      ),
      "time" -> Json.fromFloatOrString(794.64194101548821f),
      "time_text" -> Json.fromString("14 мин")
    ).as[TaxiInfoResp] should be (
      Right(
        TaxiInfoResp(
          currency = RUB,
          distance = 4806.4010953903198f,
          options = List(
            RideOption(
              className = Econom,
              classText = "Эконом",
              classLevel = 50,
              minPrice = 69.0f,
              price = 116.0f,
              priceText = "~116 руб.",
              waitingTime = Some(335.0f)
            )
          ),
          time = Some(794.64194101548821f),
          timeText = Some("14 мин")
        )
      )
    )
  }
}
