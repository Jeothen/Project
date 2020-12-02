<template>
  <v-container class="mx-auto">
    <v-row>
      <v-col cols="12" md="7">
        <div>
          <p id="home_plot1"><strong id="title">Cup of tea</strong>란?</p>
          <p id="home_plot2">
            커피 원두 고르는게 어려우신 분들<br>
            <strong>cup of tea</strong>를 통해 매일 색다른 원두를 체험해보세요<br>
            저희를 구독하시면 매달 새로운 원두를 제공해드립니다.<br>
          </p>
        </div>
      </v-col>
      <v-col cols="6" md="5">
        <img id="home_img" src="https://media.tumblr.com/tumblr_md75laJebh1r5kl7zo1_500.gif"/>
      </v-col>
    </v-row>
    <!-- 소개글! -->
    <span id="section"><br></span>
    <p id="concept_title">Effect</p>
    <v-row>
      <v-col cols="12" md="7">
        <img id="intro_img" src="https://i.ytimg.com/vi/P-WAvo7OgEc/maxresdefault.jpg"/>
      </v-col>
      <v-col cols="12" md="5">
        <span>01</span><h1>숙취 해소</h1><p>"해장에는 아메키라노"</p>
        <p id="description">커피와 같은 카페인이 함유된 음료를 마시면,
          간 기능을 활발하게 해 아세트알데히드 분해를 빠르게 하고
          신장의 기능도 활발하게 해서 배설을 촉진시킨다고 합니다.</p>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="5">
        <span>02</span><h1>항산화 효과</h1><p>"탄력있는 피부"</p>
        <p id="description">커피의 한 성분인 클로로겐산은 폴리페놀 화합물의 일종으로 항산화 작용을 통해 탄력 있고 매끄러운 피부로 가꿔주며,
          인체 내에서 과산화지질과 콜레스테롤 생성을 억제합니다.</p>
      </v-col>
      <v-col cols="12" md="7" style="text-align:right">
        <img id="intro_img" src="http://img.ezmember.co.kr/cache/board/2015/11/30/f4fc3c4ad8d9b04098e15f69b1e6eaa5.JPEG"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="7">
        <img id="intro_img" src="https://post-phinf.pstatic.net/MjAxODEwMDFfNjkg/MDAxNTM4MzU2Mzg5Njg2.flON8C3HTt8y4hzLBVuimkvn3tOFAkJSMwkocrydrb0g.XzewGE2d1qN05Eooy9gNeiUBvSa8N9045eE_1hpthxAg.JPEG/%EC%BB%A4%ED%94%BC%ED%96%A5%EA%B8%B01.jpg?type=w1200"/>
      </v-col>
      <v-col cols="12" md="5">
        <span>03</span><h1>스트레스 해소</h1><p>"하루의 끝은 커피향으로"</p>
        <p id="description">커피향을 맡는 것이 마시는 것보다 더 스트레스 해소 효과가 빠르다고 하네요.
          스트레스를 많이 받는 사람의 경우,
          볶은 커피 원두를 작은 주머니에 넣고 향을 맡으면 기분전환과 스트레스를 줄이는 데 도움이 된답니다.
          </p>
      </v-col>
    </v-row>
    <!-- 커피의 효능 -->
    <span id="section"><br></span>
    <p id="concept_title">Month</p>
    <v-row>
      <v-card id="month_card">
        <div>
          <div class="d-flex justify-space-between">
            <v-card class="ma-2"
              v-for="(item, i) in monthItem" v-bind:key="i"
              max-width="350">
              <v-img
                class="white--text align-end"
                height="200px"
                :src="item.image">
                <v-card-title>{{item.name}} {{BlendPercents[i]}}%</v-card-title>
              </v-img>

              <v-card-subtitle class="pb-0">{{item.country}}</v-card-subtitle>
              <v-card-text class="text--primary">
                <p style="font-family: 'Noto Serif KR', serif;">{{item.content}}</p>
              </v-card-text>
            </v-card>
          </div>
          <br>
          <v-card>
            <v-card-title id="month_content">
            {{ this.monthBlend }}
            </v-card-title>
          </v-card>
        </div>
      </v-card>
    </v-row>
    <!-- 이 달의 원두 -->
    <span id="section"><br></span>
    <p id="concept_title">Service</p>
    <v-row>
      <v-col>
        <v-card id="how-to" class="mx-auto d-flex justify-space-between">
          <v-row>
          <v-col style="text-align:center">
            <img id="how-to-image" src="../../../public/img/subscribe.png"/>
            <p id="how-to-title">Cup of tea를 <strong>구독</strong>하시면</p>
          </v-col>
          <i class="fas fa-caret-right fa-4x my-auto"></i>
          <v-col style="text-align:center">
            <img id="how-to-image" src="../../../public/img/delivery.png"/>
            <p id="how-to-title">원하는 장소에 <strong>배달</strong>해드립니다.</p>
          </v-col>
          <i class="fas fa-caret-right fa-4x my-auto"></i>
          <v-col style="text-align:center">
            <img id="how-to-image" src="../../../public/img/bean.png"/>
            <p id="how-to-title">색다른 <strong>원두</strong>를 집에서 즐겨보세요.</p>
          </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <!-- 이용 안내! -->
    <span id="section"><br></span>
    <v-row>
      <v-col>
        <v-card id="FAQ" class="mx-auto" data-aos="fade-up" data-aos-delay="150"
              outlined>
          <p id="concept_title">FAQ</p>
          <v-expansion-panels focusable>
            <v-expansion-panel
              v-for="(val,key) in this.FAQs"
              :key="val">
              <v-expansion-panel-header>{{ key }}
                <template v-slot:actions>
                  <v-icon>mdi-chevron-down</v-icon>
                </template>
              </v-expansion-panel-header>
              <v-expansion-panel-content id="description">
              {{ val }}
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-card>
      </v-col>
    </v-row>
    <!-- FAQ! -->
  </v-container>
