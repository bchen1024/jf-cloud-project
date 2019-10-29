<template>
    <div>
        <JFDetail :op="detailOp"  @detailEdit="openEdit"/>
        <Edit :formId="formId" formKey="userId"
                :visible.sync="showEdit" 
                :formData="formData"/>
    </div>
</template>
<script>
import Edit from '@/views/account/user/edit.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
export default {
    mixins:[editMixins,detailMixins],
    components:{
        Edit
    },
    data(){
        let vm=this;
        return {
            detailOp:{
                autoLoad:true,
                search:{
                    url:'jfcloud/jf-cloud-platform/personal/baseInfo'
                },
                items:[
                    {cols:[
                        {key:'userId'},
                        {key:'userNo'},
                    ]},
                    {cols:[
                        {key:'userCn'},
                        {key:'userEn'}
                    ]},
                    {cols:[
                        {key:'mobile',valid:'mobileValidated'},
                        {key:'email',valid:'emailValidated'}
                    ]},
                    {cols:[
                        {key:'userType',type:'type'},
                        {key:'userStatus',type:'status'}
                    ]},
                     {cols:[
                        {key:'passwordUpdateTime'},
                        {key:'lockTime'}
                    ]},
                ]
            }
        }
    }
}
</script>