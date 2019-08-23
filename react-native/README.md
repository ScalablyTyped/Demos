# Scala.js + Slinky Expo Template
This template sets up a simple React Native application written in Scala through [Scala.js](https://www.scala-js.org), [Slinky](https://slinky.dev), and [Expo](https://expo.io).

## Installation
Install the Expo CLI:
```
$ npm install -g expo-cli
```

Use the template
```
$ expo init --template expo-template-scala
```

## Building Apps
Once you've created your project, first compile your Scala code to JavaScript by running
```
$ sbt fastOptJS
```

Then, launch the app with Expo
```
$ expo start
```

## Production Bundles
To create a minified app, first build an optimized Scala.js bundle
```
$ sbt fullOptJS
```

Then, go to `App.js` and uncomment `module.exports = require("./target/scala-2.12/app-opt.js").app;` in order to enable the production bundle. Finally, switch Expo to production mode to load your production app.
