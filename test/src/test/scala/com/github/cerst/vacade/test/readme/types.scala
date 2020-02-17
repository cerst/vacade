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
import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._

object types {

  @newtype
  final class ItemId(val asInt: Int)

  object ItemId {

    implicit val jsonCodecForItemId: JsonCodec[ItemId] = vcJsonCodec.int(apply)(_.asInt)

    implicit val schemaForBicoderForItemId: SchemaForBicoder[ItemId] = vcSchemaForBicoder(apply)(_.asInt)

    val pm: PathMatcher1[ItemId] = vcPathMatcher.int(apply)

    def apply(value: Int): ItemId = {
      value.coerce
    }

  }

}
