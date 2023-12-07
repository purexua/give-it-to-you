import axios from "axios";

export default {
    namespaced: true,
    actions: {
        getAllProductInterestRate(context) {
            axios(
                {
                    method: "get",
                    url: "http://localhost:3919/serve8080/rate/product/info"
                }
            )
                .then((response) => {
                    context.commit("GETALLPRODUCTINTERESTRATE", response.data);
                }).catch((error) => {
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
        productInterestRate: []
    },
    getters: {
    },
}