package demo.pluginfilters

import demo.assets.BunnyImage
import demo.pixi.PIXIExample
import typings.pixiFilterGlow.mod.{GlowFilter, GlowFilterOptions}
import typings.pixiFilterOutline.mod.OutlineFilter
import typings.pixiJs.mod.{Application, Sprite, Texture}

import scala.scalajs.js

case object Outline extends PIXIExample:
  val name:    String = "Outline"
  val pixiUrl: String = "https://pixijs.io/examples/#/plugin-filters/outline.js"

  protected def newApplication(): Application =
    val app = new Application()

    app.stage.position.set(400, 300)

    val outlineFilterBlue = new OutlineFilter(2, 0x99ff99)
    val outlineFilterRed = new GlowFilter(
      GlowFilterOptions()
        .setOuterStrength(15)
        .setDistance(2)
        .setInnerStrength(1)
        .setColor(0xff9999)
        .setQuality(0.5)
    )

    def filterOn(sprite: Sprite): Unit =
      sprite.filters = js.Array(outlineFilterRed)

    def filterOff(sprite: Sprite): Unit =
      sprite.filters = js.Array(outlineFilterBlue)

    val texture = Texture.from(BunnyImage)

    for _ <- 0 until 20 do
      val bunny = new Sprite(texture)
      bunny.interactive = true
      bunny.position.set((Math.random() * 2 - 1) * 300, (Math.random() * 2 - 1) * 200)
      bunny.scale.x = (Math.random() * 3) + 1
      bunny
        .on("pointerover", () => filterOn(bunny))
        .on("pointerout", () => filterOff(bunny))
      filterOff(bunny)
      app.stage.addChild(bunny)
    end for

    app
  end newApplication
end Outline
