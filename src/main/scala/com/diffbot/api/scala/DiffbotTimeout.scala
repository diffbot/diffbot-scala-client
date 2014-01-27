package com.diffbot.api.scala

import akka.util.Timeout

/**
 * Timeout values for the Diffbot service calls
 * @param timeout The length of time the client waits for the response.
 */
case class DiffbotTimeout(val timeout: Timeout)