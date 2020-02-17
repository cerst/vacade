/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.jsoniter_scala

import java.time._
import java.util.UUID

import com.github.cerst.vacade.jsoniter_scala.internal.newJsonCodec
import com.github.plokhotnyuk.jsoniter_scala.core.JsonCodec

/**
  * Explicit specification of the underlying type is required because jsoniter-scala does not allow to summon JsonCodec's.
  */
object vcJsonCodec {

  // ===================================================================================================================
  // BigDecimal
  // ===================================================================================================================
  def bigDecimal[VC](construct: BigDecimal => VC)(destruct: VC => BigDecimal): JsonCodec[VC] = {
    newJsonCodec[BigDecimal, VC](
      "BigDecimal",
      construct,
      destruct,
      readU = _.readBigDecimal(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBigDecimal(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // BigInt
  // ===================================================================================================================
  final def bigInt[VC](construct: BigInt => VC)(destruct: VC => BigInt): JsonCodec[VC] = {
    newJsonCodec[BigInt, VC](
      "BigInt",
      construct,
      destruct,
      readU = _.readBigInt(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBigInt(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // BOOLEAN
  // ===================================================================================================================
  def boolean[VC](construct: Boolean => VC)(destruct: VC => Boolean): JsonCodec[VC] = {
    newJsonCodec[Boolean, VC](
      "Boolean",
      construct,
      destruct,
      readU = _.readBoolean,
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBoolean(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // BYTE
  // ===================================================================================================================
  def byte[VC](construct: Byte => VC)(destruct: VC => Byte): JsonCodec[VC] = {
    newJsonCodec[Byte, VC](
      "Byte",
      construct,
      destruct,
      readU = _.readByte(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsByte(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // DOUBLE
  // ===================================================================================================================
  def double[VC](construct: Double => VC)(destruct: VC => Double): JsonCodec[VC] = {
    newJsonCodec[Double, VC](
      "Double",
      construct,
      destruct,
      readU = _.readDouble(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsDouble(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // DURATION
  // ===================================================================================================================
  final case class _duration[VC](construct: Duration => VC) {
    def apply(destruct: VC => Duration): JsonCodec[VC] = {
      newJsonCodec[Duration, VC](
        "Duration",
        construct,
        destruct,
        readU = _.readDuration(default = null),
        writeVal = _.writeVal,
        readKeyAsU = _.readKeyAsDuration(),
        writeKey = _.writeKey
      )
    }
  }

  def duration[VC](construct: Duration => VC, destruct: VC => Duration)(): JsonCodec[VC] = {
    newJsonCodec[Duration, VC](
      "Duration",
      construct,
      destruct,
      readU = _.readDuration(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsDuration(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // FLOAT
  // ===================================================================================================================
  def float[VC](construct: Float => VC)(destruct: VC => Float): JsonCodec[VC] = {
    newJsonCodec[Float, VC](
      "Float",
      construct,
      destruct,
      readU = _.readFloat(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsFloat(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // INSTANT
  // ===================================================================================================================
  def instant[VC](construct: Instant => VC)(destruct: VC => Instant): JsonCodec[VC] = {
    newJsonCodec[Instant, VC](
      "Instant",
      construct,
      destruct,
      readU = _.readInstant(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsInstant(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // INT
  // ===================================================================================================================
  def int[VC](construct: Int => VC)(destruct: VC => Int): JsonCodec[VC] = {
    newJsonCodec[Int, VC](
      "Int",
      construct,
      destruct,
      readU = _.readInt(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsInt(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // LOCAL DATE TIME
  // ===================================================================================================================
  def localDateTime[VC](construct: LocalDateTime => VC)(destruct: VC => LocalDateTime): JsonCodec[VC] = {
    newJsonCodec[LocalDateTime, VC](
      "LocalDateTime",
      construct,
      destruct,
      readU = _.readLocalDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsLocalDateTime(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // LONG
  // ===================================================================================================================
  def long[VC](construct: Long => VC)(destruct: VC => Long): JsonCodec[VC] = {
    newJsonCodec[Long, VC](
      "Long",
      construct,
      destruct,
      readU = _.readLong(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsLong(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // OFFSET DATE TIME
  // ===================================================================================================================
  def offsetDateTime[VC](construct: OffsetDateTime => VC)(destruct: VC => OffsetDateTime): JsonCodec[VC] = {
    newJsonCodec[OffsetDateTime, VC](
      "OffsetDateTime",
      construct,
      destruct,
      readU = _.readOffsetDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsOffsetDateTime(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // SHORT
  // ===================================================================================================================
  def short[VC](construct: Short => VC)(destruct: VC => Short): JsonCodec[VC] = {
    newJsonCodec[Short, VC](
      "Short",
      construct,
      destruct,
      readU = _.readShort(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsShort(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // STRING
  // ===================================================================================================================
  def string[VC](construct: String => VC)(destruct: VC => String): JsonCodec[VC] = {
    newJsonCodec[String, VC](
      "String",
      construct,
      destruct,
      readU = _.readString(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsString(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // UUID
  // ===================================================================================================================
  def uuid[VC](construct: UUID => VC)(destruct: VC => UUID): JsonCodec[VC] = {
    newJsonCodec[UUID, VC](
      "UUID",
      construct,
      destruct,
      readU = _.readUUID(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsUUID(),
      writeKey = _.writeKey
    )

  }

  // ===================================================================================================================
  // ZONED DATE TIME
  // ===================================================================================================================
  def zonedDateTime[VC](construct: ZonedDateTime => VC)(destruct: VC => ZonedDateTime): JsonCodec[VC] = {
    newJsonCodec[ZonedDateTime, VC](
      "ZonedDateTime",
      construct,
      destruct,
      readU = _.readZonedDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsZonedDateTime(),
      writeKey = _.writeKey
    )
  }

}
