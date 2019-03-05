package demo

import typings.reactLib.reactMod.ReactNs.ReactElement
import typings.semanticDashUiDashReactLib.distCommonjsElementsHeaderHeaderMod.HeaderProps
import typings.semanticDashUiDashReactLib.distCommonjsModulesProgressProgressMod.ProgressProps
import typings.semanticDashUiDashReactLib.distCommonjsModulesSearchSearchMod.SearchProps
import typings.semanticDashUiDashReactLib.distCommonjsModulesTabTabMod.TabProps
import typings.semanticDashUiDashReactLib.distCommonjsViewsCardCardHeaderMod.CardHeaderProps
import typings.semanticDashUiDashReactLib.distCommonjsViewsCardCardMetaMod.CardMetaProps
import typings.semanticDashUiDashReactLib.distCommonjsViewsCardCardMod.{CardProps, StrictCardProps}
import typings.semanticDashUiDashReactLib.semanticDashUiDashReactMod.{^ => Sui}
import typings.semanticDashUiDashReactLib.{
  Anon_Content => TabStructure,
  semanticDashUiDashReactLibStrings => SuiStrings
}
import typings.reactDashReduxLib.ReduxFacade.Connected

import scala.scalajs.js

object Demo {
  import typings.reactLib.dsl._

  class Props(val title: String) extends js.Object

  val CardDemo: ReactElement[CardProps] =
    Sui.Card.props(
      CardProps(color = SuiStrings.orange),
      Sui.CardHeader.noprops("CardHeader"),
      Sui.CardMeta.noprops("CardMeta"),
      Sui.Divider.noprops(),
      Sui.Search.props(SearchProps(minCharacters = 1)),
    )

  val ProgressDemo: ReactElement[CardProps] =
    Sui.Card.noprops(
      Sui.Progress.props(ProgressProps(percent = 70, warning  = true)),
      Sui.Progress.props(ProgressProps(percent = 100, warning = false))
    )

  val C = define.fc[Connected[GithubSearch.State, GithubSearch.SearchAction] with Props](
    props =>
      div.noprops(
        Sui.Header.props(HeaderProps(size = SuiStrings.large), props.title),
        Sui.Tab.props(
          TabProps(
            panes = js.Array(
              TabStructure(
                menuItem = "Repo search",
                render   = () => GithubSearch.C.props(new GithubSearch.Props(props.state, props.dispatch))
              ),
              TabStructure(menuItem = "Card", render     = () => CardDemo),
              TabStructure(menuItem = "Progress", render = () => ProgressDemo)
            )
          )
        ),
    )
  )
}
