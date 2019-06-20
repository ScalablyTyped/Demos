package demo

import demo.ScalableSlinky._
import org.scalajs.dom.console
import slinky.core._
import slinky.core.facade.Hooks._
import slinky.web.html._
import typings.antdLib.antdLibComponents.{List => _, _}
import typings.antdLib.antdLibStrings
import typings.antdLib.esFormFormMod.FormProps
import typings.reactLib.reactMod.{FormEvent, MouseEvent}

import scala.scalajs.js

object App {
  val component = FunctionalComponent[Unit] { _ =>
    val (isModalVisible, updateIsModalVisible) = useState(false)
    val (selectValue, updateSelectValue)       = useState("lucy")

    val renderIntro = Row.noprops(
      Col.props(ColProps(span = 7)),
      Col.props(ColProps(span = 10))(
        header(className := "App-header")(h1(className := "App-title")("Welcome to React (with Scala.js!)")),
        p(className := "App-intro")("To get started, edit ", code("App.scala"), " and save to reload.")
      ),
      Col.props(ColProps(span = 7))
    )

    val renderGrid = section(
      h2("Grid"),
      Row.noprops(
        Col.props(ColProps(span = 12))(div(className := "block blue1")("col-12")),
        Col.props(ColProps(span = 12))(div(className := "block blue2")("col-12"))
      ),
      Row.noprops(
        Col.props(ColProps(span = 8))(div(className := "block blue1")("col-8")),
        Col.props(ColProps(span = 8))(div(className := "block blue2")("col-8")),
        Col.props(ColProps(span = 8))(div(className := "block blue1")("col-8"))
      ),
      Row.noprops(
        Col.props(ColProps(span = 6))(div(className := "block blue1")("col-6")),
        Col.props(ColProps(span = 6))(div(className := "block blue2")("col-6")),
        Col.props(ColProps(span = 6))(div(className := "block blue1")("col-6")),
        Col.props(ColProps(span = 6))(div(className := "block blue2")("col-6"))
      )
    )

    val renderTag = section(h2("Tag"), Tag.noprops("Tag 1"), Tag.props(TagProps(color = "red"))("red"))

    class TableItem(val key: Int, val name: String, val age: Int, val address: String) extends js.Object

    val renderTable = section(
      h2("Table"),
      Table[TableItem].props(
        TableProps(
          dataSource = js.Array(
            new TableItem(1, "Mike", 32, "10 Downing St."),
            new TableItem(2, "John", 42, "10 Downing St.")
          ),
          columns = js.Array(
            ColumnProps[TableItem](title     = "Name",
                                   dataIndex = "name",
                                   key       = "name",
                                   render    = (text, _, _) => Tag.noprops(text.toString).toST),
            ColumnProps(title                = "Age", dataIndex = "age", key = "age"),
            ColumnProps(title                = "Address", dataIndex = "address", key = "address")
          )
        )
      )
    )

    val renderAlert = section(
      h2("Alert"),
      Alert.props(
        AlertProps(message     = "Success Tips",
                   description = "Detailed description and advice about successful copywriting.",
                   `type`      = antdLibStrings.success,
                   showIcon    = true)
      )
    )

    val renderButton =
      section(h2("Button"), Button.props(ButtonProps(icon = "download", `type` = antdLibStrings.primary))("Download"))

    val renderModal = section(
      h2("Modal"),
      Button.props(ButtonProps(onClick = (_: MouseEvent[_, _]) => updateIsModalVisible(true)))("Open modal"),
      Modal.props(
        ModalProps(visible  = isModalVisible,
                   title    = "Basic modal",
                   onCancel = _ => updateIsModalVisible(false),
                   onOk     = _ => updateIsModalVisible(false))
      )(p("Some contents..."), p("Some contents..."), p("Some contents..."))
    )

    val renderSelect = section(
      h2("Select"),
      Select[String].props(
        SelectProps(defaultValue = selectValue, onChange = (changedValue, _) => updateSelectValue(changedValue))
      )(
        List(
          Option.noprops("Jack").withKey("jack"),
          Option.noprops("Lucy").withKey("lucy"),
          Option.props(OptionProps(disabled = true))("Disabled").withKey("disabled"),
          Option.noprops("Yiminghe").withKey("yiminghe")
        )
      )
    )

    val renderIcon = section(h2("Icon"), Icon.props(IconProps(`type` = "home")))

    val renderInput = section(
      h2("Input"),
      Input.props(
        InputProps(placeholder = "Basic usage",
                   addonBefore = Icon.props(IconProps(`type` = "user")).toST,
                   onChange    = event => console.log(event.target_ChangeEvent.value))
      )
    )

    val renderPassword =
      section(h2("Password Input"),
              Password.props(PasswordProps(placeholder = "input password", addonBefore = "Password")))

    val renderSpin = section(
      h2("Spin"),
      Spin.props(SpinProps(size = antdLibStrings.large, spinning = true))(
        Alert.props(
          AlertProps(message     = "Alert message title",
                     description = "Further details about the context of this alert.",
                     `type`      = antdLibStrings.info,
                     showIcon    = true)
        )
      )
    )

    val renderForm = section(
      h2("Form"),
      Form.props(FormProps(onSubmit = (e: FormEvent[_]) => {
        e.preventDefault()
        console.log("Form submitted")
      }))(
        FormItem.noprops(
          Input.props(
            InputProps(
              placeholder = "input email",
              addonBefore = Icon.props(IconProps(`type` = "mail", theme = antdLibStrings.twoTone)).toST,
            )
          )
        ),
        FormItem.noprops(
          Password.props(
            PasswordProps(
              placeholder = "input password",
              addonBefore = Icon.props(IconProps(`type` = "lock", theme = antdLibStrings.twoTone)).toST,
            )
          )
        ),
        FormItem.noprops(
          Button.props(
            ButtonProps(
              `type`   = antdLibStrings.primary,
              htmlType = antdLibStrings.submit
            )
          )("Log in")
        )
      )
    )

    val renderCoordinated =
      section(h2("Form coordinated controls"), CoordinatedDemo(new CoordinatedDemo.Props("write note here")))

    div(className := "App")(
      renderIntro,
      Row.noprops(
        Col.props(ColProps(span = 2)),
        Col.props(ColProps(span = 20))(
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
          renderCoordinated
        ),
        Col.props(ColProps(span = 2))
      )
    )
  }
}
