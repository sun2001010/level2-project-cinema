<template>
  <lay-tab v-model="current1">
    <lay-tab-item title="正在热映" v-if="true" id="1">
      <div>
        <div class="content">
          <div class="cinema">
            <div class="cinema1">
              <lay-button type="primary">类型</lay-button>
            </div>
            <div class="cinema2">
              <div v-for="(item,index2) in cities" :key="index2" class="cinema3"
                   :class="ChangeColor2 === index2?'color1':'color2'" @click="selected2(index2,item)">
                <lay-button v-model="cinemaInfo.position">{{ item }}</lay-button>
              </div>
            </div>
          </div>
          <div class="cinema">
            <div class="cinema1">
              <lay-button type="primary">导演</lay-button>
            </div>
            <div class="cinema2">
              <div v-for="(item,index3) in lookTime" :key="index3" class="cinema3"
                   :class="ChangeColor3===index3?'color1':'color2'" @click="selected3(index3,item)">
                <lay-button v-model="cinemaInfo.lookTime">{{ item }}</lay-button>
              </div>
            </div>
          </div>
        </div>
        <div class="contain">
          <div v-for="item in Num1" :key="item" class="cinema">
            <div class="data">
              <img class="Img" :src=url+item.fImage>
              <p style="text-align: center;color: #0feeba;font-size: 20px">{{item.fName}}</p>
              <!--<p>{{item.director}}</p>-->
              </div>
          </div>
        </div>
      </div>

    </lay-tab-item>
    <lay-tab-item title="即将上映" id="2">
      <div>
        <div class="content">
          <div class="cinema">
            <div class="cinema1">
              <lay-button type="primary">类型</lay-button>
            </div>
            <div class="cinema2">
              <div v-for="(item,index2) in cities" :key="index2" class="cinema3"
                   :class="ChangeColor2 === index2?'color1':'color2'" @click="selected2(index2,item)">
                <lay-button v-model="cinemaInfo.position">{{ item }}</lay-button>
              </div>
            </div>
          </div>
          <div class="cinema">
            <div class="cinema1">
              <lay-button type="primary">导演</lay-button>
            </div>
            <div class="cinema2">
              <div v-for="(item,index3) in lookTime" :key="index3" class="cinema3"
                   :class="ChangeColor3===index3?'color1':'color2'" @click="selected3(index3,item)">
                <lay-button v-model="cinemaInfo.lookTime">{{ item }}</lay-button>
              </div>
            </div>
          </div>
        </div>
        <div class="contain">
          <div v-for="item in Num1" :key="item" class="cinema">
            <div class="data">
              <img class="Img" :src=url+item.fImage>
              <p style="text-align: center;color: #0feeba;font-size: 20px">{{item.fName}}</p>
              <!--<p>{{item.director}}</p>-->
            </div>
          </div>
        </div>
      </div>
    </lay-tab-item>
  </lay-tab>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import {layer} from "@layui/layui-vue";
import {findAll} from "../home/page.js";
import { ref } from 'vue'

const url="src/views/film/filmInformation/images/film/"
const current1 = ref("1")
let Num=reactive([]);

onMounted(()=>{
  findAll().then(res=>{
    for (let i of res.data.list){
      if (Num.length<res.data.list.length){
        Num.push(i)
      }
    }
    for (let i of Num){
      console.log(i.oType)
    }
  }).catch(error=>{
    layer.msg("错误")
  })
})


let cities=reactive(["爱情",'戏剧','动画','剧情','恐怖','动作','冒险'])
let cinemaName=reactive([])
let cinemaAddress=reactive([])
let cinema=reactive([])
let cinema1=reactive([])
let cinema2=reactive([])
let lookTime=reactive(['阿斯顿','久熙','诺兰','刘海波','杨树鹏','程小东','卡梅隆'])

let hallName=reactive([])
let hall=reactive([])


let ChangeColor = ref(0)
let ChangeColor1 = ref(0)
let ChangeColor2 = ref(0)
let ChangeColor3 = ref(0)
let ChangeColor4 = ref(0)

const cinemaInfo=reactive(
    {
      position:"",
      cName:"",
      cAddress:"",
      hallName:"",
      lookTime:""
    }
)
let Num1=reactive([])

//类型
function selected2(index2,item){
  ChangeColor2.value=index2
  cinemaInfo.position=item
  Num1=JSON.parse(JSON.stringify(Num))
  for (let i of Num)
  {
    arrDelete(Num1,(i)=>i.oType!==item)
  }
}

//导演
function selected3(index3,item){
  ChangeColor3.value=index3
  cinemaInfo.lookTime=item
  Num1=JSON.parse(JSON.stringify(Num))
  for (let i of Num)
  {
    arrDelete(Num1,(i)=>i.director!==item)
  }
}
function arrDelete(arr, func) {
  // 遍历取到每个对象和对应下标，通过自定义的函数判断该对象是否删除，
  arr.forEach((item1, i) => {
    if (func(item1)) {
      arr.splice(i, 1)
    }
  })
}
</script>

<style scoped lang="scss">
.content{
  display: flex;
  align-content: center;
  flex-direction: column;
  margin: 5px;
  border: #cccccc solid 1px;
  height: 100%;

  .cinema {
    display: flex;
    margin: 2px;
    .cinema1 {
      margin: 2px;
      display: flex;
      .layui-btn {
        margin: 5px;
      }
    }
    .cinema2 {
      width: 100%;
      margin: 4px;
      display: flex;
      flex-wrap: wrap;
      .cinema3{
        margin: 3px;
      }
      .color1 {
        background-color: blueviolet;
        color: white;
      }
      .color2 {
        background-color: white;
      }
    }
  }
}
.contain{
  display: flex;
  flex-wrap: wrap;
  margin: 10px;
  .cinema{
    display: flex;
    flex-wrap: wrap;
    width: 300px;
    height: 300px;
    margin-top: 20px;
    .data{
      width: 200px;
      height: 200px;
      margin: 5px;
    }
    .price{
      font-size: 20px;
      margin: 10px;
    }
  }
}
.Img{
  height: 250px;
  width: 180px;

}
</style>
