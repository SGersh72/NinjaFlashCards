<template>
  <div class="banner-conatiner">
        <div class="banner-text-block">
            <div class="last-title-btn"> 
                <h2 class="fr-last-title">Start creating your own flashcards today!</h2>
                <button v-on:click="goToRegister" id="last-btn-shaped">Register</button>
            </div>
            <h2 class="banner-f-title">Students who use Ninja FlashCards are very happy with the overall experience</h2>
            <div class="banner-first-row">
                <div class="fr-column">
                    <h2 class="fr-title">Engaging Content</h2>
                    <h3 class="fr-content">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </h3>
                    <h2 class="fr-title">Ninja Decks</h2>
                    <h3 class="fr-content">
                        Decks can be set to unlisted to share with your peers, but not public to view!
                        Consider this "Ninja-mode"!
                    </h3>
                </div>
                <div class="fr-image" id="fr-img">
                    <img id="desk-notebook" src="..\assets\pexels-pixabay-459791.jpg" alt="Desk with notebook" width="100%" height="auto">
                </div> 
            </div>

        </div>
    </div>
</template>

<script>

export default {
    name: 'the-banner',
    methods: {
        goToRegister() {
            this.$router.push({name: 'register'});
        }
    }
}
</script>

<style scoped>
#desk-notebook {
    border-radius: 10px;
    box-shadow: 2px 3px 5px;
    margin-top: 10px;
}
.banner-f-title {
    text-align: center;
    text-shadow: 6px 6px 10px rgba(57, 158, 57, 0.76);
    font-size: 2em;

}

.banner-first-row {
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 20px;
    overflow: hidden;
}

.fr-title {
    font-size: 2em;
}
.fr-column {
    width: 50%;
}
.fr-image {
    width: 50%;
}

.last-title-btn {
    display: flex;
    justify-content: center;
    align-items: baseline;
    margin: auto;
}

.fr-last-title{
    text-align: center;
    text-shadow: 6px 6px 10px rgba(57, 158, 57, 0.76);
    font-size: 2em;
}

#last-btn-shaped{
    border-radius: 16px;
    padding: 12px 24px;
    margin-left: 10px;
}

@media only screen and (max-width: 1300px) {
    .last-title-btn {
        flex-direction: column;
        align-items: center;
    }
    .banner-first-row {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .fr-column, .fr-image {
        width: 100%;
        padding: 0;
    }
    .fr-title {
        margin: auto;
        text-align: center;
    }

}
/* 
@media only screen and (max-width: 590px) {

    .fr-content {
        max-width: 60%;
    }

}

@media only screen and (max-width: 500px) {

    .fr-content {
        max-width: 50%;
    }

}

@media only screen and (max-width: 400px) {

    .fr-content {
        max-width: 40%;
    }

} */

</style>
