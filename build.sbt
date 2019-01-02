import java.nio.file.{Files, StandardCopyOption}

// Usage: `project d3`, then `dev` in sbt. Don't think we can scope these by project :/
addCommandAlias("dev", ";start;~fastOptJS::webpack")

val baseSettings: Project => Project =
  _.enablePlugins(ScalaJSPlugin)
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      scalaVersion := "2.12.7",
      organization := "org.scalablytyped.demo",
      version := "0.1-SNAPSHOT",
      emitSourceMaps := false,
      resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped"),
      scalaJSUseMainModuleInitializer := true,
      scalaJSModuleKind := ModuleKind.CommonJSModule,
      scalacOptions ++= ScalacOptions.flags,
      scalacOptions += "-P:scalajs:sjsDefinedByDefault",
      version in startWebpackDevServer := "3.1.10",
      version in webpack := "4.26.1",
      webpackExtraArgs in (Compile, fastOptJS) += "--mode=development",
      webpackExtraArgs in (Compile, fullOptJS) += "--mode=production",
      webpackDevServerExtraArgs in (Compile, fastOptJS) += "--mode=development",
      webpackDevServerExtraArgs in (Compile, fullOptJS) += "--mode=production",
      useYarn := true,
    )

lazy val start = TaskKey[Unit]("start")

val browserProject: Project => Project =
  _.settings(
    webpackResources :=
      webpackResources.value +++
        PathFinder(Seq(baseDirectory.value / "assets" / "index.html")) ** "*.*",
    start := {
      (startWebpackDevServer in (Compile, fastOptJS)).value
      val base   = baseDirectory.value
      val target = (npmUpdate in (Compile, fastOptJS)).value

      /* I'm sure there are better ways to do this, but it was *not* easy to discover */
      Files.copy(
        new File(base, "assets/index.html").toPath,
        new File(target, "index.html").toPath,
        StandardCopyOption.REPLACE_EXISTING
      )
    },
  )

val nodeProject: Project => Project =
  _.settings(
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv,
  )

val `react-mobx` =
  project
    .configure(baseSettings, browserProject)
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

val `react-slick` =
  project
    .configure(baseSettings, browserProject)
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

val `vue` =
  project
    .configure(baseSettings, browserProject)
    .settings(
      webpackDevServerPort := 8006,
      libraryDependencies ++= Seq(
        ScalablyTyped.V.vue,
      ),
      npmDependencies in Compile ++= Seq(
        "vue" -> "2.5.21",
      )
    )

val d3 = project
  .configure(baseSettings, browserProject)
  .settings(
    webpackDevServerPort := 8002,
    libraryDependencies ++= Seq(ScalablyTyped.D.d3),
    npmDependencies in Compile ++= Seq("d3" -> "5.5.0"),
  )

val jquery = project
  .configure(baseSettings, browserProject)
  .settings(
    webpackDevServerPort := 8003,
    libraryDependencies ++= Seq(ScalablyTyped.J.jquery, ScalablyTyped.J.jqueryui),
    npmDependencies in Compile ++= Seq("jquery" -> "3.3", "jqueryui" -> "1.11.1"),
  )

val `google-maps` = project
  .configure(baseSettings, browserProject)
  .settings(
    webpackDevServerPort := 8004,
    libraryDependencies ++= Seq(
      ScalablyTyped.G.googlemaps,
    ),
  )

val lodash =
  project
    .configure(baseSettings, nodeProject)
    .settings(
      libraryDependencies ++= Seq(ScalablyTyped.L.lodash),
      npmDependencies in Compile ++= Seq("lodash" -> "4.17.11")
    )

val `node-express` =
  project
    .configure(baseSettings, nodeProject)
    .settings(libraryDependencies ++= Seq(ScalablyTyped.E.express))

val typescript =
  project
    .configure(baseSettings, nodeProject)
    .settings(
      libraryDependencies ++= Seq(ScalablyTyped.N.node, ScalablyTyped.T.typescript),
      npmDependencies in Compile ++= Seq("typescript" -> "3.2.2")
    )
