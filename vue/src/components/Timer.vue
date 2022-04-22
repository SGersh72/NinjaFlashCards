<template>
 <div id="timer">
   <h3>Timed Quiz</h3>
   <h4>timer sped up for demo</h4>
   <form class="set-time" v-if="totalTime === 0">
     <label for="min">Minutes
      <input type="number" v-model="setTime" id="min" min="0" max="60">
     </label>
     <button id="timer-button" type="button" @click="setTotalTime">Set Min</button>
	</form>
  <div class="timer-container" v-show="totalTime > 0">
     <div class="timer">{{minutes}}:{{seconds}}</div>
    <div class="timer-button">
      <button @click="startTimer" v-if="!running">Start</button>
      <button @click="pauseTimer" v-if="running">Pause</button>
      <button v-on:click="resetTimer()" v-if="!running">Reset</button>
    </div>
  </div>
 </div>
</template>

<script>
export default {
  name: "timer",
  data() {
    return {
      timer: null,
      running: false,  
      setTime: 0,
      totalTime: 0, 
    }
  },

  methods: {
    showTimer() {

    },
    startTimer() {
      this.$emit('start-timed-quiz');
      if (!this.timer) {
        this.running = true;
         this.timer = setInterval( () => {
						if (this.totalTime > 0) {
              this.countdown();
						} else {
              clearInterval(this.timer);
              this.resetTimer();//sets timer to 0 if they would start again
						}
				}, 200 );//ticks in milliseconds
      }
    },

    pauseTimer() {
      this.running = false;
      clearInterval(this.timer);
      this.timer = null;
    },

    resetTimer() {
      this.setTime = 0;
      this.totalTime = 0;
      this.secondes = 0;
			this.minutes = 0;
      this.timer = null;
      this.running = false;
      document.getElementById("min").value = 0;
      this.$emit('finished-timer');
    },

    padTime(time) {
      return (time < 10 ? "0" : "") + time;
    },
    countdown() {
      this.totalTime--;
    },
    setTotalTime() {
      this.totalTime = this.setTime * 60;
      return this.totalTime;
    }
  },

  computed: {
    minutes() {
      const minutes = Math.floor(this.totalTime / 60);
      return this.padTime(minutes);
    },
    seconds() {
      const seconds = this.totalTime - this.minutes * 60;
      return this.padTime(seconds);
    },
  },
};
</script>

<style scoped>
#timer {
  text-align: center;
  width: 300px;
  height: fit-content;
  padding: 4px 2px;
  border-radius: 5px;
  box-shadow: 0 2px 4px 0;
  transition: transform .2s;
  background-color: rgb(255, 212, 207);
}

#timer h3 {
  font-size: 1.2em;
}

.set-time {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.set-time label {
  font-size: 1.2em;
  margin-bottom: 4px;
  margin-right: 2px;
}

.set-time input[type="number"] {
    font-size:1.2em;
    margin-right: 2px;
} 

#timer-button {
  font-size: 1.1em;
  padding: 4px;
  margin-left: 2px;
}

.timer-container .timer {
  font-size: 1.3em;
}

.timer-button {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content:center;
  text-align: center;
  padding: 4px;
  font-size: 1.1em;
  font-weight:500;
}

.timer-button:hover {
  /* background-color: rgb(7, 82, 7); */
  padding: 4px;
  font-weight: bold;
}
#min {
  width: 100px;
}

</style>