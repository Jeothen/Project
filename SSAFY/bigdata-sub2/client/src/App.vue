<template>
  <v-app id="app">
    <v-app-bar app clipped-left color="indigo" flat>
      <v-app-bar-nav-icon class="white--text" @click="drawer = !drawer" />
      <span class="title ml-3 mr-5 white--text">영화 추천 서비스</span>
      <v-spacer />
    </v-app-bar>

    <v-content>
      <v-container fluid fill-height class="grey lighten-4">
        <v-layout justify-center align-center>
          <router-view />
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>

export default {
  data: () => ({
    drawer: null,
    choices: [
      {
        icon: "mdi-movie",
        text: "영화 검색",
        path: "movie-search"
      }
    ]
  }),
  methods: {

  }
};
</script>

<style>
#keep .v-navigation-drawer__border {
  display: none;
}
</style>