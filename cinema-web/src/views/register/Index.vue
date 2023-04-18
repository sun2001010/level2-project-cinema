<template>
  <div class="register">
    <lay-row space="10">
      <lay-col md="10" md-offset="7" lg="6" lg-offset="9" sm="12" sm-offset="6" xs="24" class="login-form">
        <lay-row space="10">
          <lay-col span="24" class="title">
            <h1>注册</h1>
          </lay-col>
        </lay-row>
        <lay-row space="10">
          <lay-col span="24">
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                用&ensp;户&ensp;名
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.uName" placeholder="请输入用户名"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                密&emsp;&emsp;码
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.uPassword" type="password" placeholder="请输入密码"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                确认密码
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.rePassword" type="password" placeholder="请再次输入密码"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                密&emsp;&emsp;保
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.uConfidentiality" placeholder="请输入密保"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                电&emsp;&emsp;话
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.uPhone" placeholder="请输入电话号码"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="lable" lg="6" md="8" sm="24" xs="24">
                邮&emsp;&emsp;箱
              </lay-col>
              <lay-col lg="18" md="16" sm="24" xs="24">
                <lay-input v-model="userInfo.uEmail" placeholder="请输入邮箱地址"/>
              </lay-col>
            </lay-row>
            <lay-row space="10" class="form-item">
              <lay-col class="btns" lg="12" md="12" sm="24" xs="24">
                  <lay-button border="blue" fluid @click="back">返回</lay-button>
              </lay-col>
              <lay-col class="btns" lg="12" md="12" sm="24" xs="24">
                <lay-button type="normal" fluid @click="onRegister">注册</lay-button>
              </lay-col>
            </lay-row>
          </lay-col>
        </lay-row>
      </lay-col>
    </lay-row>
  </div>
</template>

<script setup>
import {register} from "./api.js";
import {useRoute} from "vue-router";
import {ref} from "vue";
import router from "../../config/router.js";
import {layer} from "@layui/layui-vue";
const userInfo = ref({
  uName: '',
  uPassword: '',
  rePassword: '',
  uConfidentiality: '',
  uPhone: '',
  uEmail:''
})
function back(){
  router.push({name:'login'})
}
function onRegister(e){
  e.preventDefault()
  e.stopPropagation()
  register(userInfo.value).then(res=>{
    layer.msg("注册成功")
    router.push({name:'login'})
  }).catch(err=>{
    layer.msg("error")
  })
}
</script>

<style scoped lang="scss">
.register {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  background-image: url("../login/image/01.jpg");
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
  }
  line-height: 50px;
  }
}
</style>