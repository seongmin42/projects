<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      <h1 class="display-3 text-white">시청할 영상 부위를 선택하세요</h1>
      <b-row class="d-flex justify-content-center mt-5">

          <base-button type="secondary" @click="body" class="mr-7 ml-7">
            <span class="text-nowrap" style="color:black; font-weight: bold;">전신</span>
          </base-button>

          <base-button type="secondary" @click="upper" class="mr-7 ml-7">
            <span class="text-nowrap" style="color:black; font-weight: bold;">상체</span>
          </base-button>

          <base-button type="secondary" @click="lower" class="mr-7 ml-7">
            <span class="text-nowrap" style="color:black; font-weight: bold;">하체</span>
          </base-button>

          <base-button type="secondary" @click="stomach" class="mr-7 ml-7">
            <span class="text-nowrap" style="color:black; font-weight: bold;">복부</span>
          </base-button>

      </b-row>
  

      <b-card-group class="mt-6">
        <b-col v-for="video in videos" :key="video.videoIndex" xl="3" md="6">
          <router-link :to="`/video/${video.videoIndex}`">
            <b-card :title="video.title" type="gradient-red" :sub-title="video.user.nickname" class="mb-4">

              <template slot="footer">
                <iframe width="100%" height="100%" :src="video.url" title="YouTube video player" frameborder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                  allowfullscreen></iframe>
                <p class="text-success mr-2" style="font-weight:bold ;">조회수 {{ video.viewCnt }}</p>
                <span class="text-nowrap"><i class="ni ni-like-2"
                    style="color:darkslategrey; font-weight: bold">&nbsp;{{ video.like
                    }}</i></span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="text-nowrap" style="color:darkslategrey; font-weight: bold;"><i class="ni ni-like-2"
                    style="transform:scaleY(-1);"></i>&nbsp;{{ video.dislike }}</span>
              </template>
            </b-card>
          </router-link>
        </b-col>
      </b-card-group>

    </base-header>


  </div>
</template>
<script>
import Vue from 'vue'
import VueClipboard from 'vue-clipboard2'
import BaseHeader from '@/components/BaseHeader';
import { mapState } from 'vuex';

Vue.use(VueClipboard)
export default {
  name: 'Part',
  data() {
    return {
      mode: 1,
      keyword: '',
    };
  },
  computed: {
    ...mapState(['videos']),
  },
  components: {
    BaseHeader
  },
  created() {
    this.$store.dispatch('getVideos');
  },
  methods: {

    body() {
      this.$store.dispatch('getVideosByPart', "전신")
    },
    upper() {
      this.$store.dispatch('getVideosByPart', "상체")
    },
    lower() {
      this.$store.dispatch('getVideosByPart', "하체")
    },
    stomach() {
      this.$store.dispatch('getVideosByPart', "복부")
    },
  }
};
</script>
<style>

</style>
