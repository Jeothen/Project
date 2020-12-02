<template>
  <v-container class="pa-2" fluid grid-list-md>
    <v-layout column>
      <v-flex v-for="card in movie_list" :key="card.id" pa-2>
        <MovieRateListCard
          :mylist="profile.user_visited"
          :id="card.id"
          :img="card.img"
          :title="card.title"
          :genres="card.genres"
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
      <v-pagination :total-visible="10" v-if="max_page > 1" v-model="page" :length="max_page" />
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
import MovieRateListCard from './MovieRateListCard'

export default {
  components:{
    MovieRateListCard,
  },
  props:{
    movieRateListCards: {
      type: Array,
      default: () => new Array(),
    }
  },
  created () {
    // filter = ''
    this.$store.dispatch('SEARCH_MOVIES','?')
  },

  data(){
    return{
      page:0,
      // mylist: []
    }
  },
  computed: {
    ...mapState(['max_page','movie_list','filter', 'profile',])
  },
  watch: {
    page(){
    //   console.log('filter', this.filter)
      this.$store.dispatch('SEARCH_MOVIES','?'+`&page=${this.page}`)
      
    }
  }
}
</script>