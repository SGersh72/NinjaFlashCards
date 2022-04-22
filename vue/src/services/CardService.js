import axios from 'axios';

export default{

    getAllCard(id){
        return axios.get(`/deck/${id}/card`);
    },

    addCard(card){
        return axios.post(`/deck/${card.deckId}/card`, card);
    },

    updateCard(card){
        return axios.put(`/deck/${card.deckId}/card/${card.cardId}`, card)
    },

    deleteCard(deckId, cardId){
        return axios.delete(`/deck/${deckId}/card/${cardId}`);
    }

}