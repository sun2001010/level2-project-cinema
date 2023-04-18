import http from "../../config/http.js";
import useLogin from "../../stores/LoginStore.js";

export function register(userInfo){
    return http.put("/users/register",userInfo)
        .then(res=>{
            if(res.code === 200){
                const loginInfo = useLogin();
                // 保存token
                loginInfo.setToken(res.token);
                // 保存用户信息
                loginInfo.setUserInfo(res.userInfo);
            }
            return res;
        })
}