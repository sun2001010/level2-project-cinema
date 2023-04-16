import axios from "axios";
import useLogin from "../stores/LoginStore"
import NProgress from "nprogress";
import router from "./router.js";
const http = axios.create({
    baseURL: 'http://localhost/api',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    }
})

http.interceptors.request.use(config=>{
    NProgress.start()
    // 添加token
    const loginInfo  = useLogin();
    if(loginInfo.isLogin()){
        // 将token添加到请求头中
        config.headers['hm-token'] = loginInfo.token;
    }
    return config;
},error=>{
    if (error.response?.status===400){
        if (error.response.data.code===401){
            router.push({name:'login'})
        }
    }
    return Promise.reject(error)
})

http.interceptors.response.use(response=>{
    return new Promise((resolve,reject)=>{
        NProgress.done()
        if(response.data.code === 200){
            resolve(response.data)
        }else{
            reject(response.data)
        }
    })
})

export default http
export const put=http.put
export const post=http.post
export const get=http.get
export const del=http.delete