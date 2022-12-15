<template>
  <div>

  </div>
</template>


<script>
export default {
  name:"logout",
  data() {
    return {

    };
  },
  beforeCreate(){
      sessionStorage.removeItem("loginUser");
      sessionStorage.removeItem("access-token");
      this.$store.commit("USER_LOGOUT");
      this.$router.push("/")
  }
};
</script>
