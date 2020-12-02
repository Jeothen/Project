<template>
  <div>
    <v-card
    max-width="800"
    class="mx-auto"
  >
    <v-system-bar color="pink darken-2"></v-system-bar>
      <div class="flex-grow-1"></div>
    <v-container
      class="pa-2"
      fluid
    >
      <v-row>
        <!-- <v-col>
          <v-card
            color="#385F73"
            dark
          >
            <v-card-text class="white--text">
              <div class="headline mb-2">Unlimited music now</div>
              Listen to your favorite artists and albums whenever and wherever, online and offline.
            </v-card-text>

            <v-card-actions>
              <v-btn text>Listen Now</v-btn>
            </v-card-actions>
          </v-card>
        </v-col> -->
        <!-- {{items_title}} -->
        <v-col
          v-for="(item, i) in items_title[0]"
          :key="i"
        >
          <v-card
            orange
          >
            <v-list-item three-line>
              <v-list-item-content class="align-self-start">
                <v-list-item-title
                  class="headline mb-2"
                  v-text="items_title[0][i]"
                ></v-list-item-title>

                <v-list-item-subtitle v-text="items_genre[0][i]"></v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-avatar
                size="125"
                tile
              >
                <!-- <v-img :src="item.src"></v-img> -->
              </v-list-item-avatar>
            </v-list-item>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
  </div>
</template>

<script>
import 'axios'
import { mapGetters } from 'vuex';
// import session from './session'

export default {
  components:{
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
    items_title: [
     ],
    items_genre:[],
  }),
  created() {
    this.$store.dispatch('INITIALIZE').then((res)=>{
      console.log('userid값')
      // console.log(this.$store.state.profile.id)
      var id= this.$store.state.profile.id
      console.log(id)
      this.$store.dispatch('RECO_MOVIE',id).then((res)=>{
        console.log('값들고옴')
        console.log(res)
        this.items_title.push(res.title)
        console.log(this.items_title)
        this.items_genre.push(res.genres)
      })
      // session.get(`${apiUrl}/movie/mf/1/`).then((res)=>{
      //   console.log(res.data)
      // })
    })
  },
  methods: {
  },
  computed: {
    ...mapGetters([
      'isAuthenticated',
    ])
  },

};
</script>

<style>
#keep .v-navigation-drawer__border {
  display: none;
}
</style>
