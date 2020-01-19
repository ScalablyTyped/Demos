package demo

import typings.react.mod.ReactElement
import typings.reactRedux.ReduxFacade.Connected
import typings.semanticUiReact.{
  AnonMenuItem => TabStructure,
  semanticUiReactComponents => Sui,
  semanticUiReactStrings => SuiStrings
}

import scala.scalajs.js

object Demo {
  import typings.react.dsl._

  class Props(val title: String) extends js.Object

  val CardDemo: ReactElement =
    Sui.Card.props(
      Sui.CardProps(color = SuiStrings.orange),
      Sui.CardHeader.noprops("CardHeader"),
      Sui.CardMeta.noprops("CardMeta"),
      Sui.Divider.noprops(),
      Sui.Search.props(Sui.SearchProps(minCharacters = 1))
    )

  val ProgressDemo: ReactElement =
    Sui.Card.noprops(
      Sui.Progress.props(Sui.ProgressProps(percent = 70, warning  = true)),
      Sui.Progress.props(Sui.ProgressProps(percent = 100, warning = false))
    )

  val C = define.fc[Connected[GithubSearch.State, GithubSearch.SearchAction] with Props](
    props =>
      div.noprops(
        Sui.Header.props(Sui.HeaderProps(size = SuiStrings.large), props.title),
        Sui.Tab.props(
          Sui.TabProps(
            panes = js.Array(
              TabStructure(
                menuItem = "Repo search",
                render   = () => GithubSearch.C.props(new GithubSearch.Props(props.state, props.dispatch))
              ),
              TabStructure(menuItem = "Card", render     = () => CardDemo),
              TabStructure(menuItem = "Progress", render = () => ProgressDemo)
            )
          )
        )
      )
  )
}
