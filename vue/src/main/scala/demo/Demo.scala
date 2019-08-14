package demo

import demo.Knowledge.{Vue, VueClass}
import org.scalablytyped.runtime.StringDictionary
import typings.vue
import typings.vue.typesOptionsMod.{DefaultProps, PropsDefinition, _}
import typings.vue.typesVnodeMod.VNodeDirective
import typings.vue.typesVueMod.{CombinedVueInstance, Vue}

import scala.scalajs.js
import scala.scalajs.js.ThisFunction0
import scala.scalajs.js.annotation.JSImport

object Todo {

  @js.native
  trait DemoVue extends Vue {
    var title: String                = js.native
    var n:     Double                = js.native
    var todos: js.Array[DemoVueTodo] = js.native
  }

  trait DemoVueTodo extends js.Object {
    var done: Boolean
    val content: String
  }

  trait Methods extends js.Object {
    val clickHandler: js.ThisFunction0[DemoVue, Unit]
    val addTask:      js.ThisFunction0[DemoVue, Unit]
    val change1st:    js.ThisFunction0[DemoVue, Unit]
    val remove:       js.ThisFunction1[DemoVue, Int, Unit]
    val flipAll:      js.ThisFunction0[DemoVue, Unit]
  }

  trait Data extends js.Object {
    val message:  String
    val title:    String
    val todos:    js.Array[DemoVueTodo]
    val barValue: Int
    val n:        Int
  }

  trait Computed extends js.Object {
    val todosComputed: js.ThisFunction0[DemoVue, js.Array[String]]
  }

  def main(args: Array[String]): Unit = {

    val tasks = js.Array("Learn JavaScript", "Learn Vue.js", "Learn Scala.js")

    def ts = new java.util.Date().toString

    Vue.component(
      "my-component",
      ComponentOptions(
        props    = js.Array("myMsg"),
        template = "<p>A custom component with msg {{myMsg}} <slot>default content</slot></p>"
      )
    )

    Vue.directive(
      "mydirective",
      DirectiveOptions(
        update = (el, directive, _, _) => {
          val dir = directive.asInstanceOf[VNodeDirective]
          el.innerHTML = "This comes from my-directive with contents " + dir.value + " and expression " + dir.expression
        }
      )
    )

    val demoOpt =
      ComponentOptions[DemoVue, ThisFunction0[DemoVue, Data], Methods, Computed, PropsDefinition[DemoVue], DefaultProps](
        el = "#demo",
        data = _ =>
          new Data {
            val message = "Hello Vue.js!!!!!"
            val title   = "Todo App"
            val todos = tasks.map(
              c =>
                new DemoVueTodo {
                  var done    = c == tasks.head
                  val content = c
                }
            )
            val barValue = 100
            val n        = 0
          },
        methods = new Methods {
          val clickHandler = demoVue => demoVue.n -= 1

          val addTask = demoVue =>
            demoVue.todos.append(new DemoVueTodo {
              var done    = false
              val content = s"new $ts"
            })

          val change1st = demoVue =>
            Vue.set(demoVue.todos, 0, new DemoVueTodo {
              var done    = false
              val content = ts
            })

          val remove = (demoVue, idx) => Vue.delete(demoVue.todos, idx)

          val flipAll = demoVue => demoVue.todos.foreach(td => td.done = !td.done)
        },
        computed = Knowledge.isAccessors(new Computed {
          val todosComputed = (demoVue: DemoVue) => demoVue.todos.map(_.content)
        }),
        filters = new StringDictionary[js.Function] {
          val reverse: js.Function1[js.Any, String] =
            _.toString.reverse
          val wrap: js.Function3[js.Any, String, String, String] =
            (value: js.Any, begin: String, end: String) => begin + value.toString + end
          val extract: js.Function2[js.UndefOr[js.Array[js.Dynamic]], String, js.UndefOr[js.Array[js.Dynamic]]] =
            (array, field) => array.map(_.map(_.selectDynamic(field)))
        }
      )

    val demo = new VueClass(demoOpt).value

    demo.$watch("title", (_, newValue: js.Any, _: js.Any) => println("changed " + newValue))
  }
}

object Knowledge {

  /**
    * We need a custom import because the normal module doesn't include
    *  a... compiler or something. Seems we're running
    *  code at runtime which could have ran at build time.
    *
    *  Discussion at https://github.com/vuejs-templates/webpack/issues/215
    */
  @JSImport("vue/dist/vue", JSImport.Namespace)
  @js.native
  object Vue extends vue.typesVueMod.VueConstructor[vue.typesVueMod.Vue]

  /**
    * Needs the same custom import as above (it's the same object)
    *
    * In the ScalablyTyped encoding the constructor ended up as an `Instantiable`,
    *  which means it has lost its type parameters. We recreate it here to avoid casting.
    *
    * Finally, the shape of the Vue object is hard to rewrite into a Scala class,
    * because the return type is an intersection type (can't extend from that).
    * That's what's fixed by the `.value` ceremony
    */
  @JSImport("vue/dist/vue", JSImport.Namespace)
  @js.native
  class VueClass[V <: Vue, Data, Methods, Computed, PropsDef, Props](
      options: ComponentOptions[V, Data, Methods, Computed, PropsDef, Props]
  ) extends js.Object

  object VueClass {
    @inline implicit class VueClassOps[V <: Vue, Data, Methods, Computed, PropsDef, Props](
        val instance: VueClass[V, Data, Methods, Computed, PropsDef, Props]
    ) extends AnyVal {
      @inline def value: CombinedVueInstance[V, Data, Methods, Computed, Props] =
        instance.asInstanceOf[CombinedVueInstance[V, Data, Methods, Computed, Props]]
    }
  }

  def isAccessors[T](t: T): Accessors[T] =
    t.asInstanceOf[Accessors[T]]
}
