package io.github.oybek.ytaxi4s.domain

import scala.concurrent.duration.FiniteDuration

case class RideOption(clazz: Clazz,
                      clazzText: String,
                      clazzLevel: Int,
                      minPrice: Float,
                      price: Float,
                      priceText: String,
                      waitingTime: Option[FiniteDuration])
