<template>
  <div>
    <div class="content">
      <div class="cinema">
        <div class="cinema1">
          <lay-button type="primary">定位：</lay-button>
        </div>
        <div class="cinema2">
          <div v-for="(item,index2) in cities" :key="index2" class="cinema3"
               :class="ChangePositionColor === index2?'color1':'color2'" @click="selectPosition(index2,item)">
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
               :class="ChangeCinemaNameColor === index?'color1':'color2'" @click="selectByCinemaName(index,item)">
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
               :class="ChangeCinemaAddressColor===index1?'color1':'color2'" @click="selectedByCinemaAddress(index1,item)">
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
               :class="ChangeHallNameColor === index4?'color1':'color2'" @click="selectByHallName(index4,item)">
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
               :class="ChangeLookTimeColor===index3?'color1':'color2'" @click="selectLookTime(index3,item)">
            <lay-button v-model="cinemaInfo.lookTime">{{ item }}</lay-button>
          </div>
        </div>
      </div>
    </div>
    <div class="contain">
      <lay-button type="normal">影院列表</lay-button>
      <div v-for="item in cinemaName1" :key="item" class="cinema">
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

const data = {
  key: "XOXBZ-MZWWD-CDX4H-PONXN-UA5PJ-D7FJN" //这个key就是你申请的密钥
};
const url = "https://apis.map.qq.com/ws/location/v1/ip"; //这个就是地理位置信息的接口
data.output = "jsonp";

const cinemaInfo=reactive(
  {
    position:"",
    cName:"",
    cAddress:"",
    hallName:"",
    lookTime:""
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
    hallName=["全部",...hallName]
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
  for (let i of cinemaName1) {
    arrDelete(cinemaName1, (i) => i.cName !== item)
  }
  console.log(cinemaName1)
}

//按地区查询
function selectByCinemaAddress(index1,item){
  ChangeCinemaAddressColor.value=index1
  cinemaInfo.cAddress=item
}

//位置
function selectPosition(index2,item){
  ChangePositionColor.value=index2
  cinemaInfo.position=item
  position(item).then(res=>{
    console.log(res)
  }).catch(err=>{
    layer.msg("position error")
  })

  // JSONP形式从服务器获取数据
  // var url = 'http://vue.studyit.io/api/jsonp';
  // this.$http.jsonp(url).then(res => {
  //   console.log(res);
  // });
}
function requestByGet(){
    console.log("==========requestByGet==========")
    this.$http.get("test.txt").then(function (successData){
      this.resultMsgByGet = successData.data+"=======请求响应码是"+successData.status;
    },function (errorData){
      console.log("=====errorData==="+errorData)
    });
  }
function requestByPost(){
    console.log("==========requestByPost==========")
    var baseUrl = "testlogin.php";// POST请求PHP 页面
    var params = { // 请求数据
      username:"小明",
      userpass:"123456"
    };
    var otherParam = {
      emulateJSON:true // 告诉服务器数据参数类型以JSON 传递接收。
    };
    this.$http.post(baseUrl,params,otherParam).then(function (successData){
      this.resultMsgByPost = successData.data+"=======请求响应码是"+successData.status;
    },function (errorData){
      console.log("=====失败=====requestByPost=========="+errorData)
    });
  }
function requestByJsonp(){
    console.log("==========requestByJsonp==========")
    var baseUrl = "https://suggest.taobao.com/sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb"
    this.$http.jsonp(baseUrl).then(function(myData){
      // 解析JSON数据
      var myObj = JSON.parse(myData.bodyText);
      var tempD= myObj.result;
      console.log(tempD)
      var tempC = [];
      tempD.forEach(function(item){ // 遍历数据集合，取出数据，放入临时数组tempC
        console.log();
        tempC.push(item[0]);
      });
      this.taoBaoData = tempC;
    },function(errorData){
      console.log("==========errorData==========") //请求失败
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