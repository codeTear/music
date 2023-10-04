import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import VueCoverflow from '../node_modules/vue-coverflow/lib'
import './assets/css/global.css'
import axios from 'axios'
import VuePlyr from '../node_modules/vue-plyr'
import 'vue-plyr/dist/vue-plyr.css'
import '@icon-park/vue/styles/index.css';
import './assets/fonts/font/iconfont.css'

Vue.use(VuePlyr, {
  plyr: {}
})
// 挂在http请求
// 跨域请求的解决
axios.defaults.withCredentials=true
Vue.prototype.$http=axios
Vue.use(VueCoverflow)
Vue.config.productionTip = false
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
