package demo

import typings.jquery.{JQueryEventObject, mod as $}
import typings.onsenui.mod as ons

import scala.scalajs.js

@main
def main: Unit =

  /** Note, using `on` with the jquery typings is very frustrating. It'll be fixed eventually, but for now I would
    * copy/paste the facade and delete a bunch of lines to make it simpler
    */
  val f: js.Function1[JQueryEventObject, Unit] = _ => ons.notification.alert("Button is tapped!")

  $("ons-button").on("click", f)
end main

