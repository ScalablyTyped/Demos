package demo

import demo.Knowledge.Force
import typings.reactDashNativeDashVectorDashIconsLib.iconMod.IconProps
import typings.reactDashNativeDashVectorDashIconsLib.materialiconsMod.{default => MuiIcon}
import typings.reactDashNativeLib.reactDashNativeMod._
import typings.reactDashNavigationLib.reactDashNavigationMod.{
  NavigationComponent,
  NavigationRoute,
  NavigationScreenProp,
  NavigationScreenProps
}
import typings.reactLib.dsl._
import typings.reactLib.reactMod.ReactNs.{FC, ReactNode}
import typings.stdLib.^.console

import scala.scalajs.js

object screens {
  val Drafts: NavigationComponent = define.fc[NavigationScreenProps[js.Any, js.Any]](
    props =>
      MyNavScreen.props(
        new MyNavScreenProps {
          val header              = "Drafts screen"
          override val navigation = props.navigation
        },
        cls[Text].noprops("Your collection of email drafts goes here")
    )
  )
  val Inbox: NavigationComponent = define.fc[NavigationScreenProps[js.Any, js.Any]](
    props =>
      MyNavScreen.props(
        new MyNavScreenProps {
          val header              = "Inbox screen"
          override val navigation = props.navigation
        },
        cls[Text].noprops("Pretend there is an email inbox here")
    )
  )

  trait MyNavScreenProps extends js.Object {
    val header:     String
    val navigation: NavigationScreenProp[NavigationRoute[js.Any], js.Any]
  }

  private val MyNavScreen: FC[MyNavScreenProps] = define.fc[MyNavScreenProps] { props =>
    StatusBar.setBackgroundColor("#c62828")

    cls[ScrollView].props(
      new ScrollViewProps {
        style = styles.navScreenContainer
      },
      cls[View].props(
        new ViewProps {
          style = styles.navScreenHeader
        },
        cls[TouchableOpacity].props(
          new TouchableOpacityProps {
            onPress = js.defined(_ => props.navigation.openDrawer(): Unit)
          },
          cls[MuiIcon].props(new IconProps {
            override var name = "menu"
            color = "white"
            size  = js.defined(30)
          })
        )
      ),
      cls[Text].props(new TextProps {
        style = styles.sampleText
      }, props.header),
      props.children.flatten,
      cls[Button].props(new ButtonPropsFixed {
        override var title: String =
          "Go back"

        override var onPress = _ => {
          console.warn("goBack")
          props.navigation.goBack()

        }
      }.force)
    ): ReactNode
  }

  /**
    * `onPress` is a method in the generated code.
    * I'm not sure who is to blame, but the javascript coming out from scala.js is not correct
    */
  trait ButtonPropsFixed extends js.Object {
    var accessibilityLabel: js.UndefOr[java.lang.String] = js.undefined
    var color:              js.UndefOr[java.lang.String] = js.undefined
    var disabled:           js.UndefOr[scala.Boolean]    = js.undefined

    /**
      * Used to locate this button in end-to-end tests.
      */
    var testID: js.UndefOr[java.lang.String] = js.undefined
    var title:   java.lang.String
    var onPress: js.Function1[NativeSyntheticEvent[NativeTouchEvent], Unit]
  }

}
