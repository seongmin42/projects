<template>
  <card>
    <b-row align-v="center" slot="header">
      <b-col cols="8">
        <h3 class="mb-0">마이페이지</h3>
      </b-col>
    </b-row>

    <b-form @submit.prevent="updateUser">
      <h6 class="heading-small text-muted mb-4">정보 수정하기</h6>
      <input type="hidden" id="id" v-model="id" />
      <div class="pl-lg-4">
        <b-row>
          <b-col lg="6">
            <base-input
              id="password"
              type="password"
              label="Password"
              placeholder="Password"
              v-model="password"
            >
            </base-input>
          </b-col>
          <b-col lg="6">
            <base-input
              id="email"
              type="email"
              label="Email"
              placeholder="Email"
              v-model="email"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="6">
            <base-input
              id="name"
              type="text"
              label="Name"
              placeholder="Name"
              v-model="name"
            >
            </base-input>
          </b-col>
          <b-col lg="6">
            <base-input
              id="nickname"
              type="text"
              label="Nickname"
              placeholder="Nickname"
              v-model="nickname"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-input-group-append>
          <b-button variant="outline-primary" @click="updateUser"
            >유저 정보 수정</b-button
          >
        </b-input-group-append>
      </div>
    </b-form>
  </card>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "UserUpdate",
  data() {
    return {
      id: this.$store.state.loginUser,
      password:"",
      name:"",
      email:"",
      nickname:"",
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  methods: {
    updateUser() {
      let updateUser = {
        id : this.id,
        password: this.password,
        name: this.name,
        email: this.email,
        nickname: this.nickname,
      };
      
      this.$store.dispatch("updateUser", updateUser);
    },
  },
};
</script>
<style></style>
