package demo

import demo.Knowledge.Force
import typings.reactDashNativeDashVectorDashIconsLib.{reactDashNativeDashVectorDashIconsLibComponents => Icons}
import typings.reactDashNativeLib.reactDashNativeMod.StatusBar
import typings.reactDashNativeLib.{reactDashNativeLibComponents => RN}
import typings.reactDashNavigationLib.reactDashNavigationMod.{NavigationComponent, NavigationRoute, NavigationScreenProp, NavigationScreenProps}
import typings.reactLib.dsl._
import typings.reactLib.reactMod.ReactNs.{FC, ReactNode}
import typings.stdLib.^.console

import scala.scalajs.js

object screens {
  val Drafts: NavigationComponent = define.fc[NavigationScreenProps[js.Any, js.Any]](
    props =>
      MyNavScreen.props(
        new MyNavScreenProps("Drafts screen", props.navigation),
        RN.Text.noprops("Your collection of email drafts goes here")
    )
  )
  val Inbox: NavigationComponent = define.fc[NavigationScreenProps[js.Any, js.Any]](
    props =>
      MyNavScreen.props(
        new MyNavScreenProps("Inbox screen", props.navigation),
        RN.Text.noprops("Pretend there is an email inbox here")
    )
  )

  class MyNavScreenProps(
      val header:     String,
      val navigation: NavigationScreenProp[NavigationRoute[js.Any], js.Any]
  ) extends js.Object

  private val MyNavScreen: FC[MyNavScreenProps] = define.fc[MyNavScreenProps] { props =>
    StatusBar.setBackgroundColor("#c62828")

    RN.ScrollView.props(
      RN.ScrollViewProps(style = styles.navScreenContainer),
      RN.View.props(
        RN.ViewProps(style = styles.navScreenHeader),
        RN.TouchableOpacity.props(
          RN.TouchableOpacityProps(onPress = _ => props.navigation.openDrawer()),
          Icons.MaterialIcons.props(Icons.IconProps(name = "menu", color = "white", size = 30))
        )
      ),
      RN.Text.props(RN.TextProps(style = styles.sampleText), props.header),
      props.children.flatten,
      RN.Button.props(
        RN.ButtonProps(
            title = "Go back",
            onPress = _ => {
              console.warn("goBack")
              props.navigation.goBack()
            }
          )
          .force
      )
    ): ReactNode
  }
}
