import typings.angularCore.mod.Type

import scala.language.higherKinds
import scala.scalajs.js
import scala.scalajs.js.|

package object demo:

  /** Get the Type[T] of a Class, by calling js.constructorOf
    */
  @inline def typeOf[T <: js.Any](implicit tag: js.ConstructorTag[T]): Type[js.Any] =
    tag.constructor.asInstanceOf[Type[js.Any]]

  /* pretend that the world is contravariant */
  @inline def unspecify[M[_], T <: js.Object](mt: M[T]): M[js.Object] =
    mt.asInstanceOf[M[js.Object]]

  @inline def asOption[T](ot: T | Null): Option[T] =
    Option(ot.asInstanceOf[T])
end demo
