package demo

import typings.reactDashDomLib.reactDashDomMod.^.render
import typings.reactDashReduxLib.reactDashReduxMod.Provider
import typings.reactDashReduxLib.{reactDashReduxMod, ReduxFacade}
import typings.reactLib.reactMod.ReactNs.{Dispatch => _, _}
import typings.reduxDashDevtoolsDashExtensionLib.reduxDashDevtoolsDashExtensionMod.EnhancerOptions
import typings.reduxDashDevtoolsDashExtensionLib.reduxDashDevtoolsDashExtensionMod.^.devToolsEnhancer
import typings.reduxLib.reduxMod.Store
import typings.reduxLib.reduxMod
import typings.stdLib.^.window
import typings.{reactLib, stdLib}

import scala.scalajs.js.|

object Main {
  def main(argv: Array[String]): Unit =
    Knowledge.asOption(window.document.getElementById("container")) match {
      case None =>
      case Some(container) =>
        import reactLib.dsl._

        /* set up redux store with devtools*/
        val Store: Store[GithubSearch.State, GithubSearch.SearchAction] =
          reduxMod.^.createStore(GithubSearch.Reducer, devToolsEnhancer(EnhancerOptions(name = "github search store")))

        /* Connect `Demo` component */
        val ConnectedDemo: FC[Demo.Props] =
          ReduxFacade.simpleConnect(Store, Demo.C)

        /* And use `Provider` instead of just passing a normal, goddamn parameter */
        render(
          cls[Provider[GithubSearch.SearchAction]].props(
            reactDashReduxMod.ProviderProps[GithubSearch.SearchAction](store = Store),
            ConnectedDemo.props(new Demo.Props("Welcome"))
          ),
          Knowledge.isElement(container)
        )
    }
}

object Knowledge {
  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])
  def isElement(e: stdLib.Element): reactLib.Element =
    e.asInstanceOf[reactLib.Element]
}
