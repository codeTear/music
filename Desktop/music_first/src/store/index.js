import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)
let http = axios.create()
export default new Vuex.Store({
  state: {
    id: "",
    title: "",
    playCount: "",
    favCount: "",
    imgUrl: "",
    tableSongData: [
      {
        songName: "",
        singer:"",
        albumTitle: "",
        timeInterval: "",
      },
    ],
  },
  getters: {

  },
  mutations: {
    setId(state, id) {
      state.id = id
    },
    setTitle(state, title) {
      state.title = title
    },
    setPlayCount(state, playCount) {
      state.playCount = playCount
    },
    setFavCount(state, favCount) {
      state.favCount = favCount
    },
    setImgUrl(state, imgUrl) {
      state.imgUrl = imgUrl
    },
    setTableSongData(state, tableSongData) {
      state.tableSongData = tableSongData
    }
  },
  actions: {
  
  },
  modules: {
  }
})
