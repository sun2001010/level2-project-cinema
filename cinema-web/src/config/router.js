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
        meta: {noLogin: true},
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
            },
        ]
    },
    {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/admin/Index.vue'),
        children: [
            {
                path: '/filmsManage',
                name: 'filmsManage',
                component: () => import('../views/admin/filmsManage/Index.vue')
            },
            {
                path: '/plating',
                name: 'plating',
                component: () => import('../views/admin/plating/Index.vue')
            },
            {
                path: '/rowSeats',
                name: 'rowSeats',
                component: () => import('../views/admin/rowSeats/Index.vue')
            },
            {
                path: '/userInfo',
                name: 'userInfo',
                component: () => import('../views/admin/UserInfo.vue')
            },
            {
                path: '/order',
                name: 'order',
                component: () => import('../views/Order/Order.vue')
            },
        ]
    },
    {
        path: '/select',
        name: 'select',
        component: () => import('../views/select/Index.vue')
    },
    {
        path: '/test',
        name: 'test',
        component: () => import('../views/select/Test.vue')
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

// 导航守卫
router.beforeEach((to, from) => {
    // 如果是目标路由不需要登录,则直接跳转
    NProgress.start()
    if(to.meta.noLogin){
        return true;
    }
    const loginInfo  = useLogin();
    if(loginInfo.isLogin()){
        return true;
    }else{
        // 如果没有登录,则跳转到登录页面
        return {name: "userLogin"};
    }
})
router.afterEach((to, from) => {
    NProgress.done()
})

export default router