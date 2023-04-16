import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "./config/router.js";
import store from "./config/store.js";
import layui from '@layui/layui-vue'
import '@layui/layui-vue/lib/index.css'
import 'nprogress/nprogress.css'

const app = createApp(App);
// 注册路由
app.use(router);
// 注册状态管理
app.use(store);
// 注册layui
app.use(layui);
app.mount('#app')
