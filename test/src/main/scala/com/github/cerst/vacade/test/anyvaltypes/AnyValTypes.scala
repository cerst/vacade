/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.test.anyvaltypes

import java.time.{Duration, LocalDateTime, OffsetDateTime, ZonedDateTime}
import java.util.UUID

import akka.http.scaladsl.server.PathMatcher1
import com.github.cerst.vacade.akka.http._
import com.github.cerst.vacade.avro4s._

// ================================================================================================================
// BIG DECIMAL
// ================================================================================================================
final case class BigDecimalValueClass private (value: BigDecimal) extends AnyVal

object BigDecimalValueClass {
}

// ================================================================================================================
// BIG INT
// ================================================================================================================
final case class BigIntValueClass private (value: BigInt) extends AnyVal

object BigIntValueClass {
}

// ================================================================================================================
// BOOLEAN
// ================================================================================================================
final case class BooleanValueClass private (value: Boolean) extends AnyVal

object BooleanValueClass {
}

// ================================================================================================================
// DOUBLE
// ================================================================================================================
final case class DoubleValueClass private (value: Double) extends AnyVal

object DoubleValueClass {
  val pm: PathMatcher1[DoubleValueClass] = vcPathMatcher.double(apply)

  def apply(value: Double): DoubleValueClass = {
    require(value > 0)
    new DoubleValueClass(value)
  }
}

// =====================================================================================================================
// DURATION
// =====================================================================================================================
final case class DurationValueClass private (val value: Duration) extends AnyVal

object DurationValueClass {
  def apply(value: Duration): DurationValueClass = {
    new DurationValueClass(value)
  }
}

// ================================================================================================================
// FLOAT
// ================================================================================================================
final case class FloatValueClass private (value: Float) extends AnyVal

object FloatValueClass {
  def apply(value: Float): FloatValueClass = {
    require(value > 0)
    new FloatValueClass(value)
  }
}

// ================================================================================================================
// INT
// ================================================================================================================
final case class IntValueClass private (value: Int) extends AnyVal

object IntValueClass {
  implicit val schemaForBicoderForIntValueClass: SchemaForBicoder[IntValueClass] = vcSchemaForBicoder(apply)(_.value)

  val hexIntPm: PathMatcher1[IntValueClass] = vcPathMatcher.hexInt(apply)

  val intPm: PathMatcher1[IntValueClass] = vcPathMatcher.int(apply)

  def apply(value: Int): IntValueClass = {
    require(value > 0)
    new IntValueClass(value)
  }
}

// ================================================================================================================
// LOCAL DATE TIME
// ================================================================================================================
final case class LocalDateTimeValueClass private (value: LocalDateTime) extends AnyVal

object LocalDateTimeValueClass {
  def apply(value: LocalDateTime): LocalDateTimeValueClass = {
    new LocalDateTimeValueClass(value)
  }
}

// ================================================================================================================
// LONG
// ================================================================================================================
final case class LongValueClass private (value: Long) extends AnyVal

object LongValueClass {
  val hexLongPm: PathMatcher1[LongValueClass] = vcPathMatcher.hexLong(apply)

  val longPm: PathMatcher1[LongValueClass] = vcPathMatcher.long(apply)

  def apply(value: Long): LongValueClass = {
    require(value > 0)
    new LongValueClass(value)
  }
}

// ================================================================================================================
// OFFSET DATE TIME
// ================================================================================================================
final case class OffsetDateTimeValueClass(value: OffsetDateTime) extends AnyVal

object OffsetDateTimeValueClass {
  def apply(value: OffsetDateTime): OffsetDateTimeValueClass = {
    new OffsetDateTimeValueClass(value)
  }
}

// ================================================================================================================
// SHORT
// ================================================================================================================
final case class ShortValueClass(value: Short) extends AnyVal

object ShortValueClass {
  def apply(value: Short): ShortValueClass = {
    new ShortValueClass(value)
  }
}

// ================================================================================================================
// STRING
// ================================================================================================================
final case class StringValueClass private (value: String) extends AnyVal

object StringValueClass {
  val pm: PathMatcher1[StringValueClass] = vcPathMatcher.string(apply)

  def apply(value: String): StringValueClass = {
    require(value.nonEmpty)
    new StringValueClass(value)
  }
}

// ================================================================================================================
// UUID
// ================================================================================================================
final case class UuidValueClass private (value: UUID) extends AnyVal

object UuidValueClass {
  val pm: PathMatcher1[UuidValueClass] = vcPathMatcher.uuid(apply)
}

// ================================================================================================================
// ZONED DATE TIME
// ================================================================================================================
final case class ZonedDateTimeValueClass(value: ZonedDateTime)

object ZonedDateTimeValueClass {
  def apply(value: ZonedDateTime): ZonedDateTimeValueClass = {
    new ZonedDateTimeValueClass(value)
  }
}
