import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import { store } from './store/index'

import vuetify from './plugins/vuetify'

Vue.config.productionTip = false;
Vue.config.devtools = true

new Vue({
  el: '#app',
  router,
  store,
  vuetify,
  render: h => h(App)
})
