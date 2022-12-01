<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- 리뷰 디테일 -->
      <b-row>
        <b-col>
          <b-card class="mb-0 mr-xl-0 ml-xl-4" style="width: 80%">
            <input
              type="hidden"
              id="reviewIndex"
              v-model="review.reviewIndex"
            />
            <b-form-group>
              <b-input-group>
                <b-form-input
                  v-model="review.title"
                  type="text"
                  id="title"
                ></b-form-input>
              </b-input-group>
            </b-form-group>

            <b-input-group>
              <b-form-textarea
                placeholder="내용"
                id="content"
                v-model="review.content"
              ></b-form-textarea>
              <b-input-group-append>
                <b-button variant="outline-primary" @click="updateReview"
                  >등록</b-button
                >
              </b-input-group-append>
            </b-input-group>
          </b-card>
        </b-col>
      </b-row>
    </base-header>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "ReviewUpdate",
  computed: {
    ...mapState(["review"]),
  },
  methods: {
    updateReview() {
      let updateReview = {
        reviewIndex: this.review.reviewIndex,
        title: this.review.title,
        content: this.review.content,
      };
      console.log(updateReview);
      this.$store.dispatch("updateReview", updateReview);
    },
  },
};
</script>

<style></style>
