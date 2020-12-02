<template>
<!-- 유저 검색 페이지 -->
  <v-container grid-list-md text-center class="mt-5">
    <v-layout justify-center wrap>
      <!-- 검색 폼 -->
      <v-flex xs6>
        <span class="s-title display-1 font-weight-medium">유저 검색</span>

        <UserSearchForm v-on:startSearch="searchUser"/>
        <!-- <UserSearchForm v-on:startSearch="searchMovies"  /> -->
        <!-- 필터링기능 -->

      </v-flex>
      <!-- 검색 결과 -->
      <v-flex xs7>
        <UserList :user-list-cards="user_list" />
        <!-- <UserList :movie-list-cards="movie_list" /> -->
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import UserSearchForm from './UserSearchForm'
import UserList from './UserList'

export default {
  components:{
    UserSearchForm,
    UserList,
  },
  methods: {
    searchUser(s){
      let filter = `?search=${s}`
      this.$store.dispatch('SEARCH_USER',filter)
      this.$store.commit('SET_FILTER',filter)
    }
  },
  computed: {
    ...mapState([
      'user_list'
    ])
  }
}
</script>

<style scoped>
.s-title{
  color: #d6f8ff;
}
</style>