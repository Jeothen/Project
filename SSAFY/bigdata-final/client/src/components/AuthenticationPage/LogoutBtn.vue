<template>
  <div>
    <v-btn color="red" @click="logout">로그아웃</v-btn>
  </div>

</template>

<script>
export default {
  methods: {
    logout(){
      this.$router.push({path:'/'})
      this.$store.commit('LOGOUT')
      alert('로그아웃 완료')
    }
  }
}
</script>

<style>

</style>
