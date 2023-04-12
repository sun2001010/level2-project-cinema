import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 80,
    open: true,
    // 代理,用来解决跨域问题
    proxy: {
      '/api': {
        target: 'http://localhost:81',
        changeOrigin: true,
        // 将路径中的/api重写为空 : /api/admin/login => /admin/login
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
