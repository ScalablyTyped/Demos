package demo.meshandshaders

import demo.assets.BackgroundSceneRotate
import demo.pixi.PIXIExample
import typings.pixiJs.mod.{Application, Geometry, Mesh, Shader, Texture}
import typings.pixiJs.PIXI.Buffer
import demo.monkeypatching.PIXIPatching.*

import scala.scalajs.js
import scala.scalajs.js.|

case object Uniform extends PIXIExample:
  val name:    String = "Uniform"
  val pixiUrl: String = "https://pixijs.io/examples/#/mesh-and-shaders/uniforms.js"

  protected def newApplication(): Application =

    val app = new Application()

    val geometry = new Geometry()
      .addAttribute(
        "aVertexPosition", // the attribute name
        js.Array(-100.0, -100, // x, y
          100, -100, // x, y
          100, 100, -100, 100)
          .asInstanceOf[js.UndefOr[js.Array[Double] | Buffer]], // x, y
        2,
        js.undefined,
        js.undefined,
        js.undefined,
        js.undefined
      ) // the size of the attribute
      .addAttribute(
        "aUvs", // the attribute name
        js.Array(0.0, 0, // u, v
          1, 0, // u, v
          1, 1, 0, 1)
          .asInstanceOf[js.UndefOr[js.Array[Double] | Buffer]], // u, v
        2,
        js.undefined,
        js.undefined,
        js.undefined,
        js.undefined
      ) // the size of the attribute
      .addIndex(js.Array(0.0, 1, 2, 0, 2, 3))

    val vertexSrc =
      """
        |precision mediump float;
        |
        |    attribute vec2 aVertexPosition;
        |    attribute vec2 aUvs;
        |
        |    uniform mat3 translationMatrix;
        |    uniform mat3 projectionMatrix;
        |
        |    varying vec2 vUvs;
        |
        |    void main() {
        |
        |        vUvs = aUvs;
        |        gl_Position = vec4((projectionMatrix * translationMatrix * vec3(aVertexPosition, 1.0)).xy, 0.0, 1.0);
        |
        |    }
        |""".stripMargin

    val fragmentSrc = """
                        |precision mediump float;
                        |
                        |    varying vec2 vUvs;
                        |
                        |    uniform sampler2D uSampler2;
                        |    uniform float time;
                        |
                        |    void main() {
                        |
                        |        gl_FragColor = texture2D(uSampler2, vUvs + sin( (time + (vUvs.x) * 14.) ) * 0.1 );
                        |    }
                        """.stripMargin

    class Uniform(val uSampler2: Texture, var time: Double) extends js.Object

    val uniforms = new Uniform(
      Texture.from(BackgroundSceneRotate).asInstanceOf[typings.pixiJs.mod.Texture],
      time = 0
    )

    val shader = Shader.from(vertexSrc, fragmentSrc, uniforms)

    val quad = new Mesh(geometry, shader)

    quad.position.set(400, 300)
    quad.scale.set(2)

    app.stage.addChild(quad)

    app.ticker.add { () =>
      quad.rotation += 0.01
      quad.shader.asInstanceOf[Shader].uniforms.asInstanceOf[Uniform].time += 0.1
    }

    app
  end newApplication
end Uniform
