package demo

import org.scalablytyped.runtime.StringDictionary
import typings.reactDashReduxLib.ReduxFacade.Extractor
import typings.reactLib.reactMod.ReactNs.{AnchorHTMLAttributes, InputHTMLAttributes}
import typings.reduxLib.reduxMod.{Action, Dispatch, Reducer}
import typings.semanticDashUiDashReactLib.distCommonjsElementsButtonButtonMod.ButtonProps
import typings.semanticDashUiDashReactLib.distCommonjsElementsInputInputMod.InputProps
import typings.semanticDashUiDashReactLib.distCommonjsElementsListListHeaderMod.ListHeaderProps
import typings.semanticDashUiDashReactLib.distCommonjsElementsListListIconMod.ListIconProps
import typings.semanticDashUiDashReactLib.distCommonjsElementsListListMod.ListProps
import typings.semanticDashUiDashReactLib.semanticDashUiDashReactLibStrings
import typings.semanticDashUiDashReactLib.semanticDashUiDashReactMod.{Button, Input, ^ => Sui}
import typings.stdLib.^.{console, fetch}
import typings.stdLib.{HTMLAnchorElement, HTMLInputElement, Record, RequestInit}
import typings.stdLib.ThenableOps.ThenableOps

import scala.scalajs.js
import scala.scalajs.js.Promise

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

    def doSearch(search: String): Promise[Response] = {
      val init = new RequestInit {
        headers = StringDictionary("Accept" -> "application/vnd.github.v3+json"): Record[String, String]
      }

      fetch(s"https://api.github.com/search/repositories?q=$search&sort=stars", init)
        .flatMap(_.json())
        .assertType[Response]
        .map { res =>
          console.warn("got data", res.items)
          res
        }
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

  trait Props extends js.Object {
    val state:    State
    val dispatch: Dispatch[SearchAction]
  }

  val C = define.fc[Props] { props =>
    div.noprops(
      cls[Input].props(
        new InputProps {
          val defaultValue = props.state.search
          onChange = js.defined((_, data) => props.dispatch(SearchTextChanged(data.value)))
        },
        input.props(new InputHTMLAttributes[HTMLInputElement] {}),
        cls[Button].props(new ButtonProps {
          onClick = js.defined(
            (e, data) =>
              api
                .doSearch(props.state.search)
                .foreach(res => props.dispatch(SearchReposSuccess(res.items)))
          )
        })
      ),
      props.state.repos.map(
        repos =>
          Sui.List.props(
            new ListProps {
              divided = true
              relaxed = true
            },
            repos.map(
              repo =>
                Sui.List.Item_Original
                  .withKey(repo.name)
                  .noprops(
                    Sui.List.Icon_Original.props(new ListIconProps {
                      name          = "github"
                      size          = semanticDashUiDashReactLibStrings.large
                      verticalAlign = semanticDashUiDashReactLibStrings.middle
                    }),
                    Sui.List.Content_Original.noprops(
                      Sui.List.Header_Original.props(new ListHeaderProps {
                        content = js.defined(a.props(new AnchorHTMLAttributes[HTMLAnchorElement] {
                          href = repo.html_url
                        }, repo.full_name))
                      }),
                      Sui.List.Description_Original.noprops(repo.description)
                    )
                )
            )
        )
      )
    )
  }
}
