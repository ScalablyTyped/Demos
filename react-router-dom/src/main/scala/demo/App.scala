package demo

import typings.history.mod.{Location, LocationState}
import typings.react.dsl._
import typings.react.mod.{ComponentType, HTMLAttributes, ReactElement}
import typings.reactRouter.mod.{RouteComponentProps, RouteProps, StaticContext, _}
import typings.reactRouterDom.reactRouterDomComponents.{Route => RouteAlt, _}

import scala.scalajs.js
import scala.scalajs.js.|

object App {

  def home = div.noprops(h2.noprops("Home"))

  def about = div.noprops(h2.noprops("About"))

  val component = define.fc[js.Object] { _ =>
    val renderIntro = div.noprops(
      header.props(
        HTMLAttributes(className = "App-header"),
        h1.props(HTMLAttributes(className = "App-title"), "Welcome to React (with Scala.js!)")
      ),
      p.props(
        HTMLAttributes(className = "App-intro"),
        "To get started, edit ",
        code.noprops("App.scala"),
        " and save to reload."
      )
    )

    div.props(
      HTMLAttributes(className = "App"),
      renderIntro,
      BrowserRouter.noprops(
        div.noprops(
          ul.noprops(
            li.noprops(Link[js.Object].props(LinkProps(to = "/"), "Home")),
            li.noprops(Link[js.Object].props(LinkProps(to = "/about"), "About")),
            li.noprops(Link[js.Object].props(LinkProps(to = "/topics"), "Topics"))
          ),
          hr.noprops(),
          Route[js.Object](exact = true, path        = "/", render = _ => home),
          Route[js.Object](path  = "/about", render  = _ => about),
          Route[js.Object](path  = "/topics", render = props => Topics(props.`match`))
        )
      )
    )
  }

  object Topics {
    def apply(`match`: `match`[js.Object]) = component.props(new Props(`match`))

    class Props(val `match`: `match`[js.Object]) extends js.Object

    val component = define.fc[Props] { props =>
      div.noprops(
        h2.noprops("Topics"),
        ul.noprops(
          li.noprops(Link[js.Object].props(LinkProps(to = props.`match`.url + "/rendering"), "Rendering with React")),
          li.noprops(Link[js.Object].props(LinkProps(to = props.`match`.url + "/components"), "Components")),
          li.noprops(Link[js.Object].props(LinkProps(to = props.`match`.url + "/props-v-state"), "Props v. State"))
        ),
        hr.noprops(),
        Route[Topic.Param](path = props.`match`.path + "/:topicId", render = props => Topic(props.`match`)),
        Route[js.Object](
          exact  = true,
          path   = props.`match`.path,
          render = _ => h3.noprops("Please select a topic")
        )
      )
    }
  }

  object Topic {
    def apply(`match`: `match`[Param]) = component.props(`match`.params)

    @js.native
    trait Param extends js.Object {
      val topicId: String = js.native
    }

    val component = define.fc[Param] { params =>
      div.noprops(
        h3.noprops("Topic: " + params.topicId)
      )
    }
  }

  /** The description of the `Route` component is somewhat rough, so this is a somewhat better wrapper.
    *
    * The main problematic issue is the type parameter `T` which ends up in `match`, which is awkward in
    * typescript jsx, in slinky, and in ScalablyTyped for three different reasons.
    *
    * Note that the `component` param has been renamed to `componentAlt` to avoid conflicts with Slinky's
    * definitions
    *
    * Note that this could be developed much further, probably the only way to make a somewhat sane API
    *  would be to use a builder.
    */
  def Route[T](
      alwaysRender: js.Function1[ /* props */ RouteChildrenProps[T, LocationState], ReactElement]    = null,
      componentAlt: ComponentType[RouteComponentProps[T, StaticContext, LocationState]]              = null,
      exact:        js.UndefOr[scala.Boolean]                                                        = js.undefined,
      location:     Location[LocationState]                                                          = null,
      path:         String | js.Array[String]                                                        = null,
      render:       /* props */ RouteComponentProps[T, StaticContext, LocationState] => ReactElement = null,
      sensitive:    js.UndefOr[Boolean]                                                              = js.undefined,
      strict:       js.UndefOr[Boolean]                                                              = js.undefined
  ): ReactElement = {

    val props = js.Dynamic.literal()
    if (alwaysRender != null) props.updateDynamic("children")(alwaysRender.asInstanceOf[js.Any])
    if (componentAlt != null) props.updateDynamic("component")(componentAlt.asInstanceOf[js.Any])
    if (!js.isUndefined(exact)) props.updateDynamic("exact")(exact)
    if (location != null) props.updateDynamic("location")(location)
    if (path != null) props.updateDynamic("path")(path.asInstanceOf[js.Any])
    if (render != null) props.updateDynamic("render")(js.Any.fromFunction1(render))
    if (!js.isUndefined(sensitive)) props.updateDynamic("sensitive")(sensitive)
    if (!js.isUndefined(strict)) props.updateDynamic("strict")(strict)

    RouteAlt[RouteProps].props(props.asInstanceOf[RouteProps])
  }
}
