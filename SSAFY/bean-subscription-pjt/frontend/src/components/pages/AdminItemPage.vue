<template>
    <v-flex sm6 offset-sm3>
        <v-btn @click="createItem()">등록</v-btn>
        <v-list two-line
                v-for="(listItem, index) in listData"
                :key="index">
            <v-list-item>
                <v-list-item-content>
                <v-list-item-title class="text--primary">
                    {{ listItem.name }}
                    <!-- 상세보기 버튼 (내용 숨김) -->
                    <v-btn @click="deleteItem(listItem['id'])"> 삭제</v-btn>
                </v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        <v-divider></v-divider>
        </v-list>
    </v-flex>
</template>
<script>
import axios from 'axios'
import router from "../../router"

export default {
    data() {
        return {
        listData: []
        }
    },
    mounted() {
        const apiUrl = '/api'
        axios.get(`${apiUrl}/get_item/`)
        .then((response) => {
            this.listData = response.data;
        });
    },
    components: {
        
    },
    methods: {
        createItem() {
            router.push({name:"createitem"})
        },
        deleteItem(itemid) {
            const apiUrl = '/api'
            axios.post(`${apiUrl}/delete_item/`, {pk:itemid})
            window.location.reload()
        }
    }
}
</script>