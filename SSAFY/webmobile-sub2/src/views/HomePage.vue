<template>
  <div id="Entrance">
    <!-- 1. 메인 이미지 배너 -->
    <ImgBanner
      imgSrc="https://images.unsplash.com/photo-1508157942875-586a83457569?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1052&q=80"
    >
      <div style="line-height:10vh;" slot="text1">
        <span class="banner_1">空手來空手去</span>
        <p class="banner_1_2">(공수래공수거)</p>
      </div>
      <div style="line-height: 6vh;" slot="text2">
        <span class="banner_2">
          <p>모든 사람은 빈 손으로 태어나 빈 손으로 떠난다.</p>
          <p>살아가면서 아무리 재물을 탐하고</p>
          <p>권력을 좇아도 결국 모두 부질없으므로,</p>
          <p>아등바등 욕심부리며 살 필요가 없다.</p>
        </span>
      </div>
      
    </ImgBanner>
    <!-- 2. 미생 -->
    <div id="AboutUs" style="margin-bottom: 110px;"></div>
    <AboutUs></AboutUs>

    <!-- 3. 걸작 -->
    <div id="MasterPiece" style="margin-bottom: 80px;"></div>
    <MasterPiece></MasterPiece>
  </div>
</template>

<script>
const axios = require("axios");
import ImgBanner from "../components/ImgBanner";
import AboutUs from "../components/AboutUs";
import MasterPiece from "../components/MasterPiece";
import PortfolioList from "../components/PortfolioList";
import PostList from "../components/PostList";
import RepositoryList from "../components/RepositoryList";
import Graph from "../components/RepositoryGraph";
export default {
  name: "HomePage",
  mounted(){
    this.ax()
  },
  components: {
    ImgBanner,
    AboutUs,
    MasterPiece,
    PortfolioList,
    PostList,
    RepositoryList,
    // Graph
  },

  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    kotoen: function(text) {
      axios({
        method : 'post',
        url : 'https://translation.googleapis.com/language/translate/v2',
        params  :{
          source : 'ko',
          target : 'en',
          q : text,
          key : 'AIzaSyAM3pZMOpmnKyKnhorj1s-LGK0hBe5gQbA',
        },
      }).then(res => {
        document.getElementById("ext").innerText = res.data.data.translations[0].translatedText;
      })
    },
    entoko: function(text) {
      axios({
        method : 'post',
        url : 'https://translation.googleapis.com/language/translate/v2',
        params  :{
          source : 'en',
          target : 'ko',
          q : text,
          key : 'AIzaSyAM3pZMOpmnKyKnhorj1s-LGK0hBe5gQbA',
        },
      }).then(res => {
        document.getElementById("ext").innerText = res.data.data.translations[0].translatedText;
      })
    },
    ax : function()  {
      axios.get("https://us-central1-webmobile-sub2-639ef.cloudfunctions.net/addMessage?text='홈페이지방문'")
    }
  }
};


</script>


<style>
.body {
  font-family: "Song Myung", serif;
}
.banner_1 {
  font-family: "ZCOOL QingKe HuangYou", cursive;
  white-space: nowrap;
}
.banner_1_2 {
  font-family: "Song Myung", serif;
  white-space: nowrap;
  font-size: 35px;
}
.banner_2 {
  font-family: "Song Myung", serif;
  white-space: nowrap;
}
</style>
