/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.test.readme

import akka.http.scaladsl.server.PathMatcher1
import com.github.cerst.vacade.akka.http._
import com.github.cerst.vacade.avro4s._
import com.github.cerst.vacade.jsoniter_scala._
import com.github.plokhotnyuk.jsoniter_scala.core.JsonCodec
import com.sksamuel.avro4s.{Decoder, Encoder, SchemaFor}
import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._

object types {

  @newtype
  final class ItemId(val asInt: Int)

  object ItemId {

    implicit val jsonCodecForItemId: JsonCodec[ItemId] = vcJsonCodec.int(apply)(_.asInt)

    // due to a change in Avro4s 4.0, it is unfortunately no longer possible to have a single type implementing Encoder & Decoder
    implicit val (
      encoderForItemId: Encoder[ItemId],
      decoderForItemId: Decoder[ItemId],
      schemaForItemId: SchemaFor[ItemId]
      ) = vcAvro4s(apply)(_.asInt)

    val pm: PathMatcher1[ItemId] = vcPathMatcher.int(apply)

    def apply(value: Int): ItemId = {
      value.coerce
    }

  }

}
