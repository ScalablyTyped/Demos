package demo

import typings.angularCore.mod.{NgModule, NgModuleCls}
import typings.angularRouter.mod.{Route, RouterModule, Routes}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class AppRoutingModule extends js.Object

object AppRoutingModule {

  @JSExportStatic
  val annotations =
    js.Array(
      new NgModuleCls(
        new NgModule {
          imports = js.defined(js.Array(unspecify(RouterModule.forRoot(routes))))
          exports = js.defined(js.Array(typeOf[RouterModule]))
        }
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
