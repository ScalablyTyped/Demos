package demo

import typings.antd.AntdFacade._
import typings.antd.antdStrings
import typings.antd.libFormFormMod.{FormCreateOption, GetFieldDecoratorOptions, ValidationRule}
import typings.react.ScalableSlinky.ExternalComponentP
import typings.react.reactMod.FormEventHandler
import typings.std.{console, HTMLFormElement}

import scala.scalajs.js
import scala.scalajs.js.JSON

object CoordinatedDemo {

  // case class won't work because `Form.create` will rewrite the props object
  class Props(val noteTitle: String) extends js.Object

  val Component: ExternalComponentP[Props] =
    formComponent(FormCreateOption[Props](name = "coordinated")) { props =>
      val handleSubmit: FormEventHandler[HTMLFormElement] = e => {
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

      val noteInput = {
        val options = GetFieldDecoratorOptions(
          rules = js.Array(ValidationRule(required = true, message = "Please input your note!"))
        )
        decoratedField(props.form, "note", options) { Input(InputProps()) }
      }

      val genderInput = {
        val options = GetFieldDecoratorOptions(
          rules = js.Array(ValidationRule(required = true, message = "Please select your gender!'"))
        )
        decoratedField(props.form, "gender", options) {
          Select[String](
            SelectProps(
              placeholder = "Select a option and change input text above",
              onChange    = handleSelectChange
            )
          )(
            Option(OptionProps(value = "male"))("male"),
            Option(OptionProps(value = "female"))("female")
          )
        }
      }

      Form(FormProps(labelCol = ColProps(span = 5), wrapperCol = ColProps(span = 12), onSubmit = handleSubmit))(
        FormItem(FormItemProps(label      = props.noteTitle))(noteInput),
        FormItem(FormItemProps(label      = "Gender"))(genderInput),
        FormItem(FormItemProps(wrapperCol = ColProps(span = 12, offset = 5)))(
          Button(ButtonProps(`type` = antdStrings.primary, htmlType = antdStrings.submit))("Submit")
        )
      )
    }
}
