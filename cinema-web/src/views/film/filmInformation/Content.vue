<template>
  <div class="content">
    <div>
      <div class="movie-intro">
        <div class="header">
          <span>简介</span>
        </div>
        <p>公元397年，风景秀丽的张家界，以黑心虎为首的魔教放火烧山，荼毒生灵，欲抓森林之灵兽玉兽麒麟，妄图借喝麒麟的热血以增强内力，称霸武林。</p>
        </div>
      <div class="dy">
        &ensp;&ensp;<span>导演</span>
        <div class="dy-img">
          &ensp;&ensp;<img src="./images/director/久熙.png"> <br>
          &ensp;&ensp;<span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;久熙&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
        </div>
      </div >
      <br>
      <div class="yy">
        &ensp;&ensp;<span>演员</span>
        <div class="yy-img">
          &ensp;&ensp;<img src="./images/player/虹猫.jpg">
          <img src="./images/player/蓝兔.jpg">
          <img src="./images/player/黑小虎.jpg"><br>
          &ensp;&ensp;<span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;虹猫&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
          <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;蓝兔&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
          <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;黑小虎&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;</span>
        </div>
      </div>
    </div><br><br>
    <div class="comments">
      &ensp;&ensp;<span>影片评论</span><br>
      <comments></comments>
    </div>
  </div>
</template>

<script setup>
import Comments from "./comments.vue";


</script>

<style lang="scss" scoped>
.content {
  width: 80%;
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