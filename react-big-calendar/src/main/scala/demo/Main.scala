package demo

import typings.momentLib.momentLibStrings
import typings.momentLib.momentMod.{momentModMembers => Moment}
import typings.reactDashBigDashCalendarLib.reactDashBigDashCalendarMod.{
  BigCalendarProps,
  Navigate,
  default => ReactBigCalendar
}
import typings.reactDashBigDashCalendarLib.{reactDashBigDashCalendarLibStrings, Anon_Myweek}
import typings.reactDashDomLib.reactDashDomMod.reactDashDomModMembers
import typings.reactLib.ReactDsl
import typings.reactLib.reactMod.ReactNs.{CSSProperties, FC, ReactNode}
import typings.stdLib.Date
import typings.stdLib.stdLibMembers.{console, document, Date, Object}
import typings.{reactLib, stdLib}

import scala.language.higherKinds
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.{|, JSON, UndefOr}

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
  import Knowedge._

  BigCalendarCss // touch to load css

  val Localizer = ReactBigCalendar.momentLocalizer(Moment)

  /* define a functional component with static members */
  val MyNiceMonth =
    Object.assign[FC[BigCalendarProps[Event, js.Object]], ViewStatic[Event]](
      fc { props =>
        ReactDsl.div.noprops(
          "My jsony month",
          js.defined(props).fold("No props")(x => JSON.stringify(x))
        )
      },
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
    asOption(document.getElementById("container")) match {
      case Some(container) =>
        reactDashDomModMembers.render(
          ReactDsl
            .componentClass[ReactBigCalendar[Event, js.Object]]
            .apply(
              new BigCalendarProps[Event, js.Object] {
                override var localizer = Localizer
                events      = js.Array(someEvent)
                defaultDate = Date.newInstance0()
                defaultView = reactDashBigDashCalendarLibStrings.month
                views = new Anon_Myweek {
                  override var month  = true
                  override var myweek = MyNiceMonth.covary[js.Object]
                  override var week   = true
                }
                /* can pass style even though it isn't specified in `BigCalendarProps` */
                val style = new CSSProperties {
                  height = "100vh"
                }
              }
            ),
          stdLibElementIsReactElement(container),
        )
      case None => console.error("Could not find #container")
    }

}

object Knowedge {
  def stdLibElementIsReactElement(e: stdLib.Element): reactLib.Element =
    e.asInstanceOf[reactLib.Element]

  implicit class Covary[M[_], O <: js.Object](mo: M[O]) {
    def covary[OO >: O <: js.Object]: M[OO] = mo.asInstanceOf[M[OO]]
  }

  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])

  /* a js.Function1 is a functional component */
  def fc[Props <: js.Object](f: js.Function1[Props, ReactNode]): FC[Props] =
    f.asInstanceOf[FC[Props]]
}
