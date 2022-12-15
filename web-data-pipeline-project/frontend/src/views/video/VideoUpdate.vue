<template>
  <div>
    <h2>영상 수정</h2>
    <fieldset>
      <legend>영상 수정</legend>
      <!-- 히든videoIndex?? 어떻게전달하지 -->
      <input type="hidden" id = "videoIndex" value="video.videoIndex" v-model="video.videoIndex" >
      <label for="title">제목</label>
      <input type="text" id="title" v-model="video.title" /> <br />
      <label for="category">분류</label>
      <input type="text" id="category" v-model="video.category" /> <br />
      <label for="url">주소</label>
      <input type="text" id="url" v-model="video.url"/><br />
      <button @click="updateVideo">수정</button>
    </fieldset>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'VideoUpdate',
  computed: {
    ...mapState(['video']),
  },
  methods: {
    updateVideo() {
      let updateVideo = {
        id: this.video.videoIndex,
        title: this.video.title,
        category: this.video.category,
        url: this.video.url,
      };
      this.$store.dispatch('updatevideo', updateVideo);
    },
  },
};
</script>

<style></style>
