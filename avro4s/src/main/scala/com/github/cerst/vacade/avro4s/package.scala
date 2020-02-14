package com.github.cerst.vacade

import com.sksamuel.avro4s.{Decoder, Encoder, FieldMapper, SchemaFor}
import org.apache.avro.Schema

/**
  * No derivations for solely `SchemaFor`, `Encoder` or `Decoder` because you can directly (co-) map them.
  */
package object avro4s {

  /**
    *
    * @param overrideSchemaU By default, the schema of the underlying type `U` is used. Use this parameter to override.
    */
  final def vcSchemaForBicoder[U: SchemaFor: Decoder: Encoder, VC](
    construct: U => VC
  )(destruct: VC => U, overrideSchemaU: Option[Schema] = None): SchemaForBicoder[VC] = {

    new SchemaForBicoder[VC] {
      override def schema(fieldMapper: FieldMapper): Schema = {
        overrideSchemaU.getOrElse(SchemaFor[U].schema(fieldMapper))
      }

      override def encode(vc: VC, schema: Schema, fieldMapper: FieldMapper): AnyRef = {
        val u = destruct(vc)
        Encoder[U].encode(u, schema, fieldMapper)
      }

      override def decode(value: Any, schema: Schema, fieldMapper: FieldMapper): VC = {
        val u = Decoder[U].decode(value, schema, fieldMapper)
        construct(u)
      }

    }

  }

}
