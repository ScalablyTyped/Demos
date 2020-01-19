package demo

import typings.lodash.mod.{ArrayIterator, MemoListIterator, ^ => L}
import typings.lodash.{fpMod => Fp}
import typings.std.{console, ArrayLike}

import scala.scalajs.js

object LodashDemo {

  class Person(val name: String, val age: Int) extends js.Object

  val Fiona   = new Person("Fiona First", 1)
  val Sam     = new Person("Sam Second", 101)
  val Persons = js.Array(Fiona, Sam)

  def main(args: Array[String]): Unit = {

    val summarizeNames: MemoListIterator[Person, js.UndefOr[String], js.Array[Person]] =
      (prevU, curr, idx, all) =>
        prevU.fold(curr.name) { prev =>
          prev + " and " + curr.name
        }

    val value2 = L.reduce(Persons, summarizeNames, js.undefined)
    console.log("Summarized names of two persons", value2)

    val value3 = L.reduce(js.Array[Person](), summarizeNames, js.undefined)
    console.log("Summarized names of no persons", value3)

    val toAge: ArrayIterator[Person, Int] =
      (curr, _, _) => curr.age

    console.log("Ages for persons", L.map(Persons, toAge))

    console.log("fields for Fiona", L.entriesIn(Fiona))

    console.log("Dropped first", Fp.^.drop(1, Knowledge.isArrayLike(Persons)))
  }
}

object Knowledge {
  def isArrayLike[T](ts: js.Array[T]): ArrayLike[T] =
    ts.asInstanceOf[ArrayLike[T]]
}
