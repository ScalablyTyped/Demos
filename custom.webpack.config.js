var merge = require('webpack-merge');
var generated = require('./scalajs.webpack.config');
var path = require('path');

var local = {
    resolve: {
        alias: {
            /* this is resolved from <project>/target/scala-2.12/scalajs-bundler/main */
            assets: path.resolve(__dirname, '../../../../assets/'),
        }
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(ttf|eot|woff|png|glb|jpeg|jpg|mp4)$/,
                use: 'file-loader'
            },
            {
                test: /\.(eot)$/,
                use: 'url-loader'
            }
        ]
    }
};

module.exports = merge(generated, local);
