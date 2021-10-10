# Demos for ScalablyTyped

This is a collection of tiny demo projects to show off how we can use javascript libraries from scala.js

## React demos

All react demos are now moved to separate repositories by the react library they use.
See slinky demos [here](https://github.com/ScalablyTyped/SlinkyTypedDemos)

## Browser demos 

### d3 
[Demo](https://scalablytyped.github.io/Demos/d3/)

It uses d3 to generate a rather fancy spinning globe. Demo is converted from [here](https://bl.ocks.org/animateddata/1f6522d3fcec29c01e7f4a5894e1fd94)

`sbt> d3/start` starts a webpack-dev-server at http://localhost:8001 .

### google-maps
[Demo](https://scalablytyped.github.io/Demos/google-maps/)

The demo loads the google maps javascript as distributed by google (see [index.html](./google-maps/assets/index.html) ).
It's very simple usage, it just shows the location of a few beaches.

`sbt> google-maps/start` starts a webpack-dev-server at http://localhost:8002 .

### jquery
[Demo](https://scalablytyped.github.io/Demos/jquery/)

This demo shows how to interact with old-style javascript.
Jqueryui is a global library (as in, not a module), so you'll see the code touches an object to include it.
It also extends jquery with more functionality, so you'll see an explicit cast to tell the compiler about this. 
This is poor mans interface augmentation (a mechanism by which typescript does this automatically)

#### Libraries used

- jquery
- jqueryui

`sbt> jquery/start` starts a webpack-dev-server at http://localhost:8003 .

### Vue
[Demo](https://scalablytyped.github.io/Demos/vue/)

This demo showcases a pretty simple todo app (stolen and adapted from [scalajs-vue](https://github.com/fancellu/scalajs-vue/)).
Some templating is done in [index.html](./vue/assets/index.html), while a bunch of stuff is done in Scala.

From its design it's pretty clear that Vue was designed by javascript people.
Trying to obtain type safety in this mess will probably never be worth it, 
but at least now you can try! :)
`sbt> vue/start` starts a webpack-dev-server at http://localhost:8004 .


### Three.js
[Demo](https://scalablytyped.github.io/Demos/three/)

A fancy animation of a horse, stolen from [three.js demos](https://github.com/mrdoob/three.js/blob/master/examples/webgl_morphtargets_horse.html).
`sbt> three/start` starts a webpack-dev-server at http://localhost:8005 .

### Reveal.js
[Demo](https://scalablytyped.github.io/Demos/reveal/)

Write your talks in scala.js! This uses highlight.js and reveal.js along with
 scalajs-react. adapted from [scala-reveal-js](https://github.com/pheymann/scala-reveal-js),
`sbt> reveal/start` starts a webpack-dev-server at http://localhost:8006 .

### Chart.js
[Demo](https://scalablytyped.github.io/Demos/chart/)

Simple charting using canvas elements. Shows off how to work with the DOM as well
 as how to use chart.js. Heavily adapted from the [retyped demo](https://github.com/Retyped/Demos/tree/master/ChartJsDemo),
`sbt> chart/start` starts a webpack-dev-server at http://localhost:8007 .

### Angular 8 
[Demo](https://scalablytyped.github.io/Demos/angular/)

Let's be nice and say that Angular is a reasonable alternative for creating a frontend app.
If you agree, now is your chance to use it with Scala.js.

Adapted from [sherpal's prototype](https://github.com/sherpal/AngularScalaPOC).
`sbt> angular/start` starts a webpack-dev-server at http://localhost:8008 .

### P5
[Demo](https://scalablytyped.github.io/Demos/p5/index.html)

Demo adapted from [documentation](https://p5js.org/examples/instance-mode-instantiation.html)
`sbt> p5/start` starts a webpack-dev-server at http://localhost:8009 .

### Leaflet
[Demo](https://scalablytyped.github.io/Demos/leaflet/index.html)

Demo adapted from [scalajs-leaflet](https://github.com/fancellu/scalajs-leaflet/blob/master/example/src/main/scala/example/QuickStartLeaflet.scala)
`sbt> leaflet/start` starts a webpack-dev-server at http://localhost:8010 .
 

### Onsenui 
[Demo](https://scalablytyped.github.io/Demos/onsenui/index.html)
Adapted from [documentation](https://onsen.io/v2/guide/jquery/)
 
`sbt> onsenui/start` starts a webpack-dev-server at http://localhost:8011 .

### phaser 
[Demo](https://scalablytyped.github.io/Demos/phaser/index.html)
Adapted from [animation/create-from-sprite-config example](http://phaser.io/examples/v3/view/animation/create-from-sprite-config)
 
`sbt> phaser/start` starts a webpack-dev-server at http://localhost:8012 .

### Pixi
[Demo](https://scalablytyped.github.io/Demos/pixi/index.html) 
This is the translation of some of the [examples](https://pixijs.io/examples) into Scala.

[Pixi.js](https://pixijs.io) is a library to render blazingly fast 2D animations on Canvas, using WebGL under the hood.

`sbt> pixi/start` starts a webpack-dev-server at http://localhost:8013 .

You will be presented with a menu that has the same structure as the examples from the Pixi website.
 
## Electron
Implements the backend/mainprocess part of an Electron app in Scala.js,
 though it would be easy to do the frontend as well (in another module).

Start the project like this:

```
sbt>electron/run
``` 

Again adapted from [sherpal's work](https://github.com/sherpal/Scala.js-Electron-App-Example).

## Node demos

### lodash
This is a very simple app which uses a few functions from lodash.
`sbt> lodash/run` runs the demo in node.

### node-express
This demo is a HTTP endpoint written in express, which runs on node. 
Adapted from [this](https://github.com/BrianDGLS/express-ts)

`sbt> node-express/run` will start it.

You'll need for instance `curl` to test it:
```bash
> curl http://localhost:3000/welcome
#Hello, World!

> curl http://localhost:3000/welcome/foo
# Hello, foo!
```

### typescript

`sbt> typescript/run` runs the typescript compiler on two files (one of which is meant to fail).
It accepts parameters to specify other files if you want to play around.

### cypress

`sbt> cypress/run` runs a basic test

## Your demo here! :)
Pull requests most welcome!
