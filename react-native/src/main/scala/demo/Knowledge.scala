package demo

import scala.language.implicitConversions

object Knowledge {

  /**
    * It's difficult to convince scalac that all these types match.
    * Sooo, we cheat a bit.
    */
  @inline implicit class Force[T](t: T) {
    def force[U]: U = t.asInstanceOf[U]
  }
}
