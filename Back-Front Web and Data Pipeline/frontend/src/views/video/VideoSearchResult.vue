<template>
  <div>
    <!-- BootstrapVue Done -->
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
        <b-card-group>
        <b-col v-for="video in videos" :key="video.videoIndex" xl="3" md="6" >
          <router-link :to="`/video/${video.videoIndex}`">
          <b-card :title="video.title" type="gradient-red" :sub-title="video.writerId" 
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
  name: 'VideoSearchResult',
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
    search() {
      const payload = {
        keyword: this.keyword,
      };
      this.$store.dispatch('searchVideo', payload);
    },
  }
};
</script>
<style>

</style>
