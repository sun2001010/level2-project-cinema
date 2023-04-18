<template>
  <div class="container">
    <lay-row space="10">
      <lay-col md="12" md-offset="5">
        <lay-input placeholder="请输入影片id" v-model="query.id"></lay-input>
      </lay-col>
      <lay-col md="2">
        <lay-tooltip content="搜索">
          <lay-button type="normal" @click="selectId">
            <lay-icon type="layui-icon-search"></lay-icon>
          </lay-button>
        </lay-tooltip>
      </lay-col>
      <lay-col md="2">
        <lay-tooltip content="添加影片">
          <lay-button type="primary" @click="filmsAddVisible = true">
            <lay-icon type="layui-icon-add-circle"></lay-icon>
          </lay-button>
        </lay-tooltip>
      </lay-col>
    </lay-row>
    <lay-row space="10">
      <lay-col>
        <lay-table :columns="columns" :data-source="data" :page="page" @change="pageChange">

          <template #action="{row}">
            <lay-tooltip content="修改">
              <lay-button type="normal" size="sm" @click="filmsUpdateVisible = true,onUpdate1(row)">
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
    <lay-layer v-model="filmsAddVisible" :area="['600px','600px']">
      <lay-form class="add">
        <lay-form-item label="影&ensp;片&ensp;名:">
          <lay-input v-model="filmsAdd.fName" placeholder="请输入影片名"></lay-input>
        </lay-form-item>
        <lay-form-item label="影片图片:">
          <lay-input v-model="filmsAdd.fImage" placeholder="请上传影片图片"></lay-input>
        </lay-form-item>
        <lay-form-item label="影片类型:">
          <lay-input v-model="filmsAdd.oType" placeholder="请输入影片类型"></lay-input>
        </lay-form-item>
        <lay-form-item label="导&ensp;&ensp;&ensp;&ensp;演:">
          <lay-input v-model="filmsAdd.director" placeholder="请输入导演"></lay-input>
        </lay-form-item>
        <lay-form-item label="导演图片:">
          <lay-input v-model="filmsAdd.directorImage" placeholder="请上传导演图片"></lay-input>
        </lay-form-item>
        <lay-form-item label="影片简介:">
          <lay-input v-model="filmsAdd.fContent" placeholder="请输入影片简介"></lay-input>
        </lay-form-item>
        <lay-form-item label="上映时间:">
          <lay-input v-model="filmsAdd.fTime" placeholder="请选择上映时间,例: '2023-01-01'"></lay-input>
        </lay-form-item>
        <lay-form-item label="价&ensp;&ensp;&ensp;&ensp;格:">
          <lay-input v-model="filmsAdd.fPrice" placeholder="请输入价格"></lay-input>
        </lay-form-item>
        <lay-form-item class="btns">
          <lay-button @click="filmsAddVisible = false">取消</lay-button>
          <lay-button type="normal" @click="onAdd">添加</lay-button>
        </lay-form-item>
      </lay-form>
    </lay-layer>
    <lay-layer v-model="filmsUpdateVisible" :area="['600px','600px']">
      <lay-form>
        <lay-form-item label="影&ensp;片&ensp;ID:">
          <lay-input  v-model="fId" placeholder="" :disabled="true"></lay-input>
        </lay-form-item>
        <lay-form-item label="影&ensp;片&ensp;名:">
          <lay-input v-model="fName" placeholder="请输入影片名"></lay-input>
        </lay-form-item>
        <lay-form-item label="影片图片:">
          <lay-upload field="file" @done="" multiple>
        </lay-upload>
        </lay-form-item>
        <lay-form-item label="影片类型:">
          <lay-input v-model="oType" placeholder="请输入影片类型"></lay-input>
        </lay-form-item>
        <lay-form-item label="导&ensp;&ensp;&ensp;&ensp;演:">
          <lay-input v-model="director" placeholder="请输入导演"></lay-input>
        </lay-form-item>
        <lay-form-item label="导演图片:">
          <lay-input v-model="directorImage" placeholder="请上传导演图片"></lay-input>
        </lay-form-item>
        <lay-form-item label="影片简介:">
          <lay-input v-model="fContent" placeholder="请输入影片简介"></lay-input>
        </lay-form-item>
        <lay-form-item label="上映时间:">
          <lay-input v-model="fTime" placeholder="请选择上映时间,例: '2023-01-01'"></lay-input>
        </lay-form-item>
        <lay-form-item label="价&ensp;&ensp;&ensp;&ensp;格:">
          <lay-input v-model="fPrice" placeholder="请输入价格"></lay-input>
        </lay-form-item>
        <lay-form-item class="btns">
          <lay-button @click="filmsUpdateVisible = false">取消</lay-button>
          <lay-button type="normal" @click="onUpdate">确认</lay-button>
        </lay-form-item>
      </lay-form>
    </lay-layer>
  </div>
