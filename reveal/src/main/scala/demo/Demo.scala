package demo

import org.scalajs.dom
import typings.highlightJs.mod.initHighlightingOnLoad
import typings.reveal.{RevealOptions, RevealStatic}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Demo {
  def main(args: Array[String]): Unit = {
    // Touch to load
    Includes.HighlightingCss
    Includes.WhiteThemeCss
    Includes.RevealCss
    Includes.Reveal
    Includes.ZoomJs

    /* initialize highlight.js */
    initHighlightingOnLoad()

    /* render talk before we initialize Reveal */
    MyTalk.Talk().renderIntoDOM(dom.document.body)

    Includes.Reveal.initialize(
      RevealOptions(
        width      = "80%",
        height     = "100%",
        controls   = false,
        progress   = false,
        history    = false,
        center     = true,
        transition = "none"
      )
    )
  }
}

object Includes {

  /* customize import and use as module, even though the typings originally were global */
  @JSImport("reveal.js/js/reveal.js", JSImport.Namespace)
  @js.native
  object Reveal extends RevealStatic

  @JSImport("reveal.js/plugin/zoom-js/zoom.js", JSImport.Namespace)
  @js.native
  object ZoomJs extends RevealStatic

  @JSImport("reveal.js/lib/css/zenburn.css", JSImport.Namespace)
  @js.native
  object HighlightingCss extends js.Object

  @JSImport("reveal.js/css/theme/white.css", JSImport.Namespace)
  @js.native
  object WhiteThemeCss extends js.Object

  @JSImport("reveal.js/css/reveal.css", JSImport.Namespace)
  @js.native
  object RevealCss extends js.Object
}
