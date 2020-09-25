<template>
    <v-container text-center>
        <p id="title">Preference</p>
        <v-card class="mx-auto" style="width:700px">
            <v-card-text>
                <v-layout>
                    <p>Gender</p>
                    <v-radio-group v-model="gender" row>
                        <v-radio label="Female" value="F"></v-radio>
                        <v-radio label="Male" value="M"></v-radio>
                    </v-radio-group>
                </v-layout>
                <v-layout>
                    <p>Age</p>
                    <v-select
                        :items="ages"
                        v-model="age"
                        outlined
                    ></v-select>
                </v-layout>
                <v-btn @click="recommand()">추천받기</v-btn>
            </v-card-text>
        </v-card>
        <v-card v-if="ratings" style="margin-top: 2rem;">
            <v-card-title style="margin-left: 23rem;" v-if="gender == 'F'">{{age}}대 여성 선호 원두</v-card-title>
            <v-card-title style="margin-left: 23rem;" v-else>{{age}}대 남성 선호 원두</v-card-title>
            <v-card-text v-if="ratings.length == 0">
                <p style="font-size: 1rem; margin-left: 23.3rem;">선호 데이터가 없습니다.</p>
            </v-card-text>
            <v-card-text v-else>
                <v-simple-table fixed-header height="300px">
                    <template v-slot:default>
                    <thead>
                        <tr>
                        <th class="text-left">Rank</th>
                        <th class="text-left">Item</th>
                        <th class="text-left">Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, index) in ratings" :key="item">
                        <td>{{ index+1 }}</td>
                        <td>{{ item[1] }}</td>
                        <td>{{ item[0] }}</td>
                        </tr>
                    </tbody>
                    </template>
                </v-simple-table>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
    data() {
        return {
            gender: 'F',
            age: 20,
            ages: [10, 20, 30, 40, 50, 60, 70, 80, 90],
            ratings: null
        }
    },
    methods: {
        async recommand() {
            let __this = this;
            await axios.post('/api/filtering/', {
                gender: __this.gender,
                age: __this.age
            }).then(res => {
                __this.ratings = res.data
                console.log(res.data)
            })
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