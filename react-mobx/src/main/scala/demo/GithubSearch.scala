package demo

import typings.axiosLib.axiosMod.AxiosRequestConfig
import typings.axiosLib.axiosMod.axiosModMembers.{default => Axios}
import typings.materialDashUiLib.flatbuttonMod.{default => FlatButton}
import typings.materialDashUiLib.paperMod.{default => Paper}
import typings.materialDashUiLib.tableMod._
import typings.materialDashUiLib.textfieldMod.{default => TextField}
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.{FlatButtonProps, PaperProps, TextFieldProps}
import typings.mobxDashReactLib.mobxDashReactMod.mobxDashReactModMembers.observer
import typings.mobxLib.libTypesObservablevalueMod.IObservableValue
import typings.mobxLib.mobxMod.{mobxModMembers => MobX}
import typings.reactLib.Anon_Children
import typings.reactLib.ReactDsl.{component, componentClass, div}
import typings.reactLib.reactMod.ReactNs.{CSSProperties, ComponentClass, FC, ReactNode}
import typings.reactLib.reactMod._
import typings.stdLib.stdLibMembers.{console, window}

import scala.scalajs.js

object GithubSearch {
  trait Props extends js.Object {
    val store: Store
  }

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
      MobX.observable.box("ScalablyTyped")

    val result: IObservableValue[js.UndefOr[js.Array[Repository]]] =
      MobX.observable.box(js.undefined)

    def searchForRepos: js.Function0[Unit] =
      MobX.action(
        "searchForRepos",
        () =>
          Axios
            .get[Response](
              "https://api.github.com/search/repositories",
              new AxiosRequestConfig {
                params = js.Dynamic.literal(q = search.get(), sort = "stars")

                headers = js.Dynamic.literal(Accept = "application/vnd.github.v3+json")
              }
            )
            .`then`[Unit] { res =>
              console.warn("got data", res.data)
              result.set(res.data.items)
          }
      )
  }

  /* this is a simple functional component to display a github repo in a table row */
  private val RepoRow = Knowledge.fc[Repository](
    repo =>
      componentClass[TableRow].noprops(
        componentClass[TableRowColumn].noprops(repo.name),
        componentClass[TableRowColumn].noprops(repo.forks_count),
        componentClass[TableRowColumn].noprops(repo.stargazers_count),
        componentClass[TableRowColumn].noprops(
          componentClass[FlatButton].apply(new FlatButtonProps {
            onClick = js.defined(_ => window.location.assign(repo.html_url))
          }, "Go to project")
        ),
    )
  )

  private class C extends Component[Props, js.Any, js.Any] {
    override def render(): ReactNode =
      div.noprops(
        componentClass[Paper].apply(
          new PaperProps {
            style = new CSSProperties {
              height         = "100px"
              display        = "flex"
              alignItems     = "center"
              justifyContent = "center"
            }
            rounded = true
          },
          componentClass[TextField].apply(new TextFieldProps {
            name     = "search"
            value    = props.store.search.get
            onChange = js.defined((_, newValue) => props.store.search.set(newValue))
          }),
          componentClass[FlatButton].apply(
            new FlatButtonProps { onClick = js.defined(_ => props.store.searchForRepos()) },
            "Search"
          ),
        ),
        props.store.result
          .get()
          .fold[ReactNode](div.noprops("No result yet"))(
            repos =>
              componentClass[Table].noprops(
                componentClass[TableHeader].noprops(
                  componentClass[TableRow].noprops(
                    componentClass[TableRowColumn].noprops("name"),
                    componentClass[TableRowColumn].noprops("forks_count"),
                    componentClass[TableRowColumn].noprops("stargazers_count"),
                    componentClass[TableRowColumn].noprops("link"),
                  )
                ),
                componentClass[TableBody].noprops(
                  repos.map(repo => component(RepoRow).withKey(repo.name)(repo))
                )
            )
          )
      )
  }

  /* this applies a decorator. Sadly, we're missing good syntax for this so far */
  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])
}

object Knowledge {
  // this is missing from the react dsl for now. Defines a functional component
  def fc[P](f: js.Function1[P with Anon_Children, ReactNode]): FC[P] =
    f.asInstanceOf[FC[P]]
}
