package demo
package heroeditor

import typings.atAngularCoreLib.atAngularCoreMod.{^ => Core}
import typings.rxjsLib.internalObservableMod.Observable
import typings.rxjsLib.rxjsMod.{^ => rxjs}

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExportStatic

final class HeroService(messageService: MessageService) extends js.Object {
  def heroes: Observable[js.Array[Hero]] = {
    messageService.add("HeroService: fetched heroes")
    rxjs.of(MockHeroes.heroes)
  }

  def getHero(id: Int): js.UndefOr[Hero] = {
    messageService.add(s"HeroService: fetched hero id=$id")
    MockHeroes.heroes.find(_.id == id).orUndefined
  }
}

object HeroService {
  @JSExportStatic
  val annotations = js.Array(Core.Injectable.newInstance0())

  @JSExportStatic
  val parameters = js.Array(typeOf[MessageService])
}
