<template>
  <div>
    <h3>영상 등록</h3>
    <fieldset>
      <legend>영상 등록</legend>
      <label for="title">제목 : </label>
      <input type="text" id="title" v-model="title" /><br />
      <label for="category">분류 : </label>
      <input type="text" id="category" v-model="category" /><br />
      <label for="content">내용 : </label>
      <textarea id="content" cols="30" rows="10" v-model="content"></textarea>
      <br>
      <label for="url">주소 : </label>
      <input type="text" id="url" v-model="url" /><br />
      <button @click="createVideo">등록</button>
    </fieldset>
  </div>
</template>

<script>
  export default {
    name: 'VideoCreate',
    data(){
      return{
        title: '',
        category: '',
        content: '',
        url: '',
      };
    },
    methods:{
      createVideo(){
        let video = {
          title: this.title,
          category: this.category,
          content: this.content,
          url: this.url,
        };

        this.$store.dispatch('createVideo', video);
      },
    },
  };
</script>

<style></style>