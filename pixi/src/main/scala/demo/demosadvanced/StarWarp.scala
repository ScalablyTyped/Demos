package demo.demosadvanced

import demo.assets.StarImage
import demo.pixi.PIXIExample
import typings.pixiJs.anon.Antialias as ApplicationOptions

import typings.pixiJs.mod.{Application, Sprite, Texture}

import demo.monkeypatching.PIXIPatching.*

import scala.scalajs.js.timers.setInterval

case object StarWarp extends PIXIExample:

  val name: String = "Star warp"

  val pixiUrl: String =
    "https://pixijs.io/examples/#/demos-advanced/star-warp.js"

  def newApplication(): Application =
    val app = new Application(ApplicationOptions().setBackgroundColor(0))

    //Get the texture for rope.
    val starTexture = Texture.from(StarImage)

    val starAmount   = 1000
    var cameraZ      = 0.0
    val fov          = 20.0
    val baseSpeed    = 0.025
    var speed        = 0.0
    var warpSpeed    = 0.0
    val starStretch  = 5.0
    val starBaseSize = 0.05

    final class Star:
      val sprite: Sprite = new Sprite(starTexture)
      var z:      Double = 0
      var x:      Double = 0
      var y:      Double = 0

    def randomizeStar(star: Star, initial: Boolean): Unit =
      star.z =
        if initial then Math.random() * 2000
        else cameraZ + Math.random() * 1000 + 2000

      //Calculate star positions with radial random coordinate so no star hits the camera.
      val deg      = Math.random() * Math.PI * 2
      val distance = Math.random() * 50 + 1
      star.x = Math.cos(deg) * distance
      star.y = Math.sin(deg) * distance
    end randomizeStar

    //Create the stars
    val stars = for (_ <- 0 until starAmount) yield
      val star = new Star
      star.sprite.anchor.x = 0.5
      star.sprite.anchor.y = 0.7
      randomizeStar(star, initial = true)
      app.stage.addChild(star.sprite)
      star

    //Change flight speed every 5 seconds
    setInterval(5000) {
      warpSpeed = 1 - warpSpeed
    }

    def ticker(delta: Double): Unit =
      //Simple easing. This should be changed to proper easing function when used for real.
      speed += (warpSpeed - speed) / 20
      cameraZ += delta * 10 * (speed + baseSpeed)
      for star <- stars do
        if star.z < cameraZ then randomizeStar(star, initial = false)

        //Map star 3d position to 2d with really simple projection
        val z = star.z - cameraZ
        star.sprite.x = star.x * (fov / z) * app.renderer.screen.width + app.renderer.screen.width / 2
        star.sprite.y = star.y * (fov / z) * app.renderer.screen.width + app.renderer.screen.height / 2

        //Calculate star scale & rotation.
        val dxCenter = star.sprite.x - app.renderer.screen.width / 2
        val dyCenter = star.sprite.y - app.renderer.screen.height / 2
        val distanceCenter =
          Math.sqrt(dxCenter * dxCenter + dyCenter + dyCenter)
        val distanceScale = Math.max(0, (2000 - z) / 2000)
        star.sprite.scale.x = distanceScale * starBaseSize
        //Star is looking towards center so that y axis is towards center.
        //Scale the star depending on how fast we are moving, what the stretchfactor is and depending on how far away
        // it is from the center.
        star.sprite.scale.y = distanceScale * starBaseSize +
          distanceScale * speed * starStretch * distanceCenter / app.renderer.screen.width
        star.sprite.rotation = Math.atan2(dyCenter, dxCenter) + Math.PI / 2
      end for
    end ticker

    // Listen for animate update
    app.ticker.add(ticker(_: Double))

    app
  end newApplication
end StarWarp
