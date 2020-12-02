/* eslint-disable */
import Vue from 'vue'
import VueRouter from 'vue-router'
import VueSession from 'vue-session'

import HomePage from '../components/pages/HomePage'
import SignupPage from '../components/pages/SignUpPage'
import LoginPage from '../components/pages/LoginPage'
import CreateItemPage from '../components/pages/CreateItemPage'
import CreateBlendPage from '../components/CreateBlendPage'
import MyPage from '../components/pages/MyPage'

import AdminPage from '../components/pages/AdminPage'
import AdminUser from '../components/AdminUser'
import AdminItem from '../components/AdminItem'
import AdminBlend from '../components/AdminBlend'
import AdminSubs from '../components/AdminSubs'
import AdminDeli from '../components/AdminDeli'

import RatingPage from '../components/pages/RatingPage'
import FilterPage from '../components/pages/FilterPage'

Vue.use(VueRouter)
Vue.use(VueSession)

const router = new VueRouter({
    mode: 'history',
    routes: [
        { path: "", component: HomePage, name: 'home' },
        { path: "/signup", component: SignupPage, name: 'signup' },
        { path: '/login', component: LoginPage, name: 'login' },
        { path: "/mypage", component: MyPage, name: 'mypage' },
        { path: "/createitem", component: CreateItemPage, name: 'createitem' },
        { path: "/createblend", component: CreateBlendPage, name: 'createblend' },
        { path: "/admin", component: AdminPage, name: 'admin' },
        { path: "/admin/user", component: AdminUser, name: 'adminuser' },
        { path: "/admin/item", component: AdminItem, name: 'adminitem' },
        { path: "/admin/subs", component: AdminSubs, name: 'adminsubs' },
        { path: "/admin/blend", component: AdminBlend, name: 'adminblend' },
        { path: "/admin/deli", component: AdminDeli, name: 'admindeli' },
        { path: "/rating", component: RatingPage, name: 'rating' },
        { path: "/filtering", component: FilterPage, name: 'filtering' },
    ],
    scrollBehavior() {
        return { x: 0, y: 0 }
    },
})

export default router