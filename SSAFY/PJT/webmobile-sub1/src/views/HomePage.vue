<template>
  <div>
    <ImgBanner imgSrc="https://source.unsplash.com/random/1600x900">
      <div style="line-height:1.2em; background-color :#000000db; color:white;" slot="text">Quickview's Blog<br></div>
    </ImgBanner>
    <v-container>
      <!-- About Me -->
      <v-layout>
        <v-flex xs12 >
          <h2 class="headline mb-3 text-center">About me</h2>
        </v-flex>
      </v-layout>
      <v-layout wrap>
        <v-flex xs12 sm12 lg8>
          <template>
            <v-timeline>
              <v-timeline-item v-for="(item, i) in years" :key="i" :color="item.color" small>
              <template v-slot:opposite>
                <span
                :class="`headline font-weight-bold ${item.color}--text`"
                v-text="item.header"
                ></span>
              </template>
              <div class="py-3">
                <h5 :class="`headline font-weight-light mb-3 ${item.color}--text`">{{item.value}}</h5>
              </div>
            </v-timeline-item>
          </v-timeline>
        </template>
      </v-flex>
      <!-- image hidden option -->
      <v-flex lg4 hidden-md-and-down>
        <v-img class="profile" :src="getImgUrl('myIcon.jpg')" aspect-ratio="1.5"/>
      </v-flex>
    </v-layout>


    <!-- Portfolio -->
    <v-layout my-5>
      <v-flex xs12 >
        <h2 class="headline my-5 text-xs-center">Portfolio</h2>
        <a class="btn btn-primary writeBtn" href="/portfolioWrite">작성하기</a>
        <PortfolioList></PortfolioList>
      </v-flex>
    </v-layout>

    <!-- Post -->
    <v-layout my-5>
      <v-flex xs12>
        <h2 class="headline my-5 text-xs-center">Post</h2>
        <PostList :column="2"></PostList>
      </v-flex>
    </v-layout>


    <!-- Github -->
    <v-layout my-5>
      <v-flex xs12>
        <section id="myProjects">
        </section>
        <h2 class="headline my-5 text-xs-center">Project</h2>
        <RepositoryList></RepositoryList>
      </v-flex>
    </v-layout>
  </v-container>
</div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import PortfolioList from '../components/PortfolioList'
import PostList from '../components/PostList'
import RepositoryList from '../components/RepositoryList'

export default {
  name: 'HomePage',
  components: {
    ImgBanner,
    PortfolioList,
    PostList,
    RepositoryList
  },
  methods: {
    getImgUrl(img) {
      return require('../assets/img/' + img)
    }
  },
  data: () => ({
    years: [
      {
        color: 'cyan',
        header: '오명현',
        value: '94.10.08'
      },
      {
        color: 'green',
        header: '한남대학교',
        value: '멀티미디어 공학 전공'

      },
      {
        color: 'pink',
        header: '주요 스택',
        value: 'Java, HTML, CSS, JavaScript'

      }
    ]
  })
}
</script>

<style lang="css" scoped>
.header-text{
  z-index: 99;
  background-color: #black;
}

.profile{
  width: 400px;
  height: 400px;
  margin-left: 3em;
}

.writeBtn{
  float: right;
  margin-right: 20px;
}
</style>
