import axios from 'axios';

const apiUrl = '/api'

export default {
  search_movies(filter) {
    return axios.get(`${apiUrl}/movie/list/${filter}`)
  },

  fetch_user_detail(slug){
    return axios.get(`${apiUrl}/auth/${slug}`)
  },

  fetch_movie_detail(slug){
    return axios.get(`${apiUrl}/movie/detail/${slug}/`)
  },
  search_movie_age(age){
    return axios.get(`${apiUrl}/auth/age/${age}`)
  }
}
