package mainprocess

import typings.electron.Electron.BrowserWindowConstructorOptions
import typings.electron.electronStrings
import typings.electron.mod.{BrowserWindow, app}
import typings.node.{urlMod, pathMod as path}
import typings.node.urlMod.UrlObject

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("electron", "BrowserWindow")
@js.native
class FixedBrowserWindow(options: BrowserWindowConstructorOptions) extends typings.electron.mod.BrowserWindow

/** This object represents the translation into Scala.js of the main.js file presented in the Electron Quick Start
  * guide, available at https://electron.atom.io/docs/tutorial/quick-start/
  */
/** Every created windows will be put into the windows mutable.Set. */
val windows: mutable.Set[BrowserWindow] = mutable.Set()

def createWindow(): Unit =

  /** Printing "hello" in the main process, just to see it in action. */
  println("hello")

  /** Creating the BrowserWindow object, with the desired options.
    */
  val window = new FixedBrowserWindow(
    BrowserWindowConstructorOptions().setHeight(600).setWidth(800)
  )
  window.loadURL(
    urlMod.format(
      UrlObject().setPathname(path.join(app.getAppPath(), "index.html")).setProtocol("file:").setSlashes(true)
    )
  )

  /** In Scala.js, you have two modes for compiling your code. A fast and a full optimized way. Fast optimisation should
    * be used in development only. Compilation time is significantly shorted, and to a lesser extent, produces more
    * "human readable" JavaScript code in case you really want to have a look. Full optimized compilation leads to more
    * optimized JavaScript code, as well as lighter JavaScript files.
    *
    * As a result, Scala.js also allows you to perform snippets of codes depending on the mode of compilation you chose.
    * In this case, we only want to open the dev tools of a window if we are in development mode, but it is something
    * that you clearly want to avoid in production code.
    */
  if scala.scalajs.LinkingInfo.developmentMode then window.webContents.openDevTools()
  end if

  window.on_closed(electronStrings.closed, () => windows -= window)

  windows += window
end createWindow

/** The main method is called automatically when the JavaScript file is loaded, thanks to the sbt setting
  * scalaJSUseMainModuleInitializer := true in the build.sbt file. Removing this method will fail at compile time.
  */
@main
def main: Unit =
  app.on_ready(electronStrings.ready, (event, launchInfo) => createWindow())
  app.on_windowallclosed(electronStrings.`window-all-closed`, () => app.quit())
end main
