import Vue from 'vue';
import VueCookie from 'vue-cookie';

Vue.use(VueCookie);

import App from './App.vue';
import {router} from "./routes";

Vue.config.productionTip = false


new Vue({
  render: h => h(App),
  router
}).$mount('#app')
