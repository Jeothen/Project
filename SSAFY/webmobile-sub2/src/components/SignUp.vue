<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="#008080" dark v-on="on">Sign Up</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">User Profile</span>
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
          <v-btn color="blue darken-1" flat v-on:click="handleSignUp">Sign Up</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
import FirebaseService from '@/services/FirebaseService'
import firebase from 'firebase/app';
import 'firebase/auth';
import { async } from 'q';
  export default {
    data: () => ({
      dialog: false,
      myEmail: "",
      myPassword: "",
      
    })
    ,
    methods:{
     handleSignUp() {
        var that=this;
        var email=this.myEmail;
        var password=this.myPassword;
      if (email==null||email.length < 4) {
        alert('Please enter an email address.');
        return;
      }
      if (password==null||password.length < 4) {
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
      // [START createwithemail]
      firebase.auth().createUserWithEmailAndPassword(email, password).then(function()
      {
          alert('회원가입이 완료되었습니다.');
      // [END createwithemail]
      
      that.dialog=false;
      that.myEmail="";
      that.myPassword="";
    
      }).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // [START_EXCLUDE]
        if (errorCode == 'auth/weak-password') {
          alert('The password is too weak.');
          return;
        } else{
          alert(errorMessage);
          return;
        }
      
        console.log(error);
        // [END_EXCLUDE]
      });
    }
      
    }
  }
</script>
