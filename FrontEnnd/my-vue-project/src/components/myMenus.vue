<template>
  <div class="container py-5">
    <div class="row">
      <div class="col">
        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
          <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item active" aria-current="page"><a href="/profile">User Profile</a></li>
            <li class="breadcrumb-item active" aria-current="page"><a href="/createRecipe">Create Recipe</a></li>
            <li class="breadcrumb-item active" aria-current="page"><a href="/createMenu">Create Recipe</a></li>
            <li class="breadcrumb-item active" aria-current="page"><a href="/myRecipes">My Recipes</a></li>
            <li class="breadcrumb-item active" aria-current="page"><a href="/myMenus">My Menus</a></li>
            <li class="breadcrumb-item active" aria-current="page"><a @click="Logout">Logout</a></li>
          </ol>
        </nav>
      </div>
    </div>
  </div>
  <main class="container menu mb-5 py-5 border border-5 text-center items-align-center justify-content-center" >
    <div class="table-responsive small">
      <table class="table">
        <thead>
        <tr>
          <th scope="col">№</th>
          <th scope="col">Назва</th>
          <th scope="col">Фото</th>
          <th scope="col">Дії</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item, index) in userMenus" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ item.name }}</td>
          <td></td>
<!--          <td><img :src="`http://localhost:8080/api/${item.menuId}`" alt="err" style="width: 50px; height: 50px;"></td>-->
          <td>
            <a @click="showMenu(item.menuId)" title="Перегляд"><i class="fa-sharp fa-solid fa-eye"></i></a>
            <a @click="updateMenu(item.menuId)" title="Редагувати" class="link-secondary"><i
                class="fa-regular fa-pen-to-square"></i></a>
            <a @click="deleteMenu(item.menuId)" title="Видалити" class="link-danger"><i class="fa-sharp fa-solid fa-trash"></i></a>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <button class="btn btn-success text-white " @click="findAllUserMenus()">Завантажити меню</button>
  </main>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      userMenus: [],
      user: '',
      message: '',
    };
  },
  methods: {
    showMenu(id) {
      this.$router.push('/menuInfo/'+ id);
    },
    deleteMenu(id){
      axios.get('http://localhost:8080/api/deleteMenu/'+id)
          .then(response => {
            this.message = response.data;
            this.findAllUserMenus();
          })
          .catch(error => {
            console.error(error);  // Log the entire error object for debugging
          });
    },
    updateMenu(id){
      this.$router.push('/updateMenu/'+id)
    },
    findAllUserMenus(){
      axios.post('http://localhost:8080/api/myMenus',this.user)
          .then(response => {
            this.userMenus = response.data;
          })
          .catch(error => {
            console.error(error);  // Log the entire error object for debugging
          });
    }
  },
  created(){
    const token = localStorage.getItem('token');
    if (token) {
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
      axios.get('http://localhost:8080/api/user', {withCredentials: true})
          .then(response => {
            this.user = response.data.user;
          })
          .catch(error => {
            console.error(error);  // Log the entire error object for debugging
          });
    } else {
      alert("Ви не авторизовані")
      this.$router.push('/login');
    }
  },
  mounted() {

  },
};
</script>

<style scoped>
/* Add your styles here */
</style>
