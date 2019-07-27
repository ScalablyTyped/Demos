package demo

import typings.react.reactMod._
import typings.reactDashDom.reactDashDomMod.^.render
import typings.reactDashRedux.reactDashReduxComponents.Provider
import typings.reactDashRedux.{reactDashReduxMod, ReduxFacade}
import typings.redux.reduxMod.Store
import typings.redux.reduxMod.^.createStore
import typings.reduxDashDevtoolsDashExtension.reduxDashDevtoolsDashExtensionMod.EnhancerOptions
import typings.reduxDashDevtoolsDashExtension.reduxDashDevtoolsDashExtensionMod.^.devToolsEnhancer
import typings.std.^.window

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
            reactDashReduxMod.ProviderProps[GithubSearch.SearchAction](store = Store),
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
