import ParentView from '@/components/main/components/parent-view/index.vue'
export default[
    {
        path:'/error/badGateway',
        name:'badGateway',
        component:resolve =>require(['@/components/error/502.vue'],resolve),
        meta:{
            hideMenu:true,
            noNeedLogin:true
        }
    },
    {
        path: '/',
        name: 'main',
        redirect:'home',
        component: resolve =>require(['@/components/main/index.vue'],resolve),
        meta:{
            mainMenu:true
        },
        children:[
            {
                path:'/error/noPermission',
                name:'noPermission',
                component:resolve =>require(['@/components/error/401.vue'],resolve),
                meta:{
                    hideMenu:true
                }
            },
            {
                path:'/home',
                name:'home',
                component:resolve =>require(['@/views/home/index.vue'],resolve),
                meta:{
                    icon:'md-home'
                }
            },
            {
                path:'/workspace',
                name:'workspace',
                component:ParentView,
                meta:{
                    icon:'md-home'
                },
                children:[
                    {
                        path:'/import/myImport',
                        name:'myImport',
                        component:resolve =>require(['@/views/excel/import/myImport.vue'],resolve),
                        meta:{
                            icon:'md-people'
                        }
                    },
                    {
                        path:'/export/myExport',
                        name:'myExport',
                        component:resolve =>require(['@/views/excel/export/myExport.vue'],resolve),
                        meta:{
                            icon:'md-people'
                        }
                    }
                ]
            },
            {
                name:'accountMgt',
                path:'/account',
                component:ParentView,
                meta:{
                    noLink:true,
                    icon:'ios-people'
                },
                children:[
                    {
                        path:'/account/user',
                        name:'userMgt',
                        component:resolve =>require(['@/views/account/user/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'user$page'
                        }
                    },
                    {
                        path:'/account/employee',
                        name:'employeeMgt',
                        component:resolve =>require(['@/views/account/employee/index.vue'],resolve),
                        meta:{
                            icon:'ios-people',
                            permissionCode:'employee$page'
                        }
                    }
                ]
            },
            {
                name:'securityMgt',
                path:'/security',
                component:ParentView,
                meta:{
                    noLink:true,
                    icon:'ios-people'
                },
                children:[
                    {
                        path:'/security/app',
                        name:'appMgt',
                        component:resolve =>require(['@/views/security/app/index.vue'],resolve),
                        meta:{
                            icon:'md-apps',
                            permissionCode:'app$page'
                        }
                    },
                    {
                        path:'/security/group',
                        name:'groupMgt',
                        component:resolve =>require(['@/views/security/group/index.vue'],resolve),
                        meta:{
                            icon:'ios-people',
                            permissionCode:'group$page'
                        }
                    },
                    {
                        path:'/security/role',
                        name:'roleMgt',
                        component:resolve =>require(['@/views/security/role/index.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'role$page'
                        }
                    },
                    {
                        path:'/security/permission',
                        name:'permissionMgt',
                        component:resolve =>require(['@/views/security/permission/index.vue'],resolve),
                        meta:{
                            icon:'md-lock',
                            permissionCode:'permission$tree'
                        }
                    }
                ]
            },
            {
                name:'basicConfig',
                path:'/basic/config',
                component:ParentView,
                meta:{
                    noLink:true,
                    icon:'ios-settings'
                },
                children:[
                    {
                        path:'/basic/config/organization',
                        name:'organizationMgt',
                        component:resolve =>require(['@/views/config/basic/organization/index.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'organization$tree'
                        }
                    },
                    {
                        path:'/basic/config/employeeJob',
                        name:'jobMgt',
                        component:resolve =>require(['@/views/config/basic/job/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'employeeJob$page'
                        }
                    }
                ]
            },
            {
                name:'appConfig',
                path:'/app/config',
                component:ParentView,
                meta:{
                    noLink:true,
                    icon:'ios-settings'
                },
                children:[
                    {
                        path:'/app/config/property',
                        name:'propertyMgt',
                        component:resolve =>require(['@/views/config/app/property/index.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'user$page'
                        }
                    },
                    
                    {
                        path:'/app/config/code',
                        name:'codeMgt',
                        component:resolve =>require(['@/views/config/app/code/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'code$page'
                        }
                    },
                    
                    {
                        path:'/app/config/notice',
                        name:'noticeMgt',
                        component:resolve =>require(['@/views/config/app/notice/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'notice$page'
                        }
                    },
                    {
                        path:'/app/config/template',
                        name:'templateMgt',
                        component:resolve =>require(['@/views/config/app/template/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'template$page'
                        }
                    },
                    {
                        path:'/app/config/i18n',
                        name:'i18nMgt',
                        component:resolve =>require(['@/views/config/app/i18n/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'i18n$page'
                        }
                    },
                    {
                        path:'/app/config/personalized',
                        name:'personalizedMgt',
                        component:resolve =>require(['@/views/config/app/personalized/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'personalized$page'
                        }
                    }
                ]
            },
            {
                name:'importOrExportMgt',
                path:'/importOrExport',
                component:ParentView,
                meta:{
                    noLink:true,
                    icon:'ios-settings'
                },
                children:[
                    {
                        path:'/import/monitor',
                        name:'importMonitor',
                        component:resolve =>require(['@/views/excel/import/importMonitor.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'importTask$page'
                        }
                    },
                    {
                        path:'/export/monitor',
                        name:'exportMonitor',
                        component:resolve =>require(['@/views/excel/export/exportMonitor.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'exportTask$page'
                        }
                    },
                    {
                        path:'/import/template',
                        name:'importTemplate',
                        component:resolve =>require(['@/views/excel/import/importTemplate.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'importTemplate$page'
                        }
                    },
                    {
                        path:'/export/template',
                        name:'exportTemplate',
                        component:resolve =>require(['@/views/excel/export/exportTemplate.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'exportTemplate$page'
                        }
                    },
                ]
            },
            {
                name:'monitorMgt',
                path:'/monitor',
                component:ParentView,
                meta:{
                    noLink:true,
                    icon:'ios-settings'
                },
                children:[
                    {
                        path:'/monitor/auditLog',
                        name:'auditLog',
                        component:resolve =>require(['@/views/monitor/auditLog/index.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'auditLog$page'
                        }
                    },
                    {
                        path:'/monitor/serverLog',
                        name:'serverLog',
                        component:resolve =>require(['@/views/monitor/auditLog/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'serverLog$list'
                        }
                    }
                ]
            },
            {
                path:'/personal',
                name:'personal',
                component:resolve =>require(['@/views/personal/index.vue'],resolve),
                meta:{
                    hideMenu:true,
                    icon:'md-person'
                }
            },
            {
                path:'/account/user/detail',
                name:'userDetail',
                component:resolve =>require(['@/views/account/user/detail.vue'],resolve),
                meta:{
                    parent:['accountMgt','userMgt'],
                    hideMenu:true,
                    icon:'md-person'
                }
            },
            {
                path:'/account/employee/detail',
                name:'employeeDetail',
                component:resolve =>require(['@/views/account/employee/detail.vue'],resolve),
                meta:{
                    parent:['accountMgt','employeeMgt'],
                    hideMenu:true,
                    icon:'md-person',
                    permissionCode:'employee$single'
                }
            },
            {
                path:'/security/role/detail',
                name:'roleDetail',
                component:resolve =>require(['@/views/security/role/detail.vue'],resolve),
                meta:{
                    parent:['accountMgt','roleMgt'],
                    hideMenu:true,
                    icon:'md-person'
                }
            },
            {
                path:'/security/app/detail',
                name:'appDetail',
                component:resolve =>require(['@/views/security/app/detail.vue'],resolve),
                meta:{
                    parent:['accountMgt','appMgt'],
                    hideMenu:true,
                    icon:'md-person',
                    permissionCode:'app$single|app$appUsers|app$appToken'
                }
            },
            {
                path:'/security/group/detail',
                name:'groupDetail',
                component:resolve =>require(['@/views/security/group/detail.vue'],resolve),
                meta:{
                    parent:['accountMgt','groupMgt'],
                    hideMenu:true,
                    icon:'md-person',
                    permissionCode:'group$single'
                }
            }
        ]
    },
    {
        path:'/login',
        name:'login',
        component:resolve =>require(['@/views/login/index.vue'],resolve),
        meta:{
            noNeedLogin:true
        }
    }
]