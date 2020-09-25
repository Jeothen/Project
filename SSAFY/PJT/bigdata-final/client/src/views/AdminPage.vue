<template>
<div class="admin">
  <div class="admin-title display-2 mb-5">
    Admin Page
  </div>
  <div class="admin-btn">
    <div class="admin-btn-1">
      <v-btn @click="showMovie" class="btn" color="black" dark>
        <span class="headline">영화 수정하기 <v-icon x-large class="mb-1">mdi-arrow-left</v-icon></span></v-btn>
    </div>
    <div class="admin-btn-2">
      <v-btn @click="showUser" class="btn" color="white">
        <span class="headline"> <v-icon x-large class="mb-1">mdi-arrow-right</v-icon> 유저 수정하기</span></v-btn>
    </div>
</div>

  <MovieSearch v-if="movie">

  </MovieSearch>

  <UserSearch v-if="user">

  </UserSearch>
</div>
</template>

<script>
import MovieSearch from '../components/AdminPage/MovieSearch'
import UserSearch from '../components/AdminPage/UserSearch'

export default {
  components:{
    MovieSearch,
    UserSearch,
  },
  data() {
    return {
      movie:false,
      user:false,
    }
  },
  methods: {
    showMovie(){
      this.movie = true
      this.user = false
    },
    showUser(){
      this.user = true
      this.movie = false
    }
  }
}
</script>

<style scoped lang="scss">
.admin {
  position:relative;
  &-title {
    color:#eee;
  }
  &-btn{
    display:flex;
    &-1{
      width:300px;
      height:300px;
      // background:red; 
      .btn{
        width:100%;
        height:100%;
      }
    }
    &-2{
      width:300px;
      height:300px;
      // background:red; 
      .btn{
        width:100%;
        height:100%;
      }
    }
  
  }
}

</style>