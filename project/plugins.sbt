// fast development turnaround when using sbt ~reStart
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")

//addSbtPlugin("com.lihaoyi" % "workbench" % "0.4.1")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.5")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.22")

//addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")

//addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.3")

addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.7")

//addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.3.15")
