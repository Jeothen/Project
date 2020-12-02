/* eslint-disable */
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import VueSweetalert2 from 'vue-sweetalert2'
import { store } from './store/index'
import Vuex from 'vuex'
import VueMomentJS from 'vue-momentjs'
import moment from 'moment'
Vue.use(Vuex)
Vue.config.productionTip = false
Vue.use(VueSweetalert2)
Vue.prototype.moment = moment
Vue.use(VueMomentJS, moment)
new Vue({
    router,
    store,
    vuetify,
    VueSweetalert2,
    render: h => h(App)
}).$mount('#app')
