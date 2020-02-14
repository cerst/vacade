/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.avro4s

import com.sksamuel.avro4s.{Decoder, Encoder, SchemaFor}

/**
  * Common subtype for `SchemaFor`, `Decoder` & `Encoder`
  */
trait SchemaForBicoder[A] extends SchemaFor[A] with Encoder[A] with Decoder[A]
