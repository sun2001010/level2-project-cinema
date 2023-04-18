<template>
  <div>
    <div class="content">
<!--      定位-->
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">定位：</lay-button>
          <lay-button type="normal" v-model="positionData.city" @click="AutoPosition">
            <lay-icon type="layui-icon-location"></lay-icon>
            {{positionData.city}}
          </lay-button>
          <lay-button v-model="positionData.distance">距离：约{{positionData.distance}}km</lay-button>
        </div>
      </div>
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="normal">手动定位</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index2) in cities" :key="index2" class="cinema3"
               :class="ChangePositionColor === index2?'color1':'color2'" @click="selectPosition(index2,item)">
            <lay-button v-model="cinemaInfo.position">{{ item }}</lay-button>
          </div>
        </div>
      </div>
<!--      影院-->
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">影院：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index) in cinemaName" :key="index" class="cinema3"
               :class="ChangeCinemaNameColor === index?'color1':'color2'" @click="selectByCinemaName(index,item)">
            <lay-button v-model="cinemaInfo.cName">{{ item }}</lay-button>
          </div>
        </div>
      </div>
<!--      地区-->
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">地区：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index1) in cinemaAddress" :key="index1" class="cinema3"
               :class="ChangeCinemaAddressColor===index1?'color1':'color2'" @click="selectByCinemaAddress(index1,item)">
            <lay-button v-model="cinemaInfo.cAddress">{{ item }}</lay-button>
          </div>
        </div>
      </div>
<!--      影厅名-->
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">影厅：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index4) in hallName" :key="index4" class="cinema3"
               :class="ChangeHallNameColor === index4?'color1':'color2'" @click="selectByHallName(index4,item)">
            <lay-button v-model="cinemaInfo.hallName">{{ item }}</lay-button>
          </div>
        </div>
      </div>
<!--      观影时间-->
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">观影时间：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index3) in lookTime" :key="index3" class="cinema3"
               :class="ChangeLookTimeColor===index3?'color1':'color2'" @click="selectLookTime(index3,item)">
            <lay-button v-model="cinemaInfo.lookTime">{{ item }}</lay-button>
          </div>
        </div>
      </div>
    </div>
<!--    影院列表-->
    <div class="contain">
      <lay-button type="normal">影院列表</lay-button>
      <div v-for="item in cinemaName1" :key="item" class="cinema">
        <div class="data">
          <p style="font-size: 18px">{{ item.cName }}</p>
          <p>地址：{{ item.cAddress }}</p>
        </div>
        <div class="price">{{item.cLevel}}</div>
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
import {autoPosition, buyTicket, findCinema, findHall, position} from "./api.js"
import {layer} from "@layui/layui-vue";

let cities=reactive(["长沙",'郴州','衡阳','娄底','冷水江','邵阳','常德','新疆','黑龙江','青海','吉林',
  '沈阳','武汉','成都','广州','北京','贵州','西安','上海','郑州'])
let cinemaName=reactive([])
let cinemaAddress=reactive([])
let cinemaAddress1 = reactive([])
let cinema=reactive([])
let cinemaName1=reactive([])
let lookTime=reactive(['8:00-10:00','10:00-12:00','12:00-14:00','14:00-16:00',
  '16:00-18:00','18:00-20:00','20:00-22:00','22:00-24:00'])

let hallName=reactive([])
let hall=reactive([])

let ChangePositionColor = ref(0)
let ChangeCinemaNameColor = ref(0)
let ChangeCinemaAddressColor = ref(0)
let ChangeHallNameColor = ref(0)
let ChangeLookTimeColor = ref(0)

const positionData = reactive({
  city:'',
  lng:'',
  lat:'',
  distance:''
})
const positionData1=reactive({
  lng:'',
  lat:''
})

