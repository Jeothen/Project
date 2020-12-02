<template>
  <v-container text-center>
    <p id="title">Users</p>
    <v-row>
    <div v-for="(user, i) in users" v-bind:key="i" style="width:300px;">
      <v-hover v-slot:default="{ hover }">
      <v-card :elevation="hover ? 12 : 2"
        class="ma-4"
        outlined>
        <v-list-item three-line>
          <v-list-item-content>
            <div class="overline mb-4">No.{{ user.user_pk}}</div>
            <v-list-item-title class="headline mb-1">{{ user.username }}</v-list-item-title>
            <v-list-item-subtitle>{{ user.email }}</v-list-item-subtitle>
            <p>{{ user.address }}</p>
          </v-list-item-content>
        </v-list-item>

        <v-card-actions>
          <v-btn @click="delete_user(user.user_pk)" text
            color="orange">
            delete</v-btn>
        </v-card-actions>
      </v-card>
      </v-hover>
    </div>
    </v-row>
  </v-container>
</template>
<script>
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      users:[]
    }
  },
  mounted() {
    this.alluser()
  },
  methods: {
    alluser() {
      const apiUrl = '/api'
      axios.post(`${apiUrl}/auth/alluser/`
      ).then(res=> {
        this.users = res.data
        console.log(res.data)
      })
    },
    delete_user(id) {
      const apiUrl = '/api'
      axios.post(`${apiUrl}/auth/user/${id}/delete`
      ).then(res=> {
        Swal.fire({
          text: '삭제되었습니다.',
          type: 'success'
        })
        window.location.reload()
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