[![GitHub license](https://img.shields.io/github/license/cerst/vacade)](https://github.com/cerst/vacade/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.cerst/vacade-jsoniter-scala_2.13)](https://search.maven.org/search?q=g:com.github.cerst%20AND%20a:vacade-*)



# Value Class Derivations

Value-class-derivations (vacade) is to reduce boilerplate needed to work with value classes and 
different libraries.  
It supports Scala 2.12 & 2.13.

## Usage
Due to breaking changes and compatibility problems, `vacade` - for the time being - has two release paths (starting with 0.3.0):

    Versions following regular semver conventions keeping all dependencies as updated as possible
    Versions ending in -1 which pin akka-http at version 10.1.x (due to ecosystem compatibility issues)

Pick one or more target library (depending on release path):
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
import com.sksamuel.avro4s.{Decoder, Encoder, SchemaFor}
import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._

object types {

  @newtype
  final class ItemId(val asInt: Int)

  object ItemId {

    // null.asInstanceOf is only required for AnyVal value classes due to https://github.com/scala/bug/issues/8097
    // otherwise, you'll get a runtime NullPointerException when the resulting codec is first accessed
    // leave it out for Newtype
    implicit val jsonCodecForItemId: JsonCodec[ItemId] = vcJsonCodec.int(apply)(_.asInt, null.asInstanceOf[ItemId])
    
    // due to a change in Avro4s 4.0, it is unfortunately no longer possible to have a single type implementing Encoder & Decoder
    implicit val (
      encoderForItemId: Encoder[ItemId],
      decoderForItemId: Decoder[ItemId],
      schemaForItemId: SchemaFor[ItemId]
    ) = vcAvro4s(apply)(_.value)

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
    having said types as method names (e.g. `vcJsonCodec.int`).  
    These objects also document why they are necessary.
* Implementations should work as consistently as possible for `AnyVal` and `newtype`.
