package demo

import typings.d3.d3Mod.{^ => d3}
import typings.d3DashGeo.d3DashGeoMod.{GeoContext, GeoPath, GeoPermissibleObjects, GeoProjection}
import typings.geojson.geojsonMod.{LineString, Position}
import typings.geojson.geojsonStrings
import typings.std.^.{console, document, window}
import typings.std.{stdStrings, CanvasRenderingContext2D, FrameRequestCallback, HTMLCanvasElementCls}

import scala.scalajs.js
import scala.scalajs.js.|

object D3Demo {
  def main(argv: scala.Array[String]): Unit =
    document.getElementsByTagName_canvas(stdStrings.canvas).item(0) match {
      case canvas: HTMLCanvasElementCls =>
        Knowledge.asOption(canvas.getContext_2d(stdStrings.`2d`)) match {
          case Some(ctx) => run(ctx)
          case None      => console.warn("Cannot get 2d context for", canvas)
        }
      case _ => console.warn("Cannot find canvas element")
    }

  def run(context: CanvasRenderingContext2D): Double = {

    context.lineWidth   = 0.4
    context.strokeStyle = "rgba(255, 255, 255, 0.6)"

    val width  = window.innerWidth
    val height = window.innerHeight
    val size: Double = width min height

    d3.select("#content")
      .attr("width", width + "px")
      .attr("height", height + "px")

    val projection: GeoProjection =
      d3.geoOrthographic()
        .scale(0.45 * size)
        .translate(js.Tuple2(0.5 * width, 0.5 * height))

    val geoGenerator: GeoPath[GeoPermissibleObjects, Null] =
      d3.geoPath_ThisDatumObject_GeoPermissibleObjects(projection, Knowledge.isGeoContext(context))

    val geometry = LineString(
      coordinates = js.Array[Position](),
      `type`      = geojsonStrings.LineString
    )

    def rndLon = -180 + Math.random() * 360
    def rndLat = -90 + Math.random() * 180

    def addPoint(): Unit =
      geometry.coordinates.push(js.Array(rndLon, rndLat))

    def update: FrameRequestCallback =
      (time: Double) => {
        if (geometry.coordinates.length < 6000) addPoint()

        projection.rotate(js.Tuple2(time / 100, 1.0))

        context.clearRect(0, 0, width, height)
        context.beginPath()

        geoGenerator(geometry, null)
        context.stroke()

        window.requestAnimationFrame(update)
      }

    window.requestAnimationFrame(update)
  }
}

object Knowledge {
  def isGeoContext(ctx: CanvasRenderingContext2D): GeoContext =
    ctx.asInstanceOf[GeoContext]
  def asOption[T](t: T | Null): Option[T] =
    Option(t.asInstanceOf[T])
}
