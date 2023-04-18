<template>
  <div id="app">
    <div class="container">
      <div class="screen">
        <img src="src/views/select/img/4k.png">
      </div>
      <div class="seats">
        <ul v-for="row in rows" :key="row" class="row">
          <li v-for="col in cols" :key="col" class="seat"
              :class="{occupied: isOccupied(row, col), selected: isSelected(row, col)}" @click="toggleSeat(row,col)">
            {{ row }}{{ col }}
          </li>
        </ul>
      </div>

    </div>
    <div class="select-seat">
        <span v-for="item in selectedSeats"
              :key="item">{{item}}</span>
    </div>
    <lay-line border-style="dashed" border-width="3px"></lay-line>
    <div class="info">
      <p>已选座位：</p>
      <p1>{{ selectedSeats.join(', ') }}</p1>

      <div class="money">
        <p >总价 : </p>
        <p2>¥</p2>
        <p1> {{ totalPrice }}</p1>
      </div>
      <div class="yes">
        <lay-button type="danger" size="lg" fluid="true" @click="submit(buyData)" radius>确认选座</lay-button>

      </div>
      <lay-button type="danger" size="lg" @click="toOrder" radius>查看订单</lay-button>
    </div>

  </div>

</template>

<script setup>
import {useRoute} from 'vue-router';
import {ref, computed, watch, reactive, onMounted} from 'vue'
import {getSeatInfo, getSeatStatus} from "./api.js";
import router from "../../config/router.js";
import {layer} from "@layui/layui-vue";
import {buy, buySeats} from "../Order/api.js";
let selectHid=ref([])
let order=ref()
let buyInfo = reactive(
    {
      uId:1,
      fName:'',
      oType:'',
      startTime:'',
      endTime:'',
      oPrice:'',
      chName:'',
      cName:''
    }

)


const selectInfo = reactive([

])

// 模拟已占用的座位
  const occupiedSeats = reactive([])
// 用户选择的座位
let selectedSeats = ref([])
let buySeat=reactive([])
const selectedSeatsId = ref([])
// 座位行数
let rows = reactive([])
// 座位列数
let cols = reactive([])
// 座位单价
const price = ref()
let data1=reactive([])
// 总价
const totalPrice = computed(() => selectedSeats.value.length * price.value)
let buyData=reactive([])
//初始化座位数据
function init(){

  occupiedSeats.length=0
  const data=JSON.parse(sessionStorage.getItem("selectInfo"));
  const film=JSON.parse(sessionStorage.getItem("filmInfo"));
  price.value=film.fPrice
  data1=data
  buyData=JSON.parse(sessionStorage.getItem("buyInfo"));
  for (let r of data) {
    rows.push(r.sRow)
    cols.push(r.sCol)
    //判断座位状态
    if (r.sStatus===1) {
      const seat=r.sRow + r.sCol
      occupiedSeats.push(seat)
    }
  }
  rows.sort()
  cols.sort()
  let set = new Set(rows);
  rows = Array.from(set);
  let set1 = new Set(cols);
  cols = Array.from(set1);
}
//页面初始化
const start=onMounted(() => {
  init()
})
function toOrder(){
  window.router=router
  router.push("/order")
}


// 判断座位是否已占用
function isOccupied(row, col) {
  return occupiedSeats.includes(row + col)
}

// 判断座位是否已选择
function isSelected(row, col) {
  return selectedSeats.value.includes(row + col)
}

