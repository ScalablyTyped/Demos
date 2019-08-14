package demo

import typings.history.historyMod.LocationState
import typings.react.dsl._
import typings.react.reactMod.{ComponentType, FunctionComponent}
import typings.reactDashDom.reactDashDomMod.^.render
import typings.reactDashRouter.reactDashRouterMod.{RouteComponentProps, RouteProps, StaticContext}
import typings.reactDashRouterDashDom.reactDashRouterDashDomComponents._
import typings.std.^.window

import scala.scalajs.js
import scala.scalajs.js.|

object App {

  val Home = define.fc[js.Object] { _ =>
    div.noprops(
      h2.noprops("Home")
    )
  }

  def topics(props: RouteComponentProps[_, StaticContext, LocationState]) = div.noprops(
    h2.noprops("Topics"),
    p.noprops("pathname: ", props.location.pathname)
  )

  val header = ul.noprops(
    li.noprops(Link.props(LinkProps(to = "/"), "Home")),
    li.noprops(Link.props(LinkProps(to = "/about"), "About")),
    li.noprops(Link.props(LinkProps(to = "/topics"), "Topics"))
  )

  val content =
    BrowserRouter.noprops(
      div.noprops(
        header,
        hr.noprops(),
        /* pass a react component as the `component` prop */
        Route[RouteProps].props(RouteProps(exact = true, path = "/", component = massage(Home))),
        /* pass a function to the `render` prop */
        Route[RouteProps].props(RouteProps(path = "/topics", render = topics)),
        /* pass a `ReactNode` as `children`. This will always render */
        Route[RouteProps].props(RouteProps(path = "/about"), div.noprops(h2.noprops("Always render")))
      )
    )

  /** The react component passed to `component` will only receive `RouteComponentProps` from `react-router`,
    *  and it can choose to ignore it. That's expressed by this... curious union type.
    *
    * Note that this is terrible in Typescript as well, and in practise it's much safer and better
    *  to use `render ` than `component` */
  private def massage[P](c: FunctionComponent[P]) =
    c.asInstanceOf[ComponentType[_ | RouteComponentProps[_, StaticContext, LocationState]]]

  def main(argv: Array[String]): Unit = render(content, window.document.body)

}
