<template>
  <div>
    <lay-button type="primary">影院：</lay-button>
    <lay-button>全部</lay-button>
    <lay-button @click="selectByName">1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
    <lay-button>1</lay-button>
  </div>
</template>

<script setup>
import {ref} from "vue";

const cinemaInfo = ref({
  cinemaName : "",
  cinemaAddress : ""
})

function selectByName(){

}
</script>

<style scoped>
.layui-btn{
  margin-top: 10px;
}
</style>