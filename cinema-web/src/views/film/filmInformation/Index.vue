<template>
  <div id="detailContainer" class="slide-enter-active">
    <Header title="影片详情">
      <i class="iconfont icon-right"></i>
    </Header>
    <div id="content" class="contentDetail">
      <div class="detail_list">
        <div class="detail_list_bg"></div>
        <div class="detail_list_bg"></div>
        <div class="detail_list_filter"></div>
        <div class="detail_list_content">
          <div class="detail_list_img">
            <img />
          </div>
          <div class="detail_list_info">
            <h2>电影名称</h2>
            <p>英文名</p>
            <p>电影类型</p>
            <p>所属国家/电影时长</p>
            <p>上映时间</p>
          </div>
        </div>
      </div>
      <div class="detail_intro">
        <h3>剧情简介</h3>
        <p>***********************************</p>
      </div>
      <div class="detail_player swiper-container">
        <ul class="swiper-wrapper">
          <li class="swiper-slide">
            <div>
              <img src="" />
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>

</script>
<style lang="scss" scoped>
#detailContainer.slide-enter-active {
  animation: 0.3s slideMove;
}
@keyframes slideMove {
  0% {
    transform: translateX(100%);
  }
  100% {
    transform: translateX(0);
  }
}
#detailContainer {
  position: absolute;
  left: 0;
  top: 0;
  z-index: 100;
  width: 100%;
  min-height: 100%;
  background: white;
  #content .detail_list {
    height: 200px;
    width: 100%;
    position: relative;
    overflow: hidden;
    .detail_list_bg {
      width: 100%;
      height: 100%;
      background:  0 40%;
      //   background: url(/images/movie_1.jpg) 0 40%;
      filter: blur(20px);
      background-size: cover;
      position: absolute;
      left: 0;
      top: 0;
    }
    .detail_list_filter {
      width: 100%;
      height: 100%;
      position: absolute;
      background-color: #40454d;
      opacity: 0.55;
      position: absolute;
      left: 0;
      top: 0;
      z-index: 1;
    }
    .detail_list_content {
      display: flex;
      width: 100%;
      height: 100%;
      position: absolute;
      left: 0;
      top: 0;
      z-index: 2;
      .detail_list_img {
        width: 108px;
        height: 150px;
        border: solid 1px #f0f2f3;
        margin: 20px;
        img {
          width: 100%;
          height: 100%;
        }
      }
      .detail_list_info {
        margin-top: 20px;
        h2 {
          font-size: 20px;
          color: white;
          font-weight: normal;
          line-height: 40px;
        }
        p {
          color: white;
          line-height: 20px;
          font-size: 14px;
          color: #ccc;
        }
      }
    }
  }
}
#content .detail_intro {
  padding: 10px;
}
#content .detail_player {
  margin: 20px;
}
.detail_player .swiper-slide {
  width: 70px;
  margin-right: 20px;
  text-align: center;
  font-size: 14px;
}
.detail_player .swiper-slide img {
  width: 100%;
  height:90px;
  margin-bottom: 5px;
}
.detail_player .swiper-slide p:nth-of-type(2) {
  color: #999;
  #content .detail_intro {
    padding: 10px;
  }
  #content .detail_player {
    margin: 20px;
    .swiper-slide {
      width: 70px;
      margin-right: 20px;
      text-align: center;
      font-size: 14px;
      img {
        width: 100%;
        height: 88px;
        margin-bottom: 5px;
      }
    }
  }
}
</style>
