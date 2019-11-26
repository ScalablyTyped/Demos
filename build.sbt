import java.nio.file.Files
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

import org.scalajs.jsenv.nodejs.NodeJSEnv

import scala.sys.process.Process

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

lazy val `react-mobx` =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8001,
      libraryDependencies ++= Seq(
        ScalablyTyped.A.axios,
        ScalablyTyped.M.`material-ui`,
        ScalablyTyped.M.mobx,
        ScalablyTyped.M.`mobx-react`,
        ScalablyTyped.R.`react-facade`,
        ScalablyTyped.R.`react-dom`,
      ),
      Compile / npmDependencies ++= Seq(
        "material-ui" -> "0.20.1",
        "react" -> "16.9",
        "react-dom" -> "16.9",
      )
    )

lazy val `react-slick` =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8005,
      libraryDependencies ++= Seq(
        ScalablyTyped.R.`react-dom`,
        ScalablyTyped.R.`react-slick`,
        ScalablyTyped.R.`react-japgolly-facade`,
      ),
      Compile / npmDependencies ++= Seq(
        "react" -> "16.9",
        "react-dom" -> "16.9",
        "react-slick" -> "0.23",
      )
    )

lazy val vue =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8006,
      libraryDependencies ++= Seq(ScalablyTyped.V.vue)
    )

lazy val `react-big-calendar` =
  project
    .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
    .settings(
      webpackDevServerPort := 8007,
      libraryDependencies ++= Seq(
        ScalablyTyped.M.moment,
        ScalablyTyped.R.`react-dom`,
        ScalablyTyped.R.`react-big-calendar`,
        ScalablyTyped.R.`react-facade`,
      ),
      Compile / npmDependencies ++= Seq(
        "react" -> "16.9",
        "react-dom" -> "16.9",
        "react-big-calendar" -> "0.22",
      )
    )

lazy val three =
  project
    .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
    .settings(
      webpackDevServerPort := 8008,
      libraryDependencies ++= Seq(ScalablyTyped.T.three),
    )

lazy val d3 = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8002,
    libraryDependencies ++= Seq(ScalablyTyped.D.d3),
    Compile / npmDependencies ++= Seq("d3" -> "5.7"),
  )

lazy val jquery = project
  .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
  .settings(
    webpackDevServerPort := 8003,
    libraryDependencies ++= Seq(ScalablyTyped.J.jquery, ScalablyTyped.J.jqueryui),
    Compile / npmDependencies ++= Seq("jquery" -> "3.3", "jqueryui" -> "1.11.1")
  )

lazy val `google-maps` = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8004,
    libraryDependencies ++= Seq(ScalablyTyped.G.googlemaps),
  )

lazy val `semantic-ui-react` = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8009,
    libraryDependencies ++= Seq(
      ScalablyTyped.R.`redux-devtools-extension`,
      ScalablyTyped.R.`react-dom`,
      ScalablyTyped.R.`react-redux-facade`,
      ScalablyTyped.R.`react-facade`,
      ScalablyTyped.S.`semantic-ui-react`,
    ),
    Compile / npmDependencies ++= Seq(
      "react-dom" -> "16.9",
      "react" -> "16.9",
      "react-redux" -> "7.1",
    ),
  )

lazy val reveal = project
  .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
  .settings(
    webpackDevServerPort := 8010,
    libraryDependencies ++= Seq(
      ScalablyTyped.H.highlight_dot_js,
      ScalablyTyped.R.`react-redux-facade`,
      ScalablyTyped.R.`react-japgolly-facade`,
      ScalablyTyped.R.`reveal`,
    ),
    Compile / npmDependencies ++= Seq(
      "highlight.js" -> "9.12",
      "reveal.js" -> "3.7.0",
      "react-dom" -> "16.9",
      "react" -> "16.9",
    ),
  )

