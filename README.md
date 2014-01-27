# Scala API for Diffbot services

## Installation

Please see the [INSTALL](INSTALL.md) file for details. The easiest way is using sbt, by [cloning the repository](http://github.com/diffbot/diffbot-scala-client) (preferably a released tag) then
```
 > publish-local
```
from this project in sbt.
After that, you can simply use it as a dependency, as the following:
```
  libraryDependencies += "com.diffbot" %% "diffbot-scala-api" % "1.0",
```
to your build configuration (typically `build.sbt`, or `project/Build.scala` in your project's folder).


## Configuration

As this client uses [spray](http://spray.io) for its middleware, you can override certain configuration of spray, by adding an [application.conf](http://spray.io/documentation/1.2.0/spray-can/configuration/) file.

The Diffbot API specific configuration is quite simple. You just have to create an implicit value with the token you gathered, like this:
```Scala
  implicit val token: Token = Token("0123456789abcdef0123456789abcdef")
```

You can override the timeouts and the version too with different implicit values, like:
```Scala
  /**The first parameter is how long the client waits for the server, while the second is for the timeout parameter passed to the server in milliseconds, which can be None if you prefer not to specify.*/
  implicit val t: DiffbotTimeout = DiffbotTimeout(/*client*/timeout=125.second, timeoutParameterInMillis=Some(100000L))
  /** The service version to use. */
  implicit val v: Version = Version.v2
```

You will also need an [ActorSystem](http://doc.akka.io/docs/akka/2.0/general/actor-systems.html) for this client:
```Scala
  implicit val diffbotSystem:ActorSystem = ActorSystem("diffbotExample")//Just an example
```

## Usage

### Analyze API
Just execute the following code and later you can examine results:
```Scala
  val result: Future[JsValue] = Diffbot("analyze", url)
```

When you get the results, you can use the usual technics on scala [Future](http://docs.scala-lang.org/overviews/core/futures.html)s, for example:
```Scala
  result onSuccess {case t => println(t.prettyPrint)}
```
If you prefer synchronous calls, you can wait till the result is ready, using  [`Await`](http://docs.scala-lang.org/overviews/core/futures.html#blocking).

### Article API
Just execute the following code and later you can examine results:
```Scala
  val result: Future[JsValue] = Diffbot("article", url)
```
You can handle the cases similarly to the Analyze API.

### Other APIs
Please check the parameters for other Diffbot products on the homepage: http://diffbot.com/products/

### Complete example
Here is a whole sample program:
```Scala
		package com.diffbot.api.scala
		
		import scala.concurrent.Future
		import spray.json._
		import akka.actor.ActorSystem
		
		object Main extends App {
		  //We need an ActorSystem
		  implicit val diffbotSystem:ActorSystem = ActorSystem("diffbotExample")
		
		  //We need a token it consists of 32 hexadecimal digits
		  //please check the http://diffbot.com/pricing/ page for details
		  implicit val token: Token = Token("0123456789abcdef0123456789abcdef")
        
          val url = "http://www.xconomy.com/san-francisco/2012/07/25/diffbot-is-using-computer-vision-to-reinvent-the-semantic-web/"
          
          //You can override the version and the timeout too with implicit values
		  //import scala.concurrent.duration._
          //implicit val t: DiffbotTimeout = DiffbotTimeout(125.second, Some(100000L))
          //implicit val v: Version = Version.v2
          //or just use our suggested defaults
		  import Implicits._
          
		  //Call the API
		  val f: Future[JsValue] = Diffbot.call("article", url)
		
		  //Use the result in some way
		  import scala.concurrent.ExecutionContext.Implicits.global
		  f.onComplete(t => {println(t.get.prettyPrint)})
		  //TODO, you might want to handle failure too
		
		  //This way you can simulate synchronous method calls
		  import scala.concurrent.Await
		  import scala.concurrent.duration._
		  val json: JsValue = Await.result(f, 125.seconds)
		  
		  //Shut down the actor system, we no longer need them.
		  diffbotSystem.shutdown
		}
```

## Notes


Please check the [Spray http-client documentation](http://spray.io/documentation/1.1-SNAPSHOT/spray-can/http-client/) for additional customization options, especially [Request level clients](http://spray.io/documentation/1.1-SNAPSHOT/spray-can/http-client/request-level/). For configuration (like proxy, or different connection specific options), please see the [Spray configuration](http://spray.io/documentation/1.2.0/spray-can/configuration/) page.

-Initial commit by Gábor Bakos-
