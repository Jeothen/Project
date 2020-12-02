export default {
  SET_MOVIEINFO(state, movie_info){
    state.movie_list = movie_info.movies_info
    state.max_page = Math.floor(movie_info.max_page/10)
  },
  SET_FILTER(state, filter) {
    state.filter = filter
  },
}