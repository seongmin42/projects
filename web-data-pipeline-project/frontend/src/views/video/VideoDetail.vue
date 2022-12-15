<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- 비디오 디테일 -->
      <b-container class="mb-0 mr-xl-0 ml-xl-4" style="width: 80%">
        <card>
          <h3 slot="header" class="mb-0">{{ video.title }}</h3>
          <h5>{{ video.user.nickname }}</h5>
          <br />
          <iframe
            width="90%"
            height="500px"
            :src="video.url"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
          ></iframe>
          <hr />
          <div
            class="text-success mr-2"
            style="font-weight: bold; color: darkslategray !important"
          >
            분류 : {{ video.category }}
          </div>
          <div
            class="text-success mr-2"
            style="font-weight: bold; color: darkslategray !important"
          >
            주소 :
            <a :href="video.url">{{ video.url }}</a>
          </div>
          <div
            class="text-success mr-2"
            style="font-weight: bold; color: darkslategray !important"
          >
            등록일 : {{ video.regDate }}
          </div>
          <div
            class="text-success mr-2"
            style="font-weight: bold; color: darkslategray !important"
          >
            조회수 : {{ video.viewCnt }}
          </div>
          <br />
          <span class="">
            <base-button
              type="primary"
              @click="like(`${video.videoIndex}`)"
              class="mr-3"
            >
              <span class="text-nowrap">
                <i
                  class="ni ni-like-2"
                  style="color: whitesmoke; font-weight: bold"
                >
                  &nbsp;{{ video.like }}</i
                >
              </span>
            </base-button>
            <base-button
              type="default"
              @click="dislike(`${video.videoIndex}`)"
              class="mr-6"
            >
              <span
                class="text-nowrap"
                style="color: whitesmoke; font-weight: bold"
                ><i class="ni ni-like-2" style="transform: scaleY(-1)"></i
                >&nbsp;{{ video.dislike }}</span
              >
            </base-button>
          </span>
          <span v-if="loginUser == video.user.id" class="flex-wrap">
            <base-button type="default" @click="updateVideo" class="mr-3">
              <span
                class="text-nowrap"
                style="color: whitesmoke; font-weight: bold"
                >수정</span
              >
            </base-button>
            <base-button type="default" @click="deleteVideo">
              <span
                class="text-nowrap"
                style="color: whitesmoke; font-weight: bold"
                >삭제</span
              >
            </base-button>
          </span>
          <br />
          <hr />
        </card>
      </b-container>

      <b-container
        v-if="getUser"
        class="mt-2 mb-2 mr-xl-0 ml-xl-4"
        style="width: 80%"
      >
        <!-- 언팔로우상태면 팔로우하기 팔로우상태면 언팔로우하기 -->
        <div v-if="video.user.id != loginUser" class="mb-2">
          <card header-classes="bg-transparent">
            <div>
              <!-- 언팔로우상태면 팔로우하기 팔로우상태면 언팔로우하기 -->
              written by {{ video.user.nickname }}
              <base-button
                type="default"
                v-if="!followings.includes(video.user.id)"
                class="follow-button ml-5"
                @click="
                  follow({
                    following: `${loginUser}`,
                    follower: `${video.user.id}`,
                  })
                "
                >팔로우하기</base-button
              >

              <base-button
                type="default"
                v-if="followings.includes(video.user.id)"
                class="follow-button ml-5"
                @click="
                  unfollow({
                    following: `${loginUser}`,
                    follower: `${video.user.id}`,
                  })
                "
              >
                언팔로우하기
              </base-button>
            </div>
          </card>
        </div>

        <!--리뷰 등록-->

        <card
          header-classes="bg-transparent"
        >
          <h3 slot="header" class="mb-0">리뷰 등록</h3>
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
        </card>
      </b-container>

      <!--리뷰 보기-->
      <b-container class="mt-2 mb-2 mr-xl-0 ml-xl-4" style="width: 80%">
        <b-row class="justify-content-center">
          <b-col lg="12">
            <b-card header-classes="bg-transparent">
              <h3 slot="header" class="mb-0">리뷰 보기</h3>
              <b-row class="icon-examples">
                <b-col
                  v-for="review in reviews"
                  :key="review.reviewIndex"
                  lg="3"
                  md="6"
                >
                  <router-link :to="`/review/${review.reviewIndex}`">
                    <b-card>
                      <i class="ni ni-collection mr-3" style="color: black"></i>
                      <span style="color: black; font-weight: bold">{{
                        review.title
                      }}</span>

                      <div class="d-flex justify-content-end text-success">
                        {{ review.user.nickname }}
                      </div>
                    </b-card>
                  </router-link>
                </b-col>
              </b-row>
            </b-card>
          </b-col>
        </b-row>
      </b-container>
    </base-header>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "VideoDetail",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  components: {},
  computed: {
    ...mapState(["video", "reviews", "loginUser", "followings"]),
    getUser() {
      if (this.loginUser) {
        return this.loginUser;
      } else {
        return false;
      }
    },
  },
  created() {
    const pathName = document.location.hash.split("/");
    const videoIndex = pathName[pathName.length - 1];
    this.$store.dispatch("getVideo", videoIndex);
    this.$store.dispatch("getReviewsByVideoIndex", videoIndex);
    this.$store.dispatch("getFollowings", this.loginUser);
  },
  methods: {
    updateVideo() {
      this.$router.push({ name: "videoUpdate" });
    },
    deleteVideo() {
      this.$store.dispatch("deleteVideo", this.video.id);
    },
    createReview() {
      const pathName = document.location.hash.split("/");
      const videoIndex = pathName[pathName.length - 1];
      let review = {
        videoIndex: videoIndex,
        title: this.title,
        content: this.content,
        writerId: this.loginUser,
      };
      this.$store.dispatch("createReview", review);
      this.title = "";
      this.content = "";
    },
    like(videoIndex) {
      this.$store.dispatch("likeVideo", videoIndex);
    },
    dislike(videoIndex) {
      this.$store.dispatch("dislikeVideo", videoIndex);
    },
    follow(params) {
      this.$store.dispatch("follow", params);
    },
    unfollow(params) {
      this.$store.dispatch("unfollow", params);
    },
  },
};
</script>

<style>
</style>
