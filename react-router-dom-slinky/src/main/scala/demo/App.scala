package demo

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import typings.reactDashRouterLib.reactDashRouterMod.{`match`, RouteProps}
import typings.reactDashRouterDashDomLib.reactDashRouterDashDomLibComponents._
import typings.reactLib.ScalableSlinky._

import scala.scalajs.js

@react object RouteAlt extends ExternalComponent {
  type Props = RouteProps
  override val component = Route[ReactComponentClass[_]]
}

@react object App {

  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
    val renderIntro = div(
      header(className := "App-header")(h1(className := "App-title")("Welcome to React (with Scala.js!)")),
      p(className := "App-intro")("To get started, edit ", code("App.scala"), " and save to reload.")
    )

    def home = div(
      h2("Home")
    )

    def about = div(
      h2("About")
    )

    div(className := "App")(
      renderIntro,
      BrowserRouter.noprops(
        div(
          ul(
            li(Link.props(LinkProps(to = "/"))("Home")),
            li(Link.props(LinkProps(to = "/about"))("About")),
            li(Link.props(LinkProps(to = "/topics"))("Topics"))
          ),
          hr(),
          RouteAlt(RouteProps(exact = true, path        = "/", render = _ => home: ReactElement)),
          RouteAlt(RouteProps(path  = "/about", render  = _ => about: ReactElement)),
          RouteAlt(RouteProps(path  = "/topics", render = props => Topics(props.`match`): ReactElement)),
        )
      )
    )
  }
}

@react object Topics {

  case class Props(`match`: `match`[_])

  val component = FunctionalComponent[Props] { props =>
    div(
      h2("Topics"),
      ul(
        li(Link.props(LinkProps(to = {
          props.`match`.url + "/rendering"
        }))("Rendering with React")),
        li(Link.props(LinkProps(to = {
          props.`match`.url + "/components"
        }))("Components")),
        li(Link.props(LinkProps(to = {
          props.`match`.url + "/props-v-state"
        }))("Props v. State"))
      ),
      hr(),
      RouteAlt(RouteProps(path = {
        props.`match`.path + "/:topicId"
      }, render                 = props => Topic(props.`match`): ReactElement)),
      RouteAlt(RouteProps(exact = true, path = {
        props.`match`.path
      }, render = _ => h3("Please select a topic"): ReactElement)),
    )
  }
}

@react object Topic {

  @js.native
  trait TopicParam extends js.Object {
    val topicId: String = js.native
  }

  case class Props(`match`: `match`[_])

  val component = FunctionalComponent[Props] { props =>
    div(
      h3("Topic: " + props.`match`.params.asInstanceOf[TopicParam].topicId)
    )
  }
}
