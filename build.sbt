import org.scalajs.linker.interface.ModuleSplitStyle
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

ThisBuild / organization := "dev.yumuuu"
ThisBuild / scalaVersion := "3.9.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

ThisBuild / scalafixDependencies +=
  "io.github.dedis" %% "scapegoat-scalafix" % "1.1.4"
ThisBuild / scalafixDependencies +=
  "com.github.xuwei-k" % "scalafix-rules_2.13" % "0.6.30"

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-encoding",
    "utf-8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Wunused:all",
    "-Wvalue-discard",
    "-Wnonunit-statement",
  ),
  wartremoverErrors := Warts.all,
  wartremoverErrors ++= ContribWart.All,
)

lazy val scalaJsSettings = Seq(
  scalaJSUseMainModuleInitializer := true,
  scalaJSLinkerConfig ~= {
    _.withModuleKind(ModuleKind.ESModule)
      .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("example")))
  },
)

lazy val coreDomain = project
  .in(file("modules/core-domain"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
  .settings(
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.13.0",
    libraryDependencies += "org.typelevel" %% "cats-laws" % "2.13.0" % Test,
    libraryDependencies += "org.typelevel" %% "discipline-scalatest" % "2.3.0" % Test,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  )

lazy val domain = project
  .in(file("modules/domain"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(coreDomain)
  .settings(commonSettings)

lazy val usecase = project
  .in(file("modules/usecase"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)

lazy val application = project
  .in(file("modules/application"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(domain, usecase)
  .settings(commonSettings)

lazy val infrastructure = project
  .in(file("modules/infrastructure"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(domain)
  .settings(commonSettings)

lazy val presentation = project
  .in(file("modules/presentation"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(application, domain, usecase)
  .settings(commonSettings)

lazy val bootstrap = project
  .in(file("modules/bootstrap"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(domain, usecase, application, infrastructure, presentation)
  .settings(commonSettings)
  .settings(scalaJsSettings)

lazy val root = project
  .in(file("."))
  .aggregate(coreDomain, domain, usecase, application, infrastructure, presentation, bootstrap)
  .settings(commonSettings)
  .settings(
    name := "musicology-next",
    publish / skip := true,
  )
