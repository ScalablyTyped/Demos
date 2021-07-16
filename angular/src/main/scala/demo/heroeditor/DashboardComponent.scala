package demo
package heroeditor

import typings.angularCore.mod.{Component, ComponentCls, OnInit}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class DashboardComponent(heroService: HeroService) extends OnInit:
  var heroes: js.Array[Hero] = MockHeroes.heroes.slice(1, 5)

  override def ngOnInit(): Unit =
    getHeroes()

  def getHeroes(): Unit =
    heroService.heroes.subscribe(hs => heroes = hs.slice(1, 5))

object DashboardComponent:

  @JSExportStatic
  val annotations = js.Array(
    new ComponentCls(
      new Component {}
        .setSelector("app-dashboard")
        .setTemplate(
          """
          |<h3>Top Heroes</h3>
          |<div class="grid grid-pad">
          |    <a *ngFor="let hero of heroes" class="col-1-4"
          |       routerLink="/detail/{{hero.id}}">
          |        <div class="module hero">
          |            <h4>{{hero.name}}</h4>
          |        </div>
          |    </a>
          |</div>
          |
        """.stripMargin
        )
        .setStylesVarargs("""
          |/* DashboardComponent's private CSS styles */
          |[class*='col-'] {
          |  float: left;
          |  padding-right: 20px;
          |  padding-bottom: 20px;
          |}
          |[class*='col-']:last-of-type {
          |  padding-right: 0;
          |}
          |a {
          |  text-decoration: none;
          |}
          |*, *:after, *:before {
          |  -webkit-box-sizing: border-box;
          |  -moz-box-sizing: border-box;
          |  box-sizing: border-box;
          |}
          |h3 {
          |  text-align: center; margin-bottom: 0;
          |}
          |h4 {
          |  position: relative;
          |}
          |.grid {
          |  margin: 0;
          |}
          |.col-1-4 {
          |  width: 25%;
          |}
          |.module {
          |  padding: 20px;
          |  text-align: center;
          |  color: #eee;
          |  max-height: 120px;
          |  min-width: 120px;
          |  background-color: #607d8b;
          |  border-radius: 2px;
          |}
          |.module:hover {
          |  background-color: #eee;
          |  cursor: pointer;
          |  color: #607d8b;
          |}
          |.grid-pad {
          |  padding: 10px 0;
          |}
          |.grid-pad > [class*='col-']:last-of-type {
          |  padding-right: 20px;
          |}
          |@media (max-width: 600px) {
          |  .module {
          |    font-size: 10px;
          |    max-height: 75px; }
          |}
          |@media (max-width: 1024px) {
          |  .grid {
          |    margin: 0;
          |  }
          |  .module {
          |    min-width: 60px;
          |  }
          |}
        """.stripMargin)
    )
  )

  @JSExportStatic
  val parameters = js.Array(typeOf[HeroService])
end DashboardComponent
