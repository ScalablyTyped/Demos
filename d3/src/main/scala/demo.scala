package demo

import typings.d3.mod as d3Mod
import typings.d3Geo.mod.{GeoContext, GeoProjection_}
import typings.geojson.geojsonStrings
import typings.geojson.mod.{LineString, Position}
import typings.std.global.{console, document, window}
import typings.std.{stdStrings, CanvasRenderingContext2D, FrameRequestCallback, HTMLCanvasElement}

import scala.scalajs.js
import scala.scalajs.js.|

// where is my `?.` :( :(
extension [T](ot: T | Null)
  def andThen[U](f: T => U | Null): U | Null =
    if ot != null then f(ot.asInstanceOf[T]) else null // todo: revisit with explicit nulls

// this conforms structurally
def isGeoContext(ctx: CanvasRenderingContext2D): GeoContext =
  ctx.asInstanceOf[GeoContext]

@main
def main(): Unit =
  val maybeContext: CanvasRenderingContext2D | Null =
    document
      .getElementsByTagName_canvas(stdStrings.canvas)
      .item(0)
      .andThen(_.getContext_2d(stdStrings.`2d`))

  if maybeContext != null then start(maybeContext)
  else sys.error("Cannot get 2d context")
end main

def start(context: CanvasRenderingContext2D): Double =
  context.lineWidth = 0.4
  context.strokeStyle = "rgba(255, 255, 255, 0.6)"

  val width  = window.innerWidth
  val height = window.innerHeight
  val size   = width min height

  d3Mod
    .select("#content")
    .attr("width", s"${width}px")
    .attr("height", s"${height}px")

  val projection: GeoProjection_ =
    d3Mod
      .geoOrthographic()
      .scale(0.45 * size)
      .translate(js.Tuple2(0.5 * width, 0.5 * height))

  val geoGenerator =
    d3Mod.geoPath(projection, isGeoContext(context))

  val geometry = LineString(coordinates = js.Array[Position]())

  def rndLon = -180 + Math.random() * 360
  def rndLat = -90 + Math.random() * 180

  def addPoint(): Unit =
    geometry.coordinates.push(js.Array(rndLon, rndLat))

  def update: FrameRequestCallback =
    (time: Double) =>
      if geometry.coordinates.length < 6000 then addPoint()

      projection.rotate(js.Tuple2(time / 100, 1.0))

      context.clearRect(0, 0, width, height)
      context.beginPath()

      geoGenerator(geometry, null.asInstanceOf[js.Any])
      context.stroke()

      window.requestAnimationFrame(update)

  window.requestAnimationFrame(update)
end start
