package demo.demosbasic

import demo.assets.BunnyImage
import demo.monkeypatching.PIXIPatching.*
import demo.pixi.PIXIExample
import typings.pixiJs.anon.Antialias as ApplicationOptions
import typings.pixiJs.mod.{Application, Container, Sprite, Texture}

case object ContainerPivot extends PIXIExample:

  val name: String = "Container Pivot"

  val pixiUrl: String = "https://pixijs.io/examples/#/demos-basic/container.js"

  def newApplication(): Application =
    val app = new Application(ApplicationOptions().setBackgroundColor(0x1099bb))

    val container = new Container()

    app.stage.addChild(container)

    // Create a new texture
    val texture = Texture.from(BunnyImage)

    // Create a 5x5 grid of bunnies
    for i <- 0 until 25 do
      val bunny = new Sprite(texture)
      bunny.anchor.set(0.5)
      bunny.x = (i % 5) * 40
      bunny.y = i / 5 * 40
      container.addChild(bunny)

    // Move container to the center
    container.x = app.screen.width / 2
    container.y = app.screen.height / 2

    // Center bunny sprite in local container coordinates
    container.pivot.x = container.width / 2
    container.pivot.y = container.height / 2

    val tickerF = (delta: Double) =>
      // rotate the container!
      // use delta to create frame-independent transform
      container.rotation -= 0.01 * delta

    // Listen for animate update
    app.ticker.add(tickerF)

    app
  end newApplication
end ContainerPivot
