
<template>
  <div class="initial">
 <div class="first-layer">
   <div class="second-left">
<!--左侧顶部——上映区-->
    <div class="second-top">
      <div style="width: 100%;height: 100%">
    <div style="font-size: 20px;color: red;padding-left: 20px">正在热映</div>
        <div class="Div"  v-for="(item,index) in Num" :key="index">
          <img class="Img" :src=url+item.fName>
          <span>{{item.fId}}</span>
          <router-link to='/filmInformation'>
            <button style="width:120px" @click="jump(item)">购票</button>
          </router-link>
        </div>
      </div>
    </div>
<!--左侧底部——待映区-->
    <div class="second-bottom">
      <div style="font-size: 20px;color: red;padding-left: 20px">即将上映</div>
      <!--电影轮播-->
      <lay-carousel style="height: 100%;width: 100%" v-model="active4">
        <lay-carousel-item style="width: 100%;height: 100%"  :id="item.id" v-for="item in arrays">
          <div style="display: flex; justify-content: center;  width:100%;height:100%;">
            <img style="width: 80%;height: 100%" :src=item.text alt="图片路径错误">
          </div>
        </lay-carousel-item>
      </lay-carousel>
    </div>
   </div>

<!--&lt;!&ndash;右侧&ndash;&gt;-->
<!--   <div class="second-right" >-->

<!--   </div>-->
<!--   &lt;!&ndash;数据显示的表格&ndash;&gt;-->
<!--   <lay-table :columns="columns" :data-source="dataSource">-->
<!--   </lay-table>-->
 </div>
    </div>





</template>



<script setup>
import {findAll, findOne} from "./page.js";
import {onMounted, reactive, ref} from "vue";
import {layer} from "@layui/layui-vue";

let filmsInfo=ref()


const active4 = ref("1")
const arrays = ref([])
setTimeout(() => {
      arrays.value = [
        {id: "1", text: "src/views/film/filmInformation/images/film/虹猫仗剑走天涯.jpg"},
        {id: "2", text: "src/views/film/filmInformation/images/film/虹猫蓝兔七侠传.jpg"},
        {id: "3", text: "src/views/film/filmInformation/images/film/虹猫蓝兔勇者归来.jpg"},
        {id: "4", text: "src/views/film/filmInformation/images/film/虹猫蓝兔勇者归来.jpg"}
      ]
},1000)






//跳转页面并发送数据
function jump(item){
  sessionStorage.removeItem('FilmName')
  sessionStorage.setItem('FilmName',item.fId)
  findOne(item.fId).then(res=>{
    filmsInfo.value=res.data
    sessionStorage.setItem('filmInfo',JSON.stringify(filmsInfo.value))
  }).catch(err=>{

  })
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

//电影ID数组
 const  Num=reactive([]);
const dataSource=reactive([])

onMounted(()=>{
  findAll().then(res=>{
    dataSource.push(...res.data.list)
    for (let i = 0; i < res.data.list.length; i++) {
       // 获取电影表中的电影
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
    //border: 1px solid aqua;
    overflow: auto;
    //background-color: aqua;

  }

  .second-bottom{
    width: 100%;
    height: 50%;
    float: bottom;
    //overflow: auto;
    //border:aqua solid 1px;
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

