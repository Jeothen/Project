<template>
  <v-container class="mx-auto">
    <v-row>
    <v-col cols="12" md="3">
      <v-navigation-drawer permanent>
        <v-list dense>
          <v-list-item>
            <v-list-item-avatar>
              <i class="fas fa-tools"></i>
            </v-list-item-avatar>
            <h1>Admin</h1>
          </v-list-item>

          <v-divider></v-divider>

          <template v-for="item in items">
            <v-list-item :key="item.title" link @click="choicepath(item)" style="text-align:center">
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-list>
      </v-navigation-drawer>
    </v-col>
    <v-col cols="12" md="9">
      <v-content>
        <v-layout align-center>
          <AdminItem id="adminitem" style="display:none;"/>
          <AdminUser id="adminuser" style="display:none;"/>
          <AdminBlend id="adminblend" style="display:none;"/>
          <AdminSubs id="adminsubs" style="display:none;"/>
          <AdminDeli id="admindeli" stlye="display:none"/>
        </v-layout>
      </v-content>
    </v-col>
    </v-row>
  </v-container>
</template>

<script>
import router from "../../router"
import axios from 'axios'
import AdminItem from "../AdminItem"
import AdminUser from "../AdminUser"
import AdminSubs from "../AdminSubs"
import AdminBlend from "../AdminBlend"
import AdminDeli from "../AdminDeli"
import CreateBlendPage from "../CreateBlendPage"

export default {
  components: {
    AdminItem,
    AdminUser,
    AdminSubs,
    AdminBlend,
    AdminDeli,
    CreateBlendPage,
  },
  data() {
    return {
      users: [],
      drawer: true,
      items: [
        { title: 'Home', icon: 'mdi-home-city', path:0 },
        { title: '유저 관리', icon: 'mdi-account-group-outline', path:1 },
        { title: '원두 관리', icon: 'mdi-coffee', path:2 },
        { title: '블렌드 관리', icon: 'mdi-cart-outline', path:3 },
        { title: '구독 관리', icon: 'mdi-bell', path:4 },
        { title: '이 달의 블렌드', icon: 'mdi-calendar-check', path: 5 },
      ],
    }
  },
  mounted() {
    // this.getList();
    this.flagall()
  },
  methods: {
    // user detail 정보 불러오기
    flagall() {
      document.getElementById("adminuser").style.display = "none"
      document.getElementById("adminitem").style.display = "none"
      document.getElementById("adminblend").style.display = "none"
      document.getElementById("adminsubs").style.display = "none"
      document.getElementById("admindeli").style.display = "none"
    },
    goTo: function(path) {
      if(path==1) {
        document.getElementById("adminuser").style.display = "block"
        document.getElementById("adminitem").style.display = "none"
        document.getElementById("adminblend").style.display = "none"
        document.getElementById("adminsubs").style.display = "none"
        document.getElementById("admindeli").style.display = "none"
      } else if(path==2) {
        document.getElementById("adminuser").style.display = "none"
        document.getElementById("adminitem").style.display = "block"
        document.getElementById("adminblend").style.display = "none"
        document.getElementById("adminsubs").style.display = "none"
        document.getElementById("admindeli").style.display = "none"
      } else if(path==3) {
        document.getElementById("adminuser").style.display = "none"
        document.getElementById("adminitem").style.display = "none"
        document.getElementById("adminblend").style.display ="block"
        document.getElementById("adminsubs").style.display = "none"
        document.getElementById("admindeli").style.display = "none"
      } else if(path==4) {
        document.getElementById("adminuser").style.display = "none"
        document.getElementById("adminitem").style.display = "none"
        document.getElementById("adminblend").style.display = "none"
        document.getElementById("adminsubs").style.display = "block"
        document.getElementById("admindeli").style.display = "none"
      } else if(path==5) {
        document.getElementById("adminuser").style.display = "none"
        document.getElementById("adminitem").style.display = "none"
        document.getElementById("adminblend").style.display = "none"
        document.getElementById("adminsubs").style.display = "none"
        document.getElementById("admindeli").style.display = "block"
      }
    },
    choicepath(choice) {
      if(choice.path==0) {
        router.push('/')
      }
      else {
        this.goTo(choice.path)
      }
    },
    async getList() {
      let __this = this;
      await axios.get('/api/subscription/manager/').then(res => {
        if (res.data) {
            // 구독 신청 후, 승인되지 않은 리스트 리턴
            __this.users = res.data
        }
      })
    },
    async permit(u_id, s_id, days) {
        await axios.post('/api/subscription/manager/', {
            'days': days,
            'user_id': u_id,
            'sub_id': s_id
        }).then(res => {
            console.log('변경 완료')
        })
    }
  }
}
</script>
