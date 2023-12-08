import axios from "axios"

export default {
    namespaced: true,
    actions: {
        getApplicationList(context, data) {
            axios({
                method: "get",
                url: `http://localhost:3919/serve8080/application/info/page`,
                params: {
                    pageNum: data.pageNum,
                    pageSize: data.pageSize,
                }
            }).then((response) => {
                context.commit("GETAPPLICATIONLIST", response.data);
            }).catch((error) => {
                console.log(error);
            })
        },
        getApplicationListCount(context, data) {
            axios({
                method: "get",
                url: `http://localhost:3919/serve8080/application/info/count`,
                params: {
                    pageNum: data.pageNum,
                    pageSize: data.pageSize,
                }
            }).then((response) => {
                context.commit("GETAPPLICATIONLISTCOUNT", response.data);
            }).catch((error) => {
                console.log(error);
            })
        },
        updateLoanApplication(context, data) {
            axios({
                method: "put",
                url: `http://localhost:3919/serve8080/application/change/status`,
                params: {
                    applicationId: data.applicationId,
                    status: data.status,
                }
            }).then((response) => {
                context.dispatch("getApplicationList", {
                    pageNum: data.pageNum,
                    pageSize: data.pageSize,
                });
                context.dispatch("getApplicationListCount", {
                    pageNum: data.pageNum,
                    pageSize: data.pageSize,
                });
            }).catch((error) => {
                console.log(error);
            })
        },
    },
    mutations: {
        GETAPPLICATIONLIST(state, applicationList) {
            state.applicationList = applicationList;
        },
        GETAPPLICATIONLISTCOUNT(state, applicationListCount) {
            state.applicationListCount = applicationListCount;
        },
    },
    state: {
        applicationList: [],
        applicationListCount: 0,
    },
    getters: {
    },
}