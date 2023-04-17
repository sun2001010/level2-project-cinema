<template>
  <lay-header>
  <lay-menu :selected-key="selectedKey" :open-keys="openKeys">
    <router-link to="/home">
      <lay-menu-item>首页</lay-menu-item>
    </router-link>
    <router-link to="/film">
      <lay-menu-item>电影</lay-menu-item>
    </router-link>
    <router-link to="/cinema">
      <lay-menu-item>影院</lay-menu-item>
    </router-link>
    <router-link to="/rank">
      <lay-menu-item>排行</lay-menu-item>
    </router-link>
    <lay-menu-item class="input">
      <lay-input placeholder="搜索影片、影院"></lay-input>
    </lay-menu-item>
    <lay-menu-item class="btn">
      <lay-button type="primary">
        <lay-icon type="layui-icon-search" size="18px"></lay-icon>
      </lay-button>
    </lay-menu-item>
    <router-link to="/login">
      <lay-menu-item>登录</lay-menu-item>
    </router-link>
  </lay-menu>
  </lay-header>
  <lay-body>
    <router-view></router-view>
  </lay-body>
</template>

<script setup>
import {ref} from "vue";

const selectedKey = ref("5")
const openKeys = ref(["7"])
</script>

<style scoped lang="scss">
.layui-body{
  padding: 10px;
}
.layui-nav-item{
  padding-left: 40px;
  padding-right: 40px;
}
.input{
  padding-right: 0;
}
.btn{
  padding-left: 0;
}
</style>
