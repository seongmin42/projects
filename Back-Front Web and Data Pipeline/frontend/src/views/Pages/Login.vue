<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container>
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">로그인을 진행해 주세요</h1>
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg
          x="0"
          y="0"
          viewBox="0 0 2560 100"
          preserveAspectRatio="none"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
        >
          <polygon
            class="fill-default"
            points="2560 0 2560 100 0 100"
          ></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <b-row class="justify-content-center">
        <b-col lg="5" md="7">
          <b-card no-body class="bg-secondary border-0 mb-0">
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>아이디와 비밀번호를 입력하세요</small>
              </div>
              <validation-observer
                v-slot="{ handleSubmit }"
                ref="formValidator"
              >
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <base-input
                    alternative
                    class="mb-3"
                    name="Id"
                    :rules="{ required: true }"
                    prepend-icon="ni ni-single-02"
                    placeholder="아이디"
                    v-model="id"
                  >
                  </base-input>

                  <base-input
                    alternative
                    class="mb-3"
                    name="Password"
                    :rules="{ required: true }"
                    prepend-icon="ni ni-lock-circle-open"
                    type="password"
                    placeholder="비밀번호"
                    v-model="password"
                  >
                  </base-input>
                  <div class="text-center">
                    <base-button
                      type="primary"
                      native-type="submit"
                      class="my-4"
                      >로그인</base-button
                    >
                    <router-link to="/signin" class="submit"
                      >회원가입</router-link
                    >
                  </div>
                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>


<script>
export default {
  name: "login",
  data() {
    return {
      id: "",
      password: "",
    };
  },
  methods: {
    onSubmit() {
      let user = {
        id: this.id,
        password: this.password,
      };
      this.$store.dispatch("login", user);
    },
  },
};
</script>
