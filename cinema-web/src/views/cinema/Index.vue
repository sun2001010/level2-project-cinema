<template>
  <div>
    <div class="content">
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">定位：</lay-button>
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
          <lay-button type="primary">影院：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index) in cinemaName" :key="index" class="cinema3"
               :class="ChangeColor === index?'color1':'color2'" @click="selected(index,item)">
            <lay-button v-model="cinemaInfo.cName">{{ item }}</lay-button>
          </div>
        </div>
      </div>
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">地区：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index1) in cinemaAddress" :key="index1" class="cinema3"
               :class="ChangeColor1===index1?'color1':'color2'" @click="selected1(index1,item)">
            <lay-button v-model="cinemaInfo.cAddress">{{ item }}</lay-button>
          </div>
        </div>
      </div>
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">影厅：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index4) in hallName" :key="index4" class="cinema3"
               :class="ChangeColor4 === index4?'color1':'color2'" @click="selected4(index4,item)">
            <lay-button v-model="cinemaInfo.hallName">{{ item }}</lay-button>
          </div>
        </div>
      </div>
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">观影时间：</lay-button>
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
      <lay-button type="normal">影院列表</lay-button>
      <div v-for="item in cinema1" :key="item" class="cinema">
        <div class="data">
          <p style="font-size: 18px">{{ item.cName }}</p>
          <p>地址：{{ item.cAddress }}</p>
        </div>
        <div class="price">32.0</div>
        <div class="data1">
          <lay-button type="danger" radius @click="selectSeat">选座购票</lay-button>
        </div>
        <lay-line theme="black"></lay-line>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {buyTicket, findCinema,findHall,position} from "./api.js"
import {layer} from "@layui/layui-vue";

let cities=reactive(['自动定位',"长沙",'郴州','衡阳','娄底','冷水江','邵阳','常德'])
let cinemaName=reactive([])
let cinemaAddress=reactive([])
let cinema=reactive([])
let cinema1=reactive([])
let lookTime=reactive(['8:00-10:00','10:00-12:00','12:00-14:00','14:00-16:00',
  '16:00-18:00','18:00-20:00','20:00-22:00','22:00-24:00'])

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

onMounted(()=>{
  findCinema().then(res=>{
    for(let i of res.data.list){
      if (cinema.length<res.data.list.length){
        cinema.push(i)
      }
    }
    for (let j of cinema){
      cinemaName.push(j.cName)
      cinemaAddress.push(j.cAddress)
    }
    cinemaName=["全部",...cinemaName]
    cinemaAddress=["全部",...cinemaAddress]
    let set = new Set(cinemaName)
    cinemaName = Array.from(set)
    let arr = new Set(cinemaAddress)
    cinemaAddress = Array.from(arr)
  }).catch(error=>{
    layer.msg("错误")
  })
})

onMounted(()=>{
  findHall().then(res=>{
    for(let i of res.data.list){
      if (hall.length<res.data.list.length){
        hall.push(i)
      }
    }
    for (let j of hall){
      hallName.push(j.hName)
    }
    hallName=["全部",...hallName]
    let set = new Set(hallName)
    hallName = Array.from(set)
  }).catch(error=>{
    layer.msg("hall 错误")
  })
})

//影院
function selected(index,item){
  ChangeColor.value=index
  cinemaInfo.cName=item
  cinema1=JSON.parse(JSON.stringify(cinema));
  for (let i of cinema1) {
    arrDelete(cinema1, (i) => i.cName !== item)
  }
  console.log(cinema1)
}

//地区
function selected1(index1,item){
  ChangeColor1.value=index1
  cinemaInfo.cAddress=item
}

//位置
function selected2(index2,item){
  ChangeColor2.value=index2
  cinemaInfo.position=item
  position(item).then(res=>{
    console.log(res)
  }).catch(err=>{
    layer.msg("position error")
  })
}

//影厅
function selected4(index4,item){
  ChangeColor4.value=index4
  cinemaInfo.hallName=item
}
//观影时间
function selected3(index3,item){
  ChangeColor3.value=index3
  cinemaInfo.lookTime=item
}
//选座购票
function selectSeat(){
  console.log(cinemaInfo)
  // buyTicket(cinemaInfo.value).then(res=>{
  //   console.log("")
  // }).catch(err=>{
  //   layer.msg("buy ticket error")
  // })
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
        background-color: orangered;
        color: white;
      }
      .color2 {
        background-color: white;
      }
    }
  }
}
.contain{
  margin: 10px;
  .cinema{
    display: flex;
    flex-wrap: wrap;
    margin-top: 20px;
    .data{
      margin: 5px;
      width: 87%;
    }
    .price{
      font-size: 20px;
      margin: 10px;
    }
  }
}
</style>