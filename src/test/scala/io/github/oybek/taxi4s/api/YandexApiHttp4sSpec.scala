package io.github.oybek.taxi4s.api

import cats.data.Ior
import cats.effect.{Blocker, ContextShift, IO}
import io.github.oybek.taxi4s.api.domain.TaxiInfoReq
import io.github.oybek.taxi4s.domain.{Business, Coord, Econom}
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.client.middleware.Logger
import org.http4s.client.okhttp.OkHttpBuilder
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class YandexApiHttp4sSpec extends FlatSpec with Matchers {

  implicit val ctx = IO.contextShift(global)
  implicit val blocker = Blocker.liftExecutionContext(global)

  "testing" must "be by hand" in {
    // TODO: take out to package
    OkHttpBuilder
      .withDefaultClient[IO](blocker)
      .map(_.create)
      .use { client =>
        val clid = "gdetram"
        val apikey = "565c7b100d6343068275c8f857d4982d"

        val userLocation = Coord(56.841099f, 60.659566f)
        val cinemaLocation = Coord(56.837791f, 60.598800f)
        val yandexTaxiApi = new YandexTaxiApiHttp4s[IO](client)

        for {
          taxiInfo <- yandexTaxiApi.taxiInfo(
            TaxiInfoReq(
              clid = clid,
              apikey = apikey,
              path = Ior.both(userLocation, cinemaLocation),
              clazz = Business
            )
          )
          rideOpt = taxiInfo
            .options
            .find(_.className == Business)
          replyToUser =
            rideOpt.fold(
              "No business tariffs for your path at current moment"
            )(ride =>
              s"""
                 |Taxi arrives in ${ride.waitingTime.getOrElse("unknown")} seconds
                 |Route will take ${taxiInfo.timeText.getOrElse("unknown")}
                 |Price: ${ride.priceText}
                 |""".stripMargin
            )
          _ <- IO(println(replyToUser))
        } yield ()
      }
      .unsafeRunSync()
  }
}
