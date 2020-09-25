import api from '../api/index'
import { store } from './index'
import { router } from '../router'
const isProduction = process.env.NODE_ENV === 'production';
const TOKEN_STORAGE_ACCESS = 'TOKEN_STORAGE_ACCESS';
const TOKEN_STORAGE_REFRESH = 'TOKEN_STORAGE_REFRESH';

const verify_token = () => {
    console.log('Access Token 만료입니다. 이제 refresh토큰이 유효한지 검사합니다.');
    const refresh = localStorage.getItem(TOKEN_STORAGE_REFRESH);
    return api.verify({ token: refresh })
        .then(res => {
            console.log('refresh 토큰이 유효합니다. access 토큰을 재발급합니다.')
            return api.refresh({ refresh: refresh })
                .then(res => {
                    store.commit('SET_TOKEN', { access: res.data.access, refresh })
                    console.log('access 토큰이 발급되었습니다.')
                })
        })
        .catch(() => {
            console.log('refresh 토큰이 만료되었습니다. 로그아웃을 진행합니다.')
            store.commit('LOGOUT')
            router.push({ path: '/home' })
        })
}


export default {
    // 영화 검색
    SEARCH_MOVIES({ commit }, filter) {
        console.log(filter, '필터입니다.')
        return api.search_movies(filter)
            .then(res => {
                console.log(res.data.results)
                const movies_info = res.data.results.map(d => ({
                    id: d.id,
                    title: d.title,
                    genres: d.genres_array,
                    viewCnt: d.views,
                    rating: d.rating__avg,
                    slug: d.slug,
                    poster:d.poster,
                    runtime:d.runtime,
                    director:d.director,
                    writer:d.writer,
                    actor:d.actor,
                    plot:d.plot,
                    country:d.country
                }))
                console.log('gg')
                // const rating_info = res.data.results.ratings.map(d => ({
                //     slug: d.slug,
                //     rating: d.rating__avg
                // }))
                const payload = {
                    movies_info,
                    max_page: res.data.count,
                }
                commit('SET_MOVIEINFO', payload);
            })
            .catch((err) => {
                if (err.response.status === 401 && store.state.token) {
                    verify_token()
                } else {
                    console.log('로그인부터 해주세요.')
                }
            })
    },
    RAN_MOVIE({commit}){
      return api.random_movie().then((res)=>{
        console.log(res.data)
        return res.data
    })
    },
    FETCH_USER_DETAIL({ commit }, slug) {
        return api.fetch_user_detail(slug)
            .then(res => {
                console.log(res)
                return res.data
                    // commit('SET_USER_DETAIL',res.data)
            })
    },
    FETCH_MOVIE_DETAIL({ commit }, slug) {
        return api.fetch_movie_detail(slug)
            .then(res => {
                console.log(res)
                return res.data
            })
    },
    SEARCH_MOVIE_AGE({ commit }, age) {
        return api.search_movie_age(age).then(res => {
            console.log('1',res)
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
    },
    RECO_MOVIE({ commit }, id){
      console.log(id)
      return api.reco_movies(id).then((res)=>{
        // console.log(res.data)
        return res.data
      })
    },

    // 가입과정
    REGISTER({ commit }, info) {
        commit('REGISTRATION_BEGIN');
        return api.register(info)
            .then(() => commit('REGISTRATION_SUCCESS'))
            .catch(() => commit('REGISTRATION_FAILURE'))
    },

    // 로그인, 가입성공시 토큰을 얻는과정
    GET_TOKEN({ commit }, info) {
        commit('LOGIN_BEGIN');
        return api.gettoken(info)
            .then((res) => commit('SET_TOKEN', res.data))
            .then(() => commit('LOGIN_SUCCESS'))
            .then(() => {
                return api.getuser()
                    .then(res => commit('SET_USER', res.data))
                    .catch(err => {
                        verify_token()
                    });
            })
            .catch(() => commit('LOGIN_FAILURE'));
    },

    // 세션유지중인지 가려냄 새로고침시 마다 작동
    INITIALIZE({ commit }) {
        const access = localStorage.getItem(TOKEN_STORAGE_ACCESS);
        const refresh = localStorage.getItem(TOKEN_STORAGE_REFRESH);
        console.log('Init')
        if (isProduction && access) {
            commit('REMOVE_TOKEN');
        }
        if (!isProduction && access) {
            commit('SET_TOKEN', { access, refresh });
        }
        if (access) {
            return api.getuser()
                .then(res => {

                    console.log(res.data)
                    commit('SET_USER', res.data)
                })
                .catch(err => {
                    verify_token()
                });
        }
    },

    SEARCH_USER({ commit }, filter) {
        return api.search_user(filter)
            .then((res) => {
                const movie_info = { userinfo: res.data.results, max_page: res.data.count }
                console.log(res.data.results)
                commit('SET_USERLIST', movie_info)
            })
    },

    EDIT_USER({ commit }, payload) {
        return api.edit_user(payload)
            .then(res => {
                return res
            })
    },

    CREATERATE({ commit }, payload) {
        return api.createrate(payload)
            .then((res) => {
                console.log(res.data)
                return res.data
                // this.$router.push("/movie/rate")

                // commit('RELOAD_RATE')
            })
            .catch(err => {
                if (err.response) {
                    throw Error('평점 에러')
                }
            })
    },

    DELRATE({ commit }, payload) {
        return api.delrate(payload)
            .then((res) => {
                console.log(res.data)
                return res.data
                // this.$router.push("/movie/rate")

                // commit('RELOAD_RATE')
            })
            .catch(err => {
                if (err.response) {
                    throw Error('수정 에러')
                }
            })
    },


    USERVISITCHECK({commit}, user) {
        return api.uservisit(user)
            .then((res) => {
                console.log(res.data) // mylist
            })
            .catch(err => console.log(err.response))

    },

    SUBSCRIPTION({ commit }) {
        return api.subscription()
            .then(res => {
                console.log(res.data)
                commit('SET_USER',res.data)
            })
            .catch(err => console.log(err.response))
    }


}
