<template>
    <div div="signup">
        <div class="login-form">
        <b-card
              title="Register"
              tag="article"
              style="max-width: 20rem;"
              class="mb-2"
            >
            <div>
                <b-alert
                      :show="dismissCountDown"
                      dismissible
                      variant="danger"
                      @dismissed="dismissCountDown=0"
                      @dismiss-count-down="countDownChanged"
                    > {{ alertMessage }}
                    </b-alert>
            </div>
            <div>
              <b-alert variant="success" :show="successfullyRegistered">
                You have been successfully registered! Now you can login with your credentials
                <hr />
                <router-link to="/login">
                     <b-button variant="primary" class="btn-violet">Login</b-button>
                </router-link>
              </b-alert>
            </div>
              <div>
                 <b-form-input type="text" placeholder="Username" v-model="username" />
                 <div class="mt-2"></div>

                 <b-form-input type="text" placeholder="First Name" v-model="firstname" />
                 <div class="mt-2"></div>

                 <b-form-input type="text" placeholder="Last name" v-model="lastname" />
                 <div class="mt-2"></div>

                 <b-form-input type="text" placeholder="Email" v-model="email" />
                 <div class="mt-2"></div>

                 <b-form-input type="password" placeholder="Password" v-model="password" />
                 <div class="mt-2"></div>

                <b-form-input type="password" placeholder="Confirm Password" v-model="confirmpassword" />
                 <div class="mt-2"></div>
              </div>

              <b-button class="btn-violet" v-on:click="register" variant="primary">Register</b-button>

            </b-card>
        </div>
    </div>
</template>

<script>
import {AXIOS} from './http-common'

export default {
    name: 'SignUp',
    data () {
        return {
            username: '',
            firstname: '',
            lastname: '',
            email: '',
            password: '',
            confirmpassword: '',
            dismissSecs: 5,
            dismissCountDown: 0,
            alertMessage: '',
            successfullyRegistered: false
        }
    },
    methods: {
        register: function () {
            if (this.$data.username === '' || this.$data.username == null) {
                this.$data.alertMessage = 'Please, fill "Username" field';
                this.showAlert();
            } else if (this.$data.firstname === '' || this.$data.firstname == null) {
                this.$data.alertMessage = 'Please, fill "First name" field';
                this.showAlert();
            } else if (this.$data.lastname === '' || this.$data.lastname == null) {
                this.$data.alertMessage = 'Please, fill "Last name" field';
                this.showAlert();
            } else if (this.$data.email === '' || this.$data.email == null) {
                this.$data.alertMessage = 'Please, fill "Email" field';
                this.showAlert();
            } else if (!this.$data.email.includes('@')) {
                this.$data.alertMessage = 'Email is incorrect';
                this.showAlert();
            } else if (this.$data.password === '' || this.$data.password == null) {
                this.$data.alertMessage = 'Please, fill "Password" field';
                this.showAlert();
            } else if (this.$data.confirmpassword === '' || this.$data.confirmpassword == null) {
                this.$data.alertMessage = 'Please, confirm password';
                this.showAlert();
            } else if (this.$data.confirmpassword !== this.$data.password) {
                this.$data.alertMessage = 'Passwords are not match';
                this.showAlert();
            } else {

                var newUser = {
                    'username': this.$data.username,
                    'firstName': this.$data.firstname,
                    'lastName': this.$data.lastname,
                    'email': this.$data.email,
                    'password': this.$data.password
                };

                AXIOS.post('/auth/signup', newUser)
                .then(response => {
                    console.log(response);
                    this.successAlert();
                }, error => {
                    this.$data.alertMessage = (error.response.data.message.length < 150) ? error.response.data.message : 'Request error. Please, report this error website owners'
                    this.showAlert();
                })
                 .catch(error => {
                    console.log(error);
                    this.$data.alertMessage = 'Request error. Please, report this error website owners';
                    this.showAlert();
                 });
            }
        },
        countDownChanged(dismissCountDown) {
            this.dismissCountDown = dismissCountDown
        },
        showAlert() {
            this.dismissCountDown = this.dismissSecs
        },
        successAlert() {
            this.username = '';
            this.firstname = '';
            this.lastname = '';
            this.email = '';
            this.password = '';
            this.confirmpassword = '';
            this.successfullyRegistered = true;
        }
    }
}
</script>

<style>
.login-form {
    margin-left: 38%;
    margin-top: 50px;
}
</style>