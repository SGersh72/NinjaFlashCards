<template>
  <form id="deckForm" :class="{changed: isChanged}">
        <!-- <h4>Create a new study set</h4> -->
        <!-- <p>{{this.deck}}</p>
        <p>{{this.newDeck}}</p> -->
        <i v-if="newDeck.status == 1" class="fa-solid fa-lock"></i>
        <i v-if="newDeck.status == 2" class="fa-solid fa-user-ninja"></i>
        <i v-if="newDeck.status == 3" class="fa-solid fa-user-group"></i>
        <p v-if="isChanged">Please save your changes.</p>
        <div>
            <!-- Title: -->
            <input type="text" class="form-control" v-model="newDeck.deckName" />
        </div>
        <div>
            <!-- Genre: -->
            <select class="form-control" v-model="newDeck.genre">
                <option 
                v-for="genre in genresWithoutClear"
                v-bind:key="genre.id">
                    {{genre}}
                </option>
            </select>
        </div>
        <div>
            <!-- Description: -->
            <textarea maxlength="38" v-model="newDeck.deckDesc"/>
        </div>
        <div id="status">
            <!-- <span>Choose a status:</span> -->
            <select class="form-control" v-model="newDeck.status">
                <option :value="1">Private<i v-if="deck.status == 1" class="fa-solid fa-lock"></i></option>
                <option :value="2">Unlisted<i v-if="deck.status == 2" class="fa-solid fa-user-ninja"></i></option>
                <option :value="3">Public<i v-if="deck.status == 3" class="fa-solid fa-user-group"></i></option>
            </select>
        </div>

        <button class="btn-submit" v-on:click.prevent="saveNewDeck()">Save Deck Details</button>
        <button id="delete-deck-btn" class="btn-submit" v-on:click.prevent="deleteDeck()">Delete Deck</button>
  </form>
</template>

<script>
import deckService from '@/services/DeckService'

export default {
    name: 'deck-form',
    components: {

    },
    data() {
        return{
            newDeck: {
                deckId: this.deck.deckId,
                ownerId: this.$store.state.user.id,
                genre: this.deck.genre,
                deckName: this.deck.deckName,
                deckDesc: this.deck.deckDesc,
                status: this.deck.status
            },
            currentDeck: Object,
            errorMsg: ''
        }
    },
    props: {
        deck: Object
    },
    created() {
        this.currentDeck = JSON.parse(JSON.stringify(this.newDeck));
    },
    methods: {
        saveNewDeck(){
            // window.location.href = '/';
            deckService
            .updateDeck(this.newDeck)
            .then(() => {
                this.currentDeck = JSON.parse(JSON.stringify(this.newDeck));//Resets for background color
            }).catch(error => {
                if(error.response){
                    this.errorMsg = "Error adding new deck. Response: "+error.response.statusText;
                }
            })
        },
        deleteDeck(){
            deckService
                .deleteDeck(this.deck.deckId)
                .then(() => {
                    this.$router.go(-1);
                });
        }
    },
    computed: {
        genresWithoutClear() {
            return this.$store.state.genres.filter(genre => {
                return genre !== 'Clear'; //horrible
            });
        },
        isChanged() {
            return JSON.stringify(this.newDeck) != JSON.stringify(this.currentDeck);
        }
    }
}
</script>

<style scoped>
#deckForm i {
    margin-top: 5px;
}
#deckForm {  
    width: auto;
    height: auto;
    background-color: rgb(255, 212, 207);
    box-shadow: 1px 1px 5px #333;
    border-radius: 5px;
    text-align: center;
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    display: inline-block;
    align-items: center; 
    margin: 5px;
    /* width: 380px; */
}
#deckForm > div {
    margin: 10px 10px;
}
#deckForm > button {
    margin-bottom: 10px;
}
.changed {
    background-color: rgb(205, 179, 176) !important;
}
/* .btn-submit {
    margin-top: 5px;
} */

.form-control{
    margin-bottom: 5px;
}

#delete-deck-btn {
    background: rgb(238, 63, 63);
    border-color: red;
}

input {
    width: 220px;
    background: whitesmoke;
    cursor: pointer;
}
input:focus {
    background: rgb(245, 187, 111);
}
select {
    width: 227px;
    background: whitesmoke;
    cursor: pointer;
}
select:focus {
    background: rgb(245, 187, 111);
}
textarea {
    width: 220px;
    background: whitesmoke;
    cursor: pointer;
}
textarea:focus {
    background: rgb(245, 187, 111);
}
</style>