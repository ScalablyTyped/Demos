package demo

import typings.materialDashUiLib.stylesBaseThemesLightBaseThemeMod.{default => theme}
import typings.materialDashUiLib.stylesMod.MuiTheme
import typings.materialDashUiLib.stylesMod.^.getMuiTheme
import typings.materialDashUiLib.stylesMuiThemeProviderMod.{default => MuiThemeProvider}
import typings.materialDashUiLib.tabsMod._
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.StylesNs.MuiThemeProviderProps
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.TabsNs.TabProps
import typings.mobxDashReactLib.mobxDashReactMod.^.observer
import typings.reactLib.reactMod.ReactNs.{CSSProperties, ComponentClass, HTMLAttributes, ReactNode}
import typings.reactLib.reactMod._
import typings.stdLib
import typings.stdLib.^.console

import scala.scalajs.js

object MainTabs {
  import typings.reactLib.dsl._

  trait Props extends js.Object {
    val testStore:   MobXTest.Store
    val githubStore: GithubSearch.Store
  }

  private class C extends Component[Props, js.Any, js.Any] {
    override def render(): ReactNode = {
      val effectiveTheme = getMuiTheme(new MuiTheme {
        rawTheme = js.defined(theme)
        palette  = theme.palette
      })

      cls[MuiThemeProvider].props(
        new MuiThemeProviderProps { muiTheme = effectiveTheme },
        div.props(
          new HTMLAttributes[stdLib.HTMLDivElement] {
            onClick = js.defined(e => console.warn("onclick", e))
            style = new CSSProperties {
              backgroundColor = theme.palette.flatMap(_.canvasColor)
              width           = 800
              height          = 800
            }
          },
          cls[Tabs].noprops(
            cls[Tab].props(
              new TabProps { label = js.defined("Github search") },
              GithubSearch.Component.props(
                new GithubSearch.Props { val store = props.githubStore },
                "Fetch IP address"
              )
            ),
            cls[Tab].props(
              new TabProps { label = js.defined("MobX") },
              MobXTest.Component.props(new MobXTest.Props { val store = props.testStore })
            ),
          )
        )
      )
    }
  }

  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])
}
