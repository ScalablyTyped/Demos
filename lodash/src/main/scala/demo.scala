import org.scalablytyped.runtime.NumberDictionary
import typings.lodash.fpMod as Fp
import typings.lodash.mod.{ArrayIterator, MemoListIterator, ^ as L}
import typings.node.global.console
import typings.std.ArrayLike

import scala.scalajs.js

class Person(val name: String, val age: Int) extends js.Object

val Fiona   = new Person("Fiona First", 1)
val Sam     = new Person("Sam Second", 101)
val Persons = js.Array(Fiona, Sam)

// matches structurally
def isArrayLike[T](ts: js.Array[T]): ArrayLike[T] =
  ts.asInstanceOf[ArrayLike[T]]

@main
def main: Unit =

  val summarizeNames: MemoListIterator[Person, String, js.Array[Person]] =
    (prev, curr, idx, all) => prev + " and " + curr.name

  val value2 = L.reduce(Persons, summarizeNames, "")
  console.log("Summarized names of two persons", value2)

  val value3 = L.reduce(js.Array[Person](), summarizeNames, "")
  console.log("Summarized names of no persons", value3)

  val toAge: ArrayIterator[Person, Int] =
    (curr, _, _) => curr.age

  console.log("Ages for persons", L.map(Persons.asInstanceOf[NumberDictionary[Double]], toAge))

  console.log("fields for Fiona", L.entriesIn(Fiona))

  console.log("Dropped first", Fp.^.drop(1, isArrayLike(Persons)))
end main
