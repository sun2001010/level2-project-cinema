<template>
  <div class="order-list">
    <h1>订单列表 </h1>
    <table class="layui-table">
      <thead>
      <tr>
        <th>电影名称</th>
        <th>图片</th>
        <th>价格</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="order in orders" :key="order.id">
        <td>{{ order.fName }}</td>
        <td><img src=../film/filmInformation/images/film/虹猫仗剑走天涯.jpg /></td>
        <td>{{ order.oPrice}}</td>
        <td>{{ order.cStatus===1 ?'激活':'失效' }}</td>
        <td>
          <lay-button v-if="order.cStatus<1" type="warm" radius="true" @click="deleteOrder(order.oId)">删除订单</lay-button>
          <lay-button v-if="order.cStatus>0" type="danger" radius="true" @click="cancelOrder(order.oId)">取消订单</lay-button>
          <lay-button type="normal" radius="true" @click="orderDetail(order)">查看订单详情</lay-button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
  <lay-layer  v-model="getOrderVisible"  :area="['800px','600px']" >
    <div class="img-content" >
      <img src=../film/filmInformation/images/film/虹猫仗剑走天涯.jpg />
      <p style="margin-top: 18px">一套开源免费且受众广泛的 Web UI 组件库</p>
    </div>
  </lay-layer>
  </template>
  <script setup>

  import {onMounted, onUpdated, reactive, ref} from "vue";
  import {getSeatInfo, getSeatStatus} from "../select/api.js";
    import router from "../../config/router.js";
    import {layer} from "@layui/layui-vue";
  import {buy, buySeats, canselOrder, delOrder, getOrderInfo} from "./api.js";
    let getOrderVisible=ref(false)
    let oneOrder=reactive({

    })
    let orders=reactive([
    ])
    const start=onMounted(() => {
      getInfo()
    })




    function orderDetail(order){
      oneOrder=order
      getOrderVisible.value=true
    }
    function getInfo() {
      getOrderInfo(1).then((res)=>{
        for (let r of res.data) {
          if (orders.length < res.data.length) {
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
        icon: 1, //可以无限个按钮
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
  </style>