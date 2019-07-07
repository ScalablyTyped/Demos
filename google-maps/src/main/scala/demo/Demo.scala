package demo

import typings.googlemapsLib.googleNs.{mapsNs => GMaps}
import typings.stdLib.^.document

import scala.scalajs.js.|

object Demo {
  val beaches: Map[String, GMaps.LatLng] =
    Map(
      "Bondi Beach" -> new GMaps.LatLng(-33.890542, 151.274856),
      "Coogee Beach" -> new GMaps.LatLng(-33.923036, 151.259052),
      "Cronulla Beach" -> new GMaps.LatLng(-34.028249, 151.157507),
      "Manly Beach" -> new GMaps.LatLng(-33.80010128657071, 151.28747820854187),
    )

  def main(argv: scala.Array[String]): Unit = {
    val containerId = "content"

    Knowledge.asOption(document.getElementById(containerId)) match {
      case Some(container) =>
        val m = new GMaps.Map(
          container,
          GMaps.MapOptions(
            center = new GMaps.LatLng(-33.9, 151.2),
            zoom   = 4
          )
        )

        val info = new GMaps.InfoWindow

        beaches.foreach {
          case (beach, pos) =>
            val marker = new GMaps.Marker(
              GMaps.ReadonlyMarkerOptions(
                position = pos,
                title    = beach,
                map      = m
              )
            )

            GMaps.event.addListener(marker, "click", () => {
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
