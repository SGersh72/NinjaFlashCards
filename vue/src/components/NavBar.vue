<template>
  <div id="nav">
      <div class="reg-items">
        <a href="/">
          <i id="ninja" class="fa-solid fa-user-ninja"></i><span id="Title">Ninja FlashCards </span>
        </a>
        <button class="nav-item public-deck" v-on:click="goToMenuItem('public-decks')">
          Public Decks
        </button>
        <button class="nav-item" v-on:click="goToMenuItem('my-decks')" v-if="$store.state.token != ''">
          My Decks
        </button>
      </div>
      <div class="logoutlogin">
        <button class="nav-item" v-on:click="goToMenuItem('login')" v-if="$store.state.token == ''">
          Login
        </button>
        <button class="nav-item" v-on:click="goToMenuItem('logout')" v-if="$store.state.token != ''">
          Logout
        </button>
      </div>
    </div>
</template>

<script>
export default {
    name: 'nav-bar',
    methods: {
    goToMenuItem(menuItem) {
      this.$router.push({name: menuItem});
    }
  }
}
</script>

<style>
a {
  color: black;
  text-decoration: none;
  transition: .5s;
}
a:hover {
  padding: 8px;
}
#nav {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}
#ninja {
  color: rgb(5, 66, 5);
  font-size: 50px;
}
.nav-item {
  font-size: 30px;
  background-color: rgb(159, 148, 233);
  color: white;
  border: 1px solid rgb(194, 171, 214);
}
.nav-item:hover {
  background: whitesmoke;
  color: rgb(0, 13, 255);
  border: 5px solid rgb(255, 255, 255);
  padding: 8px;
}

.public-deck{
  margin-right: 5px;
}

@media (max-width: 515px) {
  #nav {
    flex-direction: column;
    align-items: center;
  }

  .reg-items {
    display: flex;
    
    flex-wrap: wrap;
    justify-content: center;
  }
  
}

</style>