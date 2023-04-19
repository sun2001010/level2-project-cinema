import http from "../../../config/http.js";
import useLogin from "../../../stores/LoginStore.js";

export function userLogin(userInfo){
    return http.post("/users/login",userInfo)
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