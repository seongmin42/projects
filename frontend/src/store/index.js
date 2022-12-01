import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/routes/router.js'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const HOST_IP = `http://localhost:9999`
const REST_API = `${HOST_IP}/main-api`
const VIDEO_API = `${HOST_IP}/video-api`
const REVIEW_API = `${HOST_IP}/review-api`
const USER_API = `${HOST_IP}/user-api`
const FOllOW_API = `${HOST_IP}/follow-api`

export default new Vuex.Store({
  plugins: [createPersistedState({
    //storage: window.sessionStorage,
    //paths:["review"],
  })],
  state: {
    videos: [],
    video: {},
    reviews: [],
    review: {},
    followings: [],
    loginUser: null,
  },
  getters: {
  },
  mutations: {
    GET_VIDEOS(state, payload) {
      state.videos = payload
    },
    GET_VIDEO(state, payload) {
      state.video = payload
    },

    GET_REVIEW(state, payload) {
      state.review = payload
    },
    GET_REVIEWS_BY_VIDEO_INDEX(state, payload) {
      state.reviews = payload
    },

    USER_LOGIN(state, payload) {
      state.loginUser = payload
    },
    USER_LOGOUT(state) {
      state.loginUser = null;
    },
    
    SEARCH_YOUTUBE(state, videos) {
      state.videos = videos
    },
    CLICK_VIDEO(state, video) {
      state.video = video
    },
    GET_FOLLOW(state, payload) {
      state.followings = payload
    },
  },
  actions: {
    // Video
    getVideos({ commit }) {
      const API_URL = `${VIDEO_API}/list`
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        commit('GET_VIDEOS', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    // 좀더 깔끔하게
    getVideo({ commit }, id) {
      const API_URL = `${VIDEO_API}/detail/${id}`
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        commit('GET_VIDEO', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    getVideosByPart({ commit }, part) {
      const API_URL = `${REST_API}/${part}`
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        commit('GET_VIDEOS', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    getVideosByViewCnt({ commit }) {
      const API_URL = `${REST_API}/view-cnt`
      console.log(API_URL);
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        commit('GET_VIDEOS', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    createVideo({ commit }, video) {
      const API_URL = `${VIDEO_API}/video`
      commit
      axios({
        url: API_URL,
        method: 'POST',
        params: video,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        router.push("/video")
      }).catch((err) => {
        console.log(err)
      })
    },
    updateVideo({ commit }, video) {
      const API_URL = `${VIDEO_API}/video`
      axios({
        url: API_URL,
        method: "PUT",
        params: video,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        commit('GET_VIDEO', video)
        router.push({ name: 'videoDetail', params: { id: video.id } })
        window.history.go(-1)
      })
    },
    deleteVideo(id) {
      const API_URL = `${VIDEO_API}/video/${id}`
      axios({
        url: API_URL,
        method: 'DELETE',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        router.push({ name: 'videoList' })
      }).catch((err) => {
        console.log(err)
      })
    },
    likeVideo({ commit }, videoIndex) {
      const API_URL = `${VIDEO_API}/video/like/${videoIndex}`
      axios({
        url: API_URL,
        method: 'PUT',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
        commit('GET_VIDEO', res.data)
      }).catch(() => {
        window.alert("로그인이 필요합니다.")
        router.push({name:'login'})
      })
    },
    dislikeVideo({ commit }, videoIndex) {
      const API_URL = `${VIDEO_API}/video/dislike/${videoIndex}`
      axios({
        url: API_URL,
        method: 'PUT',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
        commit('GET_VIDEO', res.data)
        console.log(res)
      }).catch((err) => {
        window.alert("로그인이 필요합니다.")
        console.log(err)
      })
    },
    searchVideo({ commit }, payload) {
      const API_URL = `${VIDEO_API}/video/search/${payload.query}`
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        commit('GET_VIDEOS', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    // Review
    // 좀더 깔끔하게
    getReview({ commit }, reviewIndex) {
      const API_URL = `${REVIEW_API}/detail/${reviewIndex}`
      console.log(API_URL)
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        commit('GET_REVIEW', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    getReviewsByVideoIndex({ commit }, videoIndex) {
      const API_URL = `${REVIEW_API}/list/video?videoIndex=${videoIndex}`
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        commit('GET_REVIEWS_BY_VIDEO_INDEX', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    createReview({ commit }, review) {
      const API_URL = `${REVIEW_API}/review`
      console.log(review)
      commit
      axios({
        url: API_URL,
        method: 'POST',
        params: review,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        window.history.go();
      }).catch((err) => {
        console.log(err)
      })
    },
    updateReview({ commit }, review) {
      const API_URL = `${REVIEW_API}/review`
      commit
      axios({
        url: API_URL,
        method: 'PUT',
        params: review,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        window.history.go(-1);
      }).catch((err) => {
        console.log(err)
      })
    },
    deleteReview({ commit }, params) {
      const API_URL = `${REVIEW_API}/review/${params.reviewIndex}`
      axios({
        url: API_URL,
        method: 'DELETE',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        commit
        router.push(`/video/` + params.videoIndex)
        window.history.go(-1)
      }).catch((err) => {
        console.log(err)
      })
    },
    likeReview({ commit }, reviewIndex) {
      const API_URL = `${REVIEW_API}/review/like/${reviewIndex}`
      axios({
        url: API_URL,
        method: "PUT",
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
        commit("GET_REVIEW", res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    dislikeReview({ commit }, reviewIndex) {
      const API_URL = `${REVIEW_API}/review/dislike/${reviewIndex}`
      axios({
        url: API_URL,
        method: "PUT",
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
        commit("GET_REVIEW", res.data)
      }).catch((err) => {
        console.log(err)
      })
    },


    // User
    createUser({ commit }, user) {
      const API_URL = `${USER_API}/signin`
      commit
      axios({
        url: API_URL,
        method: 'POST',
        params: user,
      }).then(() => {
        router.push(`/user/login`);
      }).catch((err) => {
        console.log(err)
      })
    },
    login({ commit }, user) {
      const API_URL = `${USER_API}/login`
      axios({
        url: API_URL,
        method: 'POST',
        params: user,
      }).then((res) => {
        commit('USER_LOGIN', user.id)
        sessionStorage.setItem("access-token", res.data["access-token"]);
        sessionStorage.setItem("loginUser", res.data["loginUser"]);
        window.history.back();
      }).catch((err) => {
        console.log(err)
        // window.history.go();
        window.alert("로그인에 실패하였습니다.")
      })
    },
    follow({ commit }, par) {
      const API_URL = `${FOllOW_API}/follow`
      commit
      axios({
        url: API_URL,
        method: 'PUT',
        params: par,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        window.alert("팔로우되었습니다.")
        window.history.go()
      }).catch((err) => {
        console.log(err)
      })
    },
    unfollow({ commit }, par) {
      const API_URL = `${FOllOW_API}/unfollow`
      commit
      axios({
        url: API_URL,
        method: 'PUT',
        params: par,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        window.alert("언팔로우되었습니다.")
        window.history.go()
      }).catch((err) => {
        console.log(err)
      })
    },
    getFollowings({ commit }, user) {
      const API_URL = `${FOllOW_API}/list/${user}`
      axios({
        url: API_URL,
        method: 'GET',
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then((res) => {
        commit('GET_FOLLOW', res.data)
      }).catch((err) => {
        console.log(err)
      })
    },
    updateUser({ commit }, updateUser) {
      const API_URL = `${USER_API}/user`
      commit
      axios({
        url: API_URL,
        method: 'PUT',
        params: updateUser,
        headers: {
          "access-token": sessionStorage.getItem("access-token")
        },
      }).then(() => {
        console.log(updateUser)
        window.alert("유저 정보가 변경되었습니다.")
        window.history.go(-1);
      }).catch((err) => {
        console.log(err)
      })
    },
    //youtube
    searchYoutube({ commit }, payload) {
      const API_URL = `https://www.googleapis.com/youtube/v3/search`;
      const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;

      axios({
        url: API_URL,
        method: "GET",
        params: {
          key: YOUTUBE_KEY,
          part: "snippet",
          q: payload,
          type: "video",
          maxResults: 10,
        },
      })
      .then((res) => {
        commit("SEARCH_YOUTUBE", res.data.items)
      }).catch((err) => {
        console.log(err)
      });
    },
    clickVideo({ commit }, payload) {
      commit("CLICK_VIDEO", payload)
    },

  },
  modules: {
  },
})
