<template>
  <v-hover v-slot:default="{ hover }">
    <v-card :elevation="hover ? 8 : 2" @click="userDetail(slug)">
      <v-layout align-center py-4 pl-4>
        <v-flex text-center>
          <v-container grid-list-lg pa-0>
            <v-layout column>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title class="headline">{{ id }} | {{ username }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              
              <v-card-text>
                <span>성별 : <span class="title">{{ gender }}</span></span>
                <span>나이 : <span class="title">{{ age }}</span></span>
                <span v-if="is_staff">권한 : <span class="title">관리자</span></span>
                <span v-else>권한 : <span class="title">일반</span></span>
              </v-card-text>
              <!-- <v-card-text>

              </v-card-text> -->
            </v-layout>
          </v-container>
        </v-flex>
      </v-layout>

      <UserModal 
        :dialog="dialog"
        :username="username"
        :is_staff="is_staff"
        :gender="gendered"
        :occupation="occupation"
        :age="age"
        :slug="slug"
        :id="id"
        @close="close"></UserModal>
      <!-- ===================모달====================== -->
    </v-card>
  </v-hover>
</template>

<script>
import UserModal from './UserModal'
export default {
  components:{
    UserModal,
  },
  data() {
    return {
      dialog:false,
    };
  },
  props: {
    id: {
      type: Number,
      default: 0,
    },
    username: {
      type: String,
      default: "",
    },
    gender: {
      type: String,
      default: "",
    },
    age: {
      type: Number,
      default: 0,
    },
    occupation: {
      type: String,
      default: ""
    },
    slug: {
      type: String,
      default: "",
    },
    is_staff: {
      type: Boolean,
    }
  },
  methods: {
    userDetail(slug) {
      this.dialog = true;
    },
    close() {
      this.dialog = false;
    },
    editf(userinfo) {
      console.log('gg')
      // this.username = userinfo.username
      // this.age = userinfo.age
      // this.gender = userinfo.gender
      // this.occupation = userinfo.occupation
    }
  },
  computed: {
    gendered(){
      if (this.gender === 'M') {
        return 'Male'
      } else {
        return 'Female'
      }
      
    }
  }
};
</script>