<template>
    <transition name="fade" tag="div">
      <div class="account-wrap">
        <h1>Incorrect username or password</h1>
        <router-link to="/login" exact>
            <button>Login again</button>
        </router-link>
      </div>
    </transition>
  </template>
  
  <script>
  import Account from '@/components/Account'
  
  export default {
    name: 'failed',
    components: {
    }
  }
  </script>