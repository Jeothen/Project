<template>
  <div class="row">
    <div style="text-align: center;">
      <p class="masterpiece__titleCh">名作</p>
      <div
        style="width: 100px; border-bottom: 8px solid grey; margin: 0 auto; margin-bottom: 25px;"
      ></div>
      <p class="masterpiece__titleKo">
        <strong>명작</strong>, 매우 훌륭한 작품을 이르는 말이다. 걸작이라고도 부른다.
        <br />어쨌든 전 세계에서 이 말은
        <strong>최고의 작품</strong>으로 통한다.
      </p>
    </div>
    <v-container grid-list-md>
      <v-layout wrap>

        <!-- 사진첨부 -->
        <v-flex xs12 sm6>
          <v-hover class="cursor">
            <v-card class="feature" slot-scope="{ hover }" :class="`elevation-${hover ? 12 : 2}`">
              <v-img :src="PhotoImage" aspect-ratio="2.75"/>
              <v-card-text class="pt-4" style="position: relative;">
                <v-dialog class="dialog" v-model="dialog" persistent max-width="450">
                  <template v-slot:activator="{ on }">
                    <v-btn absolute color="orange" v-on="on" @click.stop="dialog" class="white--text" fab large right top>
                      <v-icon>fa-image</v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title class="headline">사진 선택</v-card-title>
                    <v-card-text>랜덤 이미지 또는 선택하는 사진을 업로드 해보세요!</v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <span class="ThumbnailLink" style="display:none"></span>
                      <input type="file" ref="file" class="imgur" accept="image/*" data-max-size="5000" style="display:none"/>
                      <v-btn class="cancelBtn" color="green darken-1" flat @click="dialog = false">cancel</v-btn>
                      <v-btn class="saveBtn" color="green darken-1" flat @click="changeImage" style="display:none">OK</v-btn>
                      <v-btn class="selectBtn" color="green darken-1" flat  @click="$refs.file.click()">Select IMG</v-btn>
                      <v-btn class="randomBtn" color="green darken-1" flat @click="randomImage">Random</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
                <div>
                  <h3 class="headline mb-2">사진 첨부</h3>
                  <div>
                    - 사진첨부기능 활성화 및 섬네일 표시
                    <br />- 이미지 배너를 랜덤 혹은 본인의 이미지로 선택
                  </div>
                </div>
              </v-card-text>
            </v-card>
          </v-hover>
        </v-flex>

        <!-- 한영번역 -->
        <v-flex xs12 sm6>
          <v-hover class="cursor">
            <v-card class="feature" slot-scope="{ hover }" :class="`elevation-${hover ? 12 : 2}`">
              <v-img
                src="https://images.unsplash.com/photo-1530379768560-fac46435511d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80"
                aspect-ratio="2.75"
              ></v-img>
              <v-card-text class="pt-4" style="position: relative;">
                <p v-if = "flag === 'A'">
                  <v-btn absolute color="orange" class="white--text" fab large right top  v-on:click="kotoen(text)"  >
                     <v-icon>fa-language</v-icon>
                   </v-btn>
                </p>
                <p v-else>
                  <v-btn absolute color="orange" class="white--text" fab large right top  v-on:click="entoko(text)"  >
                    <v-icon>fa-language</v-icon>
                  </v-btn>
                </p>
                <div>
                  <h3 class="headline mb-2">한/영 번역</h3>
                  <p id="ext">
                    {{text}}
                  </p>
                </div>
              </v-card-text>
            </v-card>
          </v-hover>
        </v-flex>

        <!-- Gitlab 레포 -->
        <v-flex xs12 sm6>
          <v-hover class="cursor">
            <v-card class="feature" slot-scope="{ hover }" :class="`elevation-${hover ? 12 : 2}`">
              <v-img
                src="https://images.unsplash.com/photo-1531030874896-fdef6826f2f7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
                aspect-ratio="2.75"
              ></v-img>
              <v-card-text class="pt-4" style="position: relative;">
                <v-dialog class="dialog2" v-model="dialog2" persistent>
                  <template v-slot:activator="{ on }">
                    <v-btn absolute color="orange" v-on="on" @click.stop="dialog2" class="white--text" fab large right top>
                      <v-icon>fa-chart-bar</v-icon>
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title class="headline">Gitlab Repository Graph</v-card-title>
                    <v-flex hidden-xs-only>
                        <Graph/>
                    </v-flex>
                    <v-flex hidden-sm-and-up>
                        <v-card-text hidden-sm-and-up>해당 페이지는 모바일화면에서는 표기할수 없습니다.<br/>다른 환경에서 접속해주세요.</v-card-text>
                    </v-flex>
                    <v-card-actions>
                      <v-btn class="cancelBtn" color="green darken-1" flat @click="dialog2 = false" left>exit</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
                <div>
                  <h3 class="headline mb-2">Gitlab Graph</h3>
                  <p>
                    - Gitlab 그래프를 가시적으로 확인
                    <br />- 모바일 사이즈 그래프 미노출
                    <br />- Repository 클릭시 해당 Repository 페이지로 이동
                    <br />
                  </p>
                </div>
              </v-card-text>
            </v-card>
          </v-hover>
        </v-flex>

        <!-- 사용자 인증 -->
        <v-flex xs12 sm6>
          <v-hover class="cursor">
            <v-card class="feature" slot-scope="{ hover }" :class="`elevation-${hover ? 12 : 2}`">
              <v-img
                src="https://images.unsplash.com/photo-1429216967620-ece20ff3a5f9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1055&q=80"
                aspect-ratio="2.75"
              ></v-img>
              <v-card-text class="pt-4" style="position: relative;">
                <!-- <a href="/login"> -->
                <v-btn absolute color="orange" class="white--text" fab large right top to="/login">
                  <v-icon>fa-sign-in</v-icon>
                </v-btn>
                <!-- </a> -->
                <div>
                  <h3 class="headline mb-2">사용자 인증</h3>
                  <p>
                    - 로그인 기능
                    <br />- 회원가입 기능
                    <br />
                  </p>
                </div>
              </v-card-text>
            </v-card>
          </v-hover>
        </v-flex>

        <!-- 이미지 업로더 컴포넌트 -->
        <!-- <v-flex xs12 sm4>
          <v-hover class="cursor">
            <v-card class="feature" slot-scope="{ hover }" :class="`elevation-${hover ? 12 : 2}`">
              <v-img
                src="https://images.unsplash.com/photo-1517329782449-810562a4ec2f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1044&q=80"
                aspect-ratio="2.75"
              ></v-img>
              <v-card-text class="pt-4" style="position: relative;">
                <v-btn absolute color="orange" class="white--text" fab large right top>
                  <v-icon>fa-image</v-icon>
                </v-btn>
                <div>
                  <h3 class="headline mb-2">이미지 업로더 컴포넌트</h3>
                  <p>
                    - Imgur API 사용
                    <br />
                  </p>
                </div>
              </v-card-text>
            </v-card>
          </v-hover>
        </v-flex> -->

        <!-- 날씨 정보 -->
        <v-flex xs12 sm6>
          <v-hover class="cursor">
            <v-card class="feature" slot-scope="{ hover }" :class="`elevation-${hover ? 12 : 2}`">
              <v-img
                src="https://images.unsplash.com/photo-1536936343740-68cb2a95f935?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80"
                aspect-ratio="2.75"
              ></v-img>
              <v-card-text class="pt-4" style="position: relative;">
                <a href="#weather">
                  <v-btn absolute color="orange" class="white--text" fab large right top>
                    <v-icon>fa-cloud</v-icon>
                  </v-btn>
                </a>
                <div>
                  <h3 class="headline mb-2">날씨정보 제공</h3>
                  <p>
                    - 날씨 정보를 제공하는 API를 통해 날씨를 제공한다.
                    <br />
                  </p>
                </div>
              </v-card-text>
            </v-card>
          </v-hover>
        </v-flex>

        <!-- UI/UX -->
        <v-flex xs12 sm6>
          <v-hover class="cursor">
            <v-card class="feature" slot-scope="{ hover }" :class="`elevation-${hover ? 12 : 2}`">
              <v-img
                src="https://images.unsplash.com/photo-1535598745644-bc7913bb1a2a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1055&q=80"
                aspect-ratio="2.75"
              ></v-img>
              <v-card-text class="pt-4" style="position: relative;">
                <a href="#Entrance">
                  <v-btn absolute color="orange" class="white--text" fab large right top>
                    <v-icon>fa-smile</v-icon>
                  </v-btn>
                </a>
                <div>
                  <h3 class="headline mb-2">UI/UX 디자인</h3>
                  <p>
                    - 보기 좋은 떡이 먹기도 좋다.
                    <br />
                  </p>
                </div>
              </v-card-text>
            </v-card>
          </v-hover>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import $ from 'jquery';
