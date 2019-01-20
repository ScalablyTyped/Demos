package demo

import typings.jqueryLib.{JQuery, JQueryEventObject, jqueryMod => $}
import typings.jqueryuiLib.jqueryuiLibRequire
import typings.stdLib.^.document
import typings.stdLib.{Element, HTMLButtonElement, HTMLLabelElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object JQueryDemo {
  def main(args: Array[String]): Unit = {
    // trigger loading of global library and CSS
    jqueryuiLibRequire
    Knowledge.JqueryUiCss

    Knowledge.asOption(document.getElementById("container")) match {
      case None =>
        sys.error("Could not find #container")

      case Some(container) =>
        val $container: JQuery[Element] = $(container)

        var counter = 1
        val renderLabel: js.Function1[ /* event */ JQueryEventObject, scala.Unit] = eo => {
          counter += 1
          $[HTMLLabelElement]("#label").text(s"Value is $counter")
        }

        $[HTMLButtonElement]("#button")
          .text("bumpit")
          .on("click", renderLabel)

        Knowledge.isJQueryUi($("#accordion")).accordion()
    }
  }
}

object Knowledge {
  @JSImport("jqueryui/jquery-ui.css", JSImport.Namespace)
  @js.native
  object JqueryUiCss extends js.Object

  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])

  /* fake interface augmentation. This is done automatically in typescript. */
  def isJQueryUi[T](x: JQuery[T]): typings.jqueryuiLib.JQuery =
    x.asInstanceOf[typings.jqueryuiLib.JQuery]
}
