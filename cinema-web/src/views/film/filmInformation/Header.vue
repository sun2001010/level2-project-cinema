<template>
  <div class="movie-header">
    <div class="movie-img">
      <img :src=url+filmInfos.fImage>
    </div>
    <ul class="movie-info">
      <li class="name">{{ filmInfos.fName }}</li>
      <li class="enm">{{ filmInfos.oType }}</li><br>
      <li style="font-size:24px;color:#ff0000" v-if="score">评分：<span>{{ score }}</span></li>
      <li style="font-size:24px;color:#ff0000" v-if="!score">暂无评分</li><br>
      <li class="score"><span>{{ colNum }}</span> 想看</li><br>
      <li>导演：{{ filmInfos.director }}</li><br>
      <li>演员：虹猫 / 蓝兔 / 黑小虎</li><br>
      <li>中国大陆 / {{ filmInfos.fTime }}</li>

    </ul>
  </div>
  <div class="btn-group">
    <div>
      <button class="buy-btn" @click=""><i class="fa-heart" ></i> 想看</button>
      <button class="buy-btn" @click="selectCinema">特惠购票</button>
    </div>
    <div>
      <lay-rate v-model="all2" :length="length"></lay-rate>
      <button class="score-btn" @click="scoreAdd">评分</button>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import router from "../../../config/router.js";
import {layer} from "@layui/layui-vue";
let filmInfos=ref()

const all2 = ref(4);
const length = ref(10)


filmInfos = JSON.parse(sessionStorage.getItem('filmInfo'));
const colNum = JSON.parse(sessionStorage.getItem('collectNum'));
const score = JSON.parse(sessionStorage.getItem('score'));


const url="src/views/film/filmInformation/images/film/"
console.log(filmInfos)

function selectCinema() {
  router.push("/cinema")
}

function scoreAdd(){
  scoreAdd(score).then(res=>{

  }).catch(err=>{
    layer.msg("错误")
  })
}


</script>

<style scoped lang="scss">
@import "./css/movieLIst.css";
@import "./scss/main.scss";

.pageTitle {
  position: fixed;
  left: 0;
  top: 0;
}

.movie-header {
  background: url("./images/bg.jpg");
@include flex-box(columns) {
  justify-content: left;
  align-items: center;
}
height: 420px;
width: 98%;
padding: 15px;
overflow: hidden;

> .bg-second {
  position: absolute;
  width: 100%;
  height: 420px;
  background-color: rgba(136, 136, 136, 0.53);
  margin-left: -15px;
  z-index: -1;
}

> .bg-last {
  position: absolute;
  width: 100%;
  height: 165px;
  background: radial-gradient(60% 55%, rgb(184, 194, 189), rgb(16, 80, 85));
  margin-left: -15px;
  z-index: -2;
}

> .movie-info {
  height: 420px;
  font-size: 24px;
  color: #ffffff;
  list-style: none;
  padding-left: 10px;
  padding-top: 10px;

> .name {
  font-size: 48px;
  color: #ffffff;
  font-weight: bold;
}

> .enm {
  font-size: 24px;
  color: #ffffff;
  margin-top: 6px;
}

}


> .movie-img {
  position: relative;

img {
  width: 320px;
  height: 420px;
}
}
}


.btn-group{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.buy-btn {
// width: calc(100% - 30px);
  width: 130px;
  margin: 10px 15px;
  padding: 9px;
  font-size: 16px;
  color: #fff;
  text-align: center;
  border-radius: 4px;
  border: none;
  background-color: #e54847;
}
.score-btn{
  width: 80px;
  margin: 10px 15px;
  padding: 9px;
  font-size: 16px;
  color: #fff;
  text-align: center;
  border-radius: 4px;
  border: none;
  background-color: #ffcc00;
  font-weight: bold;
}

.movie-intro {

  padding: 3px 16px;
  width: calc(100% - 30px);

>p {
// height: 100px;
  font-size: 18px;
  color: #000000;
  overflow: hidden;
  margin-bottom: 20px;
}

>.btn {
  width: 16px;
}

>.header {
  display: flex;
  justify-content: space-between;
  color: #000000;
  font-weight: bold;
  font-size: 18px;

.header-img{
  height: 165px;
  width: 90%;
}
}
}

.dy{
  font-size: 18px;
  font-weight: bold;
>.dy-img {
img {
  width: 160px;
  height: 215px;
}
}
}

.yy{
  font-size: 18px;
  font-weight: bold;
  display: inline-block;
>.yy-img {
  position: relative;

img {
  width: 160px;
  height: 215px;
  padding-right: 30px;
}
span {
  padding-right: 30px;
}
}
}

.overflow {
  max-height: 60px;
}

</style>