<template>
  <div class="content">
    <div class="cinema">
      <CinemaName/>
    </div>
    <div class="cinema">
      <CinemaAddress/>
    </div>
    <div class="cinema">
      <Hall/>
    </div>
    <div class="contain">
      <lay-button type="normal">影院列表</lay-button>
      <lay-table :columns="columns" :data-source="dataSource">
      </lay-table>
    </div>
  </div>
</template>

<script setup>
import CinemaName from "./cinemaName/index.vue";
import CinemaAddress from "./cinemaAddress/index.vue"
import Hall from "./hall/index.vue"
import {onMounted, reactive, ref} from "vue";
import {findAll} from "./api.js"
import {layer} from "@layui/layui-vue";

const columns=[
  {title: 'ID',key:'cId'},
  {title: '影院名',key: 'cName'},
  {title: '影院等级',key: 'cLevel'},
  {title: '影院地址',key: 'cAddress'},
  {title: '操作',customSlot: 'action'}
]
const dataSource=reactive([])
const cinemaName=ref([])
onMounted(()=>{
  findAll().then(res=>{
    dataSource.push(...res.data.list)
    for (let i=0;i<res.data.list.length;i++){
      cinemaName[i]=res.data.list[i].cName
    }
  }).catch(error=>{
    layer.msg("错误")
  })
  console.log(cinemaName)
})
</script>

<style scoped lang="scss">
.content{
  margin: 10px;
  border: #cccccc solid 1px;
  height: 330px;
  .cinema{
    margin: 10px;
  }
  .contain{
    margin: 20px;
  }
}
</style>