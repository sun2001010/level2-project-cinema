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

])
let buyInfo = reactive(
    {
      uId:1,
      fName:'复仇者联盟1',
      oType:'科幻',
      startTime:'2023-04-13 10:26:13',
      endTime:'2023-04-13 10:26:13',
      oPrice:123.0,
      chName:'IMAX4号厅',
      cName:'长沙万达',
      cAddress:'雨花区',
      hId:1,
    }

)
function getInfo() {
  getSeatInfo(buyInfo.hId).then((res)=>{
    for (let r of res.data) {
      if (selectInfo.length<res.data.length) {
        selectInfo.push(r)
      }
    }
    sessionStorage.setItem("selectInfo", JSON.stringify(selectInfo));
    sessionStorage.setItem("buyInfo", JSON.stringify(buyInfo));
    router.push('/select')
  }).catch(error=>{
    if (error.code===401){
      layer.msg("请先登录")
    }

  })
}
</script>

<style scoped lang="scss">
.info {
  text-align: center;
  margin-top: 20px;

}
</style>