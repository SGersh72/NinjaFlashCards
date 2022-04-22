import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import EditDeck from '../views/EditDeck.vue'
import MyDecks from '../views/MyDecks.vue'
import Session from '../views/Session.vue'
import PublicDecks from '../views/PublicDecks.vue'
//import TestTimer from '../views/TestTimer.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/myDecks",
      name: "my-decks",
      component: MyDecks,
      meta: {
        requiresAuth: false
      }
    },
    // {
    //   //path: "/deck/:deck-id/:card-id",
    //   path: "/card",
    //   name: "card",
    //   component: Card,
    //   meta: {
    //     requiresAuth: false
    //   }
    // },
    {
      path: "/deck/:deckId",
      name: "session",
      component: Session,
      props: true,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/editDeck",
      name: "edit-deck",
      component: EditDeck,
      props: true,
      afterEnter: [removeQueryParams],
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/publicDecks",
      name: "public-decks",
      component: PublicDecks,
      meta: {
        requiresAuth: false
      }
    }
    // {
    //   path: "/testTimer",
    //   name: "test-timer",
    //   component: TestTimer,
    //   meta: {
    //     requiresAuth: false
    //   }
    // }
  ]
})

function removeQueryParams(to) {
  if (Object.keys(to.query).length)
    return { path: to.path, query: {}, hash: to.hash }
}

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
