<template>
    <div class="hero-container">
        <div class="text-block">
            <h1 class="hero-header">Learn it. Ninja FlashCards.</h1>
            <p class="hero-body">Pick a subject, create cards and decks, study to get better grades.</p>
            <button v-on:click="goToRegister" id="get-started">Get Started</button>
        </div>
        <img id="notebook" src="../assets/pexels-lumn-351961.jpg" alt="Desk with notebook" style="width: 100%;">
    </div>
</template>

<script>
export default {
    name: 'the-hero',
    methods: {
        goToRegister() {
            this.$router.push({name: 'register'});
        }
    }

}
</script>

<style>

.hero-container {
    position: relative;
}

#notebook {
    border-radius: 10px;
    box-shadow: 2px 3px 5px;
    margin-top: 10px;
}

.text-block{ 
  position: absolute;
  top: 25%;
  left: 4%;
  padding-left: 20px;
  padding-right: 20px;
  color: black;
  text-shadow: black;
  font-size: 16px;
  width: 380px; 
}

.hero-header {
    /* text-shadow: 8px 8px 12px black; */
    text-shadow: 6px 6px 10px rgba(57, 158, 57, 0.76);
    /* text-shadow: 6px 6px 10px rgb(255, 174, 0); */
    /* color: white; */
    font-size: 3em;
    font-weight: 600;
}

.hero-body {
    /* text-shadow: 8px 8px 12px black; */
    text-shadow: 6px 6px 10px rgba(57, 158, 57, 0.76);
    /* text-shadow: 6px 6px 10px rgb(255, 174, 0); */
    /* color: white; */
    font-size: 1.5em;
    font-weight: 600;

}

#get-started {
    padding: 12px 24px;
    border-radius: 5px;
    box-shadow: 2px 3px 5px;
    background-color: rgba(57, 158, 57, 0.76);
    cursor: pointer;
    border-radius: 16px;
}

#get-started:hover {
    color: white;    
    transition: 1s;
}

@media (max-width:1300px) {
    .hero-body {
        display: none;
    }
}
@media (max-width: 1180px) {
    .text-block {
        text-align: center;
        padding: 0;
        position: relative;
        width: 100%;
        display: flex;
        justify-content: center;
        flex-direction: column;
    }
    #get-started {
        display: none;
    }
}

/* @media only screen and (max-width: 768px) {
    .text-block{
        width: 200px;
    }

    .hero-header {
        font-size: 2em;
    }

    .hero-body {
        font-size: 1em;
    }

    #get-started {
        padding: 7px 19px;
    }
    
}

@media only screen and (max-width: 580px) {
    .text-block{
        width: 190px;
    }

    .hero-header {
        font-size: 1.8em;
    }

    .hero-body {
        font-size: .8em;
    }

    #get-started {
        padding: 5px 17px;
    }
}

@media only screen and (max-width: 480px) {
    .text-block{
        width: 170px;
    }

    .hero-header {
        font-size: 1.5em;
    }

    .hero-body {
        font-size: .6em;
    }

    #get-started {
        padding: 4px 16px;
    }
}

@media only screen and (max-width: 390px) {
    .text-block{
        width: 150px;
    }

    .hero-header {
        font-size: 1.3em;
    }

    .hero-body {
        font-size: .4em;
    }

    #get-started {
        padding: 3px 14px;
    }
}

@media only screen and (max-width: 350px) {
    .text-block{
        width: 130px;
    }

    .hero-header {
        font-size: 1.1em;
    }

    .hero-body {
        font-size: .3em;
    }

    #get-started {
        padding: 2px 12px;
    }
}
 */


</style>
