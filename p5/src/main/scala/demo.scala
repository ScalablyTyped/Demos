import org.scalajs.dom.console
import typings.p5.mod.{p5InstanceExtensions, ^ as P5}

import scala.scalajs.js

@main
def main: Unit =
  val sketch = P5Facade { p =>
    val x = 100
    val y = 100

    p.setup = () => p.createCanvas(700, 410)

    p.draw = () =>
      p.background(0)
      p.fill(255)
      p.rect(x, y, 50, 50)
  }
  console.warn(sketch.windowHeight)
end main


object P5Facade:

  /** We need this because the function `sketch` provided to `P5` has not been specified well in typescript
    *
    * @param sketch
    *   a closure that can set optional preload(), setup(), and/or draw() properties on the given p5 instance
    */
  def apply(sketch: js.Function1[P5Config with p5InstanceExtensions, Unit]): P5 =
    new P5(sketch.asInstanceOf[js.Function1[js.Any, js.Any]])

  /** We need this because in the `p5` trait, the functions have been translated to methods, so we can't change them
    */
  @js.native
  trait P5Config extends js.Object:
    var draw:    js.Function0[Unit] = js.native
    var preload: js.Function0[Unit] = js.native
    var remove:  js.Function0[Unit] = js.native
    var setup:   js.Function0[Unit] = js.native
  end P5Config
end P5Facade
