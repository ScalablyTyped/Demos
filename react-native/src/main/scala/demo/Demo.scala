package demo

import org.scalablytyped.runtime.TopLevel
import typings.expoDashFont.{expoDashFontMod => Font}
import typings.react.dsl._
import typings.react.reactMod.{Component, ReactNode}
import typings.reactDashNative.{reactDashNativeComponents => RN}
import typings.reactDashNativeDashGestureDashHandler.reactDashNativeDashGestureDashHandlerRequire

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.util.{Failure, Success}

object Demo {
  @JSImport("../../node_modules/react-native-vector-icons/Fonts/MaterialIcons.ttf", JSImport.Namespace)
  @js.native
  object LoadIcon extends TopLevel[Font.FontSource]

  class MainState(val result: Option[Either[String, Unit]]) extends js.Object

  // https://docs.expo.io/versions/v34.0.0/guides/using-custom-fonts/
  class Main extends Component[Unit, MainState, Unit] {
    state = new MainState(None)
    componentDidMount = js.defined(
      () =>
        Font
          .loadAsync("MaterialIcons", LoadIcon)
          .toFuture
          .onComplete {
            case Failure(exception) => setState(new MainState(Some(Left(exception.toString))))
            case Success(value)     => setState(new MainState(Some(Right(()))))
          }
    )

    override def render: ReactNode =
      state.result match {
        case Some(Right(value)) => routing.AppContainer.noprops()
        case Some(Left(value))  => RN.Text.noprops(s"Could not load fonts: $value")
        case None               => RN.Text.noprops("Loading fonts...")
      }
  }

  @JSExportTopLevel("app")
  val app: js.Object = {
    /* touch to load before react-navigation, see https://github.com/kmagiera/react-native-gesture-handler/issues/320#issuecomment-489271515 */
    reactDashNativeDashGestureDashHandlerRequire

    /* export top level component so it can be picked up in `App.js` */
    js.constructorOf[Main].asInstanceOf[js.Object]
  }
}
