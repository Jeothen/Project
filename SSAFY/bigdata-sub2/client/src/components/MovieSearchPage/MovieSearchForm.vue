<template>
  <v-form ref="form">
    <!-- 필터링기능 -->
    <v-select
      v-model="selected_filter"
      :items="filtering"
      label="Filter"
      multiple
    >
    </v-select>
    <!-- 정렬 기능 -->
    <v-select
      v-model="selected_order_data"
      :items="order_data"
      label="Field you want to order"
    >
    </v-select>
    <v-select
      v-model="selected_order"
      :items="ordering"
      label="Order"
    >
    </v-select>

    {{ selected_filter }}
    {{ selected_order }}
    {{ selected_order_data }}


    <!-- 제목 입력 -->
    <v-text-field v-model="title" label="영화 제목" />
    <v-layout justify-center pa-10>
      <v-btn large color="indigo white--text" @click="startSearch">Search</v-btn>
    </v-layout>
    <v-flex>
    <v-menu offset-y
        transition="slide-x-transition"
        bottom
        right>
        <template v-slot:activator="{ on }">
        <v-btn  v-on="on" color="#FF9800" dark class="deep-orange">
          {{type}}
        </v-btn>
        </template>
        <v-list>
          <v-list-item
            v-for="(item, index) in types"
            :key="index"
            @click="search_age(index)"
          >
          <v-list-item-title>{{item}}</v-list-item-title>
        </v-list-item>
      </v-list>
     </v-menu>
    </v-flex>
  </v-form>
</template>

<script>
export default {

  data() {
    return {
      title:'',
      selected_filter:'',
      selected_order:'',
      selected_order_data: '',

      filtering:['Title','Genres'],
      ordering:['Ascending','Descending'],
      order_data:['Views','Title','Rating'],
      types : {0:'10대 미만', 10:"10대" ,20:"20대", 30:"30대" , 40:"40대" , 50:"50대" , 60:"60대",70:"70대"},
      type: "연령대 별 검색",
    }
  },
  methods: {
    startSearch(){
      const obj = {
        filter:this.selected_filter,
        order:this.selected_order,
        order_data:this.selected_order_data,
        target:this.title
      }
      this.$emit('startSearch',obj)
    },
    search_age(age){
      console.log(age)
      this.$emit('startSearch',age,'연령대 별 검색')
      this.type = this.types[age]
    }
  }
};
</script>
