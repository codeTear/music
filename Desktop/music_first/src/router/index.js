import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import index from '../components/index.vue'
import register from '../components/register.vue'
import singerlist from '../components/singerlist.vue'
import singer from '../components/singerlistcontent.vue'
import songlist from '../components/songlist.vue'
import user from '../components/user.vue'
import userdetail from '../components/userdetail.vue'
import play from '../components/play.vue'
Vue.use(VueRouter)

const routes = [
  //  主界面的路由
  {path: '/', redirect: '/index'},
  { path: '/login', component: Login },
  { path: '/index', component: index },
  { path: '/register', component: register },
  { path: '/singerlist', component: singerlist },
  // { path: '/singer', component: singer, name: 'singer' }
  { path: '/singer/:singerName', component: singer, name: 'singer' },
  {path: '/songlist', component:songlist, name:'songlist'},
  {path: '/user', component:user, name:'user'},
  {path: '/userdetail', component:userdetail, name:'userdetail'},
   {path: '/play', component:play, name:'play'}
]

const router = new VueRouter({
  routes
})
// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  /*
  to 将要访问的路径
  from 从哪个路径跳转过来
  next 是否放行
  */
  if(to.path==='/login') return next()
  if(to.path==='/index') return next()
  if(to.path==='/register') return next()
  if(to.path==='/api/users/generate') return next()
  const token = window.sessionStorage.getItem("token")
  if (!token) return next('/login')
  next()
})

export default router
