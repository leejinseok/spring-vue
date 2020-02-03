import Vue from 'vue';
import VueRouter from "vue-router";

import Welcome from "../pages/Welcome";
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import Articles from "../pages/articles/List";
import WriteArticle from '../pages/articles/Write';

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Welcome
    },
    {
      path: '/auth/login',
      component: Login
    },
    {
      path: '/auth/register',
      component: Register
    },
    {
      path: '/articles',
      component: Articles
    },
    {
      path: '/articles/write',
      component: WriteArticle
    }
  ]
});
