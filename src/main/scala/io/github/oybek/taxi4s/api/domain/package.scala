package io.github.oybek.taxi4s.api

import cats.data.Ior
import io.github.oybek.taxi4s.domain._
import io.github.oybek.taxi4s.util.Url

package object domain {

  implicit val taxiInfoEncoder = Url.derive[TaxiInfoReq] {
    case TaxiInfoReq(clid, apikey, path, clazz) =>
      "https://taxi-routeinfo.taxi.yandex.net/taxi_info?" +
      List(
        s"clid=$clid",
        s"apikey=$apikey",
        path.fold(
          a => s"rll=${a.longitude},${a.latitude}",
          b => s"rll=${b.longitude},${b.latitude}",
          (a, b) => s"rll=${a.longitude},${a.latitude}~${b.longitude},${b.latitude}"
        ),
        s"class=${clazz.toString.toLowerCase}"
      ).mkString("&")
  }

  implicit val formUrlEncoder = Url.derive[FormUrl] {
    case FormUrl(appCode, start, end, level, ref, appmetricaTrackingId) =>
      "https://" +
        (appCode match {
          case YandexTaxi => "3"
          case Yango => "2187871"
        }) +
        ".redirect.appmetrica.yandex.com/route?" +
        List(
          start.map {
            case Coord(lat, lon) => s"start-lat=$lat&start-lon=$lon"
          },
          end.map {
            case Coord(lat, lon) => s"end-lat=$lat&end-lon=$lon"
          },
          level.map(x => s"level=$x"),
          ref.map(x => s"ref=$x"),
          appmetricaTrackingId
            .map(_ -> appCode)
            .map {
              case (AppPage, YandexTaxi) => "1178268795219780156"
              case (Site, YandexTaxi) => "25395763362139037"
              case (AppPage, Yango) => "1179283486394045767"
              case (Site, Yango) => "386659076819479524"
            }.map(x => s"appmetrica_tracking_id=$x")
        ).collect { case Some(x) => x }.mkString("&")
  }
}
