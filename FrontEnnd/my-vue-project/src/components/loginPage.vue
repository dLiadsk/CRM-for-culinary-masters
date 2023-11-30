<template>
  <div class="wrapper">
    <div class="container mt-5 p-5  border border-5">
    <div class="row justify-content-center">
        <div class="col-md-6 ">
            <h2 class="text-center mb-3">Авторизація</h2>
            <form @submit.prevent="login">
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fas fa-envelope fa-lg me-3 fa-fw"></i></span>
                    <input type="email" class="form-control" id="email" placeholder="Введіть електронну пошту" v-model="user.email">
                </div>
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fas fa-lock fa-lg me-3 fa-fw"></i></span>
                    <input type="password" class="form-control" id="password" placeholder="Введіть пароль" v-model="user.password">
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary btn-block">Авторизуватись</button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
<p class="text-center mt-3">Немає аккаунту? Реєстрація - <a class="text-decoration-none text-success" href="registration.html">тут</a></p>
  </template>
  
  <script>
import axios from 'axios';
  export default {
    data() {
      return {
        user: {
        email: "",
        password: "",
    },
    testdata: ''
      }
    },
      methods: {
          login() {
            
              axios.post('http://localhost:8080/api/newLogin', this.user, { withCredentials: true })
                  .then(response => {
                      axios.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;
                      const token = response.data.token;
                      localStorage.setItem('token', token);
                      this.$router.push('/profile');/////
                  })
                  .catch(error => {
                      alert('Error: ' + error.message);  // Display the error message
                      console.error(error);  // Log the entire error object for debugging
                  });
          },
  },
  mounted() {
  const token = localStorage.getItem('token');
  if (token) {
    alert("Ви уже авторизовані")
    this.$router.push('/profile');
  }

},
}
  </script>
  
  <style scoped>
  .wrapper {
  display: flex;
  flex-direction: column;
  min-height: 76.5vh;
}
</style>