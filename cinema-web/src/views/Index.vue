<template>
  <lay-header>
  <lay-menu v-model:selected-key="selectedKey" v-model:open-keys="openKeys">
    <lay-menu-item>
      <router-link to="/home">
        首页
      </router-link>
    </lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item>
      <router-link to="/film">
        电影
      </router-link>
    </lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item>
      <router-link to="/cinema">
        影院
      </router-link>
    </lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item>排行</lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item></lay-menu-item>
    <lay-menu-item>
      <lay-input placeholder="搜索影片、影院"></lay-input>
    </lay-menu-item>
    <lay-menu-item>
      <lay-button type="primary">
        <lay-icon type="layui-icon-search" size="18px"></lay-icon>
      </lay-button>
    </lay-menu-item>
    <lay-sub-menu>
      <template #title>
        个人中心
      </template>
      <lay-menu-item>个人信息</lay-menu-item>
      <lay-menu-item>注销登录</lay-menu-item>
    </lay-sub-menu>
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

<style scoped>

</style>
