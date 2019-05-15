package demo

import typings.atMaterialDashUiCoreLib.stylesCreateMuiThemeMod.Theme
import typings.atMaterialDashUiCoreLib.{
  atMaterialDashUiCoreLibComponents => Mui,
  atMaterialDashUiCoreLibStrings => MuiStrings
}
import typings.atMaterialDashUiIconsLib.{atMaterialDashUiIconsLibComponents => MuiIcons}
import typings.reactDashDomLib.reactDashDomMod.^.render
import typings.reactLib.dsl._
import typings.reactLib.reactMod.{FC, FunctionComponent}
import typings.reactLib.reactMod.^.useState
import typings.stdLib.^.window

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => obj}

object Demo {
  import typings.reactLib.dsl._

  class ButtonTestProps(val name: String) extends js.Object

  val ButtonTest = define.fc[ButtonTestProps] { props =>
    /* use a hook to keep state */
    val js.Tuple2(state, setState) = useState(1)

    div.noprops(
      /* a cake icon to celebrate */
      MuiIcons.CakeOutlined.props(MuiIcons.CakeOutlinedProps(color = MuiStrings.action)),
      /* text field controlled by the value of the state hook above*/
      Mui.TextField.props(new Mui.TextFieldProps {
        value    = state
        disabled = true
      }),
      /* a button which calls the `setState` function*/
      Mui.Button.props(
        Mui.ButtonProps(
          action  = null, // todo: check what this should be and why it's required
          onClick = _ => setState(state + 1)
        ),
        s"Increment it, ${props.name}"
      )
    )
  }

  def main(argv: Array[String]): Unit =
    render(
      div.noprops(
        ButtonTest.props(new ButtonTestProps("dear user")),
        SimpleBadge.Component.props(obj())
      ),
      window.document.body
    )
}

// https://github.com/mui-org/material-ui/blob/master/docs/src/pages/demos/badges/SimpleBadge.js
object SimpleBadge {
  trait StyleOverrides[T] extends js.Object {
    val margin:  T
    val padding: T
  }

  val styles: js.Function1[Theme, StyleOverrides[js.Dynamic]] = theme =>
    new StyleOverrides[js.Dynamic] {
      override val margin  = obj(margin  = theme.spacing.unit * 2)
      override val padding = obj(padding = s"0 ${theme.spacing.unit * 2}px")
  }

  class Props(val classes: StyleOverrides[String]) extends js.Object

  val Base: FC[Props] = define.fc[Props] { props =>
    div.noprops(
      div.noprops(
        Mui.Badge.props(
          Mui.BadgeProps(
            children     = MuiIcons.Mail.noprops(),
            className    = props.classes.margin,
            badgeContent = 4,
            color        = MuiStrings.primary
          )
        ),
        Mui.Badge.props(
          Mui.BadgeProps(
            children     = MuiIcons.Mail.noprops(),
            className    = props.classes.margin,
            badgeContent = 10,
            color        = MuiStrings.secondary
          )
        ),
        Mui.IconButton.props(
          Mui.IconButtonProps(
            action       = null,
            `aria-label` = "4 pending messages",
            className    = props.classes.margin,
            children = Mui.Badge.props(
              Mui.BadgeProps(badgeContent = 4, color = MuiStrings.primary, children = MuiIcons.MailOutline.noprops())
            )
          ),
        )
      ),
      Mui.AppBar.props(
        Mui.AppBarProps(position = MuiStrings.static, className = props.classes.margin),
        Mui.Tabs.props(
          obj(value = 0).asInstanceOf[Mui.TabsProps], // new Mui.TabsProps { value = 0 },
          Mui.Tab.props(
            Mui.TabProps(
              action = null,
              label = Mui.Badge.props(
                Mui.BadgeProps(children     = "Item One",
                               className    = props.classes.padding,
                               color        = MuiStrings.secondary,
                               badgeContent = 4)
              )
            )
          )
        )
      ),
      Mui.Badge.props(
        Mui.BadgeProps(
          children     = Mui.Typography.props(Mui.TypographyProps(className = props.classes.padding), "Typography"),
          color        = MuiStrings.primary,
          badgeContent = 4,
          className    = props.classes.margin
        )
      ),
      Mui.Badge.props(
        Mui.BadgeProps(
          children     = Mui.Button.props(obj(variant = MuiStrings.contained).asInstanceOf[Mui.ButtonProps], "Button"),
          color        = MuiStrings.primary,
          badgeContent = 4,
          className    = props.classes.margin
        )
      ),
    )
  }

  // todo: we'll need a facade here...
  val Component = typings.atMaterialDashUiCoreLib.stylesMod.^.asInstanceOf[js.Dynamic]
    .withStyles(styles)(Base)
    .asInstanceOf[FunctionComponent[js.Object]]

}
