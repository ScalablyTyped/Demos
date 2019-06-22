package demo

import org.scalajs.dom
import slinky.web.ReactDOM

object Main {
  def main(args: Array[String]): Unit = {
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    ReactDOM.render(App.component(()), container)
  }
}
