package demo

import org.scalajs.dom.{Event, document}
import typings.angularCore.{mod => Core}
import typings.angularPlatformBrowserDynamic.mod.platformBrowserDynamic

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Main {

  @js.native @JSImport("assets/styling.css", JSImport.Namespace)
  object Style extends js.Object

  /* rejoice this line, it took longer to write than the rest of the demo >: */
  @js.native @JSImport("core-js/client/shim.min.js", JSImport.Namespace)
  object coreJsCustomRequire extends js.Object

  def main(args: Array[String]): Unit = {
    /* touch to require */
    coreJsCustomRequire
    typings.tslib.tslibRequire
    typings.zoneJs.zoneJsRequire
    Style

    /**
      * Waiting the DOM to be loaded otherwise the CSS selectors won't exist.
      */
    document.addEventListener[Event](
      "DOMContentLoaded",
      (_: Event) => {
        if (!scala.scalajs.LinkingInfo.developmentMode) {

          /**
            * Syncing Angular production mode with Scala.js production mode.
            */
          Core.enableProdMode()
        }

        println("Charging App")
        platformBrowserDynamic().bootstrapModule(typeOf[AppModule])
      }
    )
  }
}
