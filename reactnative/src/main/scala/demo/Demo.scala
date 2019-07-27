package demo

import demo.Knowledge.Force
import typings.reactDashNative.reactDashNativeMod.AppRegistryNs

object Demo {
  def main(args: Array[String]): Unit =
    AppRegistryNs.registerComponent("reactnative", () => routing.AppContainer.force)
}
