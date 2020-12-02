<template>
  <v-container>
    <h1>이번 달 블렌드 상품의 점수를 주세요</h1>

  </v-container>
</template>

<script>
import router from "../../router";
import axios from 'axios';
import Swal from 'sweetalert2';
import { mapState } from "vuex";
import { mapGetters } from 'vuex';

export default {
  data() {
    return {

    }
  },
  methods: {
    list_delivery(){
      axios.get('api/delivery/').then((res)=>{
        console.log(res)
      })
    },
    now(){
      console.log(this.$moment().format('YYYYMMDD'))
    },
  },
  created() {
    this.list_delivery();
    this.now();
  },
}
</script>
<style scoped>
  #signup_card {
    width:600px;
  }
  #background {
    background-image: url(../../../public/img/signup_toolbar.jpg);
    background-size: cover;
    background-position: center;
  }
  #toolbar-title {
    margin:0px;
    font-size:50px;
    font-family: 'Indie Flower', cursive;
    width:100%;
    color:orange;
  }
</style>
