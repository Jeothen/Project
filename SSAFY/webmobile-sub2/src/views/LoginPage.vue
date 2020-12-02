<template>

 <v-container>
 <v-layout row align-center justify-center class="py-5">

                            
 </v-layout>

  <v-layout align-center justify-center row fill-height>
    <v-flex xs5 text-xs-center>
      <v-layout align-center justify-center row fill-height elevation-5 style="min-height:500px;" white pa-4>
        <v-flex xs12 text-xs-center>
          <SignIn></SignIn>
          <SignUp></SignUp>
		    </v-flex>
      </v-layout>
    </v-flex>
  </v-layout>
 </v-container>
</template>

<script>
const axios = require('axios')
import SignUp from '../components/SignUp'
import SignIn from '../components/SignIn'

export default {
  name: 'LoginPage',
  mounted(){
    this.aa()
  },
  data: () => ({
    
  }),
	components: {
    SignUp,
    SignIn
  },
  
	methods: {
    aa : function() {
      console.log('azz')
      axios.get("https://us-central1-webmobile-sub2-639ef.cloudfunctions.net/addMessage?text='로그인 페이지 방문'")
    }
  },
  created:function(){
      if (this.$store.state.user!=null) {
        alert("이미 로그인 되어있습니다.\n로그아웃 후 이용해주세요.")
        this.$router.push('/');
      }
    
  }
}
</script>
