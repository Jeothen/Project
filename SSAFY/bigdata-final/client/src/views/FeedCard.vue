<template>
  <v-flex
    xs12
  >
    <v-hover v-slot:default="{hover}">
    <base-card
      :height="450"
      color="grey lighten-1"
      dark
      href="#!"
    >
      <v-img
        :src="poster"
        height="300px;"
        width="100%"
        gradient="rgba(0, 0, 0, .42), rgba(0, 0, 0, .42)"
      >
      <v-expand-transition>
      <div
        v-if="hover === true"
        class="d-flex transition-fast-in-fast-out v-card--reveal"
        style="height: 300px; background-color:#B0BEC5; background-color: rgba( 0, 0, 0, .42);"
      >
         <h2 style="padding-top:40px; color:white; font-size:30px; text-align: center; letter-spacing: 2px;" class="mb-0 target">{{title}}</h2>
         <h2 style="padding-top:20px; padding-right:35px; color:white; font-size:15px; text-align: center;">{{genres}}</h2>
      </div>
      </v-expand-transition>
      </v-img>
    </base-card>
    </v-hover>
  </v-flex>
</template>

<script>
  export default {
    props: {
      size: {
        type: Number,
        required: true
      },
      value: {
        type: Object,
        default: () => ({})
      },
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
      classes () {
        return {
          'md6': this.size === 2,
          'md4': this.size === 3
        }
      }
    }
  }
</script>

<style>
.v-image__image {
  transition: .3s linear;
}
</style>
