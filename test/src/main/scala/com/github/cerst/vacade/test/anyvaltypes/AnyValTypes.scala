package com.github.cerst.vacade.test.anyvaltypes

import java.util.UUID

import akka.http.scaladsl.server.PathMatcher1
import com.github.cerst.vacade.akka.http._

// ================================================================================================================
// BIG DECIMAL
// ================================================================================================================
final case class BigDecimalValueClass(value: BigDecimal) extends AnyVal

object BigDecimalValueClass {
  // there is no PathMatcher for BigDecimal
}

// ================================================================================================================
// BIG INT
// ================================================================================================================
final case class BigIntValueClass(value: BigInt) extends AnyVal

object BigIntValueClass {
  // there is no PathMatcher for BigInt
}

// ================================================================================================================
// BOOLEAN
// ================================================================================================================
final case class BooleanValueClass(value: Boolean) extends AnyVal

object BooleanValueClass {
  // there is no PatchMatcher for Boolean
}

// ================================================================================================================
// DOUBLE
// ================================================================================================================
final case class DoubleValueClass(value: Double) extends AnyVal

object DoubleValueClass {
  val pm: PathMatcher1[DoubleValueClass] = vcPathMatcher.double(apply)

  def apply(value: Double): DoubleValueClass = {
    require(value > 0)
    new DoubleValueClass(value)
  }
}

// ================================================================================================================
// INT
// ================================================================================================================
final case class IntValueClass(value: Int) extends AnyVal

object IntValueClass {
  val hexIntPm: PathMatcher1[IntValueClass] = vcPathMatcher.hexInt(apply)

  val intPm: PathMatcher1[IntValueClass] = vcPathMatcher.int(apply)

  def apply(value: Int): IntValueClass = {
    require(value > 0)
    new IntValueClass(value)
  }
}

// ================================================================================================================
// LONG
// ================================================================================================================
final case class LongValueClass(value: Long) extends AnyVal

object LongValueClass {
  val hexLongPm: PathMatcher1[LongValueClass] = vcPathMatcher.hexLong(apply)

  val longPm: PathMatcher1[LongValueClass] = vcPathMatcher.long(apply)

  def apply(value: Long): LongValueClass = {
    require(value > 0)
    new LongValueClass(value)
  }
}

// ================================================================================================================
// STRING
// ================================================================================================================
final case class StringValueClass(value: String) extends AnyVal

object StringValueClass {
  val pm: PathMatcher1[StringValueClass] = vcPathMatcher.string(apply)

  def apply(value: String): StringValueClass = {
    require(value.nonEmpty)
    new StringValueClass(value)
  }
}

// ================================================================================================================
// WRAPPED UUID
// ================================================================================================================
final case class UuidValueClass(value: UUID) extends AnyVal

object UuidValueClass {
  val pm: PathMatcher1[UuidValueClass] = vcPathMatcher.uuid(apply)
}
