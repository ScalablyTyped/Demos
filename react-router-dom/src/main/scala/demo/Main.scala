package demo

import typings.react.dsl._
import typings.reactDashDom.reactDashDomMod.render
import typings.std.document

import scala.scalajs.js.|

object Main {
  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])

  def main(args: Array[String]): Unit = {
    val container = asOption(document.getElementById("root")).getOrElse {
      val elem = document.createElement("div")
      elem.id = "root"
      document.body.appendChild(elem)
      elem
    }

    render(App.component.noprops(), container)
  }
}
