<template>
<div>
  <headerPage/>

  <main>
    <RouterView/>
  </main>

  <footerPage/>
</div>
</template>


<style>
@import '~bootstrap/dist/css/bootstrap.css';
@import '@fortawesome/fontawesome-free/css/all.css';
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>

<script>
import axios from 'axios';
import headerPage from './components/PageComponents/headerPage.vue';
import footerPage from './components/PageComponents/footerPage.vue';

export default {
  components: {
    headerPage,
    footerPage
  },
    data() {
      return {
      testdata: '',
      }
    },
    created() {
  // Встановіть токен при створенні компонента
  axios.post('http://localhost:8080/api/refresh',{},{withCredentials: true})
                  .then(response => {
                      localStorage.setItem('token',response.data.message);
                  })
                  .catch(error => {
                      alert('Error: ' + error.message);  // Display the error message
                      console.error(error);  // Log the entire error object for debugging
                  });
},
}
</script>