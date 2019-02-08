package demo

import typings.reactDashNativeLib.{reactDashNativeLibStrings, Anon_Height}
import typings.reactDashNativeLib.reactDashNativeMod.{TextStyle, ViewStyle}

import scala.scalajs.js

object styles {
  @inline def red500 = "#f44336"

  object drawerItems extends ViewStyle {
    marginTop = 0
  }

  object wholeContainer extends ViewStyle {
    flex = js.defined(1)
  }

  object drawerHeader extends ViewStyle {
    height          = 200
    justifyContent  = reactDashNativeLibStrings.center
    alignItems      = reactDashNativeLibStrings.center
    backgroundColor = red500
  }

  object drawerHeaderLogo extends TextStyle {
    fontWeight = reactDashNativeLibStrings.bold
    fontSize   = js.defined(20)
    color      = "white"
  }

  object navScreenContainer extends ViewStyle {
    flex = js.defined(1)
  }

  object navScreenHeader extends ViewStyle {
    height          = 44
    paddingTop      = 0
    backgroundColor = red500
  }

  object sampleText extends TextStyle {
    margin = 14
    shadowOffset = js.defined(new Anon_Height {
      override var height: Double = 10
      override var width:  Double = 10
    })
  }
}
