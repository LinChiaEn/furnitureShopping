import Vue from 'vue'
import Router from 'vue-router'
import index from '@/sections/index'
import cart from '@/sections/cart'
import bathroom from '@/sections/bathroom'
import bedding from '@/sections/bedding'
import chair from '@/sections/chair'
import cookware from '@/sections/cookware'
import decoration from '@/sections/decoration'
import lighting from '@/sections/lighting'
import outdoor from '@/sections/outdoor'
import sofa from '@/sections/sofa'
import storage from '@/sections/storage'
import table from '@/sections/table'
import tableware from '@/sections/tableware'
import SideMainLayout from '@/layout/SideMainLayout'
import login from '@/sections/login'
import succeed from '@/sections/succeed'
import failed from '@/sections/failed'
import loginCheck from '@/sections/loginCheck'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: SideMainLayout,
      children: [
        {
          path: '/',
          component: index
        },
        {
          path: '/bathroom',
          name: 'bathroom',
          component: bathroom
        },
        {
          path: '/bedding',
          name: 'bedding',
          component: bedding
        },
        {
          path: '/chair',
          name: 'chair',
          component: chair
        },
        {
          path: '/cookware',
          name: 'cookware',
          component: cookware
        },
        {
          path: '/decoration',
          name: 'decoration',
          component: decoration
        },
        {
          path: '/lighting',
          name: 'lighting',
          component: lighting
        },
        {
          path: '/outdoor',
          name: 'outdoor',
          component: outdoor
        },
        {
          path: '/sofa',
          name: 'sofa',
          component: sofa
        },
        {
          path: '/storage',
          name: 'storage',
          component: storage
        },
        {
          path: '/table',
          name: 'table',
          component: table
        },
        {
          path: '/tableware',
          name: 'tableware',
          component: tableware
        }
      ]
    },
    {
      path: '/cart',
      name: 'cart',
      component: cart
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/succeed',
      name: 'succeed',
      component: succeed
    },
    {
      path: '/failed',
      name: 'failed',
      component: failed
    },
    {
      path: '/loginCheck',
      name: 'loginCheck',
      component: loginCheck
    }
  ]
})