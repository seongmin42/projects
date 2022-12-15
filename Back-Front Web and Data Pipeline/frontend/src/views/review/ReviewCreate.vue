<template>
  <div>
    <h3>리뷰 등록</h3>
    <fieldset>
      <legend>리뷰 등록</legend>
      <input type="hidden" id="videoIndex" v-model="videoIndex" /><br />
      <label for="title">제목 : </label>
      <input type="text" id="title" v-model="title" /><br />
      <label for="content">내용 : </label>
      <textarea id="content" cols="100" rows="5" v-model="content"></textarea>
      <br />
      <button @click="createReview">등록</button>
    </fieldset>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "ReviewCreate",
  data() {
    return {
      videoIndex: "",
      title: "",
      content: "",
      writerId: this.$store.state.loginUser.id,
    };
  },
  computed: {
    ...mapState(["video", "reviews", "loginUser"]),
  },
  methods: {
    createReview() {
      let review = {
        videoIndex: this.videoIndex,
        title: this.title,
        content: this.content,
        writerId: this.loginUser,
      };
      this.$store.dispatch("createReview", review);
    },
  },
};
</script>

<style>
</style>