lazy val chart = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8011,
    libraryDependencies ++= Seq(ScalablyTyped.C.chart_dot_js),
    Compile / npmDependencies ++= Seq("chart.js" -> "2.8")
  )

lazy val p5 = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8014,
    libraryDependencies ++= Seq(ScalablyTyped.P.p5),
    Compile / npmDependencies ++= Seq("p5" -> "0.9")
  )

lazy val leaflet = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8015,
    libraryDependencies ++= Seq(ScalablyTyped.L.leaflet),
    Compile / npmDependencies ++= Seq("leaflet" -> "1.5")
  )

lazy val angular = project
  .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
  .settings(
    webpackDevServerPort := 8012,
    libraryDependencies ++= Seq(
      ScalablyTyped.C.`core-js`,
      ScalablyTyped.T.tslib,
      ScalablyTyped.Z.zone_dot_js,
      ScalablyTyped.R.rxjs,
      ScalablyTyped.A.angular__core,
      ScalablyTyped.A.angular__common,
      ScalablyTyped.A.angular__compiler,
      ScalablyTyped.A.angular__router,
      ScalablyTyped.A.`angular__platform-browser`,
      ScalablyTyped.A.`angular__platform-browser-dynamic`,
      ScalablyTyped.A.angular__forms,
    ),
    Compile / npmDependencies ++= Seq(
      "core-js" -> "2.6.8",
    )
  )

lazy val `storybook-react` = project
  .configure(baseSettings, application)
  .settings(
    libraryDependencies ++= Seq(
      ScalablyTyped.N.node,
      ScalablyTyped.R.`react-facade`,
      ScalablyTyped.S.storybook__react,
    ),
    /** This is not suitable for development, but effective for demo.
      * Run `yarn` and `yarn storybook` commands yourself, and run `~storybook-react/fastOptJS` from sbt
      */
    run := {
      Process("yarn", baseDirectory.value).!
      (Compile / fastOptJS).value
      Process("yarn storybook", baseDirectory.value).!
    },
    dist := {
      val distFolder = (ThisBuild / baseDirectory).value / "docs" / moduleName.value
      (Compile / fullOptJS).value
      Process("yarn dist", baseDirectory.value).!
      distFolder
    }
  )

lazy val `material-ui` =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8016,
      libraryDependencies ++= Seq(
        ScalablyTyped.M.`material-ui__core`,
        ScalablyTyped.M.`material-ui__icons`,
        ScalablyTyped.R.`react-facade`,
        ScalablyTyped.R.`react-dom`,
      ),
      Compile / npmDependencies ++= Seq(
        "react" -> "16.9",
        "react-dom" -> "16.9",
      )
    )

lazy val antd =
  project
    .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
    .settings(
      webpackDevServerPort := 8017,
      libraryDependencies ++= Seq(
        ScalablyTyped.A.antd,
        ScalablyTyped.R.`react-dom`,
        ScalablyTyped.R.`react-facade`
      ),
      Compile / npmDependencies ++= Seq(
        "react" -> "16.9",
        "react-dom" -> "16.9",
      )
    )

lazy val `antd-slinky` =
  project
    .configure(baseSettings, bundlerSettings, browserProject, withCssLoading)
    .settings(
      webpackDevServerPort := 8018,
      addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
      libraryDependencies ++= Seq(
        ScalablyTyped.A.`antd-slinky-facade`,
        ScalablyTyped.R.`react-dom`,
      ),
      Compile / npmDependencies ++= Seq(
        "react" -> "16.9",
        "react-dom" -> "16.9",
      )
    )

lazy val `react-router-dom` =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8019,
      libraryDependencies ++= Seq(
        ScalablyTyped.R.`react-dom`,
        ScalablyTyped.R.`react-router-dom`,
        ScalablyTyped.R.`react-facade`
      ),
      Compile / npmDependencies ++= Seq(
        "react" -> "16.9",
        "react-dom" -> "16.9",
        "react-router-dom" -> "5.0.0",
      )
    )

