var merge = require('webpack-merge');
var generated = require('./scalajs.webpack.config');

var local = {
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(ttf|eot|woff|png|glb)$/,
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
