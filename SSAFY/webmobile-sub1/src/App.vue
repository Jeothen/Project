<template>
  <v-app>
    <v-content>
      <main-header/>
      <router-view/>
    </v-content>
      <main-footer/>
  </v-app>
</template>

<script>
import store from './store'
import MainHeader from './components/MainHeader.vue'
import MainFooter from './components/MainFooter.vue'

export default {
	name: 'App',
  components: {
    'main-header' : MainHeader,
    'main-footer' : MainFooter
  },
	store,
	data() {
		return {
			//
		}
	}
}
</script>

</script>
<style>
.v-alert{
 z-index:1000;
 position: fixed;
 bottom:0px;
 width: 100%;
}
</style>
