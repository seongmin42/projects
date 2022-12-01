<template>
  <div>
    <h3>리뷰 목록</h3>
    <hr />
    <div>
      <table>
        <tr>
          <th>인덱스</th>
          <th>비디오 번호</th>
          <th>제목</th>
          <th>내용</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>조회수</th>
          <th>좋아요</th>
          <th>싫어요</th>
        </tr>
        <tr v-for="review in reviews" :key="review.reviewIndex">
          <td>{{ review.reviewIndex }}</td>
          <td>{{ review.videoIndex }}</td>
          <td>
            <router-link :to="`/review/${review.videoIndex}`">{{
              review.title
            }}</router-link>
          </td>
          <td>{{ review.category }}</td>
          <td>{{ review.writerId }}</td>
          <td>{{ review.url }}</td>
          <td>{{ review.regDate }}</td>
          <td>{{ review.viewCnt }}</td>
          <td>{{ review.like }}</td>
          <td>{{ review.dislike }}</td>
          <button class="button" @click="likecnt++">좋아요</button> <span>좋아요 : {{likecnt}}</span>
          <button class="button" @click="dislikecnt++">싫어요</button> <span>싫어요 : {{dislikecnt}}</span>
        </tr>
      </table>
    </div>

    <div>
      <select v-model="mode">
        <option value="1">제목</option>
        <option value="2">내용</option>
        <option value="3">제목+내용</option>
      </select>
      <input type="text" v-model="keyword" />
      <button @click="search">검색</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'ReviewList',
  data() {
    return {
      mode: 1,
      keyword: '',
      likecnt: 0,
      dislikecnt: 0,
    };
  },
  computed: {
    ...mapState(['reviews']),
  },
  created() {
    this.$store.dispatch('getReviews');
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch('getReviews', payload);
    },
  },
};
</script>

<style></style>
