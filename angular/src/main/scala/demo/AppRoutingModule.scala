package demo

import typings.angularCore.mod.{NgModule, NgModuleCls}
import typings.angularRouter.mod.{Route, RouterModule, Routes}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class AppRoutingModule extends js.Object

object AppRoutingModule:

  @JSExportStatic
  val annotations =
    js.Array(
      new NgModuleCls(
        new NgModule {}
          .setImportsVarargs(unspecify(RouterModule.forRoot(routes)))
          .setExports(js.Array(typeOf[RouterModule]))
      )
    )

  def routes: Routes = js.Array(
    Route().setPath("heroes").setComponent(typeOf[heroeditor.HeroesComponent]),
    Route().setPath("dashboard").setComponent(typeOf[heroeditor.DashboardComponent]),
    Route().setPath("").setRedirectTo("/dashboard").setPathMatch("full"),
    Route().setPath("detail/:id").setComponent(typeOf[heroeditor.HeroDetailComponent])
  )
end AppRoutingModule
