import typings.node.global.console
import typings.node.processMod as process
import typings.typescript.mod as ts

import scala.scalajs.js
import scala.scalajs.js.|

def compile(fileNames: js.Array[String], options: ts.CompilerOptions): Unit =
  val program: ts.Program = ts.createProgram(fileNames, options)
  val emitResult = program.emit()

  val allDiagnostics = ts.getPreEmitDiagnostics(program).concat(emitResult.diagnostics)

  allDiagnostics.foreach { diagnostic =>
    val baseMessage = ts.flattenDiagnosticMessageText(Knowledge.force(diagnostic.messageText), "\n")

    val message = diagnostic.file.fold(baseMessage) { file =>
      val pos = file.getLineAndCharacterOfPosition(diagnostic.start.get)
      s"${diagnostic.file.get.fileName} (${pos.line + 1},${pos.character + 1}): $baseMessage"
    }
    console.log(message)
  }

  val exitCode = if emitResult.emitSkipped then 1 else 0
  console.log(s"Process exiting with code '$exitCode'.")
  process.exit(exitCode)
end compile

@main def main: Unit =
  val files: js.Array[String] =
    process.argv.drop(2) match
      case empty if empty.length == 0 =>
        js.Array(
          "typescript/src/main/resources/good.ts",
          "typescript/src/main/resources/bad.ts"
        )
      case nonEmpty => nonEmpty

  compile(
    files,
    ts.CompilerOptions().setNoEmitOnError(true).setNoImplicitAny(true).setOutDir("typescript/target/temp")
  )
end main

object Knowledge:
  def force(a: String | ts.DiagnosticMessageChain): ts.DiagnosticMessageChain =
    a.asInstanceOf[ts.DiagnosticMessageChain]
