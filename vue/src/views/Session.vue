<template>
  <div id="session">
    <div class="deck-info">
      <h2>{{this.deckName}}</h2> 
      <h3>{{this.deckDesc}}</h3> 
    </div>
    <div class="quiz">
      <div class="active" v-if="currentIndex < cards.length">
        <card class="current-card" v-bind:card="cards[currentIndex]" @update-score="nextQuestion"/>
      </div>
      <div class="last-card" v-else>
        <h1>Quiz is completed!</h1>
      </div>
      <div class="end">
        <!-- TODO emit score to player_deck_history if logged in -->
        <button v-on:click="resetTest">Reset</button>
        <button id="save-score" v-if="isLoggedIn" v-on:click="saveScore">Save Score</button>
        <button v-on:click="backToDecks">Back to Decks</button>
      </div>
      <div class="score-info">
        <span v-if="currentIndex < cards.length">
          <p>Current Card: {{currentIndex + 1}} / {{cards.length}}</p>
        </span>
        <p v-else>Current Card: {{currentIndex}} / {{cards.length}}</p>
        <p>Score: {{currentScore}} / {{cards.length}}</p>
      </div>
      <div class="timer-container">
      <!-- <button v-if="!timerDisabled" v-on:click="toggleTimedQuiz"> timerDisabled -->
        <div class = "end">
          <button v-on:click="toggleTimedQuiz">
            <span v-if="timed">Exit Timed Quiz</span>
            <span v-else>Show Timed Quiz</span>
          </button>
          <button id="shuffle" v-on:click="shuffleCards">
            <span>Shuffle Cards</span>
          </button>
        </div>
        <div class="timed-quiz">
          <timer v-if="timed" @start-timed-quiz="startTimedQuiz" @finished-timer="endTimedQuiz"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CardService from '@/services/CardService.js'
import DeckService from '@/services/DeckService.js'
import Timer from '../components/Timer.vue'
import Card from '../components/Card.vue'

export default {
  components: {
    Timer,
    Card
  },
  props: {
    userScore: Number
  },
  data() {
    return {
      deckId: 0,
      deckName: '',
      deckDesc: '',
      cards: [],
      currentScore: 0,
      currentIndex: 0,
      isLoading: true,
      timed: false
      // timerDisabled: false
    }
  },
  methods: {
    nextQuestion(point) {
      this.currentIndex += 1;
      this.currentScore += point;
    },
    saveScore() {
      if(this.userScore > 0) {
        DeckService.updateUserDeckHistory(
          this.deckId, this.currentScore / this.cards.length);
      } else {
        DeckService.createUserDeckHistory(
          this.deckId, this.currentScore / this.cards.length);
      }
      this.backToDecks();
    },
    resetTest() {
      this.currentIndex = 0;
      this.currentScore = 0;
    },
    /* TODO context dependent, all decks, my decks, filtered */
    backToDecks() {
      //this.$router.push("/");
      this.$router.back();
    },
    toggleTimedQuiz() {
      this.timed = !this.timed;
      if(!this.timed) {
        this.resetTest();
      }
    },
    startTimedQuiz() {
      this.resetTest();
      this.timerDisabled = true;
    },
    endTimedQuiz() {
      this.timed = false;
      // this.timerDisabled = false;
      // alert("timer : " + this.timed + ", timerDisabled : " + this.timerDisabled)
      this.currentIndex = this.cards.length;//sets index to invalid, finishes quiz
    },
    shuffleCards() {
      let m = this.cards.length, t, i;
      // While there remain elements to shuffle…
      while(m > 0) {
        // Pick a remaining element…
        i = Math.floor(Math.random() * m--);
        // And swap it with the current element.
        t = this.cards[m];
        this.cards[m] = this.cards[i];
        this.cards[i] = t;
      }
      this.resetTest();
    }
  },
  created() {
    // alert(this.$route.params.deckId);
    DeckService
      .getDeckByDeckId(this.$route.params.deckId)
      .then(response => {
        if(response.data.status == 1) {//should be in backend
          if(this.$store.state.token == '' || this.$store.state.user.id != response.data.ownerId) {
            alert("Access Denied... Taking the ninja flashcards to heart");//only way to get to this is to copy paste URL
            this.$router.push('/');//pushes to home
          }
        }
        this.deckId = response.data.deckId;
        this.deckName = response.data.deckName;
        this.deckDesc = response.data.deckDesc;
      });
    CardService
      .getAllCard(this.$route.params.deckId)
      .then(response => {
        this.cards = response.data;
        this.isLoading = false;
      });
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.token != '';
    }
  }
}
</script>

<style>
.timer-container {
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.last-card {
  text-align: center;
}
.deck-info {
  text-align: center;
}
.end button {
  margin: 5px;
}
.score-info {
  text-align: center;
}
.end {
  display: flex;
  justify-content: center;
}
#shuffle:focus {
  animation: shuffle 0.5s;
}
@media (max-width:480px) {
  .current-card {
    width: 90vw;
  }
}
@keyframes shuffle {
  10%, 90% {
    transform: translate3d(-1px, 0, 0);
  }
  
  20%, 80% {
    transform: translate3d(2px, 0, 0);
  }

  30%, 50%, 70% {
    transform: translate3d(-4px, 0, 0);
  }

  40%, 60% {
    transform: translate3d(4px, 0, 0);
  }
}
#save-score {  
  background-color: rgb(255, 212, 207);
  border-color: pink;
}

#save-score:hover{
  background: salmon;
}
</style>