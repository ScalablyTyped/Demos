package demo

import org.scalablytyped.runtime.{Instantiable1, Instantiable2}
import slinky.core.{BuildingComponent, KeyAddingStage, KeyAndRefAddingStage, TagMod, WithAttrs, facade => S}
import typings.reactLib.{reactMod => ST}

import scala.language.implicitConversions
import scala.scalajs.js

object ScalableSlinky {
  /* Support using ScalablyTyped components as slinky ExternalComponents */
  @inline implicit def fromExoticComponent[P <: js.Object](c: ST.ExoticComponent[P]): AwaitProps[P] =
    new AwaitProps[P](c.asInstanceOf[js.Any])
  @inline implicit def fromComponentClass[P <: js.Object](c: ST.ComponentClass[P, _]): AwaitProps[P] =
    new AwaitProps[P](c.asInstanceOf[js.Any])
  @inline implicit def fromInstantiable1[P <: js.Object](c: Instantiable1[P, ST.ReactElement]): AwaitProps[P] =
    new AwaitProps[P](c.asInstanceOf[js.Any])
  @inline implicit def fromInstantiable2[P <: js.Object](c: Instantiable2[P, _, ST.ReactElement]): AwaitProps[P] =
    new AwaitProps[P](c.asInstanceOf[js.Any])
  @inline implicit def fromComponentType[P <: js.Object](c: ST.ComponentType[P]): AwaitProps[P] =
    new AwaitProps[P](c.asInstanceOf[js.Any])
  @inline implicit def fromFc[P <: js.Object](c: ST.FunctionComponent[P]): AwaitProps[P] =
    new AwaitProps[P](c.asInstanceOf[js.Any])

  @inline final class AwaitProps[P <: js.Object](private val comp: js.Any) extends AnyVal {
    @inline def props(props: P): BuildingComponent[Nothing, js.Object] =
      new BuildingComponent(js.Array(comp, props))

    @inline def noprops: BuildingComponent[Nothing, js.Object] =
      new BuildingComponent(js.Array(comp, new js.Object))
  }

  @inline implicit final class FromStReactNode(val node: ST.ReactNode) {
    def fromST: TagMod[Any] = node.asInstanceOf[TagMod[Any]]
  }

  /* Support using Slinky things when a ScalablyTyped `ReactElement` is expected */
  @inline final class ToStReactElement(val elem: S.ReactElement) extends AnyVal {
    @inline def toST: ST.ReactElement = elem.asInstanceOf[ST.ReactElement]
  }

  @inline implicit def buildExternal[E, R <: js.Object](comp: BuildingComponent[E, R]): ToStReactElement =
    new ToStReactElement(BuildingComponent.make[E, R](comp))

  @inline implicit def buildWithAttrs[A](withAttrs: WithAttrs[A]): ToStReactElement =
    new ToStReactElement(WithAttrs.build(withAttrs))

  @inline implicit def buildKeyAddingState(stage: KeyAddingStage): ToStReactElement =
    new ToStReactElement(KeyAddingStage.build(stage))

  @inline final implicit def buildKeyAndRefAddingStage[D](stage: KeyAndRefAddingStage[D]): ToStReactElement =
    new ToStReactElement(KeyAndRefAddingStage.build(stage))
}
