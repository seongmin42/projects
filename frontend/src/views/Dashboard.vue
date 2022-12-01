<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
    <h1>Welcome to Travel ! ! !</h1>
    </base-header>

    <h1 class="ml-4 mt-4">금주의 인기영상들</h1>
    <b-card-group class="mt-4">
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


  </div>
</template>
<script>
  import { mapState } from 'vuex';
  export default {
    components: {

    },
    data() {
      return {
      
      }
    },
    computed:{
      ...mapState(['videos']),
    },
    methods: {
      
    },
    created() {
      this.$store.dispatch('getVideosByViewCnt');
    }
  };
</script>
<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
</style>
