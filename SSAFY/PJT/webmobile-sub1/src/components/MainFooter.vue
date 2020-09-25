<template>
  <div class="footer-container">
      <p class="text-muted small mb-0">Copyright &copy; All Image <a href="https://source.unsplash.com/" target="_blank">unsplash</a> 2019</p>
      <p class="text-muted small mb-0">Copyright &copy; Oh Myung-Hyun 2019</p>
      <button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
    </div>

</template>

<script>
export default {
}
</script>

<style lang="css" scoped>
  .footer-container {
    text-align: center;
}
#myBtn{
display: none;
 position: fixed;
 bottom: 20px;
 right: 30px;
 z-index: 99;
 font-size: 18px;
 border: none;
 outline: none;
 background-color: #81aeff;
 color: white;
 cursor: pointer;
 padding: 15px;
 border-radius: 4px;
}
#myBtn:hover {
  background-color: #275bb9;
}
</style>
