package demo

import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html._
import typings.antd.AntdFacade.{List => _, _}
import typings.antd.antdStrings
import typings.antd.esNotificationMod.^.{default => Notification}
import typings.react.ScalableSlinky._
import typings.react.reactMod.{FormEvent, MouseEvent}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object CSS extends js.Any

@react object App {
  type Props = Unit

  private val css = CSS

  val component = FunctionalComponent[Props] { _ =>
    val (isModalVisible, updateIsModalVisible) = useState(false)
    val (selectValue, updateSelectValue)       = useState("lucy")

    val renderIntro = Row(RowProps())(
      Col(ColProps(span = 7)),
      Col(ColProps(span = 10))(
        header(className := "App-header")(h1(className := "App-title")("Welcome to React (with Scala.js!)")),
        p(className := "App-intro")("To get started, edit ", code("App.scala"), " and save to reload.")
      ),
      Col(ColProps(span = 7))
    )

    val renderGrid = section(
      h2("Grid"),
      Row(RowProps())(
        Col(ColProps(span = 12))(div(className := "block blue1")("col-12")),
        Col(ColProps(span = 12))(div(className := "block blue2")("col-12"))
      ),
      Row(RowProps())(
        Col(ColProps(span = 8))(div(className := "block blue1")("col-8")),
        Col(ColProps(span = 8))(div(className := "block blue2")("col-8")),
        Col(ColProps(span = 8))(div(className := "block blue1")("col-8"))
      ),
      Row(RowProps())(
        Col(ColProps(span = 6))(div(className := "block blue1")("col-6")),
        Col(ColProps(span = 6))(div(className := "block blue2")("col-6")),
        Col(ColProps(span = 6))(div(className := "block blue1")("col-6")),
        Col(ColProps(span = 6))(div(className := "block blue2")("col-6"))
      )
    )

    val renderTag = section(
      h2("Tag"),
      Tag(TagProps())("Tag 1"),
      Tag(TagProps(color = "red"))("red")
    )

    class TableItem(val key: Int, val name: String, val age: Int, val address: String) extends js.Object

    val renderTable = section(
      h2("Table"),
      Table[TableItem](
        TableProps(
          dataSource = js.Array(
            new TableItem(1, "Mike", 32, "10 Downing St."),
            new TableItem(2, "John", 42, "10 Downing St.")
          ),
          columns = js.Array(
            ColumnProps[TableItem](
              title     = "Name",
              dataIndex = "name",
              key       = "name",
              render    = (text, _, _) => Tag(TagProps())(text.toString).toST
            ),
            ColumnProps(title = "Age", dataIndex     = "age", key     = "age"),
            ColumnProps(title = "Address", dataIndex = "address", key = "address")
          )
        )
      )
    )

    val renderAlert = section(
      h2("Alert"),
      Alert(
        AlertProps(
          message     = "Success Tips",
          description = "Detailed description and advice about successful copywriting.",
          `type`      = antdStrings.success,
          showIcon    = true
        )
      )
    )

    val renderButton =
      section(h2("Button"), Button(ButtonProps(icon = "download", `type` = antdStrings.primary))("Download"))

    val renderModal = section(
      h2("Modal"),
      Button(ButtonProps(onClick = (_: MouseEvent[_, _]) => updateIsModalVisible(true)))("Open modal"),
      Modal(
        ModalProps(
          visible  = isModalVisible,
          title    = "Basic modal",
          onCancel = _ => updateIsModalVisible(false),
          onOk     = _ => updateIsModalVisible(false)
        )
      )(p("Some contents..."), p("Some contents..."), p("Some contents..."))
    )

    val renderSelect = section(
      h2("Select"),
      Select[String](
        SelectProps(
          defaultValue = selectValue,
          onChange     = (changedValue, _) => updateSelectValue(changedValue)
        )
      )(
        List(
          Option(OptionProps())("Jack").withKey("jack"),
          Option(OptionProps())("Lucy").withKey("lucy"),
          Option(OptionProps(disabled = true))("Disabled").withKey("disabled"),
          Option(OptionProps())("Yiminghe").withKey("yiminghe")
        )
      )
    )

    val renderIcon = section(h2("Icon"), Icon(IconProps(`type` = "home")))

    val renderInput = section(
      h2("Input"),
      Input(
        InputProps(
          placeholder = "Basic usage",
          addonBefore = Icon(IconProps(`type` = "user")).toST,
          onChange    = event => console.log(event.target_ChangeEvent.value)
        )
      )
    )

    val renderPassword =
      section(h2("Password Input"), Password(PasswordProps(placeholder = "input password", addonBefore = "Password")))

    val renderSpin = section(
      h2("Spin"),
      Spin(SpinProps(size = antdStrings.large, spinning = true))(
        Alert(
          AlertProps(
            message     = "Alert message title",
            description = "Further details about the context of this alert.",
            `type`      = antdStrings.info,
            showIcon    = true
          )
        )
      )
    )

    val renderForm = section(
      h2("Form"),
      Form(FormProps(onSubmit = (e: FormEvent[_]) => {
        e.preventDefault()
        console.log("Form submitted")
      }))(
        FormItem(FormItemProps())(
          Input(
            InputProps(
              placeholder = "input email",
              addonBefore = Icon(IconProps(`type` = "mail", theme = antdStrings.twoTone)).toST
            )
          )
        ),
        FormItem(FormItemProps())(
          Password(
            PasswordProps(
              placeholder = "input password",
              addonBefore = Icon(IconProps(`type` = "lock", theme = antdStrings.twoTone)).toST
            )
          )
        ),
        FormItem(FormItemProps())(
          Button(
            ButtonProps(
              `type`   = antdStrings.primary,
              htmlType = antdStrings.submit
            )
          )("Log in")
        )
      )
    )

    val renderCoordinated =
      section(h2("Form coordinated controls"), CoordinatedDemo.Component(new CoordinatedDemo.Props("write note here")))

    val renderNotification = section(
      h2("Notification"),
      Button(
        ButtonProps(
          onClick = (_: MouseEvent[_, _]) =>
            Notification.open(
              NotificationArgsProps(
                message = "Notification Title",
                description =
                  "This is the content of the notification. This is the content of the notification. This is the content of the notification.",
                `type` = antdStrings.success
              )
            )
        )
      )("Show notification")
    )

    div(className := "App")(
      renderIntro,
      Row(RowProps())(
        Col(ColProps(span = 2)),
        Col(ColProps(span = 20))(
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
          renderForm,
          renderCoordinated,
          renderNotification
        ),
        Col(ColProps(span = 2))
      )
    )
  }
}
