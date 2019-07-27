package demo

import typings.googlemaps.google.maps
import typings.std.document

import scala.scalajs.js.|

object Demo {
  val beaches: Map[String, maps.LatLng] =
    Map(
      "Bondi Beach" -> new maps.LatLng(-33.890542, 151.274856),
      "Coogee Beach" -> new maps.LatLng(-33.923036, 151.259052),
      "Cronulla Beach" -> new maps.LatLng(-34.028249, 151.157507),
      "Manly Beach" -> new maps.LatLng(-33.80010128657071, 151.28747820854187)
    )

  def main(argv: scala.Array[String]): Unit = {
    val containerId = "content"

    Knowledge.asOption(document.getElementById(containerId)) match {
      case Some(container) =>
        val m = new maps.Map(
          container,
          maps.MapOptions(
            center = new maps.LatLng(-33.9, 151.2),
            zoom   = 4
          )
        )

        val info = new maps.InfoWindow

        beaches.foreach {
          case (beach, pos) =>
            val marker = new maps.Marker(
              maps.ReadonlyMarkerOptions(
                position = pos,
                title    = beach,
                map      = m
              )
            )

            maps.event.addListener(marker, "click", _ => {
              info.setContent(s"<h3>This is $beach </h3>")
              info.open(m, marker)
            })
        }

      case _ => sys.error(s"Could not find $containerId")
    }
  }
}

object Knowledge {
  def asOption[T](ot: T | Null): Option[T] = Option(ot.asInstanceOf[T])
}
