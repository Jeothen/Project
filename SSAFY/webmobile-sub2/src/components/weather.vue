<template>
<weather
       api-key="f3878217fcb9bcfb41a13f5e6ec031c6"
       title="Weather"
       :latitude="latitude"
       :longitude="longitude"
       language="ko"
       units="si">
      </weather>
</template>
<script>
  import VueWeatherWidget from 'vue-weather-widget'
  import 'vue-weather-widget/dist/css/vue-weather-widget.css'

  export default{
    data(){
      return{
        latitude: '0',
        longitude: '0'
      }
    },
    components:{
      'weather': VueWeatherWidget
    },
    mounted(){
      var vm = this
      navigator.geolocation.getCurrentPosition(function(pos){
        vm.latitude = pos.coords.latitude.toString()
        vm.longitude = pos.coords.longitude.toString()
        navigator.geolocation.clearWatch(pos)
      })
    }
  }
</script>