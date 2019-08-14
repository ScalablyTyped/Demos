package demo

import typings.materialDashUi.{materialDashUiComponents => Mui}
import typings.mobx.libCoreComputedvalueMod.IComputedValue
import typings.mobx.libTypesObservablevalueMod.IObservableValue
import typings.mobx.mobxMod.{^ => MobX}
import typings.mobxDashReact.mobxDashReactMod.^.observer
import typings.react.reactMod._
import typings.std.^.console

import scala.scalajs.js

object MobXTest {
  case class Foo(str: String, num: Double)

  case class Bar(strnum: String)

  class Store() extends js.Object {
    val foo: IObservableValue[Foo] =
      MobX.observable.box(Foo("arne", 42))

    val bar: IComputedValue[Bar] =
      MobX.computed(() => Bar(foo.get.str + foo.get.num))

    def increaseNum: js.Function1[Double, Unit] =
      MobX.action("increaseNum", (n: Double) => foo.set(foo.get.copy(num = foo.get.num + n)))
  }

  class Props(val store: Store) extends js.Object

  import typings.react.dsl._

  private class C extends Component[Props, js.Any, js.Any] {
    val increaseNum: MouseEventHandler[js.Object] = _ => {
      console.log("increase num")
      props.store.increaseNum(1)
    }

    override def render: ReactNode =
      div.noprops(
        Mui.Avatar.props(
          Mui.AvatarProps(
            icon = Mui.ActionAlarm.props(
              Mui.SvgIconProps(
                SVGAttributes = SVGAttributes(
                  DOMAttributes = DOMAttributes(onClick = e => console.warn(s"icon clicked ${e.nativeEvent}"))
                ),
                hoverColor = "blue"
              )
            ),
            onClick = e => console.warn(s"avatar clicked ${e.nativeEvent}")
          )
        ),
        "Current bar ",
        props.store.bar.get.strnum,
        " Current foo num ",
        props.store.foo.get.num,
        " Current foo str ",
        props.store.foo.get.str,
        " ",
        Mui.RaisedButton.props(
          Mui.RaisedButtonProps(onClick = increaseNum),
          "increase num"
        )
      )
  }

  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])

}
