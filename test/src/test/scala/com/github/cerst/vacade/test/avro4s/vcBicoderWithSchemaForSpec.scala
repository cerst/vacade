package com.github.cerst.vacade.test.avro4s

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import com.github.cerst.vacade.test.newtypeTypes.IntValueClass
import com.sksamuel.avro4s.{AvroInputStream, AvroOutputStream, AvroSchema, DefaultFieldMapper, SchemaFor}
import org.apache.avro.Schema
import org.scalatest.Assertions
import org.scalatest.freespec.AnyFreeSpec

final class vcBicoderWithSchemaForSpec extends AnyFreeSpec with Assertions {

  import vcBicoderWithSchemaForSpec._

  "schema is preserved" in {
    assert(SchemaFor[IntValueClass].schema(DefaultFieldMapper) == SchemaFor[Int].schema(DefaultFieldMapper))
  }

  "(de-) serialization matches the one of the underlying type" in {
    val plainRecord = PlainRecord(1)
    val plainOutputStream = new ByteArrayOutputStream()
    val plainAvroOutputStream = AvroOutputStream.binary[PlainRecord].to(plainOutputStream).build(PlainRecord.schema)
    plainAvroOutputStream.write(plainRecord)
    plainAvroOutputStream.flush()
    plainAvroOutputStream.close()
    val plainBytes = plainOutputStream.toByteArray

    val valueClassRecord = ValueClassRecord(IntValueClass(1))
    val valueClassOutputStream = new ByteArrayOutputStream()
    val valueClassAvroOutputStream =
      AvroOutputStream.binary[ValueClassRecord].to(valueClassOutputStream).build(ValueClassRecord.schema)
    valueClassAvroOutputStream.write(valueClassRecord)
    valueClassAvroOutputStream.flush()
    valueClassAvroOutputStream.close()
    val valueClassBytes = valueClassOutputStream.toByteArray

    assert(plainBytes sameElements valueClassBytes)

    val inputStream = new ByteArrayInputStream(plainBytes)
    val avroInputStream = AvroInputStream.binary[ValueClassRecord].from(inputStream).build(ValueClassRecord.schema)
    val parseValueClassRecord = avroInputStream.iterator.next()
    avroInputStream.close()

    assert(parseValueClassRecord == valueClassRecord)
  }

}

object vcBicoderWithSchemaForSpec {

  final case class PlainRecord(int: Int)

  object PlainRecord {
    val schema: Schema = AvroSchema[PlainRecord]
  }

  final case class ValueClassRecord(intValueClass: IntValueClass)

  object ValueClassRecord {
    val schema: Schema = AvroSchema[ValueClassRecord]
  }

}
