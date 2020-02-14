/*
 * Copyright 2020 Constantin Gerstberger
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.github.cerst.vacade.test.akka.http

import java.util.UUID

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatcher1
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.Assertions
import org.scalatest.freespec.AnyFreeSpec

final class vcPathMatcherSpec extends AnyFreeSpec with Assertions with ScalatestRouteTest {

  private def test[VC](pathMatcher1: PathMatcher1[VC], matchable: (String, VC), unmatchable: String): Unit = {
    val route = path(pathMatcher1) { refined =>
      validate(refined == matchable._2, "PathMatcher yielded unexpected value") {
        complete(StatusCodes.OK)
      }
    }

    Get(s"/${matchable._1}") ~> route ~> check {
      assert(status == StatusCodes.OK)
    }

    Get(s"/$unmatchable") ~> route ~> check {
      val _ = assert(!handled)
    }
  }

  "AnyVal" - {
    import com.github.cerst.vacade.test.anyvaltypes._

    "double" in {
      test(DoubleValueClass.pm, "1.3" -> DoubleValueClass(1.3D), "-2.4")
    }

    "int from hex-string" in {
      test(IntValueClass.hexIntPm, "b" -> IntValueClass(11), "a")
    }

    "int from string" in {
      test(IntValueClass.intPm, "1" -> IntValueClass(1), "0")
    }

    "long from hex-string" in {
      test(LongValueClass.hexLongPm, "f" -> LongValueClass(15), "0")
    }

    "long from string" in {
      test(LongValueClass.longPm, "1" -> LongValueClass(1), "-1")
    }

    "string" in {
      test(StringValueClass.pm, "a" -> StringValueClass("a"), "")
    }

    "uuid" in {
      test(
        UuidValueClass.pm,
        "2d1047f3-9a87-4a3b-acff-4f0c0e6fed05" -> UuidValueClass(
          UUID fromString "2d1047f3-9a87-4a3b-acff-4f0c0e6fed05"
        ),
        ""
      )
    }
  }

  "newtype" - {
    import com.github.cerst.vacade.test.newtypeTypes._
    "double" in {
      test(DoubleValueClass.pm, "1.3" -> DoubleValueClass(1.3D), "-2.4")
    }

    "int from hex-string" in {
      test(IntValueClass.hexIntPm, "b" -> IntValueClass(11), "a")
    }

    "int from string" in {
      test(IntValueClass.intPm, "1" -> IntValueClass(1), "0")
    }

    "long from hex-string" in {
      test(LongValueClass.hexLongPm, "f" -> LongValueClass(15), "0")
    }

    "long from string" in {
      test(LongValueClass.longPm, "1" -> LongValueClass(1), "-1")
    }

    "string" in {
      test(StringValueClass.pm, "a" -> StringValueClass("a"), "")
    }

    "uuid" in {
      test(
        UuidValueClass.pm,
        "2d1047f3-9a87-4a3b-acff-4f0c0e6fed05" -> UuidValueClass(
          UUID fromString "2d1047f3-9a87-4a3b-acff-4f0c0e6fed05"
        ),
        ""
      )
    }
  }

}
