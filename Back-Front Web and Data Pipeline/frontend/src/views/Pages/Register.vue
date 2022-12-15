<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">회원가입을 진행해 주세요</h1>
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
             xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8" >
          <b-card no-body class="bg-secondary border-0">
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>회원 정보를 입력하세요</small>
              </div>
              <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                <b-form role="form" @submit.prevent="handleSubmit(createUser)">
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-single-02"
                              placeholder="아이디"
                              name="Id"
                              :rules="{required: true}"
                              v-model="id">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="비밀번호"
                              type="password"
                              name="Password"
                              :rules="{required: true}"
                              v-model="password">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-caps-small"
                              placeholder="이름"
                              name="Name"
                              :rules="{required: true}"
                              v-model="name">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-email-83"
                              placeholder="이메일"
                              name="Email"
                              :rules="{required: true, email: true}"
                              v-model="email">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-ruler-pencil"
                              placeholder="닉네임"
                              name="Nickname"
                              :rules="{required: true}"
                              v-model="nickname">
                  </base-input>
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4">회원 가입</b-button>
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
    name: 'UserCreate',
    data() {
      return {
          id: '',
          password: '',
          name: '',
          email: '',
          nickname: '',
      }
    },
    methods: {
      createUser(){
        let user = {
          id: this.id,
          password: this.password,
          name: this.name,
          email: this.email,
          nickname: this.nickname,
        };

        this.$store.dispatch('createUser', user);
      },
    },
  };
</script>
<style>
</style>
