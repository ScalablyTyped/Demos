package demo

import org.scalablytyped.runtime.NumberDictionary
import typings.lodash.mod.{ArrayIterator, MemoListIterator, ^ => L}
import typings.lodash.{fpMod => Fp}
import typings.node.global.console
import typings.std.ArrayLike

import scala.scalajs.js
import scala.scalajs.js.|

object LodashDemo {

  class Person(val name: String, val age: Int) extends js.Object

  val Fiona   = new Person("Fiona First", 1)
  val Sam     = new Person("Sam Second", 101)
  val Persons = js.Array(Fiona, Sam)

  def main(args: Array[String]): Unit = {

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

    console.log("Dropped first", Fp.^.drop(1, Knowledge.isArrayLike(Persons)))
  }
}

object Knowledge {
  def isArrayLike[T](ts: js.Array[T]): ArrayLike[T] =
    ts.asInstanceOf[ArrayLike[T]]
}
