package demo.demosbasic

import demo.assets.EggHeadImage
import demo.pixi.PIXIExample
import typings.pixiJs.mod.{Application, Rectangle, Sprite, Texture}

import demo.monkeypatching.PIXIPatching.*

case object Tinting extends PIXIExample:

  val name: String = "Tinting"

  val pixiUrl: String = "https://pixijs.io/examples/#/demos-basic/tinting.js"

  def newApplication(): Application =
    val app = new Application()

    val totalDudes = 20

    final class Dude:

      // create a new Sprite that uses the image name that we just generated as its source
      val dude: Sprite = new Sprite(Texture.from(EggHeadImage))

      // set the anchor point so the texture is centerd on the sprite
      dude.anchor.set(0.5)

      // set a random scale for the dude - no point them all being the same size!
      dude.scale.set(0.8 + Math.random() * 0.3)

      // finally lets set the dude to be at a random position..
      dude.x = Math.random() * app.screen.width
      dude.y = Math.random() * app.screen.height

      dude.tint = Math.random() * 0xffffff

      // create some extra properties that will control movement :
      // create a random direction in radians. This is a number between 0 and PI*2 which is the equivalent of 0 - 360 degrees
      var direction: Double = Math.random() * Math.PI * 2

      // this number will be used to modify the direction of the dude over time
      var turningSpeed: Double = Math.random() - 0.8

      // create a random speed for the dude between 0 - 2
      var speed: Double = 2 + Math.random() * 2

      app.stage.addChild(dude)
    end Dude

    val aliens = (0 until totalDudes).map(_ => new Dude)

    // create a bounding box for the little dudes
    val dudeBoundsPadding = 100
    val dudeBounds = new Rectangle(
      -dudeBoundsPadding,
      -dudeBoundsPadding,
      app.screen.width + dudeBoundsPadding * 2,
      app.screen.height + dudeBoundsPadding * 2
    )

    app.ticker.add { (_: Double) =>
      // iterate through the dudes and update their position
      for dude <- aliens do

        dude.direction += dude.turningSpeed * 0.01
        dude.dude.x += Math.sin(dude.direction) * dude.speed
        dude.dude.y += Math.cos(dude.direction) * dude.speed
        dude.dude.rotation = -dude.direction - Math.PI / 2

        // wrap the dudes by testing their bounds...
        if dude.dude.x < dudeBounds.x then dude.dude.x += dudeBounds.width
        else if dude.dude.x > dudeBounds.x + dudeBounds.width then dude.dude.x -= dudeBounds.width

        if dude.dude.y < dudeBounds.y then dude.dude.y += dudeBounds.height
        else if dude.dude.y > dudeBounds.y + dudeBounds.height then dude.dude.y -= dudeBounds.height

    }

    app
  end newApplication
end Tinting
