package demo

import typings.chartDotJsLib.chartDotJsLibStrings
import typings.chartDotJsLib.chartDotJsMod.ChartNs._
import typings.chartDotJsLib.chartDotJsMod.{namespaced => Chart}
import typings.stdLib.^.document
import typings.stdLib.{stdLibStrings, HTMLButtonElement, HTMLCanvasElement, HTMLDivElement, MouseEvent}

import scala.scalajs.js
import scala.scalajs.js.|
import scala.util.Random

object Demo {
  val random = new Random()

  def main(argv: scala.Array[String]): Unit = {

    val section = document.createElement_section(stdLibStrings.section)
    section.className = "w"
    section.append(
      chart(chartConfig(chartDotJsLibStrings.bar, randomData(100, random.nextInt()))),
      chart(chartConfig(chartDotJsLibStrings.pie, randomData(100, random.nextInt()))),
      chart(chartConfig(chartDotJsLibStrings.polarArea, randomData(100, random.nextInt()))),
      chart(chartConfig(chartDotJsLibStrings.line, randomData(100, random.nextInt()))),
    )

    document.body.append(section)
  }

  def chartConfig(tpe: ChartType, Data: js.Array[js.UndefOr[Double | Null]]): ChartConfiguration =
    ChartConfiguration(
      `type` = tpe,
      data = ChartData(
        labels = Labels,
        datasets = js.Array(
          ChartDataSets(
            label           = "Dataset 1",
            data            = Data,
            borderWidth     = 1,
            backgroundColor = BackgroundColor,
            borderColor     = BorderColor
          )
        )
      ),
      options = ChartOptions(responsive = true)
    )

  def chart(config: ChartConfiguration): HTMLDivElement = {
    val div:    HTMLDivElement    = document.createElement_div(stdLibStrings.div)
    val canvas: HTMLCanvasElement = document.createElement_canvas(stdLibStrings.canvas)
    val c:      Chart             = new Chart(canvas, config)

    def dataSetsU: js.UndefOr[js.Array[ChartDataSets]] =
      config.data.flatMap(_.datasets)

    val randomizeBtn = button("Randomize Data", (_, _) => {
      dataSetsU.foreach(_.foreach(dataset => dataset.data = randomData(100, random.nextInt())))
      c.update()
    })

    val addDataSet = button(
      "Add Dataset",
      (_, _) => {
        val newDataset = ChartDataSets(
          label           = "Dataset " + dataSetsU.fold(0)(_.length + 1),
          data            = randomData(100, random.nextInt()),
          borderWidth     = 1,
          backgroundColor = BackgroundColor,
          borderColor     = BorderColor,
        )

        dataSetsU.foreach(_.push(newDataset))
        c.update()
      }
    )

    val removeDataset = button("Remove dataset", (_, _) => {
      dataSetsU.foreach(_.splice(0, 1))
      c.update()
    })

    div.append(canvas, randomizeBtn, addDataSet, removeDataset)
    div
  }

  def button(title: String, onClick: js.ThisFunction1[HTMLButtonElement, MouseEvent, Unit]): HTMLButtonElement = {
    val btn = document.createElement_button(stdLibStrings.button)
    btn.textContent = title
    btn.addEventListener_click(stdLibStrings.click, onClick)
    btn
  }

  def randomData(max: Int, seed: Int): js.Array[js.UndefOr[Double | scala.Null]] = {
    val random = new Random(seed)
    js.Array[js.UndefOr[Double | Null]](
      random.nextInt(max),
      random.nextInt(max),
      random.nextInt(max),
      random.nextInt(max),
      random.nextInt(max),
      random.nextInt(max)
    )
  }

  val Labels: js.Array[java.lang.String | js.Array[java.lang.String]] =
    js.Array("Red", "Blue", "Yellow", "Green", "Purple", "Orange")

  val BackgroundColor: ChartColor =
    js.Array(
      color(54, 162, 235, 0.2),
      color(255, 206, 86, 0.2),
      color(75, 192, 192, 0.2),
      color(153, 102, 255, 0.2),
      color(255, 159, 64, 0.2)
    )

  val BorderColor: ChartColor =
    js.Array(
      color(255, 99, 132, 1),
      color(54, 162, 235, 1),
      color(255, 206, 86, 1),
      color(75, 192, 192, 1),
      color(153, 102, 255, 1),
      color(255, 159, 64, 1)
    )

  def color(r: Int, g: Int, b: Int, a: Double): String =
    s"rgba($r, $g, $b, $a)"
}
