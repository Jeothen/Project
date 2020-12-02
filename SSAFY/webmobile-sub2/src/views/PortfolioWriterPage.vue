<template>
  <div>
    <ImgBanner imgSrc="https://source.unsplash.com/5brvJbR1Pn8/1600x900">
      <div style="line-height:1.2em;font-size:1.2em;" slot="text">Portfolio</div>
    </ImgBanner>
    <v-container>
          <Writer></Writer>
    </v-container>
  </div>
</template>

<script>
const axios = require('axios')

import ImgBanner from '../components/ImgBanner'
import PortfolioList from '../components/PortfolioList'
import Writer from '../components/PortfolioWriter'
import Portfolio from '@/components/Portfolio'
import FirebaseService from '@/services/FirebaseService'

export default {
	name: 'PortfolioWriterPage',
	components: {
    Writer,
		ImgBanner,
		PortfolioList,
  },
  methods:{

    async postPortfolio(title, body, img){

    },
    mounted(){
      this.ax()
    },
    methods :{
      ax : function() {
        axios.get("https://us-central1-webmobile-sub2-639ef.cloudfunctions.net/addMessage?text='포트폴리오 작성 페이지 방문'")
      }
    }
  }
}
</script>
