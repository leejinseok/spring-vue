<template>
  <div id="app">
    <router-view
      :startSpin="startSpin"
      :stopSpin="stopSpin"
      :setSession="setSession"
      :session="session"
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
      spin: false,
      session: {}
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
    },
    setSession(session) {
      this.session = session;
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
}

/*
=====================================
              CSS RESET
=====================================
*/
html, body, div, span, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
abbr, address, cite, code,
del, dfn, em, img, ins, kbd, q, samp,
small, strong, sub, sup, var,
b, i,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, figcaption, figure,
footer, header, hgroup, menu, nav, section, summary,
time, mark, audio, video { margin:0; padding:0; border:0; outline:0; font-size:100%; vertical-align:baseline;}
body {line-height:1;}
article,aside,details,figcaption,figure,
footer,header,hgroup,menu,nav,section {display:block;}
ul li{list-style:none;}
a {margin: 0; padding: 0; border: 0; vertical-align: baseline; background: transparent; text-decoration: none; color: inherit; display: inline-block;}
img {vertical-align: bottom;}

/*
=====================================
           COMMON SYSTEM
=====================================
*/
.wrapper {max-width: 1200px; margin: 0 auto;}
.clearfix:after {content: ""; display: block; clear: both;}
.table_cell {display: table-cell; vertical-align: middle;}
/*
=====================================
            GRID SYSTEM
=====================================
*/
[class*="col-"] {
  width: 100%;
  float: left;
  padding: 10px;
  box-sizing: border-box;
}
.col-right{float: right;}
.col-fit  {padding: 0;}

.col-1  {width: 8.33%;}
.col-2  {width: 16.66%;}
.col-3  {width: 25%;}
.col-4  {width: 33.33%;}
.col-5  {width: 41.66%;}
.col-6  {width: 50%;}
.col-7  {width: 58.33%;}
.col-8  {width: 66.66%;}
.col-9  {width: 75%;}
.col-10 {width: 83.33%;}
.col-11 {width: 91.66%;}
.col-12 {width: 100%;}

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
  background-color: rgba(44, 62, 80, 0.25);
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
