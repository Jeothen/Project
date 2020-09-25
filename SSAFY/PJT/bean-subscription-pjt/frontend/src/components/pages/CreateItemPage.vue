<template>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="name"
      :counter="20"
      :rules="nameRules"
      label="Name"
      required
    ></v-text-field>

    <v-text-field
      v-model="content"
      label="content"
    ></v-text-field>
   
    <v-text-field
      v-model="image"
      label="imageurl"
    ></v-text-field>
    
    <v-text-field
      v-model="country"
      label="country"
    ></v-text-field>

    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="validate"
    >
      등록
    </v-btn>

    <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Reset
    </v-btn>
  </v-form>
</template>

<script>
import axios from 'axios'
import router from '../../router';

export default {
  data: () => ({
    valid: true,
    name: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => (v && v.length <= 20) || 'Name must be less than 20 characters',
    ],
    text: '',
    image: '', country:'', content:''
  }),

  methods: {
    validate () {
        let __this = this;
      if (this.$refs.form.validate()) {
          axios.post('/api/add_rawdata/', {
              name: __this.name,
              content: __this.content,
              image : __this.image,
              country : __this.country
          }).then(res => {
            console.log('등록')
            router.push({name:"adminitem"})
          })
          this.snackbar = true
      }
    },
    reset () {
      this.$refs.form.reset()
    }
  }
}
</script>