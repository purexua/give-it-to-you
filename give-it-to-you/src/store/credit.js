import axios from "axios";

export default {
    namespaced: true,
    actions: {
        getUserCreditScoreByUserId(context, userId) {
            axios.get(`http://localhost:3919/serve8080/credit/score?userId=${userId}`)
                .then((response) => {
                    context.commit("GETUSERCREDITSCOREBYUSERID", response.data);
                }).catch((error) => {
                    console.log(error);
                })
        }
    },
    mutations: {
        GETUSERCREDITSCOREBYUSERID(state, userCreditScore) {
            state.userCreditScore = userCreditScore;
        }
    },
    state: {
        userCreditScore: '',
    },
    getters: {
    },
}