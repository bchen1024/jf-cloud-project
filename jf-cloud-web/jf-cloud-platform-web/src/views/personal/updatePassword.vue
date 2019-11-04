<template>
    <Form ref='updatePasswordForm' v-model="formData" :rules="formRules" style="margin-left:35%;margin-right:35%;">
        <FormItem :label="$t('userNo')"   prop="userNo">
            <Input v-model="formData.userNo" disabled/>
        </FormItem>
        <FormItem :label="$t('originalPassword')"   prop="originalPassword">
            <Input type='password' name='originalPassword' v-model="formData.originalPassword" autocomplete="off"/>
        </FormItem>
        <FormItem :label="$t('newPassword')"   prop="newPassword">
            <Input type='password' name='newPassword' v-model="formData.newPassword" autocomplete="off"/>
        </FormItem>
        <FormItem :label="$t('confirmPassword')"   prop="confirmPassword">
            <Input type='password' v-model="formData.confirmPassword" autocomplete="off"/>
        </FormItem>
        <FormItem v-if="formData.originalPassword && formData.newPassword && formData.confirmPassword" prop="validateCode">
            <Row>
                <Col span="14">
                    <Input type="text" v-model="formData.validateCode" :placeholder="$t('validateCode')">
                        <Icon type="ios-lock-outline" slot="prepend"></Icon>
                    </Input>
                </Col>
                <Col span="10">
                    <img  v-if="imgSrc && formData.userNo" :src="imgSrc"  style="margin-left:8px;cursor: pointer;" :title="$t('validCodeTip')"/>
                </Col>
            </Row>
        </FormItem>
    </Form>
</template>
<script>
export default {
    data(){
        return {
            formData:{userNo:this.$store.state.user.userInfo.userNo},
            formRules:{

            },
            imgSrc:null
        }
    }
}
</script>