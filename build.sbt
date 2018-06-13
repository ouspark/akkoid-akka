import sbt.Keys._
import sbt.Project.projectToRef

lazy val server = (project in file("server"))
  .settings(
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value,
      libraryDependencies ++= Settings.jsDependencies.value,
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    // triggers scalaJSPipeline when using compile or continuous compilation
    compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
    WebKeys.packagePrefix in Assets := "public/",
    managedClasspath in Runtime += (packageBin in Assets).value,
    // Compile the project before generating Eclipse files, so that generated .scala or .class files for Twirl templates are present
  //  EclipseKeys.preTasks := Seq(compile in Compile)
  )
  .enablePlugins(SbtWeb, JavaAppPackaging)
  .dependsOn(sharedJVM)

lazy val client = (project in file("client"))
  .settings(
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.scalajsDependencies.value,

    scalaJSUseMainModuleInitializer := true
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(sharedJS)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(
    scalaVersion := Settings.versions.scala,
    libraryDependencies ++= Settings.sharedDependencies.value
  )
  // set up settings specific to the JS project
  .jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJVM = shared.jvm
lazy val sharedJS = shared.js

// loads the server project at sbt startup
onLoad in Global := (onLoad in Global).value andThen {s: State => "project server" :: s}

evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false)