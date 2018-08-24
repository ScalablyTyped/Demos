package demo

import typings.materialDashUiLib.avatarMod.{default => Avatar}
import typings.materialDashUiLib.raisedbuttonMod.{default => RaisedButton}
import typings.materialDashUiLib.svgDashIconsActionAlarmMod.{default => ActionAlarm}
import typings.materialDashUiLib.underscoreUnderscoreMaterialUINs.{AvatarProps, RaisedButtonProps, SvgIconProps}
import typings.mobxDashReactLib.mobxDashReactMod.mobxDashReactModMembers.observer
import typings.mobxLib.libCoreComputedvalueMod.IComputedValue
import typings.mobxLib.libTypesObservablevalueMod.IObservableValue
import typings.mobxLib.mobxMod.{mobxModMembers => MobX}
import typings.reactLib.ReactDsl.{componentClass, div}
import typings.reactLib.reactMod.ReactNs.{ComponentClass, MouseEventHandler, ReactNode}
import typings.reactLib.reactMod._
import typings.stdLib.stdLibMembers.console

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

  trait Props extends js.Object {
    val store: Store
  }

  private class C extends Component[Props, js.Any, js.Any] {
    val increaseNum: MouseEventHandler[js.Object] = _ => {
      console.log("increase num")
      props.store.increaseNum(1)
    }

    override def render: ReactNode =
      div.noprops(
        componentClass[Avatar].apply(
          new AvatarProps {
            icon = componentClass[ActionAlarm].apply(
              new SvgIconProps {
                hoverColor = "blue"
                onClick    = js.defined(e => console.warn(s"icon clicked ${e.nativeEvent}"))
              }
            )
            onClick = js.defined(e => console.warn(s"avatar clicked ${e.nativeEvent}"))
          },
        ),
        "Current bar ",
        props.store.bar.get.strnum,
        " Current foo num ",
        props.store.foo.get.num,
        " Current foo str ",
        props.store.foo.get.str,
        " ",
        componentClass[RaisedButton].apply(
          new RaisedButtonProps { onClick = increaseNum },
          "increase num"
        )
      )
  }

  val Component: ComponentClass[Props, js.Any] =
    observer(js.constructorOf[C].asInstanceOf[ComponentClass[Props, js.Any]])
}
