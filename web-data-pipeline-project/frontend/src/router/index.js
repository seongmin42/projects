import DashboardLayout from '@/views/Layout/DashboardLayout.vue';
import AuthLayout from '@/views/Pages/AuthLayout.vue';

import MapView from '@/views/MapView.vue'

import VideoCreate from '@/views/video/VideoCreate.vue'
import VideoDetail from '@/views/video/VideoDetail.vue'
import VideoUpdate from '@/views/video/VideoUpdate.vue'
import VideoSearchResult from '@/views/video/VideoSearchResult.vue'

import ReviewDetail from '@/views/review/ReviewDetail.vue'
import ReviewCreate from '@/views/review/ReviewCreate.vue'
import ReviewList from '@/views/review/ReviewList.vue'
import ReviewUpdate from '@/views/review/ReviewUpdate.vue'

import NotFound from '@/views/NotFoundPage.vue';
import UserProfile from '@/views/Pages/UserProfile.vue';

const routes = [
  {
    path: '/',
    redirect: 'dashboard',
    component: DashboardLayout,
    children: [
      {
        path: '/dashboard',
        name: 'dashboard',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/Dashboard.vue')
      },
      {
        path: '/video/list',
        name: 'VideoList',
        component: () => import('../views/video/VideoList.vue')
      },
      {
        path: '/part',
        name: 'Part',
        component: () => import('../views/video/VideoPart.vue')
      },
      {
        path: '/profile',
        name: 'profile',
        component: UserProfile
      },
      {
        path: '/maps',
        name: 'MapView',
        component: MapView
      },
      {
        path: '/weather',
        name: 'Weather',
        component: () => import('../views/WeatherTables.vue')
      },
      {
        path: '/logout',
        name: 'logout',
        component: () => import('../views/Pages/Logout.vue')
      },
    ]
  },
  {
    path: '/video',
    component: DashboardLayout,
    children:[
      {
        path: "create",
        name : "videoCreate",
        component: VideoCreate,
      },
      {
        path : ":id",
        name : "VideoDetail",
        component : VideoDetail,
      },
      {
        path : "update",
        name: "videoUpdate",
        component : VideoUpdate,
      },
      {
        path : "search",
        name: "VideoSearchResult",
        component : VideoSearchResult,
      },
    ]
  },
  {
    path: '/review',
    component: DashboardLayout,
    children:[
      {
        path:"",
        name : "reviewList",
        component: ReviewList,
      },
      {
        path: "create",
        name : "reviewCreate",
        component: ReviewCreate,
      },
      {
        path : ":id",
        name : "ReviewDetail",
        component: ReviewDetail,
      },
      {
        path : "update",
        name: "reviewUpdate",
        component : ReviewUpdate,
      },
    ],
  },
  {
    path: '/',
    redirect: 'login',
    component: AuthLayout,
    children: [
      {
        path: '/login',
        name: 'login',
        component: () => import('../views/Pages/Login.vue')
      },
      {
        path: '/signin',
        name: 'signin',
        component: () => import('../views/Pages/Register.vue')
      },
      { path: '*', component: NotFound }
    ]
  },
];

export default routes;
