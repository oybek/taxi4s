# Taxi4s ![master](https://github.com/oybek/taxi4s/workflows/master/badge.svg) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=oybek_taxi4s&metric=coverage)](https://sonarcloud.io/dashboard?id=oybek_taxi4s) [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=oybek_taxi4s&metric=ncloc)](https://sonarcloud.io/dashboard?id=oybek_taxi4s) <a href="https://typelevel.org/cats/"><img src="https://typelevel.org/cats/img/cats-badge.svg" height="40px" align="right" alt="Cats friendly" /></a>

Comfortable interface for working with taxi service api

## Usage

```scala
import YandexTaxiSyntax._

implicit val cfg = YandexTaxiSyntax.Cfg(clid="", apiKey="")
implicit val yandexTaxiApi = new YandexTaxiApiHttp4s[IO](client)

val user = Coord(56.841099f, 60.659566f)
val cinema = Coord(56.837791f, 60.598800f)

for {
  taxiInfo <- (user to cinema).request(Econom)
  rideOpt = taxiInfo
    .options
    .find(_.className == Econom)
  replyToUser =
    rideOpt.fold(
      "No econom tariffs for your path at current moment"
    )(ride =>
      s"""
         |Taxi arrives in ${ride.waitingTime.getOrElse("unknown")} seconds
         |Route will take ${taxiInfo.timeText.getOrElse("unknown")}
         |Price: ${ride.priceText}
         |""".stripMargin)
  ...
} yield ()
```

`client` could be any http4s backend (`blaze`, `okhttp`, `finagle`, etc)

## Taxi services supported

* [Yandex.Taxi](https://yandex.ru/dev/taxi/)
* [Uber (Coming soon)](https://developer.uber.com/docs)

