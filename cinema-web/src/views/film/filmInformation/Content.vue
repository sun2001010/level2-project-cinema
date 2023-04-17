<template>
  <div class="content">
    <div>
      <div class="movie-intro">
        <div class="header">
          <span>简介</span>
        </div>
        <p>{{filmInfo.fContent}}</p>
        </div>
      <div class="dy">
        <span>导演</span>
        <div class="dy-img">
          <img src="./images/director/{{filmInfo.directorImage}}"> <br>
          <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;{{filmInfo.director}}&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
        </div>
      </div >
      <br>
      <div class="yy">
        <span>演员</span>
        <div class="yy-img">
          <img src="./images/player/虹猫.jpg">
          <img src="./images/player/蓝兔.jpg">
          <img src="./images/player/黑小虎.jpg"><br>
          <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;虹猫&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
          <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;蓝兔&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
          <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;黑小虎&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
        </div>
      </div>
    </div><br><br>
    <div class="comments">
      <span>影片评论</span><br>
      <comments></comments>
    </div>
  </div>
</template>

<script setup>
import Comments from "./comments.vue";
import {selectByPrimaryKey} from "./api.js";

const item = JSON.parse(sessionStorage.getItem('FilmName'));
const filmInfo = JSON.parse(sessionStorage.getItem('filmInfo'));



</script>

<style lang="scss" scoped>
.content {
  width: 80%;
}
.movie-intro {


  >p {
    // height: 100px;
    font-size: 18px;
    color: #000000;
    overflow: hidden;
    margin-bottom: 20px;
    font-weight: normal;
  }

  >.btn {
    width: 16px;
  }

  >.header {
    display: flex;
    justify-content: space-between;
    color: #000000;
    font-weight: bold;
    font-size: 24px;

    .header-img{
      height: 165px;
      width: 90%;
    }
  }
}

.dy{
  font-size: 24px;
  font-weight: bold;
  >.dy-img {
    img {
      width: 160px;
      height: 215px;
    }
    span{
      font-size: 18px;
      font-weight: bold;
    }
  }
}

.yy{
  font-size: 24px;
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
      font-size: 18px;
      font-weight: bold;
      padding-right: 30px;
    }
  }
}
.content{
  font-size: 24px;
  font-weight: bold;
}
</style>