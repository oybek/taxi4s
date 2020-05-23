# Taxi4s ![master](https://github.com/oybek/taxi4s/workflows/master/badge.svg) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=oybek_taxi4s&metric=coverage)](https://sonarcloud.io/dashboard?id=oybek_taxi4s) [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=oybek_taxi4s&metric=ncloc)](https://sonarcloud.io/dashboard?id=oybek_taxi4s) <a href="https://typelevel.org/cats/"><img src="https://typelevel.org/cats/img/cats-badge.svg" height="40px" align="right" alt="Cats friendly" /></a>

Comfortable interface for working with taxi service api

## Usage

```scala
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
         |""".stripMargin)
  ...
} yield ()
```

`client` could be any http4s backend (`blaze`, `okhttp`, `finagle`, etc)

## Taxi services supported

* [Yandex.Taxi](https://yandex.ru/dev/taxi/)
* [Uber (Coming soon)](https://developer.uber.com/docs)

