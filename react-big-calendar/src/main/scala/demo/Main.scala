package demo

import typings.moment.momentMod.{^ => Moment}
import typings.moment.momentStrings
import typings.reactDashBigDashCalendar.reactDashBigDashCalendarMod.{momentLocalizer, View}
import typings.reactDashBigDashCalendar.{
  reactDashBigDashCalendarComponents => BC,
  reactDashBigDashCalendarStrings => calStrings
}
import typings.reactDashDom.reactDashDomMod
import typings.std.{console, document, Date, DateCls}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("react-big-calendar/lib/css/react-big-calendar.css", JSImport.Namespace)
@js.native
object BigCalendarCss extends js.Object

class Event(val start: Date, val end: Date, val title: js.UndefOr[String]) extends js.Object

object Main {
  import typings.react.dsl._

  BigCalendarCss // touch to load css

  val Localizer = momentLocalizer(Moment)

  val someEvent = new Event(
    start = new DateCls,
    end   = Moment(new DateCls).add(1, momentStrings.day).toDate(),
    title = "My amazing event"
  )

  def main(argv: Array[String]): Unit =
    Knowledge.asOption(document.getElementById("container")) match {
      case Some(container) =>
        reactDashDomMod.render(
          BC.Calendar[Event, js.Object]
            .props(
              BC.CalendarProps(
                localizer   = Localizer,
                events      = js.Array(someEvent),
                defaultDate = new DateCls,
                defaultView = View.week,
                views       = js.Array(View.agenda, View.day, View.week)
              )
            ),
          container
        )
      case None => console.error("Could not find #container")
    }
}

object Knowledge {
  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])
}
