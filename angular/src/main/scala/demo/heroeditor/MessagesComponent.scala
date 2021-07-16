package demo
package heroeditor

import typings.angularCore.mod.{Component, ComponentCls}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class MessagesComponent(val messageService: MessageService) extends js.Object:
  def ngOnInit(): Unit = ()

object MessagesComponent:
  @JSExportStatic
  val annotations =
    js.Array(
      new ComponentCls(
        new Component {}
          .setSelector("app-messages")
          .setTemplate("""
<div *ngIf="messageService.messages.length">

    <h2>Messages</h2>
    <button class="clear"
            (click)="messageService.clear()">clear</button>
    <div *ngFor='let message of messageService.messages'> {{message}} </div>

</div> """)
          .setStylesVarargs(""" 
h2 {
  color: red;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: lighter;
}
body {
  margin: 2em;
}
body, input[text], button {
  color: crimson;
  font-family: Cambria, Georgia;
}

button.clear {
  font-family: Arial;
  background-color: #eee;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  cursor: hand;
}
button:hover {
  background-color: #cfd8dc;
}
button:disabled {
  background-color: #eee;
  color: #aaa;
  cursor: auto;
}
button.clear {
  color: #888;
  margin-bottom: 12px;
}
""")
      )
    )

  @JSExportStatic
  val parameters = js.Array(typeOf[MessageService])
end MessagesComponent
