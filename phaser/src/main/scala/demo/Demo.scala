package demo

import org.scalablytyped.runtime.TopLevel
import typings.phaser.Phaser.Types.Animations.{Animation, GenerateFrameNames}
import typings.phaser.Phaser.Types.Core.GameConfig
import typings.phaser.Phaser.Types.GameObjects.GameObjectConfig
import typings.phaser.Phaser.Types.GameObjects.Sprite.SpriteConfig
import typings.phaser.Phaser.Types.Scenes.CreateSceneFromObjectConfig
import typings.phaser.phaserMod.{Game, Scene}
import typings.phaser.{phaserMod => Phaser}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.util.Random

object Demo {
  @JSImport("../../../../src/main/resources/gems.png", JSImport.Namespace)
  @js.native
  object GemsPng extends TopLevel[String]

  /* file renamed from .json because of https://github.com/webpack/webpack/issues/6586 */
  @JSImport("!file-loader!../../../../src/main/resources/gems.jsn", JSImport.Namespace)
  @js.native
  object GemsJson extends TopLevel[String]

  val preload: js.ThisFunction0[Scene, Unit] =
    _.load.atlas("gems", GemsPng, GemsJson)

  val create: js.ThisFunction1[Scene, js.Object, Unit] = (scene, data) => {
    //  Define the animations first
    scene.anims.create(
      Animation(
        key = "ruby",
        frames = scene.anims.generateFrameNames("gems", GenerateFrameNames(prefix = "ruby_", end = 6, zeroPad = 4)),
        repeat = -1
      )
    );
    scene.anims.create(
      Animation(
        key = "square",
        frames = scene.anims.generateFrameNames("gems", GenerateFrameNames(prefix = "square_", end = 14, zeroPad = 4)),
        repeat = -1
      )
    );

    //  Make 16 sprites using the config above
    0 to 16 foreach { _ =>
      //  The Sprite config
      val config = new SpriteConfig {
        key = "gems"
        x = Random.nextInt(800): Double
        y = Random.nextInt(300): Double
        scale = Random.between(0.5f, 1.5f): Double
        /* add a member describing the annotation which is completely unchecked in typescript */
        var anims = "ruby"
      }
      scene.make.sprite(config)
    }

    //  Make 16 sprites using the config above
    0 to 16 foreach { _ =>
      //  A more complex animation config object.
      //  This time with a call to delayedPlay that's a function.
      val config = new SpriteConfig {
        key = "gems"
        x = Random.nextInt(800): Double
        y = Random.nextInt(300) + 300: Double
        scale = Random.between(0.5f, 1.5f): Double
        val anims =
          new js.Object {
            val key = "square"
            val repeat = -1
            val repeatDelay = 1 + Random.nextInt(3)
            val delayedPlay: js.Function0[Double] = () => Math.random() * 6
          }
      }
      scene.make.sprite(config);
    }
  }

  val config = GameConfig(
    `type` = Phaser.AUTO: Double,
    parent = "phaser-example",
    width = 800,
    height = 600,
    scene = createScene(preload = preload, create = create)
  )

  def main(args: Array[String]): Unit =
    new Game(config)
}

/* note that we could probably have refactored this whole thing to use classes to not avoid this.
* To keep in line with the example I changed CreateSceneFromObjectConfig to take `ThisFunction`s.
*
* The only reason it's not already there is that it's not specified in typescript,
*  leaving it completely unchecked.
*
* Unfortunately `js.ThisFunctionN` is not a subtype of the corresponding `js.FunctionN`
 */
object createScene {
  @scala.inline
  def apply(
      create: /* data */ js.ThisFunction1[Scene, js.Object, Unit] = null,
      extend: js.Any = null,
      extendDotdata: js.Any = null,
      init: /* data */ js.ThisFunction1[Scene, js.Object, Unit] = null,
      preload: js.ThisFunction0[Scene, Unit] = null,
      update: js.ThisFunction0[Scene, Unit] = null
  ): CreateSceneFromObjectConfig = {
    val __obj = js.Dynamic.literal()
    if (create != null) __obj.updateDynamic("create")(create)
    if (extend != null) __obj.updateDynamic("extend")(extend.asInstanceOf[js.Any])
    if (extendDotdata != null) __obj.updateDynamic("extend.data")(extendDotdata.asInstanceOf[js.Any])
    if (init != null) __obj.updateDynamic("init")(init)
    if (preload != null) __obj.updateDynamic("preload")(preload)
    if (update != null) __obj.updateDynamic("update")(update.asInstanceOf[js.Any])
    __obj.asInstanceOf[CreateSceneFromObjectConfig]
  }
}
