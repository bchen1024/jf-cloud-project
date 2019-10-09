import ParentView from '@/views/main/components/parent-view/index.vue'
export default[
    {
        path: '/',
        name: 'main',
        redirect:'home',
        component: resolve =>require(['@/views/main/index.vue'],resolve),
        meta:{
            mainMenu:true
        },
        children:[
            {
                path:'/error/noPermission',
                name:'noPermission',
                component:resolve =>require(['@/views/error/401.vue'],resolve),
                meta:{
                    hideMenu:true
                }
            },
            {
                path:'/error/badGateway',
                name:'badGateway',
                component:resolve =>require(['@/views/error/502.vue'],resolve),
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
                        path:'/account/app',
                        name:'appMgt',
                        component:resolve =>require(['@/views/account/app/index.vue'],resolve),
                        meta:{
                            icon:'md-apps',
                            permissionCode:'app$page'
                        }
                    },
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
                    },
                    {
                        path:'/account/role',
                        name:'roleMgt',
                        component:resolve =>require(['@/views/account/role/index.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'role$page'
                        }
                    },
                    {
                        path:'/account/group',
                        name:'groupMgt',
                        component:resolve =>require(['@/views/account/group/index.vue'],resolve),
                        meta:{
                            icon:'ios-people',
                            permissionCode:'group$page'
                        }
                    },
                    {
                        path:'/account/permission',
                        name:'permissionMgt',
                        component:resolve =>require(['@/views/account/permission/index.vue'],resolve),
                        meta:{
                            icon:'md-lock',
                            permissionCode:'permission$list'
                        }
                    }
                ]
            },
            {
                name:'configMgt',
                path:'/config',
                component:ParentView,
                meta:{
                    noLink:true,
                    icon:'ios-settings'
                },
                children:[
                    {
                        path:'/config/property',
                        name:'propertyMgt',
                        component:resolve =>require(['@/views/config/property/index.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'property$page'
                        }
                    },
                    
                    {
                        path:'/config/code',
                        name:'codeMgt',
                        component:resolve =>require(['@/views/config/code/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'code$page'
                        }
                    },
                    {
                        path:'/config/organization',
                        name:'organizationMgt',
                        component:resolve =>require(['@/views/config/organization/index.vue'],resolve),
                        meta:{
                            icon:'md-people',
                            permissionCode:'organization$page'
                        }
                    },
                    {
                        path:'/config/employeeJob',
                        name:'jobMgt',
                        component:resolve =>require(['@/views/config/job/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'employeeJob$page'
                        }
                    },
                    {
                        path:'/config/notice',
                        name:'noticeMgt',
                        component:resolve =>require(['@/views/config/notice/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'notice$page'
                        }
                    },
                    {
                        path:'/config/template',
                        name:'templateMgt',
                        component:resolve =>require(['@/views/config/template/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'template$page'
                        }
                    },
                    {
                        path:'/config/i18n',
                        name:'i18nMgt',
                        component:resolve =>require(['@/views/config/i18n/index.vue'],resolve),
                        meta:{
                            icon:'md-person',
                            permissionCode:'i18n$page'
                        }
                    },
                    {
                        path:'/config/personalized',
                        name:'personalizedMgt',
                        component:resolve =>require(['@/views/config/personalized/index.vue'],resolve),
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
                path:'/account/role/detail',
                name:'roleDetail',
                component:resolve =>require(['@/views/account/role/detail.vue'],resolve),
                meta:{
                    parent:['accountMgt','roleMgt'],
                    hideMenu:true,
                    icon:'md-person'
                }
            },
            {
                path:'/account/app/detail',
                name:'appDetail',
                component:resolve =>require(['@/views/account/app/detail.vue'],resolve),
                meta:{
                    parent:['accountMgt','appMgt'],
                    hideMenu:true,
                    icon:'md-person',
                    permissionCode:'app$single|app$appUsers|app$appToken'
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