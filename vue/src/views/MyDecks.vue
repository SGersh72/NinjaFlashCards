<template>
  <div class="my-decks">
    <div class="my-decks-header">
      <h1>{{this.$store.state.user.username}}'s Decks</h1>
      <button class="create-btn" v-on:click="makeNewDeck">Create Deck</button>
    </div>
    <div class="loading" v-if="isLoading">
      <ninja-loader/>
    </div>
    <deck-list :decks="this.decks" v-else/>
  </div>
</template>

<script>
import DeckList from '../components/DeckList.vue';
import DeckService from '@/services/DeckService.js'
import NinjaLoader from '../components/NinjaLoader.vue';
export default {
  name: "my-decks",
  data() {
    return {
      decks: [],
      isLoading: true,
      newDeckForm: false
    }
  },
  components: {
    DeckList,
    NinjaLoader,
  }, 
  created() {
    DeckService
    .getMyDecks(this.$store.state.user.id)
    .then(response => {
      setTimeout(() => {
        this.isLoading = false;
      }, 1000);
      this.decks = response.data;
    });
  },
  methods: {
    makeNewDeck() {
      this.$store.commit('SET_BLANK_DECK_USER_ID', this.$store.state.user.id);
      //makes blank deck model ownerId with current user's id
      DeckService
        .createDeck(this.$store.state.blankDeck)
        .then(response => {
          this.$router.push({name: 'edit-deck', 
            params: { deck: response.data }});
        });
    }
  }
};
</script>
<style>
.my-decks-header {
  display: flex;
  justify-content: center;
}

.my-decks-header h1{
  margin-right: 5px;
}

.create-btn {
  margin-left: 5px;
  font-size: 20px;
  background-color: rgb(255, 212, 207);
  border-color: pink;
  
}

.create-btn:hover{
  background: salmon;
}
</style>