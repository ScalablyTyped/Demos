package demo

import typings.express.expressMod.{^ => express}
import typings.expressDashServeDashStaticDashCore.expressDashServeDashStaticDashCoreMod._
import typings.node.processMod
import typings.std.^.console

import scala.scalajs.js

object WelcomeController {
  val Router: Router =
    express.Router()

  val Index: RequestHandler[Unit] =
    (_, res, _) => res.send(js.defined("Hello, World!"))

  trait HasName extends js.Object {
    val name: js.UndefOr[String]
  }

  val Name: RequestHandler[HasName] = (req, res, next) =>
    res.send(js.defined(s"Hello, ${req.params.name.getOrElse("No Name")}!"))

  Router
    .get[Unit]("/", Index)
    .get[HasName]("/:name", Name)
}

object NodeExpress {

  def main(args: Array[String]): Unit = {
    val app  = express()
    val port = processMod.env.get("PORT").flatMap(_.toOption).fold(3000)(_.toInt)
    app.use[ParamsDictionary]("/welcome", WelcomeController.Router)

    app.listen(port, () => console.log(s"Listening at http://localhost:$port/"))
  }
}
