package demo

import typings.leaflet.{mod => L}
import org.scalajs.dom.document
import scala.scalajs.js

object Main {
  val TileLayerUri =
    "https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiZmFuY2VsbHUiLCJhIjoiY2oxMHRzZm5zMDAyMDMycndyaTZyYnp6NSJ9.AJ3owakJtFAJaaRuYB7Ukw"

  def main(argv: Array[String]): Unit = {
    val el = document.getElementById("content").asInstanceOf[typings.std.HTMLElement]

    val map = L.map(el).setView(js.Tuple2(51.505, -0.09), zoom = 13)

    L.tileLayer(
        TileLayerUri,
        L.TileLayerOptions(
          id          = "mapbox.streets",
          maxZoom     = 19,
          attribution = """Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors,
                        |<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>,
                        |Imagery © <a href="http://mapbox.com">Mapbox</a>""".stripMargin
        )
      )
      .addTo(map)

    L.marker(js.Tuple2(51.5, -0.09), L.MarkerOptions(title = "I am a marker"))
      .bindPopup("I am a popup")
      .addTo(map)

    L.circle(
        js.Tuple2(51.508, -0.11),
        L.CircleMarkerOptions(color = "red", fillColor = "#f03", fillOpacity = 0.5, radius = 500)
      )
      .bindPopup("I am a circle")
      .addTo(map)

    L.circle(
        js.Tuple2(51.516, -0.11),
        L.CircleMarkerOptions(color = "green", fillColor = "#f03", fillOpacity = 0.5, radius = 200)
      )
      .addTo(map)

    L.polygon(js.Array(js.Tuple2(51.509, -0.08), js.Tuple2(51.503, -0.06), js.Tuple2(51.51, -0.047)))
      .bindPopup("I am a polygon")
      .addTo(map)

    L.popup()
      .setLatLng(js.Tuple2(51.5, -0.09))
      .setContent("I am a <b>standalone</b> popup.")
      .openOn(map)
  }
}
