package demo

import typings.reactDashDomLib.reactDashDomMod.^.render
import typings.reactLib.Element
import typings.reactLib.dsl.fromComponent
import typings.stdLib.^.window

object Main {
  def main(argv: Array[String]): Unit = {
    val props = new MainTabs.Props {
      val githubStore = new GithubSearch.Store()
      val testStore   = new MobXTest.Store
    }

    render(
      MainTabs.Component.props(props),
      window.document.body.asInstanceOf[Element]
    )
  }
}
