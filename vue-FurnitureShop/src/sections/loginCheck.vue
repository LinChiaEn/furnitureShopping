<template>
    <transition name="fade" tag="div">
    <div class="account-wrap">
    {{ user }}
    </div>
    </transition>
  </template>
  
<script>
import Account from '@/components/Account'
import router from '../router';
  
export default {
    name: 'succeed',
    components: {
    router
    },
    updated(){
        let success = false
        console.log("created"+this.user)
        if(this.user.length>0 && this.user!="none") {
            success = true
            this.$router.push("/succeed");
        }
        else {
            this.$router.push("/failed");
            this.$store.state.username = ""
        }
        
    },
    computed: {
        user() {
        return this.$store.state.username
        }
    }
}
</script>