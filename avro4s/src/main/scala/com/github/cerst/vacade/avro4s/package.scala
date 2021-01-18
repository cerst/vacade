/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade

import com.sksamuel.avro4s.{Decoder, Encoder, SchemaFor}

/**
  * No derivations for solely `SchemaFor`, `Encoder` or `Decoder` because you can directly (co-) map them.
  * <p>
  * Due to a change in Avro4s 4.0, it is no longer possible to provide a single type implementing
  * `Encoder` and `Decoder` (due to a conflicting parent trait).
  */
package object avro4s {

  final def vcAvro4s[U: SchemaFor: Decoder: Encoder, VC](
    construct: U => VC
  )(destruct: VC => U, overrideSchemaFor: Option[SchemaFor[VC]] = None): (Encoder[VC], Decoder[VC], SchemaFor[VC]) = {

    val schemaForVC = overrideSchemaFor.getOrElse(SchemaFor[U].forType[VC])

    val encoder = new Encoder[VC] {
      override def encode(vc: VC): AnyRef = {
        val u = destruct(vc)
        Encoder[U].encode(u)
      }

      override val schemaFor: SchemaFor[VC] = schemaForVC
    }

    val decoder = new Decoder[VC] {
      override def decode(value: Any): VC = {
        val u = Decoder[U].decode(value)
        construct(u)
      }

      override val schemaFor: SchemaFor[VC] = schemaForVC
    }

    (encoder, decoder, schemaForVC)
  }

}
