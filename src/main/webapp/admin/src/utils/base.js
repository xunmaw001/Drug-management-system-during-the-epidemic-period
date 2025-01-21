const base = {
    get() {
        return {
            url : "http://localhost:8080/ssmukk17/",
            name: "ssmukk17",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/ssmukk17/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "疫情时期药物管理系统"
        } 
    }
}
export default base
