import session from './session'

const apiUrl = '/api'

export default {
  search_movies(filter) {
    return session.get(`${apiUrl}/movie/list/${filter}`)
  },
  random_movie(){
    return session.get(`${apiUrl}/movie/get/random/`)
  },
  fetch_user_detail(slug){
    return session.get(`${apiUrl}/auth/detail/${slug}`)
  },
  fetch_movie_detail(slug){
    return session.get(`${apiUrl}/movie/detail/${slug}/`)
  },
  search_movie_age(age){
    return session.get(`${apiUrl}/auth/age/${age}`)
  },
  search_user(filter){
    return session.get(`${apiUrl}/auth/list/${filter}`)
  },
  edit_user(payload){
    console.log('페이로드',payload)
    return session.put(`${apiUrl}/auth/update/${payload.slug}/`,payload.obj)
  },
  reco_movies(id){
    return session.get(`${apiUrl}/movie/mf/${id}`)
  },

  // Auth
  register(info){
    return session.post(`${apiUrl}/auth/register/`,info)
  },
  gettoken(info){
    return session.post(`${apiUrl}/auth/token/obtain/`,info)
  },
  verify(token){
    return session.post(`${apiUrl}/auth/token/verify/`,token)
  },
  refresh(token){
    return session.post(`${apiUrl}/auth/token/refresh/`,token)
  },
  getuser(){ // 나의 유저정보 가져옴
    return session.get(`${apiUrl}/auth/getuser/`)
  },

  // Rating
  createrate(payload){
    const rating = payload.rating
    return session.post(`${apiUrl}/movie/${payload.movie}/${payload.user}/rating/`, { rating,})
  },

  delrate(payload){
    return session.post(`${apiUrl}/movie/${payload.movie}/${payload.user}/rating/del/`)
  },

  // 유저가 본 영화 체크
  uservisit(user){
    return session.post(`${apiUrl}/movie/${user}/visit/`)
  },

  subscription() {
    return session.get(`${apiUrl}/auth/subscription/`)
  }
}
