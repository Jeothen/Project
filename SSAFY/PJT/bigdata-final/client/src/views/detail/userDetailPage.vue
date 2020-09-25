<template>
  <v-card>
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title>{{ username }}</v-list-item-title>
        <v-list-item-subtitle>{{ gender }}</v-list-item-subtitle>
        <v-list-item-subtitle><span>Age : </span>{{ age }}</v-list-item-subtitle>
        <v-list-item-subtitle>{{ occupation }}</v-list-item-subtitle>
        
    <span class="overline">{{ watched_movie}}</span>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      username:'',
      gender:'',
      age:'',
      occupation:'',
      watched_movie:[],
    }
  },
  created(){
    console.log(this.$route.params.slug)
    this.$store.dispatch('FETCH_USER_DETAIL',this.$route.params.slug)
    .then(res => {
      this.username = res.username
      this.gender = res.gender == 'F' ? '남자' : '여자'
      this.age = res.age
      this.occupation = res.occupation
      this.watched_movie = res.watched_movie
    })
  }
}
</script>

<style>

</style>