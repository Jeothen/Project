<template>
  <v-layout py-4 h-100>
    <v-flex row>
      <div class="caption">{{formatedDate}}</div>
      <h2 class="color-333 headline font-weight-light">{{title}}</h2>
      <p class="mb-1 color-666 font-weight-light subheading">{{body}}</p>
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
  .color-666 {
    color: #666;
  }
  .color-333 {
    color: #333;
  }
  .h-100 {
    height: 100%;
  }

  .headline{
    display: inline-block;
    width: 95%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;

  }

  .subheading{
    display: inline-block; width: 95%; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;

    white-space: normal; line-height: 1.2; height: 3.6em; text-align: left; word-wrap: break-word; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;

  }

</style>
