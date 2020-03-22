package demo

import org.scalablytyped.runtime.TopLevel

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object assets {
  object pixiFilters {
    @js.native
    @JSImport("assets/assets/pixi-filters/displacement_map_repeat.jpg", JSImport.Namespace)
    object DisplacementMapRepeat extends TopLevel[String]

    @js.native
    @JSImport("assets/assets/pixi-filters/flag.png", JSImport.Namespace)
    object FlagImage extends TopLevel[String]
  }

  @js.native
  @JSImport("assets/assets/bg_grass.jpg", JSImport.Namespace)
  object BackgroundGrass extends TopLevel[String]

  @js.native
  @JSImport("assets/assets/bg_scene_rotate.jpg", JSImport.Namespace)
  object BackgroundSceneRotate extends TopLevel[String]

  @js.native
  @JSImport("assets/assets/bunny.png", JSImport.Namespace)
  object BunnyImage extends TopLevel[String]

  @js.native
  @JSImport("assets/assets/eggHead.png", JSImport.Namespace)
  object EggHeadImage extends TopLevel[String]

  @js.native
  @JSImport("assets/assets/p2.jpeg", JSImport.Namespace)
  object P2Image extends TopLevel[String]

  @js.native
  @JSImport("assets/assets/star.png", JSImport.Namespace)
  object StarImage extends TopLevel[String]

  @js.native
  @JSImport("assets/assets/video.mp4", JSImport.Namespace)
  object TheVideo extends TopLevel[String]

  @js.native
  @JSImport("assets/assets/trail.png", JSImport.Namespace)
  object TrailImage extends TopLevel[String]
}
