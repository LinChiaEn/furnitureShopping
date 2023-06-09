<template>
  <transition name="fade" tag="div">
    <div class="account-wrap">
      <Account/>
    </div>
  </transition>
</template>

<script>
import Account from '@/components/Account'

export default {
  name: 'login',
  components: {
    Account
  }
}
</script>

