package demo

import typings.momentLib.momentLibStrings
import typings.momentLib.momentMod.{^ => Moment}
import typings.reactDashBigDashCalendarLib.reactDashBigDashCalendarMod.Navigate
import typings.reactDashBigDashCalendarLib.reactDashBigDashCalendarMod.default.momentLocalizer
import typings.reactDashBigDashCalendarLib.{Anon_Month, reactDashBigDashCalendarLibStrings, reactDashBigDashCalendarLibComponents => BC}
import typings.reactDashDomLib.reactDashDomMod
import typings.reactLib.reactMod.ReactNs.{FC, ReactNode}
import typings.stdLib.Date
import typings.stdLib.^.{Date, Object, console, document}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.{JSON, UndefOr, |}

@JSImport("react-big-calendar/lib/css/react-big-calendar.css", JSImport.Namespace)
@js.native
object BigCalendarCss extends js.Object

trait Event extends js.Object {
  val start: Date
  val end:   Date
  val title: js.UndefOr[String]
}

trait ViewStatic[E] extends js.Object {
  def title(e:       js.UndefOr[E]): String
  def navigate(date: js.UndefOr[Date], action: js.UndefOr[Navigate], props: js.UndefOr[js.Any]): Date
}

object Main {
  import typings.reactLib.dsl._

  BigCalendarCss // touch to load css

  val Localizer = momentLocalizer(Moment)

  /* define a functional component with static members */
  val MyJsonyMonth =
    define.fc[BC.BigCalendarProps[Event, js.Object]] { props =>
      div.noprops(
        "My jsony month",
        js.defined(props).fold[ReactNode]("No props")(x => pre.noprops(code.noprops(JSON.stringify(x))))
      )
    }

  /* define a functional component with static members */
  val MyJsonyMonthExtended =
    Object.assign[FC[BC.BigCalendarProps[Event, js.Object]], ViewStatic[Event]](
      MyJsonyMonth,
      new ViewStatic[Event] {
        override def title(e: js.UndefOr[Event]): String =
          e.flatMap(_.title).getOrElse("No title")
        override def navigate(date: UndefOr[Date], action: UndefOr[Navigate], props: UndefOr[js.Any]): Date =
          date.getOrElse(Date.newInstance0())
      }
    )

  val someEvent = new Event {
    val start = Date.newInstance0()
    val end   = Moment(start).add(1, momentLibStrings.day).toDate()
    val title = "My amazing event"
  }

  def main(argv: Array[String]): Unit =
    Knowledge.asOption(document.getElementById("container")) match {
      case Some(container) =>
        reactDashDomMod.^.render(
          BC.ReactDashBigDashCalendar[Event, js.Object].props(
            BC.BigCalendarProps[Event, js.Object](
              localizer   = Localizer,
              events      = js.Array(someEvent),
              defaultDate = Date.newInstance0(),
              defaultView = reactDashBigDashCalendarLibStrings.week,
              views = Anon_Month(
                month  = true,
                myweek = Knowledge.unspecify(MyJsonyMonthExtended),
                week   = true
              )
            )
          ),
          container,
        )
      case None => console.error("Could not find #container")
    }

}

object Knowledge {
  def unspecify[T](c: FC[T]): FC[js.Object] =
    c.asInstanceOf[FC[js.Object]]

  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])
}
