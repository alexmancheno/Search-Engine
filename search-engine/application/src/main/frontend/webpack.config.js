const HtmlWebPackPlugin = require("html-webpack-plugin");
const webpack = require('webpack');
const dotenv = require('dotenv');
const fs = require('fs');
const path = require('path');

// Generates the output
const htmlPlugin = new HtmlWebPackPlugin({
    template: "./src/index.html",
    filename: "index.html"
});

module.exports = env => {
    let environmentKeys = {
        'process.env.API_URL': JSON.stringify(env.API_URL),
    }
    return {
        plugins: [htmlPlugin, new webpack.DefinePlugin(environmentKeys)],
        module: {
            rules: [
                {
                    test: /\.js$/,
                    exclude: /node_modules/,
                    use: {
                        loader: "babel-loader"
                    }
                },
                {
                    test: /\.css$/,
                    use: [
                        {
                            loader: "style-loader"
                        },
                        {
                            loader: "css-loader",
                            options: {
                                modules: true,
                                importLoaders: 1,
                                localIdentName: "[name]_[local]_[hash:base64]",
                                sourceMap: true,
                                minimize: true
                            }
                        }
                    ]
                },
                {
                    test: /\.svg$/,
                    use: [
                        {
                            loader: "babel-loader"
                        },
                        {
                            loader: "react-svg-loader",
                            options: {
                                jsx: true // true outputs JSX tags
                            }
                        }
                    ]
                }
            ]
        }
    }
};