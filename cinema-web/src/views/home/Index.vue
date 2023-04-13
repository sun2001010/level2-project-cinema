
<template>
  <div class="initial">
 <div class="first-layer">
   <div class="second-left">
   <div class="second-top">
     <div>
    <span style="font-size: 20px;color: red;padding-left: 20px">正在热映</span>
     <div class="icon1" >
       <div  class="hit-film" v-for="PageName in TRR" >
       <img style="width: 100%;height: 160px" :src=PageName>
       <button style="width: 100%" >购票</button>
       </div>
    </div>

   </div>
   <div class="second-bottom">
   <span style="font-size: 20px;color: red;padding-left: 20px;padding-top: 30px">即将上映</span>
     <div class="icon1" >
       <div  class="hit-film" v-for="PageName in TRR" >
         <img style="width: 100%;height: 160px" :src=PageName>
         <button style="width: 100%" >购票</button>
       </div>
     </div>
   </div>
   </div>
   <div class="second-right">

   </div>
<!--   &lt;!&ndash;数据显示的表格&ndash;&gt;-->
<!--   <lay-table :columns="columns" :data-source="dataSource">-->

<!--   </lay-table>-->
 </div>
    </div>
  </div>




</template>

<script setup>
import {findAll} from "./page.js";
import {onMounted, reactive} from "vue";
import {layer} from "@layui/layui-vue";
function jump(){
  sessionStorage.removeItem('FilmName')
  sessionStorage.setItem('FilmName',TRR[0])
}


const columns=[
  {title:'ID',key:'fId'},
  {title: '电影名',key: 'fName'},
  {title: '电影图片',key: 'fImage'},
  {title: '电影类型',key: 'oType'},
  {title: '作者',key: 'director'},
  {title: '作者图片',key: 'directorImage'},
  {title: '电影介绍',key: 'fContent'},
  {title: '上映时间',key: 'fTime'},
  {title: '价格',key: 'fPrice'}

]
let TRR=reactive([]);
const dataSource=reactive([])

onMounted(()=>{
  findAll().then(res=>{
    dataSource.push(...res.data.list)
    for (let i = 0; i < res.data.list.length; i++) {
       // 获取电影表中的电影名字
       TRR[i]=res.data.list[i].fName
      //转变成图片地址
      TRR[i]="src/views/film/filmInformation/images/film/"+TRR[i]

      // console.log(TRR[i])
    }

  }).catch(error=>{
    layer.msg("错误")
  })
})





</script>

<style lang="scss" scoped >
.icon1{
  width:300px;
  height: 400px;
  display: flex;
  flex-wrap: wrap;
}
.initial{
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  .first-layer{
    height: 100%;
    width: 1200px;

  }
}
.second-left{
  width: 950px;
  height: 100%;
  float: left;

  .second-top{
    width: 950px;
    height: 50%;
    float:top;
    background-color: aqua;
    .hit-film{
      width: 120px;
      height: 180px;
      padding-right: 15px;
      padding-top:15px


    }
  }
  .second-bottom{
    width: 950px;
    height: 50%;
    float: bottom;
    border: red solid 1px;
    background-color: red;
  }
}

.second-right{
  width: 250px;
  height: 100%;
  float: right;

}

</style>

