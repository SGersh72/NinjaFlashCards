<template>
   <div class="deck-list">
    <div class="nav-deck">
      <side-menu @change-genre="filterGenre" />
      <div class="deck-itr-container">
        <div class="deck-iteration" v-for="deck in genreFilter" v-bind:key="deck.deckId">
          <deck class="deck" v-bind:deck="deck"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Deck from '../components/Deck.vue';
import SideMenu from './SideMenu.vue';

export default {
  name: 'deck-list',
  data() {
    return {
      genreSearch: ""
    }
  },
  components: {
    Deck,
    SideMenu
  },
  props: {
    decks: Array
  },
  computed: {
    isVisible(deckStatus) {
      return this.status == deckStatus;
    },
    genreFilter() {
      return this.decks.filter((deck) => {
        return deck.genre.match(this.genreSearch);
      });
    }
  },
  methods: {
    filterGenre(genre){
      //if genre is Clear, set this.genreSearch to "" to reset
      this.genreSearch = (genre === "Clear")?"":genre;
    }
  }
}

</script>

<style>

.deck-iteration {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.nav-deck {
  width: 100%;
}

.deck-itr-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.deck {
  width: 380px;
  padding: 2%;
  margin: 1%;
  height: auto;
  border-radius: 5px;
  box-shadow: 0 2px 4px 0 black;
  border: 1px solid lightgray;
  background-color: whitesmoke;
}

@media (max-width:480px) {
  .deck {
    width: 100vw;
  }
}

</style>