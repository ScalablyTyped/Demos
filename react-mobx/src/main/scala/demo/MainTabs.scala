package demo

import typings.csstypeLib.csstypeMod.BackgroundColorProperty
import typings.materialDashUiLib.stylesBaseThemesLightBaseThemeMod.{default => theme}
import typings.materialDashUiLib.stylesMod.^.getMuiTheme
import typings.materialDashUiLib.stylesMuiThemeProviderMod.{default => MuiThemeProvider}
import typings.materialDashUiLib.tabsMod._
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.StylesNs.{MuiTheme, MuiThemeProviderProps}
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.TabsNs.TabProps
import typings.mobxDashReactLib.mobxDashReactMod.^.observer
import typings.reactLib.reactMod.ReactNs.{Component => _, _}
import typings.reactLib.reactMod._
import typings.stdLib.^.console

import scala.scalajs.js

object MainTabs {
  import typings.reactLib.dsl._

  class Props(val testStore: MobXTest.Store, val githubStore: GithubSearch.Store) extends js.Object

  private class C extends Component[Props, js.Any, js.Any] {
    override def render(): ReactNode = {
      val effectiveTheme = getMuiTheme(new MuiTheme {
        rawTheme = js.defined(theme)
        palette  = theme.palette
      })

      cls[MuiThemeProvider].props(
        MuiThemeProviderProps(effectiveTheme),
        div.props(
          HTMLAttributes(
            DOMAttributes(
              onClick = e => console.warn("onclick", e)
            ),
            style = new CSSProperties {
              backgroundColor = theme.palette.flatMap(_.canvasColor).asInstanceOf[js.UndefOr[BackgroundColorProperty]]
              width           = 800
              height          = 800
            }
          ),
          cls[Tabs].noprops(
            cls[Tab].props(
              TabProps(label = "Github search"),
              GithubSearch.Component.props(
                new GithubSearch.Props(props.githubStore),
                "Fetch IP address"
              )
            ),
            cls[Tab].props(
              TabProps(label = "MobX"),
              MobXTest.Component.props(new MobXTest.Props(props.testStore))
            ),
          )
        )
      )
    }
  }

  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])
}
