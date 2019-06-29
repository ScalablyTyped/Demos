package demo

import slinky.core.TagMod
import slinky.core.facade.ReactElement
import typings.antdLib.esFormFormMod.default.{create => createForm}
import typings.antdLib.esFormFormMod.{FormCreateOption, GetFieldDecoratorOptions, WrappedFormUtils}
import typings.antdLib.{antdLibComponents => Antd, antdLibProps}
import typings.reactLib.ScalableSlinky._
import typings.reactLib.reactMod.ComponentType

import scala.scalajs.js

/**
  * This is a proposal for what a slinky facade for antd could look like.
  *
  * It would obviously need to be completed, right now it just has what the demo needs.
  */
object AntdFacade extends antdLibProps {
  /* rewrites to slinky external components */
  @inline def Alert:     ExternalComponentP[AlertProps]     = importSTComponent(Antd.Alert)
  @inline def Button:    ExternalComponentP[ButtonProps]    = importSTComponent(Antd.Button)
  @inline def Col:       ExternalComponentP[ColProps]       = importSTComponent(Antd.Col)
  @inline def Form:      ExternalComponentP[FormProps]      = importSTComponent(Antd.Form)
  @inline def FormItem:  ExternalComponentP[FormItemProps]  = importSTComponent(Antd.FormItem)
  @inline def Icon:      ExternalComponentP[IconProps]      = importSTComponent(Antd.Icon)
  @inline def Input:     ExternalComponentP[InputProps]     = importSTComponent(Antd.Input)
  @inline def Modal:     ExternalComponentP[ModalProps]     = importSTComponent(Antd.Modal)
  @inline def Option:    ExternalComponentP[OptionProps]    = importSTComponent(Antd.Option)
  @inline def Password:  ExternalComponentP[PasswordProps]  = importSTComponent(Antd.Password)
  @inline def Row:       ExternalComponentP[RowProps]       = importSTComponent(Antd.Row)
  @inline def Select[T]: ExternalComponentP[SelectProps[T]] = importSTComponent(Antd.Select[T])
  @inline def Spin:      ExternalComponentP[SpinProps]      = importSTComponent(Antd.Spin)
  @inline def Table[T]:  ExternalComponentP[TableProps[T]]  = importSTComponent(Antd.Table[T])
  @inline def Tag:       ExternalComponentP[TagProps]       = importSTComponent(Antd.Tag)

  @inline def NotificationArgsProps = typings.antdLib.esNotificationMod.ArgsProps
  type NotificationArgsProps = typings.antdLib.esNotificationMod.ArgsProps

  /**
    * This is an example of something a bit more complicated than just rewriting component types, and which a manually
    *  written facade. Given an implementation of a component which has a `form` prop which is to be prefilled,
    *  this will generate a ready-to-use `ExternalComponent` for it.
    */
  def formComponent[Props <: js.Object](
      options: FormCreateOption[Props]
  )(f:         js.Function1[Props with WithForm, ReactElement]): ExternalComponentP[Props] =
    importSTComponent(createForm(options)(f).asInstanceOf[ComponentType[Props]])

  trait WithForm extends js.Object {
    val form: WrappedFormUtils[js.Object]
  }

  def decoratedField(form: WrappedFormUtils[js.Object], fieldName: String, options: GetFieldDecoratorOptions)(
      children:            ReactElement
  ): TagMod[Any] =
    form.getFieldDecorator(fieldName, options).apply(children.toST).fromST
}
