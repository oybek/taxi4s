package io.github.oybek.taxi4s.domain

case class RideOption(className: Clazz,
                      classText: String,
                      classLevel: Int,
                      minPrice: Float,
                      price: Float,
                      priceText: String,
                      waitingTime: Option[Float])
