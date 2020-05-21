package io.github.oybek.taxi4s

import io.circe.Decoder
import io.circe.generic.extras.Configuration
import io.github.oybek.taxi4s.api.domain.TaxiInfoResp
import io.circe.generic.extras.auto._
import io.circe.generic.extras.semiauto._

package object domain {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit val currencyDecoder: Decoder[Currency] = Decoder.decodeString.emap {
    case "RUB" => Right(RUB)
    case "KZT" => Right(KZT)
    case "AMD" => Right(AMD)
    case "GEL" => Right(GEL)
    case "AZN" => Right(AZN)
    case "UAH" => Right(UAH)
    case "BYN" => Right(BYN)
    case "BYR" => Right(BYR)
    case "KGS" => Right(KGS)
    case "EUR" => Right(EUR)
    case "MDL" => Right(MDL)
    case "UZS" => Right(UZS)
    case x => Left(s"No such currency $x")
  }

  implicit val clazzDecoder: Decoder[Clazz] = Decoder.decodeString.emap {
    case "econom" => Right(Econom)
    case "business" => Right(Business)
    case "comfortplus" => Right(ComfortPlus)
    case "minivan" => Right(Minivan)
    case "vip" => Right(VIP)
    case x => Left(s"No such class $x")
  }

  implicit val taxiInfoRespDecoder: Decoder[TaxiInfoResp] = deriveConfiguredDecoder
}
