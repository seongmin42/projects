<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- 리뷰 디테일 -->
      <b-row>
        <b-col>
          <b-card :title="review.title" :sub-title="review.user.nickname" class="mb-0 mr-xl-0 ml-xl-4" style="width: 80%;">
            <hr>
            {{review.content}}
            <template slot="footer">
              <div class="text-success  mr-2" style="font-weight:bold ; color: darkslategray !important">등록일 :
                {{ review.regDate }}</div>
              <div class="text-success mr-2" style="font-weight:bold ; color: darkslategray !important">조회수 :
                {{ review.viewCnt }}</div>
              <br>
              <span class="">
                <base-button type="primary" @click="like(`${review.reviewIndex}`)" class="mr-3">
                  <span class="text-nowrap">
                    <i class="ni ni-like-2" style="color:whitesmoke; font-weight: bold">
                    &nbsp;{{review.like}}</i>
                  </span>
                </base-button>
                <base-button type="default" @click="dislike(`${review.reviewIndex}`)" class="mr-6">
                  <span class="text-nowrap" style="color:whitesmoke; font-weight: bold;"><i class="ni ni-like-2"
                    style="transform:scaleY(-1);"></i>&nbsp;{{ review.dislike }}</span>
                </base-button>
              </span>
              <span v-if="(loginUser == review.user.id)" class="flex-wrap">
                <base-button type="default" @click="updateReview" class="mr-3">
                  <span class="text-nowrap" style="color:whitesmoke; font-weight: bold;">수정</span>
                </base-button>
                <base-button type="default" @click="deleteReview">
                  <span class="text-nowrap" style="color:whitesmoke; font-weight: bold;">삭제</span>
                </base-button>
              </span>
              <br>
            </template>
          </b-card>
        </b-col>
      </b-row>
    </base-header>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ReviewDetail",
  computed: {
    ...mapState(["review", "loginUser"]),
    getUser() {
      if (this.loginUser) {
        return true;
      } else {
        return false;
      }
    },
  },
  created() {
    const pathName = document.location.hash.split("/");
    const reviewIndex = pathName[pathName.length - 1];

    this.$store.dispatch("getReview", reviewIndex);
  },
  methods: {
    updateReview() {
      this.$router.push({ name: "reviewUpdate" });
    },
    deleteReview() {
      const params = {
        reviewIndex: this.$store.state.review.reviewIndex,
        videoIndex: this.$store.state.review.videoIndex,
      };
      this.$store.dispatch("deleteReview", params);
    },
    like(reviewIndex){
      this.$store.dispatch('likeReview', reviewIndex);
    },
    dislike(reviewIndex){
      this.$store.dispatch('dislikeReview', reviewIndex);
    },
  },
};
</script>

<style></style>
