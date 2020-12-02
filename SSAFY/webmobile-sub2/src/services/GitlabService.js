import Api from '@/services/Api'

const BASE_URL = 'https://lab.ssafy.com/api/v4'

export default {
	getRepos(userName) {
		return Api(BASE_URL).get(`/users/${userName}/projects`)
	},
	async getCommits(fullName) {
		let d = new Date()
		d.setMonth(d.getMonth() - 1)
		const response = await Api(BASE_URL).get(`/projects/${fullName}/events?since=${d.toISOString()}&private_token=8UrHN-MnVikVxWPcP-dR`)
		if(response.status !== 200){
			return
		}
		const num = response.headers['x-total-pages']
		var list = []
		var res
		for(var i = 1;i <= num;i++){
			res = await Api(BASE_URL).get(`/projects/${fullName}/events?since=${d.toISOString()}&page=`+i+`&private_token=8UrHN-MnVikVxWPcP-dR`)
			list =list.concat(res.data)
		}
		return list
	}
}