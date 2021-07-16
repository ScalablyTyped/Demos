package demo.pixi

import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.raw.XMLHttpRequest
import typings.highlightJs.mod.{highlightBlock, Node}

import scala.scalajs.js.timers.setTimeout
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** This object is dedicated to display the correct example chosen by the user. When the user changes an example, we
  * stop the current animation, we load the next one and we fetch the Scala source code from Github, displaying it with
  * syntax highlighting using highlight.js
  */
object ExampleSelector:

  private val overallContainer: html.Div = dom.document
    .getElementById("overall-container")
    .asInstanceOf[html.Div]

  private val welcomeContainer: html.Div = dom.document
    .getElementById("welcome-message")
    .asInstanceOf[html.Div]

  private val titleH1: html.Heading =
    dom.document.getElementById("title").asInstanceOf[html.Heading]

  private val codeDiv: html.Div =
    dom.document.getElementById("code-container").asInstanceOf[html.Div]

  private val canvasContainer: html.Div = dom.document
    .getElementById("canvas-container")
    .asInstanceOf[html.Div]

  private val pixiUrlLink: html.Anchor = dom.document
    .getElementById("pixi-url")
    .asInstanceOf[html.Anchor]

  def loadAndDisplayCode(example: PIXIExample, pkg: String): Unit =
    val request = new XMLHttpRequest

    def display(): Unit =
      // checking if the page is still the same when we receive the response
      if titleH1.textContent == example.name & request.readyState == 4 & request.status == 200 then
        val code = request.responseText

        codeDiv.innerHTML = s"""<pre><code class="scala hljs">$code</code></pre>"""
        setTimeout(1) {

          highlightBlock(codeDiv.firstChild.firstChild.asInstanceOf[Node])
        }

    request.open("GET", example.githubUrl(pkg))
    request.send()

    request.onreadystatechange = (_: dom.Event) => display()
  end loadAndDisplayCode

  /** Removes the content of the canvas-container div, and puts a new html.Canvas into it.
    */
  def changeCanvas(example: PIXIExample): html.Canvas =
    val children = canvasContainer.children
    for idx <- 0 until children.length do canvasContainer.removeChild(children(idx))

    titleH1.textContent = example.name
    pixiUrlLink.href = example.pixiUrl
    canvasContainer.appendChild(example.canvas)
    example.canvas
  end changeCanvas

  private val menu: html.Div =
    dom.document.getElementById("menu").asInstanceOf[html.Div]

  def makeSection(title: String, examples: List[PIXIExample]): Unit =
    menu.appendChild {
      val div = dom.document.createElement("div").asInstanceOf[html.Div]
      div.className = "menu-section"

      val exampleContainer =
        dom.document.createElement("div").asInstanceOf[html.Div]
      exampleContainer.style.display = "none"
      for example <- examples do
        val option = dom.document.createElement("div").asInstanceOf[html.Option]
        option.className = "example-option"
        option.textContent = example.name
        option.value = example.name

        option.onclick = (_: dom.MouseEvent) =>
          println(s"Running example ${example.name}")
          welcomeContainer.innerHTML = ""
          example.run(title.toLowerCase.filterNot(_ == '-').filterNot(_ == ' '))
          overallContainer.style.display = "block"

        exampleContainer.appendChild(option)
      end for

      val titleDiv = dom.document.createElement("div").asInstanceOf[html.Div]
      titleDiv.className = "section-header"
      titleDiv.textContent = title
      titleDiv.style.cursor = "pointer"
      titleDiv.onclick = (_: dom.MouseEvent) =>
        if exampleContainer.style.display == "none" then exampleContainer.style.display = "block"
        else exampleContainer.style.display = "none"

      div.appendChild(titleDiv)
      div.appendChild(exampleContainer)

      div
    }

  @js.native @JSImport("./styles.css", JSImport.Namespace)
  object Style extends js.Object

  @js.native @JSImport("./a11y-light.css", JSImport.Namespace)
  object `a11y-light.css` extends js.Object

  @main
  def main: Unit =

    /** Touch to load */
    Style
    `a11y-light.css`

    PIXIExample.allExamples.foreach { case (title, examples) =>
      makeSection(title, examples)
    }
  end main
end ExampleSelector
