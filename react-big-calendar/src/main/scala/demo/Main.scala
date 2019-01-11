package demo

import typings.momentLib.momentLibStrings
import typings.momentLib.momentMod.{^ => Moment}
import typings.reactDashBigDashCalendarLib.reactDashBigDashCalendarMod.{BigCalendarProps, Navigate, default => ReactBigCalendar}
import typings.reactDashBigDashCalendarLib.{Anon_Month, reactDashBigDashCalendarLibStrings}
import typings.reactDashDomLib.reactDashDomMod
import typings.reactLib.reactMod.ReactNs.{CSSProperties, FC, ReactNode}
import typings.stdLib.Date
import typings.stdLib.^.{Date, Object, console, document}
import typings.{reactLib, stdLib}

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
  def navigate(date: js.UndefOr[stdLib.Date], action: js.UndefOr[Navigate], props: js.UndefOr[js.Any]): stdLib.Date
}

object Main {
  import typings.reactLib.dsl._

  BigCalendarCss // touch to load css

  val Localizer = ReactBigCalendar.momentLocalizer(Moment)

  /* define a functional component with static members */
  val MyJsonyMonth =
    define.fc[BigCalendarProps[Event, js.Object]] { props =>
      div.noprops(
        "My jsony month",
        js.defined(props).fold[ReactNode]("No props")(x => pre.noprops(code.noprops(JSON.stringify(x))))
      )
    }

  /* define a functional component with static members */
  val MyJsonyMonthExtended =
    Object.assign[FC[BigCalendarProps[Event, js.Object]], ViewStatic[Event]](
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
          cls[ReactBigCalendar[Event, js.Object]].props(
            new BigCalendarProps[Event, js.Object] {
              override var localizer = Localizer
              events      = js.Array(someEvent)
              defaultDate = Date.newInstance0()
              defaultView = reactDashBigDashCalendarLibStrings.month
              views = new Anon_Month {
                override var month  = true
                override var myweek = Knowledge.unspecify(MyJsonyMonthExtended)
                override var week   = true
              }
              /* can pass style even though it isn't specified in `BigCalendarProps` */
              val style = new CSSProperties {
                height = "100vh"
              }
            }
          ),
          Knowledge.isElement(container),
        )
      case None => console.error("Could not find #container")
    }

}

object Knowledge {
  def isElement(e: stdLib.Element): reactLib.Element =
    e.asInstanceOf[reactLib.Element]

  def unspecify[T](c: FC[T]): FC[js.Object] =
    c.asInstanceOf[FC[js.Object]]

  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])
}
