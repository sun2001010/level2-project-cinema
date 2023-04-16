<template>
  <div class="right">
    <div class="right-yg">
      <p>预告片</p><br>
      <div class="yg-img">
        <img src="./images/film/虹猫蓝兔七侠传.jpg">
        <img src="./images/film/虹猫蓝兔勇者归来.jpg">
        <img src="./images/film/虹猫仗剑走天涯.jpg">
      </div>
    </div>
    <div class="right-love">
      <p>猜你喜欢</p><br>
      <div class="love-img">
        <img src="./images/film/虹猫蓝兔七侠传.jpg">
        <img src="./images/film/虹猫蓝兔勇者归来.jpg"><br>
        <img src="./images/film/虹猫仗剑走天涯.jpg">
      </div>
    </div>
  </div>
</template>

<script>

</script>

<style scoped lang="scss">
.right{
  font-size: 18px;
  font-weight: bold;
  display: inline-block;
  padding-top: 50px;
  .right-yg{
    >.yg-img{

      img{
        height: 200px;
        width: 300px;
        padding-bottom: 25px;
        padding-right: 20px;
      }
    }
  }
  .right-love{
    >.love-img{
      white-space:nowrap;
      img{
        height: 200px;
        width: 150px;
        padding-bottom: 25px;
        padding-right: 20px;

      }
    }
  }
}
</style>