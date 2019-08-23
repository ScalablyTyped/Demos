package demo

import typings.reactDashNative.Anon_Height
import typings.reactDashNative.reactDashNativeMod.{TextStyle, ViewStyle}
import typings.reactDashNative.reactDashNativeStrings.{bold, center}

object styles {
  @inline def red500 = "#f44336"

  val drawerItems        = ViewStyle(marginTop  = 0)
  val wholeContainer     = ViewStyle(flex       = 1)
  val drawerHeader       = ViewStyle(height     = 200, justifyContent = center, alignItems = center, backgroundColor = red500)
  val drawerHeaderLogo   = TextStyle(fontWeight = bold, fontSize = 20, color = "white")
  val navScreenContainer = ViewStyle(flex       = 1)
  val navScreenHeader    = ViewStyle(height     = 44, paddingTop = 10, marginTop = 20, backgroundColor = red500)
  val sampleText         = TextStyle(margin     = 14, shadowOffset = Anon_Height(height = 10, width = 10))
}