lazy val `react-router-dom-slinky` =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8020,
      addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
      libraryDependencies ++= Seq(ScalablyTyped.R.`react-router-dom-slinky-facade`),
      Compile / npmDependencies ++= Seq(
        "react" -> "16.9",
        "react-dom" -> "16.9",
        "react-router-dom" -> "5.0.0",
      )
    )

lazy val onsenui =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8021,
      libraryDependencies ++= Seq(ScalablyTyped.J.jquery, ScalablyTyped.O.onsenui),
      Compile / npmDependencies ++= Seq("jquery" -> "3.3")
    )

lazy val electron = project
  .configure(baseSettings, outputModule, application)
  .settings(
    libraryDependencies ++= Seq(ScalablyTyped.E.electron),
    /* run with globally installed electron */
    jsEnv := new NodeJSEnv(
      NodeJSEnv
        .Config()
        .withExecutable("electron")
        .withArgs(List((Compile / classDirectory).value.toString))
    ),
  )

// todo: disabled since they changed everything in react-navigation and I'm lazy.
//  We'll wait for the next version or replace it altogether.
//
//lazy val `react-native` = project
//  .configure(baseSettings, outputModule, application)
//  .settings(
//    scalaJSUseMainModuleInitializer := false,
//    libraryDependencies ++= Seq(
//      ScalablyTyped.E.`expo-font`,
//      ScalablyTyped.R.`react-native`,
//      ScalablyTyped.R.`react-navigation`,
//      ScalablyTyped.R.`react-native-gesture-handler`,
//      ScalablyTyped.R.`react-native-vector-icons`,
//      ScalablyTyped.R.`react-facade`
//    ),
//  )

lazy val lodash =
  project
    .configure(baseSettings, bundlerSettings, nodeProject)
    .settings(
      libraryDependencies ++= Seq(ScalablyTyped.L.lodash),
      Compile / npmDependencies ++= Seq("lodash" -> "4.17.11")
    )

lazy val `node-express` =
  project
    .configure(baseSettings, bundlerSettings, nodeProject)
    .settings(libraryDependencies ++= Seq(ScalablyTyped.E.express))

lazy val typescript =
  project
    .configure(baseSettings, bundlerSettings, nodeProject)
    .settings(
      libraryDependencies ++= Seq(ScalablyTyped.N.node, ScalablyTyped.T.typescript),
    )

lazy val baseSettings: Project => Project =
  _.enablePlugins(ScalaJSPlugin)
    .settings(
      scalaVersion := "2.12.9",
      version := "0.1-SNAPSHOT",
      scalacOptions ++= ScalacOptions.flags,
      /* in preparation for scala.js 1.0 */
      scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    )

lazy val application: Project => Project =
  _.settings(
    scalaJSUseMainModuleInitializer := true,
    /* disabled because it somehow triggers many warnings */
    emitSourceMaps := false,
    scalaJSModuleKind := ModuleKind.CommonJSModule,
  )

lazy val bundlerSettings: Project => Project =
  _.enablePlugins(ScalaJSBundlerPlugin)
    .configure(application)
    .settings(
      /* Specify current versions and modes */
      startWebpackDevServer / version := "3.1.10",
      webpack / version := "4.26.1",
      Compile / fastOptJS / webpackExtraArgs += "--mode=development",
      Compile / fullOptJS / webpackExtraArgs += "--mode=production",
      Compile / fastOptJS / webpackDevServerExtraArgs += "--mode=development",
      Compile / fullOptJS / webpackDevServerExtraArgs += "--mode=production",
      useYarn := true,
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
      "url-loader" -> "1.1.2",
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

val nodeProject: Project => Project =
  _.settings(jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv)

/* turn the javascript artifact into a module. */
val outputModule: Project => Project =
  _.settings(scalaJSModuleKind := ModuleKind.CommonJSModule)
