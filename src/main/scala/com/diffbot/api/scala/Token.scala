package com.diffbot.api.scala

/**
 * A wrapper around the Diffbot token. This way, the implicit parameter will not be confused with other implicit values.
 * @param token The diffbot token to wrap.
 */
case class Token(token: String)/* extends AnyVal*/ {
  require {token.matches("[0-9a-f]{32}")}//either this check, or the value class
}