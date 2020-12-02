<template>
  <v-layout>
    <v-flex xs12>
      <v-toolbar class="blue-grey darken-3" fixed dark>
        <v-btn icon large to="/">
          <v-icon>home</v-icon>
        </v-btn>

        <v-toolbar-title to="/" class="mainName">공수래공수거</v-toolbar-title>
        <v-spacer></v-spacer>

        <v-toolbar-items class="hidden-xs-only">
             <v-btn class="menus" href="#Entrance" flat>
            대문
            <span class="chinese">大門</span>
          </v-btn>
          <v-btn class="menus" href="#AboutUs" flat>
            미생
            <span class="chinese">未生</span>
          </v-btn>
          <v-btn class="menus" href="#MasterPiece" flat>
            명작
            <span class="chinese">名作</span>
          </v-btn>

          <v-card v-if="logincheck==true" class="text-md-right" >
          <div>
            <span><br/>반갑습니다. {{this.$store.state.user.email}}님 </span>
          </div>
          <v-btn flat color="orange" @click="logout">로그아웃</v-btn>
     
        </v-card>

                
        </v-toolbar-items>

        <v-menu class="hidden-sm-and-up">
          <v-toolbar-side-icon slot="activator">
            <v-icon>reorder</v-icon>
          </v-toolbar-side-icon>
          <v-list>
            <v-list-tile href="#AboutUs">
              <v-list-tile-content class="menus">대문</v-list-tile-content>
            </v-list-tile>
            <v-list-tile href="#AboutUs">
              <v-list-tile-content class="menus">미생</v-list-tile-content>
            </v-list-tile>
            <v-list-tile href="#AboutUs">
              <v-list-tile-content class="menus">명작</v-list-tile-content>
            </v-list-tile>
          </v-list>
        </v-menu>
      </v-toolbar>
    </v-flex>
  </v-layout>
</template>


<script>
import firebase from 'firebase/app';
import 'firebase/auth';
export default {
  name: "Myheader",
  props: {},
  data: () => ({
    menu: [
      { icon: "home", title: "홈", link: "/" },
      { icon: "portfolio", title: "포트폴리오", link: "#AboutUs" },
      { icon: "post", title: "포스트", link: "/post" },
      { icon: "login", title: "로그인", link: "/login" }
    ]
  }),
  methods: {
    logout(){
      var that=this;
      firebase.auth().signOut().then(function() {
      // Sign-out successful.
      alert("로그아웃 되었습니다!")
        that.$store.state.user=null;
      //alert(that.$store.state.user)
        
      }).catch(function(error) {
       // An error happened.
      alert("로그아웃에 실패하였습니다")
      alert(error)
      });
    },
    bookmark: function() {
      if (window.sidebar && window.sidebar.addPanel) {
        // Firefox <23
        window.sidebar.addPanel(document.title, window.location.href, "");
      } else if (window.external && "AddFavorite" in window.external) {
        // Internet Explorer
        window.external.AddFavorite(location.href, document.title);
      } else if (
        (window.opera && window.print) ||
        (window.sidebar && !(window.sidebar instanceof Node))
      ) {
        // Opera <15 and Firefox >23
        /**
         * For Firefox <23 and Opera <15, no need for JS to add to bookmarks
         * The only thing needed is a `title` and a `rel="sidebar"`
         * To ensure that the bookmarked URL doesn't have a complementary `#` from our trigger's href
         * we force the current URL
         */
        triggerBookmark
          .attr("rel", "sidebar")
          .attr("title", document.title)
          .attr("href", window.location.href);
        return true;
      } else {
        // For the other browsers (mainly WebKit) we use a simple alert to inform users that they can add to bookmarks with ctrl+D/cmd+D
        alert(
          "You can add this page to your bookmarks by pressing " +
            (navigator.userAgent.toLowerCase().indexOf("mac") != -1
              ? "Command/Cmd"
              : "CTRL") +
            " + D on your keyboard."
        );
      }
      // If you have something in the `href` of your trigger
      return false;
    }
  },
   computed: {
    logincheck(){
      this.$store.state.user=firebase.auth().currentUser;
       if(this.$store.state.user!=null){
         return true;
      }else{
          this.$store.state.user=null;
         return false;
       }
     }
   },

};
document.querySelectorAll('v-btn[href^="#"]').forEach(anchor => {
  console.log('Hi!')
  console.log(anchor)
  anchor.addEventListener("click", function(e) {
    e.preventDefault();
    console.log('Click!')

    document.querySelector(this.getAttribute("href")).scrollIntoView({
      behavior: "smooth"
    });
  });
});
</script>

<style>
.mainName {
  font-family: "Song Myung";
  /* font-size: 2vw; */
}

.menus {
  font-family: "Song Myung";
  font-size: 17px;
}

.chinese {
  font-family: "ZCOOL QingKe HuangYou", cursive;
}
</style>