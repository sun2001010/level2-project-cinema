<template>
  <div class="contain">
    <lay-row space="10">
      <lay-col>

      </lay-col>
    </lay-row>
    <lay-row space="10">
      <lay-col>
        <lay-table :columns="columns">
          <template #action{row}>
            <lay-tooltip content="修改">
              <lay-button type="normal" size="sm">
                <lay-icon type="layui-icon-edit" size="18px"></lay-icon>
              </lay-button>
            </lay-tooltip>
            <lay-tooltip content="删除">
              <lay-button type="danger" size="sm" @click="onDelete(row)">
                <lay-icon type="layui-icon-delete" size="18px"></lay-icon>
              </lay-button>
            </lay-tooltip>
          </template>
        </lay-table>
      </lay-col>
    </lay-row>
  </div>
</template>

<script setup>
import {reactive} from "vue";
const data = reactive([])
const columns = reactive([
  {
  title: "影片",
  key: "fId",
  align: "center",
  },
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
    title: "放映时间",
    key: "fTime",
    align: "center"
  },
  {
    title: "操作",
    customSlot:'action',
    align:'center',
  }
])
</script>

<style scoped>

</style>