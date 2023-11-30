<template>
  <main>
    <section style="background-color: #eee;" class="wrapper">
      <div class="container py-5">
        <div class="row">
          <div class="col">
            <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item active" aria-current="page"><a href="/profile">User Profile</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/createRecipe">Create Recipe</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/createMenu">Create Menu</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/myRecipes">My Recipes</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/myMenus">My Menus</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a @click="Logout">Logout</a></li>
              </ol>
            </nav>
          </div>
        </div>


        <div class="row">
          <div class="col-lg-4">
            <div class="card mb-4 mb-md-0">
              <div class="card-body text-center">
                <img :src="avatarSrc" alt="avatar" class="rounded-circle img-fluid" style="width: 150px;">
                <h5 class="my-3">{{ username }}</h5>
                <div class="d-flex justify-content-center mb-2">
                  <button type="button" class="btn btn-primary" @click="shareLink">Share link</button>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 mb-md-0">
              <div class="card-body">
                <h2 class="mb-2">Recipes:</h2>
                <ul class="list-group list-group-flush list-unstyled">
                  <li v-for="(recipe, index) in recipes" :key="index" class="ms-2 py-1">
                    <input class="form-check-input me-3" type="checkbox" disabled>
                    <a :href="`/recipeInfo/${recipe.recipeId}`" class="text-decoration-none text-black">{{ recipe.name }}</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card mb-4 mb-md-0">
              <div class="card-body">
                <h2 class="mb-2">Menu's:</h2>
                <ul class="list-group list-group-flush list-unstyled">
                  <li v-for="(menu, index) in menus" :key="index" class="ms-2 py-1">
                    <input class="form-check-input me-3" type="checkbox" disabled>
                    <a :href="`/menuInfo/${menu.menuId}`" class="text-decoration-none text-black">{{ menu.name }}</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>
  
<script>
import axios from 'axios';
export default {
  data() {
    return {
      avatarSrc: "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp",
      username: "",
      recipes: [],
      menus: [],
    };
  },
  methods: {
    getUser() {
      axios.get('http://localhost:8080/api/user', { withCredentials: true })
        .then(response => {
          this.username = response.data.user.firstName + " " + response.data.user.lastName;
          this.user = response.data
                axios.post('http://localhost:8080/api/myRecipes', response.data.user)
                    .then(response => {
                        this.recipes = response.data;
                    })
                    .catch(error => {
                        console.error(error);  // Log the entire error object for debugging
                    });
                    axios.post('http://localhost:8080/api/myMenus', response.data.user)
                    .then(response => {
                        this.menus = response.data;
                    })
                    .catch(error => {
                        console.error(error);  // Log the entire error object for debugging
                    });
        })
        .catch(error => {
          console.error(error);  // Log the entire error object for debugging
        });
    },
    Logout() {
      axios.post('http://localhost:8080/api/logout')
        .then(response => {
          alert("Success" + response.data.message, { withCredentials: true })
          localStorage.removeItem('token');
          this.$router.push('/login');
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
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
      this.getUser();
    } else {
      alert("Ви не авторизовані")
      this.$router.push('/login');
    }
  },
};

</script>
  
<style scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  min-height: 76.5vh;
}
</style>
  