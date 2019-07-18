<template>
    <div div="home">
        <b-jumbotron>
        <template slot="header">Kotlin + Spring Boot + Vue.js</template>

        <template slot="lead">
          This is the demo web-application written in Kotlin using Spring Boot and Vue.js for frontend
        </template>

        <hr class="my-4" />

        <p v-if="!this.$store.getters.isAuthenticated">
          Login and start
        </p>

        <router-link to="/login" v-if="!this.$store.getters.isAuthenticated">
            <b-button variant="primary">Login</b-button>
        </router-link>

      </b-jumbotron>
    </div>
</template>

<script>
export default {
    name: 'Home'
}
</script>

<style>
</style>