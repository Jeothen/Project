<template>
  <v-form ref="form">
    
    <!-- 제목 입력 -->
    <v-text-field v-model="title" label="영화 제목" color="#d6f8ff" dark/>
    <v-layout justify-center pa-10>
      <v-btn large color="indigo white--text" @click="startSearch">Search</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
export default {

  data() {
    return {
      title:'',
    }
  },
  methods: {
    startSearch(){
      const obj = {
        filter:['Title'],
        order:'',
        order_data:'',
        target:this.title
      }
      this.$emit('startSearch',obj)
    },
  }
};
</script>
