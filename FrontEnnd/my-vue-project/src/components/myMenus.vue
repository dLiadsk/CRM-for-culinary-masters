<template>
  <div class="wrapper">
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
        </div>
  <h1 class="text-center">Ваші меню</h1>
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
         <td><img :src="`http://localhost:8080/api/menus/${item.menuId}`" alt="err" style="width: 50px; height: 50px;"></td>
          <td>
            <a @click="showMenu(item.menuId)" title="Перегляд"><i class="fa-sharp fa-solid fa-eye fa-fw"></i></a>
            <a @click="updateMenu(item.menuId)" title="Редагувати" class="link-secondary"><i
                class="fa-regular fa-pen-to-square fa-fw"></i></a>
            <a @click="deleteMenu(item.menuId)" title="Видалити" class="link-danger"><i class="fa-sharp fa-solid fa-trash fa-fw"></i></a>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </main>
  </div>
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
            this.findAllUserMenus(this.user);
          })
          .catch(error => {
            console.error(error);  // Log the entire error object for debugging
          });
    },
    updateMenu(id){
      this.$router.push('/updateMenu/'+id)
    },
    findAllUserMenus(data){
      axios.post('http://localhost:8080/api/myMenus',data)
          .then(response => {
            this.userMenus = response.data;
          })
          .catch(error => {
            console.error(error);  // Log the entire error object for debugging
          });
    }
  },
  created(){
    // const token = localStorage.getItem('token');
    // if (token) {
    //   axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    //   axios.get('http://localhost:8080/api/user', {withCredentials: true})
    //       .then(response => {
    //         this.user = response.data.user;
    //         this.findAllUserMenus(response.data.user);
    //       })
    //       .catch(error => {
    //         console.error(error);  // Log the entire error object for debugging
    //       });
    // } else {
    //   alert("Ви не авторизовані")
    //   this.$router.push('/login');
    // }
  },
  mounted() {

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
