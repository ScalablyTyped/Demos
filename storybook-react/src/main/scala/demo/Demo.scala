package demo

import typings.atStorybookReactLib.atStorybookReactMod.RenderFunction
import typings.atStorybookReactLib.atStorybookReactMod.^.storiesOf
import typings.nodeLib.^.module
import typings.reactLib.reactMod.ReactNs.ButtonHTMLAttributes
import typings.stdLib.HTMLButtonElement
import typings.stdLib.^.window

import scala.scalajs.js

object Demo {
  import typings.reactLib.dsl._

  /* This is really just translating P to js.Any, since ReactElement is not covariant in scala */
  def fn[P](f: js.Function0[P]): RenderFunction =
    f.asInstanceOf[RenderFunction]

  def main(args: Array[String]): Unit =
    storiesOf("Button", module)
      .add("with text", fn(() => button.noprops("Hello Button")))
      .add(
        "with some emoji",
        fn(
          () =>
            button.props(
              new ButtonHTMLAttributes[HTMLButtonElement] {
                `aria-label` = "so cool"
                role         = "img"
                onClick = js.defined(e => {
                  window.alert(s"x: ${e.pageX}, y: ${e.pageY}")
                })
              },
              span.noprops("😀😎")
          )
        )
      )
}
