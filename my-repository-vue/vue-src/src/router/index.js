import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import SignView from '@/views/SignView'

const routes = [
  { path: '/home', component: HomeView },
  { path: '/EasterEgg', component: EasterView },
  {
    path: '/sign',
    component: SignView,
    beforeEnter: (to, from, next) => {
      const token = localStorage.getItem('token')
      if (token && to.path === '/sign') {
        next('/home')
      } else {
        next()
      }
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if ((token && to.path !== '/') || to.path === '/sign') {
    next()
  } else {
    next('/sign')
  }
})
export default router
