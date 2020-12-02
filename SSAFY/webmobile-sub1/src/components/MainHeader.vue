<template>
  <div class ="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand"><router-link to ="/">Quickview's Blog</router-link></a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li><router-link to ="./Post">Post</router-link></li>
          <li><router-link to ="./Portfolio">Portfolio</router-link></li>
          <li><a @click="addFavorit">Add Favorit</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="./login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
      </div>
    </div>
  </div>
</template>


<script>
export default {
   name: 'main-header',
   methods:{
     addFavorit(){
       // Internet Explorer
      if(document.all)
      {
        eval("window.external.AddFa-vorite('http://localhost:8080', Quickview's Blog)".replace(/-/g,''));
	    }
      // Google Chrome
      else if(window.chrome){
         alert("Ctrl+D키를 누르시면 즐겨찾기에 추가하실 수 있습니다.");
      }
      // Firefox
      else if (window.sidebar) // firefox
      {
          window.sidebar.addPanel('http://localhost:8080/',"Quickview's Blog", "");
      }
      // Opera
      else if(window.opera && window.print)
      { // opera
         var elem = document.createElement('a');
         elem.setAttribute('href','http://localhost:8080');
         elem.setAttribute('title',"Quickview's Blog");
         elem.setAttribute('rel','sidebar');
         elem.click();
      }

     }

   }
}


</script>


<style lang="css" scoped>
</style>
