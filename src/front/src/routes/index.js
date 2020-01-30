import Vue from 'vue'
import VueRouter from "vue-router";

import Home from "../pages/Home";
import Login from "../pages/auth/Login";

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/auth/login',
      component: Login
    },
  ]
});
