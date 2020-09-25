import Vue from 'vue';
import VueRouter from 'vue-router';

import MovieSearchPage from './views/MovieSearchPage'
import movieDetailPage from './views/detail/movieDetailPage';
import userDetailPage from './views/detail/userDetailPage';
Vue.use(VueRouter)

export const router = new VueRouter({
  mode:'history',
  routes: [
    {
      path:'/',
      redirect:'/home'
    },
    {
      path:'/movie/search',
      component: MovieSearchPage,
    },
    {
      path:'/movie/:slug',
      component: movieDetailPage,
    },
    {
      path:'/auth/:slug',
      component: userDetailPage,
    },
  ]
})