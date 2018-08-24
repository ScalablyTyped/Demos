package demo

import typings.reactDashDomLib.reactDashDomMod.reactDashDomModMembers.render
import typings.reactLib.Element
import typings.reactLib.ReactDsl.component
import typings.stdLib.stdLibMembers.window

object Main {
  def main(argv: Array[String]): Unit = {
    val props = new MainTabs.Props {
      val githubStore = new GithubSearch.Store()
      val testStore   = new MobXTest.Store
    }

    render(
      component(MainTabs.Component)(props),
      window.document.body.asInstanceOf[Element]
    )
  }
}
