<template>
  <v-layout>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-flex xs12 text-xs-center>
        <v-btn round color="#32cd32" dark v-on="on" style="width:100%;"><v-icon size="25" class="mr-2">widgets</v-icon>회원 로그인</v-btn>
        <v-btn round color="#df4a31" dark v-on:click="loginWithGoogle" style="width:100%;"><v-icon size="25" class="mr-2">fa-google</v-icon> Google 로그인</v-btn>
          <v-btn round color="#3b5998" dark v-on:click="loginWithFacebook" style="width:100%;"><v-icon size="25" class="mr-2">fa-facebook</v-icon> Facebook 로그인</v-btn>
         
        </v-flex>
      </template>
      
      <v-card>
        <v-card-title>
          <span class="headline">Login</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              
              <v-flex xs12>
                <v-text-field label="Email*" v-model="myEmail" required></v-text-field>
              </v-flex>
              <v-flex xs12>
                <v-text-field label="Password*" v-model="myPassword" type="password" required></v-text-field>
              </v-flex>
            </v-layout>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" flat v-on:click="toggleSignIn" >Sign In</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
import FirebaseService from '@/services/FirebaseService'
import firebase from 'firebase/app';
import 'firebase/auth';
  export default {
    data: () => ({
      dialog: false,
      myEmail: "",
      myPassword: "",
      
    })
    ,
    methods:{
      async loginWithGoogle() {
			const result = await FirebaseService.loginWithGoogle()
      this.$store.state.accessToken = result.credential.accessToken
      this.$store.state.user = result.user
      this.$router.push('/') 
    },
		async loginWithFacebook() {
			const result = await FirebaseService.loginWithFacebook()
			this.$store.state.accessToken = result.credential.accessToken
      this.$store.state.user = result.user
      this.$router.push('/') 
    },
      	toggleSignIn() {
    var that=this;
      if (firebase.auth().currentUser) {
        // [START signout]
        firebase.auth().signOut();
        // [END signout]
        
      } else {
        var email = this.myEmail;
		var password = this.myPassword;
		
      if (email==null||email.length < 4) {
          alert('Please enter an email address.');
          return;
        }
        if (email==null||password.length < 4) {
          alert('Please enter a password.');
          return;
        }
              if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
      {
       }else{
         alert("You have entered an invalid email address!")
          return;
       }
        // Sign in with email and pass.
        // [START authwithemail]
        
      firebase.auth().signInWithEmailAndPassword(email, password).then(function(result){
        		//that.$store.state.accessToken = result.credential.accessToken
			      that.$store.state.user = result.user
            alert("로그인 성공!")
            //alert(that.$store.state.user)
            var user = firebase.auth().currentUser;

            if (user != null) {
              user.providerData.forEach(function (profile) {
              console.log("Sign-in provider: " + profile.providerId);
              console.log("  Provider-specific UID: " + profile.uid);
              console.log("  Name: " +   profile.displayName);
              console.log("  Email: " + profile.email);
              console.log("  Photo URL: " + profile.photoURL);
            });
}
            
          that.dialog=false;
          that.myEmail="";
          that.myPassword="";
        that.$router.push('/') 
      }).catch(function(error) {
          // Handle Errors here.
          var errorCode = error.code;
          var errorMessage = error.message;
          alert("로그인 실패!")
          // [START_EXCLUDE]
          if (errorCode === 'auth/wrong-password') {
            alert('Wrong password.');
          } else {
            alert(errorMessage);
          }
          console.log(error);
          // [END_EXCLUDE]
        });
        // [END authwithemail]
      }
     

      
    }	
    }
  }
</script>
