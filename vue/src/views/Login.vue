<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1>Please Sign In</h1>
      <div v-if="invalidCredentials">Invalid username and password!</div>
      <div v-if="this.$route.query.registration">Thank you for registering, you can now sign in.</div>
      <label for="username"></label>
      <input
        type="text"
        id="username"
        class="form-control signin-input"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password"></label>
      <input
        type="password"
        id="password"
        class="form-control signin-input"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <div id="btn-login-page">
      <button class="user-button" v-on:click="goToRegister">Create Account</button>
      <button class="user-button login-button" type="submit">Login</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    goToRegister() {
      this.$router.push({name: 'register'});
    },
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
.login-button {
  color: rgb(99, 189, 119);
  background-color: rgb(0, 0, 0);
  border-color: white;

}
#login {    
  width: 500px;
  height: 250px;
  padding: 4px 2px;
  border-radius: 5px;
  box-shadow: 0 2px 4px 0;
  transition: transform .2s;
  background-color: whitesmoke;
  margin:auto;
}

#btn-login-page {
  display: flex;
  justify-content: space-around;
}

</style>
