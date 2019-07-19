<template>
    <div id="email">
        <b-button  v-on:click="sendSimpleMessage" variant="primary">Simple Email</b-button><br/>
        <b-button  v-on:click="sendEmailUsingTemplate" variant="primary">Template Email</b-button><br/>
        <b-button v-on:click="sendHTMLEmail" variant="primary">HTML Email</b-button><br/>
        </div>
</template>

<script>
import {AXIOS} from './http-common'

export default {
    name: 'EmailPage',
    data() {
        return {
            counter: 0,
            username: '',
            header: {'Authorization': 'Bearer ' + this.$store.getters.getToken}
        }
    },
    methods: {
        sendSimpleMessage() {
            AXIOS.get('/sendSimpleEmail', { headers: this.$data.header })
            .then(response => {
                console.log(response);
                alert("OK");
            })
            .catch(error => {
                console.log('ERROR: ' + error.response.data);
            })
        },
        sendEmailUsingTemplate() {
            AXIOS.get('/sendTemplateEmail', { headers: this.$data.header })
            .then(response => {
                console.log(response);
                alert("OK")
            })
            .catch(error => {
                console.log('ERROR: ' + error.response.data);
            })
        },
        sendHTMLEmail() {
            AXIOS.get('/sendHtmlEmail', { headers: this.$data.header })
            .then(response => {
                console.log(response);
                alert("OK")
            })
            .catch(error => {
                console.log('ERROR: ' + error.response.data);
            })
        }
    }
}
</script>

<style>
#email {
    margin-left: 38%;
    margin-top: 50px;
}
button {
    width: 150px;
}
</style>