<template>
  <div class="login">
    <h1>Login</h1>
    <form @submit="onLogin">
      <label for="username">用户名</label>
      <input type="text" id="username" v-model="userInfo.username" placeholder="请输入用户名"/>
      <br>
      <label for="password">密码</label>
      <input type="password" id="password" v-model="userInfo.password" placeholder="请输入密码"/>
      <br>
      <button type="submit">登录</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import {login} from './api'
import {useRouter} from "vue-router";
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

<style scoped>

</style>
