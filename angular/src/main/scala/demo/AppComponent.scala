package demo

import typings.angularCore.mod.{Component, ComponentCls, OnInit}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

/** An example of a component. The @Component decorator is taken care of in the companion object, under annotations.
  *
  * AppComponent extends from [[OnInit]] so that it does stuff when initiated.
  */
final class AppComponent extends OnInit:
  val title: String = "Angular with Scala.js"

  def subtitle(): String = "This is a subtitle"

  override def ngOnInit(): Unit = println("AppComponent")
end AppComponent

object AppComponent:

  /** The @Component decorator for AppComponent.
    */
  @JSExportStatic
  val annotations = js.Array(
    new ComponentCls(
      new Component {}
        .setSelector("app-root")
        .setTemplate("""
<h1>{{ title }}</h1>
<nav>
    <a routerLink="/dashboard">Dashboard</a>
    <a routerLink="/heroes">Heroes</a>
</nav>
<router-outlet></router-outlet>

<app-messages></app-messages>
""").setStylesVarargs("""
h1 {
  font-size: 1.2em;
  color: #999;
  margin-bottom: 0;
}
h2 {
  font-size: 2em;
  margin-top: 0;
  padding-top: 0;
}
nav a {
  padding: 5px 10px;
  text-decoration: none;
  margin-top: 10px;
  display: inline-block;
  background-color: #eee;
  border-radius: 4px;
}
nav a:visited, a:link {
  color: #607d8b;
}
nav a:hover {
  color: #039be5;
  background-color: #cfd8dc;
}
nav a.active {
  color: #039be5;
}
""")
    )
  )
end AppComponent
