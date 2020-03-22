package demo.pixi

import demo.demosbasic._
import demo.demosadvanced._
import demo.filtersbasic.DisplacementMapFlag
import demo.graphics.Simple
import demo.interaction.Click
import demo.meshandshaders.Uniform
import demo.pluginfilters.Outline
import demo.sprite.{TilingSpriteExample, VideoExample}
import org.scalajs.dom.html
import typings.pixiJs.mod.Application
import typings.pixiJs.{AnonBaseTexture => StageOptions}

trait PIXIExample {

  val name: String

  val pixiUrl: String

  def githubUrl(pkg: String): String =
    s"""https://raw.githubusercontent.com/ScalablyTyped/Demos/master/pixi/src/main/scala/demo/$pkg/$toString.scala"""

  private var pixiApp: Option[Application] = None

  private def stop(): Unit = {
    pixiApp match {
      case Some(app) =>
        app.destroy(
          removeView   = true,
          stageOptions = StageOptions(baseTexture = true)
        )
      case None =>
    }
    pixiApp = None
  }

  protected def newApplication(): Application

  def run(pkg: String): Unit = {
    PIXIExample.stopAll()
    pixiApp = Some(newApplication())
    ExampleSelector.changeCanvas(this)
    ExampleSelector.loadAndDisplayCode(this, pkg)
  }

  def canvas: html.Canvas = pixiApp.get.view

}

object PIXIExample {

  val allExamples: Map[String, List[PIXIExample]] = Map(
    "DEMOS-BASIC" -> List(Basics, Containers, ContainerPivot, Tinting),
    "DEMOS-ADVANCED" -> List(MouseTrail, StarWarp),
    "SPRITE" -> List(TilingSpriteExample, VideoExample),
    "GRAPHICS" -> List(Simple),
    "INTERACTION" -> List(Click),
    "MESH-AND-SHADERS" -> List(Uniform),
    "FILTERS-BASIC" -> List(DisplacementMapFlag),
    "PLUGIN-FILTERS" -> List(Outline)
  )

  def stopAll(): Unit =
    allExamples.values.flatten.foreach(_.stop())

}
