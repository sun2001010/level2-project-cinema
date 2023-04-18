<template>
  <div class="contain">
    <lay-row space="10">
      <lay-col>
        <lay-table :columns="columns" :data-source="data">
          <template #action="{row}">
            <lay-tooltip content="修改">
              <lay-button type="normal" size="sm" @click="sessionUpdateVisible =true,onUpdate1(row)">
                <lay-icon type="layui-icon-edit" size="18px"></lay-icon>
              </lay-button>
            </lay-tooltip>
            <lay-tooltip content="删除">
              <lay-button type="danger" size="sm" @click="onDelete(row)">
                <lay-icon type="layui-icon-delete" size="18px"></lay-icon>
              </lay-button>
            </lay-tooltip>
          </template>
        </lay-table>
      </lay-col>
    </lay-row>
    <lay-layer v-model="sessionUpdateVisible" :area="['450px','450px']">
      <lay-form>
        <lay-form-item label="场&ensp;次&ensp;ID:">
          <lay-input v-model="seId" :disabled="true"></lay-input>
        </lay-form-item>
        <lay-form-item label="影&emsp;&emsp;片:">
          <lay-input v-model="fName"></lay-input>
        </lay-form-item>
        <lay-form-item label="放映时间:">
          <lay-date-picker type="datetime" v-model="seStarttime"></lay-date-picker>
        </lay-form-item>
        <lay-form-item label="结束时间:">
          <lay-input v-model="seEndtime"></lay-input>
        </lay-form-item>
        <lay-form-item label="影&emsp;&emsp;院:">
          <lay-input v-model="cName"></lay-input>
        </lay-form-item>
        <lay-form-item label="影&emsp;&emsp;厅:">
          <lay-input v-model="chName"></lay-input>
        </lay-form-item>
        <lay-form-item class="btns">
          <lay-button @click="sessionUpdateVisible = false">取消</lay-button>
          <lay-button type="normal" @click="onUpdate">确认</lay-button>
        </lay-form-item>
      </lay-form>
    </lay-layer>
  </div>
</template>

<script setup>
import {onMounted, onUpdated, reactive, ref} from "vue";
import {list, deleted, update} from "./api.js";
import {layer} from "@layui/layui-vue";
let seId
let fName
let seStarttime
let seEndtime
let cName
let chName
const data = reactive([])
const columns = reactive([
  {
  title: "场次ID",
  key: "seId",
  align: "center",
  },
  {
    title: "影片",
    key: "fName",
    align: "center"
  },
  {
    title: "放映时间",
    key: "seStarttime",
    align: "center"
  },
  {
    title: "结束时间",
    key: "seEndtime",
    align: "center"
  },
  {
    title: "影院",
    key: "cName",
    align: "center"
  },
  {
    title: "影厅",
    key: "chName",
    align: "center"
  },
  {
    title: "操作",
    customSlot:'action',
    align:'center',
  }
])
const sessionUpdateVisible = ref(false);
const sessionUpdate = reactive({
  seId:'',
  fName: '',
  seStarttime: '',
  seEndtime: '',
  cName: '',
  chName: '',
})
function onUpdate1(row) {
  seId = row.seId
  fName = row.fName
  seStarttime = row.seStarttime
  seEndtime = row.seEndtime
  cName = row.cName
  chName = row.chName
}
function onUpdate(){
  sessionUpdate.seId = seId
  sessionUpdate.fName = fName
  sessionUpdate.seStarttime = seStarttime
  sessionUpdate.seEndtime = seEndtime
  sessionUpdate.cName = cName
  sessionUpdate.chName = chName
  update(sessionUpdate).then(res=>{
    console.log(res)
  }).catch(err=>{
    layer.msg("error")
  })
  sessionUpdateVisible.value = false
  reLode()
}
function reLode(){
  list().then(res=>{
    data.length = 0;
    data.push(...res.data.list);
  })
}
function onDelete(session){
  layer.confirm(`是否删除${session.fName}?`,{
    btn:[
      {text:"否",callback(id){
          layer.close(id)
        }},
      {text:"是",
        callback(id){
          deleted(session).then(res=>{
            layer.msg('删除成功')
            layer.close(id)
          }).catch(err=>{
            layer.msg("del err")
          })
          reLode()
        }
      }]
  });
}
onMounted(reLode)
onUpdated(reLode)
</script>

<style scoped lang="scss">
.btns{
  text-align: center;
}
</style>