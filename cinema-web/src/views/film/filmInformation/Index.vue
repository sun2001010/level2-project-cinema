 <template>
  <div >
    <!-- <div> -->
    <loading v-if="false"></loading>
    <div v-if="true">
      <div class="movie-header">
        <div class="movie-img">
          <img src="./images/film/虹猫蓝兔七侠传.jpg">
        </div>
        <ul class="movie-info">
          <li class="name">虹猫蓝兔七侠传</li>
          <li class="enm">冒险</li><br>
          <li style="font-size:24px;color:#ff0000" v-if="true">评分：<span>9.9</span></li>
          <li style="font-size:24px;color:#ff0000" v-if="false">暂无评分</li><br>
          <li class="score"><span>99</span> 想看</li><br>
          <li>导演：久熙</li><br>
          <li>演员：虹猫 / 蓝兔 / 黑小虎</li><br>
          <li>中国大陆 / 17分钟</li><br>
        </ul>
      </div>
      <div class="btn-group">
        <button class="buy-btn" @click=""><i class="fa-heart" ></i> 想看</button>
        <button class="buy-btn">特惠购票</button>
      </div>

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
          &ensp;&ensp;<span>&ensp;&ensp;&ensp;&ensp;久熙&ensp;&ensp;&ensp;&ensp;</span>
        </div>
      </div >
      <br>
      <div class="yy">
        &ensp;&ensp;<span>演员</span>
        <div class="yy-img">
          &ensp;&ensp;<img src="./images/player/虹猫.jpg">
          <img src="./images/player/蓝兔.jpg">
          <img src="./images/player/黑小虎.jpg"><br>
          &ensp;&ensp;<span>&ensp;&ensp;&ensp;&ensp;虹猫&ensp;&ensp;&ensp;&ensp;</span>
          <span>&ensp;&ensp;&ensp;&ensp;蓝兔&ensp;&ensp;&ensp;&ensp;</span>
          <span>&ensp;&ensp;&ensp;&ensp;黑小虎&ensp;&ensp;&ensp;&ensp;</span>
        </div>
      </div>
      <!-- 播放电影宣传片 -->
      <div class="play-vd" v-if="false">
        <video controls autoplay width="100%" src="./images/film/虹猫蓝兔七侠传.jpg"></video>
      </div>
    </div>
    <div class="contain">
      <lay-button type="normal">影院列表</lay-button>
      <lay-table :columns="columns" :data-source="dataSource">
      </lay-table>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue';
import {findAll} from "../../film/filmInformation/api.js";
import {layer} from "@layui/layui-vue";
// var item = sessionStorage.getItem("FilmName");
// console.log(item)

const columns=[
  {title: 'ID',key:'fPId'},
  {title: '电影ID',key:'filmid'},
  {title: '电影名',key: 'filmname'},
  {title: '演员ID',key:'playerid'},
  {title: '演员名',key: 'playername'}
]
const dataSource=reactive([])
const fpName=ref([])
onMounted(()=>{
  findAll().then(res=>{
    dataSource.push(...res.data.list)
    for (let i=0;i<res.data.list.length;i++){
      fpName[i]=res.data.list[i].filmname
    }
  }).catch(error=>{
    layer.msg("错误")
  })
  console.log(fpName)
})
</script>

<style lang="scss" scoped>
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

  >.bg-second {
    position: absolute;
    width: 100%;
    height: 420px;
    background-color: rgba(136, 136, 136, 0.53);
    margin-left: -15px;
    z-index: -1;
  }

  >.bg-last {
    position: absolute;
    width: 100%;
    height: 165px;
    background: radial-gradient(60% 55%, rgb(184, 194, 189), rgb(16, 80, 85));
    margin-left: -15px;
    z-index: -2;
  }

  >.movie-info {
    height: 420px;
    font-size: 24px;
    color: #ffffff;
    list-style: none;
    padding-left: 10px;
    padding-top: 10px;

    >.name {
      font-size: 48px;
      color: #ffffff;
      font-weight: bold;
    }

    >.enm {
      font-size: 24px;
      color: #ffffff;
      margin-top: 6px;
    }

  }

  >.movie-img {
    position: relative;

    img {
      width: 320px;
      height: 420px;
    }

    /*&:after {
      content: "▶";
      color: rgb(255, 255, 255);
      font-size: 20px;
      width: 35px;
      height: 35px;
      border-radius: 50%;
      border: 1px solid #ffffff;
      position: absolute;
      right: 12px;
      bottom: 8px;
      line-height: 33px;
      text-align: center;
      background-color: #333;
      opacity: 0.8;
    }*/
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
    font-size: 16px;

    .header-img{
      height: 165px;
      width: 90%;
    }
  }
}

.dy{
  font-size: 16px;
  font-weight: bold;
  >.dy-img {
    img {
      width: 109px;
      height: 145px;
    }
  }
}

.yy{
  font-size: 16px;
  font-weight: bold;
  display: inline-block;
  >.yy-img {
    position: relative;

    img {
      width: 96.5px;
      height: 135px;
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

.play-vd {
  position: fixed;
  height: 100%;
  width: 90%;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, .7);

  >video {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
  }
}
</style>