</template>
<script>
import AOS from 'aos'
import 'aos/dist/aos.css'
import axios from 'axios'
export default {
  data() {
    return {
      FAQs:{"원두를 블렌딩하는 이유는 무엇인가요?":"단일 원두 커피는 계절적 요인 때문에 지속적인 공급이 어렵고, 맛의 호불호가 갈릴 수 있습니다. 반면 블렌드 커피는 맛의 밸런스가 뛰어나면서도, 특정한 맛을 강조할 수 있습니다.",
            "배달비는 별도인가요?":"구독료는 배송비까지 포함된 가격이니 안심하시고 주문하세요!",
            "전에 받은 원두를 이번에도 받고싶어요.":"구독자에 한해서 원하는 원두를 따로 구매하실 수 있습니다!"},
      user:"",
      now_data:"",
      monthBlend:"",
      monthItem:"",
      BlendPercents: [],
    }
  },
  created () {
    AOS.init()
    this.setUser()
    console.log(this.$session.get('id'))
    console.log(this.$session.get('user'))
    this.getDeli()
  },
  methods: {
    setUser () {
      if (this.$session.get('id') == '') {
        this.user = ''
      } else {
        this.user = this.$session.get('user')
      }
    },
    async getDeli() {
      // let __this = this;
      // const id = this.$session.get('id')
      this.now_date = this.$moment().format('YYYYMMDD')
      console.log(this.now_date.substring(0,4))
      console.log(this.now_date.substring(4,6))
      var url = '/api/thismonth/delivery/' + this.now_date.substring(0,4) + '/' + this.now_date.substring(4,6)
      await axios.get(url)
      .then(res => {
        console.log(res.data)
        this.monthBlend = res.data.blend.content
        this.monthItem = res.data.items
        this.BlendPercents = res.data.percents
        console.log(this.monthBlend)
        console.log(this.monthItem)
      })
    },
  }
}
</script>
<style scoped>
  #home_plot1 {
    font-size:4em;
    text-align: left;
  }
  #home_plot2 {
    font-size:1.4rem;
    font-family: 'Gaegu', cursive;
    /* text-align: center; */
  }
  #home_img {
    width: 100%;
  }
  #intro_img {
    width: 80%;
    height: 300px;
  }
  #description {
    font-family: 'Noto Serif KR', serif;
  }
  #how-to {
    width: 1000px;
    background-color: rgb(255, 206, 114);
  }
  #how-to-image {
    width:80%;
  }
  #how-to-title {
    font-size: 15px;
    text-align: center;
  }
  #FAQ {
    width: 1000px;
  }
  #FAQ_value {
    visibility: hidden;
    transition: all 0.5s;
  }
  #title {
    font-family: 'Acme', sans-serif;
  }
  #concept_title {
    font-family: 'Acme', sans-serif;
    text-align: center;
    font-size: 50px;
    text-decoration: underline;
  }
  #section {
    line-height:100px;
  }
  #month_bean {
    text-align: center;
  }
  #month_card {
    background-image: url('../../../public/img/background_month.png');
    background-color: #ffb89880;
    /* background-color: #ffffff; */
    /* opacity: 0.5; */
    background-size: cover;
    width: 100%;
    height: 450px;
  }
  #month_content {
    font-family: 'Noto Serif KR', serif;
    text-align: center;
    /* font-size: 30px; */
  }
</style>
