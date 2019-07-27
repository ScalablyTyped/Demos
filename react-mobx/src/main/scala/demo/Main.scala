package demo

import typings.reactDashDom.reactDashDomMod.^.render
import typings.std.^.window

object Main {
  import typings.react.dsl._

  def main(argv: Array[String]): Unit =
    render(
      MainTabs.Component.props(new MainTabs.Props(new MobXTest.Store, new GithubSearch.Store())),
      window.document.body
    )
}
