<template>
  <div>
    <div style="line-height:1.2em;font-size:3.2em; text-align:center;" slot="text">Add Post<br></div>
    <v-flex>
      <v-text-field
      label="Title"
      v-model="title"
      outline
      ></v-text-field>
    </v-flex>
    <v-layout>
      <v-flex xs12 sm12 md12 >
        <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
      </v-flex>
    </v-layout>
    <v-btn color="info" dark @click="postPost">등록하기</v-btn>
    <v-btn color="info" dark to="post">돌아가기</v-btn>

  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import Portfolio from '@/components/Portfolio'
import FirebaseService from '@/services/FirebaseService'
export default {
  name: 'app',
  data() {
    return {
      editor: ClassicEditor,
      editorData: '<p>Content of the editor.</p>',
      editorConfig: {
        // The configuration of the editor.
      }
    };
  },
  methods:{
    postPost(){
      FirebaseService.postPost(this.title,this.editorData);
      this.$router.push('/');
    }
  }
}
</script>


<style>
.ck-editor__editable {
  min-height: 500px;
  width: 100%;
}
</style>
