<template>
  <div>
    <h3 slot="header" class="mb-0">리뷰 등록</h3>
    <br>
    <b-form-group>
      <b-input-group>
        <b-form-input
          placeholder="제목"
          type="text"
          id="title"
          v-model="title"
        ></b-form-input>
      </b-input-group>
    </b-form-group>

    <b-input-group>
      <b-form-textarea
        placeholder="내용"
        id="content"
        v-model="content"
      ></b-form-textarea>
      <b-input-group-append>
        <b-button variant="outline-primary" @click="createReview"
          >등록</b-button
        >
      </b-input-group-append>
    </b-input-group>
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
        user: this.$store.state.loginUser,
      };
      console.log(39);
      console.log(review);
      this.$store.dispatch("createReview", review);
    },
  },
};
</script>

<style>
</style>