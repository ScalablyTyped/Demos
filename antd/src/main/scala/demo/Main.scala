package demo

import typings.reactDashDomLib.reactDashDomMod.^.render
import typings.reactLib.dsl._
import typings.stdLib.^.window

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Main {
  def main(argv: Array[String]): Unit = {
    IndexCSS // touch to load
    render(App.Component.noprops(), window.document.body)
  }
}

@JSImport("../../../../src/main/resources/index.css", JSImport.Namespace)
@js.native
object IndexCSS extends js.Object
