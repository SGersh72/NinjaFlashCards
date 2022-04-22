<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1>Create Account</h1>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control signin-input"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <div v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <button class="user-button" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#register {    
  width: 500px;
  height: auto;
  padding: 4px 2px;
  border-radius: 5px;
  box-shadow: 0 2px 4px 0;
  transition: transform .2s;
  background-color: whitesmoke;
  margin:auto;
}
button {
  margin-bottom: 10px;
}
</style>
