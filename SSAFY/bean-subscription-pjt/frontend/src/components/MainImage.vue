<template>
  <div>
    <img class="mainGif"
        src="https://media.tumblr.com/tumblr_md75laJebh1r5kl7zo1_500.gif"/>
  </div>
</template>
<script>
export default {
  data () {
    return {
      items: ['http://24.media.tumblr.com/437d31e3e35531c0715b3606af226c84/tumblr_mou37e7BNf1qfp0dho1_500.gif',
        'https://media.tumblr.com/tumblr_md75laJebh1r5kl7zo1_500.gif'],
      item: '',
      index: 0
    }
  },
  mounted () {
    // this.repeatGIF();...
  },
  methods: {
    // repeatGIF: function() {
    //   this.item = this.items[this.index];
    //   setTimeout(function(){
    //     console.log(this.index)
    //     var index = 0;
    //     index += 1;
    //     if(index==2) {
    //       index = 0;
    //     }
    //     this.index = index;
    //     console.log(this.index)
    //   }, 2000);
    // }
  }
}
</script>
<style scoped>
  .mainGif {
    width:100%;
    height:550px;
  }
</style>
