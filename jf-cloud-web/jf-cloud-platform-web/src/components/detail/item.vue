<template>
    <FormItem :label="item.label || $t(item.key)" :class="item.desc?'jf-form-detail-desc':''">
        <template v-if="item.type=='user'">
            <JFUser :userId="formData[item.key]"/>
        </template>
        <template v-else-if="item.type=='status'">
            <JFStatus :value="formData[item.key]" :type="item.key"/>
        </template>
        <template v-else-if="item.type=='type'">
            <JFType :value="formData[item.key]" :type="item.key"/>
        </template>
        <template v-else>
            <Icon v-if="formData[item.key] && formData[item.valid]=='Y'" type='md-checkmark' color='#19be6b' :size="18"/>
            <Icon v-if="formData[item.key] && formData[item.valid]=='N'" type='md-close' color='#ed4014' :size="18"/>
            {{item.render?item.render(item,formData):(formData[item.key] || '')}}
        </template>
    </FormItem>
</template>
<script>
export default {
    props:{
        item:{
            type:Object,
            required:true
        },
        formData:{
            type:Object,
            required:true,
            default:{}
        }
    }
}
</script>