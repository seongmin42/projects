<template>
  <b-card no-body class="card-profile" alt="Image placeholder" img-top>
    <b-row class="justify-content-center">
      <b-col lg="3" class="order-lg-2">
        <div class="card-profile-image">
          <a href="#">
            <b-img src="img/theme/team-4.jpg" rounded="circle" />
          </a>
        </div>
      </b-col>
    </b-row>

    <b-card-header class="text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
      <div class="d-flex justify-content-between">
        <a href="#" >ㅤ</a>
        <a href="#" >ㅤ</a>
      </div>
    </b-card-header>

    <b-card-body class="pt-0">
      <b-row>
        <br>
        <br>
        <br>
      </b-row>
      <div class="text-center">
        <h5 class="h3">
          {{loginUser}}<span class="font-weight-light"></span>
        </h5>
        <div class="h5 font-weight-300">
          <i class="ni location_pin mr-2"></i>Seoul, Korea
        </div>
        <div class="h5 mt-4">
          <i class="ni business_briefcase-24 mr-2"></i>Travel
        </div>
        <div>
          <i class="ni education_hat mr-2"></i>backend engineer
        </div>
        <hr class="my-4" />
        <p>
          Lorem Ipsum is simply dummy text of the printing and text ever since the 1500s, when an unknown printe book.
        </p>
        <a href="#">Show more</a>
      </div>
    </b-card-body>
  </b-card>
</template>
<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>
<style></style>
