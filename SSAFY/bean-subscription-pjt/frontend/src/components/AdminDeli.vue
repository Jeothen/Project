<template>
  <v-container text-center>
    <p id="title">Blend list</p>
    <v-row>
    <v-card
      class="ma-2"
      width="900" v-for="(item, i) in listData" v-bind:key="i">
      <v-col>
      <dis>
        <v-card-text class="text--primary">
          <!-- <p>{{item.id}}</p> -->
          <dis class="left">
            <v-flex v-for="(name,key) in listitems[i]" v-bind:key="key">
              <p style="font-family: 'Noto Serif KR', serif;">{{name.name}} {{listpercents[i][key]}}%</p>
            </v-flex>
          </dis>
        </v-card-text>
        <v-card-subtitle class="pb-0">
          <dis class="right">{{item.content}}</dis>
        </v-card-subtitle>
      </dis>
      <v-btn color="red lighten-2"
      dark @click="add_delivery(item.id,item.method)">배달 상품 등록</v-btn>
      <div class="text-center">
        <v-dialog
          v-model="flag_d"
          width="500"
        >
          <v-card>
            <v-card-title
              primary-title
            >
              배달 정보를 등록해주세요
            </v-card-title>

            <v-form>
             <v-container>
               <v-row>
                 <v-col cols="12" sm="6" md="3">
                   <v-text-field
                     label="년도"
                     v-model="year"
                    ></v-text-field>
                 </v-col>

                 <v-col cols="12" sm="6" md="3">

                   <v-text-field
                     label="월"

                     v-model="month"
                   ></v-text-field>
                 </v-col>

                 <!-- <v-col cols="12" sm="6" md="3">

                   <v-text-field
                     label="주 ex)1번째 주"
                     placeholder3=""
                     v-model="weeks"
                   ></v-text-field>
                 </v-col> -->
               </v-row>
               <p>이달의 블렌드 : </p>
               <v-card color="grey">
                 <p style="font-family: 'Noto Serif KR', serif;">{{blend}}</p>
               </v-card>
             </v-container>
           </v-form>

            <v-divider></v-divider>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="primary"
                text
                @click="flag_d =false"
              >
                취소
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn
                color="primary"
                text
                @click="go_delivery(year,month,weeks)"
              >
                배달 리스트 등록
              </v-btn>
            </v-card-actions>
          </v-card>

        </v-dialog>
        </div>
    </v-col></v-card>
    </v-row>
  </v-container>
</template>
<script>
import axios from 'axios'
import router from "../router"
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      listData: [],
      listitems: [],
      listpercents:[],
      show: false,
      delivery : [],
      flag_d: false,
      blend: '',
      blend_id: 0,
      year: '',
      month: '',
      weeks: '',
      gender: 'F',
      age: 20,
      ages: [10, 20, 30, 40, 50, 60, 70, 80, 90],
      ratings: null
    }
  },
  mounted() {
    this.allItem()
  },
  methods: {
    allItem() {
      const apiUrl = '/api'
      axios.get(`${apiUrl}/blend/`)
      .then((response) => {
        this.listData = response.data.list;
        // console.log(this.listData)
        this.listitems = response.data.items
        // console.log(response.data.items)
        this.listpercents=response.data.percents
        console.log(response.data)
      });
    },
    async recommand() {
      let __this = this;
      await axios.post('/api/filtering/', {
        gender: __this.gender,
        age: __this.age
      }).then(res => {
        __this.ratings = res.data
        console.log(res.data)
      })
    },
    add_delivery(id,name){
      console.log(id)
      if (this.flag_d == false) {
        this.flag_d = true
        this.blend= name
        this.blend_id= id
        this.year=''
        this.month=''
        this.weeks=''
      }
    },
    go_delivery(year,month,weeks){
      if( this.flag_d == true) {
        console.log(year,month,weeks)
        var url = '/api/add/delivery/' + this.blend_id
        axios({
          method: 'post',
          url: url,
          data: {
            year: year,
            month: month,
            weeks: 1,
          },
        }).then((res)=>{
          console.log(res)
        })
        // axios.post((url), {
        //   body :{
        //     year:year, month: month, weeks:weeks}
        //   }).then((res)=>{
        //     console.log(res)
        //     this.blend=''
        //     this.blend_id=0
        //     this.year=''
        //     this.month=''
        //     this.weeks=''
        //   })
        this.blend=''
        this.blend_id=0
        this.year=''
        this.month=''
        this.weeks=''
        this.flag_d = false
      }
    },
    createItem(){
    },
    deleteItem(itemid) {
      const apiUrl = '/api'
    }
  }
}
</script>
<style>
  dis {
    width: 100%;
    height: 190px;
  }
  dis.left {
    width: 50%;
    float: left;
  }
  dis.right {
    width: 50%;
    float: right;
  }
  #title {
    font-family: 'Acme', sans-serif;
    font-size: 50px;
  }
</style>
