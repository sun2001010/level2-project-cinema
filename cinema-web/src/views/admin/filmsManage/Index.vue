<template>
  <div class="container">
    <lay-row>
      <lay-form>
        <lay-form-item lable="影片id" >
          <lay-input placeholder="请输入影片id"></lay-input>
        </lay-form-item>
        <lay-form-item>
          <lay-button type="normal">
            <lay-icon type="layui-icon-search"></lay-icon>
          </lay-button>
        </lay-form-item>
      </lay-form>
    </lay-row>
    <lay-row space="10">
      <lay-col>
        <lay-table :columns="columns" :data-source="data"></lay-table>

        <template #action>
          <lay-button type="normal" size="sm">
            <lay-icon type="layui-icon-edit" size="18px"></lay-icon>
          </lay-button>
          <lay-button type="danger" size="sm">
            <lay-icon type="layui-icon-delete" size="18px"></lay-icon>
          </lay-button>
        </template>

      </lay-col>
    </lay-row>
  </div>
</template>

<script setup>
import {list} from "./api.js";
import {onMounted, onUpdated, reactive} from "vue";
//定义表格的数据
const data = reactive([])
//定义表格的列
const columns = reactive([
  {
    title: '影片id',
    key: 'fId',
    width:80,
    align:'center',
  },
  {
    title: '影片名',
    key: 'fName',
    align:'center',
  },
  {
    title: '影片图片',
    key: 'fImage',
    align:'center',
  },
  {
    title: '影片类型',
    key: 'oType',
    align:'center',
  },
  {
    title: '导演',
    key: 'director',
    align:'center',
  },
  {
    title: '导演图片',
    key: 'directorImage',
    align:'center',
  },
  {
    title: '影片简介',
    key: 'fContent',
    align:'center',
  },
  {
    title: '上映时间',
    key: 'fTime',
    align:'center',
  },
  {
    title: '基础影片票价',
    key: 'fPrice',
    align:'center',
  },
  {
    title: '操作',
    customSlot:'action',
    align:'center',
  }
])
function reLode(){
  list().then(res=>{
    data.length = 0
    data.push(...res.data.list)
  })
}
onMounted(reLode)
onUpdated(reLode)


</script>

<style scoped lang="scss">

</style>