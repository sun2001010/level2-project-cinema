import {createRouter,createWebHashHistory} from "vue-router";
import useLogin from "../stores/LoginStore"
import NProgress from 'nprogress'


const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/login/Index.vue'),
        meta: {
            // 标记该路由不需要登录
            noLogin: true
        }
    },
    {
        path: '/',
        name: 'main',
        component: () => import('../views/Index.vue'),
        children: [
            {
                path: '/home',
                name: 'home',
                component: () => import('../views/home/Index.vue')
            },
            {
                path: '/film',
                name: 'film',
                component: () => import('../views/film/Index.vue')
            },
            {
                path: '/filmInformation',
                name: 'filmInformation',
                component: () => import('../views/film/filmInformation/Index.vue')
            },
            {
                path: '/cinema',
                name: 'cinema',
                component: () => import('../views/cinema/Index.vue')
            },
            {
                path: '/rank',
                name: 'rank',
                component: () => import('../views/rank/Index.vue')
            }
        ]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

// 导航守卫
// router.beforeEach((to, from) => {
//     // 如果是目标路由不需要登录,则直接跳转
//     NProgress.start()
//     if(to.meta.noLogin){
//         return true;
//     }
//     const loginInfo  = useLogin();
//     if(loginInfo.isLogin()){
//         return true;
//     }else{
//         // 如果没有登录,则跳转到登录页面
//         return {name: "login"};
//     }
// })
router.afterEach((to, from) => {
    NProgress.done()
})

export default router