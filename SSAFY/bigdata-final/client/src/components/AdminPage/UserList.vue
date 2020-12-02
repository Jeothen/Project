<template>
  <v-container class="pa-2" fluid grid-list-md>
    <v-layout column>
      <v-flex v-for="user in user_list" :key="user.id" pa-2>
        <UserListCard
          :id="user.id"
          :username="user.username"
          :age="user.age"
          :gender="user.gender"
          :occupation="user.occupation"
          :is_staff="user.is_staff"
          :slug="user.slug"
        />
      </v-flex>
      <v-pagination :total-visible="10" v-if="max_user_page > 1" v-model="page" :length="max_user_page" />
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
import UserListCard from './UserListCard'

export default {
  components:{
    UserListCard,
  },
  props:{
    userListCards: {
      type: Array,
      default: () => new Array(),
    }
  },
  data(){
    return{
      page:0,
    }
  },
  computed: {
    ...mapState(['max_user_page','user_list','filter'])
  },
  watch: {
    page(){
      this.$store.dispatch('SEARCH_USER',this.filter+`&page=${this.page}`)
    }
  }
}
</script>

<style>

</style>