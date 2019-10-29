import axios from 'axios';
import util from './util';
import i18n from '@/local';
import {Message} from 'view-design';

// 创建axios实例
const http = axios.create({
  baseURL: '', // api 的 base_url
  timeout: 5000 // 请求超时时间
})

// request拦截器
http.interceptors.request.use(
  config => {
    config.headers['Accept'] = 'application/json';
    config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    config.headers['Language'] = util.getLanguage();
    config.headers['Token'] = util.getToken();
    config.headers['AppCode'] =util.getApp();
    config.headers['x-cloud-app'] ='jf-cloud-platform';
    if(config.headers.op){
      Message.loading({
        content: i18n.t(config.headers.op+'ing'),
        duration: 0
      });
    }
    return config
  },
  error => {
    Promise.reject(error);
  }
);

// response 拦截器
http.interceptors.response.use(
  response => {
    Message.destroy();
    //响应成功处理
    const isSuccess = (response.data && response.data.success) && (response.status==200)
    if (!isSuccess) {
      Message.error(i18n.t(util.handerError(response.data,null)));
      return Promise.reject(response.data)
    } else {
      if(response.config && response.config.headers.op){
        Message.success(i18n.t(response.config.headers.op+'Successful'));
      }
      return response.data;
    }
  },
  error => {
    Message.destroy();
    //响应失败处理
    let err;
    if (error.response){
      err=error.response.data;
    } else{
      err=error;
    }
    Message.error(i18n.t(util.handerError(err,null)));
    return Promise.reject(err);
  }
);
export default http
