package demo.filtersbasic

import demo.assets.pixiFilters.{DisplacementMapRepeat, FlagImage}
import demo.pixi.PIXIExample
import typings.pixiJs.mod.{filters, Application, Container, Sprite, WRAP_MODES}
import demo.monkeypatching.PIXIPatching.*

import scala.scalajs.js

case object DisplacementMapFlag extends PIXIExample:
  val name: String = "Displacement Map - Flag"
  val pixiUrl: String =
    "https://pixijs.io/examples/#/filters-basic/displacement-map-flag.js"

  protected def newApplication(): Application =

    val app = new Application()

    app.stage.interactive = true

    val container = new Container
    app.stage.addChild(container)

    val flag = Sprite.from(FlagImage)
    container.addChild(flag)
    flag.x = 100
    flag.y = 100

    val displacementSprite = Sprite.from(DisplacementMapRepeat)
    // Make sure the sprite is wrapping.
    displacementSprite.texture.baseTexture.wrapMode = WRAP_MODES.REPEAT
    val displacementFilter =
      new filters.DisplacementFilter(displacementSprite)
    displacementFilter.padding = 10

    displacementSprite.position = flag.position

    app.stage.addChild(displacementSprite)

    flag.filters = js.Array(displacementFilter)

    displacementFilter.scale.x = 30
    displacementFilter.scale.y = 60

    app.ticker.add { () =>
      // Offset the sprite position to make vFilterCoord update to larger value. Repeat wrapping makes sure there's still pixels on the coordinates.
      displacementSprite.x += 1
      // Reset x to 0 when it's over width to keep values from going to very huge numbers.
      if displacementSprite.x > displacementSprite.width then displacementSprite.x = 0

    }

    app
  end newApplication
end DisplacementMapFlag
