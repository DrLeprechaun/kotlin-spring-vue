<template>
  <div id="app">
    <b-navbar style="width: 100%" type="dark" variant="dark">
      <b-navbar-brand id="nav-brand" href="#">Kotlin+Spring+Vue</b-navbar-brand>
      <router-link to="/"><img height="30px" src="./assets/img/kotlin-logo.png" alt="Kotlin+Spring+Vue"/></router-link>
      <router-link to="/"><img height="30px" src="./assets/img/spring-boot-logo.png" alt="Kotlin+Spring+Vue"/></router-link>
      <router-link to="/"><img height="30px" src="./assets/img/vuejs-logo.png" alt="Kotlin+Spring+Vue"/></router-link>
      <router-link to="/user" class="nav-link text-light" v-if="this.$store.getters.isAuthenticated">User</router-link>
      <router-link to="/admin" class="nav-link text-light" v-if="this.$store.getters.isAuthenticated && this.$store.getters.isAdmin">Admin</router-link>
      <router-link to="/email" class="nav-link text-light" v-if="this.$store.getters.isAuthenticated">Email</router-link>
      <router-link to="/register" class="nav-link text-light" v-if="!this.$store.getters.isAuthenticated">Register</router-link>
      <router-link to="/login" class="nav-link text-light" v-if="!this.$store.getters.isAuthenticated">Login</router-link>
      <a href="#" class="nav-link text-light" v-if="this.$store.getters.isAuthenticated" v-on:click="logout">Logout </a>
    </b-navbar>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'app',
  methods: {
    logout() {
      this.$store.dispatch('logout');
      this.$router.push('/')
    }
  } 
}
</script>

<style>
</style>
