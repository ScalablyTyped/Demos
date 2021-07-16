package demo

import typings.chartJs.mod.{^ as Chart, *}
import typings.moment.mod.Moment
import typings.std.global.document
import typings.std.{stdStrings, Date, HTMLButtonElement, HTMLCanvasElement, HTMLDivElement, MouseEvent}

import scala.scalajs.js
import scala.scalajs.js.|
import scala.util.Random

val random = new Random()

@main
def main(): Unit =
  val section = document.createElement_section(stdStrings.section)
  section.className = "w"
  section.append(
    chart(chartConfig(ChartType.bar, randomData(100, random.nextInt()))),
    chart(chartConfig(ChartType.pie, randomData(100, random.nextInt()))),
    chart(chartConfig(ChartType.polarArea, randomData(100, random.nextInt()))),
    chart(chartConfig(ChartType.line, randomData(100, random.nextInt())))
  )

  document.body.append(section)
end main

def chartConfig(tpe: ChartType, Data: js.Array[js.UndefOr[ChartPoint | Double | Null]]): ChartConfiguration =
  ChartConfiguration()
    .setType(tpe)
    .setData(
      ChartData()
        .setLabels(Labels)
        .setDatasets(
          js.Array(
            ChartDataSets()
              .setLabel("Dataset 1")
              .setData(Data)
              .setBorderWidth(1)
              .setBackgroundColor(BackgroundColor)
              .setBorderColor(BorderColor)
          )
        )
    )
    .setOptions(ChartOptions().setResponsive(true))

def chart(config: ChartConfiguration): HTMLDivElement =
  val div:    HTMLDivElement    = document.createElement_div(stdStrings.div)
  val canvas: HTMLCanvasElement = document.createElement_canvas(stdStrings.canvas)
  val chart:  Chart             = new Chart(canvas, config)

  def dataSetsU: js.UndefOr[js.Array[ChartDataSets]] =
    config.data.flatMap(_.datasets)

  val randomizeBtn = button("Randomize Data") { (_, _) =>
    dataSetsU.foreach(_.foreach(dataset => dataset.data = randomData(100, random.nextInt())))
    chart.update()
  }

  val addDataSet = button("Add Dataset") { (_, _) =>
    val newDataset = ChartDataSets()
      .setLabel("Dataset " + dataSetsU.fold(0)(_.length + 1))
      .setData(randomData(100, random.nextInt()))
      .setBorderWidth(1)
      .setBackgroundColor(BackgroundColor)
      .setBorderColor(BorderColor)

    dataSetsU.foreach(_.push(newDataset))
    chart.update()
  }

  val removeDataset = button("Remove dataset") { (_, _) =>
    dataSetsU.foreach(_.splice(0, 1))
    chart.update()
  }

  div.append(canvas, randomizeBtn, addDataSet, removeDataset)
  div
end chart

def button(title: String)(onClick: js.ThisFunction1[HTMLButtonElement, MouseEvent, js.Any]): HTMLButtonElement =
  val btn = document.createElement_button(stdStrings.button)
  btn.textContent = title
  btn.addEventListener_click(stdStrings.click, onClick)
  btn
end button

def randomData(max: Int, seed: Int): js.Array[js.UndefOr[ChartPoint | Double | scala.Null]] =
  val random = new Random(seed)
  js.Array[js.UndefOr[ChartPoint | Double | Null]](
    random.nextInt(max),
    random.nextInt(max),
    random.nextInt(max),
    random.nextInt(max),
    random.nextInt(max),
    random.nextInt(max)
  )
end randomData

val Labels: js.Array[String | js.Array[Date | Double | Moment | String] | Double | Date | Moment] =
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
