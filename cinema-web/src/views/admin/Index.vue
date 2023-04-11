<template>
  <lay-layout class="example">
    <lay-header>
      <div class="header">
        <div class="title">猫耳电影</div>
        <lay-dropdown updateAtScroll>
          <lay-button type="primary">
            <div class="name">Admin</div>
            <div class="avatar"></div>
          </lay-button>
          <template #content>
            <lay-dropdown-menu>
              <lay-dropdown-menu-item>个人中心</lay-dropdown-menu-item>
              <lay-dropdown-menu-item>退出登录</lay-dropdown-menu-item>
            </lay-dropdown-menu>
          </template>
        </lay-dropdown>
      </div>
    </lay-header>
    <lay-layout>
      <lay-side>
        <lay-menu :selected-key="selectedKey" @change-selected-key="onChangeSelected" v-model:openKeys="openKeys" :tree="true">
          <lay-sub-menu id="system-manager">
            <template #title>
              系统管理
            </template>
            <lay-menu-item id="cinema-manage" to="/userInfo">影片管理</lay-menu-item>
            <lay-menu-item id="plating">排片</lay-menu-item>
            <lay-menu-item id="row-seats">排座</lay-menu-item>
          </lay-sub-menu>
        </lay-menu>
      </lay-side>
      <lay-body>
        <router-view/>
      </lay-body>
    </lay-layout>
  </lay-layout>
</template>

<script setup>
import {ref} from "vue";
const selectedKey = ref('cinema-manage')
const openKeys = ref(['system-manage'])
function onChangeSelected(key){
  selectedKey.value = key;
}


const select = ref(0);

</script>

<style scoped lang="scss">
.layui-layout {
  height: 100vh;
  .layui-header{
    background: #40454d;
    color: #fff;
    .title{
      font-size: 30px;
    }
    .header {
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      .layui-btn{
        position: absolute;
        right: 0;
      }
    }
  }
  .layui-side {
    background: #2F4056;
    color: #fff;
  }
  .layui-body{
    padding: 15px;
  }
}
</style>