<template>
  <div id="app">
    <router-view
      :startSpin="startSpin"
      :stopSpin="stopSpin"
    />

    <div id="spinner-container" v-if="spin">
      <div class="spinner-container__inner">
        <div class="spinner-container__inner__inner">
          <div class="lds-ripple" ><div></div><div></div></div>
        </div>

      </div>
    </div>

  </div>
</template>

<script>

export default {
  name: 'app',
  components: {
  },
  data() {
    return {
      spin: false
    }
  },
  methods: {
    startSpin() {
      this.spin = true;
    },
    stopSpin(smooth = false) {
      if (smooth) {
        setTimeout(() => {
          this.spin = false;
        }, 300);
      } else {
        this.spin = false;
      }
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#spinner-container {
  display: block;
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}

.spinner-container__inner {
  position: relative;
  width: 100%;
  height: 100%;
  /*background-color: #2c3e50;*/
  /*background-color: rgba(44, 62, 80, 0.25);*/
}

.spinner-container__inner__inner {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.lds-ripple {
  display: inline-block;
  position: relative;
  width: 80px;
  height: 80px;
}
.lds-ripple div {
  position: absolute;
  border: 4px solid slateblue;
  opacity: 1;
  border-radius: 50%;
  animation: lds-ripple 1s cubic-bezier(0, 0.2, 0.8, 1) infinite;
}
.lds-ripple div:nth-child(2) {
  animation-delay: -0.5s;
}
@keyframes lds-ripple {
  0% {
    top: 36px;
    left: 36px;
    width: 0;
    height: 0;
    opacity: 1;
  }
  100% {
    top: 0px;
    left: 0px;
    width: 72px;
    height: 72px;
    opacity: 0;
  }
}
</style>
