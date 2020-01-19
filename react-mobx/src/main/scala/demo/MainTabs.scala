package demo

import typings.csstype.mod.BackgroundColorProperty
import typings.materialUi.lightBaseThemeMod.{default => theme}
import typings.materialUi.stylesMod.{getMuiTheme, MuiTheme}
import typings.materialUi.{materialUiComponents => Mui}
import typings.mobxReact.mod.observer
import typings.react.mod._
import typings.std.console

import scala.scalajs.js

object MainTabs {
  import typings.react.dsl._

  class Props(val testStore: MobXTest.Store, val githubStore: GithubSearch.Store) extends js.Object

  private class C extends Component[Props, js.Any, js.Any] {
    override def render(): ReactNode = {
      val effectiveTheme = getMuiTheme(new MuiTheme {
        rawTheme = js.defined(theme)
        palette  = theme.palette
      })

      Mui.MuiThemeProvider.props(
        Mui.MuiThemeProviderProps(effectiveTheme),
        div.props(
          HTMLAttributes(
            onClick = e => console.warn("onclick", e),
            style = new CSSProperties {
              backgroundColor = theme.palette.flatMap(_.canvasColor).asInstanceOf[js.UndefOr[BackgroundColorProperty]]
              width           = 800
              height          = 800
            }
          ),
          Mui.Tabs.noprops(
            Mui.Tab.props(
              Mui.TabProps(label = "Github search"),
              GithubSearch.Component.props(
                new GithubSearch.Props(props.githubStore),
                "Fetch IP address"
              )
            ),
            Mui.Tab.props(
              Mui.TabProps(label = "MobX"),
              MobXTest.Component.props(new MobXTest.Props(props.testStore))
            )
          )
        )
      )
    }
  }

  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])
}
