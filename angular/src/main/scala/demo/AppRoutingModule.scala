package demo

import typings.atAngularCoreLib.atAngularCoreMod.NgModuleCls
import typings.atAngularCoreLib.srcMetadataNgUnderscoreModuleMod.NgModule
import typings.atAngularRouterLib.atAngularRouterMod.RouterModule
import typings.atAngularRouterLib.srcConfigMod.{Route, Routes}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class AppRoutingModule extends js.Object

object AppRoutingModule {

  @JSExportStatic
  val annotations =
    js.Array(
      new NgModuleCls(
        NgModule(
          imports = js.Array(unspecify(RouterModule.forRoot(routes))),
          exports = js.Array(typeOf[RouterModule])
        )
      )
    )

  def routes: Routes = js.Array(
    Route(
      path      = "heroes",
      component = typeOf[heroeditor.HeroesComponent]
    ),
    Route(
      path      = "dashboard",
      component = typeOf[heroeditor.DashboardComponent]
    ),
    Route(
      path       = "",
      redirectTo = "/dashboard",
      pathMatch  = "full"
    ),
    Route(
      path      = "detail/:id",
      component = typeOf[heroeditor.HeroDetailComponent]
    )
  )
}