</template>

<script setup>
import {list,deleted,selectById,update,add} from "./api.js";
import {onMounted, onUpdated, reactive, ref} from "vue";
import {layer} from "@layui/layui-vue";

let fId
let fName
let fImage
let oType
let director
let directorImage
let fContent
let fTime
let fPrice

//定义表格的数据
const data = reactive([])
//定义表格的列
const columns = reactive([
  {
    title: '影片ID',
    key: 'fId',
    width:80,
    align:'center',
  },
  {
    title: '影片名',
    key: 'fName',
    align:'center',
  },
  {
    title: '影片图片',
    key: 'fImage',
    align:'center',
  },
  {
    title: '影片类型',
    key: 'oType',
    align:'center',
  },
  {
    title: '导演',
    key: 'director',
    align:'center',
  },
  {
    title: '导演图片',
    key: 'directorImage',
    align:'center',
  },
  {
    title: '影片简介',
    key: 'fContent',
    align:'center',
  },
  {
    title: '上映时间',
    key: 'fTime',
    align:'center',
  },
  {
    title: '基础影片票价',
    key: 'fPrice',
    align:'center',
  },
  {
    title: '操作',
    customSlot:'action',
    align:'center',
  }
])
//查询条件对象
const query = reactive({
  id: ''
})
const page = reactive({
  total: 13,
  limit: 10,
  current: 1,
  showRefresh: true
})
const filmsAddVisible = ref(false);
//影片添加数据对象
const filmsAdd = reactive({
  fId:'',
  fName: '',
  fImage: '',
  oType: '',
  director: '',
  directorImage: '',
  fContent: '',
  fTime: '',
  fPrice:''
})

const filmsUpdateVisible = ref(false)
function reLode(){
  list(query,page.current,page.limit).then(res=>{
    data.length = 0
    data.push(...res.data.list)
    page.current = res.data.current
    page.total = res.data.total
  })

}
function pageChange({current,limit}){
    
  page.current = current;
  page.limit = limit
  reLode()
}
//添加影片
function onAdd(e){
  e.preventDefault()
  e.stopPropagation()
  delete filmsAdd.fId
  add(filmsAdd).then(res=>{
    layer.msg('添加成功');
    filmsAddVisible.value = false
    reLode()
  }).catch(err=>{
    layer.msg("add error")
    console.log(filmsAdd)
  })
}
let arr = reactive([])
function onDelete(films){
  layer.confirm(`是否删除${films.fName}?`,{
    btn:[
        {text:"否",callback(id){
          layer.close(id)
          }},
        {text:"是",
          callback(id){
          deleted(films).then(res=>{
            layer.msg('删除成功')
            layer.close(id)
          }).catch(err=>{
            layer.msg("del err")
          })
            reLode()
          }
        }]
  });
  console.log(arr)
  console.log(films)
  console.log(films.fId)

}
function onUpdate(){
  filmsAdd.fId=fId
  filmsAdd.fName = fName
  filmsAdd.fImage = fImage
  filmsAdd.oType = oType
  filmsAdd.director = director
  filmsAdd.directorImage = directorImage
  filmsAdd.fContent = fContent
  filmsAdd.fTime = fTime
  filmsAdd.fPrice = fPrice
  update(filmsAdd).then(res=>{
    console.log(res)
  }).catch(err=>{
    layer.msg("error")
  })
  filmsUpdateVisible.value = false
  reLode()
}
let data1 = reactive([])
function selectId(){
  console.log(query.id)
  console.log(data)
  data1=[...data]
  console.log(data1)
  for (let i of data1){
    arrDel(data,(i)=>i.fId.toString()!==query.id)
  }
  console.log(data)
}
function arrDel(arr,func) {
  arr.forEach((item,i)=>{
    if (func(item)){
      arr.splice(i,1)
    }
  })
}

function deletes(){

}
onMounted(reLode)
onUpdated(reLode)
// function select(){
//   list1().then(res=>{
//     data.length = 0
//     data.push(...res.data.list)
//   })
// }


</script>

<style scoped lang="scss">
.btns{
  text-align: center;
}
</style>