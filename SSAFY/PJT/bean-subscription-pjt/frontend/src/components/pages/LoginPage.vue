<template>
  <v-container text-center class="col" @keyup.enter="submit()">
    <v-card id="login_card" class="elevation-12 mx-auto">
      <v-toolbar id="background"
        color="brown lighten-1"
        dark
        flat
        height="200px">
        <p id="toolbar-title"><strong>Login</strong></p>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-card-text>
        <form>
          <v-text-field
            v-model="username"
            label="Name"
            prepend-icon="mdi-email"
            :counter="10"
            :rules="nameRules"
            required
            style="margin-left: 5rem; margin-right: 5rem;"
          ></v-text-field>
          <v-text-field
            v-model="password"
            label="Password"
            type="password"
            :counter="20"
            :rules="passwordRules"
            prepend-icon="mdi-lock"
            required
            style="margin-left: 5rem; margin-right: 5rem;"
          ></v-text-field>
      
        <v-btn @click="submit()" style="margin-top: 2rem; margin-bottom: 1rem;">Login</v-btn>
        </form>
        <div style="padding-top: 1.3rem; padding-bottom: 3rem;">
          <router-link to="signup" style="font-size: 1.1rem;">계정이 없으신가요?</router-link>
        </div>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import router from '../../router';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      username: "",
      password: "",
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || '10글자 이하로 입력해주세요.'
      ],
      passwordRules: [
        v => !!v || 'Password is required',
        v => (v && v.length >= 5 && v.length <= 20) || '5~20글자로 입력해주세요.'
      ],
    }
  },
  created() {
    if (this.$session.get('id')!='') {
      Swal.fire({
        type: 'error',
        title: '이미 로그인한 상태입니다!',
      })
      router.push({name:"home"})
    }
  },
  methods: {
    async submit() {
      let __this = this
      await axios.post('/api/auth/signin/', {
        password: __this.password,
        username: __this.username
      }).then(res => {
        if (res.data.user) {
          // 로그인 됨 vue-session 저장 필요
          this.$session.set('id', res.data.id)
          this.$session.set('user', res.data.user)
          Swal.fire({
            title: '로그인 완료!',
            type: 'success'
          }).then((result) => {
            router.push('/')
            window.location.reload()
          })
        } else {
          // 로그인 되지 않음, alert 띄우기
          Swal.fire({
            text: 'ID 또는 비밀번호가 다릅니다.',
            type: 'error'
          })
        }
      })
    }
  }
}
</script>
<style scoped>
  #login_card {
    width:600px;
  }
  #background {
    background-image: url(../../../public/img/login_toolbar.jpg);
    background-size: cover;
    background-position: center;
  }
  #toolbar-title {
    margin:0px;
    font-size:50px;
    font-family: 'Indie Flower', cursive;
    color: orange;
    width:100%;
  }
</style>