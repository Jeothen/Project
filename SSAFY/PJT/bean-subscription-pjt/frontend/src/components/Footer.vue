<template>
  <v-footer
    padless
    id="footer"
    color="#5D4037"
  >
    <v-card
      flat
      tile
      class="brown darken-2 white--text text-center"
      id="footer-card"
    >
      <v-card-text>
        <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4 white--text"
          icon
        >
          <v-icon size="24px">{{ icon }}</v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
        SSAFY1기_대전2반<br>

      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        Copyright ⓒ {{ new Date().getFullYear() }} by <strong>황여진/이헌규/정태현/김동민</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>
<script>
export default {
  data: () => ({
      icons: [
        'fab fa-facebook',
        'fab fa-twitter',
        'fab fa-instagram',
      ],
    }),
}
</script>
<style>
  #footer {
    height:200px;
    margin-bottom: 0;
  }
  #footer-card {
    width:100%;
  }
</style>
