<template>
  <div>
    <v-text-field v-model="title" label="ID" color="#d6f8ff" dark />
    <v-layout justify-center pa-10>
      <v-btn large color="red white--text" @click="startSearch">Search</v-btn>
    </v-layout>
  </div>
</template>

<script>
export default {
  data(){
    return {
      title: '',
    }
  },
  methods:{
    startSearch() {
      this.$emit('startSearch',this.title)
    }
  }
}
</script>

<style>

</style>