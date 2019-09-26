import Vue from 'vue'
import Vuex from 'vuex'

import menu from './module/menu'
import user from './module/user'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //
  },
  mutations: {
    //
  },
  actions: {
    //
  },
  modules: {
    menu,
    user
  }
})
