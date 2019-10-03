import JFGrid from './grid/index.vue';
import JFUser from './user/index.vue';
import JFStatus from './status/index.vue';
import JFType from './type/index.vue';
import JFDetail from './detail/index.vue';

const jfviews={
    JFGrid,
    JFUser,
    JFStatus,
    JFType,
    JFDetail
};

const install=function(Vue){
    Object.keys(jfviews).forEach(key => {
        Vue.component(key, jfviews[key]);
    });
};
// auto install
if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue);
}
export default {jfviews,install};