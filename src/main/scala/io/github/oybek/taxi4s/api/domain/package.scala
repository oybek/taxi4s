package io.github.oybek.taxi4s.api

import io.github.oybek.taxi4s.util.Url

package object domain {

  implicit val formUrlReqUrlEncoder = Url.derive[FormUrlReq] {
    case FormUrlReq(appCode, start, end, level, ref, appmetricaTrackingId) =>
      ???
  }
}
