<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="red" dark v-on="on">가입</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Sign Up</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <!-- ID 입력칸 -->
              <v-col cols="12">
                <v-text-field
                  counter="10"
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
                  v-model="password1"
                  :error-messages="passwordErrors1"
                  @input="$v.password1.$touch()"
                  @blur="$v.password1.$touch()"
                  required>
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Confirm Password"
                  type="password"
                  v-model="password2"
                  :error-messages="passwordErrors2"
                  @input="$v.password2.$touch()"
                  @blur="$v.password2.$touch()"
                  required>
                </v-text-field>
              </v-col>

              <!-- 나이 입력칸 -->
              <v-col cols="12" sm="6">
                <v-text-field
                  label="Age"
                  v-model="age"
                  :error-messages="ageErrors"
                  @input="$v.age.$touch()"
                  @blur="$v.age.$touch()"
                  required
                ></v-text-field>
              </v-col>

              <!-- 성별 입력칸 -->
              <v-col cols="12" sm="6">
                <v-select
                  :items="['Male','Female']"
                  label="gender"
                  v-model="gender"
                  required
                ></v-select>
              </v-col>

              <!-- 직업 입력칸 -->
              <v-col cols="12">
                <v-autocomplete
                  :items="occupations"
                  label="Occupation"
                  v-model="occupation"
                ></v-autocomplete>
              </v-col>
            </v-row>
          </v-container>

        </v-card-text>
        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="blue" text @click="register">Submit</v-btn>
          <v-btn color="red darken-1" text @click="close">Close</v-btn>
        </v-card-actions>

      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, minLength, maxLength, sameAs, between } from "vuelidate/lib/validators";

export default {
  data: () => ({
    dialog: false,
    occupations:[
      "other",
      "academic/educator",
      "artist",
      "clerical/admin",
      "college/grad student",
      "customer service",
      "doctor/health care",
      "executive/managerial",
      "farmer",
      "homemaker",
      "K-12 student",
      "lawyer",
      "programmer",
      "retired",
      "sales/marketing",
      "scientist",
      "self-employed",
      "technician/engineer",
      "tradesman/craftsman",
      "unemployed",
      "writer",
    ],
    username:'',
    password1:'',
    password2:'',
    age:'',
    occupation:'artist',
    gender:'Male',
    error:false,
  }),

  mixins: [validationMixin],
  validations: {
    username: {
      required,
      maxLength: maxLength(10)
     },
    password1: {
      required,
      minLength: minLength(8)
    },
    password2: {
      required,
      sameAsPassword: sameAs("password1")
    },
    age: {
      required,
      between: between(8,60)
    }
  },
  created(){
  },
  computed: {
    usernameErrors() {
      const errors = [];
      if (!this.$v.username.$dirty) return errors;
      !this.$v.username.maxLength && errors.push("10글자를 초과할 수 없어요.");
      !this.$v.username.required && errors.push("ID를 입력해주세요");
      if (this.$store.state.registrationError){
        errors.push("해당유저는 존재합니다.")
      }
      return errors;
    },
    passwordErrors1() {
      const errors = [];
      if (!this.$v.password1.$dirty) return errors;
      !this.$v.password1.required && errors.push("비밀번호를 입력해주세요");
      !this.$v.password1.minLength && errors.push('비밀번호는 8자 이상, 하나이상의 특수문자를 포함')
      !this.validatePassword(this.$v.password1) &&
        errors.push(
          "비밀번호는 8자 이상, 영문 조합, 하나이상의 특수문자를 포함해야해요."
        );
      return errors;
    },
    passwordErrors2() {
      const errors = [];
      if (!this.$v.password2.$dirty) return errors;
      !this.$v.password2.required && errors.push("비밀번호를 입력해주세요");
      !this.$v.password2.sameAsPassword && errors.push("비밀번호를 일치시켜주세요.");
      return errors;
    },
    ageErrors() {
      const errors = [];
      if (!this.$v.age.$dirty) return errors;
      !this.$v.age.required && errors.push("나이를 입력해주세요");
      !this.$v.age.between && errors.push("유효한 나이를 입력해주세요. 8~60세");
      return errors;
    },
  },

  methods: {
    validatePassword(character) {
      let m = character.$model;
      return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$/.test(
        m
      );
    },

    close(){
      this.dialog = false
      this.age = ''
      this.username = ''
      this.password1 = ''
      this.password2 = ''
      this.username = ''
      this.gender = 'Male'
      this.occupation = 'artist'
      this.$v.$reset()
      this.$store.state.registrationError = false
    },
    register() {
      this.$v.$touch();
      // this.$store.state.snackbar.status = false
      // 검증작업이 끝나면 서버로 요청을 보냄
      if (!this.$v.$error) { // 에러가 없다면?
        const obj = {
          username:this.username,
          password1:this.password1,
          password2:this.password2,
          gender: this.gender,
          occupation: this.occupation,
          age: this.age,
        }
        this.$store.dispatch('REGISTER',obj)
          .then(() => {
            if (!this.$store.state.registrationError){
              console.log('성공적으로 등록완료.')
              this.$store.dispatch('GET_TOKEN',{
                username:this.username,
                password:this.password1
                })
              this.close()
              this.$router.push({
              path:'/movie/rate',
              })
            }
            else{
              console.log('에러발생 이메일이 중복되는지 확인할 것.')
            }
          })
      }
    },

  }
}
</script>
