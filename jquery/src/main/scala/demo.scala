import org.scalajs.dom.document
import org.scalajs.dom.html.{Button, Label}
import org.scalajs.dom.raw.Element
import typings.jquery.{JQuery, JQueryEventObject, mod as $}
import typings.jqueryui
import typings.jqueryui.jqueryuiRequire

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("jqueryui/jquery-ui.css", JSImport.Namespace)
@js.native
object JqueryUiCss extends js.Object

/* fake interface augmentation. This is done automatically in typescript. */
given [T]: Conversion[JQuery[T], typings.jqueryui.JQuery] with
  override def apply(x: JQuery[T]): jqueryui.JQuery = x.asInstanceOf[typings.jqueryui.JQuery]

@main
def main: Unit =
  // trigger loading of global library and CSS
  jqueryuiRequire
  JqueryUiCss

  var counter = 1
  val renderLabel: js.Function1[ /* event */ JQueryEventObject, scala.Unit] = eo =>
    counter += 1
    $[Label]("#label").text(s"Value is $counter")

  $[Button]("#button")
    .text("bumpit")
    .on("click", renderLabel)

  $("#accordion").accordion()
end main
