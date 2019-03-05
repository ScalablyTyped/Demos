package demo

import typings.reactDashDomLib.reactDashDomMod.^.render
import typings.reactLib.Element
import typings.reactLib.dsl.fromComponent
import typings.stdLib.^.window

object Main {
  def main(argv: Array[String]): Unit =
    render(
      MainTabs.Component.props(new MainTabs.Props(new MobXTest.Store, new GithubSearch.Store())),
      window.document.body.asInstanceOf[Element]
    )
}
