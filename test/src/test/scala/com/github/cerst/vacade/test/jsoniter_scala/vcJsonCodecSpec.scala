/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.test.jsoniter_scala

import java.nio.charset.StandardCharsets.UTF_8
import java.time.{Duration, LocalDateTime, OffsetDateTime, ZonedDateTime}
import java.util.UUID

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.Assertions
import org.scalatest.freespec.AnyFreeSpec

final class vcJsonCodecSpec extends AnyFreeSpec with Assertions with TypeCheckedTripleEquals {

  import vcJsonCodecSpec._

  "AnyVal" - {
    "to Json bytes" in {
      val actual = writeToArray(anyval.Record.instance)
      assert(actual === anyval.Record.jsonBytes)
    }
    "from Json bytes" in {
      val actual = readFromArray[anyval.Record](anyval.Record.jsonBytes)
      assert(actual == anyval.Record.instance)
    }
  }

  "Newtype" - {

    "to Json bytes" in {
      val actual = writeToArray(newtype.Record.instance)
      assert(actual === newtype.Record.jsonBytes)
    }

    "from Json bytes" in {
      val actual = readFromArray[newtype.Record](newtype.Record.jsonBytes)
      assert(actual == newtype.Record.instance)
    }

  }
}

private object vcJsonCodecSpec {

  object anyval {

    import com.github.cerst.vacade.test.anyvaltypes._

    final case class Record(bigDecimalValueClass: BigDecimalValueClass,
                            bigIntValueClass: BigIntValueClass,
                            booleanValueClass: BooleanValueClass,
                            doubleValueClass: DoubleValueClass,
                            durationValueClass: DurationValueClass,
                            floatValueClass: FloatValueClass,
                            intValueClass: IntValueClass,
                            localDateTimeValueClass: LocalDateTimeValueClass,
                            longValueClass: LongValueClass,
                            offsetDateTimeValueClass: OffsetDateTimeValueClass,
                            shortValueClass: ShortValueClass,
                            stringValueClass: StringValueClass,
                            uuidValueClass: UuidValueClass,
                            zonedDateTimeValueClass: ZonedDateTimeValueClass)

    object Record {
      implicit val jsonValueCodecForRecord: JsonValueCodec[Record] = JsonCodecMaker.make(CodecMakerConfig)

      val instance: Record = Record(
        BigDecimalValueClass(BigDecimal(200.0)),
        BigIntValueClass(BigInt(200)),
        BooleanValueClass(true),
        DoubleValueClass(5.4),
        DurationValueClass(Duration.ofSeconds(50, 399)),
        FloatValueClass(10.45F),
        IntValueClass(48),
        LocalDateTimeValueClass(LocalDateTime parse "2019-05-29T19:49:35.814"),
        LongValueClass(1234L),
        OffsetDateTimeValueClass(OffsetDateTime parse "2019-05-29T19:49:35.814+02:00"),
        ShortValueClass(7),
        StringValueClass("Hello World"),
        UuidValueClass(UUID fromString "c1df0070-4ccc-431c-a634-378c90624620"),
        ZonedDateTimeValueClass(ZonedDateTime parse "2019-05-29T19:50:54.008+02:00[Europe/Berlin]")
      )

      val jsonBytes: Array[Byte] =
        """{
          |"bigDecimalValueClass":200.0,
          |"bigIntValueClass":200,
          |"booleanValueClass":true,
          |"doubleValueClass":5.4,
          |"durationValueClass":"PT50.000000399S",
          |"floatValueClass":10.45,
          |"intValueClass":48,
          |"localDateTimeValueClass":"2019-05-29T19:49:35.814",
          |"longValueClass":1234,
          |"offsetDateTimeValueClass":"2019-05-29T19:49:35.814+02:00",
          |"shortValueClass":7,
          |"stringValueClass":"Hello World",
          |"uuidValueClass":"c1df0070-4ccc-431c-a634-378c90624620",
          |"zonedDateTimeValueClass":"2019-05-29T19:50:54.008+02:00[Europe/Berlin]"
          |}""".stripMargin.replaceAll("\n", "").getBytes(UTF_8)
    }

  }

  object newtype {

    import com.github.cerst.vacade.test.newtypeTypes._

    final case class Record(bigDecimalValueClass: BigDecimalValueClass,
                            bigIntValueClass: BigIntValueClass,
                            booleanValueClass: BooleanValueClass,
                            doubleValueClass: DoubleValueClass,
                            durationValueClass: DurationValueClass,
                            floatValueClass: FloatValueClass,
                            intValueClass: IntValueClass,
                            localDateTimeValueClass: LocalDateTimeValueClass,
                            longValueClass: LongValueClass,
                            offsetDateTimeValueClass: OffsetDateTimeValueClass,
                            shortValueClass: ShortValueClass,
                            stringValueClass: StringValueClass,
                            uuidValueClass: UuidValueClass,
                            zonedDateTimeValueClass: ZonedDateTimeValueClass)

    object Record {
      implicit val jsonValueCodecForRecord: JsonValueCodec[Record] = JsonCodecMaker.make(CodecMakerConfig)

      val instance: Record = Record(
        BigDecimalValueClass(BigDecimal(200.0)),
        BigIntValueClass(BigInt(200)),
        BooleanValueClass(true),
        DoubleValueClass(5.4),
        DurationValueClass(Duration.ofSeconds(50, 399)),
        FloatValueClass(10.45F),
        IntValueClass(48),
        LocalDateTimeValueClass(LocalDateTime parse "2019-05-29T19:49:35.814"),
        LongValueClass(1234L),
        OffsetDateTimeValueClass(OffsetDateTime parse "2019-05-29T19:49:35.814+02:00"),
        ShortValueClass(7),
        StringValueClass("Hello World"),
        UuidValueClass(UUID fromString "c1df0070-4ccc-431c-a634-378c90624620"),
        ZonedDateTimeValueClass(ZonedDateTime parse "2019-05-29T19:50:54.008+02:00[Europe/Berlin]")
      )

      val jsonBytes: Array[Byte] =
        """{
          |"bigDecimalValueClass":200.0,
          |"bigIntValueClass":200,
          |"booleanValueClass":true,
          |"doubleValueClass":5.4,
          |"durationValueClass":"PT50.000000399S",
          |"floatValueClass":10.45,
          |"intValueClass":48,
          |"localDateTimeValueClass":"2019-05-29T19:49:35.814",
          |"longValueClass":1234,
          |"offsetDateTimeValueClass":"2019-05-29T19:49:35.814+02:00",
          |"shortValueClass":7,
          |"stringValueClass":"Hello World",
          |"uuidValueClass":"c1df0070-4ccc-431c-a634-378c90624620",
          |"zonedDateTimeValueClass":"2019-05-29T19:50:54.008+02:00[Europe/Berlin]"
          |}""".stripMargin.replaceAll("\n", "").getBytes(UTF_8)
    }

  }

}
