export default {
	namespaced: true,
	actions: {
		getUserInfoById(context, data) {
			axios({
				method: 'get',
				url: 'http://localhost:3919/serve8080/getUserInfo',
				params: {
					userId: data.userId,
				}
			}).then(res => {
				context.commit('SAVEUSERINFO', res.data)
			}).catch(err => {
				console.log(err)
			})
		}
	},
	mutations: {
		SAVEUSERINFO(state, user) {
			state.user = user;
		},
		CHANGEPASSWORD(state, password) {
			state.user.password = password
		},
		CHANGEINFO(state, user) {
			state.user.realName = user.realName
			state.user.email = user.email
			state.user.idCard = user.idCard
			state.user.address = user.address
			state.user.phoneNumber = user.phoneNumber
		},
		CHANGEBALANCE(state, balance) {
			state.user.balance = state.user.balance + balance
		},
	},
	state: {
		user: {
			userId: '',
			userName: '',
			password: '',
			realName: '',
			idCard: '',
			email: '',
			address: '',
			phoneNumber: '',
			balance: '',
			role: '',
		}
	},
	getters: {
	},
}