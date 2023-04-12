<template>
  <div id="app">
    <div class="container">
      <div class="screen">屏幕</div>
      <div class="seats">
        <ul v-for="row in rows" :key="row" class="row">
          <li v-for="col in cols" :key="col" class="seat"
              :class="{occupied: isOccupied(row, col), selected: isSelected(row, col)}" @click="toggleSeat(row,col)">
            {{ row }}{{ col }}
          </li>
        </ul>
      </div>
      <div class="info">
        <p>已选座位：{{ selectedSeats.join(', ') }}</p>
        <p>总价：{{ totalPrice }}</p>
        <button class="layui-btn layui-btn-normal" @click="submit">购票</button>
        <button class="layui-btn layui-btn-normal" @click="test">？</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {useRoute} from 'vue-router';
import {ref, computed, watch, reactive, onMounted} from 'vue'
import {getSeatInfo, getSeatStatus} from "./api.js";
import router from "../../config/router.js";
import {layer} from "@layui/layui-vue";



const selectInfo = reactive([
  {
    sId: '',
    sRow: '',
    sCol: '',
    sStatus: '',
    hID: ''
  }
])
// const o=computed(()=>{
//   for (let r of data) {
//     if (data.sStatus===1) {
//       occupiedSeats.push(r)
//     }
//   }
//   return occupiedSeats
// })


// watch(data, (newValue, oldValue) => {
//   for (let r of data) {
//     if (data.sStatus === 1) {
//       selectInfo.push(r)
//     }
//   }
//   console.log(selectInfo)
// },{immediate:true})

// 模拟已占用的座位
const occupiedSeats = reactive([])
// 用户选择的座位
let selectedSeats = ref([])
const selectedSeatsId = ref([])
// 座位行数
let rows = reactive([])
// 座位列数
let cols = reactive([])
// 座位单价
const price = ref(50)
// 总价
const totalPrice = computed(() => selectedSeats.value.length * price.value)

//初始化座位数据
function init(){
  occupiedSeats.length=0
  const data=JSON.parse(sessionStorage.getItem("selectInfo"));
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
function test(){
  console.log(data)
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
    alert("该座位已被选择")
    return
  }
  const seat = row + col
  if (isSelected(row, col)) {
    selectedSeats.value = selectedSeats.value.filter(s => s !== seat)
  } else {
    selectedSeats.value.push(seat)
  }
}

// 提交选座
function submit() {
  selectInfo.length=0
  getSeatInfo(1).then((res)=>{
     selectInfo.splice(0,1)
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
  if (selectedSeats.value.length === 0) {
    alert('请至少选择一个座位')
    init()
  }
  else {

    alert('您已成功选座：' + selectedSeats.value.join(', '))
    //清空已选择状态
    selectedSeats.value.length =0
    init()
    // TODO: 调用后端接口，保存选座信息
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
.container {
  width: 600px;
  margin: 0 auto;
}


.screen {
  width: 100%;
  height: 50px;
  background-image: url("img/4k.png");
  background-size: auto;
  color: #fff;
  text-align: center;
  line-height: 50px;
}

.seats {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.row {
  display: flex;
}

.seat {
  width: 60px;
  height: 60px;
  border: 1px solid #ccc;
  list-style: none;
  text-align: center;
  line-height: 60px;
}

.occupied {
  background-color: #da2c2c;
}

.selected {
  background-color: #66cc66;
}

.info {
  text-align: center;
  margin-top: 20px;
}
</style>