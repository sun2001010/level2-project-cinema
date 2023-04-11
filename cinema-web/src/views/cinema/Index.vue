<template>
  <div class="content">
    <div class="cinemaName">
      影院：
      <layui-button class="grid-demo">1465456465</layui-button>
      <layui-button class="grid-demo">1</layui-button>
      <layui-button class="grid-demo">1</layui-button>
      <layui-button class="grid-demo">1</layui-button>
      <layui-button class="grid-demo">1</layui-button>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.content{
  margin: 10px;
  border: #cccccc solid 1px;
  height: 300px;
}
.cinemaName{
  margin: 10px;
}
.grid-demo {
  margin: 20px;
  width: 50px;
  padding: 10px;
  line-height: 10px;
  border-radius: 2px;
  text-align: center;
  background-color: green;
  color: white;
}
</style>