import axios from "axios"

export default {
    namespaced: true,
    actions: {
        // getUserCreditScoreByUserId(context, userId) {
        //     axios.get(`http://localhost:3919/serve8080/credit/score?userId=${userId}`)
        //         .then((response) => {
        //             context.commit("GETUSERCREDITSCOREBYUSERID", response.data);
        //         }).catch((error) => {
        //             console.log(error);
        //         })
        // },
        updateLimitAmountAfterLoanApplication(context, data) {
            axios({
                method: 'put',
                url: 'http://localhost:3919/serve8080/credit/score',
                params: {
                    userId: data.userId,
                    amount: data.amount
                }
            }).then((response) => {
                context.dispatch("getUserCreditScoreByUserId", data.userId);
            }).catch((error) => {
                console.log(error);
            })
        },
        addLimitAmountAfterRepayment(context, data) {
            axios({
                method: 'put',
                url: 'http://localhost:3919/serve8080/credit/score/return',
                params: {
                    userId: data.userId,
                    amount: data.amount
                }
            }).then((response) => {
                context.dispatch("getUserCreditScoreByUserId", data.userId);
            }).catch((error) => {
                console.log(error);
            })
        },
        subBalanceAfterRepament(context, data) {
            axios({
                method: 'put',
                url: 'http://localhost:3919/serve8080/changeBalance',
                params: {
                    userId: data.userId,
                    balance: data.balance
                }   
            }).then(() => {
            }).catch((error) => {
                console.log(error);
            })
        },
    },
    mutations: {
        GETUSERCREDITSCOREBYUSERID(state, userCreditScore) {
            state.userCreditScore = userCreditScore;
        },
    },
    state: {
        userCreditScore: '',
    },
    getters: {
    },
}