package demo

import typings.reactLib.reactMod.ReactNs.ReactElement
import typings.semanticDashUiDashReactLib.distCommonjsElementsHeaderHeaderMod.HeaderProps
import typings.semanticDashUiDashReactLib.distCommonjsModulesProgressProgressMod.ProgressProps
import typings.semanticDashUiDashReactLib.distCommonjsModulesSearchSearchMod.SearchProps
import typings.semanticDashUiDashReactLib.distCommonjsModulesTabTabMod.TabProps
import typings.semanticDashUiDashReactLib.distCommonjsViewsCardCardHeaderMod.CardHeaderProps
import typings.semanticDashUiDashReactLib.distCommonjsViewsCardCardMetaMod.CardMetaProps
import typings.semanticDashUiDashReactLib.distCommonjsViewsCardCardMod.CardProps
import typings.semanticDashUiDashReactLib.semanticDashUiDashReactMod.{^ => Sui}
import typings.semanticDashUiDashReactLib.{Anon_Content, semanticDashUiDashReactLibStrings => SuiStrings}
import typings.reactDashReduxLib.ReduxFacade.Connected

import scala.scalajs.js

object Demo {
  import typings.reactLib.dsl._

  /* This object is unfortunately anonymous in typescript */
  type TabStructure = Anon_Content

  trait Props extends js.Object {
    val title: String
  }

  val CardDemo: ReactElement[CardProps] =
    Sui.Card.props(
      new CardProps { color = SuiStrings.orange },
      Sui.CardHeader.props(new CardHeaderProps {}, "CardHeader"),
      Sui.CardMeta.props(new CardMetaProps {}, "CardMeta"),
      Sui.Divider.noprops(),
      Sui.Search.props(new SearchProps {
        minCharacters = js.defined(1)
      }),
    )

  val ProgressDemo: ReactElement[CardProps] =
    Sui.Card.noprops(
      Sui.Progress.props(new ProgressProps {
        percent = 70
        warning = true
      }),
      Sui.Progress.props(new ProgressProps {
        percent = 100
        warning = false
      })
    )

  val C = define.fc[Connected[GithubSearch.State, GithubSearch.SearchAction] with Props](
    props =>
      div.noprops(
        Sui.Header.props(new HeaderProps {
          size = SuiStrings.large
        }, props.title),
        Sui.Tab.props(new TabProps {
          panes = js.Array(
            new TabStructure {
              menuItem = js.defined("Repo search")
              render = js.defined(
                () =>
                  GithubSearch.C.props(new GithubSearch.Props {
                    val state    = props.state
                    val dispatch = props.dispatch
                  })
              )
            },
            new TabStructure {
              menuItem = js.defined("Card")
              render   = js.defined(() => CardDemo)
            },
            new TabStructure {
              menuItem = js.defined("Progress")
              render   = js.defined(() => ProgressDemo)
            }
          )
        }),
    )
  )
}
