# Pixi.js with Scala.js

[Pixi.js](https://pixijs.io) is a library to render blazingly fast 2D animations on Canvas, using WebGL under the hood.

This is the translation of some of the [examples](https://pixijs.io/examples) into Scala.

## Run the example

Issue the `pixi/start` command inside sbt and go to `http://localhost:8013`. You will be presented with a menu that has the same structure as the examples from the Pixi website.

## Assets

Before running the project, you need to download the assets used in the examples. They are available at the [Pixi example repo](https://github.com/pixijs/examples) under `examples`. You can clone that repo and copy paste the `examples/assets` folder into `pixi/assets` (that's right, you'll have a path that looks like `pixi/assets/assets/bunny.png`). The repo contains dummy images so that compilation doesn't fail (most examples will therefore "work" as is but not as in the Pixi website).

## ScalablyTyped parsing failure

The Pixi `.d.ts` file contains an enum with parenthesis in it, which is not yet handled by ScalablyTyped. You can easily replace them following the error file path.
