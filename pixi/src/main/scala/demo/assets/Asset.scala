package demo.assets

import scala.language.implicitConversions
import scala.scalajs.js

/**
  * An [[Asset]] is used to create the the asset representation of static files such
  * as images and videos.
  * There is an implicit conversion to String so that you can easily use, e.g., the
  * method `Texture.from` using the definition of the Asset.
  */
trait Asset extends js.Object

object Asset {

  implicit def assetAsString(asset: Asset): String = asset.asInstanceOf[String]

}
