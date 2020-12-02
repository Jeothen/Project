<template>
  <div>
    <div style="line-height:1.2em;font-size:3.2em; text-align:center;" slot="text">Add Portfolio<br></div>
    <v-flex>
      <v-text-field
      label="Title"
      v-model="title"
      outline
      ></v-text-field>
    </v-flex>
    <v-layout>
      <v-flex xs12 sm12 md12 >
        <input type="file" ref="file" class="imgur" accept="image/*" data-max-size="5000" style="display:none"/>
        <v-btn class="ThumbnailBtn" color="info" @click="$refs.file.click()">썸네일 선택</v-btn>
        <span class="ThumbnailLink"></span>
        <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
      </v-flex>
    </v-layout>
    <v-btn color="info" dark @click="postPortfolio">등록하기</v-btn>
    <v-btn color="info" dark to="portfolio">돌아가기</v-btn>

  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import Portfolio from '@/components/Portfolio'
import FirebaseService from '@/services/FirebaseService'
export default {
  mounted(){
    $('input[type=file]').on("change", function () {
        var $files = $(this).get(0).files;
        if ($files.length) {

            // Reject big files
            if ($files[0].size > $(this).data("max-size") * 1024) {
                console.log("Please select a smaller file");
                return false;
            }

            // Replace ctrlq with your own API key
            var apiUrl = 'https://api.imgur.com/3/image';
            var apiKey = '5029977fb779471';

            var formData = new FormData();
            formData.append("image", $files[0]);

            var settings = {
                "async": true,
                "crossDomain": true,
                "url": apiUrl,
                "method": "POST",
                "datatype": "json",
                "headers": {
                    "Authorization": "Client-ID " + apiKey
                },
                "processData": false,
                "contentType": false,
                "data": formData,
                "type": 'POST',
                beforeSend: function (xhr) {
                    console.log("Uploading");
                },
                success: function (res) {
                    console.log(res.data.link);
                    Swal.fire({
                      type: 'success',
                      title: 'Success!!!',
                      text: '썸네일이 해당 링크에 업로드 되었습니다.',
                      footer: '<a href='+res.data.link+' target="_blank">'+res.data.link+'</a>'
                    });
                    $(".ThumbnailBtn").html('선택 완료');
                    $(".ThumbnailLink").html('<a href='+res.data.link+' target="_blank" class="imgLink">'+res.data.link+'</a>');
                },
                error: function () {
                    alert("Failed");
                }
            }
            $.ajax(settings).done(function (response) {
                console.log("Done");
            });
        }
    });
  },
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
    postPortfolio(){
      var imgLink = $(".imgLink").html();
      alert(imgLink);
      FirebaseService.postPortfolio(this.title,this.editorData,imgLink);
      alert("글 작성 완료 홈페이지로 돌아갑니다.");
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

a:link{
  color: black;
}
a:hover{
  color: #519dca;
}
</style>
