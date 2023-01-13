package demo.interaction

import demo.assets.BunnyImage
import demo.pixi.PIXIExample
import typings.pixiJs.anon.Antialias as ApplicationOptions
import typings.pixiJs.mod.*

import scala.scalajs.js

case object Click extends PIXIExample:

  val name: String = "Click"

  val pixiUrl: String = "https://pixijs.io/examples/#/interaction/click.js"

  def newApplication(): Application =

    val app = new Application(ApplicationOptions().setBackgroundColor(0x1099bb))

    // Scale mode for all textures, will retain pixelation
    settings.SCALE_MODE = SCALE_MODES.NEAREST

    val texture = Texture.from(BunnyImage)
    val sprite  = new Sprite(texture)

    // Set the initial position
    sprite.anchor.set(0.5)
    sprite.x = app.screen.width / 2
    sprite.y = app.screen.height / 2

    // Opt-in to interactivity
    sprite.interactive = true

    // Shows hand cursor
    sprite.buttonMode = true

    // Pointers normalize touch and mouse
    sprite.on("pointerdown", (_: js.Any) => onClick())

    // Alternatively, use the mouse & touch events:
    // sprite.on('click', onClick); // mouse-only
    // sprite.on('tap', onClick); // touch-only

    app.stage.addChild(sprite)

    def onClick(): Unit =
      sprite.scale.x *= 1.25
      sprite.scale.y *= 1.25

    app
  end newApplication
end Click
