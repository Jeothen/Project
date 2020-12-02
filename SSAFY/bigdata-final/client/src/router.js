import Vue from 'vue';
import VueRouter from 'vue-router';

import MovieSearchPage from './views/MovieSearchPage';
import movierecommend from './views/movierecommend';
import MovieRatePage from './views/MovieRatePage';
import movieDetailPage from './views/detail/movieDetailPage';
import userDetailPage from './views/detail/userDetailPage';
import adminPage from './views/adminPage';
import { store } from './store/index';
import main from './views/main';
Vue.use(VueRouter)

// middleware
const requireAuthenticated = (to, from, next) => {
    store.dispatch('INITIALIZE')
        .then(() => {
            if (!store.getters['isAuthenticated']) {
                alert('로그인 해주세요')
                next('/home');
            } else {
                next();
            }
        });
};

const isAdmin = (to, from, next) => {
    store.dispatch('INITIALIZE')
        .then(() => {
            if (!store.state.profile.is_admin) {
                alert('권한 없음')
                next('/home');
            } else {
                next();
            }
        });
}

const isSubscription = (to, from, next) => {
    store.dispatch('INITIALIZE')
        .then(() => {
            if (!store.state.profile.is_subscription) {
                alert('구독 안하셨습니다.')
                next('/home')
            } else {
                next();
            }
        })
}

export const router = new VueRouter({
    mode: 'history',
    routes: [{
            path: '/',
            redirect: '/home'
        },
        {
          path:'/movie/reco',
          component: movierecommend,
          beforeEnter: isSubscription
        },
        {
          path:'/main',
          component: main,
        },
        {
            path:'/movie/rate',
            component: MovieRatePage,
          },
        {
            path: '/movie/search',
            component: MovieSearchPage,
            beforeEnter: requireAuthenticated,
        },
        {
            path: '/movie/:slug',
            component: movieDetailPage,
        },
        {
            path: '/auth/:slug',
            component: userDetailPage,
        },
        {
            path: '/administrate',
            component: adminPage,
            beforeEnter: isAdmin,
        },
    ]
})
