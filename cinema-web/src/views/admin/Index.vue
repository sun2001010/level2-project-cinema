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
        <lay-menu :selected-key="selectedKey" :open-keys="openKeys">
          <lay-sub-menu id="system-manager">
            <template #title>
              系统管理
            </template>
            <router-link to="/filmsManage">
              <lay-menu-item>影片管理</lay-menu-item>
            </router-link>
            <router-link to="/plating">
              <lay-menu-item>排片</lay-menu-item>
            </router-link>
            <router-link to="/rowSeats">
              <lay-menu-item>排座</lay-menu-item>
            </router-link>
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
import {reactive, ref} from "vue";
import {list} from "./api.js";

const selectedKey = ref('cinema-manage')
const openKeys = ref(['system-manage'])
//定义表格的数据
const data = reactive([])
//定义表格的列
const columns = reactive([
  {
    title: '影片id',
    key: 'fId',
    width:80,
  },
  {
    title: '影片名',
    key: 'fName',
  },
  {
    title: '影片图片',
    key: 'fImage',
  },
  {
    title: '影片类型',
    key: 'oType',
  },
  {
    title: '导演',
    key: 'director',
  },
  {
    title: '导演图片',
    key: 'directorImage',
  },
  {
    title: '影片简介',
    key: 'fContent',
  },
  {
    title: '上映时间',
    key: 'fTime',
  },
  {
    title: '基础影片票价',
    key: 'fPrice',
  },
  {
    title: '操作',
    customSlot:'action',
  }
])
function reLode(){
  list().then(res=>{
    data.length = 0
    data.push(...res.data)
  })
}
// function onChangeSelected(key){
//   selectedKey.value = key;
// }
//
//
// const select = ref(0);

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