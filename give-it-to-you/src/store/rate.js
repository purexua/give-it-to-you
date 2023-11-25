import axios from 'axios'
export default {
	namespaced: true,
	actions: {
		
	},
	mutations: {
		GETALLPRODUCTINTERESTRATE(state) {
            axios.get('http://localhost:3919/serve8080/loan/application/product')
            .then(response => {
                state.productInterestRate = response.data;
            })
        }
	},
	state: {
		productInterestRate:[]
	},
	getters: {

	},
}