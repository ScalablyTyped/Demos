package demo.pixi

import demo.demosadvanced.*
import demo.demosbasic.*
import demo.filtersbasic.DisplacementMapFlag
import demo.graphics.Simple
import demo.interaction.Click
import demo.meshandshaders.Uniform
import demo.pluginfilters.Outline
import demo.sprite.{TilingSpriteExample, VideoExample}
import org.scalajs.dom.html
import typings.pixiJs.anon.BaseTexture as StageOptions
import typings.pixiJs.mod.Application

trait PIXIExample:

  val name: String

  val pixiUrl: String

  def githubUrl(pkg: String): String =
    s"""https://raw.githubusercontent.com/ScalablyTyped/Demos/master/pixi/src/main/scala/demo/$pkg/$toString.scala"""

  private var pixiApp: Option[Application] = None

  private def stop(): Unit =
    pixiApp match
      case Some(app) =>
        app.destroy(
          removeView = true,
          stageOptions = StageOptions().setBaseTexture(true)
        )
      case None =>
    end match
    pixiApp = None
  end stop

  protected def newApplication(): Application

  def run(pkg: String): Unit =
    PIXIExample.stopAll()
    pixiApp = Some(newApplication())
    ExampleSelector.changeCanvas(this)
    ExampleSelector.loadAndDisplayCode(this, pkg)
  end run

  def canvas: html.Canvas = pixiApp.get.view
end PIXIExample

object PIXIExample:

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
end PIXIExample
