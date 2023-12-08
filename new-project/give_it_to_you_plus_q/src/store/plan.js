import axios from "axios"

export default {
    namespaced: true,
    actions: {
        addRepaymentPlan(context, data) {
            axios({
                method: "post",
                url: "http://localhost:3919/serve8080/plan/add",
                data: data,
            })
        },
    },
    mutations: {

    },
    state: {

    },
    getters: {
    },
}