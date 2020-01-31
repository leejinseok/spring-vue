import Vue from 'vue';
import VueCookie from 'vue-cookie';
import App from './App.vue';
import {router} from "./routes";

Vue.config.productionTip = false;

Vue.use(VueCookie);

new Vue({
  render: h => h(App),
  router
}).$mount('#app');
