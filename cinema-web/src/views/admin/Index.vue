<template>
  <lay-layout class="example">
    <lay-header>
      <div class="header">
        <div class="title">猫耳电影</div>
        <lay-dropdown updateAtScroll>
          <lay-button type="primary">
            <router-link to="/login">
            <div class="name">退出登录</div>
            </router-link>
            <div class="avatar"></div>
          </lay-button>
        </lay-dropdown>
      </div>
    </lay-header>
    <lay-layout>
      <lay-side>
        <lay-menu :selected-key="selectedKey" @change-selected-Key="changeSelectedKey" v-model:open-keys="openKeys" @change-open-keys="changeOpenKeys" :tree="true">
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
const changeSelectedKey = (val) => {
  selectedKey.value = val;
}
const changeOpenKeys = (val) => {
  openKeys2.value = val;
}
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