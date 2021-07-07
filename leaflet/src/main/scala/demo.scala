package demo

import org.scalajs.dom.{document, html}
import typings.leaflet.mod as L

import scala.scalajs.js

val TileLayerUri =
  "https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiZmFuY2VsbHUiLCJhIjoiY2oxMHRzZm5zMDAyMDMycndyaTZyYnp6NSJ9.AJ3owakJtFAJaaRuYB7Ukw"

@main
def main: Unit =
  val el  = document.getElementById("content").asInstanceOf[html.Element]
  val map = L.map(el).setView(L.LatLngLiteral(51.505, -0.09), zoom = 13)

  L.tileLayer(
    TileLayerUri,
    L.TileLayerOptions()
      .setId("mapbox.streets")
      .setMaxZoom(19)
      .setAttribution(
        """Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors,
                        |<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>,
                        |Imagery © <a href="http://mapbox.com">Mapbox</a>""".stripMargin
      )
  ).addTo(map)

  L.marker(L.LatLngLiteral(51.5, -0.09), L.MarkerOptions().setTitle("I am a marker"))
    .bindPopup("I am a popup")
    .addTo(map)

  L.circle(
    L.LatLngLiteral(51.508, -0.11),
    L.CircleMarkerOptions().setColor("red").setFillColor("#f03").setFillOpacity(0.5).setRadius(500)
  ).bindPopup("I am a circle")
    .addTo(map)

  L.circle(
    L.LatLngLiteral(51.516, -0.11),
    L.CircleMarkerOptions().setColor("green").setFillColor("#f03").setFillOpacity(0.5).setRadius(200)
  ).addTo(map)

  L.polygon(js.Array(L.LatLngLiteral(51.509, -0.08), L.LatLngLiteral(51.503, -0.06), L.LatLngLiteral(51.51, -0.047)))
    .bindPopup("I am a polygon")
    .addTo(map)

  L.popup()
    .setLatLng(L.LatLngLiteral(51.5, -0.09))
    .setContent("I am a <b>standalone</b> popup.")
    .openOn(map)
end main
