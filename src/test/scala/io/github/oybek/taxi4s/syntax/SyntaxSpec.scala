package io.github.oybek.taxi4s.syntax

import cats.data.Ior.Both
import cats.effect.{Blocker, IO}
import io.github.oybek.taxi4s.api.YandexTaxiApi
import io.github.oybek.taxi4s.api.domain.{FormUrl, TaxiInfoReq, ZoneInfoReq}
import io.github.oybek.taxi4s.domain
import io.github.oybek.taxi4s.domain.{AppPage, Coord, Econom, YandexTaxi}
import io.github.oybek.taxi4s.util.Url
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global

class SyntaxSpec extends FlatSpec with Matchers with MockFactory {

  implicit val ctx = IO.contextShift(global)
  implicit val blocker = Blocker.liftExecutionContext(global)

  implicit val cfg = YandexTaxiSyntax.Cfg(clid="", apiKey="")
  implicit val yandexApi = mock[YandexTaxiApi[IO]]

  "syntax" should "work correct" in {
    import YandexTaxiSyntax._

    val coord = Coord(0.1f, 0.2f)

    (yandexApi.zoneInfo _).expects(
      ZoneInfoReq(
        clid = cfg.clid,
        apikey = cfg.apiKey,
        coord = coord
      )
    ).once()

    //
    coord.zoneInfo
  }

  "syntax" should "work correct!" in {
    import YandexTaxiSyntax._

    val A = Coord(0.1f, 0.2f)
    val B = Coord(0.3f, 0.4f)

    (yandexApi.taxiInfo _).expects(
      TaxiInfoReq(
        clid = cfg.clid,
        apikey = cfg.apiKey,
        path = Both(A, B),
        clazz = Econom
      )
    ).once()

    //
    (A to B).request(Econom)
  }

  "syntax" should "work correct!!" in {
    import YandexTaxiSyntax._

    val A = Coord(0.1f, 0.2f)
    val B = Coord(0.3f, 0.4f)

    //
    (A to B).by(YandexTaxi, AppPage, "123") should be (
      Url.from(
        FormUrl(
          YandexTaxi, Some(A), Some(B), None, Some("123"), Some(AppPage)
        )
      )
    )
  }
}
