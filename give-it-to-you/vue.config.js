const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: true,
  devServer: {
    port:3919,
    proxy: {
      '/serve8080': {
        target: 'http://localhost:8080',
				pathRewrite:{'^/serve8080':''},
      },
    }
  },
})
  