<template>
    <div div="signin">
        <div class="login-form">
            <b-card
              title="Login"
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
                 <b-form-input type="text" placeholder="Username" v-model="username" />
                 <div class="mt-2"></div>

                 <b-form-input type="password" placeholder="Password" v-model="password" />
                 <div class="mt-2"></div>
              </div>

              <b-button v-on:click="validateCaptcha" variant="primary">Login</b-button>

              <hr class="my-4" />

              <vue-recaptcha
                    ref="recaptcha"
                    size="invisible"
                    :sitekey="sitekey"
                    @verify="onCapthcaVerified"
                    @expired="onCaptchaExpired"
                />

              <b-button variant="link">Forget password?</b-button>
            </b-card>
          </div>
    </div>
</template>

<script>
import {AXIOS} from './http-common'
import VueRecaptcha from 'vue-recaptcha'

export default {
    name: 'SignIn',
    components: { VueRecaptcha },
    data() {
          return {
          username: '',
          password: '',
          dismissSecs: 5,
          dismissCountDown: 0,
          alertMessage: 'Request error',
          sitekey: '6LfDWa4UAAAAAG4lOwUUwqadaE0c9mrGRl0HYb5t'
      }
    },
    methods: {
      login() {
        AXIOS.post(`/auth/signin`, {'username': this.$data.username, 'password': this.$data.password})
          .then(response => {
            this.$store.dispatch('login', {'token': response.data.accessToken, 'roles': response.data.authorities, 'username': response.data.username});
            this.$router.push('/home')
          }, error => {
            this.$data.alertMessage = (error.response.data.message.length < 150) ? error.response.data.message : 'Request error. Please, report this error website owners';
            console.log(error)
          })
          .catch(e => {
            console.log(e);
            this.showAlert();
          })
      },
      countDownChanged(dismissCountDown) {
          this.dismissCountDown = dismissCountDown
      },
      showAlert() {
          this.dismissCountDown = this.dismissSecs
      },
      validateCaptcha() {
            this.$refs.recaptcha.execute()
        },
        onCapthcaVerified(recaptchaToken) {
            AXIOS.post(`/auth/signin`, {'username': this.$data.username, 'password': this.$data.password, 'recapctha_token': recaptchaToken})
            .then(response => {
              this.$store.dispatch('login', {'token': response.data.accessToken, 'roles': response.data.authorities, 'username': response.data.username});
              this.$router.push('/home')
            }, error => {
              this.showAlert(error.response.data.message);
            })
            .catch(e => {
              console.log(e);
              this.showAlert('Server error. Please, report this error website owners');
            })
        },
        onCaptchaExpired() {
            this.$refs.recaptcha.reset()
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