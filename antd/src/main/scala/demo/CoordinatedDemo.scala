package demo

import typings.antdLib.esFormFormMod.{
  FormCreateOption,
  GetFieldDecoratorOptions,
  ValidationRule,
  WrappedFormUtils,
  default => Form
}
import typings.antdLib.{antdLibStrings, antdLibComponents => Antd}
import typings.reactLib.dsl._
import typings.reactLib.reactMod._
import typings.stdLib.HTMLElement
import typings.stdLib.^.console

import scala.scalajs.js

// translated from https://github.com/ant-design/ant-design/blob/master/components/form/demo/coordinated.md
object CoordinatedDemo {

  class Props(val noteTitle: js.UndefOr[String]) extends js.Object

  trait WithForm extends js.Object {
    val form: WrappedFormUtils[js.Object]
  }

  val Base = define.fc[Props with WithForm] { props =>
    val handleSubmit: FormEventHandler[HTMLElement] = e => {
      e.preventDefault()
      props.form.validateFields(
        (err, values) => if (js.isUndefined(err)) console.log(s"Received values of form: $values")
      )
    }

    def handleSelectChange(value: String, option: Any): Unit = {
      console.log(value)
      props.form.setFieldsValue(new js.Object {
        val note = s"Hi, ${if (value == "male") "man" else "lady"}!"
      })
    }

    Antd.Form.props(
      Antd.FormProps(labelCol   = Antd.ColProps(span = 5),
                     wrapperCol = Antd.ColProps(span = 12),
                     onSubmit   = handleSubmit),
      Antd.FormItem.props(
        Antd.FormItemProps(label = props.noteTitle.getOrElse[String]("Note")),
        props.form
          .getFieldDecorator(
            "note",
            GetFieldDecoratorOptions(
              rules = js.Array(ValidationRule(required = true, message = "Please input your note!"))
            )
          )
          .apply(Antd.Input.noprops())
      ),
      Antd.FormItem.props(
        Antd.FormItemProps(label = "Gender"),
        props.form
          .getFieldDecorator(
            "gender",
            GetFieldDecoratorOptions(
              rules = js.Array(ValidationRule(required = true, message = "Please select your gender!'"))
            )
          )
          .apply(
            Antd
              .Select[String]
              .props(
                Antd.SelectProps(
                  placeholder = "Select a option and change input text above",
                  onChange    = handleSelectChange
                ),
                Antd.Option.props(Antd.OptionProps(value = "male"), "male"),
                Antd.Option.props(Antd.OptionProps(value = "female"), "female"),
              )
          )
      ),
      Antd.FormItem.props(
        Antd.FormItemProps(wrapperCol = Antd.ColProps(span = 12, offset = 5)),
        Antd.Button.props(
          Antd.ButtonProps(`type` = antdLibStrings.primary, htmlType = antdLibStrings.submit),
          "Submit"
        )
      )
    )
  }

  /* `Form.create` returns a new component with the `form` prop already filled */
  val Component: ComponentType[Props] =
    Form
      .create(FormCreateOption[Props](name = "coordinated"))(Base)
      .asInstanceOf[ComponentType[Props]] // The type returned from `Form.create` is complicated, but effectively it's just the original component without `form`
}
