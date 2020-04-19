import java.nio.file.Files
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

import org.scalajs.jsenv.nodejs.NodeJSEnv

import scala.sys.process.Process

onLoad in Global := {
  println(
    """*
      |* Welcome to ScalablyTyped demos!
      |*
      |* For documentation see https://scalablytyped.org .
      |*
      |* Note that the first time you import/compile the projects it'll take a while for the dependencies to build
      |*""".stripMargin
  )
  (onLoad in Global).value
}

// both intellij and ci needs this to not OOM during initial import since we have so many projects
Global / concurrentRestrictions ++= {
  val gigabytes = (java.lang.Runtime.getRuntime.maxMemory) / (1000 * 1000 * 1000)
  val numParallel = Math.max(1, gigabytes.toInt)
  List(Tags.limit(ScalablyTypedTag, numParallel))
}

/**
  * Custom task to start demo with webpack-dev-server, use as `<project>/start`.
  * Just `start` also works, and starts all frontend demos
  *
  * After that, the incantation is this to watch and compile on change:
  * `~<project>/fastOptJS::webpack`
  */
lazy val start = TaskKey[Unit]("start")

/** Say just `dist` or `<project>/dist` to make a production bundle in
  * `docs` for github publishing
  */
lazy val dist = TaskKey[File]("dist")

lazy val baseSettings: Project => Project =
  _.enablePlugins(ScalaJSPlugin)
    .settings(
      scalaVersion := "2.13.1",
      version := "0.1-SNAPSHOT",
      scalacOptions ++= ScalacOptions.flags,
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig ~= (_
      /* disabled because it somehow triggers many warnings */
        .withSourceMap(false)
        .withModuleKind(ModuleKind.CommonJSModule))
    )

lazy val bundlerSettings: Project => Project =
  _.enablePlugins(ScalablyTypedConverterPlugin)
    .settings(
      Compile / fastOptJS / webpackExtraArgs += "--mode=development",
      Compile / fullOptJS / webpackExtraArgs += "--mode=production",
      Compile / fastOptJS / webpackDevServerExtraArgs += "--mode=development",
      Compile / fullOptJS / webpackDevServerExtraArgs += "--mode=production",
      useYarn := true
    )

lazy val vue =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8004,
      Compile / npmDependencies ++= Seq("vue" -> "2.6.11")
    )

lazy val three =
  project
    .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
    .settings(
      webpackDevServerPort := 8005,
      Compile / npmDependencies ++= Seq("three" -> "0.112.1")
    )

lazy val d3 = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8001,
    /* we use a bit of functionality which can't be found in scala-js-dom */
    stUseScalaJsDom := false,
    Compile / npmDependencies ++= Seq(
      "d3" -> "5.15",
      "@types/d3" -> "5.7.2"
    )
  )

lazy val jquery = project
  .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
  .settings(
    webpackDevServerPort := 8003,
    Compile / npmDependencies ++= Seq(
      "jquery" -> "3.3",
      "@types/jquery" -> "3.3.31",
      "jqueryui" -> "1.11.1",
      "@types/jqueryui" -> "1.12.10"
    )
  )

lazy val `google-maps` = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8002,
    Compile / npmDependencies ++= Seq(
      "@types/googlemaps" -> "3.39.2"
    )
  )

lazy val reveal = project
  .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
  .settings(
    webpackDevServerPort := 8006,
    stFlavour := Flavour.Japgolly,
    Compile / npmDependencies ++= Seq(
      "@types/highlight.js" -> "9.12.3",
      "@types/reveal" -> "3.3.33",
      "highlight.js" -> "9.12",
      "reveal.js" -> "3.7.0",
      "react" -> "16.9",
      "react-dom" -> "16.9"
    )
  )

lazy val chart = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8007,
    stUseScalaJsDom := false,
    Compile / npmDependencies ++= Seq(
      "@types/chart.js" -> "2.9.11",
      "chart.js" -> "2.9.3"
    )
  )

lazy val p5 = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8009,
    Compile / npmDependencies ++= Seq(
      "@types/p5" -> "0.9.0",
      "p5" -> "0.9"
    )
  )

lazy val leaflet = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8010,
    Compile / npmDependencies ++= Seq(
      "leaflet" -> "1.5.1",
      "@types/leaflet" -> "1.5.8"
    )
  )

lazy val angular = project
  .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
  .settings(
    webpackDevServerPort := 8008,
    stEnableScalaJsDefined := Selection.NoneExcept("@angular/core"),
    /* this shouldn't be used directly */
    stIgnore := List("@angular/compiler"),
    Compile / npmDependencies ++= Seq(
      "@angular/common" -> "8.2.14",
      "@angular/compiler" -> "8.2.14",
      "@angular/core" -> "8.2.14",
      "@angular/forms" -> "8.2.14",
      "@angular/platform-browser-dynamic" -> "8.2.14",
      "@angular/platform-browser" -> "8.2.14",
      "@angular/router" -> "8.2.14",
      "core-js" -> "2.6.8",
      "rxjs" -> "6.5.4",
      "tslib" -> "1.10.0",
      "zone.js" -> "0.9.1"
    )
  )

