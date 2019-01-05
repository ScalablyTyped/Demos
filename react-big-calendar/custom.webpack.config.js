var merge = require('webpack-merge');
var generated = require('./scalajs.webpack.config');

var local = {
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
        ]
    }
};

module.exports = merge(generated, local);