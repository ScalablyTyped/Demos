package demo

import demo.heroeditor.*
import typings.angularCore.mod.{NgModule, NgModuleCls}
import typings.angularForms.mod.FormsModule
import typings.angularPlatformBrowser.mod.BrowserModule

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

/** The @NgModule Decorator is implemented in the companion object, under annotations static field.
  */
final class AppModule extends js.Object

object AppModule:

  @JSExportStatic
  val annotations = js.Array(
    new NgModuleCls(
      new NgModule {}
        .setImportsVarargs(
          typeOf[BrowserModule],
          typeOf[FormsModule],
          typeOf[AppRoutingModule]
        )
        .setDeclarationsVarargs(
          typeOf[AppComponent],
          typeOf[HeroesComponent],
          typeOf[HeroDetailComponent],
          typeOf[MessagesComponent],
          typeOf[DashboardComponent]
        )
        .setBootstrapVarargs(typeOf[AppComponent])
        .setProvidersVarargs(typeOf[HeroService], typeOf[MessageService])
    )
  )
end AppModule
