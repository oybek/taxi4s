package io.github.oybek.taxi4s.api

import cats.data.Ior
import cats.effect.{Blocker, ContextShift, IO}
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
  implicit val blocker = Blocker.liftExecutionContext(global)

  "testing" must "be by hand" in {
  }
}
