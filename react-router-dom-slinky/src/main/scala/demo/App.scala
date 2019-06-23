package demo

import demo.ReactRouterFacade._
import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import typings.reactDashRouterLib.reactDashRouterMod._

import scala.scalajs.js

@react object App {

  type Props = Unit

  def home = div(h2("Home"))

  def about = div(h2("About"))

  val component = FunctionalComponent[Props] { _ =>
    val renderIntro = div(
      header(className := "App-header")(h1(className := "App-title")("Welcome to React (with Scala.js!)")),
      p(className := "App-intro")("To get started, edit ", code("App.scala"), " and save to reload.")
    )

    div(className := "App")(
      renderIntro,
      BrowserRouter(BrowserRouterProps())(
        div(
          ul(
            li(Link(LinkProps(to = "/"))("Home")),
            li(Link(LinkProps(to = "/about"))("About")),
            li(Link(LinkProps(to = "/topics"))("Topics"))
          ),
          hr(),
          Route[Unit](exact = true, path        = "/", render = _ => home),
          Route[Unit](path  = "/about", render  = _ => about),
          Route[Unit](path  = "/topics", render = props => Topics(props.`match`)),
        )
      )
    )
  }
}

@react object Topics {

  case class Props(`match`: `match`[Unit])

  val component = FunctionalComponent[Props] { props =>
    div(
      h2("Topics"),
      ul(
        li(Link(LinkProps(to = props.`match`.url + "/rendering"))("Rendering with React")),
        li(Link(LinkProps(to = props.`match`.url + "/components"))("Components")),
        li(Link(LinkProps(to = props.`match`.url + "/props-v-state"))("Props v. State"))
      ),
      hr(),
      Route[Topic.Param](path = props.`match`.path + "/:topicId", render = props => Topic(props.`match`)),
      Route[Unit](exact       = true, path                               = props.`match`.path, render = _ => h3("Please select a topic")),
    )
  }
}

@react object Topic {

  @js.native
  trait Param extends js.Object {
    val topicId: String = js.native
  }

  case class Props(`match`: `match`[Topic.Param])

  val component = FunctionalComponent[Props] { props =>
    div(
      h3("Topic: " + props.`match`.params.topicId)
    )
  }
}
