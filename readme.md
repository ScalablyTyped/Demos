# Demos for ScalablyTyped

This is a collection of tiny demo projects to show off how we can use javascript libraries from scala.js

Some of the projects run in the browser, while some run on node.

### react-mobx
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/react-mobx/)

ScalablyTyped supports that users contribute their own "sugar" on top
 of the generated typings. These projects are called `facade` projects, 
 and this project uses `react-facade` to make it smoother to work with react.
 
#### Libraries used

- react and react-dom for rendering with react-facade helpers
- csstypes for a fully typed definition of css
- MobX for state management
- Axios to fetch a json resource
- Material-ui (the old version, 0.20) for graphical components.

`sbt> react-mobx/start` starts a webpack-dev-server at http://localhost:8001 .

### react-slick
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/react-slick/)

Integrates an image carousel library called react-slick with japgolly's
fantastic [scalajs-react wrapper](https://github.com/japgolly/scalajs-react)

`sbt> react-slick/start` starts a webpack-dev-server at http://localhost:8005 .

### d3 
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/d3/)

It uses d3 to generate a rather fancy spinning globe. Demo is converted from [here](https://bl.ocks.org/animateddata/1f6522d3fcec29c01e7f4a5894e1fd94)

`sbt> d3/start` starts a webpack-dev-server at http://localhost:8002 .

### google-maps
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/google-maps/)

The demo loads the google maps javascript as distributed by google (see [index.html](./google-maps/assets/index.html) ).
It's very simple usage, it just shows the location of a few beaches.

`sbt> google-maps/start` starts a webpack-dev-server at http://localhost:8004 .

### jquery
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/jquery/)

This demo shows how to interact with old-style javascript.
Jqueryui is a global library (as in, not a module), so you'll see the code touches an object to include it.
It also extends jquery with more functionality, so you'll see an explicit cast to tell the compiler about this. 
This is poor mans interface augmentation (a mechanism by which typescript does this automatically)

#### Libraries used

- jquery
- jqueryui

`sbt> jquery/start` starts a webpack-dev-server at http://localhost:8003 .

### Vue
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/vue/)

This demo showcases a pretty simple todo app (stolen and adapted from [scalajs-vue](https://github.com/fancellu/scalajs-vue/)).
Some templating is done in [index.html](./vue/assets/index.html), while a bunch of stuff is done in Scala.

From its design it's pretty clear that Vue was designed by javascript people.
Trying to obtain type safety in this mess will probably never be worth it, 
but at least now you can try! :)
`sbt> vue/start` starts a webpack-dev-server at http://localhost:8006 .

### React big calendar
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/react-big-calendar/)

This uses react and react-big-calendar

`sbt> react-big-calendar/start` starts a webpack-dev-server at http://localhost:8007 .

### Semantic-ui-react and redux
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/semantic-ui-react/)

Implements the same github repository search as in the react-mobx demo,
 just with redux and semantic-ui-react instead, along with a random sampling of
 graphical components.
 
`sbt> semantic-ui-react/start` starts a webpack-dev-server at http://localhost:8009 .
 

### Three.js
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/three/)

A fancy animation of a horse, stolen from [three.js demos](https://github.com/mrdoob/three.js/blob/master/examples/webgl_morphtargets_horse.html),
 with a change of loaders from [retyped demos](https://github.com/Retyped/Demos/tree/master/ThreeJsDemo)
`sbt> three/start` starts a webpack-dev-server at http://localhost:8008 .

### Reveal.js
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/reveal/)

Write your talks in scala.js! This uses highlight.js and reveal.js along with
 scalajs-react. adapted from [scala-reveal-js](https://github.com/pheymann/scala-reveal-js),
`sbt> reveal/start` starts a webpack-dev-server at http://localhost:8010 .

### Chart.js
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/chart/)

Simple charting using canvas elements. Shows off how to work with the DOM as well
 as how to use chart.js. Heavily adapted from the [retyped demo](https://github.com/Retyped/Demos/tree/master/ChartJsDemo),
`sbt> chart/start` starts a webpack-dev-server at http://localhost:8011 .

### Angular 8 
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/angular/)

Let's be nice and say that Angular is a reasonable alternative for creating a frontend app.
If you agree, now is your chance to use it with Scala.js.

Adapted from [sherpal's prototype](https://github.com/sherpal/AngularScalaPOC).
`sbt> angular/start` starts a webpack-dev-server at http://localhost:8012 .

### Storybook
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/storybook-react/index.html)

Storybook is a pretty great prototyping and demo tool. It does a lot of webpack and babel
 itself, so the setup is a bit more hassle than other libraries. On the bright side
 it comes with hot module reloading out of the box, even with Scala.js
 
`sbt> storybook-react/run` starts a webpack-dev-server at http://localhost:8013 .

### P5
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/p5/index.html)

Demo adapted from [documentation](https://p5js.org/examples/instance-mode-instantiation.html)
`sbt> p5/start` starts a webpack-dev-server at http://localhost:8014 .

### Leaflet
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/leaflet/index.html)

Demo adapted from [scalajs-leaflet](https://github.com/fancellu/scalajs-leaflet/blob/master/example/src/main/scala/example/QuickStartLeaflet.scala)
`sbt> leaflet/start` starts a webpack-dev-server at http://localhost:8015 .
 
### Material-ui
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/material-ui/index.html)

`sbt> material-ui/start` starts a webpack-dev-server at http://localhost:8016 .
 
### Antd
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/antd/index.html)
Adapted from [mcallisto](https://github.com/mcallisto/slinky-antd.g8/blob/master/src/main/g8/src/main/scala/%24package%24/App.scala)
 
`sbt> antd/start` starts a webpack-dev-server at http://localhost:8017 .

### Antd (with Slinky)
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/antd-slinky/index.html)
A version of the `antd` demo made using slinky.
 
`sbt> antd-slinky/start` starts a webpack-dev-server at http://localhost:8018 .

### React-router-dom
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/react-router-dom/index.html)
Adapted from [mcallisto](https://gist.github.com/mcallisto/5a15d2f9567e084da055e14c8bb4b084)
 
`sbt> react-router-dom/start` starts a webpack-dev-server at http://localhost:8019 .

### React-router-dom (with Slinky)
[Demo](https://oyvindberg.github.io/ScalablyTypedDemos/react-router-dom-slinky/index.html)
A version of the `react-router-dom` demo made using slinky.
 
`sbt> react-router-dom-slinky/start` starts a webpack-dev-server at http://localhost:8020 .
 
### Electron
Implements the backend/mainprocess part of an Electron app in Scala.js,
 though it would be easy to do the frontend as well (in another module).

To start it you'll need electron installed globally:
```
yarn global add electron
```

and then start the project like this:

```
sbt>electron/run
``` 

Again adapted from [sherpal's work](https://github.com/sherpal/Scala.js-Electron-App-Example).

### React-native (android)

The setup is based on the [slinky react native expo template](https://github.com/shadaj/expo-template-scala).
The code heavily adapted from [sri](https://github.com/scalajs-react-interface/sri), namely the 
[App with drawer navigation](https://github.com/scalajs-react-interface/sri/blob/master/docs/GettingStarted.md#drawer-navigation) 
example.  

This example is a bit more involved to run. By following the instructions in the [readme](react-native/README.md), you should be able to get expo and an android/ios emulator running. 

Afterwards, start the demo like this:

```
sbt> ~react-native/fastOptJS

(in another shell)
shell>cd react-native
shell>expo start # (push 'a' do deploy to android, for instance)
``` 

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

### Your demo here! :)
Pull requests most welcome!
