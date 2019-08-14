package demo

import typings.antd.antdComponents._
import typings.antd.antdStrings
import typings.antd.esNotificationMod.ArgsProps
import typings.antd.esNotificationMod.^.{default => Notification}
import typings.react.dsl._
import typings.react.reactMod.^.useState
import typings.react.reactMod.{HTMLAttributes, MouseEvent}
import typings.std.^.console

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object CSS extends js.Any

object App {

  private val css = CSS

  val Component = define.fc[js.Object] { _ =>
    val js.Tuple2(isModalVisible, updateIsModalVisible) = useState(false)
    val js.Tuple2(selectValue, updateSelectValue)       = useState("lucy")

    def renderIntro = Row.noprops(
      Col.props(ColProps(span = 7)),
      Col.props(
        ColProps(span = 10),
        header.props(
          HTMLAttributes(className = "App-header"),
          h1.props(HTMLAttributes(className = "App-title"), "Welcome to React (with Scala.js!)")
        ),
        p.props(
          HTMLAttributes(className = "App-intro"),
          "To get started, edit ",
          code.noprops("App.scala"),
          " and save to reload."
        )
      ),
      Col.props(ColProps(span = 7))
    )

    def renderGrid = section.noprops(
      h2.noprops("Grid"),
      Row.noprops(
        Col.props(ColProps(span = 12), div.props(HTMLAttributes(className = "block blue1"), "col-12")),
        Col.props(ColProps(span = 12), div.props(HTMLAttributes(className = "block blue2"), "col-12"))
      ),
      Row.noprops(
        Col.props(ColProps(span = 8), div.props(HTMLAttributes(className = "block blue1"), "col-8")),
        Col.props(ColProps(span = 8), div.props(HTMLAttributes(className = "block blue2"), "col-8")),
        Col.props(ColProps(span = 8), div.props(HTMLAttributes(className = "block blue1"), "col-8"))
      ),
      Row.noprops(
        Col.props(ColProps(span = 6), div.props(HTMLAttributes(className = "block blue1"), "col-6")),
        Col.props(ColProps(span = 6), div.props(HTMLAttributes(className = "block blue2"), "col-6")),
        Col.props(ColProps(span = 6), div.props(HTMLAttributes(className = "block blue1"), "col-6")),
        Col.props(ColProps(span = 6), div.props(HTMLAttributes(className = "block blue2"), "col-6"))
      )
    )

    def renderTag = section.noprops(
      h2.noprops("Tag"),
      Tag.noprops("Tag 1"),
      Tag.props(TagProps(color = "red"), "red")
    )

    class TableItem(val key: Int, val name: String, val age: Int, val address: String) extends js.Object

    def renderTable = section.noprops(
      h2.noprops("Table"),
      Table[TableItem].props(
        TableProps(
          dataSource = js.Array(
            new TableItem(1, "Mike", 32, "10 Downing St."),
            new TableItem(2, "John", 42, "10 Downing St.")
          ),
          columns = js.Array(
            ColumnProps(
              title     = "Name",
              dataIndex = "name",
              key       = "name",
              render    = (text, _, _) => Tag.noprops(text.toString)
            ),
            ColumnProps(title = "Age", dataIndex     = "age", key     = "age"),
            ColumnProps(title = "Address", dataIndex = "address", key = "address")
          )
        )
      )
    )

    def renderAlert = section.noprops(
      h2.noprops("Alert"),
      Alert.props(
        AlertProps(
          message     = "Success Tips",
          description = "Detailed description and advice about successful copywriting.",
          `type`      = antdStrings.success,
          showIcon    = true
        )
      )
    )

    def renderButton = section.noprops(
      h2.noprops("Button"),
      Button.props(ButtonProps(icon = "download", `type` = antdStrings.primary), "Download")
    )

    def renderModal = section.noprops(
      h2.noprops("Modal"),
      Button.props(ButtonProps(onClick = (_: MouseEvent[_, _]) => updateIsModalVisible(true)), "Open modal"),
      Modal.props(
        ModalProps(
          visible  = isModalVisible,
          title    = "Basic modal",
          onCancel = _ => updateIsModalVisible(false),
          onOk     = _ => updateIsModalVisible(false)
        ),
        p.noprops("Some contents..."),
        p.noprops("Some contents..."),
        p.noprops("Some contents...")
      )
    )

    def renderSelect = section.noprops(
      h2.noprops("Select"),
      Select[String].props(
        SelectProps(defaultValue = selectValue, onChange = (changedValue, _) => updateSelectValue(changedValue)),
        Option.withKey("jack").noprops("Jack"),
        Option.withKey("lucy").noprops("Lucy"),
        Option.withKey("disabled").props(OptionProps(disabled = true), "Disabled"),
        Option.withKey("yiminghe").noprops("Yiminghe")
      )
    )

    def renderIcon = section.noprops(
      h2.noprops("Icon"),
      Icon.props(IconProps(`type` = "home"))
    )

    def renderInput = section.noprops(
      h2.noprops("Input"),
      Input.props(
        InputProps(
          placeholder = "Basic usage",
          addonBefore = Icon.props(IconProps(`type` = "user")),
          onChange    = event => console.log(event.target_ChangeEvent.value)
        )
      )
    )

    def renderPassword = section.noprops(
      h2.noprops("Password Input"),
      Password.props(PasswordProps(placeholder = "input password", addonBefore = "Password"))
    )

    def renderSpin = section.noprops(
      h2.noprops("Spin"),
      Spin.props(
        SpinProps(size = antdStrings.large, spinning = true),
        Alert.props(
          AlertProps(
            message     = "Alert message title",
            description = "Further details about the context of this alert.",
            `type`      = antdStrings.info,
            showIcon    = true
          )
        )
      )
    )

    def renderNotification = section.noprops(
      h2.noprops("Notification"),
      Button.props(
        ButtonProps(
          onClick = (_: MouseEvent[_, _]) =>
            Notification.open(
              ArgsProps(
                message = "Notification Title",
                description =
                  "This is the content of the notification. This is the content of the notification. This is the content of the notification.",
                `type` = antdStrings.success
              )
            )
        ),
        "Show notification"
      )
    )

    div.props(
      HTMLAttributes(className = "App"),
      renderIntro,
      Row.noprops(
        Col.props(ColProps(span = 2)),
        Col.props(
          ColProps(span = 20),
          renderGrid,
          renderTag,
          renderTable,
          renderAlert,
          renderButton,
          renderModal,
          renderSelect,
          renderIcon,
          renderInput,
          renderPassword,
          renderSpin,
          section.noprops(
            h2.noprops("Validation"),
            CoordinatedDemo.Component.props(new CoordinatedDemo.Props(noteTitle = "Input note here"))
          ),
          renderNotification
        ),
        Col.props(ColProps(span = 2))
      )
    )
  }

}
