<template>
<div class="container">
  <div class="deck_part">
    <deck-form v-bind:deck="deck"/>
  </div>
  <div class="card_list">
    <card-list v-bind:deck="deck"></card-list>
  </div>
</div>
</template>

<script>
// import CardForm from '../components/CardForm.vue'
import DeckForm from '../components/DeckForm.vue';
import CardList from '@/components/CardList';

export default {
    name:"edit-deck",
    components: { 
        // CardForm, 
        DeckForm,
        CardList
    },
    props: {
        deck: Object
    }
}
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

</style>