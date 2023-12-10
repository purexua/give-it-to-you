import axios from 'axios'

export default {
	namespaced: true,
	actions: {
		getUserInfoById(context, data) {
			axios({
				method: 'get',
				url: 'http://localhost:3919/serve8080/user/info',
				params: {
					userId: data.userId,
				}
			}).then(res => {
				context.commit('SAVEUSERINFO', res.data)
			}).catch(err => {
				console.log(err)
			})
		},
		changePassword(context, data) {
			axios({
				method: 'put',
				url: 'http://localhost:3919/serve8080/user/change/password',
				params: {
					userId: data.userId,
					password: data.password,
				}
			}).then(res => {
				context.commit('CHANGEPASSWORD', data.password)
				alert('修改成功')
			}).catch(err => {
				console.log(err)
				alert('修改失败')
			})
		},
		changeInfo(context, data) {
			axios({
				method: 'PUT',
				url: 'http://localhost:3919/serve8080/user/change/info',
				data: {
					userId: data.userId,
					userName: data.userName,
					realName: data.realName,
					idCard: data.idCard,
					email: data.email,
					address: data.address,
					phoneNumber: data.phoneNumber,
				}
			}).then(res => {
				alert('修改成功')
				context.commit('CHANGEINFO', data)
			}).catch(err => {
				console.log(err)
			})
		},
		recharge(context, rechargeAmount) {
			axios({
                method: 'PUT',
                url: 'http://localhost:3919/serve8080/user/recharge',
                params: {
                    userId: context.state.user.userId,
                    rechargeAmount: rechargeAmount,
                }
            }).then(res => {
				context.commit('CHANGEWALLET', rechargeAmount)
				alert('充值成功')
			}).catch(err => {
				console.log(err)
				alert('充值失败')
			})
		},
	},
	mutations: {
		SAVEUSERINFO(state, user) {
			state.user = user;
		},
		CHANGEPASSWORD(state, password) {
			state.user.password = password
		},
		CHANGEINFO(state, data) {
			state.user.userId = data.userId
			state.user.userName = data.userName
			state.user.realName = data.realName
			state.user.idCard = data.idCard
			state.user.email = data.email
			state.user.address = data.address
			state.user.phoneNumber = data.phoneNumber
		},
		CHANGEWALLET(state, rechargeAmount) {
			state.user.wallet += rechargeAmount
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
			healthStatus: '',
			creditStatus: '',
			role: '',
			wallet: '',
		}
	},
	getters: {
	},
}