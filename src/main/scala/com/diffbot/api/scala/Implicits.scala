package com.diffbot.api.scala

import akka.actor.ActorSystem
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Default values for the Diffbot API calls.
 */
object Implicits {
  /**
   * The default timeout for the client call and the server parameter:
   * 125 seconds and 100 seconds respectively.
   */
  implicit val timeout: DiffbotTimeout = DiffbotTimeout(125.seconds)

  /**
   * The default Diffbot API to use, v2.
   */
  implicit val version: Version = Version.v3
}