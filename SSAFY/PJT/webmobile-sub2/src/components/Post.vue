<template>
  <v-layout py-4 h-100>
    <v-flex row>
      <div class="caption">{{formatedDate}}</div>
      <h2 class="color-333 headline font-weight-light title">{{title}}</h2>
      <p class="mb-1 color-666 font-weight-light subheading body">{{body}}</p>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
   name: 'Post',
   props: {
      date: {type: Date},
      title: {type: String},
      body: {type: String}
   },
  computed: {
      formatedDate() {
         return `${this.date.getFullYear()}년 ${this.date.getMonth()}월 ${this.date.getDate()}일`
    }
  }
}
</script>
<style>
  .title{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1; /* 라인수 */
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    line-height: 1.2em;
    height: 1.2em; 
  }
  .body{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3; /* 라인수 */
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    line-height: 1.2em;
    height: 3.6em;
  }
  .color-666 {
    color: #666;
  }
  .color-333 {
    color: #333;
  }
  .h-100 {
    height: 100%;
  }
</style>