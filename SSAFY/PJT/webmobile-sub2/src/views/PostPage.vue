<template>
  <div>
    <ImgBanner imgSrc="https://source.unsplash.com/random/1600x900">
      <div style="line-height:1.2em;font-size:1.2em;" slot="text1">Post</div>
    </ImgBanner>
    <v-container>

      <!-- Post -->
      <v-layout>
        <v-flex xs12>
          <PostList :limits="6" :load-more="true"></PostList>
        </v-flex>
      </v-layout>

    </v-container>
  </div>
</template>

<script>
const axios = require('axios')

import ImgBanner from '../components/ImgBanner'
import PostList from '../components/PostList'

export default {
	name: 'PostPage',
	components: {
		ImgBanner,
		PostList,
  },
  mounted(){
    this.ax()
  },
  methods :{
    ax : function() {
      axios.get("https://us-central1-webmobile-sub2-639ef.cloudfunctions.net/addMessage?text='포스트  페이지 방문'")
    }
  }
}
</script>
