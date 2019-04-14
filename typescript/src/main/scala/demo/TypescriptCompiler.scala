package demo

import typings.nodeLib.{processMod => process}
import typings.stdLib.^.console
import typings.typescriptLib.typescriptMod.{CompilerOptions, DiagnosticMessageChain, Program}
import typings.typescriptLib.typescriptMod.{^ => ts}

import scala.scalajs.js
import scala.scalajs.js.|

object TypescriptCompiler {
  def compile(fileNames: js.Array[String], options: CompilerOptions): Unit = {
    val program: Program = ts.createProgram(fileNames, options)
    val emitResult = program.emit()

    val allDiagnostics = ts
      .getPreEmitDiagnostics(program)
      .concat(emitResult.diagnostics)

    allDiagnostics.foreach(diagnostic => {
      val baseMessage = ts.flattenDiagnosticMessageText(Knowledge.force(diagnostic.messageText), "\n")

      val message = diagnostic.file.fold(baseMessage) { file =>
        val pos = file.getLineAndCharacterOfPosition(diagnostic.start.get)
        s"${diagnostic.file.get.fileName} (${pos.line + 1},${pos.character + 1}): $baseMessage"
      }

      console.log(message)
    })

    val exitCode = if (emitResult.emitSkipped) 1 else 0
    console.log(s"Process exiting with code '$exitCode'.")
    process.exit(exitCode)
  }

  def main(args: Array[String]): Unit = {
    val files: js.Array[String] =
      process.argv.drop(2) match {
        case empty if empty.length == 0 =>
          js.Array(
            "typescript/src/main/resources/good.ts",
            "typescript/src/main/resources/bad.ts",
          )
        case nonEmpty => nonEmpty
      }

    compile(files, CompilerOptions(noEmitOnError = true, noImplicitAny = true, outDir = "typescript/target/temp"))
  }
}

object Knowledge {
  def force(a: String | DiagnosticMessageChain): DiagnosticMessageChain =
    a.asInstanceOf[DiagnosticMessageChain]
}
