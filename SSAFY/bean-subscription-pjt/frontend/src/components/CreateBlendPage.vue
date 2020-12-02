<template>
  <v-flex sm6 offset-sm3>
  <div id = "checkitem">
    <v-btn @click="createBlend(checked, content)">Blend 등록</v-btn>
    <v-text-field
      v-model="content"
      :rules="nameRules"
      label="content"
      required
    ></v-text-field>
    
    <v-flex>
      원두 이름 : <v-select
        :items="listData"
        name="listitem"
        label="Select a item"
        v-model="listitem"
        item-text="name"
        required>
      </v-select>
    </v-flex>
    <v-flex>
      비율 : <v-select
        :items="percents"
        v-model="value1"
        filled
        required
      ></v-select>
    </v-flex>
    <v-btn @click="addcart(listitem, value1, checked)">추가</v-btn><br>
    <div v-for="(check, i) in checked" v-bind:key="i">
      <p>{{check[0]}} : {{check[1]}}%</p>
    </div>
  </div>
  </v-flex>
</template>


<script>
import axios from 'axios'
import router from "../router"

export default {
    data() {
        return {
          content: '',
          value: 0,
          names: '',
          checked: [],
          listData: [],
          percents:[0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100],
          valid: true,
          method: '',
          listitem: '',
          value1: 0,
          nameRules: [
            v => !!v || 'method is required',
            v => (v && v.length <= 20) || 'method must be less than 20 characters',
          ],
        }
    },
    mounted() {
        const apiUrl = '/api'
        axios.get(`${apiUrl}/get_item/`)
        .then((response) => {
            this.listData = response.data;
        });
    },
    methods: {
      createBlend(checked, content) {
        const apiUrl = '/api'
        axios.post(`${apiUrl}/add_blend/`, {array:checked, content:content})
        .then((res) => {
          console.log(res.data)
          if(res.data==false) {
            alert('No 100 % !')
          } else {
            alert('yes')
          }
        })
      },
      addcart(listitem, value1, checked) {
        console.log(listitem)
        console.log(value1)
        if (listitem==null || value1 == 0 ) alert('must be registered')
        else {
          checked.push([listitem,value1])
        }
      },

    },
    components: {
    }
}
</script>

