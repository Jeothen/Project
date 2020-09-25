<template>
  <v-container text-center>
    <p id="title">Items</p>
    <v-row>
      <div v-for="(item, i) in listData" v-bind:key="i">
      <v-hover v-slot:default="{ hover }">
        <v-card :elevation="hover ? 12 : 2"
          class="ma-2"
          max-width="250">
          <v-img
            class="white--text align-end"
            height="150px"
            :src="item.image">
            <v-card-title>{{item.name}}</v-card-title>
          </v-img>

          <v-card-subtitle class="pb-0">{{item.country}}</v-card-subtitle>
          <v-card-text style="height:240px;" class="text--primary">
            <p style="font-family: 'Noto Serif KR', serif;">{{item.content}}</p>
          </v-card-text>
          <v-btn
            color="orange"
            text 
            >
            Delete
          </v-btn>
        </v-card>
      </v-hover>
      </div>
    </v-row>
  </v-container>
</template>
<script>
import axios from 'axios'
import router from "../router"
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      listData: [],
      show: false
    }
  },
  mounted() {
    this.allItem()
  },
  components: {
      
  },
  methods: {
    allItem() {
      const apiUrl = '/api'
      axios.get(`${apiUrl}/get_item/`)
      .then((response) => {
        this.listData = response.data;
        console.log(this.listData)
      });
    },
    createItem() {
      router.push({name:"createitem"})
    },
    deleteItem(itemid) {
      const apiUrl = '/api'
      axios.post(`${apiUrl}/delete_item/`, {pk:itemid})
      window.location.reload()
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