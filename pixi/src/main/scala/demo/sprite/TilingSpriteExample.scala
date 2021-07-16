package demo.sprite

import demo.assets.P2Image
import demo.pixi.PIXIExample
import typings.pixiJs.mod.{Application, Texture, TilingSprite}
import demo.monkeypatching.PIXIPatching.*

case object TilingSpriteExample extends PIXIExample:

  val name: String = "Tiling Sprite"

  val pixiUrl: String = "https://pixijs.io/examples/#/sprite/tiling-sprite.js"

  def newApplication(): Application =

    val app = new Application()

    // create a texture from an image path
    val texture = Texture.from(P2Image)

    /* create a tiling sprite ...
     * requires a texture, a width and a height
     * in WebGL the image size should preferably be a power of two
     */
    val tilingSprite =
      new TilingSprite(texture, app.screen.width, app.screen.height)
    app.stage.addChild(tilingSprite)

    var count: Double = 0

    app.ticker.add { () =>

      count += 0.005

      tilingSprite.tileScale.x = 2 + Math.sin(count)
      tilingSprite.tileScale.y = 2 + Math.cos(count)

      tilingSprite.tilePosition.x += 1
      tilingSprite.tilePosition.y += 1

    }

    app
  end newApplication
end TilingSpriteExample
