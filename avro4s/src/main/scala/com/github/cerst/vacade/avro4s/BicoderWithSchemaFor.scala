package com.github.cerst.vacade.avro4s

import com.sksamuel.avro4s.{Decoder, Encoder, SchemaFor}

/**
 * Common subtype for Decoder, `Encoder` & SchemaFor
 * @tparam R
 */
trait BicoderWithSchemaFor[R] extends Decoder[R] with Encoder[R] with SchemaFor[R]
