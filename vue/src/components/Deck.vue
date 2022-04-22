<template>
   <div class="deck-container">
    <div class="deck-items">
      <div class="name-genre">
        <h3>
          <i v-if="deck.status == 1" class="fa-solid fa-lock"></i>
          <i v-if="deck.status == 2" class="fa-solid fa-user-ninja"></i>
          <i v-if="deck.status == 3" class="fa-solid fa-user-group"></i>
          {{deck.deckName}}
        </h3>
        <h3>{{this.ownerName}}</h3>
        <h3>{{deck.genre}}</h3>
      </div>
      <div>
        <p>
          <i class="fa-solid fa-brain" :class="brainColor" v-if="hasValue"></i>
          {{deck.deckDesc}}
        </p>
      </div>
    </div>
    <div class="buttons">
      <button class="start" v-on:click="startTest">
        Start
      </button>
      <button class="edit" v-on:click="editDeck" 
      v-if="deck.ownerId == this.$store.state.user.id">
        Edit
      </button>
    </div>
  </div>
</template>

<script>
import DeckService from '@/services/DeckService.js'

export default {
  name: 'deck',
  data() {
    return {
      score: 0,
      brainColor: '',
      ownerName: ''
    }
  },
  props: {
    deck: Object
  },
  computed: {
    hasValue() {
      return this.score > 0;
    }
  },
  methods: {
    startTest() {
      this.$router.push({name: 'session', params: { deckId: this.deck.deckId, userScore: this.score }})
    },
    editDeck() {
      this.$router.push({name: 'edit-deck', 
        params: { deck: this.deck }});
    },
    getScore() {
    }
  },
  created() {
    DeckService
      .getHistoryUserDeck(this.deck.deckId)
      .then(response => {
        this.score = response.data;
        this.brainColor = (this.score == 1)?'red':(this.score == 2)?'yellow':'green';
      });
    DeckService
      .getOwnerUsername(this.deck.ownerId)
      .then(response => {
        this.ownerName = response.data;
      });
  }

}
</script>

<style>
.red {
  color: red;
}
.yellow {
  color: rgb(255, 213, 0);
}
.green {
  color: rgb(12, 184, 12);
}
.name-genre {
  display: flex;
  justify-content: space-between;
}
.buttons {
  display: flex;
  justify-content: space-between;
}
/*
.start, .edit {
  border-radius: 5%;
  transition: 1s;
  outline: none;
}
.start:hover {
  background-color: limegreen;
  color: black;
}
.edit:hover {
  background-color: rgb(205, 148, 50);
  color: black;
} */

.edit {
  background-color: burlywood;
  border: 1px solid rgb(117, 84, 18);
}
.edit:hover {
  background-color: rgb(147, 49, 16);
  color: rgb(255, 255, 255);
}
</style>
