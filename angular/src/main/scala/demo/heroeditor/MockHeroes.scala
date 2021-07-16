package demo.heroeditor

import scala.scalajs.js

object MockHeroes:

  private val names = js.Array(
    "Mr. Nice",
    "Narco",
    "Bombasto",
    "Celeritas",
    "Magneta",
    "RubberMan",
    "Dynama",
    "Dr IQ",
    "Magma",
    "Tornado"
  )

  val heroes: js.Array[Hero] =
    names.zipWithIndex.map { case (n, index) => new Hero(index + 11, n) }
end MockHeroes
