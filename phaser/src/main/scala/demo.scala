package demo

import org.scalablytyped.runtime.{StringDictionary, TopLevel}
import typings.phaser.Phaser.Types.Animations.{Animation, GenerateFrameNames}
import typings.phaser.Phaser.Types.Core.GameConfig
import typings.phaser.Phaser.Types.GameObjects.GameObjectConfig
import typings.phaser.Phaser.Types.GameObjects.Sprite.SpriteConfig
import typings.phaser.Phaser.Types.Scenes.CreateSceneFromObjectConfig
import typings.phaser.phaserMod.{Game, Scene}
import typings.phaser.phaserMod as Phaser

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.util.Random

@JSImport("./gems.png", JSImport.Namespace)
@js.native
val GemsPng: String = js.native

/* file renamed from .json because of https://github.com/webpack/webpack/issues/6586 */
@JSImport("./gems.jsn", JSImport.Namespace)
@js.native
val GemsJson: String = js.native

val preload: js.ThisFunction0[Scene, Unit] =
  _.load.atlas("gems", GemsPng, GemsJson, js.undefined, js.undefined)

val create: js.ThisFunction1[Scene, js.Object, Unit] = (scene, data) =>
  //  Define the animations first
  scene.anims.create(
    Animation()
      .setKey("ruby")
      .setFrames(
        scene.anims.generateFrameNames("gems", GenerateFrameNames().setPrefix("ruby_").setEnd(6).setZeroPad(4))
      )
      .setRepeat(-1)
  );

  scene.anims.create(
    Animation()
      .setKey("square")
      .setFrames(
        scene.anims.generateFrameNames("gems", GenerateFrameNames().setPrefix("square_").setEnd(14).setZeroPad(4))
      )
      .setRepeat(-1)
  )

  //  Make 16 sprites using the config above
  0 to 16 foreach { _ =>
    //  The Sprite config
    val config = SpriteConfig()
      .setKey("gems")
      .setX(Random.nextInt(800))
      .setY(Random.nextInt(300))
      .setScale(Random.between(0.5, 1.5))
      /* add a member describing the annotation which is completely unchecked in typescript */
      .set("anims", "ruby")
    scene.make.sprite(config)
  }

  //  Make 16 sprites using the config above
  0 to 16 foreach { _ =>
    //  A more complex animation config object.
    //  This time with a call to delayedPlay that's a function.
    val config = SpriteConfig()
      .setKey("gems")
      .setX(Random.nextInt(800))
      .setY(Random.nextInt(300) + 300)
      .setScale(Random.between(0.5, 1.5))
      .set(
        "anims",
        StringDictionary[js.Any](
          "key" -> "square",
          "repeat" -> -1,
          "repeatDelay" -> (1 + Random.nextInt(3)),
          "delayedPlay" -> (() => Math.random() * 6)
        )
      )
    scene.make.sprite(config);
  }

val config = GameConfig()
  .setType(Phaser.AUTO: Double)
  .setParent("phaser-example")
  .setWidth(800)
  .setHeight(600)
  .setScene(createScene(preload = preload, create = create))

@main
def main: Unit =
  new Game(config)

/* note that we could probably have refactored this whole thing to use classes to not avoid this.
 * To keep in line with the example I changed CreateSceneFromObjectConfig to take `ThisFunction`s.
 *
 * The only reason it's not already there is that it's not specified in typescript,
 *  leaving it completely unchecked.
 *
 * Unfortunately `js.ThisFunctionN` is not a subtype of the corresponding `js.FunctionN`
 */
@scala.inline
def createScene(
    create:        /* data */ js.ThisFunction1[Scene, js.Object, Unit] = null,
    extend:        js.Any = null,
    extendDotdata: js.Any = null,
    init:          /* data */ js.ThisFunction1[Scene, js.Object, Unit] = null,
    preload:       js.ThisFunction0[Scene, Unit] = null,
    update:        js.ThisFunction0[Scene, Unit] = null
): CreateSceneFromObjectConfig =
  val __obj = js.Dynamic.literal()
  if create != null then __obj.updateDynamic("create")(create)
  if extend != null then __obj.updateDynamic("extend")(extend.asInstanceOf[js.Any])
  if extendDotdata != null then __obj.updateDynamic("extend.data")(extendDotdata.asInstanceOf[js.Any])
  if init != null then __obj.updateDynamic("init")(init)
  if preload != null then __obj.updateDynamic("preload")(preload)
  if update != null then __obj.updateDynamic("update")(update.asInstanceOf[js.Any])
  __obj.asInstanceOf[CreateSceneFromObjectConfig]
end createScene
