
<template>
  <div class="initial">
 <div class="first-layer">
   <div class="second-left">
<!--左侧顶部——待映区-->
     <div class="second-bottom">
       <div style="font-size: 20px;color: #3c9fe6;padding-left: 20px">即将上映</div>
       <!--电影轮播-->
       <lay-carousel style="height: 90%;width: 100%" v-model="active4">
         <lay-carousel-item style="width: 100%;height: 100%"  :id="item.id" v-for="item in arrays">
           <div style="display: flex; justify-content: center;  width:100%;height:100%;">
             <img style="width: 80%;height: 100%" :src=item.text alt="图片路径错误">
           </div>
         </lay-carousel-item>
       </lay-carousel>
     </div>

<!--左侧底部——上映区-->
     <div class="second-top">
       <div style="width: 100%;height: 100%">
         <div style="font-size: 20px;color: red;padding-left: 20px">正在热映</div>
         <div class="Div"  v-for="(item,index) in Num" :key="index">
           <img class="Img" :src=url+item.fImage>
<!--打印电影ID-->
<!--           <span>{{item.fId}}</span>-->
           <router-link to='/filmInformation'>
             <button class="Button" @click="jump(item)">购票</button>
           </router-link>
         </div>
       </div>
     </div>
   </div>

<!--右侧-->
   <div class="second-right" >
     <div style="width: 100%;height: 70%;">
     <span style="font-size: 20px;color: #3c9fe6;margin-left: 20px">今日上榜</span>
     <div style="width: 100%;height: 30%;">
       <img style="width: 90%;height: 100%;margin-left: 20px;" src="src/views/film/filmInformation/images/film/忠犬小八.jpg">
     </div>
     <div style="width: 100%;height: 55%;margin-top: 30px;">
       <ol style="margin-left: 20px">
         <li style="font-size: 15px;margin-bottom: 30px"><i style="color: red;font-size: 20px">2</i>&ensp;&ensp;&ensp;铃芽之旅&ensp;&ensp;1111万<img style="width: 15px;height: 15px"  src="src/views/film/filmInformation/images/film/热度.png"></li>
         <li style="font-size: 15px;margin-bottom: 30px"><i style="color: red;font-size: 20px">3</i>&ensp;&ensp;&ensp;龙马精神&ensp;&ensp;1110万<img style="width: 15px;height: 15px"  src="src/views/film/filmInformation/images/film/热度.png"></li>
         <li style="font-size: 15px;margin-bottom: 30px"><i style="color: #cccccc;font-size: 20px">4</i>&ensp;&ensp;&ensp;人生大事&ensp;&ensp;1109万<img style="width: 15px;height: 15px"  src="src/views/film/filmInformation/images/film/热度.png"></li>
         <li style="font-size: 15px;margin-bottom: 30px"><i style="color: #cccccc;font-size: 20px">5</i>&ensp;&ensp;&ensp;你的婚礼&ensp;&ensp;1104万<img style="width: 15px;height: 15px"  src="src/views/film/filmInformation/images/film/热度.png"></li>
         <li style="font-size: 15px;margin-bottom: 30px"><i style="color: #cccccc;font-size: 20px">6</i>&ensp;&ensp;&ensp;灌篮高手&ensp;&ensp;1100万<img style="width: 15px;height: 15px"  src="src/views/film/filmInformation/images/film/热度.png"></li>
       </ol>
     </div>
     </div>
     <div style="width: 90%;height: 30%;margin-left: 20px">
       <div style="width: 100%;display: flex;align-items: center">
       <span style=" background-color: #3c9fe6; width: 100%;font-size: 20px;color: #0feeba;text-align: center">App下载</span>
       </div>
       <div style="width: 160px;height: 180px                                     ;margin-left: 10px">
         <img style="width:160px;height: 180px" src="src/views/film/filmInformation/images/film/猫眼二维码.png">
       </div>
     </div>
   </div>
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


const active4 = ref("1")
const arrays = ref([])
setTimeout(() => {
      arrays.value = [
        {id: "1", text: "src/views/film/filmInformation/images/film/信条.webp"},
        {id: "2", text: "src/views/film/filmInformation/images/film/少年.webp"},
        {id: "3", text: "src/views/film/filmInformation/images/film/名侦探柯南.webp"},
        {id: "4", text: "src/views/film/filmInformation/images/film/左耳.webp"},
        {id: "54", text: "src/views/film/filmInformation/images/film/扫毒.webp"},
        {id: "6", text: "src/views/film/filmInformation/images/film/暴疯语.webp"},
        {id: "7", text: "src/views/film/filmInformation/images/film/黑衣人.webp"},
        {id: "8", text: "src/views/film/filmInformation/images/film/独立日.webp"},
      ]
},1000)






//跳转页面并发送数据
function jump(item){
  sessionStorage.removeItem('FilmName')
  sessionStorage.setItem('FilmName',item.fId)
  findOne(item.fId).then(res=>{
    console.log(res.data)
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
.Button{
  width:160px;
  font-size: 18px;
  opacity: 0.5;
  background-color: black;
  color: white;
}
Button:hover{
  background-color: red;
  color: black;
}
.Img{
  height: 200px;
  width: 160px;
}
.Div{
  height: 230px;
  width: 170px;
 //border: red 1px solid;
  margin-left: 20px;
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

}

</style>

