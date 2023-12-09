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
            ).then((response) => {
                context.commit("GETALLPRODUCTINTERESTRATE", response.data);
            }).catch((error) => {
                console.log(error);
            })
        },
        addProductInterestRate(context, productInterestRate) {
            axios(
                {
                    method: "post",
                    url: "http://localhost:3919/serve8080/rate/product/add",
                    data: productInterestRate
                }
            ).then((response) => {
                context.dispatch("getAllProductInterestRate");
            }).catch((error) => {
                console.log(error);
            })
        },
        deleteProductInterestRate(context, id) {
            axios(
                {
                    method: "delete",
                    url: "http://localhost:3919/serve8080/rate/product/delete",
                    params: {
                        rateId: id
                    }
                }
            ).then((response) => {
                context.dispatch("getAllProductInterestRate");
            }).catch((error) => {
                console.log(error);
            })
        },
        updateProductInterestRate(context, productInterestRate) {
            axios(
                {
                    method: "put",
                    url: "http://localhost:3919/serve8080/rate/product/update",
                    data: productInterestRate
                }
            ).then((response) => {
                context.dispatch("getAllProductInterestRate");
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