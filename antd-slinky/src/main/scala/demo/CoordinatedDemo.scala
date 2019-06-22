package demo

import demo.ScalableSlinky._
import slinky.core.{ExternalComponent, FunctionalComponent, ReactComponentClass}
import slinky.core.annotations.react
import typings.antdLib.antdLibComponents.{Button, FormItem, Input, Option, Select, Form => FormAlt}
import typings.antdLib.antdLibStrings
import typings.antdLib.esButtonButtonMod.ButtonProps
import typings.antdLib.esFormFormItemMod.FormItemProps
import typings.antdLib.esFormFormMod.{
  FormCreateOption,
  FormProps,
  GetFieldDecoratorOptions,
  ValidationRule,
  WrappedFormUtils,
  default => Form
}
import typings.antdLib.esGridColMod.ColProps
import typings.antdLib.esSelectMod.{OptionProps, SelectProps}
import typings.reactLib.reactMod.FormEventHandler
import typings.stdLib.^.console

import scala.scalajs.js
import scala.scalajs.js.JSON

@react object CoordinatedDemo extends ExternalComponent {

  // case class won't work because `Form.create` will rewrite the props object
  class Props(val noteTitle: String) extends js.Object

  trait WithForm extends js.Object {
    val form: WrappedFormUtils[js.Object]
  }

  val Base = FunctionalComponent[Props with WithForm] { props =>
    val handleSubmit: FormEventHandler[js.Any] = e => {
      e.preventDefault()
      props.form.validateFields((err, values) => {
        if (err == null) {
          console.log("Received values of form: " + JSON.stringify(values))
        }
      })
    }

    def handleSelectChange(value: String, option: Any): Unit = {
      console.log(value)
      props.form.setFieldsValue(new js.Object {
        val note = "Hi, " + { if (value == "male") "man" else "lady" } + "!"
      })
    }

    val noteInput = props.form
      .getFieldDecorator(
        "note",
        GetFieldDecoratorOptions(
          rules = js.Array(ValidationRule(required = true, message = "Please input your note!"))
        )
      )
      .apply(Input.noprops().toST)

    val genderInput = props.form
      .getFieldDecorator(
        "gender",
        GetFieldDecoratorOptions(
          rules = js.Array(ValidationRule(required = true, message = "Please select your gender!'"))
        )
      )
      .apply(
        Select[String]
          .props(
            SelectProps(
              placeholder = "Select a option and change input text above",
              onChange    = handleSelectChange
            )
          )(
            Option.props(OptionProps(value = "male"))("male"),
            Option.props(OptionProps(value = "female"))("female")
          )
          .toST
      )

    FormAlt.props(
      FormProps(labelCol = ColProps(span = 5), wrapperCol = ColProps(span = 12), onSubmit = handleSubmit)
    )(
      FormItem.props(FormItemProps(label      = props.noteTitle))(noteInput.fromST),
      FormItem.props(FormItemProps(label      = "Gender"))(genderInput.fromST),
      FormItem.props(FormItemProps(wrapperCol = ColProps(span = 12, offset = 5)))(
        Button.props(ButtonProps(`type` = antdLibStrings.primary, htmlType = antdLibStrings.submit))("Submit")
      )
    )
  }

  /* `Form.create` returns a new component with the `form` prop already filled */
  override val component =
    Form.create(FormCreateOption[Props](name = "coordinated"))(ReactComponentClass.functionalComponentToClass(Base))
}
