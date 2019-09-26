import http from '@/libs/request'
import util from '@/libs/util'


const prototypes={
    http,
    util
};

const install=function(Vue){
    Object.keys(prototypes).forEach(key => {
        Vue.prototype['$'+key]=prototypes[key];
    });
};
// auto install
if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue);
}
export default {prototypes,install};