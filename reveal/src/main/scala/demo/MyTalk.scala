package demo

import demo.PresentationUtil.Enumeration.*
import demo.PresentationUtil.*
import japgolly.scalajs.react.component.Scala.Component
import japgolly.scalajs.react.{CtorType, ScalaComponent}
import japgolly.scalajs.react.vdom.html_<^.*

object MyTalk:

  val chapter1 = chapter(
    chapterSlide(
      <.h2("Build your presentations with ScalaJS + reveal.js"),
      <.br,
      <.h4("move down (down-arrow)")
    ),
    headerSlide(
      "reveal.js commands",
      <.p("Press 'f' to go full-screen and ESC to see an overview of your slides."),
      <.br,
      <.p("You can navigate to the right and down.")
    ),
    headerSlide(
      "My Header",
      <.h3("Headers everywhere")
    ),
    headerSlide(
      "Enumeration",
      Enumeration(
        Item.stable("always show this item"),
        Item.fadeIn("I fade in"),
        Item.stable("I am also always here")
      )
    ),
    headerSlide(
      "Code, so much code",
      scalaC("""
        def main(args: Array[String]): Unit = {
          println("hello, world")
        }
      """),
      scalaFragment("""
        def moreSideEffects(): Unit = {
          println("pop in")
        }
      """)
    ),
    noHeaderSlide(
      <.h3("Or have a blank slide")
    )
  )

  val chapter2 = chapter(
    chapterSlide(
      <.h2("Where can I find more information?")
    ),
    headerSlide(
      "about reveal.js",
      <.a(
        ^.href := "https://github.com/hakimel/reveal.js/",
        "reveal.js"
      )
    ),
    headerSlide(
      "about ScalaJS",
      <.a(
        ^.href := "https://www.scala-js.org",
        "ScalaJS"
      )
    )
  )

  val Talk: Component[Unit, Unit, Unit, CtorType.Nullary] = ScalaComponent
    .builder[Unit]("Presentation")
    .renderStatic(
      <.div(
        ^.cls := "reveal",
        <.div(
          ^.cls := "slides",
          chapter1,
          chapter2
        )
      )
    )
    .build(CtorType.Summoner.summonN, implicitly)
end MyTalk
