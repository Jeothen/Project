<template>
<!-- 영화 검색 페이지 -->
  <v-container grid-list-md text-center>
    <v-layout justify-center wrap>
      <!-- 검색 폼 -->
      <v-flex xs12>
        <div class="display-2 pa-10" style="color :white">영화 검색</div>

        <MovieSearchForm v-on:startSearch="searchMovies"  />
        <!-- 필터링기능 -->
      </v-flex>
      <!-- 검색 결과 -->
      <v-flex md6>
        <MovieList :movie-list-cards="movie_list" />
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import MovieSearchForm from '../components/MovieSearchPage/MovieSearchForm'
import MovieList from '../components/MovieSearchPage/MovieList';

export default {
  components:{
    MovieSearchForm,
    MovieList,
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
  computed: {
    ...mapState([
      'movie_list'
    ])
  }
}
</script>
