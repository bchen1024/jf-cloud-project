<template>
    <Tree :data="data"></Tree>
</template>
<script>
export default {
    data(){
        return {
            data:[
                {title:'深圳前海美联科技有限公司',children:[
                    {title:'技术创新部',children:[
                        {title:'架构组'},
                        {title:'PM管理组'},
                        {title:'产品设计组'},
                        {title:'后台开发组'},
                        {title:'WEB开发组'},
                        {title:'运维测试组'}
                    ]},
                    {title:'人事管理部'}
                ]}
            ]
        }
    }
}
</script>