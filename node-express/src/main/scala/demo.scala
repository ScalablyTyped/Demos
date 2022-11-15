import typings.express.mod as e
import typings.expressServeStaticCore.mod.*
import typings.node.global.console
import typings.node.processMod.^ as process

import scala.scalajs.js

object WelcomeController:

  val Router: Router =
    e.Router()

  val Index: RequestHandler[Unit, String, Unit, Unit, Unit] =
    (_, res, _) => res.send("Hello, World!")

  trait HasName extends js.Object:
    val name: js.UndefOr[String]

  val Name: RequestHandler[HasName, String, Unit, Unit, Unit] = (req, res, next) =>
    res.send(s"Hello, ${req.params.name.getOrElse("No Name")}!")

  Router
    .get[Unit, String, Unit, Unit, Unit]("/", Index)
    .get[HasName, String, Unit, Unit, Unit]("/:name", Name)

end WelcomeController

@main
def main: Unit =
  val app  = e()
  val port = process.env.get("PORT").flatMap(_.toOption).fold(3000)(_.toInt)
  app.use("/welcome", WelcomeController.Router)

  app.listen(port, () => console.log(s"Listening at http://localhost:$port/"))
end main
