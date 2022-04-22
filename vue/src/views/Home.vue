<template>
  <div class="home">
    <TheHero></TheHero>
    <TheBanner></TheBanner>
  </div>
</template>

<script>
import TheHero from '@/components/TheHero.vue'
import TheBanner from '@/components/TheBanner.vue'

export default {
  name: "home",
  data() {
    return {
      decks: []
    }
  },
  components: {
    TheHero,
    TheBanner
  }
};
</script>
