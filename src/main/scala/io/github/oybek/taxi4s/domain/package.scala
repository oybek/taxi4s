package io.github.oybek.taxi4s

import io.circe.{Decoder, HCursor}
import io.circe.generic.extras.Configuration
import io.github.oybek.taxi4s.api.domain.{TaxiInfoResp, ZoneInfoResp}
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
    case "business" => Right(Business)
    case "comfortplus" => Right(ComfortPlus)
    case "econom" => Right(Econom)
    case "minivan" => Right(Minivan)
    case "vip" => Right(VIP)
    case x => Left(s"No such class $x")
  }

  implicit val supportedRequirementsDecoder: Decoder[SupportedRequirements] = Decoder.decodeString.emap {
    case "animaltransport" =>    Right(AnimalTransport)
    case "bicycle" =>            Right(Bicycle)
    case "check" =>              Right(Check)
    case "childchair" =>         Right(ChildChair)
    case "conditioner" =>        Right(Conditioner)
    case "luggage" =>            Right(Luggage)
    case "meeting_arriving" =>   Right(MeetingArriving)
    case "nosmoking" =>          Right(NoSmoking)
    case "ski" =>                Right(Ski)
    case "universal" =>          Right(Universal)
    case "waiting_in_transit" => Right(WaitingInTransit)
    case "yellowcarnumber" =>    Right(YellowCarNumber)
    case x => Left(s"No such supported requirement $x")
  }

  implicit val taxiInfoRespDecoder: Decoder[TaxiInfoResp] = deriveConfiguredDecoder
  implicit val zoneInfoRespDecoder: Decoder[ZoneInfoResp] = deriveConfiguredDecoder
  implicit val tariffDecoder: Decoder[Tariff] = (c: HCursor) => for {
    name <- c.downField("name").as[Clazz]
    reqs <- c.downField("supported_requirements").as[List[SR]]
  } yield Tariff(name, reqs.map(_.name))

  private case class SR(name: SupportedRequirements)
}
