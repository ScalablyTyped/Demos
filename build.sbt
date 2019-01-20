import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

/**
  * Custom task to start demo with webpack-dev-server, use as `<project>/start`.
  * Just `start` also works, and starts all demos
  *
  * After that, the incantation is this to watch and compile on change:
  * `~<project>/fastOptJS::webpack`
  */
lazy val start = TaskKey[Unit]("start")

/** Say just `dist` or `<project>/dist` to make a production bundle in
  * `docs` for github publishing
  */
lazy val dist = TaskKey[Unit]("dist")

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
        ScalablyTyped.R.`react-contrib`,
        ScalablyTyped.R.`react-dom`,
      ),
      npmDependencies in Compile ++= Seq(
        "axios" -> "0.18.0",
        "material-ui" -> "0.20.1",
        "mobx" -> "5.8.0",
        "mobx-react" -> "5.4.3",
        "react" -> "16.7.0",
        "react-dom" -> "16.7.0",
      )
    )

lazy val `react-slick` =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8005,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core" % "1.3.1",
        ScalablyTyped.R.`react-dom`,
        ScalablyTyped.R.`react-slick`,
        ScalablyTyped.R.`react-contrib`,
        ScalablyTyped.R.`react-japgolly-contrib`,
      ),
      npmDependencies in Compile ++= Seq(
        "react" -> "16.5.1",
        "react-dom" -> "16.5.1",
        "react-slick" -> "0.23",
      )
    )

lazy val vue =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8006,
      libraryDependencies ++= Seq(ScalablyTyped.V.vue),
      npmDependencies in Compile ++= Seq("vue" -> "2.5.21")
    )

lazy val `react-big-calendar` =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8007,
      /* custom webpack file to include css */
      webpackConfigFile := Some(baseDirectory.value / "custom.webpack.config.js"),
      libraryDependencies ++= Seq(
        ScalablyTyped.M.moment,
        ScalablyTyped.R.`react-dom`,
        ScalablyTyped.R.`react-big-calendar`,
        ScalablyTyped.R.`react-contrib`,
      ),
      npmDependencies in Compile ++= Seq(
        "moment" -> "2.23.0",
        "react" -> "16.5.1",
        "react-dom" -> "16.5.1",
        "react-big-calendar" -> "0.20",
      ),
      npmDevDependencies in Compile ++= Seq(
        "webpack-merge" -> "4.1",
        "css-loader" -> "2.1.0",
        "style-loader" -> "0.23.1"
      )
    )

lazy val three =
  project
    .configure(baseSettings, bundlerSettings, browserProject)
    .settings(
      webpackDevServerPort := 8008,
      libraryDependencies ++= Seq(ScalablyTyped.T.three),
      npmDependencies in Compile ++= Seq("three" -> "0.93")
    )

lazy val d3 = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8002,
    libraryDependencies ++= Seq(ScalablyTyped.D.d3),
    npmDependencies in Compile ++= Seq("d3" -> "5.5.0"),
  )

lazy val jquery = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8003,
    libraryDependencies ++= Seq(ScalablyTyped.J.jquery, ScalablyTyped.J.jqueryui),
    npmDependencies in Compile ++= Seq("jquery" -> "3.3", "jqueryui" -> "1.11.1"),
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
      ScalablyTyped.R.`redux`,
      ScalablyTyped.R.`redux-devtools-extension`,
      ScalablyTyped.R.`react-redux`,
      ScalablyTyped.R.`react-redux-contrib`,
      ScalablyTyped.R.`react-contrib`,
      ScalablyTyped.S.`semantic-ui-react`,
      ScalablyTyped.S.`std-contrib`,
    ),
    npmDependencies in Compile ++= Seq(
      "redux" -> "4.0.1",
      "redux-devtools-extension" -> "2.13.7",
      "react-dom" -> "16.7.0",
      "react" -> "16.7.0",
      "react-redux" -> "6.0",
      "semantic-ui-react" -> "0.84.0",
    ),
  )

