<template>
  <div>
    <v-btn color="red" v-if="$store.state.profile.is_subscription"><router-link style="text-decoration: none; color: white;" to="/movie/reco">{{ $store.state.profile.username }}의 시청기반 추천받기</router-link></v-btn>
    <v-btn color="red" @click="subscription" v-show="$store.state.profile.username" v-if="!$store.state.profile.is_subscription">구독 신청</v-btn>
    <v-btn color="red" @click="subscription" v-if="$store.state.profile.is_subscription">구독 취소</v-btn>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  data:()=>({

  }),
  methods: {
    subscription(){
      if(this.$store.state.profile.is_subscription) {
        this.$store.dispatch('SUBSCRIPTION').then(()=>{
          window.scrollTo(0, 9000)
        })
      }
      else {
        window.scrollTo(0, 9000)
      }
    }
  }
}
</script>

<style>

</style>
