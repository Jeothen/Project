import Vue from 'vue';
import Vuex from 'vuex';

import actions from './actions.js';
import mutations from './mutations.js';

Vue.use(Vuex); // vuex를 플러그인으로 사용하기 때문에

export const store = new Vuex.Store({
    state: {
      token: false,
      
      profile: [],
      movie_list : [],
      user_list: [],
      max_user_page: 0,
      max_page: 0,
      filter:'',

      
      snackbar:{
        status: false,
        msg:'',
        color:'',
      },
      
      loginerror:false,
      authenticating:false,
      registrationCompleted: false,
      registrationError: false,
      registrationLoading: false,
      auth_error:false,
    },
    getters: {
      isAuthenticated(state) {
        return !!state.token
      },
    },
    mutations,
    actions,
});