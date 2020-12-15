package demo

import demo.heroeditor._
import typings.angularCore.mod.{NgModule, NgModuleCls}
import typings.angularForms.mod.FormsModule
import typings.angularPlatformBrowser.mod.BrowserModule

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

/**
  * The @NgModule Decorator is implemented in the companion object, under annotations static field.
  */
final class AppModule extends js.Object

object AppModule {

  @JSExportStatic
  val annotations = js.Array(
    new NgModuleCls(
      new NgModule{}
        .setImportsVarargs(
          typeOf.any[BrowserModule],
          typeOf.any[FormsModule],
          typeOf.any[AppRoutingModule]
        )
        .setDeclarationsVarargs(
          typeOf.any[AppComponent],
          typeOf.any[HeroesComponent],
          typeOf.any[HeroDetailComponent],
          typeOf.any[MessagesComponent],
          typeOf.any[DashboardComponent]
        )
        .setBootstrapVarargs(typeOf.any[AppComponent])
        .setProvidersVarargs(typeOf.any[HeroService], typeOf.any[MessageService])
    )
  )
}
