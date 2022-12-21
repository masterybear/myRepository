import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/css/global.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import qs from 'qs'

const app = createApp(App)
app.config.globalProperties.$qs = qs
app.use(VueAxios, axios)
app.use(ElementPlus)
app.use(router)
app.mount('#app')
