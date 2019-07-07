package demo

import typings.momentLib.momentLibStrings
import typings.momentLib.momentMod.{^ => Moment}
import typings.reactDashBigDashCalendarLib.reactDashBigDashCalendarMod.View
import typings.reactDashBigDashCalendarLib.reactDashBigDashCalendarMod.^.momentLocalizer
import typings.reactDashBigDashCalendarLib.{
  reactDashBigDashCalendarLibComponents => BC,
  reactDashBigDashCalendarLibStrings => calStrings
}
import typings.reactDashDomLib.reactDashDomMod
import typings.stdLib.^.{console, document}
import typings.stdLib.{Date, DateCls}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("react-big-calendar/lib/css/react-big-calendar.css", JSImport.Namespace)
@js.native
object BigCalendarCss extends js.Object

class Event(val start: Date, val end: Date, val title: js.UndefOr[String]) extends js.Object

object Main {
  import typings.reactLib.dsl._

  BigCalendarCss // touch to load css

  val Localizer = momentLocalizer(Moment)

  val someEvent = new Event(
    start = new DateCls,
    end   = Moment(new DateCls).add(1, momentLibStrings.day).toDate(),
    title = "My amazing event"
  )

  def main(argv: Array[String]): Unit =
    Knowledge.asOption(document.getElementById("container")) match {
      case Some(container) =>
        reactDashDomMod.^.render(
          BC.Calendar[Event, js.Object]
            .props(
              BC.CalendarProps(
                localizer   = Localizer,
                events      = js.Array(someEvent),
                defaultDate = new DateCls,
                defaultView = calStrings.week,
                views       = js.Array[View](calStrings.agenda, calStrings.day, calStrings.week)
              )
            ),
          container,
        )
      case None => console.error("Could not find #container")
    }
}

object Knowledge {
  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])
}
