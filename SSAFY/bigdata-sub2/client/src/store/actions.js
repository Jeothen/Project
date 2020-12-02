import api from '../api/index'

export default {
  SEARCH_MOVIES({ commit }, filter){
    console.log(filter,'필터입니다.')
    return api.search_movies(filter)
      .then(res => {

        const movies_info = res.data.results.map(d => ({
          id: d.id,
          title: d.title,
          genres: d.genres_array,
          viewCnt: d.views,
          rating: d.avg_rating,
          slug: d.slug
        }))
        const payload = {
          movies_info,
          max_page: res.data.count,
        }
        console.log(payload, '영화정보')
        commit('SET_MOVIEINFO', payload)
      })
  },

  FETCH_USER_DETAIL({ commit }, slug){
    return api.fetch_user_detail(slug)
      .then(res => {
        console.log(res)
        return res.data
        // commit('SET_USER_DETAIL',res.data)
      })
  },
  FETCH_MOVIE_DETAIL({ commit }, slug){
    return api.fetch_movie_detail(slug)
      .then(res => {
        console.log(res)
        return res.data
      })
  },
  SEARCH_MOVIE_AGE({commit}, age){
    return api.search_movie_age(age).then(res =>{
      console.log(res)
      const movies_info = res.data.map(d => ({
        id: d.id,
        title: d.title,
        genres: d.genres_array,
        viewCnt: d.views,
        rating: d.avg_rating,
        slug: d.slug
      }))
      const payload = {
        movies_info,
        max_page: res.data.count,
      }
      commit('SET_MOVIEINFO', payload)
    })
  }
}
