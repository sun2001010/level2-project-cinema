<template>
  <lay-layout class="example">
    <lay-header>
      <Header></Header>
    </lay-header>
    <lay-layout>
      <lay-body>
        <Content></Content>
      </lay-body>
      <lay-side>
        <Right></Right>
      </lay-side>
    </lay-layout>
  </lay-layout>
</template>

<script setup>
import Header from "./Header.vue";
import Content from "./Content.vue";
import Right from "./Right.vue";

</script>

<style scoped lang="scss">
.layui-layout{
  background-image: url("images/bg2.jpg");
  background-size:100% 100%;
  height: 100vh;
  .layui-header{
    height: 580px;
  }
  .layui-layout {

    .layui-body {
      padding: 100px;
      width: 60%
    }

    .layui-side {
      padding-right: 500px;
    }
  }
}
</style>