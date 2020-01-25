package demo

import typings.storybookReact.mod.storiesOf
import typings.node.module
import typings.react.mod.ButtonHTMLAttributes
import typings.std.window

object Demo {
  import typings.react.dsl._

  def main(args: Array[String]): Unit =
    storiesOf("Button", module)
      .add("with text", ctx => button.noprops("Hello Button"))
      .add(
        "with some emoji",
        ctx =>
          button.props(
            ButtonHTMLAttributes(
              onClick      = e => window.alert(s"x: ${e.pageX}, y: ${e.pageY}"),
              `aria-label` = "so cool",
              role         = "img"
            ),
            span.noprops("😀😎")
          )
      )
}
