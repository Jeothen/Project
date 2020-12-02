<template>
  <v-container text-center class="col">
    <p id="title">My Page</p>
    <v-card style="width:700px;" class="mx-auto">
      <v-card-text>
        <v-container>
          <v-text-field
            v-model="username"
            :counter="10"
            label="Name"
            required
          ></v-text-field>
          <v-text-field
            v-model="email"
            label="E-mail"
            required
          ></v-text-field>
          <v-text-field
            v-model="address"
            label="Address"
          ></v-text-field>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-container>
          <v-btn color="blue darken-1" text @click="editDetail()">Edit</v-btn>
        </v-container>
      </v-card-actions>
    </v-card>
    <!-- 구독 서비스 -->
    <div v-if="approval" class="mx-auto" style="width:500px;">
      <v-card v-if="before_extend" class="mx-3 my-3 px-8 py-3">
        <v-card-title style="font-size: 1.2rem;">{{username}}님의 구독 유효 기간 <span style="padding-left: 0.7rem;">{{sub_date}}</span></v-card-title>
        <v-card-text>
        <v-radio-group v-model="picked_amount" style="display:inline-block;" row>
          <v-radio value=30 label="30 days" color=""></v-radio>
          <v-radio value=90 label="90 days" color=""></v-radio>
          <!-- <label style="font-size:10px;">{{amount}}</label> &nbsp; -->
        </v-radio-group>
        <v-btn @click="extend_subscription()" style="margin-left: 0.7rem;">구독 연장</v-btn>
        </v-card-text>
      </v-card>

      <v-layout v-if="before_extend">
        <v-flex v-if="flag[0] && items[0] !== false">
          <v-card class="mx-3 my-3 px-8 py-3">
            <v-card-text>
              <p>지난 달의 상품 : {{method[0]}}</p>
              <p>만족스러우셨나요?</p>
              <v-rating
                v-model="last_rating"
                color="yellow darken-3"
                background-color="grey darken-1"
                empty-icon="$ratingFull"
                half-increments
                hover
              ></v-rating>
              <v-text-field
                v-model="last_comment"
                label="Comment"
              ></v-text-field>
              <v-btn @click="create_rating1()" style="margin-left: 0.7rem;">평점 등록</v-btn>
            </v-card-text>
          </v-card>
        </v-flex>

        <v-flex v-if="flag[1] && items[1] !== false">
          <v-card class="mx-3 my-3 px-8 py-3">
            <v-card-text>
              <p>이 달의 상품 : {{method[1]}}</p>
              <p>만족스러우셨나요? </p>
              <v-rating
                v-model="this_rating"
                color="yellow darken-3"
                background-color="grey darken-1"
                empty-icon="$ratingFull"
                half-increments
                hover
              ></v-rating>
              <v-text-field
                v-model="this_comment"
                label="Comment"
              ></v-text-field>
              <v-btn @click="create_rating2()" style="margin-left: 0.7rem;">평점 등록</v-btn>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>

      <v-card v-else-if="!before_extend" class="mx-3 my-3  px-3 py-3">
        <v-card-text>연장 신청이 완료되었어요. 관리자 승인 중입니다. :)</v-card-text>
      </v-card>
    </div>

    <div v-else-if="!approval" class="mx-auto" style="width:500px;">
      <v-card v-if="before_create" class="mx-3 my-3 px-8 py-3">
        <v-card-text>
          <p style="font-size: 1rem; text-align: left;">{{username}}님은 커피 구독을 이용한 적이 없어요.</p>
          <p style="font-size: 1rem; text-align: left;">구독을 원하시나요?</p>
          <v-radio-group v-model="picked_amount" row>
            <v-radio value=30 label="30 days"></v-radio>
            <v-radio value=90 label="90 days"></v-radio>
          </v-radio-group>
          <v-btn @click="create_subscription()" style="margin-left: 0.7rem;">구독 신청</v-btn>
        </v-card-text>
      </v-card>
      <v-card v-else class="mx-3 my-3 px-3 py-3">
        <v-card-text>구독 신청이 완료되었어요. 관리자 승인 중입니다 :)</v-card-text>
      </v-card>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';