lazy val onsenui =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8011,
      Compile / npmDependencies ++= Seq(
        "@types/jquery" -> "3.3.31",
        "jquery" -> "3.3",
        "onsenui" -> "2.10.10"
      )
    )

lazy val phaser =
  project
    .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
    .settings(
      webpackDevServerPort := 8012,
      stEnableScalaJsDefined := Selection.NoneExcept("phaser"),
      Compile / npmDependencies ++= Seq("phaser" -> "3.22.0")
    )

lazy val pixi = project
  .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
  .settings(
    webpackDevServerPort := 8013,
    Compile / npmDependencies ++= Seq(
      "pixi.js" -> "5.2.1",
      "pixi-filters" -> "3.1.0",
      "@types/highlight.js" -> "9.12.3",
      "highlight.js" -> "9.12"
    )
  )

lazy val electron = project
/* ScalablyTypedConverterExternalNpmPlugin requires that we define how to install node dependencies and where they are */
  .enablePlugins(ScalablyTypedConverterExternalNpmPlugin)
  .configure(baseSettings, outputModule)
  .settings(
    stStdlib := List("es5"), // doesn't include DOM
    externalNpm := {
      /* since we run yarn ourselves the dependencies live in electron/package.json */
      Process("yarn", baseDirectory.value).!
      baseDirectory.value
    },
    jsEnv := new NodeJSEnv(
      NodeJSEnv
        .Config()
        .withExecutable("electron/node_modules/.bin/electron")
        .withArgs(List((Compile / classDirectory).value.toString))
    )
  )

lazy val lodash =
  project
    .configure(baseSettings, bundlerSettings, nodeProject)
    .settings(
      Compile / npmDependencies ++= Seq(
        "@types/lodash" -> "4.14.149",
        "@types/node" -> "13.5.0",
        "lodash" -> "4.17.11"
      )
    )

lazy val `node-express` =
  project
    .configure(baseSettings, bundlerSettings, nodeProject)
    .settings(
      Compile / npmDependencies ++= Seq(
        "@types/express" -> "4.17.2",
        "@types/node" -> "13.5.0",
        "express" -> "4.17.1"
      )
    )

lazy val typescript =
  project
    .configure(baseSettings, bundlerSettings, nodeProject)
    .settings(
      Compile / npmDependencies ++= Seq(
        "typescript" -> "3.8.3",
      ),
      /* typescript is implicitly added by the plugin since that's where we get the files for stdlib, and also implicitly ignored */
      stIgnore ~= (_.filterNot(_ == "typescript"))
    )

val nodeProject: Project => Project =
  _.settings(
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv,
    // es5 doesn't include DOM, which we don't have access to in node
    stStdlib := List("es5"),
    stUseScalaJsDom := false,
    Compile / npmDependencies ++= Seq(
      "@types/node" -> "13.5.0"
    )
  )

/* turn the javascript artifact into a module. */
val outputModule: Project => Project =
  _.settings(
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule))
  )

lazy val withCssLoading: Project => Project =
  _.settings(
    /* custom webpack file to include css */
    webpackConfigFile := Some((ThisBuild / baseDirectory).value / "custom.webpack.config.js"),
    Compile / npmDevDependencies ++= Seq(
      "webpack-merge" -> "4.1",
      "css-loader" -> "2.1.0",
      "style-loader" -> "0.23.1",
      "file-loader" -> "3.0.1",
      "url-loader" -> "1.1.2"
    )
  )

/**
  * Implement the `start` and `dist` tasks defined above.
  * Most of this is really just to copy the index.html file around.
  */
lazy val browserProject: Project => Project =
  _.settings(
    start := {
      (Compile / fastOptJS / startWebpackDevServer).value
      val indexFrom = baseDirectory.value / "assets/index.html"
      val indexTo   = (Compile / fastOptJS / crossTarget).value / "index.html"
      Files.copy(indexFrom.toPath, indexTo.toPath, REPLACE_EXISTING)
    },
    dist := {
      val artifacts      = (Compile / fullOptJS / webpack).value
      val artifactFolder = (Compile / fullOptJS / crossTarget).value
      val distFolder     = (ThisBuild / baseDirectory).value / "docs" / moduleName.value

      distFolder.mkdirs()
      artifacts.foreach { artifact =>
        val target = artifact.data.relativeTo(artifactFolder) match {
          case None          => distFolder / artifact.data.name
          case Some(relFile) => distFolder / relFile.toString
        }

        Files.copy(artifact.data.toPath, target.toPath, REPLACE_EXISTING)
      }

      val indexFrom = baseDirectory.value / "assets/index.html"
      val indexTo   = distFolder / "index.html"

      val indexPatchedContent = {
        import collection.JavaConverters._
        Files
          .readAllLines(indexFrom.toPath, IO.utf8)
          .asScala
          .map(_.replaceAllLiterally("-fastopt-", "-opt-"))
          .mkString("\n")
      }

      Files.write(indexTo.toPath, indexPatchedContent.getBytes(IO.utf8))
      distFolder
    }
  )
