const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    port:3919,
    proxy: {
      '/serve8080': {
        target: 'http://localhost:8080',
				pathRewrite:{'^/serve8080':''},
        // ws: true, //用于支持websocket
        // changeOrigin: true //用于控制请求头中的host值
      },
    }
  },
})
  