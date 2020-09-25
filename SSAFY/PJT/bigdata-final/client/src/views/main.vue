<template>
  <div>
    <v-btn class="white--text" style="height: 100px; background-color:#ffffff; background-color:  rgba( 255, 255, 255, 0 ); font-size:60px; margin-left:300px;" @click="searchpage">영화 검색하기</v-btn><br>
    <!-- <a class="white--text" style="font-size:60px;" @click="random_movie">랜덤</a> -->
    <v-container
      grid-list-xl
    >
      <v-layout wrap>
        <v-flex xs12>
          <slot />
        </v-flex>

        <v-flex v-for="(card,i) in m_list"
        :key="card.id"
        :value="card"
          pa-2>
          <Feedcard
            :id="card.id"
            :img="card.img"
            :title="card.title"
            :genres="card.genres_array"
            :rating="card.rating"
            :view-cnt="card.viewCnt"
            :slug="card.slug"
            :poster="card.poster"
            :runtime="card.runtime"
            :director="card.director"
            :writer="card.writer"
            :actor="card.actor"
            :plot="card.plot"
            :country="card.country"
          />

        </v-flex>
      </v-layout>

    </v-container>
    <!-- <subscribe/> -->
  </div>
</template>

<script>
import Feed from './Feed'
import Feedcard from './Feedcard'
import subscribe from './Subscribe'
export default {
  components:{
    Feed,
    Feedcard,
    subscribe,
  },
  props:{

  },
  data(){
    return{
      m_list:[],
    }
  },
  watch: {

  },
  methods:{
    searchpage: function() {
      this.$router.push('/movie/search')
    },
    random_movie: function() {
      this.$store.dispatch("RAN_MOVIE").then((res)=>{
        console.log(res)
        this.m_list=res
      })
    },
  },
  created() {
    this.random_movie()
  },
}
</script>
