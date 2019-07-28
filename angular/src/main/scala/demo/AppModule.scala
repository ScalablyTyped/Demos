package demo

import demo.heroeditor._
import typings.atAngularCore.atAngularCoreMod.{NgModule, NgModuleCls}
import typings.atAngularForms.atAngularFormsMod.FormsModule
import typings.atAngularPlatformDashBrowser.atAngularPlatformDashBrowserMod.BrowserModule

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
      NgModule(
        imports = js.Array(typeOf[BrowserModule], typeOf[FormsModule], typeOf[AppRoutingModule]),
        declarations = js.Array(
          typeOf[AppComponent],
          typeOf[HeroesComponent],
          typeOf[HeroDetailComponent],
          typeOf[MessagesComponent],
          typeOf[DashboardComponent]
        ),
        bootstrap = js.Array(typeOf[AppComponent]),
        providers = js.Array(typeOf.any[HeroService], typeOf.any[MessageService]),
        exports   = js.Array()
      )
    )
  )
}
