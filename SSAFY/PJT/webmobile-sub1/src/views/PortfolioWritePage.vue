<template>
  <div>
    <v-container>
      <v-layout>
        <v-flex xs12>
          <PortfolioWrite></PortfolioWrite>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import PortfolioWrite from '../components/PortfolioWrite.vue'

export default{
  name : 'PortfolioWritePage',
  components:{
    PortfolioWrite,
  },
}
</script>

<style lang="css" scoped>
</style>
