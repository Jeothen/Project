<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card height="500">
        <v-card-title>
          <span class="headline">영화 수정하기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <!-- Title 입력칸 -->
              <v-col cols="12">
                <v-text-field counter="60" label="Title" v-model="title_value" required></v-text-field>
              </v-col>
              <!-- 장르 입력칸 -->
              <v-col cols="12">
                <v-select 
                v-model="genres_value" 
                :items="genres" attach chips label="Genres" multiple
                ></v-select>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <div class="flex-grow-1"></div>
          <v-btn color="blue" text @click="remove">Delete</v-btn>
          <v-btn color="blue" text @click="edit">Edit</v-btn>
          <v-btn color="red darken-1" text @click="close">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      genres: [
        "Action",
        "Adventure",
        "Animation",
        "Children's",
        "Comedy",
        "Crime",
        "Documentary",
        "Drama",
        "Fantasy",
        "Film-Noir",
        "Horror",
        "Musical",
        "Mystery",
        "Romance",
        "Sci-Fi",
        "Thriller",
        "War",
        "Western"
      ],
      title_value:'',
      genres_value:'',
    };
  },
  created(){
    this.title_value = this.title
    this.genres_value = this.genre
  },
  methods: {
    remove() {},
    edit() {},
    close() {
      this.$emit("close");
      this.genres_value = this.genre
      this.title_value = this.title
    }
  },
  props: {
    dialog: Boolean,
    title: String,
    genre: Array,
    slug: String
  }
};
</script>