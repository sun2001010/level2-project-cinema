<template>
  <div style="letter-spacing: 1px">
    <div class="head">
      <!-- 评论区域如果当前用户有头像则显示头像,没有则显示默认头像 -->
      <img v-if="picture" :src="`http://` + picture" alt="" />
      <img v-else src="./images/默认头像.jpg" alt="" />
      <!-- 评论框 -->
      <input
          type="text"
          placeholder="请输入评论 . . ."
          ref="input"
          @focus="obtain"
          @blur="lose"
          v-model="firstComments"
          @keyup.enter="sumbit"
      />
      <!-- 发布按钮 -->
      <button @click="sumbit">发表评论</button>
    </div>
    <div class="content">
      <!-- 一级评论 -->
      <div class="first" v-for="(item, index) in comments" :key="index">
        <a href="javascript:;" class="first-img">
          <!-- 如果当前用户有头像则显示头像,没有则显示默认头像 -->
          <!-- <img :src="`http://` + item.picture" /> -->
          <img v-if="item.picture" :src="`http://` + item.picture" alt="" />
          <img v-else src="./images/默认头像.jpg" alt="" />
        </a>
        <div class="first-content">
          <!-- 一级评论用户昵称 -->
          <h3 class="first-username">{{ item.username }}</h3>
          <!-- 一级评论发布时间 -->
          <span class="first-time">{{ item.date }}</span>
          <!-- 一级评论评论内容 -->
          <p class="first-comment">
            {{ item.content }}
          </p>
          <!-- 一级评论评论点赞 -->
          <div
              class="first-right"
              @mouseleave="delete_leave(item)"
              @mouseover="delete_after(item)"
          >
            <span
                class="delete"
                v-if="item.delete"
                @click="comment_delete(item, 0)"
            >删除</span
            >
            <span class="comments" @click="comment_input(item)">评论</span>
            <!-- 一级评论点赞数 -->
            <span
                class="praise"
                :class="item.like"
                @click="comment_like(item, 0)"
            >
              <span v-if="item.numbers">{{ item.numbers }}</span>
            </span>
          </div>
        </div>
      </div>
      <!-- 暂无评论的空状态 -->
      <el-empty description="暂无评论" v-if="show"></el-empty>
    </div>
    <!-- 页码 -->
    <div style="text-align: center; margin-top: 15px" v-if="showPage">
      <el-pagination
          background
          layout="prev, pager, next"
          :total="totalNumber * 10"
          @current-change="currentPage"
          :current-page="page"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
name: "comments"
}
</script>

<style scoped lang="scss">
/* 评论框 */
.head {
  background-color: rgb(248, 248, 248);
  position: relative;
  height: 75px;
  border-radius: 5px;
}
.head img {
  width: 55px;
  height: 55px;
  border-radius: 50%;
  position: absolute;
  top: 10px;
  left: 13px;
}
/* 评论框 */
.head input {
  position: absolute;
  top: 13px;
  left: 80px;
  height: 45px;
  border-radius: 5px;
  outline: none;
  width: 65%;
  font-size: 20px;
  padding: 0 20px;
  border: 2px solid #f8f8f8;
}
/* 发布评论按钮 */
.head button {
  position: absolute;
  top: 13px;
  right: 20px;
  width: 120px;
  height: 48px;
  border: 0;
  border-radius: 5px;
  font-size: 20px;
  font-weight: 500;
  color: #fff;
  background-color: rgb(118, 211, 248);
  cursor: pointer;
  letter-spacing: 2px;
}
/* 鼠标经过字体加粗 */
.head button:hover {
  font-weight: 600;
}

/* 评论内容区域 */
.content .first {
  display: flex;
  position: relative;
  padding: 10px 10px 0px 0;
}
.first .first-img {
  flex: 1;
  text-align: center;
}
.first img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
.first-username,
.second-username {
  color: #504f4f;
}
.first-content {
  flex: 9;
}
.first-time,
.second-time {
  color: #767575;
}
.first-comment,
.second-comment {
  margin-top: 5px;
}
/* 右边点赞和评论 */
.first-right,
.second-right {
  position: absolute;
  right: 1%;
  top: 10px;
}
.first-right span,
.second-right span {
  margin-right: 20px;
  cursor: pointer;
}
/* 删除评论 */
.delete:hover {
  color: red;
}
/* 评论字体图标 */
.comments::before {
  /* 想使用的icon的十六制编码，去掉&#x之后的 */
  content: "\e8b9";
  /* 必须加 */
  font-family: "iconfont";
  margin-right: 4px;
  font-size: 16px;
}
/* 点赞字体图标 */
.praise::before {
  /* 想使用的icon的十六制编码，去掉&#x之后的 */
  content: "\ec7f";
  /* 必须加 */
  font-family: "iconfont";
  margin-right: 4px;
  font-size: 19px;
}

/* 评论框 */
.reply-comment {
  margin: 10px 0 0 0;
}
.reply-comment input {
  height: 40px;
  border-radius: 5px;
  outline: none;
  width: 70%;
  font-size: 18px;
  padding: 0 20px;
  /* border: 2px solid #f8f8f8; */
  border: 2px solid skyblue;
}
/* 发布评论按钮 */
.reply-comment button {
  width: 15%;
  height: 43px;
  border: 0;
  border-radius: 5px;
  font-size: 18px;
  font-weight: 500;
  color: #fff;
  background-color: rgb(118, 211, 248);
  cursor: pointer;
  letter-spacing: 2px;
  margin-left: 20px;
}
/* 鼠标经过字体加粗 */
.reply-comment button:hover {
  font-weight: 600;
}
/* 评论点赞颜色 */
.comment-like {
  color: red;
}
</style>