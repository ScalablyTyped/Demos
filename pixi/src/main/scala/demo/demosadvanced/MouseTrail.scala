package demo.demosadvanced

import demo.assets.TrailImage
import demo.pixi.PIXIExample
import typings.pixiJs.mod.interaction.InteractionManager
import typings.pixiJs.mod.{Application, BLEND_MODES, Point, SimpleRope, Texture}
import demo.monkeypatching.PIXIPatching.*
import typings.pixiJs.anon.Antialias as ApplicationOptions

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

case object MouseTrail extends PIXIExample:
  
  val name: String = "Mouse trail"

  val pixiUrl: String =
    "https://pixijs.io/examples/#/demos-advanced/mouse-trail.js"

  def newApplication(): Application =
    val app = new Application(ApplicationOptions().setBackgroundColor(0x1099bb))

    //Get the texture for rope.
    val trailTexture = Texture.from(TrailImage)
    val historyX     = mutable.Queue[Double]()
    val historyY     = mutable.Queue[Double]()
    //historySize determines how long the trail will be.
    val historySize = 20
    //ropeSize determines how smooth the trail will be.
    val ropeSize = 100

    //Create history array.
    for _ <- 0 until historySize do
      historyX += 0
      historyY += 0

    //Create rope points.
    val points: Vector[typings.pixiJs.PIXI.Point] =
      (for (_ <- 0 until ropeSize) yield new Point(0, 0)).toVector // sadly js.Array is invariant

    //Create the rope
    val rope = new SimpleRope(trailTexture, points.toJSArray)

    //Set the blendmode
    rope.blendMode = BLEND_MODES.ADD

    app.stage.addChild(rope)

    def ticker(): Unit =
      // Read mouse points, this could be done also in mousemove/touchmove update.
      // For simplicity it is done here for now.
      // When implementing this properly, make sure to implement touchmove as interaction plugins mouse might not update
      // on certain devices.
      val mousePosition =
        app.renderer.plugins
          .asInstanceOf[js.Dynamic]
          .interaction
          .asInstanceOf[InteractionManager]
          .mouse
          .global

      //Update the mouse values to history
      historyX.dequeue()
      historyX += mousePosition.x
      historyY.dequeue()
      historyY += mousePosition.y
      //Update the points to correspond with history.
      for i <- 0 until ropeSize do
        val p = points(i)

        //Smooth the curve with cubic interpolation to prevent sharp edges.
        val ix =
          cubicInterpolation(
            historyX.toList,
            i.toDouble / ropeSize * historySize
          )
        val iy =
          cubicInterpolation(
            historyY.toList,
            i.toDouble / ropeSize * historySize
          )

        p.x = ix
        p.y = iy
      end for
    end ticker

    // Listen for animate update
    app.ticker.add(() => ticker())

    /** Cubic interpolation based on https://github.com/osuushi/Smooth.js
      */
    def clipInput[T](k: Int, arr: Seq[T]): T =
      arr(math.max(0, math.min(arr.size - 1, k)))

    def getTangent(k: Int, factor: Double, array: Seq[Double]): Double =
      factor * (clipInput(k + 1, array) - clipInput(k - 1, array)) / 2

    def cubicInterpolation(array: Seq[Double], t: Double, tangentFactor: Double = 1): Double =

      val k = Math.floor(t).toInt
      val m = Seq(
        getTangent(k, tangentFactor, array),
        getTangent(k + 1, tangentFactor, array)
      )
      val p  = Seq(clipInput(k, array), clipInput(k + 1, array))
      val u  = t - k
      val t2 = u * u
      val t3 = u * t2
      (2 * t3 - 3 * t2 + 1) * p.head + (t3 - 2 * t2 + u) * m.head + (-2 * t3 + 3 * t2) * p(
        1
      ) + (t3 - t2) * m(1)
    end cubicInterpolation

    app
  end newApplication
end MouseTrail
