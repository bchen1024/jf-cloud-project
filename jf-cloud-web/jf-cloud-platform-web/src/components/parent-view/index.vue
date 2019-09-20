<template>
    <router-view ref="child"/>
</template>
<script>
export default {
    created(){
        let router=this.$route;
    }
}
</script>
