addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.3.0")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.20.0")

resolvers += Resolver.bintrayRepo("oyvindberg", "converter")
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter" % "1.0.0-beta29+37-483f69ac")
