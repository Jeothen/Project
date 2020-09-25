<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card height="500">
        <v-card-title>
          <span class="headline">사용자 수정하기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <!-- 유저이름 입력칸 -->
              <v-col cols="12">
                <v-text-field 
                  counter="10" 
                  label="ID" 
                  v-model="username_value" 
                  :error-messages="usernameErrors"
                  @input="$v.username_value.$touch()"
                  @blur="$v.username_value.$touch()"
                  required></v-text-field>
              </v-col>

              <!-- 나이 입력칸 -->
              <v-col cols="12" sm="6">
                <v-text-field
                  label="Age"
                  v-model="age_value" 
                  :error-messages="ageErrors"
                  @input="$v.age_value.$touch()"
                  @blur="$v.age_value.$touch()"             
                  required
                ></v-text-field>
              </v-col>

              <!-- 성별 입력칸 -->
              <v-col cols="12" sm="6">
                <v-select
                  :items="['Male','Female']"
                  label="gender"
                  v-model="gender_value"
                  required
                ></v-select>
              </v-col>

              <!-- 직업 입력칸 -->
              <v-col cols="12">
                <v-autocomplete
                  :items="occupations"
                  label="Occupation"
                  v-model="occupation_value" 
                ></v-autocomplete>
              </v-col>



            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="blue" text @click="editz">Edit</v-btn>
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
  data(){
    return {
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
      username_value:'',
      age_value:'',
      gender_value:'',
      occupation_value:'',
      error:'',
      sluger:'',
    }
  },
  mixins: [validationMixin],
  validations: {
    username_value: { 
      required,
      maxLength: maxLength(10)
     },
    age_value: {
      required,
      between: between(8,60)
    }
  },
  created(){
    this.init()
  },
  methods: {
    init(){
      this.username_value = this.username
      this.age_value = this.age
      this.gender_value = this.gender
      this.occupation_value = this.occupation
      this.error = ''
      this.sluger = this.slug
    },
    editz() {
      this.$v.$touch();
      if (!this.$v.$error) { // 에러가 없다면?
      let g = ''
      if (this.gender_value === 'Male'){
        g = 'M'
      } else {
        g = 'F'
      }
      const obj = {
        username : this.username_value,
        age : this.age_value,
        gender : g,
        occupation : this.occupation_value,
      }
      const payload = {obj:obj, slug:this.sluger}

      this.$store.dispatch('EDIT_USER',payload)
        .then((res) => {
          this.$emit("close");
          let a = this.id % 10 - 1
          if (a === -1) a = 9
          this.$store.state.user_list[a].username = res.data.username
          this.$store.state.user_list[a].age = res.data.age
          this.$store.state.user_list[a].gender = res.data.gender
          this.$store.state.user_list[a].occupation = res.data.gender
          this.$store.state.user_list[a].slug = res.data.slug
          this.sluger = res.data.slug

          // this.$store.state.user_list.username = this.username_value
          // this.$store.state.user_list.age = this.age_value
          // this.$store.state.user_list.gender = this.gender_value
          // this.$store.state.user_list.occupation = this.occupation_value
          
        })
        .catch(error => {
          console.log(error)
          if (error.response.status === 409){
            this.error = 409
          }
        })
      }
    },
    close() {
      this.$emit("close");
      this.init()
    },
  },
  props: {
    dialog: Boolean,
    username: String,
    occupation: String,
    age: Number,
    gender: String,
    is_staff: Boolean,
    slug:String,
    id:Number,
  },
  computed: {
    usernameErrors() {
      const errors = [];
      if (!this.$v.username_value.$dirty) return errors;
      !this.$v.username_value.maxLength && errors.push("10글자를 초과할 수 없어요.");
      !this.$v.username_value.required && errors.push("ID를 입력해주세요");
      if (this.error === 409){
        errors.push("해당유저는 존재합니다.")
      }
      return errors;
    },

    ageErrors() {
      const errors = [];
      if (!this.$v.age_value.$dirty) return errors;
      !this.$v.age_value.required && errors.push("나이를 입력해주세요");
      !this.$v.age_value.between && errors.push("유효한 나이를 입력해주세요. 8~60세");
      return errors;
    },
  }
}
</script>

<style>

</style>