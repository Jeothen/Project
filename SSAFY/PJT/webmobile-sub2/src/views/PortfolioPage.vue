<template>
  <div>
    <v-img
      :src="photoImage"
      aspect-ratio="1.7"
      min-height="720"
      max-height="700"
    >
      <v-layout align-center justify-center row fill-height>
        <v-flex xs6 md12 text-xs-center>
          <span
            class="text-shadow display-3 font-weight-bold"
          >
            <slot name="text1" />
            <div style="line-height:1.2em;font-size:1.2em;" @click="changeImage" slot="text1">Portfolio</div>
          </span>
        </v-flex>
      </v-layout>
    </v-img>
    <v-container>

      <!-- Portfolio -->
      <v-layout>
        <v-flex xs12>
          <PortfolioList :limits="6" :load-more="true"></PortfolioList>
        </v-flex>
      </v-layout>

    </v-container>
  </div>
</template>

<script>
const axios = require('axios')
import ImgBanner from '../components/ImgBanner'
import PortfolioList from '../components/PortfolioList'

export default {
	name: 'PortfolioPage',
  data () {
       return {
       photoImage: 'https://source.unsplash.com/random/1600x900',
       }
   },
	components: {
		ImgBanner,
		PortfolioList,
  },

  mounted(){
    this.ax()
  },
  methods :{
    ax : function() {
      axios.get("https://us-central1-webmobile-sub2-639ef.cloudfunctions.net/addMessage?text='포트폴리오 페이지 방문'")
    },
    changeImage(){
      if (this.photoImage == "https://www.cntry.biz/wp-content/uploads/2015/12/Female-Coders.jpg") {
        alert("기본 사진으로 돌아가용!")
        this.photoImage = 'https://source.unsplash.com/random/1600x900'
      }else{
        alert("내가 설정한 사진이 보고싶엉?");
        this.photoImage = 'https://www.cntry.biz/wp-content/uploads/2015/12/Female-Coders.jpg'
      }
    }
  }
}
</script>
