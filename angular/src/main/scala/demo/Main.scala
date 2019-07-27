package demo

import typings.atAngularCore.atAngularCoreMod.{^ => Core}
import typings.atAngularPlatformDashBrowserDashDynamic.atAngularPlatformDashBrowserDashDynamicMod.^.platformBrowserDynamic
import typings.std.EventListenerObject
import typings.std.^.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Main {

  /* somewhat awkward, this is resolved from angular/target/scala-2.12/scalajs-bundler/main */
  @js.native @JSImport("../../../../src/main/css/styling.css", JSImport.Namespace)
  object Style extends js.Object

  /* rejoice this line, it took longer to write than the rest of the demo >: */
  @js.native @JSImport("core-js/client/shim.min.js", JSImport.Namespace)
  object coreJsCustomRequire extends js.Object

  def main(args: Array[String]): Unit = {
    /* touch to require */
    coreJsCustomRequire
    typings.tslib.tslibRequire
    typings.zoneDotJs.zoneDotJsRequire
    Style

    /**
      * Waiting the DOM to be loaded otherwise the CSS selectors won't exist.
      */
    document.addEventListener(
      "DOMContentLoaded",
      EventListenerObject { _ =>
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
