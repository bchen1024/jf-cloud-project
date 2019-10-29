<template>
    <Layout class="jf-layout-full">
        <Sider class="jf-layout-sider" :width="280">
            <Spin size="large" fix v-if="loading"></Spin>
            <!--权限树-->
            <Tree ref="permissionTree" :empty-text="$t('noPermissionData')" 
                :render="renderContent" 
                :data="treeData" 
                @on-select-change="onSelectChange"></Tree>
        </Sider>
        <Content class="jf-layout-content">
            <Alert>
                <p>{{$t('permissionTreeTip')}}</p>
                <p>{{$t('permissionSyncTip')}}</p>
                <p>{{$t('permissionDeleteTip')}}</p>
            </Alert>
            <!--操作按钮区-->
            <div class="margin-bottom-12">
                <!--同步权限-->
                <Button icon="md-sync" :loading="syncLoading" type="primary" @click="syncPermission()" v-permission="'permission$sync'">
                    {{$t('syncPermission')}}
                </Button>
                <!--刷新权限-->
                <Button  icon="md-refresh" :loading="loading" @click="loadPermission()" v-permission="'permission$tree'">
                    {{$t('refresh')}}
                </Button>
                <!--添加权限-->
                <Button icon="md-add" @click="addPermission()" v-permission="'permission$save'">
                    {{$t('addPermission')}}
                </Button>
                <!--删除权限，只有失效的注解权限才能被删除-->
                <Button type="error" icon="md-trash" :loading="deleteLoading" @click="deletePermission()"  v-permission="'permission$delete'"
                    :disabled="(disabled && data.enableFlag=='Y') || !data.permissionId">
                    {{$t('delete')}}
                </Button>
                <!--保存权限-->
                <Button icon="md-checkmark" :loading="saveLoading" type="primary" @click="savePermission()" class="float-right">
                    {{$t('save')}}
                </Button>
            </div>
            <Form ref='formPermission' :model="data" :rules="formRules" label-position="top" :disabled="disabled">
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('permissionType')"  prop="permissionType">
                            <Select v-model="data.permissionType" @on-change="onChange">
                                <Option value="resources">{{$t('resources')}}</Option>
                                <Option value="method">{{$t('method')}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('permissionCode')" prop="permissionCode">
                            <Input v-model="data.permissionCode" />
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('permissionDescCn')" prop="permissionDescCn">
                            <Input v-model="data.permissionDescCn" />
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('permissionDescEn')" prop="permissionDescEn">
                            <Input v-model="data.permissionDescEn" />
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('parentCode')" prop="parentCode" :class="{requireStar:parentRequire}">
                            <Select placeholder="" :disabled="data.permissionType=='resources'" v-model="data.parentCode" >
                                <Option v-for="item in parentCodes" :value="item.value" :key="item.value">{{item.label}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('permissionSource')" prop="permissionSource">
                            <Select disabled v-model="data.permissionSource">
                                <Option value="annotation">{{$t('annotation')}}</Option>
                                <Option value="custom">{{$t('custom')}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('sortNo')" prop="permissionSort">
                            <Input v-model="data.permissionSort" />
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('permissionParentCode')" class="jf-detail-item">
                            {{(data.permissionId && data.permissionType=='method')?data.parentCode+'$'+data.permissionCode:''}}
                        </FormItem>
                    </Col>
                </Row>
                <template v-if="data.permissionId">
                    <Row :gutter="32">
                        <Col :span="12">
                            <FormItem :label="$t('createBy')" class="jf-detail-item">
                                <JFUser :userId="data.createBy"/>
                            </FormItem>
                        </Col>
                        <Col :span="12">
                            <FormItem :label="$t('createDate')" class="jf-detail-item">
                                {{data.createDate}}
                            </FormItem>
                        </Col>
                    </Row>
                    <Row :gutter="32">
                        <Col :span="12">
                            <FormItem :label="$t('lastUpdateBy')" class="jf-detail-item">
                                <JFUser :userId="data.lastUpdateBy"/>
                            </FormItem>
                        </Col>
                        <Col :span="12">
                            <FormItem :label="$t('lastUpdateDate')" class="jf-detail-item">
                                {{data.lastUpdateDate}}
                            </FormItem>
                        </Col>
                    </Row>
                </template>
            </Form>
        </Content>
    </Layout>
</template>
<script src="./index.js"></script>