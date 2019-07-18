<template>
    <div div="userpage">
        <h2>{{ pageContent }}</h2>
    </div>
</template>

<script>
import {AXIOS} from './http-common'

export default {
    name: 'UserPage',
    data() {
        return {
            pageContent: ''
        }
    },
    methods: {
        loadUserContent() {
            const header = {'Authorization': 'Bearer ' + this.$store.getters.getToken};
            AXIOS.get('/usercontent', { headers: header })
            .then(response => {
                this.$data.pageContent = response.data;
            })
            .catch(error => {
                console.log('ERROR: ' + error.response.data);
            })
        }
    },
    mounted() {
        this.loadUserContent();
    }
}
</script>

<style>
</style>