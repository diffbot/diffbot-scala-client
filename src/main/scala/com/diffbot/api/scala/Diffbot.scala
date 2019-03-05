package com.diffbot.api.scala

import scala.util.{ Failure, Success }
import akka.actor.ActorSystem
import akka.event.Logging
import akka.util.Timeout
import scala.concurrent.Future
import akka.io.IO
import akka.pattern.ask
import spray.http._
import spray.can.Http
import HttpMethods._
import spray.json._
import java.net.URLEncoder

/**
 * This object handles the calls to different Diffbot services.
 */
object Diffbot {
  private[this] val apiBase = "https://api.diffbot.com"

  /**
   * Calls the Diffbot service with the specified parameters.
   *
   * @param serviceName The name of service, like "article", "frontpage", "product", ...
   * @param url The url of the page to analyze.
   * @param fields The fields to use, if not specified, all fields will be included.
   * @param system The ActorSystem to use.
   * @param timeout The timeouts for waiting for the result and the parameter to pass to the service request.
   * @param version The version of the API to use.
   * @param token The Diffbot API token.
   * @return A [[scala.concurrent.Future]] containing the result as a [[spray.json.JsValue]], or the cause of failure.
   */
  def apply(serviceName: String, url: String, fields: String*)(implicit system: ActorSystem, timeout: DiffbotTimeout, version: Version, token: Token): Future[JsValue] = {
    apply(serviceName, url, Map[String, Option[String]](), fields: _*)
  }
  /**
   * Calls the Diffbot service with the specified parameters.
   *
   * @param serviceName The name of service, like "article", "frontpage", "product", ...
   * @param url The url of the page to analyze.
   * @param optionalParameters The optional parameters of the service.
   * @param fields The fields to use, if not specified, all fields will be included.
   * @param system The ActorSystem to use.
   * @param timeout The timeouts for waiting for the result and the parameter to pass to the service request.
   * @param version The version of the API to use.
   * @param token The Diffbot API token.
   * @return A [[scala.concurrent.Future]] containing the result as a [[spray.json.JsValue]], or the cause of failure.
   */
  def apply(serviceName: String, url: String, optionalParameters: Map[String, Option[String]], fields: String*)(implicit system: ActorSystem, timeout: DiffbotTimeout, version: Version, token: Token): Future[JsValue] = {
    import system.dispatcher
    val log = Logging(system, getClass)

    val encodedUrl = java.net.URLEncoder.encode(url, "UTF-8")

    implicit val t: Timeout = timeout.timeout
    val request = s"""$apiBase/${version.toString}/$serviceName?token=${token.token}&url=$encodedUrl${
      if (fields.isEmpty) "" else { "&fields=" + fields.map(URLEncoder.encode(_, "UTF-8")).mkString(",") }
    }${
      val t = optionalParameters.map(parameterToString(_)).mkString("&")
      if (t.isEmpty) t else s"&$t"
    }"""
    system.log.debug("Request-Level API: request {}", request)
    for {
      response <- IO(Http).ask(HttpRequest(GET, Uri(request))).mapTo[HttpResponse]
      _ <- IO(Http) ? Http.CloseAll
    } yield {
      system.log.debug("Request-Level API: received {} response with {} bytes", response.status, response.entity.data.length)
      response.entity.asString.asJson
    }
  }

  private[this] def encode(s: String): String = java.net.URLEncoder.encode(s, "UTF-8")
  private[this] def parameterToString(param: (String, Option[String])): String = {
    param match {
      case (s, Some(v)) => s"${encode(s)}=${encode(v)}"
      case (s, None) => s"${encode(s)}"
    }
  }
}