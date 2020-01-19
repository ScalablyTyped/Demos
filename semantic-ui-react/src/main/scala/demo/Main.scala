package demo

import typings.react.mod._
import typings.reactDom.mod.render
import typings.reactRedux.reactReduxComponents.Provider
import typings.reactRedux.{mod => reactReduxMod, ReduxFacade}
import typings.redux.mod.{createStore, Store}
import typings.reduxDevtoolsExtension.mod.{devToolsEnhancer, EnhancerOptions}
import typings.std.window

import scala.scalajs.js.|

object Main {
  import typings.react.dsl._

  def main(argv: Array[String]): Unit =
    Knowledge.asOption(window.document.getElementById("container")) match {
      case None            =>
      case Some(container) =>
        /* set up redux store with devtools*/
        val Store: Store[GithubSearch.State, GithubSearch.SearchAction] =
          createStore(GithubSearch.Reducer, devToolsEnhancer(EnhancerOptions(name = "github search store")))

        /* Connect `Demo` component */
        val ConnectedDemo: FC[Demo.Props] =
          ReduxFacade.simpleConnect(Store, Demo.C)

        /* And use `Provider` instead of just passing a normal, goddamn parameter */
        render(
          Provider[GithubSearch.SearchAction].props(
            reactReduxMod.ProviderProps[GithubSearch.SearchAction](store = Store),
            ConnectedDemo.props(new Demo.Props("Welcome"))
          ),
          container
        )
    }
}

object Knowledge {
  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])
}
