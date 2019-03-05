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
        new MyNavScreenProps("Drafts screen", props.navigation),
        cls[Text].noprops("Your collection of email drafts goes here")
    )
  )
  val Inbox: NavigationComponent = define.fc[NavigationScreenProps[js.Any, js.Any]](
    props =>
      MyNavScreen.props(
        new MyNavScreenProps("Inbox screen", props.navigation),
        cls[Text].noprops("Pretend there is an email inbox here")
    )
  )

  class MyNavScreenProps(
      val header:     String,
      val navigation: NavigationScreenProp[NavigationRoute[js.Any], js.Any]
  ) extends js.Object

  private val MyNavScreen: FC[MyNavScreenProps] = define.fc[MyNavScreenProps] { props =>
    StatusBar.setBackgroundColor("#c62828")

    cls[ScrollView].props(
      ScrollViewProps(style = styles.navScreenContainer),
      cls[View].props(
        ViewProps(style = styles.navScreenHeader),
        cls[TouchableOpacity].props(
          TouchableOpacityProps(onPress = _ => props.navigation.openDrawer()),
          cls[MuiIcon].props(IconProps(name = "menu", color = "white", size = 30))
        )
      ),
      cls[Text].props(TextProps(style = styles.sampleText), props.header),
      props.children.flatten,
      cls[Button].props(
        ButtonProps(
          title = "Go back",
          onPress = _ => {
            console.warn("goBack")
            props.navigation.goBack()
          }
        ).force
      )
    ): ReactNode
  }
}