import router from '../../router';

export default {
  data() {
    return {
      email: "",
      username: "",
      id: "",
      address: "",
      approval: "",
      subscription: "",
      before_create: true,
      before_extend: true,
      sub_date: "",
      picked_amount: "30",
      monthly_item: "",
      this_rating: null,
      this_comment: '',
      last_rating: null,
      last_comment: '',
      now_date: null,
      flag: [],
      items: [],
      method:[],
    }
  },
  mounted() {
      this.getDetail();
      this.getItem();
  },
  methods: {
    // user detail 정보 불러오기
    async getDetail() {
      let __this = this;
      const id = this.$session.get('id')
      await axios.get(`/api/auth/${id}/`).then(res => {
        if (res.data) {
          const user = res.data
          __this.id = user.id
          __this.username = user.username
          __this.email = user.email
          __this.address = user.address
          __this.approval = user.approval
          __this.subscription = user.subscription
          __this.sub_date = user.subscription.substring(0, 10)
        }
      })
    },
    async getItem() {
      let __this = this;
      const id = this.$session.get('id')
      this.now_date = this.$moment().format('YYYYMMDD')
      await axios.post('/api/rating/items/', {today: __this.now_date, user: id})
      .then(res => {
        console.log(res.data)
        __this.flag = res.data[0]
        __this.items = res.data[1]
        __this.method = res.data[2]
      })
    },
    // user detail 정보 수정하기
    async editDetail() {
      let __this = this;
      const id = this.$session.get('id')
      await axios.post(`/api/auth/${id}/`, {
        username: __this.username,
        email: __this.email,
        address: __this.address
      }).then(res => {
        if (res.status == 200) {
          Swal.fire({
            title: '프로필 수정 완료!',
            type: 'success'
          })
        } else {
          Swal.fire({
            text: '오류가 발생했습니다. 다시 수정해 주세요.',
            type: 'error'
          })
        }
      })
    },
    async create_subscription() {
      var subscription = await axios.post('/api/subscription/create/', {user : this.$session.get('id'), request:this.picked_amount})
      if (subscription.data) {
        Swal.fire({
          text: subscription.data.message,
          type: 'warning',
        })
      }
      this.before_create = false
    },
    async extend_subscription() {
      var subscription = await axios.post('/api/subscription/create/', {user : this.$session.get('id'), request:this.picked_amount})
      if (subscription.data) {
        alert(subscription.data.message)
      }
      this.before_extend = false
    },
    async create_rating1() {
      let __this = this;
      const id = this.$session.get('id')
      const rating = [this.last_rating, this.last_comment]
      if (this.last_rating) {
        await axios.post('/api/rating/create/', {
          user: id,
          items: __this.items[0],
          rating: rating
        }).then(res => {
          console.log(res)
          Swal.fire({
            title: '감사합니다!',
            text: '지난 달 상품의 평점이 등록되었습니다.',
            type: 'success'
          })
        })
      } else {
        Swal.fire({
          title: '평점을 입력해 주세요.',
          type: 'warning'
        })
      }
    },
    async create_rating2() {
      let __this = this;
      const id = this.$session.get('id')
      const rating = [this.this_rating, this.this_comment]
      if (this.this_rating) {
        await axios.post('/api/rating/create/', {
          user: id,
          items: __this.items[1],
          rating: rating
        }).then(res => {
          Swal.fire({
            title: '감사합니다!',
            text: '이번 달 상품의 평점이 등록되었습니다.',
            type: 'success'
          })
          __this.getItem()
        })
      } else {
        Swal.fire({
          title: '평점을 입력해 주세요.',
          type: 'warning'
        })
      }
    }
  }
}
</script>
<style scoped>
  #title {
    font-family: 'Acme', sans-serif;
    font-size: 50px;
  }
</style>