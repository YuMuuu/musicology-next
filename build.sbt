import org.scalajs.linker.interface.ModuleSplitStyle
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

ThisBuild / scalafixDependencies +=
  "io.github.dedis" %% "scapegoat-scalafix" % "1.1.4"
ThisBuild / scalafixDependencies +=
  "com.github.xuwei-k" % "scalafix-rules_2.13" % "0.6.30"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "musicology-next",
    scalaVersion := "3.9.0",
    scalacOptions ++= Seq(
      "-encoding",
      "utf-8",
      "-deprecation",
      "-feature",
      "-Wunused:all",
    ),
    wartremoverWarnings := Warts.all,
    wartremoverWarnings ++= ContribWart.All,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,

    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("example")))
    },

    libraryDependencies += "org.scala-js" % "scalajs-dom_sjs1_3" % "2.8.1",
  )
