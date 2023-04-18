<template>

  <div class="order-list">
    <h1>订单列表 </h1>
    <lay-table :columns="columns" :data-source="orders" :page="page"
               @change="onPageChange">
      <template #action="{row}">
        <lay-button v-if="row.cStatus==='失效'" type="warm" radius="true" @click="deleteOrder(row.oId)">删除订单</lay-button>
        <lay-button v-if="row.cStatus==='激活'" type="danger" radius="true" @click="cancelOrder(row.oId)">取消订单</lay-button>
        <lay-button type="normal" radius="true" @click="orderDetail(row)">查看订单详情</lay-button>
      </template>

<!--      <tbody>-->
<!--      <tr v-for="order in orders" :key="order.id">-->
<!--        <td>{{ order.fName }}</td>-->
<!--        <td><img src=../film/filmInformation/images/film/虹猫仗剑走天涯.jpg /></td>-->
<!--        <td>{{ order.oPrice}}</td>-->
<!--        <td>{{ order.cStatus===1 ?'激活':'失效' }}</td>-->
<!--        <td>-->
<!--          <lay-button v-if="order.cStatus<1" type="warm" radius="true" @click="deleteOrder(order.oId)">删除订单</lay-button>-->
<!--          <lay-button v-if="order.cStatus>0" type="danger" radius="true" @click="cancelOrder(order.oId)">取消订单</lay-button>-->
<!--          <lay-button type="normal" radius="true" @click="orderDetail(order)">查看订单详情</lay-button>-->
<!--        </td>-->
<!--      </tr>-->
<!--      </tbody>-->
    </lay-table>
  </div>
  <lay-layer  v-model="getOrderVisible" :move="false" title="订单详情" shadeClose anim="4" isOutAnim:true :area="['1000px','800px']" >
    <lay-form>
        <lay-form-item class="image"  >
        <img  class="img-responsive" :src=url+filmInfo.fImage>
      </lay-form-item>
      <lay-form-item label="电影名" >
        <lay-input v-model="filmInfo.fName" :placeholder=filmInfo.fName readonly="readonly"></lay-input>
      </lay-form-item>
      <lay-form-item label="座位">
        <lay-input v-for="(item) in seatInfo" :placeholder=item.row+item.col readonly="readonly"></lay-input>
      </lay-form-item>
      <lay-form-item label="单价" readonly="readonly">
        <lay-input v-model="filmInfo.fPrice" :placeholder=filmInfo.fPrice readonly="readonly"></lay-input>
      </lay-form-item>
      <lay-form-item label="导演" readonly="readonly">
        <lay-input prefix-icon="prefix-icon-"  v-model="filmInfo.director" :placeholder=filmInfo.director readonly="readonly"></lay-input>
      </lay-form-item>
      <lay-form-item label="简介" readonly="readonly">
        <lay-input v-model="filmInfo.fContent"  :placeholder=filmInfo.fContent></lay-input>
      </lay-form-item>

    </lay-form>
  </lay-layer>
  </template>
  <script setup>

  import {onMounted, onUpdated, reactive, ref} from "vue";
  import {getSeatInfo, getSeatStatus} from "../select/api.js";
    import router from "../../config/router.js";
    import {layer} from "@layui/layui-vue";
  import {buy, buySeats, canselOrder, delOrder, getFilmInfo, getOrderInfo, getTicketSeat} from "./api.js";
  let filmInfo=ref()
  let seatInfo=reactive([])
  const url="src/views/film/filmInformation/images/film/"
  const columns = reactive([
    {
      title: '电影名称',
      align: 'center',
      key: 'fName'
    },
    {
      title: '类型',
      key: 'oType',
      align: 'center',
      width:'150px'

    },
    {
      title: '影院',
      key: 'cName',
      align: 'center'
    },
    {
      title: '影厅',
      key: 'chName',
      align: 'center'

    },
    {
      title: '地址',
      key: 'cAddress',
      align: 'center',
      width:'300px'
    },
    {
      title: '价格',
      key: 'oPrice',
      align: 'center',
      width:'150px'
    },
    {
      title: '状态',
      key: 'cStatus',
      align: 'center',
      width:'70px'
    },
    {
      title: '操作',
      customSlot:'action',
      align: 'center'
    }
  ])
  // onMounted(()=>{
  //   for (let order of orders) {
  //     if (ordersCopy.length<orders.length){
  //       ordersCopy.push(order)
  //     }
  //   }
  // })
  const page = reactive({
    total: 10,
    limit: 10,
    current: 1,
    showRefresh: true,
    limits:[2,4,5,8,10,15,20]
  })

  function onPageChange({current,limit}){
    page.current=current
    page.limit=limit
    getInfo()
  }
    let getOrderVisible=ref(false)
    let oneOrder=reactive({

    })
    let orders=reactive([
    ])
  let ordersCopy=reactive([
  ])
    const start=onMounted(() => {

      getInfo()

    })
  // function getFilm(name) {
  //   getFilmInfo(name).then(res=>{
  //     filmInfo.value.length=0
  //     filmInfo.value=res.data
  //
  //
  //   }).catch(err=>{
  //     layer.msg("错误")
  //   })
  //
  // }



    function orderDetail(order){
    seatInfo.length=0
      getFilmInfo(order.fName).then(res=>{
       filmInfo.value=res.data
      }).catch(err=>{
        layer.msg("错误")
      })
      getTicketSeat(order.oId).then(res=>{
        for (let r of res.data) {
          if (seatInfo.length<res.data.length){
            seatInfo.push(r)
          }
        }
        getOrderVisible.value=true
        console.log(seatInfo)
      }).catch(err=>{
        layer.msg("详情查询错误")
      })


    }
    function getInfo() {
      getOrderInfo(1,page.current,page.limit).then((res)=>{
        orders.length=0
        page.current=res.data.pageNum
        page.total=res.data.total

        for (let r of res.data.list) {
          if (orders.length < res.data.list.length) {
            if (r.cStatus===1){
              r.cStatus='激活'
            }
            else {
              r.cStatus='失效'
            }
              orders.push(r)
          }
        }

        sessionStorage.setItem("orderInfo", JSON.stringify(orders));
      }).catch(error=>{
        layer.msg("查询错误")
      })
    }
    const deleteOrder = (oid) => {
      layer.confirm('确认删除订单吗？', {
        title: "删除订单确认",
        icon: 1,
        btn:[
          {
            text:"确认",
            callback(id){
              delOrder(oid).then(res=>{
                layer.close(id)
                layer.msg("删除成功！", { icon : 6, time: 1000})
                sessionStorage.removeItem("orderInfo");
                orders.length=0
                getInfo()
              }).catch(err=>{
                layer.close(id)
                layer.msg("删除失败！", { icon : 5, time: 1000})
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
    };


    const getOrder = (oid) => {
      // 查看订单的逻辑
    };

    function cancelOrder(oId){
      layer.confirm('确认取消订单吗？', {
        title: "取消订单确认",
        icon: 1, //可以无限个按钮
        btn:[
          {
            text:"确认",
            callback(id){
              canselOrder(oId).then(res=>{
                layer.close(id)
                layer.msg("取消成功！", { icon : 6, time: 1000})
                sessionStorage.removeItem("orderInfo");
                orders.length=0
                getInfo()
              }).catch(err=>{
                layer.close(id)
                layer.msg("取消失败！", { icon : 5, time: 1000})
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
  </script>

  <style scoped lang="scss">
    .order-list {
      margin: 20px;
    }
    .layui-table{
      text-align: center;
    }
    .layui-table th{
      text-align: center;
    }
    .detail{
      text-align: center;
    }
    .img-content {
      width: 100px;
      height: 100px;
      background-size: cover;
    }
   .lay-form-item{
     .image{
       width: 100px;
     }
   }
  </style>