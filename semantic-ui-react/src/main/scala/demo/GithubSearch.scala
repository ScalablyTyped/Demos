package demo

import org.scalablytyped.runtime.StringDictionary
import typings.reactDashReduxLib.ReduxFacade.Extractor
import typings.reactLib.reactMod.AnchorHTMLAttributes
import typings.reduxLib.reduxMod.{Action, Dispatch, Reducer}
import typings.semanticDashUiDashReactLib.{
  semanticDashUiDashReactLibStrings,
  semanticDashUiDashReactLibComponents => Sui
}
import typings.stdLib.RequestInit
import typings.stdLib.^.{console, fetch}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js

object GithubSearch {
  import typings.reactLib.dsl._

  object api {
    trait Repository extends js.Object {
      val description:      String
      val forks_count:      Int
      val name:             String
      val full_name:        String
      val stargazers_count: Int
      val html_url:         String
    }

    trait Response extends js.Object {
      val items: js.Array[Repository]
    }

    trait GithubError extends js.Object {
      val message:           String
      val documentation_url: String
    }

    def doSearch(search: String): Future[Either[GithubError, Response]] =
      fetch(
        input = s"https://api.github.com/search/repositories?q=$search&sort=stars",
        init  = RequestInit(headers = js.Array(js.Array("Accept", "application/vnd.github.v3+json")))
      ).toFuture.flatMap {
        case res if res.status == 200 =>
          res.json().toFuture.map(data => Right(data.asInstanceOf[Response]))
        case errorRes =>
          errorRes.json().toFuture.map(data => Left(data.asInstanceOf[GithubError]))
      }
  }

  trait State extends js.Object {
    val search: String
    val repos: js.UndefOr[js.Array[api.Repository]] = js.undefined
  }

  object State {
    val initial: State = new State {
      val search = "ScalablyTyped"
    }

    def apply(_search: String, _repos: js.UndefOr[js.Array[api.Repository]]): State =
      new State {
        val search         = _search
        override val repos = _repos
      }
  }

  sealed trait SearchAction extends Action[String]

  trait SearchTextChanged extends SearchAction {
    val value: String
  }

  object SearchTextChanged extends Extractor[SearchTextChanged] {
    protected val _type = "SEARCH_TEXT_CHANGED"

    def apply(_value: String): SearchTextChanged =
      new SearchTextChanged {
        var `type` = _type
        val value  = _value
      }
  }

  trait SearchReposSuccess extends SearchAction {
    val repos: js.Array[api.Repository]
  }

  object SearchReposSuccess extends Extractor[SearchReposSuccess] {
    protected val _type = "SEARCH_REPOS_SUCCESS"

    def apply(_repos: js.Array[api.Repository]): SearchReposSuccess =
      new SearchReposSuccess {
        var `type` = _type
        val repos  = _repos
      }
  }

  val Reducer: Reducer[State, SearchAction] = (stateOpt, action) => {
    val state = stateOpt.getOrElse(State.initial)
    action match {
      case SearchTextChanged(x)  => State(x.value, state.repos)
      case SearchReposSuccess(x) => State(state.search, x.repos)
      case _                     => state
    }
  }

  class Props(val state: State, val dispatch: Dispatch[SearchAction]) extends js.Object

  val C = define.fc[Props] { props =>
    div.noprops(
      Sui.Input.props(
        Sui.InputProps(StringDictionary("defaultValue" -> props.state.search),
                       onChange = (_, data) => props.dispatch(SearchTextChanged(data.value))),
        input.noprops(),
        Sui.Button.props(
          Sui.ButtonProps(
            onClick = (e, data) =>
              api.doSearch(props.state.search).foreach(res => props.dispatch(SearchReposSuccess(res.items)))
          )
        )
      ),
      props.state.repos.map(
        repos =>
          Sui.List.props(
            Sui.ListProps(divided = true, relaxed = true),
            repos.map(
              repo =>
                Sui.ListItem
                  .withKey(repo.name)
                  .noprops(
                    Sui.ListIcon.props(
                      Sui.ListIconProps(name          = semanticDashUiDashReactLibStrings.github,
                                        size          = semanticDashUiDashReactLibStrings.large,
                                        verticalAlign = semanticDashUiDashReactLibStrings.middle)
                    ),
                    Sui.ListContent.noprops(
                      Sui.ListHeader.props(
                        Sui.ListHeaderProps(
                          content = a.props(AnchorHTMLAttributes(href = repo.html_url), repo.full_name)
                        )
                      ),
                      Sui.ListDescription.noprops(repo.description)
                    )
                )
            )
        )
      )
    )
  }
}
