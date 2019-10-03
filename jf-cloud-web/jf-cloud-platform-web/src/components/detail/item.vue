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
            {{formData[item.key] || ''}}
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