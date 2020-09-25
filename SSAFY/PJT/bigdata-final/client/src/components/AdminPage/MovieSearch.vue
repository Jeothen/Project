<!-- 영화 검색 페이지 -->
<template>
  <v-container grid-list-md text-center class="mt-5">
    <v-layout justify-center wrap>
      <!-- 검색 폼 -->
      <v-flex xs6>
        <span class="s-title display-1 font-weight-medium">영화 검색</span>

        <MovieSearchForm v-on:startSearch="searchMovies"  />
        <!-- 필터링기능 -->
      </v-flex>
      <!-- 검색 결과 -->
      <v-flex xs7>
        <MovieList :movie-list-cards="movie_list" />
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import MovieSearchForm from './MovieSearchForm'
import MovieList from './MovieList'

export default {
  components:{
    MovieSearchForm,
    MovieList,
  },
  methods: {
    // 필터링 적용 메서드
    searchMovies(obj,type){
      if (type=='연령대 별 검색'){
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

<style scoped>
.s-title{
  color: #d6f8ff;
}
</style>