export default{
    type:{
        appType:{
            1:'System',
            2:'Business'
        },
        userType:{
            1:'Employee',
            2:'Register'
        },
        employeeType:{
            1:'Full-time Job',
            2:'Part-time Job',
            3:'On Trial'
        },
        logType:{
            audit:'Audit',
            login:'Login',
            performance:'Performance'
        },
        userSex:{
            1:'Male',
            2:'Female'
        }
    },
    status:{
        userStatus:{
            'Y':'Normal',
            'L':'Lock',
            'D':'Delete'
        },
        employeeStatus:{
            '1':'Incumbency',
            '2':'Leave Job'
        },
        applyStatus:{
            'Y':'Can Apply',
            'N':'Not Apply'
        },
        grantStatus:{
            1:'Not Effective',
            2:'Normal',
            3:'About To Expire',
            4:'Expired'
        }
    }
}