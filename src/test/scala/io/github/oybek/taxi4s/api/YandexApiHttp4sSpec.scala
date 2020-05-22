package io.github.oybek.taxi4s.api

import cats.data.Ior
import cats.effect.{ContextShift, IO}
import io.github.oybek.taxi4s.api.domain.TaxiInfoReq
import io.github.oybek.taxi4s.domain.{Coord, Econom}
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.client.middleware.Logger
import org.http4s.client.okhttp.OkHttpBuilder
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class YandexApiHttp4sSpec extends FlatSpec with Matchers {

  implicit val ctx = IO.contextShift(global)

  "testing" must "be by hand" in {
    println("hi")
    OkHttpBuilder
        .withDefaultClient(blocker = )
    BlazeClientBuilder[IO](global).withDefaultSslContext
      .withIdleTimeout(Duration.Inf)
      .withRequestTimeout(Duration.Inf)
      .withResponseHeaderTimeout(Duration.Inf)
      .resource
      .use { client =>
        val http = Logger(logBody = true, logHeaders = true)(client)
        val yandexTaxiApi = new YandexTaxiApiHttp4s[IO](http)
        yandexTaxiApi
          .taxiInfo(
            TaxiInfoReq(
              clid = "gdetram",
              apikey = "e94719aa825141eeb25f0d36e8056274",
              path = Ior.both(
                Coord(60.659566f, 56.841099f),
                Coord(60.598800f, 56.837791f)
              ),
              clazz = Econom
            )
          )
          .flatMap { x =>
            IO {
              println("hhhhhhhhhhhhhhhhhhhhhi:" + x)
            }
          }
      }
      .unsafeRunSync()
  }
}
