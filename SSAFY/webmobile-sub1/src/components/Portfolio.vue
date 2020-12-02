<template>
  <v-card>
    <v-img :src="imgSrc" height="200px">
    </v-img>
    <v-card-title primary-title>
      <div>
        <div class="headline">{{title}}</div>
        <span class="main_text grey--text">{{body}}</span>
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
		}
	}
}
</script>

<style lang="css" scoped>
  .headline{
    display: inline-block;
    width: 95%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;

  }
  .main_text{
    display: inline-block;
    width: 95%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;

    white-space: normal;
    line-height: 1.2;
    height: 4.6em;
    text-align: left;
    word-wrap: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 4;
    -webkit-box-orient: vertical;

  }

</style>
