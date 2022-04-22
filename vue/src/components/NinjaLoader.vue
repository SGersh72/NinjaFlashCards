<template>
    <div>
        <div id="ninja-box">
            <img src="../assets/ninja transparent.png" class="image-loader"/>
        </div>
        <h1 class="this-loading">. . . l o a d i n g . . .</h1>
    </div>
</template>

<script>
export default {
    name: 'ninja-loader'
}
</script>

<style>
.this-loading {
    text-align: center;
}
#ninja-box {
    margin-top: 50px;
    display: flex;
    justify-content: center;
}
.image-loader {
    max-width: 25vw;
    max-height: 25vh;
  animation-name: spin;
  animation-duration: 679ms;
  animation-iteration-count: infinite;
  animation-timing-function: linear; 
}

@keyframes spin {
    from {
        transform:rotate(0deg);
    }
    to {
        transform:rotate(360deg);
    }
}

</style>