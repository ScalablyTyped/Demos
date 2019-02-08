package demo

import typings.reactLib.dsl
import typings.reactLib.dsl.BuildComponent
import typings.reactLib.reactMod.ReactNs.{ComponentClass, ComponentType}

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.|

object Knowledge {

  /**
    * It's difficult to convince scalac that all these types match.
    * Sooo, we cheat a bit.
    */
  @inline implicit class Force[T](t: T) {
    def force[U]: U = t.asInstanceOf[U]
  }

  @inline def nullToUndef[T](t: T | Null): js.UndefOr[T] =
    if (t == null) js.undefined else js.defined(t.asInstanceOf[T])

  @inline implicit def fromComponentType[P](_ctor: ComponentType[P]): BuildComponent[P] =
    dsl.c(_ctor.asInstanceOf[ComponentClass[P, _]])
}