const cinemaInfo=reactive({
    position:"",
    cName:"",
    cAddress:"",
    hallName:"",
    lookTime:"",
    hId:''
  }
)
//接收影院后端传到前端的数据，放在cinema数组中
//影院名放在cinemaName数组
//影院地址放在cinemaAddress数组
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
    // cinemaName=["全部",...cinemaName]
    // cinemaAddress=["全部",...cinemaAddress]
    let set = new Set(cinemaName)
    cinemaName = Array.from(set)
    let arr = new Set(cinemaAddress)
    cinemaAddress = Array.from(arr)
  }).catch(error=>{
    layer.msg("错误")
  })
})
//接收影厅后端传到前端的数据，放到hall数组中
//影厅名放到hallName数组中
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
    let set = new Set(hallName)
    hallName = Array.from(set)
  }).catch(error=>{
    layer.msg("hall 错误")
  })
})

//按影院名查询
function selectByCinemaName(index,item){
  ChangeCinemaNameColor.value=index
  cinemaInfo.cName=item
  cinemaName1=JSON.parse(JSON.stringify(cinema));
  for (let i of cinema) {
    arrDelete(cinemaName1, (i) => i.cName !== item)
  }
}

//按地区查询
function selectByCinemaAddress(index1,item){
  ChangeCinemaAddressColor.value=index1
  cinemaInfo.cAddress=item
  position(item).then(res=>{
    positionData1.lng=res.data.result.location.lng
    positionData1.lat=res.data.result.location.lat
    // Number(positionData1.lng)
    // Number(positionData1.lat)
    // Number(positionData.lng)
    // Number(positionData.lat)
    // console.log(Math.abs(lng-positionData.lng))
    // console.log(Math.abs(lat-positionData.lat))
    console.log(positionData.lng)
    if (positionData.lng!='') {
      positionData.distance = Math.round((Math.abs(positionData1.lng - positionData.lng)
          + Math.abs(positionData1.lat - positionData.lat)) * 80)
    }
    console.log(positionData.distance)
    console.log("当前选择影院地址："+item)
    console.log("经度："+res.data.result.location.lng)
    console.log("纬度："+res.data.result.location.lat)
  }).catch(err=>{
    layer.msg("position error")
  })
}
//自动定位
function AutoPosition(){
  autoPosition().then(res=>{
    positionData.city='当前位置'
    positionData.lng=res.data.content.point.x
    positionData.lat=res.data.content.point.y
    if (positionData1.lng!=''){
    positionData.distance=Math.round((Math.abs(positionData1.lng-positionData.lng)
        +Math.abs(positionData1.lat-positionData.lat))*80)
    }
    console.log("当前IP所属城市："+res.data.content.address)
    console.log("经度："+res.data.content.point.x)
    console.log("纬度："+res.data.content.point.y)
  }).catch(err=>{
    layer.msg("auto position error")
  })
}
//手动定位
function selectPosition(index2,item){
  ChangePositionColor.value=index2
  cinemaInfo.position=item
  position(item).then(res=>{
    positionData.city=item
    positionData.lng=res.data.result.location.lng
    positionData.lat=res.data.result.location.lat
    if (positionData1.lng!='') {
      positionData.distance = Math.round((Math.abs(positionData1.lng - positionData.lng)
          + Math.abs(positionData1.lat - positionData.lat)) * 80)
    }
    console.log("当前手动定位城市："+item)
    console.log("经度："+res.data.result.location.lng)
    console.log("纬度："+res.data.result.location.lat)
  }).catch(err=>{
    layer.msg("position error")
  })
}
//影厅
function selectByHallName(index4,item){
  ChangeHallNameColor.value=index4
  cinemaInfo.hallName=item
}
//观影时间
function selectLookTime(index3,item){
  ChangeLookTimeColor.value=index3
  cinemaInfo.lookTime=item
}
//选座购票
function selectSeat(){
  console.log(cinemaInfo)
  for (let i of hall){
    if ((cinemaInfo.cName===i.cName)&&(cinemaInfo.hallName===i.hName)){
      cinemaInfo.hId=i.hId
    }
    // if (i.hName=cinemaInfo.hallName)
    // console.log(i.hName)
  }
  sessionStorage.setItem("cinemaInfo", JSON.stringify(cinemaInfo))
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