// 切换座位的选择状态
function toggleSeat(row, col) {
  if (isOccupied(row, col)) {
    layer.notifiy({
      title:"Warming",
      content:"该座位已被选择！",
      icon:2
    })
    return
  }
  const seat = row + col
  if (isSelected(row, col)) {

    selectedSeats.value = selectedSeats.value.filter(s => s !== seat)
  } else {
    if (selectedSeats.value.length===6){
      layer.msg("一次最多购买6张票", { icon : 5, time: 1000})
    }else {
      selectedSeats.value.push(seat)
    }

  }
}
function updateSeatInfo(uid){
  selectInfo.length=0
  getSeatInfo(uid).then((res)=>{
    for (let r of res.data) {
      if (selectInfo.length<res.data.length) {
        selectInfo.push(r)
      }
    }
    sessionStorage.removeItem("selectInfo");
    sessionStorage.setItem("selectInfo",JSON.stringify(selectInfo));
    //重新初始化
    init()

  }).catch(error=>{
    layer.msg("查询错误")
  })
}
// 提交选座
function submit(buyData) {

  updateSeatInfo(buyInfo.uId)
  // getSeatInfo(buyInfo.uId).then((res)=>{
  //   for (let r of res.data) {
  //     if (selectInfo.length<res.data.length) {
  //       selectInfo.push(r)
  //     }
  //   }
  //   sessionStorage.removeItem("selectInfo");
  //   sessionStorage.setItem("selectInfo",JSON.stringify(selectInfo));
  //   //重新初始化
  //   init()
  //
  // }).catch(error=>{
  //   layer.msg("查询错误")
  // })
  if (selectedSeats.value.length === 0) {
    layer.notifiy({
      title:"Warming",
      content:"请至少选座一个座位~",
      icon:3
    })
  }
  else {
    for (let valueElement of selectedSeats.value) {
      if (selectHid.value.length<selectedSeats.value.length){
        selectHid.value.push(valueElement)
      }
    }
    selectHid.value.push(data1[0].hId)
    layer.confirm('确认下单吗？', {
      title: "订单确认",
      icon: 1, //可以无限个按钮
      btn:[
        {
          text:"确认",
          callback(id){
            getSeatStatus(selectHid.value).then(res=>{
              //清空已选择状态
              // init()
              buyData.oPrice=totalPrice.value
              console.log(buyData)
              buy(buyData).then(res=>{
                buySeats(selectHid.value).then(res=>{
                  selectedSeats.value.length =0
                  selectHid.value.length=0
                  updateSeatInfo(buyData.uId)
                  layer.close(id)
                  layer.msg("下单成功！", { icon : 6, time: 1000})
                }).catch(err=>{
                  selectedSeats.value.length =0
                  selectHid.value.length=0
                  layer.close(id)
                  layer.msg("下单失败！", { icon : 5, time: 1000})
                })

              }).catch(error=>{
                selectedSeats.value.length =0
                selectHid.value.length=0
                layer.close(id)
                layer.msg("下单失败！", { icon : 5, time: 1000})
              })
            }).catch(error=>{
              selectedSeats.value.length =0
              selectHid.value.length=0
              layer.close(id)
              layer.msg("座位被抢了/(ㄒoㄒ)/~~", { icon : 5, time: 1000})
              init()
            })
          }
        },
    {
      text:"取消",
          callback(id){
      layer.close(id)
    }
    }
      ]
    })
  }
}

//得到座位信息
// function getInfo(id) {
//   getSeatInfo(info).then((res) => {
//     userInfoUpdate.id = info.id
//     userInfoUpdate.userName = res.data.data.username
//     userInfoUpdate.password = res.data.data.password
//     userInfoUpdate.phone = res.data.data.phone
//     userInfoUpdate.email = res.data.data.email
//   })
// }
// function test() {
//   for (let r of data) {
//     console.log(data)
//     if (r.sStatus===1) {
//       occupiedSeats.push(r)
//     }
//   }
//   console.log(occupiedSeats)
//
// }

// return {
//   occupiedSeats,
//   selectedSeats,
//   rows,
//   cols,
//   price,
//   totalPrice,
//   isOccupied,
//   isSelected,
//   toggleSeat,
//   submit
// }

</script>

<style scoped lang="scss">
.yes{
  width: 20%;
  margin: 50px auto;
  user-select: none;
}


.screen {
  width: 100%;
  height: 100%;
  color: #fff;
  padding-top: 30px;
  padding-bottom: 30px;
  background-repeat: no-repeat;
  text-align: center;
}

.seats {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 10px;
}

.row {
  display: flex;
}

.seat {
  width: 90px;
  height: 90px;
  border: 1px solid #ccc;
  list-style: none;
  text-align: center;
  line-height: 90px;
  user-select: none;
  border-radius: 10px;
  font-size: 20px;
  cursor: pointer;
}

.occupied {
  background-color: #fb4659;
}

.selected {
  background-color: #66cc66;
}

.info {
  margin: 50px auto;
  text-align: center;
  user-select: none;
  p{
    font-size: 20px;
    display: inline;
  }
  p1{
    font-size: 20px;
    display: inline;
    color: #66cc66;
  }
}
.money{
  p{
    font-size: 20px;
    display: inline;
  }
  p1{
    font-size: 30px;
    display: inline;
    color: #e54847;

  }
  p2{
    color: #e54847;
    font-size: 20px;
  }
}

.select-seat{

  display: flex;
  width: 480px;
  min-height: 80px;
  margin: 50px auto;
  border: 2px solid #eee;
  flex-wrap: wrap;
  span{
    border-color: #666666;
    border-style: dashed;
    width: 50px;
    height: 50px;
    margin: 10px;
    background-color: #0feeba;
    text-align: center;
    line-height: 50px;
    user-select: none;
  }
}
</style>