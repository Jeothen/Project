<template>
  <v-card>
    <v-app-bar
      fixed
      color="#BCAAA4"
      dark
      shrink-on-scroll
      prominent
      src="https://picsum.photos/1920/1080?random"
      fade-img-on-scroll
    >
      <template v-slot:img="{ props }">
        <v-img
          v-bind="props"
          gradient="to top right, rgba(160,160,160,.7), rgba(25,32,72,.7)"
        ></v-img>
      </template>

      <v-toolbar-title id="toolbar_title">
        <v-btn icon @click="home()">
          <img id="logo" src="../../public/img/logo.png"/>
        </v-btn>
        Cup Of Tea
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <p>  </p>
      <v-btn icon>
        <v-icon @click="Mypage()">mdi-account</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>

      <template v-slot:extension>
        <v-tabs
          align-with-title
          background-color="transparent"
        >
          <v-tab @click="home()">Home</v-tab>
          <v-tab v-if="user==''" @click="login()">로그인</v-tab>
          <!-- <v-tab v-if="user==''" @click="signup()">회원가입</v-tab> -->
          <v-tab v-if="user!=''" @click="logout()">로그아웃</v-tab>
          <v-tab v-if="user=='admin'" @click="admin()">관리자</v-tab>
        </v-tabs>
      </template>
    </v-app-bar>
  </v-card>
</template>
<script>
import router from '../router';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      user:''
    }
  },
  created() {
    this.setUser()
  },
  methods: {
    home() {
      router.push({name:"home"})
    },
    // signup() {
    //   router.push({name:"signup"})
    // },
    login() {
      router.push({name:"login"})
    },
    admin() {
      router.push({name:"admin"})
    },
    setUser() {
      if(this.$session.get('id')=='' || this.$session.get('id') == undefined) {
        this.user = ''
      } else {
        this.user = this.$session.get('user')
      }
    },
    logout() {
      axios.get('/api/auth/logout')
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
<style>
  #logo {
    width:50px;
    height:50px;
  }
  #toolbar_title {
    font-size:30px;
    font-family: 'Liu Jian Mao Cao', cursive;
  }
</style>
