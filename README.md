# Value Class Derivations

Value-class-derivations (vacade) is to reduce boilerplate needed to work with value classes and 
different libraries.

## Usage

Pick one or more target library:
```scala
libraryDependencies ++= Seq(
    "com.github.cerst" %% "vacade-akka-http" % vacadeVersion,
    "com.github.cerst" %% "vacade-avro4s" % vacadeVersion,
    "com.github.cerst" %% "vacade-jsoniter-scala" % vacadeVersion
)
```

Pick imports and derivations as needed:
```scala
import com.github.cerst.vacade.akka.http._
import com.githuc.cerst.vacade.avro4s._
import com.githuc.cerst.vacade.jsoniter_scala._
// dependent imports
import akka.http.scaladsl.server.PathMatcher1
import com.github.plokhotnyuk.jsoniter_scala.core.JsonCodec
import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._

object types {

  @newtype
  final class ItemId(val asInt: Int)

  object ItemId {

    implicit val jsonCodecForItemId: JsonCodec[ItemId] = vcJsonCodec.int(apply)(_.asInt)

    implicit val schemaForBicoderForItemId: SchemaForBicoder[ItemId] = vcSchemaForBicoder(apply)(_.asInt)

    val pm: PathMatcher1[ItemId] = vcPathMatcher.int(apply)

    def apply(value: Int): ItemId = {
      value.coerce
    }

  }

}
```


## Design Principles

* Only add features & convenience which is missing from existing libraries
  * i.e. don't provide methods for every single library/ feature out there 
* Don't break with the design on a particular API
  * i.e. don't retrofit implicits if the underlying library does not have them
* All libraries are configured as _Provided_
* All derivations methods are prefixed with `vc` followed by the name of the target type (e.g. `vcJsonCodec`)
  * Whenever explicit specification of underlying types is required, the aforementioned methods become objects
    having said types as method names (e.g. `vcJsonCodec.int`.  
    These objects also document why they are necessary.

## Limitations
Implementations are tested against `AnyVal` and `newtype`.  

Due to a [compiler bug](https://github.com/scala/bug/issues/8097), `jsoniter_scala` derivations don't
work with `AnyVal` (the code compiles but throws a `NullPointerException` at runtime). 
