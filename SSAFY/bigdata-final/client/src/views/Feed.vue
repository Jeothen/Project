<template>
  <v-container
    grid-list-xl
  >
    <v-layout wrap>
      <v-flex xs12>
        <slot />
      </v-flex>

      <v-flex v-for="(card,i) in m_list"
      :key="card.id"
      :value="card"
        pa-2>
        <Feedcard
          :id="card.id"
          :img="card.img"
          :title="card.title"
          :genres="card.genres"
          :rating="card.rating"
          :view-cnt="card.viewCnt"
          :slug="card.slug"
          :poster="card.poster"
          :runtime="card.runtime"
          :director="card.director"
          :writer="card.writer"
          :actor="card.actor"
          :plot="card.plot"
          :country="card.country"
        />

      </v-flex>
    </v-layout>

  </v-container>
</template>

<script>
  // Utilities
  import {
    mapState
  } from 'vuex'
  import Feedcard from './Feedcard'
  export default {
    name: 'Feed',
    components: {
      Feedcard,
    },
    props:{
      movieListCards: {
        type: Array,
        default: () => new Array(),
      }
    },
    data: () => ({
      layout: [2, 2, 1, 2, 2, 3, 3, 3, 3, 3, 3],
      page: 1,
      m_list:[],
      pages:0
    }),

    computed: {
      p () {
        this.$store.dispatch("RAN_MOVIE").then((res)=>{
          this.m_list=res
          console.log(this.m_list)
          this.pages=(Object.keys(this.m_list).length)
        })
      },
    },

    watch: {
      page () {
        window.scrollTo(0, 0)
      },
    }
  }
</script>
