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
import ImgBanner from '../components/ImgBanner'
import PostList from '../components/PostList'
import Writer from '../components/PostWriter'
import Post from '@/components/Post'
import FirebaseService from '@/services/FirebaseService'

export default {
	name: 'PostWriterPage',
	components: {
    Writer,
		ImgBanner,
		PostList,
  },
  methods:{

    async postPost(title, body){

    }
  }
}
</script>
