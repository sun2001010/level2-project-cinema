import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 80,
    open: true,
    // 代理,用来解决跨域问题
    proxy: {
      '/api': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        // 将路径中的/api重写为空 : /api/admin/login => /admin/login
        rewrite: (path) => path.replace(/^\/api/, '')
      },
      '/baiduapi':{
        target: 'https://api.map.baidu.com/geocoding/v3',
        changeOrigin:true,
        rewrite: (path) => path.replace(/^\/baiduapi/, '')
      },
      '/local':{
        target: 'https://api.map.baidu.com/location',
        changeOrigin:true,
        rewrite: (path) => path.replace(/^\/local/, '')
      }
    }
  }
})
