package com.github.cerst.vacade.jsoniter_scala.internal

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonCodec, JsonReader, JsonWriter}

import scala.util.control.NonFatal

// Jsoniter-Scala does not provide an implicit structure to derive json codecs - so, the derivations have to defined explicitly

object newJsonCodec {

  final def apply[U, VC](uName: String,
                         construct: U => VC,
                         destruct: VC => U,
                         vcNullValue: VC,
                         readU: JsonReader => U,
                         writeVal: JsonWriter => U => Unit,
                         readKeyAsU: JsonReader => U,
                         writeKey: JsonWriter => U => Unit): JsonCodec[VC] = {

    new JsonCodec[VC] {
      override def decodeValue(in: JsonReader, default: VC): VC = {
        if (in isNextToken 'n') {
          in.readNullOrError(default, s"expected $uName value or null")
        } else {
          in.rollbackToken()
          val u = readU(in)
          try {
            construct(u)
          } catch {
            case NonFatal(cause) =>
              in.decodeError(cause.getMessage)
          }
        }
      }

      override def encodeValue(vc: VC, out: JsonWriter): Unit = {
        if (vc != null) {
          val u = destruct(vc)
          writeVal(out)(u)
        } else {
          out.writeNull()
        }
      }

      override def nullValue: VC = vcNullValue

      override def decodeKey(in: JsonReader): VC = {
        val u = readKeyAsU(in)
        try {
          construct(u)
        } catch {
          case NonFatal(cause) =>
            in.decodeError(cause.getMessage)
        }
      }

      override def encodeKey(vc: VC, out: JsonWriter): Unit = {
        val u = destruct(vc)
        writeKey(out)(u)
      }
    }
  }

}
