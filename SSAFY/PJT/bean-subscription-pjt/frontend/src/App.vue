<template>
  <v-app style="background-color: #fffff5">
    <Header/>
    <span style="line-height:240px"><br></span>
    <router-view/>
    <Footer/>
    <!-- <div>
    <a href="/signup" style="width:50px; height:50px; margin-top: 1.5rem; margin-bottom: 2rem; position:fixed; bottom:20%; right:1%;">
    <i class="fas fa-user-circle fa-3x" style="color:black"></i></a>
    <a href="/" style="width:50px; height:50px; margin-top: 1.5rem; margin-bottom: 2rem; position:fixed; bottom:27%; right:1.3%;">
    <i class="fas fa-home fa-3x" style="color:orange"></i></a>
    </div> -->
    <a id="side" v-if="user!=''" 
      style="width:50px; height:50px; margin-top: 1.5rem; margin-bottom: 2rem; position:fixed; bottom:21%; right:1%;">
    <i class="fas fa-sign-out-alt fa-3x" style="color:black" @click="logout()"></i>
    <span id="hovertext">logout</span></a>
    <a id="side" v-if="user!=''" 
      style="width:50px; height:50px; margin-top: 1.5rem; margin-bottom: 2rem; position:fixed; bottom:31%; right:1.3%;">
    <i class="fas fa-home fa-3x" style="color:orange" @click="Mypage()"></i>
    <span id="hovertext">mypage</span></a>

    <v-btn href="#" style="margin-top: 1.5rem; margin-bottom: 2rem; position:fixed; bottom:0%; right:1%;" fab><v-icon dark>mdi-chevron-double-up</v-icon></v-btn>
    
  </v-app>
</template>

<script>
import Header from './components/Header';
import Footer from './components/Footer';
import { mapGetters } from 'vuex';
import router from './router';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  name: 'App',
  components: {
    Header,
    Footer,
    router
  },
  data: () => ({
    user:''
  }),
  mounted() {
    this.setUser();
  },
  methods: {
    setUser() {
      if (this.$session.get('id')==undefined || this.$session.get('id')=='') {
        this.$session.set('id', '')
        this.$session.set('user', '')
        this.user = ''
      } else {
        this.user = this.$session.get('user')
      }
    },
    logout() {
      axios.get('/api/auth/logout/')
      this.user = ''
      this.$session.set('id', '')
      this.$session.set('user', '')
      Swal.fire({
        title: '로그아웃 하셨습니다.',
        type: 'success'
      }).then((result) => {
        router.push('/')
        window.location.reload()
      })
    },
    Mypage() {
      if(this.$session.get('id') == '') {
        Swal.fire({
          type: 'error',
          title: '먼저 로그인 해주세요!'
        }).then((result) => {
          router.push('/')
        })
      } else {
        router.push({ name: "mypage" })
      }
    }
  }
}
</script>
<style scoped>
  #side #hovertext {
    visibility: hidden;
    background-color: #fffff5;
    /* width: 250px; */
    color: black;
    text-align: center;
    /* Position the tooltip */
    z-index: 1;
  }
  #side:hover #hovertext {
    visibility: visible;
  }
</style>
