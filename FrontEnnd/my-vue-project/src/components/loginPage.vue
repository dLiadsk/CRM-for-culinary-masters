<template>
    <div>
      <h2>Авторизація</h2>
      <form @submit.prevent="login">
        <label for="username">Ім'я користувача:</label>
        <input type="email" id="username" v-model="user.email" required>
  
        <label for="password">Пароль:</label>
        <input type="password" id="password" v-model="user.password" required>
  
        <button type="submit">Увійти</button>
      </form>
      <div v-if="loggedIn">
        <p>Ви успішно увійшли!</p>
        <button @click="logout">Вийти</button>
      </div>
    </div>
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
  