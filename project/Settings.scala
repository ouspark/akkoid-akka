import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Settings {

  val name = "akkoid-akka"
  val organization = "com.akkoid"
  val version = "1.0.0-SNAPSHOT"

  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  object versions {
    val scala = "2.12.5"
    val scalaDom = ""
    val autowire = "0.2.6"
    val upickle = "0.6.6"
    val diode = "1.13"
    val uTest = ""

    val jQuery = "1.11.1"
    val bootstrap = "3.3.6"
    val scalajsScripts = "1.1.2"

    val akkaVersioin = "2.5.12"
    val akkaHttp = "10.1.1"
  }

  /**
    * These dependencies are shared between JS and JVM projects
    * the special %%% function selects the correct version for each project
    */
  val sharedDependencies = Def.setting(Seq(
    "com.lihaoyi" %%% "autowire" % versions.autowire,
    "com.lihaoyi" %%% "upickle" % versions.upickle,
    "com.lihaoyi" %%% "scalatags" % "0.6.7"

  ))

  /** Dependencies only used by the JVM project */
  val jvmDependencies = Def.setting(Seq(
    "com.typesafe.akka" %% "akka-http" % versions.akkaHttp,
    "com.typesafe.akka" %% "akka-stream" % versions.akkaVersioin,
    "com.typesafe.akka" %% "akka-actor" % versions.akkaVersioin
  ))

  /** Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.5"
  ))

  /** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
  val jsDependencies = Def.setting(Seq(
    "org.webjars.npm" % "purecss" % "1.0.0"
//    "org.webjars" % "jquery" % versions.jQuery / "jquery.js" minified "jquery.min.js",
//    "org.webjars" % "bootstrap" % versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js"
  ))

}
