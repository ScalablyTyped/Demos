package demo.monkeypatching

import typings.pixiJs.PIXI.Ticker

import scala.language.implicitConversions
import scala.scalajs.js

object PIXIPatching:

  implicit class TickerWithDoubleAdd(ticker: Ticker):
    def add(fn: Double => Unit): Ticker = ticker.add(fn.asInstanceOf[Any => Any])

    def add(fn: () => Unit): Ticker = ticker.add(_ => fn())
