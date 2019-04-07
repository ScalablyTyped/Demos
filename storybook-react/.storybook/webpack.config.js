/**
 * I have no idea.
 * Stolen from https://github.com/psychobolt/react-rollup-boilerplate/blob/d9cb9179cb7c00baab486646c504110bf7e2f50a/.storybook/webpack.config.js#L11
 */

module.exports = ({config}) => ({
    ...config,
    module: {
        ...config.module,
        rules: [
            // Temp fix for issue: https://github.com/storybooks/storybook/issues/3346
            ...config.module.rules.filter(rule => !(
                (rule.use && rule.use.length && rule.use.find(({loader}) => loader === 'babel-loader'))
            )),
            {
                test: /\.jsx?$/,
                include: require('path').resolve('./'),
                exclude: /(node_modules|dist)/,
                loader: 'babel-loader',
            },
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                loader: 'source-map-loader',
                enforce: 'pre',
            },
        ]
    }
});
