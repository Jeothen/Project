<template>
  <v-container grid-list-md text-center>
    <h1 style="color: white;">좋아하는 영화를 평가해주세요 !</h1>
    <v-layout justify-center wrap>
      <v-flex xs6>
          <div style="color: white;" class="display-2 pa-10">영화 검색</div>
          <MovieSearchForm v-on:startSearch="searchMovies"  />
      </v-flex>
      <v-flex xs7>
        <MovieRateList :movie-list-cards="movie_list" />
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import 'axios'
import { mapState } from "vuex";
import { mapGetters } from 'vuex';
import MovieRateList from '../components/MovieRatePage/MovieRateList';
import MovieSearchForm from '../components/MovieSearchPage/MovieSearchForm';
import MovieRateListCard from '../components/MovieRatePage/MovieRateListCard'

export default {
  components:{
    MovieRateList,
    MovieSearchForm,
    MovieRateListCard
  },
  created(){
    this.$store.commit('INIT_MOVIE')

  },
  methods: {
    // 필터링 적용 메서드
    searchMovies(obj,type){
      if (type=='연령대 별 검색'){
        console.log(type)
        console.log(obj)
        this.$store.dispatch('SEARCH_MOVIE_AGE',obj)
      }
      else{
        let filter = '?'
        if (obj.filter){
          obj.filter.forEach(f => {
            filter += `${f.toLowerCase()}=${obj.target}&`
          })
        }
        if (obj.order_data){
          if (obj.order_data == 'Rating'){
            obj.order_data = 'avg_rating'
          }

          if (obj.order === 'Ascending' || obj.order === '') {
            filter += `ordering=${obj.order_data.toLowerCase()}`
          } else if (obj.order === 'Descending'){
            filter += `ordering=-${obj.order_data.toLowerCase()}`
          }
        }
        this.$store.dispatch('SEARCH_MOVIES',filter)
        this.$store.commit('SET_FILTER',filter)
      }
    }
  },
  props:{
    movieListCards: {
      type: Array,
      default: () => new Array(),
    }
  },
  data(){
    return{
      page:0,
      MovieRateListCard,
      // movie_list=MovieRateListCard
    }
  },
  computed: {
    ...mapState([
      'movie_list'
    ])
  },
  watch: {
    page(){
      this.$store.dispatch('SEARCH_MOVIES',this.filter+`&page=${this.page}`)
    }
  }
}
</script>


<style>
#keep .v-navigation-drawer__border {
  display: none;
}
</style>
