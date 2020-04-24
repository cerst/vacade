/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.test

import java.time.{Duration, LocalDateTime, OffsetDateTime, ZonedDateTime}
import java.util.UUID

import akka.http.scaladsl.server.PathMatcher1
import com.github.cerst.vacade.akka.http._
import com.github.cerst.vacade.avro4s._
import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import com.github.cerst.vacade.jsoniter_scala._
import com.github.plokhotnyuk.jsoniter_scala.core.JsonCodec

object newtypeTypes {

  // ================================================================================================================
  // BIG DECIMAL
  // ================================================================================================================
  @newtype
  final class BigDecimalValueClass(val value: BigDecimal)

  object BigDecimalValueClass {
    implicit val jsonCodecForBigDecimalValueClass: JsonCodec[BigDecimalValueClass] =
      vcJsonCodec.bigDecimal(apply)(_.value)

    def apply(value: BigDecimal): BigDecimalValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // BIG INT
  // ================================================================================================================
  @newtype
  final class BigIntValueClass(val value: BigInt)

  object BigIntValueClass {
    implicit val jsonCodecForBigIntValueClass: JsonCodec[BigIntValueClass] = vcJsonCodec.bigInt(apply)(_.value)

    def apply(value: BigInt): BigIntValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // BOOLEAN
  // ================================================================================================================
  @newtype
  final class BooleanValueClass(val value: Boolean)

  object BooleanValueClass {
    implicit val jsonCodecForBooleanValueClass: JsonCodec[BooleanValueClass] = vcJsonCodec.boolean(apply)(_.value)

    def apply(value: Boolean): BooleanValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // DOUBLE
  // ================================================================================================================
  @newtype
  final class DoubleValueClass(val value: Double)

  object DoubleValueClass {
    implicit val jsonCodecForDoubleValueClass: JsonCodec[DoubleValueClass] = vcJsonCodec.double(apply)(_.value)

    val pm: PathMatcher1[DoubleValueClass] = vcPathMatcher.double(apply)

    def apply(value: Double): DoubleValueClass = {
      require(value > 0)
      value.coerce
    }
  }

  // =====================================================================================================================
  // DURATION
  // =====================================================================================================================
  @newtype
  final class DurationValueClass private (val value: Duration)

  object DurationValueClass {
    implicit val jsonCodecForDurationValueClass: JsonCodec[DurationValueClass] =
      vcJsonCodec.duration[DurationValueClass](apply)(_.value)

    def apply(value: Duration): DurationValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // FLOAT
  // ================================================================================================================
  @newtype
  final class FloatValueClass(val value: Float)

  object FloatValueClass {
    implicit val jsonCodecForFloatValueClass: JsonCodec[FloatValueClass] = vcJsonCodec.float(apply)(_.value)

    def apply(value: Float): FloatValueClass = {
      require(value > 0)
      value.coerce
    }
  }

  // ================================================================================================================
  // INT
  // ================================================================================================================
  @newtype
  final class IntValueClass(val value: Int)

  object IntValueClass {
    implicit val schemaForBicoderForIntValueClass: SchemaForBicoder[IntValueClass] = vcSchemaForBicoder(apply)(_.value)

    implicit val jsonCodecForIntValueClass: JsonCodec[IntValueClass] = vcJsonCodec.int(apply)(_.value)

    val hexIntPm: PathMatcher1[IntValueClass] = vcPathMatcher.hexInt(apply)

    val intPm: PathMatcher1[IntValueClass] = vcPathMatcher.int(apply)

    def apply(value: Int): IntValueClass = {
      require(value > 0)
      value.coerce
    }
  }

  // ================================================================================================================
  // LOCAL DATE TIME
  // ================================================================================================================
  @newtype
  final class LocalDateTimeValueClass(val value: LocalDateTime)

  object LocalDateTimeValueClass {
    implicit val jsonCodecForLocalDateTimeValueClass: JsonCodec[LocalDateTimeValueClass] =
      vcJsonCodec.localDateTime(apply)(_.value)

    def apply(value: LocalDateTime): LocalDateTimeValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // LONG
  // ================================================================================================================
  @newtype
  final class LongValueClass(val value: Long)

  object LongValueClass {
    implicit val jsonCodecForLongValueClass: JsonCodec[LongValueClass] = vcJsonCodec.long(apply)(_.value)

    val hexLongPm: PathMatcher1[LongValueClass] = vcPathMatcher.hexLong(apply)

    val longPm: PathMatcher1[LongValueClass] = vcPathMatcher.long(apply)

    def apply(value: Long): LongValueClass = {
      require(value > 0)
      value.coerce
    }
  }

  // ================================================================================================================
  // OFFSET DATE TIME
  // ================================================================================================================
  @newtype
  final class OffsetDateTimeValueClass(val value: OffsetDateTime)

  object OffsetDateTimeValueClass {
    implicit val jsonCodecForOffsetDateTimeValueClass: JsonCodec[OffsetDateTimeValueClass] =
      vcJsonCodec.offsetDateTime(apply)(_.value)

    def apply(value: OffsetDateTime): OffsetDateTimeValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // SHORT
  // ================================================================================================================
  @newtype
  final class ShortValueClass(val value: Short)

  object ShortValueClass {
    implicit val jsonCodecForShortValueClass: JsonCodec[ShortValueClass] = vcJsonCodec.short(apply)(_.value)

    def apply(value: Short): ShortValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // STRING
  // ================================================================================================================
  @newtype
  final class StringValueClass(val value: String)

  object StringValueClass {
    implicit val jsonCodecForStringValueClass: JsonCodec[StringValueClass] = vcJsonCodec.string(apply)(_.value)

    val pm: PathMatcher1[StringValueClass] = vcPathMatcher.string(apply)

    def apply(value: String): StringValueClass = {
      require(value.nonEmpty)
      value.coerce
    }
  }

  // ================================================================================================================
  // UUID
  // ================================================================================================================
  @newtype
  final class UuidValueClass private (val value: UUID)

  object UuidValueClass {
    implicit val jsonCodecForUuidValueClass: JsonCodec[UuidValueClass] = vcJsonCodec.uuid(apply)(_.value)

    val pm: PathMatcher1[UuidValueClass] = vcPathMatcher.uuid(apply)

    def apply(value: UUID): UuidValueClass = {
      value.coerce
    }
  }

  // ================================================================================================================
  // ZONED DATE TIME
  // ================================================================================================================
  @newtype
  final class ZonedDateTimeValueClass(val value: ZonedDateTime)

  object ZonedDateTimeValueClass {
    implicit val jsonCodecForZonedDateTimeValueClass: JsonCodec[ZonedDateTimeValueClass] =
      vcJsonCodec.zonedDateTime(apply)(_.value)

    def apply(value: ZonedDateTime): ZonedDateTimeValueClass = {
      value.coerce
    }
  }

}
