<template>
  <div class="card-list">
    <!-- Add Card Form -->
    <button id="new-card-btn" v-on:click="showNewCard = !showNewCard">Add New Card</button>
    <div id="newCard">
      <!-- <h4 id="card-list-text">CARD LIST</h4> -->
      <div v-show="showNewCard" class="cardForm">
        <div class="inputData" id="input-question">
          <!-- Question:  -->
          <input placeholder="Question" id="question" type="text" v-model="newCard.question" /> 
        </div>
        <div class="inputData">
          <!-- Answer:  -->
          <input placeholder="Answer" id="answer" type="text" v-model="newCard.answer"/>
        </div>
        <!-- <div class="inputData">
          Image URL:
          <input placeholder="Image-Url" id="imageUrl" type="text" v-model="newCard.image"/>
        </div> -->
        <div>
          <button type="submit" v-on:click="saveCard()">Save</button>
        </div>
      </div>
    </div>
  <!-- Card Loop -->
    <div id="currentCardList">
      <div v-for="card in this.$store.state.cards" v-bind:key="card.cardId" class="cardForm"> 
        <div class="inputData" id="input-question">
          <!-- Question:  -->
          <input id="question" type="text" v-model="card.question" /> 
        </div>
        <div class="inputData">
          <!-- Answer:  -->
          <input id="answer" type="text" v-model="card.answer"/>
        </div>
        <!-- <div class="inputData">
            Image URL:
            <input id="imageUrl" type="text" v-model="card.image"/>
        </div> -->
        <div>
          <button class="btnUpdateCard" v-on:click="UpdateCard(card)">Save</button>
          <button id="delete-deck-btn" v-on:click="deleteCard(card.cardId)">Delete</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import Card from '@/components/Card.vue' 
import cardService from '@/services/CardService.js'; 

export default {
    name: 'card-list',
    data() {
      return {
        showNewCard: false,
        newCard: {
          // cardId: 0, // serial
          question: "",
          answer: "",
          image: "",
          deckId: this.deck.deckId
        },
      }
    },
    props: {
        deck: Object
    },

    methods: {
      UpdateCard(card){
        cardService.updateCard(card).then(()=>{
          this.getCards(); 
        })
      },
      deleteCard(id){
        cardService.deleteCard(this.deck.deckId, id).then(()=>{
          this.getCards(); 
        });
      },
      saveCard() {
        cardService
        .addCard(this.newCard)
        .then((response) => {
            // alert(JSON.stringify(this.newCard));
            // alert(JSON.stringify(response.data));
            this.$store.state.card = response.data;
            this.getCards(); 
            this.newCard = {
              cardId: 0,
              question: "",
              answer: "",
              image: "",
              deckId: this.deck.deckId
            }
            
        });
      },
      getCards(){
            cardService.getAllCard(this.deck.deckId).then(response=>{
              this.$store.commit("SET_CARDS", response.data);
          }).catch(error=>{ 
            console.log(error.response.status);
          });
      }
     
    },
    created() {
      this.getCards();
    }
}
</script>

<style>
#newCard {
  display: flex;
  justify-content: center;
}
#newCard .cardForm {
  background-color: rgb(255, 212, 207);
}
#currentCardList {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.cardForm {
  width: 350px;
  height: 200px;
  background-color: whitesmoke;
  box-shadow: 1px 1px 5px #333;
  border-radius: 5px;
  text-align: center;
  margin-left: 10px;
  margin-right: 10px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#card-list-text{
  text-shadow: 6px 6px 10px rgba(57, 158, 57, 0.76);

}

#new-card-btn {
  margin: 15px auto 10px auto;
  display: block;
}

.card-list {
  margin-bottom: 20px;
  margin: auto;
  padding: auto;
} 


.inputData {
  margin-bottom: 5px;
  color: black;
}

input {
   width: 220px;
   background: white;
   border: 1px solid darkgray;
}
input:focus {
    background: rgb(245, 187, 111);

}
#delete-deck-btn {
    background: rgb(238, 63, 63);
}
</style>