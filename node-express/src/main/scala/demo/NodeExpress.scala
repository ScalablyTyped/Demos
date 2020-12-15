package demo

import typings.express.{mod => e}
import typings.expressServeStaticCore.mod._
import typings.node.global.console
import typings.node.{processMod => process}

import scala.scalajs.js

object WelcomeController {

  val Router: Router =
    e.Router()

  val Index: RequestHandler[Unit, String, Unit] =
    (_, res, _) => res.send("Hello, World!")

  trait HasName extends js.Object {
    val name: js.UndefOr[String]
  }

  val Name: RequestHandler[HasName, String, Unit] = (req, res, next) =>
    res.send(s"Hello, ${req.params.name.getOrElse("No Name")}!")

  Router
    .get[Unit, String, Unit]("/", Index)
    .get[HasName, String, Unit]("/:name", Name)
}

object NodeExpress {
  def main(args: Array[String]): Unit = {
    val app  = e()
    val port = process.env.get("PORT").flatMap(_.toOption).fold(3000)(_.toInt)
    app.use[ParamsDictionary, js.Any, js.Any]("/welcome", WelcomeController.Router)

    app.listen(port, () => console.log(s"Listening at http://localhost:$port/"))
  }
}
