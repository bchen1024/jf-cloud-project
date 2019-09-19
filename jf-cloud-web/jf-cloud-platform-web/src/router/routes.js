import Main from '@/components/main/index.vue'
import ParentView from '@/components/parent-view/index.vue'
import Home from '@/views/home/index.vue'
import Login from '@/views/login/index.vue'
import Role from '@/views/account/role/index.vue'
import User from '@/views/account/user/index.vue'
import App from '@/views/account/app/index.vue'
import Group from '@/views/account/group/index.vue'
import Permission from '@/views/account/permission/index.vue'
export default[
    {
        path: '/',
        name: 'main',
        component: Main,
        meta:{
            mainMenu:true
        },
        children:[
            {
                path:'/home',
                name:'home',
                component:Home,
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
                        component:Role,
                        meta:{
                            icon:'md-people'
                        }
                    },
                    {
                        path:'/export/myExport',
                        name:'myExport',
                        component:Role,
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
                        path:'/account/organization',
                        name:'organizationMgt',
                        component:Role,
                        meta:{
                            icon:'md-people'
                        }
                    },
                    {
                        path:'/account/role',
                        name:'roleMgt',
                        component:Role,
                        meta:{
                            icon:'md-people'
                        }
                    },
                    {
                        path:'/account/user',
                        name:'userMgt',
                        component:User,
                        meta:{
                            icon:'md-person'
                        }
                    },
                    {
                        path:'/account/group',
                        name:'groupMgt',
                        component:Group,
                        meta:{
                            icon:'ios-people'
                        }
                    },
                    {
                        path:'/account/permission',
                        name:'permissionMgt',
                        component:Permission,
                        meta:{
                            icon:'md-lock'
                        }
                    },
                    {
                        path:'/account/app',
                        name:'appMgt',
                        component:App,
                        meta:{
                            icon:'md-apps'
                        }
                    },
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
                        path:'/config/menu',
                        name:'menuMgt',
                        component:Role,
                        meta:{
                            icon:'md-menu'
                        }
                    },
                    {
                        path:'/config/property',
                        name:'propertyMgt',
                        component:Role,
                        meta:{
                            icon:'md-people'
                        }
                    },
                    {
                        path:'/config/i18n',
                        name:'i18nMgt',
                        component:User,
                        meta:{
                            icon:'md-person'
                        }
                    },
                    {
                        path:'/config/code',
                        name:'codeMgt',
                        component:User,
                        meta:{
                            icon:'md-person'
                        }
                    },
                    {
                        path:'/config/personalized',
                        name:'personalizedMgt',
                        component:User,
                        meta:{
                            icon:'md-person'
                        }
                    },
                    {
                        path:'/config/notice',
                        name:'noticeMgt',
                        component:User,
                        meta:{
                            icon:'md-person'
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
                        component:Role,
                        meta:{
                            icon:'md-people'
                        }
                    },
                    {
                        path:'/export/monitor',
                        name:'exportMonitor',
                        component:User,
                        meta:{
                            icon:'md-person'
                        }
                    },
                    {
                        path:'/import/template',
                        name:'importTemplate',
                        component:User,
                        meta:{
                            icon:'md-person'
                        }
                    },
                    {
                        path:'/export/template',
                        name:'exportTemplate',
                        component:User,
                        meta:{
                            icon:'md-person'
                        }
                    },
                ]
            },
            {
                path:'/personal',
                name:'personal',
                component:User,
                meta:{
                    hideMenu:true,
                    icon:'md-person'
                }
            },
            {
                path:'/account/user/detail',
                name:'userDetail',
                component:User,
                meta:{
                    parent:['accountMgt','userMgt'],
                    hideMenu:true,
                    icon:'md-person'
                }
            }
        ]
    },
    {
        path:'/login',
        name:'login',
        component:Login
    }
]