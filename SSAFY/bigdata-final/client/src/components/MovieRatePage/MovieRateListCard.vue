<template>
   <v-hover v-slot:default="{ hover }">
    <v-card :elevation="hover ? 8 : 2">
      <v-layout align-center py-4 pl-4>
        <v-flex text-center>
          <v-container grid-list-lg pa-0>
            <v-layout style="margin-right:8px;" column>
              <v-list-item>
                <v-list-item-content>
                <v-col cols="5">
                  <v-img
                  :src="poster"
                  style="cursor:pointer;"
                  @click="movieDetail(slug)"
                  aspect-ratio="0.7"
                  class="lighten-2"
                  ></v-img>
                </v-col>

                <v-col cols="5" style="text-align: left; margin-left:50px; margin-top:-45px;">
                 <v-list-item-title style="line-height: 4;" class="headline" @click="movieDetail(slug)">
                    <a style="color: black;">{{ title }}</a>
                  </v-list-item-title>
                  <v-list-item-subtitle style="line-height: 2;">{{ country }}</v-list-item-subtitle>
                  <v-list-item-subtitle style="line-height: 2;">{{ genresStr }}</v-list-item-subtitle>
                  <v-list-item-subtitle style="line-height: 2;">{{ runtime }}</v-list-item-subtitle>
                  <v-list-item-subtitle style="line-height: 2;">{{ director }}</v-list-item-subtitle>
                  <v-list-item-subtitle style="line-height: 2;">{{ actor }}</v-list-item-subtitle>
                  
                </v-col>
                <v-list-item-subtitle>{{ plot }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-card-text>
                <v-layout justify-center>
                  <v-rating
                    :value="currentRating"
                    color="indigo"
                    background-color="indigo"
                    half-increments
                    dense
                    readonly
                  />
                  <div v-if="currentRating" class="grey--text ml-4">
                    {{ currentRating.toFixed(1) }}</div>

                  <div v-else class="grey--text ml-4">
                  0.0</div>
                </v-layout>
              </v-card-text>
              <v-card-text>
                <v-layout justify-center>
                  <v-icon color="black">mdi-eye</v-icon>
                  <div class="grey--text ml-4">{{ viewCnt }}</div>
                </v-layout>
              </v-card-text>
              <v-list-item>
                <v-select
                    v-if="!chk(id)"
                    v-model="myRating"
                    :items="items"
                    label="나의 평점"
                ></v-select>
                &nbsp;
                <!-- <v-flex  -->
                  <!-- v-for="i in mylist" -->
                  <!-- :key = i> -->
                  <v-btn v-if="chk(id)" color="success" @click="delRate">나의 평점 삭제</v-btn>
                  <v-btn v-else color="primary" @click="createRate">평가</v-btn>
                <!-- </v-flex> -->
              </v-list-item>
            </v-layout>
          </v-container>
        </v-flex>
      </v-layout>
    </v-card>
  </v-hover>
</template>

<script>
import 'axios'


export default {
  data: () => ({
      items: [5, 4, 3, 2, 1],
      myRating:'',
      currentRating:'',
      flag : "",
      visitlist : [],
  }),

  props: {
    id: {
      type: Number,
      default: 0
    },
    title: {
      type: String,
      default: ""
    },
    genres: {
      type: Array,
      default: () => new Array()
    },
    img: {
      type: String,
      default: ""
    },
    rating: {
      type: Number,
      default: 0.0
    },
    viewCnt: {
      type: Number,
      default: 0
    },
    slug: {
      type: String,
      default: "",
    },
      poster: {
      type: String,
      default: "",
    },
    runtime: {
      type: String,
      default: "",
    },
    director: {
      type: String,
      default: "",
    },
    writer: {
      type: String,
      default: "",
    },
    actor: {
      type: String,
      default: "",
    },
    plot: {
      type: String,
      default: "",
    },
    country: {
      type: String,
      default: "",
    },
    mylist: {
      type: Array,
      default: [],
    },

    // user_movies: {
      // type: Object,
      // default: [],
    // },
  },
  created(){
      this.currentRating = this.rating
      this.visitlist = this.mylist
      // console.log(this.visitlist)
      // console.log(this.$store.state.profile)
      // console.log(this.$store.state.profile.user_visited)
  },
  
  computed: {
    genresStr() {
      return this.genres.join(" / ");
    },

  },
  methods: {
    chk(id){
      if ( this.visitlist.includes(id) ){
        return true
      }
      return false
    },

    movieDetail(slug){
      this.$router.push({
        path:`${slug}`
      })
    },

    delRate: function () {
      const payload = {
            movie : this.id,
            user : this.$store.state.profile.id,
      }

      this.$store.dispatch("DELRATE", payload)
        .then((res) => {
          console.log('삭제성공',res)
          this.currentRating = res.data.rating__avg
          this.visitlist = res.visit
          }).catch(err => {
              console.log('에러',err)
          })
    },

    createRate: function (){
      // console.log('gg')
        console.log(this.id, this.$store.state.profile.id, this.myRating)
        const payload = {
          movie : this.id,
          user : this.$store.state.profile.id,
          rating : this.myRating
        }

        this.$store.dispatch("CREATERATE", payload)
            .then((res) => {
              console.log('성공',res)
              this.currentRating = res.data.rating__avg
              // mylist 갱신위해 서버에 요청
              console.log('본 영화들',res.visit)
              this.visitlist = res.visit

            }).catch(err => {
              console.log('에러',err)
            })

    },
  }
}
</script>

<style>
.v-image__image {
  background-size: contain;
}
.v-responsive {
  background-color: white !important;
}

</style>