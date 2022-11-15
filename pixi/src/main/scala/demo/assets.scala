package demo

import org.scalablytyped.runtime.TopLevel

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object assets:
  object pixiFilters:
    @js.native
    @JSImport("./assets/pixi-filters/displacement_map_repeat.jpg", JSImport.Default)
    object DisplacementMapRepeat extends TopLevel[String]

    @js.native
    @JSImport("./assets/pixi-filters/flag.png", JSImport.Default)
    object FlagImage extends TopLevel[String]

  @js.native
  @JSImport("./assets/bg_grass.jpg", JSImport.Default)
  object BackgroundGrass extends TopLevel[String]

  @js.native
  @JSImport("./assets/bg_scene_rotate.jpg", JSImport.Default)
  object BackgroundSceneRotate extends TopLevel[String]

  @js.native
  @JSImport("./assets/bunny.png", JSImport.Default)
  object BunnyImage extends TopLevel[String]

  @js.native
  @JSImport("./assets/eggHead.png", JSImport.Default)
  object EggHeadImage extends TopLevel[String]

  @js.native
  @JSImport("./assets/p2.jpeg", JSImport.Default)
  object P2Image extends TopLevel[String]

  @js.native
  @JSImport("./assets/star.png", JSImport.Default)
  object StarImage extends TopLevel[String]

  @js.native
  @JSImport("./assets/video.mp4", JSImport.Default)
  object TheVideo extends TopLevel[String]

  @js.native
  @JSImport("./assets/trail.png", JSImport.Default)
  object TrailImage extends TopLevel[String]
end assets
