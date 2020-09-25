import session from "../api/session";

const isProduction = process.env.NODE_ENV === 'production';
const TOKEN_STORAGE_ACCESS = 'TOKEN_STORAGE_ACCESS';
const TOKEN_STORAGE_REFRESH = 'TOKEN_STORAGE_REFRESH';

export default {
  SET_MOVIEINFO(state, payload){
    state.movie_list = payload.movies_info
    state.max_page = Math.floor(payload.max_page/10)
  },
  SET_FILTER(state, filter) {
    state.filter = filter
  },

  // REGISTER
  REGISTRATION_BEGIN(state){
    state.registrationLoading = true;
  },
  REGISTRATION_FAILURE(state){
    state.registrationError = true;
    state.registrationLoading = false;
  },
  REGISTRATION_SUCCESS(state){
    state.registrationCompleted = true;
    state.registrationError = false;
    state.registrationLoading = false;
  },

  // LOGIN
  LOGIN_BEGIN(state){
    state.authenticating = true;
    state.loginerror = false;
  },
  LOGIN_FAILURE(state) {
    state.authenticating = false;
    state.loginerror = true;
  },
  LOGIN_SUCCESS(state) {
    state.authenticating = false;
    state.loginerror = false;
  },
  SET_TOKEN(state, token){
    if (!isProduction) {
      localStorage.setItem(TOKEN_STORAGE_ACCESS, token.access);
      localStorage.setItem(TOKEN_STORAGE_REFRESH, token.refresh);
    }
    console.log('settoken',token.access)
    session.defaults.headers.Authorization = `Bearer ${token.access}`;
    state.token = true;
  },
  REMOVE_TOKEN(state) {
    localStorage.removeItem(TOKEN_STORAGE_ACCESS);
    localStorage.removeItem(TOKEN_STORAGE_REFRESH);
    delete session.defaults.headers.Authorization;
    state.token = null;
  },

  //LOG OUT시 로컬스토리지에 저장된 토큰을 삭제한다.
  LOGOUT(state){
    state.profile= [];
    state.authenticating = false;
    state.error = false;
    localStorage.removeItem(TOKEN_STORAGE_ACCESS);
    localStorage.removeItem(TOKEN_STORAGE_REFRESH);
    delete session.defaults.headers.Authorization;
    state.token = null;
  },

  // 유저 프로필을 가져옴 ttttt
  SET_USER(state, user) {
    state.profile = user;
  },

  SET_USERLIST(state, user_info){
    state.user_list = user_info.userinfo
    state.max_user_page = Math.floor(user_info.max_page/10)
  },

  CHANGE_USER(state, user){
    
  },

  INIT_MOVIE(state){
    state.movie_list = []
    state.filter = ''
    state.max_page = 0
  },

  RELOAD_RATE(){
    // console.log(this.$route)
    // this.$router.push("/movie/rate")
  }

}