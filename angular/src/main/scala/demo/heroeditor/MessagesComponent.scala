package demo
package heroeditor

import typings.atAngularCoreLib.atAngularCoreMod.ComponentCls
import typings.atAngularCoreLib.srcMetadataDirectivesMod.Component
import typings.atAngularCoreLib.srcMetadataLifecycleUnderscoreHooksMod.OnInit

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class MessagesComponent(val messageService: MessageService) extends OnInit {
  override def ngOnInit(): Unit = ()
}

object MessagesComponent {
  @JSExportStatic
  val annotations =
    js.Array(
      new ComponentCls(
        Component(
          selector = "app-messages",
          template = """
            |<div *ngIf="messageService.messages.length">
            |
            |    <h2>Messages</h2>
            |    <button class="clear"
            |            (click)="messageService.clear()">clear</button>
            |    <div *ngFor='let message of messageService.messages'> {{message}} </div>
            |
            |</div>
          """.stripMargin,
          styles   = js.Array("""
            |h2 {
            |  color: red;
            |  font-family: Arial, Helvetica, sans-serif;
            |  font-weight: lighter;
            |}
            |body {
            |  margin: 2em;
            |}
            |body, input[text], button {
            |  color: crimson;
            |  font-family: Cambria, Georgia;
            |}
            |
            |button.clear {
            |  font-family: Arial;
            |  background-color: #eee;
            |  border: none;
            |  padding: 5px 10px;
            |  border-radius: 4px;
            |  cursor: pointer;
            |  cursor: hand;
            |}
            |button:hover {
            |  background-color: #cfd8dc;
            |}
            |button:disabled {
            |  background-color: #eee;
            |  color: #aaa;
            |  cursor: auto;
            |}
            |button.clear {
            |  color: #888;
            |  margin-bottom: 12px;
            |}
          """.stripMargin)
        )
      )
    )

  @JSExportStatic
  val parameters = js.Array(typeOf[MessageService])
}