lazy val reveal = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8010,
    /* custom webpack file to include css */
    webpackConfigFile := Some(baseDirectory.value / "custom.webpack.config.js"),
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core" % "1.3.1",
      ScalablyTyped.H.highlight_dot_js,
      ScalablyTyped.R.`react-contrib`,
      ScalablyTyped.R.`reveal`,
    ),
    npmDependencies in Compile ++= Seq(
      "highlight.js" -> "9.12",
      "reveal.js" -> "3.7.0",
      "react-dom" -> "16.7.0",
      "react" -> "16.7.0",
    ),
    npmDevDependencies in Compile ++= Seq(
      "webpack-merge" -> "4.1",
      "css-loader" -> "2.1.0",
      "style-loader" -> "0.23.1",
      "file-loader" -> "3.0.1",
      "url-loader" -> "1.1.2",
    )
  )

lazy val chart = project
  .configure(baseSettings, bundlerSettings, browserProject)
  .settings(
    webpackDevServerPort := 8011,
    libraryDependencies ++= Seq(ScalablyTyped.C.chart_dot_js),
    npmDependencies in Compile ++= Seq("chart.js" -> "2.7")
  )

lazy val lodash =
  project
    .configure(baseSettings, bundlerSettings, nodeProject)
    .settings(
      libraryDependencies ++= Seq(ScalablyTyped.L.lodash),
      npmDependencies in Compile ++= Seq("lodash" -> "4.17.11")
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
      npmDependencies in Compile ++= Seq("typescript" -> "3.2.2")
    )

lazy val baseSettings: Project => Project =
  _.enablePlugins(ScalaJSPlugin)
    .settings(
      scalaVersion := "2.12.8",
      version := "0.1-SNAPSHOT",
      scalacOptions ++= ScalacOptions.flags,
      scalaJSUseMainModuleInitializer := true,
      scalaJSModuleKind := ModuleKind.CommonJSModule,
      /* disabled because it somehow triggers many warnings */
      emitSourceMaps := false,
      /* in preparation for scala.js 1.0 */
      scalacOptions += "-P:scalajs:sjsDefinedByDefault",
      /* for ScalablyTyped */
      resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped"),
    )

lazy val bundlerSettings: Project => Project =
  _.enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      /* Specify current versions and modes */
      version in startWebpackDevServer := "3.1.10",
      version in webpack := "4.26.1",
      webpackExtraArgs in (Compile, fastOptJS) += "--mode=development",
      webpackExtraArgs in (Compile, fullOptJS) += "--mode=production",
      webpackDevServerExtraArgs in (Compile, fastOptJS) += "--mode=development",
      webpackDevServerExtraArgs in (Compile, fullOptJS) += "--mode=production",
      useYarn := true,
    )

lazy val browserProject: Project => Project =
  _.settings(
    webpackResources :=
      webpackResources.value +++
        PathFinder(Seq(baseDirectory.value / "assets" / "index.html")) ** "*.*",
    start := {
      (startWebpackDevServer in (Compile, fastOptJS)).value
      /* I'm sure there are better ways to do this, but it was *not* easy to discover */
      val indexFrom = new File(baseDirectory.value, "assets/index.html")
      val indexTo   = new File((npmUpdate in (Compile, fastOptJS)).value, "index.html").toPath
      Files.copy(indexFrom.toPath, indexTo, REPLACE_EXISTING)
    },
    dist := {
      val artifacts      = (webpack in (Compile, fullOptJS)).value
      val artifactFolder = (crossTarget in (Compile, fullOptJS)).value
      val distFolder     = (baseDirectory in ThisBuild).value / "docs" / moduleName.value

      distFolder.mkdirs()
      artifacts.foreach { artifact =>
        val target = artifact.data.relativeTo(artifactFolder) match {
          case None          => distFolder / artifact.data.name
          case Some(relFile) => distFolder / relFile.toString
        }

        Files.copy(artifact.data.toPath, target.toPath, REPLACE_EXISTING)
      }

      val utf8      = Charset.forName("UTF-8")
      val indexFrom = new File(baseDirectory.value, "assets/index.html").toPath
      val indexTo   = (distFolder / "index.html").toPath

      val indexContent = {
        import collection.JavaConverters._
        Files
          .readAllLines(indexFrom, utf8)
          .asScala
          .map(_.replaceAllLiterally("-fastopt-", "-opt-"))
          .mkString("\n")
      }

      Files.write(indexTo, indexContent.getBytes(utf8))
    }
  )

val nodeProject: Project => Project =
  _.settings(
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv,
  )
