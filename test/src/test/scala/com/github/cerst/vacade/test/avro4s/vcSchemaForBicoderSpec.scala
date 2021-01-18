/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.test.avro4s

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import com.sksamuel.avro4s._
import org.apache.avro.Schema
import org.scalatest.Assertions
import org.scalatest.freespec.AnyFreeSpec

final class vcSchemaForBicoderSpec extends AnyFreeSpec with Assertions {

  import vcSchemaForBicoderSpec._

  "AnyVal" - {
    import com.github.cerst.vacade.test.anyvaltypes._

    "schema is preserved" in {
      assert(SchemaFor[IntValueClass].schema == SchemaFor[Int].schema)
    }

    "(de-) serialization matches the one of the underlying type" in {
      val plainRecord = plain.Record(1)
      val plainBytes = serialize(plainRecord)

      val anyvalRecord = anyval.Record(IntValueClass(1))
      val anyvalBytes = serialize(anyvalRecord)

      assert(plainBytes sameElements anyvalBytes)

      val parsedAnyvalRecord = deserialize[anyval.Record](anyvalBytes, anyval.Record.schema)

      assert(parsedAnyvalRecord == anyvalRecord)
    }
  }

  "newtype" - {
    import com.github.cerst.vacade.test.newtypeTypes._

    "schema is preserved" in {
      assert(SchemaFor[IntValueClass].schema == SchemaFor[Int].schema)
    }

    "(de-) serialization matches the one of the underlying type" in {
      val plainRecord = plain.Record(1)
      val plainBytes = serialize(plainRecord)

      val newtypeRecord = newtype.Record(IntValueClass(1))
      val newtypeBytes = serialize(newtypeRecord)

      assert(plainBytes sameElements newtypeBytes)

      val parsedNewtypeRecord = deserialize[newtype.Record](newtypeBytes, anyval.Record.schema)

      assert(parsedNewtypeRecord == newtypeRecord)
    }
  }

}

private object vcSchemaForBicoderSpec {

  object plain {
    final case class Record(int: Int)

    object Record {
      val schema: Schema = AvroSchema[Record]
    }
  }

  object anyval {
    import com.github.cerst.vacade.test.anyvaltypes._

    final case class Record(intValueClass: IntValueClass)

    object Record {
      val schema: Schema = AvroSchema[Record]
    }
  }

  object newtype {
    import com.github.cerst.vacade.test.newtypeTypes._

    final case class Record(intValueClass: IntValueClass)

    object Record {
      val schema: Schema = AvroSchema[Record]
    }
  }

  def serialize[A: Encoder](a: A): Array[Byte] = {
    // no .close needed
    val byteArrayOutputStream = new ByteArrayOutputStream()
    val avroOutputStream = AvroOutputStream.binary[A].to(byteArrayOutputStream).build()
    try {
      avroOutputStream.write(a)
      avroOutputStream.flush()
      avroOutputStream.close()
      byteArrayOutputStream.toByteArray
    } finally {
      avroOutputStream.close()
    }
  }

  def deserialize[A: Decoder](bytes: Array[Byte], schema: Schema): A = {
    // no .close needed
    val byteArrayInputStream = new ByteArrayInputStream(bytes)
    val avroInputStream = AvroInputStream.binary[A].from(byteArrayInputStream).build(schema)
    try {
      avroInputStream.iterator.next()
    } finally {
      avroInputStream.close()
    }
  }

}
