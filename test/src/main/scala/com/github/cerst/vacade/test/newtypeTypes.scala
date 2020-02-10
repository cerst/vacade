package com.github.cerst.vacade.test

import java.util.UUID

import akka.http.scaladsl.server.PathMatcher1

import io.estatico.newtype.macros.newtype
import com.github.cerst.vacade.akka.http._
import io.estatico.newtype.ops._

object newtypeTypes {

  // ================================================================================================================
  // BIG DECIMAL
  // ================================================================================================================
  @newtype
  final class BigDecimalValueClass(val value: BigDecimal)

  object BigDecimalValueClass {
    // there is no PathMatcher for BigDecimal
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
    // there is no PathMatcher for BigInt
  }

  // ================================================================================================================
  // BOOLEAN
  // ================================================================================================================
  @newtype
  final class BooleanValueClass(val value: Boolean)

  object BooleanValueClass {
    // there is no PatchMatcher for Boolean
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
    val pm: PathMatcher1[DoubleValueClass] = vcPathMatcher.double(apply)

    def apply(value: Double): DoubleValueClass = {
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
    val hexIntPm: PathMatcher1[IntValueClass] = vcPathMatcher.hexInt(apply)

    val intPm: PathMatcher1[IntValueClass] = vcPathMatcher.int(apply)

    def apply(value: Int): IntValueClass = {
      require(value > 0)
      value.coerce
    }
  }

  // ================================================================================================================
  // LONG
  // ================================================================================================================
  @newtype
  final class LongValueClass(val value: Long)

  object LongValueClass {
    val hexLongPm: PathMatcher1[LongValueClass] = vcPathMatcher.hexLong(apply)

    val longPm: PathMatcher1[LongValueClass] = vcPathMatcher.long(apply)

    def apply(value: Long): LongValueClass = {
      require(value > 0)
      value.coerce
    }
  }

  // ================================================================================================================
  // STRING
  // ================================================================================================================
  @newtype
  final class StringValueClass(val value: String)

  object StringValueClass {
    val pm: PathMatcher1[StringValueClass] = vcPathMatcher.string(apply)

    def apply(value: String): StringValueClass = {
      require(value.nonEmpty)
      value.coerce
    }
  }

  // ================================================================================================================
  // WRAPPED UUID
  // ================================================================================================================
  @newtype
  final class UuidValueClass(val value: UUID)

  object UuidValueClass {
    val pm: PathMatcher1[UuidValueClass] = vcPathMatcher.uuid(apply)

    def apply(value: UUID): UuidValueClass = {
      value.coerce
    }
  }

}
