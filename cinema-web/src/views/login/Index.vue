<template>
  <div class="login">
    <lay-row space="10">
      <lay-col md="10" md-offset="7" lg="6" lg-offset="9" sm="12" sm-offset="6" xs="24" class="login-form">
        <lay-row space="10">
          <lay-col span="24" class="title">
            <h1>登录</h1>
          </lay-col>
        </lay-row>
        <lay-row space="10">
          <lay-col span="24">
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                管理员
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.aName" placeholder="请输入管理员账号"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                密&emsp;码
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.aPassword" type="password" placeholder="请输入密码"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="btns" lg="24" md="24" sm="24" xs="24">
                <lay-button type="normal" fluid @click="onLogin">登录</lay-button>
              </lay-col>
              <lay-button-container class="bts">
                <lay-button prefix-icon="layui-icon-left" suffix-icon="layui-icon-right" size="xs" @click="handOff">切换</lay-button>
              </lay-button-container>
            </lay-row>
          </lay-col>
        </lay-row>
      </lay-col>
    </lay-row>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import {login} from './api'
import {useRouter} from "vue-router"
import {layer} from "@layui/layui-vue";

const router = useRouter();
const userInfo = ref({
  aName: '',
  aPassword: ''
})
function onLogin(e){
  // 防止表单提交
  e.preventDefault()
  e.stopPropagation()
  login(userInfo.value).then(res => {
    router.push({name:'admin'})
  }).catch(err => {
    layer.msg(typeof err =='string'? err : err.msg)
  })
}
function onRegister(e){
  e.preventDefault()
  e.stopPropagation()
  router.push({name:'register'})
}
function handOff(e){
  e.preventDefault()
  e.stopPropagation()
  router.push({name:'userLogin'})
}
</script>

<style scoped lang="scss">
.login {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  background-image: url("image/01.jpg");
  background-size: cover;
  &>.layui-row{
    flex: 1 1 auto;
  }
  .login-form {
    background-color: rgba(255, 255, 255, 0.3);
    backdrop-filter: blur(1px);
    padding: 20px;
    border-radius: 5px;
    .title{
      text-align: center;
      h1{
        font-size: 2em;
      }
    }
    .form-item{
      .lable {
        font-size: 1.2em;
      }
      .btns{
        text-align: center;
      }
      .bts{
        text-align: center;
        bottom: 0;
      }
      line-height: 50px;
    }
  }
}
</style>
