import Vue from 'vue';
import Vuex from 'vuex';

import actions from './actions.js';
import mutations from './mutations.js';

Vue.use(Vuex); // vuex를 플러그인으로 사용하기 때문에

export const store = new Vuex.Store({
    state: {
      token: null,
      error:'',

      movie_list : [],
      max_page: 0,
      filter:'',

      snackbar:{
        status: false,
        msg:'',
        color:'',
      },

      activationCompleted: false,
      activationError: false,
      activationLoading: false,
      registrationCompleted: false,
      registrationError: false,
      registrationLoading: false,
    },
    getters: {
    },
    mutations,
    actions,
});