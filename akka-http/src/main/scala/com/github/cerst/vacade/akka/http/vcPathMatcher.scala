package com.github.cerst.vacade.akka.http

import java.util.UUID

import akka.http.scaladsl.server.PathMatcher1
import akka.http.scaladsl.server.PathMatchers._

import scala.util.control.NonFatal

/**
  * Type-suffix'd methods are required because akka-http
  * <ul>
  *   <li>does not have implicits for PathMatcher1</i>
  *   <li>vcPathMatcherHexInt & vcPathMatcherInt (as well as other similar pairs) have the same signature</li>
  * </ul>
  */
object vcPathMatcher {

  final def double[VC](construct: Double => VC): PathMatcher1[VC] = safeMap(DoubleNumber, construct)

  final def hexInt[VC](construct: Int => VC): PathMatcher1[VC] = safeMap(HexIntNumber, construct)

  final def hexLong[VC](construct: Long => VC): PathMatcher1[VC] = safeMap(HexLongNumber, construct)

  final def int[VC](construct: Int => VC): PathMatcher1[VC] = safeMap(IntNumber, construct)

  final def long[VC](construct: Long => VC): PathMatcher1[VC] = safeMap(LongNumber, construct)

  final def string[VC](construct: String => VC): PathMatcher1[VC] = safeMap(Segment, construct)

  final def uuid[VC](construct: UUID => VC): PathMatcher1[VC] = safeMap(JavaUUID, construct)

  private final def safeMap[U, VC](pathMatcher1: PathMatcher1[U], construct: U => VC): PathMatcher1[VC] = {
    pathMatcher1.flatMap { u =>
      try {
        Some { construct(u) }
      } catch {
        case NonFatal(_) => None
      }
    }
  }

}
