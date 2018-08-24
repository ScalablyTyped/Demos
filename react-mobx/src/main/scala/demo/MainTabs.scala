package demo

import typings.materialDashUiLib.stylesBaseThemesLightBaseThemeMod.{default => theme}
import typings.materialDashUiLib.stylesMod.MuiTheme
import typings.materialDashUiLib.stylesMod.stylesModMembers.getMuiTheme
import typings.materialDashUiLib.stylesMuiThemeProviderMod.{default => MuiThemeProvider}
import typings.materialDashUiLib.tabsMod._
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.StylesNs.MuiThemeProviderProps
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.TabsNs.TabProps
import typings.mobxDashReactLib.mobxDashReactMod.mobxDashReactModMembers.observer
import typings.reactLib.ReactDsl._
import typings.reactLib.reactMod.ReactNs.{CSSProperties, ComponentClass, HTMLAttributes, ReactNode}
import typings.reactLib.reactMod._
import typings.stdLib
import typings.stdLib.stdLibMembers.console

import scala.scalajs.js

object MainTabs {

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

      componentClass[MuiThemeProvider].apply(
        new MuiThemeProviderProps { muiTheme = effectiveTheme },
        div(
          new HTMLAttributes[stdLib.HTMLDivElement] {
            onClick = js.defined(e => console.warn("onclick", e))
            style = new CSSProperties {
              backgroundColor = theme.palette.flatMap(_.canvasColor)
              width           = 800
              height          = 800
            }
          },
          componentClass[Tabs].noprops(
            componentClass[Tab].apply(
              new TabProps { label = js.defined("Github search") },
              component(GithubSearch.Component)(
                new GithubSearch.Props { val store = props.githubStore },
                "Fetch IP address"
              )
            ),
            componentClass[Tab].apply(
              new TabProps { label = js.defined("MobX") },
              component(MobXTest.Component)(new MobXTest.Props { val store = props.testStore })
            ),
          )
        )
      )
    }
  }

  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])
}
