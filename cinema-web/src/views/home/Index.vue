
<template>
  <div class="initial">
 <div class="first-layer">
   <div class="second-left">
    <div class="second-top">
      <div style="width: 100%;height: 100%">
    <div style="font-size: 20px;color: red;padding-left: 20px">正在热映</div>
      <img  class="Img" style="width: 120px;height: 160px;padding: 10px"   v-for="PageName in TRR" :src=PageName>
      </div>
    </div>
    <div class="second-bottom">
      <div style="font-size: 20px;color: red;padding-left: 20px">即将上映</div>

      <div class="Div"  v-for="(item,index) in Num" :key="index">
        <img class="Img" :src=url+item.fName>
        <span>{{item.fId}}</span>
        <router-link to='/filmInformation'>
          <button style="width:120px" @click="jump(item)">购票</button>
        </router-link>
      </div>

    </div>

   </div>
   <div class="second-right" >

   </div>
   <!--数据显示的表格-->
   <lay-table :columns="columns" :data-source="dataSource">

   </lay-table>
 </div>
    </div>





</template>

<script setup>


import {findAll} from "./page.js";
import {onMounted, reactive, ref} from "vue";
import {layer} from "@layui/layui-vue";




const PageName=reactive(
    {
  fId:""
})
function jump(item){

  sessionStorage.removeItem('FilmName')
  sessionStorage.setItem('FilmName',item.fId)
  console.log("fid")
  console.log(item.fId)
}

const url="src/views/film/filmInformation/images/film/"
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
//电影名字数组
let TRR=reactive([]);
//电影ID数组
 const  Num=reactive([]);
const dataSource=reactive([])

onMounted(()=>{
  findAll().then(res=>{
    dataSource.push(...res.data.list)
    for (let i = 0; i < res.data.list.length; i++) {
       // 获取电影表中的电影名字
       Num[i]=res.data.list[i]


    }

  }).catch(error=>{
    layer.msg("错误")
  })
})





</script>

<style lang="scss" scoped >
.Img{
  height: 160px;
  width: 120px;
}
.Div{
  height: 170px;
  width: 130px;
 //border: red 1px solid;
  padding: 10px;
  float:left;
}
.initial{
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  .first-layer{
    height: 100%;
    width: 1270px;

  }
}
.second-left{
  width: 1070px;
  height: 100%;
  float: left;

  .second-top{
    width: 100%;
    height: 50%;
    float:top;
    border: 1px solid aqua;
    overflow: auto;
    //background-color: aqua;

  }

  .second-bottom{
    width: 100%;
    height: 50%;
    float: bottom;
    overflow: auto;
    border: red solid 1px;
    //background-color: red;
  }
}

.second-right{
  width: 200px;
  height: 100%;
  float: right;
  background-color: #66cc66;

}

</style>

