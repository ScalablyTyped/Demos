package demo

import typings.reactDashDomLib.reactDashDomMod.^.render
import typings.reactDashReduxLib.reactDashReduxLibComponents.Provider
import typings.reactDashReduxLib.{reactDashReduxMod, ReduxFacade}
import typings.reactLib.reactMod._
import typings.reactDashReduxLib.{reactDashReduxMod, ReduxFacade}
import typings.reduxDashDevtoolsDashExtensionLib.reduxDashDevtoolsDashExtensionMod.EnhancerOptions
import typings.reduxDashDevtoolsDashExtensionLib.reduxDashDevtoolsDashExtensionMod.^.devToolsEnhancer
import typings.reduxLib.reduxMod.Store
import typings.reduxLib.reduxMod.^.createStore
import typings.stdLib.^.window

import scala.scalajs.js.|

object Main {
  import typings.reactLib.dsl._

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
