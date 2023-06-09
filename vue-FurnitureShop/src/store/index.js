import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions'
import getters from './getters'
import mutations from './mutations'

Vue.use(Vuex)

export default new Vuex.Store({
  state : { // = data
    products:[],
    cart:[],
    cartItems:0,
    checkoutStatus: null,
    highprice: 100000,
    sale: false,
    username: ""
  },
  getters,
  actions,
  mutations
})
