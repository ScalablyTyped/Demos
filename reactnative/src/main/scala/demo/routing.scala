package demo

import demo.Knowledge.Force
import org.scalablytyped.runtime.StringDictionary
import typings.react.reactMod.{FC, ReactElement}
import typings.reactDashNative.{reactDashNativeComponents => RN}
import typings.reactDashNativeDashVectorDashIcons.{reactDashNativeDashVectorDashIconsComponents => Icons}
import typings.reactDashNavigation._
import typings.reactDashNavigation.reactDashNavigationMod.^.{createAppContainer, createDrawerNavigator, DrawerItems}
import typings.reactDashNavigation.reactDashNavigationMod._
import typings.std.^.Object

object routing {
  import typings.react.dsl._

  val DrawerContents: FC[DrawerItemsProps] = define.fc[DrawerItemsProps](
    p =>
      RN.View.props(
        RN.ViewProps(style = styles.wholeContainer),
        RN.View.props(
          RN.ViewProps(style = styles.drawerHeader),
          RN.View.props(
            RN.ViewProps(style = styles.drawerHeaderLogo),
            RN.Text.noprops("Scala.js")
          ),
          DrawerItems.props(p)
        )
      )
  )

  def drawerRoute(label: String, muiIcon: String, component: NavigationComponent): NavigationRouteConfig =
    Object.assign(
      Anon_Screen(component),
      Anon_NavigationOptionsPath(
        NavigationDrawerScreenOptions(
          drawerLabel = label,
          drawerIcon = define
            .fc[Anon_Focused](
              props =>
                Icons.MaterialIcons.props(
                  Icons.IconProps(
                    name  = muiIcon,
                    size  = 24,
                    color = props.tintColor.force[String]
                  )
                )
            )
            .force[ReactElement]
        ): NavigationScreenConfig[NavigationDrawerScreenOptions]
      )
    )

  val DrawerNavigator: NavigationContainer =
    createDrawerNavigator(
      StringDictionary(
        "inbox" -> drawerRoute("Inbox", "inbox", screens.Inbox),
        "drafts" -> drawerRoute("Drafts foo", "drafts", screens.Drafts)
      ),
      DrawerNavigatorConfig(
        contentComponent = DrawerContents,
        contentOptions = Anon_ActiveBackgroundColor(
          activeTintColor = "black",
          style           = styles.drawerItems
        )
      )
    )

  val AppContainer: NavigationContainer =
    createAppContainer(DrawerNavigator.force)
}
