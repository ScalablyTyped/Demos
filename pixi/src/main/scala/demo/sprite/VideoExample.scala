package demo.sprite

import demo.assets.TheVideo
import demo.pixi.PIXIExample
import typings.pixiJs.anon.Antialias as ApplicationOptions
import typings.pixiJs.mod.{Application, Graphics, Sprite, Texture}

import scala.scalajs.js

case object VideoExample extends PIXIExample:

  val name: String = "Video"

  val pixiUrl: String = "https://pixijs.io/examples/#/sprite/video.js"

  override def newApplication(): Application =
    val app = new Application(ApplicationOptions().setTransparent(true))

    // Create play button that can be used to trigger the video
    val button = new Graphics()
      .beginFill(0x0, 0.5)
      .drawRoundedRect(0, 0, 100, 100, 10)
      .endFill()
      .beginFill(0xffffff)
      .moveTo(36, 30)
      .lineTo(36, 70)
      .lineTo(70, 50)

    // Position the button
    button.x = (app.screen.width - button.width) / 2
    button.y = (app.screen.height - button.height) / 2

    // Enable interactivity on the button
    button.interactive = true
    button.buttonMode = true

    // Add to the stage
    app.stage.addChild(button)

    // Listen for a click/tap event to start playing the video
    // this is useful for some mobile platforms. For example:
    // ios9 and under cannot render videos in PIXI without a
    // polyfill - https://github.com/bfred-it/iphone-inline-video
    // ios10 and above require a click/tap event to render videos
    // that contain audio in PIXI. Videos with no audio track do
    // not have this requirement
    button.on("pointertap", (_: js.Any) => onPlayVideo())

    def onPlayVideo() =

      // Don't need the button anymore
      button.destroy()

      // create a video texture from a path
      val texture = Texture.from(TheVideo)

      // create a new Sprite using the video texture (yes it's that easy)
      val videoSprite = new Sprite(texture)

      // Stetch the fullscreen
      videoSprite.width = app.screen.width
      videoSprite.height = app.screen.height

      app.stage.addChild(videoSprite)
    end onPlayVideo

    app
  end newApplication
end VideoExample
