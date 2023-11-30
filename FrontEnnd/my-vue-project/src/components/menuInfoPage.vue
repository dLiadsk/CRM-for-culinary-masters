<template>
  <main class="d-flex justify-content-center align-items-center my-5 py-3">
    <div class="container">
      <h1 class="text-center mb-4 pb-4 border-bottom border-4">{{ menu.name }}</h1>
      <p class="text-center px-2">
        <span class="mx-5" @click="saveLike('like')"><i class="fa-solid fa-heart me-1 fa-fw fa-xl"></i> {{ likes[0]
        }}</span>
        <span class="mx-5" @click="saveLike('dislike')"><i class="fa-solid fa-heart-crack me-1 fa-fw fa-xl"></i> {{
          likes[1] }}</span>
      </p>
      <div class="card" style="width: 81rem;">
        <img class="card-img-top" :src="imageSrc" alt="Recipe Image" style="width: 100%; height: 500px;">
        <div class="card-body text-center">
          <div class="d-flex justify-content-center align-items-center">
            <div class="me-3">
              <h5 class="mb-3"><i class="fa-solid fa-user fa-fw fa-2xl"></i></h5>
              <small>Автор</small>
              <p><a class="text-decoration-none text-black" :href="'/profile/' + username">{{ username }}</a></p>
            </div>
            <div class="divider"></div>
            <div class="me-3">
              <h5 class="mb-3"><i class="fa-solid fa-clock fa-fw fa-2xl"></i></h5>
              <small>Час приготування</small>
              <p>{{ menu.preparationTime }}</p>
            </div>
            <div class="divider"></div>
            <div class="">
              <h5 class="mb-3"><i class="fa-solid fa-utensils fa-2xl fa-fw"></i></h5>
              <small>Кількість страв</small>
              <p>{{ dishesCount }}</p>
            </div>
            <div class="divider"></div>
            <div class="">
              <h5 class="mb-3"><i class="fa-solid fa-star fa-2xl fa-fw"></i></h5>
              <small>Складність</small>
              <p>{{ menu.complexity }}</p>
            </div>
          </div>
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item" style="font-size: large">
            <h4 class="mb-0">Страви</h4>
            <ul class="mb-0 list-unstyled">
              <li v-for="(dish, index) in menu.recipes" :key="index" class="ms-2 py-1">
                <input class="form-check-input me-3" type="checkbox">
                <a :href="`/recipeInfo/${dish.recipeId}`" class="text-decoration-none text-black">{{ dish.name }}</a>
              </li>
            </ul>
          </li>
          <li class="list-group-item">
            <h4 class="mb-0">Примітки: </h4>
            <ul class="mb-0 list-unstyled">
              <li v-for="(note, index) in notes" :key="index" class="ms-2 mb-2 border-bottom border-2 py-2">
                <input class="form-check-input me-3" type="checkbox">{{ note }}
              </li>
            </ul>
          </li>
        </ul>
        <div class="card-body">
          <a href="#" class="card-link text-black text-decoration-none btn btn-info" @click="copyLink">Copy link</a>
        </div>
      </div>
    </div>
  </main>
</template>
  
<script>
import axios from 'axios';
export default {

  data() {
    return {
      notes: [],
      menu: '',
      dishesCount: 7,
      username: '',
      imageSrc: '',
      likes: [],
      sendData: {
        user: '',
        menu: '',
        reaction: '',
        disReaction: ''
      }
    };
  },
  methods: {
    saveLike(condition) {
      if (condition === 'like') {
        this.sendData.reaction = '1'
        this.sendData.disReaction = '0'
      } else {
        this.sendData.disReaction = '1'
        this.sendData.reaction = '0'
      }
      axios.post('http://localhost:8080/api/saveReaction/menus', this.sendData)
        .then(this.getAllMenusReaction())
        .catch(error => {
          console.error(error);  // Log the entire error object for debugging
        });
    },
    getAllMenusReaction() {
      axios.get('http://localhost:8080/api/getReactions/menus/' + this.$route.params.id)
        .then(response => {
          this.likes = response.data.split('#');
        }).catch(error => {
          console.error(error);
        })
    },
    getUser() {
      axios.get('http://localhost:8080/api/user', { withCredentials: true })
        .then(response => {
          this.sendData.user = response.data.user ;
        })
        .catch(error => {
          console.error(error);  // Log the entire error object for debugging
        });
    },

  },
  mounted() {
    axios.get('http://localhost:8080/api/menu/' + this.$route.params.id)
      .then(response => {

        this.menu = response.data;
        this.sendData.menu = response.data;

        this.dishesCount = this.menu.recipes.length;
        this.username = this.menu.user.username;
        this.imageSrc = `http://localhost:8080/api/menus/${this.menu.menuId}`

        // Розділити рядки на масиви за роздільником "#"
        this.notes = this.menu.notes.split("#");
      })
      .catch(error => {
        console.error(error);  // Log the entire error object for debugging
      });
    const token = localStorage.getItem('token');
    if (token) {
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
      this.getUser();
    } else {
      alert("Ви не авторизовані")
      this.$router.push('/login');
    }
    this.getAllMenusReaction();
  }
};
</script>
  
<style scoped>
.divider {
  border-left: 2px solid #ccc;
  /* Лінія перегородки */
  height: 50px;
  /* Висота лінії */
  margin: 0 20px;
  /* Відступи від лівого та правого боку лінії */
}</style>
  