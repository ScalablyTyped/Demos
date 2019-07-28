package demo

import typings.atStorybookReact.atStorybookReactMod.RenderFunction
import typings.atStorybookReact.atStorybookReactMod.^.storiesOf
import typings.node.^.module
import typings.react.reactMod.{ButtonHTMLAttributes, HTMLAttributes}
import typings.std.^.window

import scala.scalajs.js

object Demo {
  import typings.react.dsl._

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
              ButtonHTMLAttributes(
                HTMLAttributes(
                  onClick      = e => window.alert(s"x: ${e.pageX}, y: ${e.pageY}"),
                  `aria-label` = "so cool",
                  role         = "img",
                ),
              ),
              span.noprops("😀😎")
          )
        )
      )
}
