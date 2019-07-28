package demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.raw.SyntheticMouseEvent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import org.scalajs.dom.document
import typings.reactDashSlick.reactDashSlickMod.{Settings, default => ReactSlick}

import scala.scalajs.js

object Main {
  /* use `JsComponent` to integrate with ScalablyTyped component */
  val SlickFacade = JsComponent[Settings, Children.Varargs, js.Object](js.constructorOf[ReactSlick])

  case class Props(images:      js.Array[String])
  case class State(selectedIdx: Option[Int])

  val SlickTest = ScalaComponent
    .builder[Props]("Foo")
    .initialState(State(None))
    .noBackend
    .render { $ =>
      def onClick(idx: Int): SyntheticMouseEvent[dom.Node] => Callback =
        e => Callback.warn(s"clicked image $idx") >> $.setState(State(Some(idx)))

      val images = $.props.images.zipWithIndex.map {
        case (source, idx) =>
          <.img(^.key := idx, ^.src := source, ^.onClick ==> onClick(idx)).render
      }

      <.div(
        ^.style := js.Dynamic.literal(position = "relative", left = "200px", width = 500, height = 500),
        <.label(
          ^.style := js.Dynamic.literal(color = "blue"),
          s"Selected image index: ${$.state.selectedIdx.fold("none")(_.toString)}"
        ),
        SlickFacade(
          Settings(
            onInit        = () => println("slick init"),
            dots          = true,
            autoplay      = true,
            autoplaySpeed = 1000,
            slidesToShow  = 2,
          )
        )(images: _*)
      )
    }
    .build

  def main(argv: Array[String]): Unit =
    <.div(
      SlickTest(
        Props(
          js.Array(
            "https://img.buzzfeed.com/buzzfeed-static/static/2014-04/enhanced/webdr07/4/16/original-16439-1396642689-17.jpg?downsize=800:*&output-format=auto&output-quality=auto",
            "https://img.buzzfeed.com/buzzfeed-static/static/2014-04/enhanced/webdr03/4/16/enhanced-26552-1396642701-1.jpg?downsize=800:*&output-format=auto&output-quality=auto",
            "https://img.buzzfeed.com/buzzfeed-static/static/2014-04/enhanced/webdr07/4/16/enhanced-16354-1396642706-25.jpg?downsize=800:*&output-format=auto&output-quality=auto",
            "https://img.buzzfeed.com/buzzfeed-static/static/2014-04/enhanced/webdr04/10/12/enhanced-buzz-29081-1397145781-14.jpg?downsize=800:*&output-format=auto&output-quality=auto",
            "https://img.buzzfeed.com/buzzfeed-static/static/2014-04/enhanced/webdr06/4/16/enhanced-11136-1396643149-13.jpg?downsize=800:*&output-format=auto&output-quality=auto",
            "https://img.buzzfeed.com/buzzfeed-static/static/2014-04/enhanced/webdr02/9/12/enhanced-buzz-11844-1397060009-22.jpg?downsize=800:*&output-format=auto&output-quality=auto",
            "https://img.buzzfeed.com/buzzfeed-static/static/2014-04/enhanced/webdr07/9/12/enhanced-buzz-28527-1397060122-10.jpg?downsize=800:*&output-format=auto&output-quality=auto"
          )
        )
      ),
    ).renderIntoDOM(document.body)
}
