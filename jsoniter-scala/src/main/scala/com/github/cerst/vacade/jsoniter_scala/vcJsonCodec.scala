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
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def bigDecimal[VC](
    construct: BigDecimal => VC
  )(destruct: VC => BigDecimal, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[BigDecimal, VC](
      "BigDecimal",
      construct,
      destruct,
      readU = _.readBigDecimal(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBigDecimal(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // BigInt
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  final def bigInt[VC](
    construct: BigInt => VC
  )(destruct: VC => BigInt, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[BigInt, VC](
      "BigInt",
      construct,
      destruct,
      readU = _.readBigInt(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBigInt(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // BOOLEAN
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def boolean[VC](
    construct: Boolean => VC
  )(destruct: VC => Boolean, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Boolean, VC](
      "Boolean",
      construct,
      destruct,
      readU = _.readBoolean(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBoolean(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // BYTE
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def byte[VC](construct: Byte => VC)(destruct: VC => Byte, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Byte, VC](
      "Byte",
      construct,
      destruct,
      readU = _.readByte(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsByte(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // DOUBLE
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def double[VC](construct: Double => VC)(destruct: VC => Double, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Double, VC](
      "Double",
      construct,
      destruct,
      readU = _.readDouble(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsDouble(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // DURATION
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def duration[VC](
    construct: Duration => VC
  )(destruct: VC => Duration, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Duration, VC](
      "Duration",
      construct,
      destruct,
      readU = _.readDuration(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsDuration(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // FLOAT
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def float[VC](construct: Float => VC)(destruct: VC => Float, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Float, VC](
      "Float",
      construct,
      destruct,
      readU = _.readFloat(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsFloat(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // INSTANT
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def instant[VC](
    construct: Instant => VC
  )(destruct: VC => Instant, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Instant, VC](
      "Instant",
      construct,
      destruct,
      readU = _.readInstant(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsInstant(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // INT
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def int[VC](construct: Int => VC)(destruct: VC => Int, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Int, VC](
      "Int",
      construct,
      destruct,
      readU = _.readInt(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsInt(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // LOCAL DATE TIME
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def localDateTime[VC](
    construct: LocalDateTime => VC
  )(destruct: VC => LocalDateTime, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[LocalDateTime, VC](
      "LocalDateTime",
      construct,
      destruct,
      readU = _.readLocalDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsLocalDateTime(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // LONG
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def long[VC](construct: Long => VC)(destruct: VC => Long, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Long, VC](
      "Long",
      construct,
      destruct,
      readU = _.readLong(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsLong(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // OFFSET DATE TIME
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def offsetDateTime[VC](
    construct: OffsetDateTime => VC
  )(destruct: VC => OffsetDateTime, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[OffsetDateTime, VC](
      "OffsetDateTime",
      construct,
      destruct,
      readU = _.readOffsetDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsOffsetDateTime(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // SHORT
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def short[VC](construct: Short => VC)(destruct: VC => Short, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Short, VC](
      "Short",
      construct,
      destruct,
      readU = _.readShort(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsShort(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // STRING
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def string[VC](construct: String => VC)(destruct: VC => String, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[String, VC](
      "String",
      construct,
      destruct,
      readU = _.readString(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsString(),
      writeKey = _.writeKey,
      nullVc
    )
  }

  // ===================================================================================================================
  // UUID
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def uuid[VC](construct: UUID => VC)(destruct: VC => UUID, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[UUID, VC](
      "UUID",
      construct,
      destruct,
      readU = _.readUUID(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsUUID(),
      writeKey = _.writeKey,
      nullVc
    )

  }

  // ===================================================================================================================
  // ZONED DATE TIME
  // ===================================================================================================================
  /**
    * @param nullVc Provide explicitly when using with AnyVal.<br/>
    *               Otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed.<br/>
    *               Workaround for this [[https://github.com/scala/bug/issues/8097 Scala compiler bug]].
    */
  def zonedDateTime[VC](
    construct: ZonedDateTime => VC
  )(destruct: VC => ZonedDateTime, nullVc: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[ZonedDateTime, VC](
      "ZonedDateTime",
      construct,
      destruct,
      readU = _.readZonedDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsZonedDateTime(),
      writeKey = _.writeKey,
      nullVc
    )
  }

}
