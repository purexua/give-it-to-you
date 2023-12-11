import axios from "axios"

export default {
    namespaced: true,
    actions: {
        getCreditScoreInfo(context, userId) {
            return axios.get(`http://localhost:3919/serve8080/credit/score/info?userId=${userId}`)
                .then((response) => {
                    context.commit("GETCREDITSOCREINFO", response.data);
                }).catch((error) => {
                    console.log(error);
                })
        },
        updateCreditScoreLimitAmountAfterLoan(context, data) {
            axios({
                method: "put",
                url: "http://localhost:3919/serve8080/credit/limit/sub",
                params: {
                    userId: data.userId,
                    loanAmount: data.loanAmount
                }
            }).catch((error) => {
                console.log(error);
            })
        }
    },
    mutations: {
        GETCREDITSOCREINFO(state, creditScoreInfo) {
            state.creditScoreInfo = creditScoreInfo;
        }
    },
    state: {
        creditScoreInfo: {
            scoreId: '',
            userId: '',
            creditScore: '',
            limitAmount: '',
            monthlyIncome: '',
            expenses: '',
            employmentStatus: '',
            jobDuration: '',
            educationLevel: '',
            overdueRecords: '',
            assetsValue: '',
            socialConnections: '',
            legalDisputes: '',
            behaviorAnalysis: ''
        }
    },
    getters: {
    },
}