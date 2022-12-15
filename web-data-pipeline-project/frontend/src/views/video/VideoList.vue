<template>
  <div>
    <!-- BootstrapVue Done -->
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      <b-form-group class="mb-4 ml-3" style="width:50%">
          <b-input-group class="input-group-alternative input-group-merge">
            <b-form-input placeholder="검색어를 입력하세요"  @keyup.13="search" type="text" v-model="query"> </b-form-input>

            <div class="input-group-append">
              <button class="input-group-text" @click="search"><i class="fas fa-search"></i></button>
            </div>
          </b-input-group>
        </b-form-group>

        <b-card-group>
        <b-col v-for="video in videos" :key="video.videoIndex" xl="3" md="6" >
          <router-link :to="`/video/${video.videoIndex}`">
          <b-card :title="video.title" type="gradient-red" :sub-title="video.user.nickname" 
          class="mb-4" >
          
            <template slot="footer" >
              <iframe width="100%" height="100%" :src="video.url" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
              <p class="text-success mr-2" style="font-weight:bold ;">조회수 {{video.viewCnt}}</p>
              <span class="text-nowrap"><i class="ni ni-like-2" style="color:darkslategrey; font-weight: bold">&nbsp;{{video.like}}</i></span>&nbsp;&nbsp;&nbsp;&nbsp;
              <span class="text-nowrap" style="color:darkslategrey; font-weight: bold;"><i class="ni ni-like-2" style="transform:scaleY(-1);"></i>&nbsp;{{video.dislike}}</span>
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
  name: 'VideoList',
  data() {
    return {
      query:"",
    };
  },
  computed: {
    ...mapState(['videos']),
  },
  components: {
    BaseHeader
  },
  created() {
    console.log(this.$store)
    this.$store.dispatch('getVideos');
  },
  methods: {
    onCopy() {
      this.$notify({
        type: 'info',
        message: 'Copied to clipboard'
      })
    },
    search(){
      console.log(72)
      const payload={
        query : this.query
      }
      this.$store.dispatch("searchVideo", payload)
    }
  }
};
</script>
<style>

</style>
