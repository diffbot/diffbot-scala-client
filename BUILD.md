Building the Scala API for Diffbot services
===========================================

Build
-----

To build this project, download sbt (at least 0.13.0, tested with 0.13.1), extract the content of the project to a folder.
Open a command prompt and go the the extracted folder.
Start sbt.

		> packageBin

will generate the jar file to use in other projects,

		> packageSrc

will generate the jar file containing the sources (in case you want to make it open source you can also publish this to the maven/ivy repository),

		> packageDoc

will generate the documentation (to be uploaded to the repository to ease the development).

Customization
-------------

There are constants in the Implicits.scala for version and internal timeout.


Distribution
------------

You should only need the output of packageBin in the target/scala-2.10 folder (diffbot-scala-api_2.10-1.0.jar for example), if you want to create a repository for the sources, use the following files:
 + README.md
 + INSTALL.md
 + BUILD.md (optional)
 + build.sbt
 + - project - + plugins.sbt
 + - src - + - main - + - scala - + - com - + - diffbot - + - api - + - scala - + Diffbot.scala
                                                                                + DiffbotTimeout.scala
                                                                                + Implicits.scala
                                                                                + Token.scala
                                                                                + Version.scala
 + - src - + - test - + - scala - + - com - + - diffbot - + - api - + - scala - + * 

A better option is setting up (or getting access to) a Maven repository and publish there. You should specify the publish configuration (http://www.scala-sbt.org/release/docs/Detailed-Topics/Publishing) in sbt and call:

        > publish 

to release a new version. For local publishing:

        > publishLocal


Disclaimer
----------

The API was created based on the following examples:
https://github.com/spray/spray/blob/release/1.3/examples/spray-can/simple-http-client/src/main/scala/spray/examples/RequestLevelApiDemo.scala
https://github.com/spray/spray-json

I guess these are either in public domain, or have the same license as spray, Apache v2 (http://spray.io/project-info/license/).

Improving
---------

You can generate the eclipse project files using the

		> eclipse

sbt command, after that, you can import the project to an eclipse with Scala IDE. (There are other sbt plugins for other developer environments, currently only eclipse is set up.)

On the JVM there is a tendency to use OSGi for modularization. It might be a good idea to make the output to an OSGi bundle.

Have fun improving it! :)