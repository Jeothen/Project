<template>
  <v-hover v-slot:default="{ hover }">
    <v-card class="carder" :elevation="hover ? 8 : 2" @click="movieDetail(slug)">
      <v-layout align-center py-4 pl-4>
        <v-flex text-center>
          <v-container grid-list-lg pa-0>
            <v-layout column>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title class="headline">{{ title }}</v-list-item-title>
                  <v-list-item-subtitle>{{ genresStr }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-card-text>
                <v-layout justify-center>
                  <v-rating
                    :value="rating"
                    color="pink"
                    background-color="pink"
                    half-increments
                    dense
                    readonly
                  />
                  <div class="grey--text ml-4">{{ rating.toFixed(1) }}</div>
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

      <!-- ===================모달====================== -->
      <!-- <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="600px">
          <v-card>
            <v-card-title>
              <span class="headline">영화 수정하기</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field counter="60" label="Title" v-model="title" required></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field counter="60" label="Genres" v-model="genre" required></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="blue" text @click="remove">Delete</v-btn>
              <v-btn color="blue" text @click="edit">Edit</v-btn>
              <v-btn color="red darken-1" text @click="close">Close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row> -->
      <MovieModal 
        :dialog="dialog" 
        :title="title"
        :genre="genres"
        :slug="slug"
        @close="close"></MovieModal>
      <!-- ===================모달====================== -->
    </v-card>
  </v-hover>
</template>

<style scoped>
.carder{
  width:330px;
  display: inline-block;
}
</style>

<script>
import MovieModal from './MovieModal'
export default {
  components:{
    MovieModal
  },
  data() {
    return {
      dialog:false,
    };
  },
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
    // img: {
    //   type: String,
    //   default: ""
    // },
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
      default: ""
    }
  },
  computed: {
    genresStr() {
      return this.genres.join(" / ");
    }
  },
  methods: {
    movieDetail(slug) {
      this.dialog = true;
    },
    remove() {},
    edit() {},
    close() {
      this.dialog = false;
    }
  }
};
</script>

