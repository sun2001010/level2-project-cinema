<template>
  <div class="login">
    <lay-form class="login1">
      <lay-form-item label="用户名">
        <lay-input v-model="userInfo.username" placeholder="请输入用户名"></lay-input>
      </lay-form-item>
      <lay-form-item label="密&ensp;&ensp;码">
        <lay-input v-model="userInfo.password" placeholder="请输入密码"></lay-input>
      </lay-form-item>
      <lay-form-item class="btns">
        <lay-button type="primary" @click="onLogin">登录</lay-button>
      </lay-form-item>
    </lay-form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import {login} from './api'
import {useRouter} from "vue-router"
import {layer} from "@layui/layui-vue";

const router = useRouter();
const userInfo = ref({
  username: '',
  password: ''
})
function onLogin(e){
  // 防止表单提交
  e.preventDefault()
  e.stopPropagation()
  login(userInfo.value).then(res => {
    router.push({name:'home'})
  }).catch(err => {
    console.error(err)
  })
}
</script>

<style scoped lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background: linear-gradient(#a770ef,#f6ceec,#d939cd);
  .login1 {
    border-radius: 5px;
    width: 400px;
    padding: 20px;
    padding-right: 80px;
    backdrop-filter: blur(15px);
    .btns{
      text-align: center;
    }
  }
}
</style>
