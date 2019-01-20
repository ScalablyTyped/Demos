# Demos for ScalablyTyped

This is a collection of tiny demo projects to show off how we can use javascript libraries from scala.js

Some of the projects run in the browser, while some run on node.

### react-mobx

ScalablyTyped supports that users contribute their own "sugar" on top
 of the generated typings. These projects are called `contrib` projects, 
 and this project uses `react-contrib` to make it smoother to work with react.
 
#### Libraries used

- react and react-dom for rendering with react-contrib helpers
- csstypes for a fully typed definition of css
- MobX for state management
- Axios to fetch a json resource
- Material-ui (the old version, 0.20) for graphical components.

`sbt> react-mobx/start` starts a webpack-dev-server at http://localhost:8001 .

### react-slick
Integrates an image carousel library called react-slick with japgolly's
fantastic [scalajs-react wrapper](https://github.com/japgolly/scalajs-react)

`sbt> react-slick/start` starts a webpack-dev-server at http://localhost:8005 .

### d3 
It uses d3 to generate a rather fancy spinning globe. Demo is converted from [here](https://bl.ocks.org/animateddata/1f6522d3fcec29c01e7f4a5894e1fd94)

`sbt> d3/start` starts a webpack-dev-server at http://localhost:8002 .

### google-maps
The demo loads the google maps javascript as distributed by google (see [index.html](./google-maps/assets/index.html) ).
It's very simple usage, it just shows the location of a few beaches.

`sbt> google-maps/start` starts a webpack-dev-server at http://localhost:8004 .

### jquery
This demo shows how to interact with old-style javascript.
Jqueryui is a global library (as in, not a module), so you'll see the code touches an object to include it.
It also extends jquery with more functionality, so you'll see an explicit cast to tell the compiler about this. 
This is poor mans interface augmentation (a mechanism by which typescript does this automatically)

#### Libraries used

- jquery
- jqueryui

`sbt> jquery/start` starts a webpack-dev-server at http://localhost:8003 .

### Vue
This demo showcases a pretty simple todo app (stolen and adapted from [scalajs-vue](https://github.com/fancellu/scalajs-vue/)).
Some templating is done in [index.html](./vue/assets/index.html), while a bunch of stuff is done in Scala.

From its design it's pretty clear that Vue was designed by javascript people.
Trying to obtain type safety in this mess will probably never be worth it, 
but at least now you can try! :)
`sbt> vue/start` starts a webpack-dev-server at http://localhost:8006 .

### React big calendar
This uses react and react-big-calendar

`sbt> react-big-calendar/start` starts a webpack-dev-server at http://localhost:8007 .

### Semantic-ui-react and redux
Implements the same github repository search as in the react-mobx demo,
 just with redux and semantic-ui-react instead, along with a random sampling of
 graphical components.
 
`sbt> semantic-ui-react/start` starts a webpack-dev-server at http://localhost:8009 .
 

### Three.js
A fancy animation of a horse, stolen from [three.js demos](https://github.com/mrdoob/three.js/blob/master/examples/webgl_morphtargets_horse.html),
 with a change of loaders from [retyped demos](https://github.com/Retyped/Demos/tree/master/ThreeJsDemo)
`sbt> three/start` starts a webpack-dev-server at http://localhost:8008 .

### Reveal.js
Write your talks in scala.js! This uses highlight.js and reveal.js along with
 scalajs-react. adapted from [scala-reveal-js](https://github.com/pheymann/scala-reveal-js),
`sbt> reveal/start` starts a webpack-dev-server at http://localhost:8010 .

### Chart.js
Simple charting using canvas elements. Shows off how to work with the DOM as well
 as how to use chart.js. Heavily adapted from the [retyped demo](https://github.com/Retyped/Demos/tree/master/ChartJsDemo),
`sbt> chart/start` starts a webpack-dev-server at http://localhost:8011 .

### lodash
This is a very simple app which uses a few functions from lodash.
`sbt> lodash/run` runs the demo in node.

### node-express
This demo is a HTTP endpoint written in express, which runs on node. 

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

### Your demo here! :)
Pull requests most welcome!
