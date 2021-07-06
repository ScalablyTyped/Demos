package demo.demosbasic

import demo.assets.BunnyImage
import demo.pixi.PIXIExample
import demo.monkeypatching.PIXIPatching.*
import typings.pixiJs.anon.Antialias as ApplicationOptions
import typings.pixiJs.mod.{Application, Sprite, Texture}

case object Basics extends PIXIExample:

  val name: String = "Basics"

  val pixiUrl: String = "https://pixijs.io/examples/#/sprite/basic.js"

  def newApplication(): Application =
    val app = new Application(ApplicationOptions().setBackgroundColor(0x1099bb))

    val texture = Texture.from(BunnyImage)

    // create a new Sprite from an image path
    val bunny: Sprite = new Sprite(texture)

    // center the sprite's anchor point
    bunny.anchor.set(0.5)

    // move the sprite to the center of the screen
    bunny.x = app.screen.width / 2
    bunny.y = app.screen.height / 2

    app.stage.addChild(bunny)

    val ticker = (delta: Double) =>
      // just for fun, let's rotate mr rabbit a little
      // delta is 1 if running at 100% performance
      // creates frame-independent transformation
      bunny.rotation += 0.1 * delta

    // Listen for animate update
    app.ticker.add(ticker)

    app
  end newApplication
end Basics
