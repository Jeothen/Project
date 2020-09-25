<template>

  <v-app>        
      <div id="warning_msg">
   <v-alert :value="myFunction()" type="warning">
     <v-icon>warning</v-icon> 해당 사이트는 크롬에 최적화 되어 있습니다.
    </v-alert>
   </div>
   <Myheader/>
    <v-content>

      <router-view/>
    </v-content>
     <GoTop/>

   



   <Myfooter/>   
  </v-app>
</template>

<script>
const axios = require('axios')
import firebase from 'firebase/app';
import 'firebase/auth';
import store from './store'
import Myfooter from './components/Myfooter'
import Myheader from './components/Myheader'
import GoTop from '@inotom/vue-go-top';
export default {
   name: 'App',
   store,
   components: {
   GoTop,
    Myfooter,
    Myheader,
   },
   data() {
     this.$store.state.user=null;
     this.$store.state.user=firebase.auth().currentUser;
      return {
         //
      }
   },
   methods:{
    myFunction: function () {
       var userAgent=navigator.userAgent.toLowerCase();
     if(userAgent.indexOf('edge')>-1){
       return true;
     }else if(userAgent.indexOf('whale')>-1){
       return true;
     }else if(userAgent.indexOf('opr')>-1){
       return true;
     }else if(userAgent.indexOf('chrome')>-1){
       return false;
     }else{
       return true;
     }

   }
   }
}


</script>
<style>
#warning_msg{
   z-index:1000;
   position: fixed;
  bottom:0px; 
  width: 100%;
}
</style>