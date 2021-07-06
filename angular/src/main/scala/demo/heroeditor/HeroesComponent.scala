package demo
package heroeditor

import typings.angularCore.mod.{Component, ComponentCls, OnInit}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class HeroesComponent(heroService: HeroService) extends OnInit:

  var heroes: js.Array[Hero] = _

  def getHeroes(): Unit =
    heroService.heroes.subscribe(hs => heroes = hs)
    heroes = MockHeroes.heroes

  override def ngOnInit(): Unit =
    println("Heroes Component")

    getHeroes()
end HeroesComponent

object HeroesComponent:
  @JSExportStatic
  val annotations = js.Array(
    new ComponentCls(
      new Component {}
        .setSelector("app-heroes")
        .setTemplate(
          """
            |
            |<h2>My Heroes</h2>
            |
            |<ul class="heroes">
            |    <li *ngFor="let hero of heroes">
            |        <a routerLink="/detail/{{hero.id}}">
            |            <span class="badge">{{hero.id}}</span> {{hero.name}}
            |        </a>
            |    </li>
            |</ul>
        """.stripMargin
        )
        .setStylesVarargs(
          """
          |/* HeroesComponent's private CSS styles */
          |.selected {
          |  background-color: #CFD8DC !important;
          |  color: white;
          |}
          |.heroes {
          |  margin: 0 0 2em 0;
          |  list-style-type: none;
          |  padding: 0;
          |  width: 15em;
          |}
          |.heroes li {
          |  cursor: pointer;
          |  position: relative;
          |  left: 0;
          |  background-color: #EEE;
          |  margin: .5em;
          |  padding: .3em 0;
          |  height: 1.6em;
          |  border-radius: 4px;
          |}
          |.heroes li.selected:hover {
          |  background-color: #BBD8DC !important;
          |  color: white;
          |}
          |.heroes li:hover {
          |  color: #607D8B;
          |  background-color: #DDD;
          |  left: .1em;
          |}
          |.heroes .text {
          |  position: relative;
          |  top: -3px;
          |}
          |.heroes .badge {
          |  display: inline-block;
          |  font-size: small;
          |  color: white;
          |  padding: 0.8em 0.7em 0 0.7em;
          |  background-color: #607D8B;
          |  line-height: 1em;
          |  position: relative;
          |  left: -1px;
          |  top: -4px;
          |  height: 1.8em;
          |  min-width: 16px;
          |  text-align: right;
          |  margin-right: .8em;
          |  border-radius: 4px 0 0 4px;
          |}
        """.stripMargin
        )
    )
  )

  @JSExportStatic
  val parameters = js.Array(typeOf[HeroService])
end HeroesComponent
