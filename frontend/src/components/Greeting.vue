<template>
    <div id="greeting">
        <h3>Greeting component</h3>
        <p>Counter: {{ counter }}</p>
        <p>Username: {{ username }}</p>
    </div>
</template>

<script>
import {AXIOS} from './http-common'

export default {
    name: 'Greeting',
    data() {
        return {
            counter: 0,
            username: ''
        }
    },
    methods: {
        loadGreeting() {
            AXIOS.get('/greeting', { params: { name: 'Vadim' } })
            .then(response => {
                this.$data.counter = response.data.id;
                this.$data.username = response.data.content;
            })
            .catch(error => {
                console.log('ERROR: ' + error.response.data);
            })
        }
    },
    mounted() {
        this.loadGreeting();
    }
}
</script>

<style>
</style>