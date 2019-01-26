package demo
import demo.heroeditor._
import typings.atAngularCoreLib.atAngularCoreMod.{^ => Core}
import typings.atAngularCoreLib.srcDiProviderMod.Provider
import typings.atAngularCoreLib.srcMetadataNgUnderscoreModuleMod.{ModuleWithProviders, NgModule}
import typings.atAngularCoreLib.srcTypeMod.Type
import typings.atAngularFormsLib.atAngularFormsMod.FormsModule
import typings.atAngularPlatformDashBrowserLib.atAngularPlatformDashBrowserMod.BrowserModule

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic
import scala.scalajs.js.|

/**
  * The @NgModule Decorator is implemented in the companion object, under annotations static field.
  */
final class AppModule extends js.Object

object AppModule {

  @JSExportStatic
  val annotations = js.Array(
    Core.NgModule.newInstance1(new NgModule {
      imports = js.Array[Import](typeOf[BrowserModule], typeOf[FormsModule], typeOf[AppRoutingModule])

      declarations = js.Array[Type[_] | js.Array[_]](
        typeOf[AppComponent],
        typeOf[HeroesComponent],
        typeOf[HeroDetailComponent],
        typeOf[MessagesComponent],
        typeOf[DashboardComponent]
      )
      bootstrap = js.Array[Type[_] | js.Array[_]](typeOf[AppComponent])
      providers = js.Array[Provider](typeOf.any[HeroService], typeOf.any[MessageService])
      exports   = js.Array[Type[_] | js.Array[_]]()
    })
  )
}
