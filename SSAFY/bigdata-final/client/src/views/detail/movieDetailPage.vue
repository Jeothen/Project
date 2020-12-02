<template>
  <v-card>
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title>{{ title }}</v-list-item-title>
        <v-list-item-subtitle>장르 리스트 : {{ genres_array }}</v-list-item-subtitle>
        <v-list-item-subtitle>조회수 : {{ views }}</v-list-item-subtitle>
        <v-list-item-subtitle>평점 : {{ avg_rating }}</v-list-item-subtitle>
      </v-list-item-content>
    
    </v-list-item>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      title:'',
      genres_array: [],
      views:'',
      slug:'',
      avg_rating:'',
    }
  },
  created(){
    console.log(this.$route.params.slug)
    this.$store.dispatch('FETCH_MOVIE_DETAIL',this.$route.params.slug)
      .then(res => {
        this.title = res.title
        this.genres_array = res.genres_array
        this.views = res.views
        this.slug = res.slug
        this.avg_rating = res.avg_rating
      })
  }
}
</script>

<style>

</style>