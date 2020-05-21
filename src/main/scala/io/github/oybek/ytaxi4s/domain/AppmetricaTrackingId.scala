package io.github.oybek.ytaxi4s.domain

sealed trait AppmetricaTrackingId

case class AppPage(appCode: AppCode) extends AppmetricaTrackingId {
  override def toString: String = appCode match {
    case YandexTaxi => "1178268795219780156"
    case Yango => "1179283486394045767"
  }
}

case class Site(appCode: AppCode) extends AppmetricaTrackingId {
  override def toString: String = appCode match {
    case YandexTaxi => "25395763362139037"
    case Yango => "386659076819479524"
  }
}
