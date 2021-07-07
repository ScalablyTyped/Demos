package demo

import org.scalajs.dom.document
import org.scalajs.dom.raw.Element
import typings.googlemaps.google.maps.{MapOptions, ReadonlyMarkerOptions}
import typings.googlemaps.global.google.maps

val beaches: Map[String, maps.LatLng] =
  Map(
    "Bondi Beach" -> new maps.LatLng(-33.890542, 151.274856),
    "Coogee Beach" -> new maps.LatLng(-33.923036, 151.259052),
    "Cronulla Beach" -> new maps.LatLng(-34.028249, 151.157507),
    "Manly Beach" -> new maps.LatLng(-33.80010128657071, 151.28747820854187)
  )

@main
def main: Unit =
  val container = document.getElementById("content")
  val m: maps.Map[Element] = new maps.Map(
    container,
    MapOptions().setCenter(new maps.LatLng(-33.9, 151.2)).setZoom(4)
  )

  val info = new maps.InfoWindow

  beaches.foreach { case (beach, pos) =>
    val marker = new maps.Marker(ReadonlyMarkerOptions().setPosition(pos).setTitle(beach).setMap(m))

    maps.event.addListener(
      marker,
      "click",
      _ =>
        info.setContent(s"<h3>This is $beach </h3>")
        info.open(m, marker)
    )
  }
end main
