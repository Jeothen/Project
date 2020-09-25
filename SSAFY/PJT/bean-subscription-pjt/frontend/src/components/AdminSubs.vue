<template>
  <v-container text-center>
    <p id="title">Subscriptions</p>
    <v-layout column>
      <v-flex>
        <li v-if="subscription.approval==false" 
        v-for="(subscription, index) in this.subscriptions"
        v-bind:key="index">
          {{subscription.username}}님이 {{subscription.request}}일 구독 신청하였습니다.
          <v-btn @click="approval(subscription, index)">승인</v-btn>
        </li>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      subscriptions : []
    }
  },
  mounted() {
    this.getRequests()
  },
  methods: {
    async getRequests() {
      let __this = this;
      await axios.get('/api/subscription/manager/')
      .then(res => {
          __this.subscriptions = res.data
      })
    },
    approval(subscription, index) {
      this.subscriptions.splice(index, 1)
      axios.post('/api/subscription/manager/', {subscription: subscription})
      .then(res => {
        if (res.status == 200) {
          Swal.fire({
            title: `${subscription.username}님의 요청 승인 완료`,
            type: 'success'
          })
        }
      })
    }
  }
}
</script>
<style scoped>
  #title {
    font-family: 'Acme', sans-serif;
    font-size: 50px;
  }
</style>