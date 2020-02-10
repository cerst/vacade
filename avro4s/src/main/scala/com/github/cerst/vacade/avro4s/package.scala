package com.github.cerst.vacade

import com.sksamuel.avro4s.{Decoder, Encoder, FieldMapper, SchemaFor}
import org.apache.avro.Schema

/**
  * No derivations for solely `Decoder`, `Encoder` or `SchemaFor` because you can directly (co-) map them.
  */
package object avro4s {

  /**
    *
    * @param overrideSchemaU By default, the schema of the underlying type `U` is used. Use this parameter to override.
    */
  final def vcBicoderWithSchemaFor[U: Decoder: Encoder: SchemaFor, VC](
    construct: U => VC
  )(destruct: VC => U, overrideSchemaU: Option[Schema] = None): BicoderWithSchemaFor[VC] = {

    new BicoderWithSchemaFor[VC] {
      override def decode(value: Any, schema: Schema, fieldMapper: FieldMapper): VC = {
        val u = Decoder[U].decode(value, schema, fieldMapper)
        construct(u)
      }

      override def encode(vc: VC, schema: Schema, fieldMapper: FieldMapper): AnyRef = {
        val u = destruct(vc)
        Encoder[U].encode(u, schema, fieldMapper)
      }

      override def schema(fieldMapper: FieldMapper): Schema = {
        overrideSchemaU.getOrElse(SchemaFor[U].schema(fieldMapper))
      }

    }
  }

}
