package demo

import demo.Knowledge.{fromComponentType, Force}
import typings.reactDashNativeDashVectorDashIconsLib.iconMod.IconProps
import typings.reactDashNativeDashVectorDashIconsLib.materialiconsMod.{default => MuiIcon}
import typings.reactDashNativeLib.reactDashNativeMod.{Text, View, ViewProps}
import typings.reactDashNavigationLib.reactDashNavigationMod.^.{createAppContainer, createDrawerNavigator, DrawerItems}
import typings.reactDashNavigationLib.reactDashNavigationMod._
import typings.reactDashNavigationLib.{
  Anon_ActiveBackgroundColorActiveTintColor,
  Anon_Focused,
  Anon_NavigationOptionsPath,
  Anon_Screen
}
import typings.reactLib.dsl._
import typings.reactLib.reactMod.ReactNs.FC

import scala.scalajs.js

object routing {
  val DrawerContents: FC[DrawerItemsProps] = define.fc[DrawerItemsProps](
    p =>
      cls[View].props(
        new ViewProps {
          style = styles.wholeContainer
        },
        cls[View].props(
          new ViewProps {
            style = styles.drawerHeader
          },
          cls[View].props(new ViewProps {
            style = styles.drawerHeaderLogo
          }, cls[Text].noprops("Scala.js")),
          DrawerItems.props(p)
        )
    )
  )

  def drawerRoute(label: String, muiIcon: String, component: NavigationComponent): NavigationRouteConfig =
    new Anon_NavigationOptionsPath with Anon_Screen {
      override var screen = component

      navigationOptions = new NavigationDrawerScreenOptions {
        drawerLabel = label
        drawerIcon = define
          .fc[Anon_Focused](
            props =>
              cls[MuiIcon].props(new IconProps {
                override var name = muiIcon
                size  = 24
                color = Knowledge.nullToUndef(props.tintColor)
              })
          )
          .force
      }: NavigationScreenConfig[NavigationDrawerScreenOptions]
    }

  val DrawerNavigator: NavigationContainer =
    createDrawerNavigator(
      new NavigationRouteConfigMap {
        val inbox  = drawerRoute("Inbox", "inbox", screens.Inbox)
        val drafts = drawerRoute("Drafts foo", "drafts", screens.Drafts)
      },
      new DrawerNavigatorConfig {
        contentOptions = new Anon_ActiveBackgroundColorActiveTintColor {
          activeTintColor  = "black"
          style            = styles.drawerItems
          contentComponent = js.defined(DrawerContents)
        }
      }
    )

  val AppContainer: NavigationContainer =
    createAppContainer(DrawerNavigator.force)
}
