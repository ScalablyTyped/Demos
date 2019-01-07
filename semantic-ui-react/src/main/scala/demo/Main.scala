package demo

import typings.reactDashDomLib.reactDashDomMod.reactDashDomModMembers.render
import typings.reactDashReduxLib.reactDashReduxMod.Provider
import typings.reactDashReduxLib.{ReduxFacade, reactDashReduxMod}
import typings.reactLib.reactMod.ReactNs.{Dispatch => _, _}
import typings.reduxDashDevtoolsDashExtensionLib.reduxDashDevtoolsDashExtensionMod.EnhancerOptions
import typings.reduxDashDevtoolsDashExtensionLib.reduxDashDevtoolsDashExtensionMod.reduxDashDevtoolsDashExtensionModMembers.devToolsEnhancer
import typings.reduxLib.reduxMod.{Store, reduxModMembers}
import typings.stdLib.stdLibMembers.window
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
          reduxModMembers.createStore(
            GithubSearch.Reducer,
            devToolsEnhancer(new EnhancerOptions {
              name = "github search store"
            })
          )

        /* Connect `Demo` component */
        val ConnectedDemo: FC[Demo.Props] =
          ReduxFacade.simpleConnect(Store, Demo.C)

        /* And use `Provider` instead of just passing a normal, goddamn parameter */
        render(
          cls[Provider[GithubSearch.SearchAction]].props(
            new reactDashReduxMod.ProviderProps[GithubSearch.SearchAction] {
              override var store: Store[_, GithubSearch.SearchAction] = Store
            },
            ConnectedDemo.props(new Demo.Props {
              val title = "Welcome"
            })
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