import Graph from './RepositoryGraph'
const axios = require("axios");

export default {
  components:{
    Graph
  },
  mounted(){
    $('input[type=file]').on("change", function () {
      var change = this;
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
                      text: '이미지 업로드 완료',
                      footer: '<a href='+res.data.link+' target="_blank">'+res.data.link+'</a>'
                    }).then((result) => {
                      if (result.value) {
                        $(".ThumbnailLink").html('<a href='+res.data.link+' target="_blank" class="imgLink">'+res.data.link+'</a>');
                        $(".saveBtn").css("display","inline");
                        $(".cancelBtn").css("display","none");
                      }
                    });
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
  name: "MasterPiece",
  data() {
    return {
      flag: 'A',
      text: " 번역은 어떤 언어로 쓰인 글을 다른 언어로 된 상응하는 의미의 글로 전달하는 일이다. \n정확한 번역을 위해서는 원전을 이해하기 위한 문화적인 배경지식과 옮겨오는 언어의 정확하고 문학적인 문장력이 필요하다.",
      dialog:false,
      dialog2:false,
      PhotoImage:"https://images.unsplash.com/photo-1481299490292-adef25f13c3f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80"

    };
  },
  methods : {
    kotoen: function(text) {
      this.flag = 'B'
      axios({
        method : 'post',
        url : 'https://translation.googleapis.com/language/translate/v2',
        params  :{
          source : 'ko',
          target : 'en',
          q : text,
          key : 'AIzaSyAM3pZMOpmnKyKnhorj1s-LGK0hBe5gQbA',
        },
      }).then(res => {
        document.getElementById("ext").innerText = res.data.data.translations[0].translatedText;
      })

    },
    entoko: function(text) {
      this.flag = 'A'
      axios({
        method : 'post',
        url : 'https://translation.googleapis.com/language/translate/v2',
        params  :{
          source : 'en',
          target : 'ko',
          q : text,
          key : 'AIzaSyAM3pZMOpmnKyKnhorj1s-LGK0hBe5gQbA',
        },
      }).then(res => {
        document.getElementById("ext").innerText = res.data.data.translations[0].translatedText;
      })
    },
    changeImage(){
          var imgLink = $(".imgLink").html();
          this.PhotoImage = imgLink;
          this.dialog = false;
        },
    randomImage(){
      this.PhotoImage = "https://source.unsplash.com/random/1600x900";
      this.dialog = false;
    }

  }
};
</script>

<style>
.cursor {
  cursor: zoom-in;
  z-index: 0;
}

.masterpiece__titleCh {
  font-size: 35px;
  font-family: "ZCOOL QingKe HuangYou", cursive;
  margin-top: 50px;
  margin-bottom: 0;
}

.masterpiece__titleKo {
  font-size: 15px;
  font-family: "Song Myung", serif;
  margin-top: 10px;
  margin-bottom: 30px;
  word-wrap: inherit;
}

.chinese {
  font-family: "ZCOOL QingKe HuangYou", cursive;
}

.feature {
  height: 100%;
}
</style>