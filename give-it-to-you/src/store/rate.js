import axios from "axios";

export default {
	namespaced: true,
	actions: {
        getAllProductInterestRate(context){
            axios.get("http://localhost:3919/serve8080/rate/product")
            .then((response)=>{
                context.commit("GETALLPRODUCTINTERESTRATE", response.data);
            }).catch((error)=>{
                console.log(error);
            })
        }
	},
	mutations: {
		GETALLPRODUCTINTERESTRATE(state, productInterestRate) {
            state.productInterestRate = productInterestRate;
        }
	},
	state: {
		productInterestRate:[]
	},
	getters: {		
	},
}