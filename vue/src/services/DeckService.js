import axios from 'axios';
import store from '../store/index.js'; //import store to access data

export default{
    getOwnerUsername(id) {
        return axios.get(`/user/${id}`);
    },

    createDeck(deck){
        return axios.post(`/deck`, deck);
    },

    getAllDecks(){
        return axios.get('/deck/public');
    },

    getDeckByDeckId(deckId) {
        return axios.get(`/deck/${deckId}`)
    },

    updateDeck(deck){
        return axios.put(`/deck/${deck.deckId}`, deck);
    },

    deleteDeck(id){
        return axios.delete(`/deck/${id}`);
    },
    getMyDecks(id) {
        return axios.get(`/deck/myDecks/${id}`)
    },

    //Returns a num, 1 2 or 3 for score, 0 if no history
    //1 red, 2 yellow, 3 green
    getHistoryUserDeck(deckId) {
        const userId = store.state.user.id;
        return axios.get(`/deck/${deckId}/history/${userId}`);
    },
    //score is %
    updateUserDeckHistory(deckId, score) {//returns response data
        const userId = store.state.user.id;
        //alert("PUT " + userId + " " + deckId + " " + score);
        return axios.put(`/deck/${deckId}/history/${userId}`,
        {}, { params: { score } });
    },
    createUserDeckHistory(deckId, score) {//returns response data
        const userId = store.state.user.id;
        //alert("POST " + userId + " " + deckId + " " + score);
        return axios.post(`/deck/${deckId}/history/${userId}`,
        {}, { params: { score } });
    }


}