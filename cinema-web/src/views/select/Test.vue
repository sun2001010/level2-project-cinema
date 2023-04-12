<template>
  <div class="info">
    <button class="layui-btn layui-btn-normal" @click="getInfo">购票</button>
  </div>
</template>

<script setup>

import Index from "./Index.vue";
import {ref, reactive} from 'vue'
import {getSeatInfo} from "./api.js";
import router from "../../config/router.js";
import {layer} from "@layui/layui-vue";


// 模拟已占用的座位
const occupiedSeats = ref()
const selectInfo = reactive([
  {
  sId: '',
    sRow: '',
    sCol: '',
    sStatus: '',
    hID: ''
  }
])
function getInfo() {
  getSeatInfo(1).then((res)=>{
    selectInfo.splice(0,1)
    for (let r of res.data) {
      if (selectInfo.length<res.data.length) {
        selectInfo.push(r)
      }
    }
    sessionStorage.setItem("selectInfo", JSON.stringify(selectInfo));
    router.push('/select')
  }).catch(error=>{
    layer.msg("查询错误")
  })
}
</script>

<style scoped lang="scss">
.info {
  text-align: center;
  margin-top: 20px;
}
</style>