<template>
  <div id="panel">
    <div class="menu-container">
      <button class="menu-btn"
       v-for="genre in this.$store.state.genres" v-bind:key="genre.id"
       v-on:click="changeGenre(genre)">
        {{genre}}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "side-menu",
  methods: {
    changeGenre(genre) {
      //document.getElementById('highlighted').removeAttribute('highlighted');
      this.$emit('change-genre', genre);
      //this.event.target.setAttribute('id', 'highlighted');
    }
  }  
}
</script>

<style scoped>
.menu-container {
  display: flex;
  flex-direction: flex;
  justify-content: center;
  flex-wrap: wrap;
  width: 100%;
  margin: 5px;
}
.menu-btn {
  width: 180px;
  font-size: 1.5em;
  border-color: rgb(194, 244, 203);
}
.menu-btn:last-child {
  background-color: rgb(255, 212, 207);
  border-color: pink;
}
.menu-btn:last-child:hover {
  background: #ffffff;
  color: red;
}
.menu-btn:focus {  
  background-color: burlywood;
  border: 1px solid rgb(117, 84, 18);
}
.menu-btn:focus:hover {
  background-color: rgb(147, 49, 16);
  color: rgb(255, 255, 255);
}
/* #highlighted {
  background: white;
} */
</style>