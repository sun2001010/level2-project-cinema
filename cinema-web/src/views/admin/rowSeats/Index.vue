<template>
  <div class="contain">
    <lay-row>
      <lay-col>
        <lay-table :columns="columns"></lay-table>
      </lay-col>
    </lay-row>
  </div>
</template>

<script setup>
import {reactive} from "vue";

const columns = reactive([
  {
    title: "影院",
    key: "cName",
    align: "center"
  },
  {
    title: "影厅",
    key: "hName",
    align: "center"
  },
  {
    title: "操作",
    customSlot:'action',
    align:'center'
  }
])
</script>

<style scoped>

</style>