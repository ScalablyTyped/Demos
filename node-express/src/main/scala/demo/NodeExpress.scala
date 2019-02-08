package demo

import typings.expressDashServeDashStaticDashCoreLib.expressDashServeDashStaticDashCoreMod._
import typings.expressLib.expressMod.{^ => express}
import typings.nodeLib.processMod
import typings.stdLib.^.console

import scala.scalajs.js

object WelcomeController {
  val Router: Router =
    express.Router()

  val Index: RequestHandler =
    (_, res, _) => res.send(js.defined("Hello, World!"))

  trait HasName extends js.Object {
    val name: js.UndefOr[String]
  }

  val Name: RequestHandler = (req, res, next) => {
    val hasName = req.params.asInstanceOf[HasName]
    res.send(js.defined(s"Hello, ${hasName.name.getOrElse("No Name")}!"))
  }

  Router
    .get("/", Index)
    .get("/:name", Name)
}

object NodeExpress {

  def main(args: Array[String]): Unit = {
    val app  = express()
    val port = processMod.env.get("PORT").flatMap(_.toOption).fold(3000)(_.toInt)
    app.use("/welcome", WelcomeController.Router)

    app.listen(port, () => console.log(s"Listening at http://localhost:$port/"))
  }
}
