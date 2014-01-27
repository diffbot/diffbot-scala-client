Setting up Scala API for Diffbot services for development
=========================================================

Dependencies
------------

This API is known to work with Scala 2.10.3 (probably with any current or future Scala 2.10.x version) and Java 7 (probably any Java version from 6 and above).

The following jars are used, //based on the output of sbt's > update command.

io.spray#spray-can;1.2.0
io.spray#spray-io;1.2.0
io.spray#spray-util;1.2.0
io.spray#spray-http;1.2.0
org.parboiled#parboiled-scala_2.10;1.1.6
org.parboiled#parboiled-core;1.1.6
io.spray#spray-json_2.10;1.2.5
com.typesafe.akka#akka-actor_2.10;2.2.3
com.typesafe#config;1.0.2
org.scala-lang#scala-compiler;2.10.3
org.scala-lang#scala-reflect;2.10.3
org.scala-lang#jline;2.10.3
org.fusesource.jansi#jansi;1.4

from:
http://repo.spray.io/io/spray/spray-can/1.2.0/spray-can-1.2.0.jar
http://repo.spray.io/io/spray/spray-io/1.2.0/spray-io-1.2.0.jar
http://repo.spray.io/io/spray/spray-util/1.2.0/spray-util-1.2.0.jar
http://repo.spray.io/io/spray/spray-http/1.2.0/spray-http-1.2.0.jar
http://repo1.maven.org/maven2/org/parboiled/parboiled-scala_2.10/1.1.6/parboiled-scala_2.10-1.1.6.jar
http://repo1.maven.org/maven2/org/parboiled/parboiled-core/1.1.6/parboiled-core-1.1.6.jar
http://repo.spray.io/io/spray/spray-json_2.10/1.2.5/spray-json_2.10-1.2.5.jar
http://central.maven.org/maven2/com/typesafe/akka/akka-actor_2.10/2.2.3/akka-actor_2.10-2.2.3.jar
http://central.maven.org/maven2/com/typesafe/config/1.0.2/config-1.0.2.jar
http://central.maven.org/maven2/org/scala-lang/scala-compiler/2.10.3/scala-compiler-2.10.3.jar
http://central.maven.org/maven2/org/scala-lang/scala-reflect/2.10.3/scala-reflect-2.10.3.jar
http://central.maven.org/maven2/org/scala-lang/jline/2.10.3/jline-2.10.3.jar
http://central.maven.org/maven2/org/fusesource/jansi/jansi/1.4/jansi-1.4.jar

Just add the dependencies with the diffbot-scala-api_2.10-1.0.jar to your classpath and use it according to the [README](README.md).