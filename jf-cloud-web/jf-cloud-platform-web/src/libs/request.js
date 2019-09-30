import axios from 'axios';
import util from './util';

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
    config.headers['x-cloud-app'] ='jf-cloud-platform';
    //TODO TOken、应用
    return config
  },
  error => {
    Promise.reject(error);
  }
);

// response 拦截器
http.interceptors.response.use(
  response => {
    //响应成功处理
    const isSuccess = (response.data && response.data.success) && (response.status==200)
    if (!isSuccess) {
      return Promise.reject(response.data)
    } else {
      return response.data;
    }
  },
  error => {
    //响应失败处理
    if (error.response){
      return Promise.reject(error.response.data);
    } else{
      return Promise.reject(error);
    }
  }
);
export default http
