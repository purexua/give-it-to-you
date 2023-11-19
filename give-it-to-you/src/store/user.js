export default {
	namespaced: true,
	actions: {
		
	},
	mutations: {
		SAVEUSERINFO(state, user) {
			state.user = user;
		}
	},
	state: {
		user:{
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