<template>
  <div class="wrapper">
    <notifications></notifications>
    <side-bar>
      <template slot="links">
        <sidebar-item :link="{
          name: 'Dashboard',
          path: '/dashboard',
          icon: 'ni ni-tv-2 text-primary',
        }">
        </sidebar-item>

        <sidebar-item :link="{
          name: '영상 보기',
          path: '/video/list',
          icon: 'ni ni-planet text-blue'
        }">
        </sidebar-item>

        <sidebar-item :link="{
          name: '부위별 영상 보기',
          path: '/part',
          icon: 'ni ni-bullet-list-67 text-blue'
        }">
        </sidebar-item>

        <sidebar-item :link="{
          name: '내 주변 관광지',
          path: '/maps',
          icon: 'ni ni-pin-3 text-orange'
        }">
        </sidebar-item>
        
        <sidebar-item :link="{
          name: '오늘의 날씨',
          path: '/weather',
          icon: 'ni ni-bullet-list-67 text-red'
        }">
        </sidebar-item>
        
        <sidebar-item v-if="getUser" :link="{
          name: '마이페이지',
          path: '/profile',
          icon: 'ni ni-single-02 text-yellow'
        }">
        </sidebar-item>
        <sidebar-item v-if="!getUser" :link="{
          name: '로그인',
          path: '/login',
          icon: 'ni ni-key-25 text-info'
        }">
        </sidebar-item>
        <sidebar-item v-if="!getUser" :link="{
          name: '회원가입',
          path: '/signin',
          icon: 'ni ni-circle-08 text-pink'
        }">
        </sidebar-item>
        <sidebar-item v-if="getUser" :link="{
          name: 'Logout',
          path: '/logout',
          icon: 'ni ni-circle-08 text-pink'
        }">
        </sidebar-item>
      </template>

      <template slot="links-after">
        <hr class="my-3">
        <h6 class="navbar-heading p-0 text-muted">Documentation</h6>

        <b-nav class="navbar-nav mb-md-3">
          <b-nav-item href="https://www.creative-tim.com/learning-lab/bootstrap-vue/quick-start/argon-dashboard">
            <i class="ni ni-spaceship"></i>
            <b-nav-text class="p-0">Getting started</b-nav-text>
          </b-nav-item>
          <b-nav-item href="https://www.creative-tim.com/learning-lab/bootstrap-vue/colors/argon-dashboard">
            <i class="ni ni-palette"></i>
            <b-nav-text class="p-0">Foundation</b-nav-text>
          </b-nav-item>
          <b-nav-item href="https://www.creative-tim.com/learning-lab/bootstrap-vue/avatar/argon-dashboard">
            <i class="ni ni-ui-04"></i>
            <b-nav-text class="p-0">Components</b-nav-text>
          </b-nav-item>
        </b-nav>
      </template>
    </side-bar>
    <div class="main-content">
      <dashboard-navbar :type="$route.meta.navbarType"></dashboard-navbar>

      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>
      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<script>
/* eslint-disable no-new */
import PerfectScrollbar from 'perfect-scrollbar';
import 'perfect-scrollbar/css/perfect-scrollbar.css';

function hasElement(className) {
  return document.getElementsByClassName(className).length > 0;
}

function initScrollbar(className) {
  if (hasElement(className)) {
    new PerfectScrollbar(`.${className}`);
  } else {
    // try to init it later in case this component is loaded async
    setTimeout(() => {
      initScrollbar(className);
    }, 100);
  }
}

import DashboardNavbar from './DashboardNavbar.vue';
import ContentFooter from './ContentFooter.vue';
import DashboardContent from './Content.vue';
import { FadeTransition } from 'vue2-transitions';
import { mapState } from 'vuex';

export default {
  components: {
    DashboardNavbar,
    ContentFooter,
    DashboardContent,
    FadeTransition
  },
  computed: {
    ...mapState(['loginUser']),
    getUser() {
      if (this.loginUser) {
        return true;
      } else {
        return false;
      }
    }
  },
  methods: {
    initScrollbar() {
      let isWindows = navigator.platform.startsWith('Win');
      if (isWindows) {
        initScrollbar('sidenav');
      }
    },

    logout() {
      sessionStorage.removeItem("loginUser");
      sessionStorage.removeItem("access-token");
      this.$store.commit("USER_LOGOUT");
      window.history.go();
    },

  },
  mounted() {
    this.initScrollbar()
  }
};
</script>
<style lang="scss">

</style>
