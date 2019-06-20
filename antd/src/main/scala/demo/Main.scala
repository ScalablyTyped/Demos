package demo

import typings.reactDashDomLib.reactDashDomMod.^.render
import typings.reactLib.dsl._
import typings.stdLib.^.window

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Main {
  def main(argv: Array[String]): Unit = {
    CSS // touch to load
    render(App.Component.noprops(), window.document.body)
  }
}

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object CSS extends js.Any
