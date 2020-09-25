<template>
  <v-container text-center class="col" @keyup.enter="submit()">
    <v-card id="signup_card" class="elevation-12 mx-auto">
      <v-toolbar id="background"
        color="brown lighten-1"
        dark
        flat
        height="200px"
        style="text-align:center">
        <p id="toolbar-title"><strong>Sign up</strong></p>
        <v-spacer></v-spacer>
      </v-toolbar>
    <v-card-text>
      <form>
        <v-text-field
          v-model="username"
          :counter="10"
          label="Name"
          :rules="nameRules"
          prepend-icon="mdi-account"
          required
          style="margin-left: 5rem; margin-right: 5rem;">
        </v-text-field>
        <v-text-field
            v-model="password"
            label="Password"
            :counter="20"
            :rules="passwordRules"
            type="password"
            prepend-icon="mdi-lock"
            required
            style="margin-left: 5rem; margin-right: 5rem;"
        ></v-text-field>
        <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            prepend-icon="mdi-email"
            required
            style="margin-left: 5rem; margin-right: 5rem;"
        ></v-text-field>
        <div>
          <p style="font-size: 1rem;">Gender</p>
          <v-radio-group v-model="gender" row>
            <v-radio label="Female" value="F"></v-radio>
            <v-radio label="Male" value="M"></v-radio>
          </v-radio-group>
        </div>
        <div>
          <p style="font-size: 1rem;">Birty Day</p>
          <v-layout>
            <v-flex>
              <v-select
                :items="years"
                v-model="year"
                filled
              ></v-select>
            </v-flex>
            <v-flex>
              <p>년</p>
            </v-flex>
            <v-flex>
              <v-select
                :items="months"
                v-model="month"
                filled
              ></v-select>
            </v-flex>
            <v-flex>
              <p>월</p>
            </v-flex>
            <v-flex>
              <v-select
                :items="days"
                v-model="day"
                filled
              ></v-select>
            </v-flex>
            <v-flex>
              <p>일</p>
            </v-flex>
          </v-layout>
        </div>
        <v-text-field
            v-model="address"
            label="Adress"
            prepend-icon="mdi-home"
            style="margin-left: 5rem; margin-right: 5rem;"
        ></v-text-field>
        <v-btn v-if="this.nameRules[0](this.username)===true && this.passwordRules[0](this.password)===true && this.emailRules[0](this.email)===true"
          @click="submit()"
          style="margin-top: 2rem; margin-bottom: 1rem;">Sign up</v-btn>
        <v-btn v-else disabled style="margin-top: 2rem; margin-bottom: 1rem;">
          Sign up</v-btn>
        </form>
        <div style="padding-top: 1.3rem; padding-bottom: 3rem;">
          <router-link to="login" style="font-size: 1.1rem;">이미 계정이 있으신가요?</router-link>
        </div>
      </v-card-text>
    </v-card>
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
      username: '',
      password: '',
      email: '',
      address: '',
      gender: "F",
      year: '',
      month: '',
      day: '',
      years: ['2019', '2018', '2017', '2016', '2015', '2014', '2013', '2012', '2011', '2010', '2009', '2008', '2007', '2006', '2005', '2004', '2003', '2002', '2001', '2000', '1999', '1998', '1997', '1996', '1995', '1994', '1993', '1992', '1991', '1990', '1989', '1988', '1987', '1986', '1985', '1984', '1983', '1982', '1981', '1980', '1979', '1978', '1977', '1976', '1975', '1974', '1973', '1972', '1971', '1970'],
      months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
      days: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
      nameRules: [
        function(v) {
        // 아예 없는지 체크
        if (!v) {
          return '이름을 입력하세요.'
        }
        // 길이 제한 체크
        if (v.length > 10) {
          return '10글자 이내로 입력하세요.'
        }
        // 공백 체크
        if (v!==v.replace(/(\s*)/g,"")) {
          return "공백은 입력이 안돼요."
        }
        //특수문자 체크
        var pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/;
        if (pattern_spc.test(v)) {
          return "특수문자 사용은 안돼요."
        }
          return !!v
        }
      ],
      passwordRules: [
          function(v) {
          // 아예 없는지 체크
          if (!v) {
              return '비밀번호를 입력하세요.'
          }
          // 길이 제한 체크
          if (v.length < 5 || v.length > 20) {
              return '5~20개 글자를 입력하세요.'
          }
          // 공백 체크
          if (v!==v.replace(/(\s*)/g,"")) {
              return "공백은 입력이 안돼요."
          }
          return !!v
          },
      ],
      emailRules: [
          v => !!v || '이메일을 입력하세요.',
          v => /.+@.+/.test(v) || '이메일 형식이 아닙니다.',
        function(v) {
          // 아예 없는지 체크
          if (!v) {
            return '비밀번호를 입력해주세요.'
          }
          // 길이 제한 체크
          if (v.length < 5 || v.length > 20) {
            return '5~20글자를 입력해주세요.'
          }
          // 공백 체크
          if (v!==v.replace(/(\s*)/g,"")) {
            return "공백은 입력할 수 없어요."
          }
          return !!v
        },
      ],
      emailRules: [
        v => !!v || '이메일을 입력해주세요.',
        v => /.+@.+\..+/.test(v) || '이메일 형식으로 입력하세요.',
      ],
    }
  },
  methods: {
      async submit() {
        let __this = this;
        const apiUrl = '/api'
        await axios.post(`${apiUrl}/auth/signup/`, {
          username: __this.username,
          password: __this.password,
          email: __this.email,
          address: __this.address,
          gender: __this.gender,
          birthday: __this.year+__this.month+__this.day
        }).then(res => {
          if (res.data == true) {
            Swal.fire({
              title: '회원가입 성공',
              type: 'success'
            })
            router.push('/login')
          } else {
            Swal.fire({
              title: '회원가입 실패',
              text: 'ID가 중복됩니다. 다른 ID를 사용하세요.',
              type: 'error'
            })
          }
      })
    }
  },
  created() {
    if (this.$session.get('id')!='') {
      Swal.fire({
        type: 'error',
        title: '로그인 상태입니다! 로그아웃 후 이용해주세요.',
      })
      router.push({name:"home"})
    }
  }
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
