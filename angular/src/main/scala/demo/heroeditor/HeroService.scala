package demo
package heroeditor

import typings.atAngularCore.atAngularCoreMod.InjectableCls
import typings.rxjs.internalObservableMod.Observable
import typings.rxjs.rxjsMod.{^ => rxjs}

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
  val annotations = js.Array(new InjectableCls)

  @JSExportStatic
  val parameters = js.Array(typeOf[MessageService])
}
