<template>
    <transition name="fade" tag="div">
      <div class="account-wrap">
        <h1>Login suceesfully</h1>
        <router-link to="/cart" exact>
            <button>back to cart</button>
        </router-link>
      </div>
    </transition>
  </template>
  
  <script>
  import Account from '@/components/Account'
  
  export default {
    name: 'succeed',
    components: {
    }
  }
  </script>