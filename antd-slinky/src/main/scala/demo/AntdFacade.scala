package demo

import slinky.core.TagMod
import slinky.core.facade.ReactElement
import typings.antdLib.esFormFormMod.default.{create => createForm}
import typings.antdLib.esFormFormMod.{FormCreateOption, GetFieldDecoratorOptions, WrappedFormUtils}
import typings.antdLib.{antdLibComponents => Antd}
import typings.reactLib.ScalableSlinky.{importSTComponent, ExternalComponentP, FromStReactNode}
import typings.reactLib.reactMod.ComponentType

import scala.scalajs.js

/**
  * This is a proposal for what a slinky facade for antd could look like.
  *
  * It would obviously need to be completed, right now it just has what the demo needs.
  */
object AntdFacade {
  /* rewrites to slinky external components */
  def Select[T]: ExternalComponentP[SelectProps[T]] = importSTComponent(Antd.Select[T])
  def Table[T]:  ExternalComponentP[TableProps[T]]  = importSTComponent(Antd.Table[T])
  val Alert:     ExternalComponentP[AlertProps]     = importSTComponent(Antd.Alert)
  val Button:    ExternalComponentP[ButtonProps]    = importSTComponent(Antd.Button)
  val Col:       ExternalComponentP[ColProps]       = importSTComponent(Antd.Col)
  val Form:      ExternalComponentP[FormProps]      = importSTComponent(Antd.Form)
  val FormItem:  ExternalComponentP[FormItemProps]  = importSTComponent(Antd.FormItem)
  val Icon:      ExternalComponentP[IconProps]      = importSTComponent(Antd.Icon)
  val Input:     ExternalComponentP[InputProps]     = importSTComponent(Antd.Input)
  val Modal:     ExternalComponentP[ModalProps]     = importSTComponent(Antd.Modal)
  val Option:    ExternalComponentP[OptionProps]    = importSTComponent(Antd.Option)
  val Password:  ExternalComponentP[PasswordProps]  = importSTComponent(Antd.Password)
  val Row:       ExternalComponentP[RowProps]       = importSTComponent(Antd.Row)
  val Spin:      ExternalComponentP[SpinProps]      = importSTComponent(Antd.Spin)
  val Tag:       ExternalComponentP[TagProps]       = importSTComponent(Antd.Tag)

  /* export unchanged */
  @inline def AlertProps = typings.antdLib.esAlertMod.AlertProps
  type AlertProps = typings.antdLib.esAlertMod.AlertProps
  @inline def ButtonProps = typings.antdLib.esButtonButtonMod.ButtonProps
  type ButtonProps = typings.antdLib.esButtonButtonMod.ButtonProps
  @inline def ColProps = typings.antdLib.esGridColMod.ColProps
  type ColProps = typings.antdLib.esGridColMod.ColProps
  @inline def ColumnProps = typings.antdLib.esTableInterfaceMod.ColumnProps
  type ColumnProps[T] = typings.antdLib.esTableInterfaceMod.ColumnProps[T]
  @inline def FormItemProps = typings.antdLib.esFormFormItemMod.FormItemProps
  type FormItemProps = typings.antdLib.esFormFormItemMod.FormItemProps
  @inline def FormProps = typings.antdLib.esFormFormMod.FormProps
  type FormProps = typings.antdLib.esFormFormMod.FormProps
  @inline def IconProps = typings.antdLib.esIconMod.IconProps
  type IconProps = typings.antdLib.esIconMod.IconProps
  @inline def InputProps = typings.antdLib.esInputInputMod.InputProps
  type InputProps = typings.antdLib.esInputInputMod.InputProps
  @inline def ModalProps = typings.antdLib.esModalModalMod.ModalProps
  type ModalProps = typings.antdLib.esModalModalMod.ModalProps
  @inline def NotificationArgsProps = typings.antdLib.esNotificationMod.ArgsProps
  type NotificationArgsProps = typings.antdLib.esNotificationMod.ArgsProps
  @inline def OptionProps = typings.antdLib.esSelectMod.OptionProps
  type OptionProps = typings.antdLib.esSelectMod.OptionProps
  @inline def PasswordProps = typings.antdLib.esInputPasswordMod.PasswordProps
  type PasswordProps = typings.antdLib.esInputPasswordMod.PasswordProps
  @inline def RowProps = typings.antdLib.esGridRowMod.RowProps
  type RowProps = typings.antdLib.esGridRowMod.RowProps
  @inline def SelectProps = typings.antdLib.esSelectMod.SelectProps
  type SelectProps[T] = typings.antdLib.esSelectMod.SelectProps[T]
  @inline def SpinProps = typings.antdLib.esSpinMod.SpinProps
  type SpinProps = typings.antdLib.esSpinMod.SpinProps
  @inline def TableProps = typings.antdLib.esTableInterfaceMod.TableProps
  type TableProps[T] = typings.antdLib.esTableInterfaceMod.TableProps[T]
  @inline def TagProps = typings.antdLib.esTagMod.TagProps
  type TagProps = typings.antdLib.esTagMod.TagProps

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
    form.getFieldDecorator(fieldName, options).apply(children).fromST
}
