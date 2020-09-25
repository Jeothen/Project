<template>
  <v-container
    id="subscribe"
    tag="section"
    style="background-color: #E6EE9C"
  >
    <base-card
      color="rgba(203, 170, 92, 0.51)"
      class="pa-3"
    >
      <v-container
        grid-list-xl
        pa-0
      >
        <v-layout wrap>
          <v-flex
            xs12
            md5
          >
            <base-subheading class="mb-3">
              Subscribe
            </base-subheading>
            <p>
              Enter your email address to subscribe to this blog
              and receive notifications of new posts by email.
            </p>

            <v-layout
              wrap
              pa-2
            >
              <v-text-field
                solo
                hide-details
                style="max-width: 400px;"
                v-if="!$store.state.profile.is_subscription"
              />
              <v-btn
                :block="$vuetify.breakpoint.xsOnly"
                class="ma-0"
                color="secondary"
                style="height: 48px"
                @click="subscribe"
                v-if="!$store.state.profile.is_subscription"
              >
                Subscribe
              </v-btn>
              <v-text style="font-weight:bold;" v-else>구독 완료!</v-text>
            </v-layout>
          </v-flex>
          <v-flex
            xs12
            md6
            offset-md1
          >
            <base-subheading>Contact</base-subheading>
            <v-list class="transparent">
              <v-list-tile>
                <v-list-tile-avatar
                  color="primary"
                >
                  <v-icon
                    class="elevation-4"
                    dark
                  >
                    mdi-email-outline
                  </v-icon>
                </v-list-tile-avatar>
                <v-list-tile-title>EMAIL</v-list-tile-title>
                <v-list-tile-sub-title>
                  THEARTOFTRAVEL@GMAIL.COM
                </v-list-tile-sub-title>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-avatar
                  color="primary"
                >
                  <v-icon
                    class="elevation-4"
                    dark
                  >
                    mdi-map-marker
                  </v-icon>
                </v-list-tile-avatar>
                <v-list-tile-title>ADDRESS</v-list-tile-title>
                <v-list-tile-sub-title>
                  1234 WORLD DR. HERE, TX 76123
                </v-list-tile-sub-title>
              </v-list-tile>
              <v-list-tile>
                <v-list-tile-avatar
                  color="primary"
                >
                  <v-icon
                    class="elevation-4"
                    dark
                  >
                    mdi-phone
                  </v-icon>
                </v-list-tile-avatar>
                <v-list-tile-title>PHONE</v-list-tile-title>
                <v-list-tile-sub-title>
                  555-789-1234
                </v-list-tile-sub-title>
              </v-list-tile>
            </v-list>
          </v-flex>
        </v-layout>
      </v-container>
    </base-card>
  </v-container>
</template>
<script>
export default {
  name: 'Subscribe',
  components: {
  },
  props:{
  },
  data: () => ({
    flag : 0
  }),

  computed: {
  },

  watch: {
  },
  methods:{
    subscribe() {
      this.$store.dispatch('SUBSCRIPTION')
      this.flag=1
    }
  }
}
</script>
