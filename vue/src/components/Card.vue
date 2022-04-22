<template>
    <div class="card-container">
        <!-- Fix the flipping transformation -->
        <div :id="id" class="flip-card-box" v-on:click="flipCard">
            <div class="flip-card-question" v-if="flipped == false">
          <!-- <img src="{{card.image}}" alt="image" style="width: 0.8em"> -->
                <p class="question">{{card.question}}</p>
            </div>
            <div class="flip-card-answer" v-if="flipped == true">
                <p class="answer">{{card.answer}}</p>
                <div class="buttons">
                    <button class="correct" v-on:click="updateScore(1)">
                        Correct
                    </button>
                    <button class="wrong" v-on:click="updateScore(0)">
                        Wrong
                    </button>
                </div>
            </div>
      </div>
    </div>
</template>
<script>
export default {
    name: "card",

    props: {
        card: Object
    },
    data() {
        return{
            flipped: false,
            id: this.card.cardId
        };
    },
    methods: {
        flipCard() {
            this.flipped = !this.flipped;//invert flip
            let cardEle = document.getElementById(this.id);
            if(cardEle !== null) {//ignore if element doesnt exist, happens when test is completed
                if(!this.flipped) {
                    cardEle.classList.remove("flipped-card");
                }else{
                    cardEle.classList.add("flipped-card");
                }
            }
        },
        updateScore(point) {
            this.$emit('update-score', point);
        }
    },
    computed: {
    }
}
</script>

<style scoped>
.flip-card-box{
    margin: auto;
    text-align: center;
    width: 380px;
    height: 200px;
    padding: 10px;
    border-radius: 5px;
    box-shadow: 0 2px 4px 0;
    transition: transform .2s;
    background-color: whitesmoke;
}

.flipped-card {
    transform: rotateY(180deg);
    background-color: conic-gradient(from .5turn at bottom center, lightblue, white);
}
.buttons {
  display: flex;
  justify-content: space-between;
}
.wrong {
  background-color: rgb(224, 54, 7);
  border: 1px solid rgb(117, 84, 18);
}
.wrong:hover {
  background-color: rgb(147, 49, 16);
  color: rgb(255, 255, 255);
}
/* 
.flip-card-question {
    position: absolute;
    width: 380px;
    height: 200px;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
} */
/* .flip-card-answer {
    background-color: rgb(118, 88, 136);
} */

/* mirroring the mirrored */
.flip-card-answer { 
    transform: rotateY(180deg);
}
.question, .answer {
    font-size: 2em;
}
</style>