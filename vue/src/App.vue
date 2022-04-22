<template>
  <div id="app">
    <nav-bar class="header-bar"/>
    <router-view />
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'
export default {
  components: {
    NavBar
  }
}
</script>


<style>
html {
  overflow-x: hidden;
}
.header-bar {
  margin-bottom: 5px;
}
* {
  font-family: 'Fredoka', sans-serif;
}
body {
  background-color: rgb(229, 255, 229);
}
#active{ 
  background: whitesmoke;
  color: rgb(0, 13, 255);
  border: 5px solid rgb(255, 255, 255);
  padding: 8px;
}
button {
  padding: 8px;
  cursor: pointer;
  transition: .5s;
  width: fit-content;
  outline: none;
  background-color:rgb(99, 189, 119);
  border-radius: 5px;
  border: 1px solid lightgreen;
}
button:hover {
  background-color: rgb(5, 66, 5);
  color: rgb(255, 255, 255);
  padding: 15px;
  font-weight: bolder;
}

#Title{
  display: inline-block;
  font-size: 34px;
  font-weight: 600;
  margin: 10px;
}


/* LOGIN AND REGISTER STYLING */
.signin-input {
  display: block;
  background: rgb(208, 208, 208);
  margin: auto;
  margin-bottom: 5px;
  width: 380px;
  font-size: 2em;
  
}
.text-center {
  text-align: center;
}
.user-button {
  font-size: 25px;
}
</style>
