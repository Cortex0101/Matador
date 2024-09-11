'use strict'

const path = require('path');
const autoprefixer = require('autoprefixer');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  //mode: 'development',
  //devtool: 'source-map',
  mode: 'production',
  entry: {
    settings: './wordle2/js/settings.js', // 'settings' is the name of the chunk
    stats: './wordle2/js/woordleStats.js', // 'stats' is the name of the chunk
    comp: './wordle2/js/comp.js', // 'comp' is the name of the chunk
    board: './wordle2/js/keyboard.js', // 'board' is the name of the chunk
    keyboard: './wordle2/js/keyboard.js', // 'keyboard' is the name of the chunk
    bootstrap: './wordle2/js/bootstrap.js', // 'bootstrap' is the name of the chunk
    main: './wordle2/js/woordle.js'
  },
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, 'wordle2-dist'),
  },
  devServer: {
    static: path.resolve(__dirname, 'wordle2-dist'),
    port: 8080,
    hot: true,
    client: {
      overlay: false,
    },
  },
  plugins: [
    new CleanWebpackPlugin(),
    new MiniCssExtractPlugin({
      filename: '[name].css',
    }),
    new HtmlWebpackPlugin({
      filename: 'wordle2.html', // Custom HTML file name
      template: './wordle/wordle2.html',
    }),
  ],
  module: {
    rules: [
      {
        test: /\.(scss)$/,
        use: [
          MiniCssExtractPlugin.loader,
          'css-loader',
          {
            loader: 'postcss-loader',
            options: {
              postcssOptions: {
                plugins: [
                  autoprefixer
                ]
              }
            }
          },
          'sass-loader'
        ]
      }
    ]
  },
  optimization: {
    splitChunks: {
      chunks: 'all',
      cacheGroups: {
        common: {
          name: 'common', // Customize the name for common modules
          minChunks: 2,
          priority: 10,
        },
      },
    },
    minimize: true,
  },
};