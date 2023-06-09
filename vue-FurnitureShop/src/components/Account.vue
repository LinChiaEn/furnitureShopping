<template>
  <div>
    <h1>Log in</h1>
    <form @submit="login">
      <label for="username">username:</label>
      <input type="text" id="username" v-model="username" required>
      <br><br>
      <label for="password">password:</label>
      <input type="password" id="password" v-model="password" required>
      <br>
      <button type="submit">Login</button>
    </form>
    <p v-if="errorMessage">{{ errorMessage }}</p>
  </div>
</template>

<script>
import {mapState, mapGetters, mapActions} from 'vuex'

export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
    };
  },
  methods: {
    ...mapActions({
      loginCheck: 'loginCheck',
      fetchCart: 'fetchCart'
    }),
    login(event) {
      event.preventDefault();
      let user =[this.username,this.password];
      this.loginCheck(user)
      this.$router.push("/loginCheck");
    },
  },
};
</script>

<style lang="css">
* {
  box-sizing: border-box;
}
.wrap {
  margin: 0 auto;
  width: 500px;
}

h1 {
  text-align: center;
  color: #9575c4;
}
.username
.password{
  width: 500px;
  height: 40px;
  padding: 10px;
  border-radius: 10px;
  border: 1px solid #333;
}
label {
  padding-right: 10px;
}

input {
  margin-top: 10px;
}

input:focus {
  outline: none;
  /* 取消focus效果 */
}

#btn {
  text-align: center;
}

#btn input {
  width: 45%;
  margin: 2%;
  margin-top: 10px;
  border: none;
  height: auto;
  padding: 5px;
  font-size: 16px;
  border-radius: 100px;
}
</style>