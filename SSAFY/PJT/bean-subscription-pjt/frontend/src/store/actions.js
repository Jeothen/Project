import api from '../api/index'
import { store } from './index'
import { router } from '../router'
const isProduction = process.env.NODE_ENV === 'production';
const TOKEN_STORAGE_ACCESS = 'TOKEN_STORAGE_ACCESS';
const TOKEN_STORAGE_REFRESH = 'TOKEN_STORAGE_REFRESH';

export default {
  DELIVERY({commit}){
    console.log('actions로 감')
    return api.delivery_list().then((res)=>{
      console.log(res)
    })
    //   console.log(res.data)
    //   return res.data
  }
}
