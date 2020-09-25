<template>
   <v-hover v-slot:default="{ hover }">
    <v-card :elevation="hover ? 8 : 2">
      <v-layout align-center py-4 pl-4>
        <v-flex text-center>
          <v-container grid-list-lg pa-0>
            <v-layout column>
              <v-list-item>
                <v-list-item-content>
                  <v-col cols="5">
                  <v-img
                  :src="poster"
                  style="height:200px; width:300px;"
                  aspect-ratio="1"
                  class="grey lighten-2"
                  ></v-img>
                </v-col>

                <v-col cols="5">
                  <v-list-item-title class="headline" @click="movieDetail(slug)">
                    {{ title }}
                  </v-list-item-title>
                  <v-list-item-subtitle>{{ country }}</v-list-item-subtitle>
                  <v-list-item-subtitle>{{ genresStr }}</v-list-item-subtitle>
                  <v-list-item-subtitle>{{ runtime }}</v-list-item-subtitle>
                  <v-list-item-subtitle>{{ director }}</v-list-item-subtitle>
                  <v-list-item-subtitle>{{ actor }}</v-list-item-subtitle>
                  </v-col>
                <v-list-item-subtitle>{{ plot }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-card-text>
                <v-layout justify-center>
                  <v-rating
                    :value="rating"
                    color="indigo"
                    background-color="indigo"
                    half-increments
                    dense
                    readonly
                  />
                  <div v-if="rating" class="grey--text ml-4">
                    {{ rating.toFixed(1) }}</div>

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

            </v-layout>
          </v-container>
        </v-flex>
      </v-layout>
    </v-card>
  </v-hover>
</template>

<script>
export default {
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
  },
  computed: {
    genresStr() {
      return this.genres.join(" / ");
    },
  },
  methods: {
    movieDetail(slug){
      this.$router.push({
        path:`${slug}`
      })
    }
  }
}
</script>
