import Mock from 'mockjs'

// 配置Ajax请求延时，可用来测试网络延迟大时项目中一些效果
Mock.setup({
  timeout: 1000
})

// 登录相关和获取用户信息
Mock.mock(/\/jf-cloud-platform\/config\/menus/, res=>{
  return {data:[]};
})

export default Mock
