package demo.demosbasic

import demo.assets.BunnyImage
import demo.pixi.PIXIExample
import typings.pixiJs.anon.Antialias as ApplicationOptions
import typings.pixiJs.mod.{Application, Container, Sprite, Texture}

case object Containers extends PIXIExample:

  val name: String = "Container"

  val pixiUrl: String = "https://pixijs.io/examples/#/basics/container.js"

  def newApplication(): Application =

    val app = new Application(ApplicationOptions().setBackgroundColor(0x1099bb))

    val container = new Container()

    app.stage.addChild(container)

    val texture = Texture.from(BunnyImage)

    // Create a 5x5 grid of bunnies
    for i <- 0 until 25 do
      val bunny = new Sprite(texture)
      bunny.anchor.set(0.5)
      bunny.x = (i % 5) * 40
      bunny.y = i / 5 * 40
      container.addChild(bunny)

    // Center on the screen
    container.x = (app.screen.width - container.width) / 2
    container.y = (app.screen.height - container.height) / 2

    app
  end newApplication
end Containers
