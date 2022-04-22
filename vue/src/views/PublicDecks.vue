<template>
  <div class="public-decks">
    <h1 class="public-deck-title">Public Decks</h1>
    <div class="loading" v-if="isLoading">
      <ninja-loader/>
    </div>
    <deck-list :decks="this.decks" v-else/>
  </div>
</template>

<script>
import DeckList from '../components/DeckList.vue';
import DeckService from '@/services/DeckService.js'
import NinjaLoader from '../components/NinjaLoader.vue'

export default {
  name: "public-decks",
  data() {
    return {
      decks: [],
      isLoading: true
    }
  },
  components: {
    NinjaLoader,
    DeckList
  }, 
  created() {
    DeckService.getAllDecks().then(response => {//gets all public decks
      this.decks = response.data;
      setTimeout(() => {
        this.isLoading = false;
      }, 2000);
    });
  }
};
</script>

<style>
.public-deck-title {
  text-align: center;
}
</style>
