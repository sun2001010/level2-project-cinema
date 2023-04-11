<template>
  <lay-table :columns="columns" :data-source="userInfoList">
  </lay-table>
</template>

<script setup>
import {ref} from "vue";
const columns =ref([
  {
    title: "ID",
    key: "id",
    align: "center"
  }
])
const userInfoList = ref([])

</script>

<style scoped>

</style>