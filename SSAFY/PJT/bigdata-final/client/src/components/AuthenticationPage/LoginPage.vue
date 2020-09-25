<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="red" dark v-on="on">로그인</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Log In</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <!-- ID 입력칸 -->
              <v-col cols="12">
                <v-text-field
                  counter="10"
                  maxlength="10"
                  label="ID"
                  v-model="username"
                  :error-messages="usernameErrors"
                  @input="$v.username.$touch()"
                  @blur="$v.username.$touch()"
                  required>
                </v-text-field>

              </v-col>
              <!-- 패스워드 -->
              <v-col cols="12">
                <v-text-field
                  label="Password"
                  type="password"
                  v-model="password"
                  :error-messages="passwordErrors"
                  @input="$v.password.$touch()"
                  @blur="$v.password.$touch()"
                  required>
                </v-text-field>
              </v-col>
            </v-row>
          </v-container>

        </v-card-text>
        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="blue" text @click="login">Login</v-btn>
          <v-btn color="red darken-1" text @click="close">Close</v-btn>
        </v-card-actions>

      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";

export default {
  data: () => ({
    dialog: false,
    occupations:[
    ],
    username:'',
    password:'',
    error:false,
  }),

  mixins: [validationMixin],
  validations: {
    username: {
      required,
     },
    password: {
      required,
    },
  },

  computed: {
    usernameErrors() {
      const errors = [];
      if (!this.$v.username.$dirty) return errors;
      !this.$v.username.required && errors.push("ID를 입력해주세요");
      if (this.$store.state.loginerror) {
        errors.push("아이디와 패스워드를 다시 확인해주세요")
      }
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.required && errors.push("비밀번호를 입력해주세요");
      if (this.$store.state.loginerror) {
        errors.push("아이디와 패스워드를 다시 확인해주세요")
      }
      return errors;
    },
  },

  methods: {

    close(){
      this.dialog = false
      this.username = ''
      this.password = ''
      this.$v.$reset()
      this.error = false
      this.$store.state.loginerror = false
    },

    login() {
      this.$v.$touch();
      // this.$store.state.snackbar.status = false
      // 검증작업이 끝나면 서버로 요청을 보냄
      if (!this.$v.$error) { // 에러가 없다면?
        const obj = {
          username:this.username,
          password:this.password,
        }
        this.$store.dispatch('GET_TOKEN',obj)
          .then((res) => {
            if (!this.$store.state.loginerror){
              this.close()
              this.$router.push('/main')
            }
            else {
              console.log('에러발생 로그인 정보확인하세요!.')
            }
          })
      }
    },

  }
}
</script>
