package demo

import typings.axiosLib.axiosMod.AxiosRequestConfig
import typings.axiosLib.axiosMod.^.{default => Axios}
import typings.csstypeLib.csstypeLibStrings
import typings.csstypeLib.csstypeMod.{Properties, StandardLonghandProperties, StandardProperties}
import typings.materialDashUiLib.{materialDashUiLibComponents => Mui}
import typings.mobxDashReactLib.mobxDashReactMod.^.observer
import typings.mobxLib.libTypesObservablevalueMod.IObservableValue
import typings.mobxLib.{mobxMod => MobX}
import typings.reactLib.reactMod._
import typings.stdLib.^.{console, window}

import scala.scalajs.js

object GithubSearch {
  import typings.reactLib.dsl._

  class Props(val store: Store) extends js.Object

  trait Repository extends js.Object {
    val description:      String
    val forks_count:      Int
    val name:             String
    val stargazers_count: Int
    val html_url:         String
  }

  trait Response extends js.Object {
    val items: js.Array[Repository]
  }

  class Store() extends js.Object {
    val search: IObservableValue[String] =
      MobX.^.observable.box("ScalablyTyped")

    val result: IObservableValue[js.UndefOr[js.Array[Repository]]] =
      MobX.^.observable.box(js.undefined)

    def searchForRepos: js.Function0[Unit] =
      MobX.^.action(
        "searchForRepos",
        () =>
          Axios
            .get[Response](
              "https://api.github.com/search/repositories",
              AxiosRequestConfig(
                params  = js.Dynamic.literal(q      = search.get(), sort = "stars"),
                headers = js.Dynamic.literal(Accept = "application/vnd.github.v3+json"),
              )
            )
            .`then`[Unit]({ res =>
              console.warn("got data", res.data)
              result.set(res.data.items)
            }, js.defined(err => console.warn("request rejected", err.toString)))
      )
  }

  /* this is a simple functional component to display a github repo in a table row */
  private val RepoRow = define.fc[Repository](
    repo =>
      Mui.TableRow.noprops(
        Mui.TableRowColumn.noprops(repo.name),
        Mui.TableRowColumn.noprops(repo.forks_count),
        Mui.TableRowColumn.noprops(repo.stargazers_count),
        Mui.TableRowColumn.noprops(
          Mui.FlatButton.props(
            Mui.FlatButtonProps(
              disabled = false,
              onClick  = _ => window.location.href = repo.html_url
            ),
            "Go to project"
          )
        ),
    )
  )

  private class C extends Component[Props, js.Any, js.Any] {
    override def render(): ReactNode =
      div.noprops(
        Mui.Paper.props(
          Mui.PaperProps(
            style = Properties(
              StandardProperties = StandardProperties(
                StandardLonghandProperties = StandardLonghandProperties(
                  height         = "100px",
                  display        = csstypeLibStrings.flex,
                  alignItems     = csstypeLibStrings.center,
                  justifyContent = csstypeLibStrings.center
                )
              )
            ),
            rounded = true
          )
        ),
        Mui.TextField.props(
          Mui.TextFieldProps(
            name     = "search",
            value    = props.store.search.get,
            onChange = (_, newValue) => props.store.search.set(newValue)
          )
        ),
        Mui.FlatButton.props(
          Mui.FlatButtonProps(onClick = _ => props.store.searchForRepos()),
          "Search"
        ),
        props.store.result
          .get()
          .fold[ReactNode](div.noprops("No result yet"))(
            repos =>
              Mui.Table.noprops(
                Mui.TableHeader.noprops(
                  Mui.TableRow.noprops(
                    Mui.TableRowColumn.noprops("name"),
                    Mui.TableRowColumn.noprops("forks_count"),
                    Mui.TableRowColumn.noprops("stargazers_count"),
                    Mui.TableRowColumn.noprops("link"),
                  )
                ),
                Mui.TableBody.noprops(
                  repos.map(repo => RepoRow.withKey(repo.name).props(repo))
                )
            )
          )
      )
  }

  /* this applies a decorator. Sadly, we're missing good syntax for this so far */
  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])
}
