<template>
  <v-card>
    <v-img :src="imgSrc" height="200px">
    </v-img>
    <v-card-title primary-title>
      <div>
        <div class="headline title">{{title}}</div>
        <span class="grey--text body">{{body}}</span>
      </div>
    </v-card-title>
  </v-card>
</template>

<script>
export default {
   name: 'Portfolio',
   props: {
      date: {type: String},
      title: {type: String},
      body: {type: String},
      imgSrc: {type: String},
   },
   data() {
      return {
         //
      }
   }
}
</script>
<style>
.title{
  overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    line-height: 1.2em;
    height: 1.2em;
}
.body{
  overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    line-height: 1.2em;
    height: 3.6em;
}
</style>