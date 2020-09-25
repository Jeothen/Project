<template>
    <div id="app" class="editor">
        <h1>Insert New Portfolio</h1>
        <form method="post" class="port_form" @submit.prevent="sendPost">

        <div class="input-group mb-3">
          <input  type="text" class="form-control" placeholder="Title" aria-describedby="basic-addon1">
        </div>
        <ckeditor :editor="editor" :config="editorConfig"></ckeditor>
        <br />
        </form>
        <a class="btn btn-danger btn-block" @click="cancel">Cancel</a>
        <button @click="save" class="btn btn-primary btn-block">Save</button>
    </div>
</template>

<script>
    import CKEditor from '@ckeditor/ckeditor5-vue';
    import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

    export default {
        name: 'app',
        methods: {
          save(){
            var flag = confirm('저장하시겠습니까?');
            if (flag == true) {
              alert('작성이 완료 되었습니다');
              return window.location.href = "./Portfolio";
            }else if(flag ==false){
              return false;
            }
          },
          cancel(){
            var cancel_flag = confirm('작성을 취소하시겠습니까?\n작성중인 내용이 모두 삭제 됩니다.');
            if (cancel_flag == true) {
              return history.back(-1);
            }else if(flag == false){
              return false;
            }
          }
        },
        data() {
            return {
                editor: ClassicEditor,
                editorData: '<h1>Your Project Title</h1> ',
                editorConfig: {
                    // The configuration of the editor.
                }
            };
        },
    }
</script>

<style lang="css">
  .ck-editor__editable {
    min-height: 500px;
  }

  .editor {
    margin-top: 100px;
  }

  .input-group{
    width: 100%;
  }
</style>
