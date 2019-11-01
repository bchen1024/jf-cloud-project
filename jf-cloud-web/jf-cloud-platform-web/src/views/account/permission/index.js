export default {
    data(){
        let vm=this;
        return {
            loading:false,
            saveLoading:false,
            syncLoading:false,
            deleteLoading:false,
            parentRequire:false,
            treeData:[],
            data:{},
            formRules:{
                permissionType:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionCode:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionDescCn:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionDescEn:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionSource:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionSort:[
                    {type:'integer',message:vm.$t('validator.integer')}
                ]
            },
            parentCodes:[]
        }
    },
    computed:{
        disabled(){
            return this.data.permissionSource=='annotation';
        }
    },
    created(){
        this.loadPermission();
    },
    methods:{
        /**
         * 加载权限树
         */
        loadPermission(){
            let vm=this;
            vm.loading=true;
            if(vm.$refs.formPermission){
                vm.$refs.formPermission.resetFields();
            }
            vm.data={permissionSource:'custom'};
            vm.treeData=[];
            vm.$http.post('jfcloud/jf-cloud-platform/security/permission/tree',{appCode:vm.$store.state.app.appInfo.appCode}).then(result=>{
                vm.treeData=result.data;
                let parentCodes=[];
                vm.loadResourcesCode(vm.treeData,parentCodes);
                vm.parentCodes=parentCodes;
            }).catch(error=>{}).then(()=>{
                vm.loading=false;
            });
        },
        /**
         * 加载资源code
         * @param {*} list 
         * @param {*} parentCodes 
         */
        loadResourcesCode(list,parentCodes){
            let vm=this;
            list.forEach(item=>{
                if(item.permissionType=='resources'){
                    parentCodes.push({value:item.permissionCode,label:item.title});
                }
                if(item.children && item.children.length>0){
                    vm.loadResourcesCode(item.children,parentCodes);
                }
            });
        },
        /**
         * 权限树选择
         * @param {*} nodes 
         */
        onSelectChange(nodes){
            let vm=this;
            if(nodes && nodes.length>0){
                if(vm.$refs.formPermission){
                    vm.$refs.formPermission.resetFields();
                }
                vm.onChange(nodes[0].permissionType);
                vm.data=Object.assign({},nodes[0]);
                vm.$util.dispatchUser(vm,vm.data);
            }
        },
        /**
         * 权限类型改变
         * @param {*} value 
         */
        onChange(value){
            let vm=this;
            //权限类型为资源时，父编码非必填
            if(value=='resources'){
                vm.parentRequire=false;
                vm.data.parentCode='';
                vm.$set(vm.formRules,'parentCode',[]);
            }
            //权限类型为方法时，编码非必填
            else if(value=='method'){
                vm.parentRequire=true;
                vm.$set(vm.formRules,'parentCode',[{required:true,message:vm.$t('validator.notEmpty')}]);
            }
        },
        /**
         * 添加权限点
         */
        addPermission(){
            let vm=this;
            let data={permissionSource:'custom',};
            let permissionType=vm.data.permissionType;
            //如果选中的是资源，直接用权限编码作为新增权限点的父编码
            if(permissionType=='resources'){
                data.parentCode=vm.data.permissionCode;
            }
            //如果选中的是方法，直接用当前权限父编码作为新增权限点的父编码
            else if(permissionType=='method'){
                data.parentCode=vm.data.parentCode;
            }
            if(vm.data.permissionId){
                data.permissionType='method';
            }
            vm.$refs.formPermission.resetFields();
            vm.data=data;
        },
        /**
         * 保存权限点
         */
        savePermission(){
            let vm=this;
            this.$refs.formPermission.validate((valid) => {
                if (valid) {
                    vm.saveLoading=true;
                    vm.data.appCode=vm.$store.state.app.appInfo.appCode;
                    vm.$http.put('jfcloud/jf-cloud-platform/security/permission/save',vm.data,{
                        headers:{op:'save'}
                    }).then(result=>{
                        vm.loadPermission();                        
                    }).catch(error=>{}).then(()=>{
                        vm.saveLoading=false;
                    });
                }
            });
        },
        /**
         * 同步权限
         */
        syncPermission(){
            let vm=this;
            vm.syncLoading=true;
            let appInfo=vm.$store.state.app.appInfo;
            vm.$http.post('jfcloud/jf-cloud-platform/security/permission/sync',{
                appCode:appInfo.appCode,contextPath:appInfo.contextPath
            },{
                headers:{op:'sync'}
            }).then(result=>{
                vm.loadPermission();
            }).catch(error=>{}).then(()=>{
                vm.syncLoading=false;
            });
        }
        ,
        /**
         * 删除权限点
         */
        deletePermission(){
            let vm=this;
            let selected=this.$refs.permissionTree.getSelectedNodes();
            if(selected.length>0){
                //存在子级不能删除
                if(selected[0].children && selected[0].children.length>0){
                    vm.$Message.warning(vm.$t('deleteNodeWarning'));
                    return;
                }
                //执行删除操作
                let appInfo=vm.$store.state.app.appInfo;
                vm.$Modal.confirm({
                    title: vm.$t('confirm'),
                    content: vm.$t('deleteConfirm'),
                    onOk: () => {
                        vm.$http.delete('jfcloud/jf-cloud-platform/security/permission/delete',{
                            data:{appCode:appInfo.appCode,id:selected[0].permissionId},
                            headers:{op:'delete'}
                        }).then(result=>{
                            vm.loadPermission();
                        });
                    }
                });
            }
        },
        /**
         * 权限树节点自定义渲染
         * @param {*} h 
         * @param {*} param1 
         */
        renderContent (h, { root, node, data }) {
            //权限点图标
            let icon='ios-paper-outline';
            if((data.children && data.children.length>0) || data.permissionType=='resources'){
                icon='ios-folder-outline';
            }
            //权限点是否失效
            let invalid=false;
            if(data.enableFlag!='Y'){
                invalid=true;
            }

            //是否一小时内添加
            let dot=false;
            if(data.createDate && (new Date()-new Date(data.createDate)<1000*3600)){
                dot=true;
            }
            return h('span', [
                h('Icon', {
                    props: {type: icon,size:16},
                    style: {marginRight: '8px'}
                }),
                h('Badge',{props:{dot:dot}},
                    [
                        h('span',{style:{
                            //失效显示删除线
                            textDecoration:invalid?'line-through':'none',cursor: 'pointer'
                        },class:'ivu-tree-title ' + (node.node.selected ?'ivu-tree-title-selected':''),
                            on: {
                                click: () => {
                                    if (!node.node.selected){
                                        this.$refs.permissionTree.handleSelect(node.nodeKey); //手动选择树节点
                                    } 
                                }
                            }
                        }, data.title)
                    ]
                )
            ]);
        }
    }
}