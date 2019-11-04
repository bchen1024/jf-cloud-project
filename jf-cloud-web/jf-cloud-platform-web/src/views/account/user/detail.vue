<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('user$single',$store.state.permission.permissionList)">
            <JFDetail :ref="detailRef" :op="detailOp" :id="id" @detailEdit="openEdit"/>
            <Edit :formId="formId" formKey="userId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
    </Tabs>
</template>
<script>
import Edit from './edit.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
import detail from '@/mixins/detailMixins';
export default {
    mixins:[editMixins,detailMixins,detail],
    components:{
        Edit
    },
    data(){
        let vm=this;
        return {
            
        }
    },
    methods:{
        
    }
}
</script>