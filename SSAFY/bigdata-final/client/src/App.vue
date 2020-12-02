<template>
  <v-app id="app" v-bind:style="{ backgroundImage: `url(${color})`}">
    <v-app-bar app clipped-left flat :color="elevation-0" style="background-color: #ffffff; background-color: rgba( 255, 255, 255, 0 );" flat=flase>
      <!-- <v-app-bar-nav-icon class="white--text" @click="drawer = !drawer" /> -->
      <v-btn class="title ml-3 mr-5 white--text"  style="background-color: #ffffff; background-color: rgba( 255, 255, 255, 0 );" href="/home">Movie</v-btn>
      <!-- <a class="title ml-3 mr-5 white--text" @click="searchpage">영화 검색하기</a> -->
      <span class="title ml-3 mr-5 white--text">{{ $store.state.profile.username }}</span>
      <v-btn color="success" v-if="$store.state.profile.username"><router-link style="text-decoration: none; color: white;" to="/movie/rate">영화 평가하기</router-link></v-btn>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <subscription></subscription>
      <v-spacer />
      <register-modal v-show="!isAuthenticated"></register-modal>
      <login-page v-show="!isAuthenticated"></login-page>
      <logout-btn v-show="isAuthenticated"></logout-btn>
    </v-app-bar>
    <v-content v-if="!$store.state.profile.username">
      <br><br><br>
      <h1 style="color:white">로그인 후 영화 추천을 이용하세요~!</h1>
    </v-content>
    <v-content v-else>
      <v-container fluid fill-height class="lighten-4">
        <v-layout justify-center align-center>
          <router-view />
        </v-layout>
      </v-container>
    </v-content>
    <subscribe/>
    <br>
    <v-footer
      class="py-4"
      dark
      height="auto"
    >
      <v-container mx-auto>
        <v-layout wrap>
          <h4 style="color:white">@정상영 @김동민 @정태현 @윤영우</h4>
          <v-spacer />
          <base-btn
            class="mr-0"
            square
            title="Go to top"
            @click="$vuetify.goTo(0)"
          >
          <v-icon>mdi-chevron-up</v-icon>
          </base-btn>
        </v-layout>
      </v-container>
    </v-footer>
  </v-app>
</template>

<script>
import RegisterModal from './components/AuthenticationPage/RegisterModal'
import LoginPage from './components/AuthenticationPage/LoginPage'
import LogoutBtn from './components/AuthenticationPage/LogoutBtn'
import { mapGetters } from 'vuex';
import subscription from './components/header/subscription'
import subscribe from './views/Subscribe'
export default {
  components:{
    RegisterModal,
    LoginPage,
    LogoutBtn,
    subscription,
    subscribe,
  },
  data: () => ({
    drawer: null,
    choices: [
      {
        icon: "mdi-movie",
        text: "영화 검색",
        path: "movie-search"
      }
    ],
    color:'https://assets.nflxext.com/ffe/siteui/acquisition/ourStory/fuji/desktop/hero-background.jpg'
  }),
  created() {
    this.$store.dispatch('INITIALIZE')
    .then(() =>
      {console.log(this.$store.state.profile.user_visited)
      this.$router.push('/main')
      } )
  },
  methods: {
    searchpage: function() {
      this.$router.push('/movie/search')
    }
  },
  computed: {
    ...mapGetters([
      'isAuthenticated',
    ])
  }
};
</script>

<style>

#keep .v-navigation-drawer__border {
  display: none;
}
.router-link-active {
  color: black;
  text-decoration: none;
}
.router-link-exact-active {
  color: black;
  text-decoration: none;
}
#app {
  display: flex;
  height: 100%;
  justify-content: center;
  width: 100;
  align-items: center;
  flex-direction: column;
  background-size: cover;
}
.font_turrent{
  font-family: 'Turret Road', cursive;
}
.font_oswa{
  font-family: 'Oswald', sans-serif;
}
</style>
