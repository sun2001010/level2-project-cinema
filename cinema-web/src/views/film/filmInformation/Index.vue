<template>
  <div style="">
    <!-- <div> -->
    <loading v-if="true"></loading>
    <div v-if="true">
      <pageTitle :showBack="true" class="pageTitle">猫眼电影</pageTitle>
      <div class="movie-header">
        <div class="movie-img">
          <img src="./images/film/虹猫蓝兔七侠传.jpg" alt="" @click="">
        </div>
        <ul class="movie-info">
          <li class="name">虹猫蓝兔七侠传</li>
          <li class="enm">动画</li>
          <li v-if="true">观众评 <span>{{2.5}}</span></li>
          <li >暂无评分</li>
          <li class="score" v-if="true"><span>{{3}}</span>想看</li>
          <li>{{4}}</li>
          <li>{{5}}</li>
          <li>{{6}}/{{7}}分钟</li>
        </ul>
      </div>
      <div class="btn-group">
        <button class="buy-btn" @click=""><i class="fa-heart"  ></i> 想看</button>
        <button class="buy-btn">特惠购票</button>
      </div>

      <!-- 查看全部介绍开关 -->
      <div class="movie-intro">
        <div class="header">
          <span>简介</span>
          <span >{{ 8 }}</span>
        </div>
        <p >{{ 9 }}</p>
      </div>
      <!-- 播放电影宣传片 -->
      <div class="play-vd" v-if="true" @click="">
        <video controls autoplay width="100%" src=""></video>
      </div>
    </div>
  </div>
</template>

<script>
</script>

<style lang="scss" scoped>

.pageTitle {
  position: fixed;
  left: 0;
  top: 0;
}

.movie-header {
  include{
    justify-content: left;
    align-items: center;
  }

  height: 165px;
  width: 90%;
  padding: 15px;
  padding: 15px;
  margin-top:51px;
  // background-color: rgba(231, 223, 223, 0.4);
  overflow: hidden;

  >.bg-second {
    position: absolute;
    width: 100%;
    height: 165px;
    background-color: rgba(85, 85, 85, .7);
    margin-left: -15px;
    z-index: -1;
  }

  >.bg-last {
    position: absolute;
    width: 100%;
    height: 165px;
    background: radial-gradient(60% 55%, rgb(0, 0, 0), rgb(16, 80, 85));
    margin-left: -15px;
    z-index: -2;
  }

  >.movie-info {
    height: 135px;
    font-size: 12.5px;
    color: #000000;
    list-style: none;
    padding-left: 0px;
    padding-top: 10px;

    >.name {
      font-size: 16px;
      color: #000000;
      font-weight: bold;
    }

    >.enm {
      font-size: 11px;
      color: #000000;
      margin-top: 6px;
    }
  }

  >.movie-img {
    position: relative;

    img {
      width: 96.5px;
      height: 135px;
    }

    &:after {
      content: "▶";
      color: #000000;
      font-size: 14px;
      width: 22px;
      height: 22px;
      border-radius: 50%;
      border: 1px solid #000000;
      position: absolute;
      right: 12px;
      bottom: 8px;
      line-height: 22px;
      text-align: center;
      background-color: #333;
      opacity: 0.8;
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
  color: #000000;
  text-align: center;
  border-radius: 4px;
  border: none;
  background-color: #e54847;
}

.movie-intro {
  padding: 3px 16px;
  width: calc(100% - 30px);

  >p {
    // height: 100px;
    font-size: 15px;
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
  }
}

.overflow {
  max-height: 60px;
}

.play-vd {
  position: fixed;
  height: 100%;
  width: 90%;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0);

  >video {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
  }
}
</style>