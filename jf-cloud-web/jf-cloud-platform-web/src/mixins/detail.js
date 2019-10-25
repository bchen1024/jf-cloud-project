export default{
    data(){
        let vm=this;
        return {
            detailId:'detail',
            tabId:'detail',
            detailRef:'detailRef',
            id:null,
            loadMap:{}
        }
    },
    created(){
        let vm=this,query=vm.$route.query;
        vm.id=query.id;
        if(query.tabId){
            vm.tabId=query.tabId;
        }
        vm.load();
    },
    methods:{
        load(name){
            let vm=this;
            if(name){
                vm.tabId=name;
            }
            if(!vm.loadMap[vm.tabId]){
                if(vm.tabId==vm.detailId){
                    vm.loadDetail();
                }else{
                    if(vm.eventMap && vm.eventMap[vm.tabId]){
                        vm.eventMap[vm.tabId].call();
                    }
                }
                vm.loadMap[vm.tabId]=true;
            }
        },
        loadDetail(){
            let vm=this;
            let detail=vm.$refs[vm.detailRef];
            if(detail){
                detail.load();
            }
        },
        getQuery(){
            return this.$route.query;
        },
        autoLoad(){
            let tabId=this.getQuery().tabId;
            return (tabId==this.detailId || !tabId)
        }
    }
}