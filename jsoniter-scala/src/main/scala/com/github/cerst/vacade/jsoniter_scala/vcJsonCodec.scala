package com.github.cerst.vacade.jsoniter_scala

import java.time._
import java.util.UUID

import com.github.cerst.vacade.jsoniter_scala.internal.newJsonCodec
import com.github.plokhotnyuk.jsoniter_scala.core.JsonCodec

object vcJsonCodec {

  // ===================================================================================================================
  // BigDecimal
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def bigDecimal[VC](construct: BigDecimal => VC,
                     destruct: VC => BigDecimal,
                     rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[BigDecimal, VC](
      "BigDecimal",
      construct,
      destruct,
      rNullValue,
      readU = _.readBigDecimal(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBigDecimal(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // BigInt
  // ===================================================================================================================
  /**
    * @param vcNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                    type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                    <br/>
    *                    This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                    <br/>
    *                    Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  final def bigInt[VC](construct: BigInt => VC,
                       destruct: VC => BigInt,
                       vcNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[BigInt, VC](
      "BigInt",
      construct,
      destruct,
      vcNullValue,
      readU = _.readBigInt(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBigInt(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // BOOLEAN
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def boolean[VC](construct: Boolean => VC,
                  destruct: VC => Boolean,
                  rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Boolean, VC](
      "Boolean",
      construct,
      destruct,
      rNullValue,
      readU = _.readBoolean,
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsBoolean(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // BYTE
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def byte[VC](construct: Byte => VC, destruct: VC => Byte, rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Byte, VC](
      "Byte",
      construct,
      destruct,
      rNullValue,
      readU = _.readByte(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsByte(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // DOUBLE
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def double[VC](construct: Double => VC,
                 destruct: VC => Double,
                 rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Double, VC](
      "Double",
      construct,
      destruct,
      rNullValue,
      readU = _.readDouble(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsDouble(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // DURATION
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def duration[VC](construct: Duration => VC,
                   destruct: VC => Duration,
                   rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Duration, VC](
      "Duration",
      construct,
      destruct,
      rNullValue,
      readU = _.readDuration(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsDuration(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // FLOAT
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def float[VC](construct: Float => VC,
                destruct: VC => Float,
                rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Float, VC](
      "Float",
      construct,
      destruct,
      rNullValue,
      readU = _.readFloat(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsFloat(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // INSTANT
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def instant[VC](construct: Instant => VC,
                  destruct: VC => Instant,
                  rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Instant, VC](
      "Instant",
      construct,
      destruct,
      rNullValue,
      readU = _.readInstant(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsInstant(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // INT
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def int[VC](construct: Int => VC, destruct: VC => Int, rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Int, VC](
      "Int",
      construct,
      destruct,
      rNullValue,
      readU = _.readInt(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsInt(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // LOCAL DATE TIME
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def localDateTime[VC](construct: LocalDateTime => VC,
                        destruct: VC => LocalDateTime,
                        rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[LocalDateTime, VC](
      "LocalDateTime",
      construct,
      destruct,
      rNullValue,
      readU = _.readLocalDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsLocalDateTime(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // LONG
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def long[VC](construct: Long => VC, destruct: VC => Long, rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Long, VC](
      "Long",
      construct,
      destruct,
      rNullValue,
      readU = _.readLong(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsLong(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // OFFSET DATE TIME
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def offsetDateTime[VC](construct: OffsetDateTime => VC,
                         destruct: VC => OffsetDateTime,
                         rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[OffsetDateTime, VC](
      "OffsetDateTime",
      construct,
      destruct,
      rNullValue,
      readU = _.readOffsetDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsOffsetDateTime(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // SHORT
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def short[VC](construct: Short => VC,
                destruct: VC => Short,
                rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[Short, VC](
      "Short",
      construct,
      destruct,
      rNullValue,
      readU = _.readShort(),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsShort(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // STRING
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def string[VC](construct: String => VC,
                 destruct: VC => String,
                 rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[String, VC](
      "String",
      construct,
      destruct,
      rNullValue,
      readU = _.readString(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsString(),
      writeKey = _.writeKey
    )
  }

  // ===================================================================================================================
  // UUID
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def uuid[VC](construct: UUID => VC, destruct: VC => UUID, rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[UUID, VC](
      "UUID",
      construct,
      destruct,
      rNullValue,
      readU = _.readUUID(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsUUID(),
      writeKey = _.writeKey
    )

  }

  // ===================================================================================================================
  // ZONED DATE TIME
  // ===================================================================================================================
  /**
    * @param rNullValue <br/>Must be set <b>explicitly</b> to <i>null.asInstanceOf[VC]</i> with <i>VC</i> being the actual
    *                   type (e.g. <i>UserId</i>) if and only if <i>VC</i> is a subtype of <i>AnyVal</i>.
    *                   <br/>
    *                   This required to work around [[https://github.com/scala/bug/issues/8097 this compiler bug]].
    *                   <br/>
    *                   Otherwise, the created codec throws a <i>NullPointerException</i> at runtime.
    */
  def zonedDateTime[VC](construct: ZonedDateTime => VC,
                        destruct: VC => ZonedDateTime,
                        rNullValue: VC = null.asInstanceOf[VC]): JsonCodec[VC] = {
    newJsonCodec[ZonedDateTime, VC](
      "ZonedDateTime",
      construct,
      destruct,
      rNullValue,
      readU = _.readZonedDateTime(default = null),
      writeVal = _.writeVal,
      readKeyAsU = _.readKeyAsZonedDateTime(),
      writeKey = _.writeKey
    )
  }

}
