package io.github.oybek.ytaxi4s.domain

sealed trait Currency
case object RUB extends Currency // российский рубль.
case object KZT extends Currency // казахстанский тенге.
case object AMD extends Currency // армянский драм.
case object GEL extends Currency // грузинский лари.
case object AZN extends Currency // азербайджанский манат.
case object UAH extends Currency // украинская гривна.
case object BYN extends Currency // новый белорусский рубль.
case object BYR extends Currency // старый белорусский рубль.
case object KGS extends Currency // киргизский сом.
case object EUR extends Currency // евро.
case object MDL extends Currency // молдавский лей.
case object UZS extends Currency // узбекский сум.
