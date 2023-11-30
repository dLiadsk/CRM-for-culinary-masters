<template>
  <div>
    <main class="d-flex justify-content-center align-items-center my-5 py-3">
      <div class="container">
        <h1 class="text-center mb-4 pb-4 border-bottom border-4">{{ name }}</h1>
        <p class="text-center px-2">
          <span class="mx-5" @click="saveLike('like')"><i class="fa-solid fa-heart me-1 fa-fw fa-xl"></i> {{ likes[0]
          }}</span>
          <span class="mx-5" @click="saveLike('dislike')"><i class="fa-solid fa-heart-crack me-1 fa-fw fa-xl"></i> {{
            likes[1] }}</span>
        </p>
        <div class="card" style="width: 81rem;">
          <img class="card-img-top" :src="imageSrc" alt="Recipe Image" style="width: 100%; height: 500px;">
          <div class="card-body text-center">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <div class="me-3">
                <h5 class="mb-3"><i class="fa-solid fa-user fa-fw fa-2xl"></i></h5>
                <small>Автор</small>
                <p><a :href="'/profile/' + author.username" class="text-decoration-none text-black">{{ author.username
                }}</a></p>
              </div>
              <div class="divider"></div>
              <div class="me-3">
                <h5 class="mb-3"><i class="fa-solid fa-clock fa-fw fa-2xl"></i></h5>
                <small>Час приготування</small>
                <p>{{ preparationTime }}</p>
              </div>
              <div class="divider"></div>
              <div class="me-3">
                <h5 class="mb-3"><i class="fa-solid fa-star fa-2xl"></i></h5>
                <small>Складність</small>
                <p>{{ complexity }}</p>
              </div>
            </div>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item" style="font-size: large">
              <h4 class="mb-0">Інгрідієнти:</h4>
              <ul class="mb-0 list-unstyled">
                <li v-for="(ingredient, index) in ingredients" :key="index" class="ms-2 py-1">
                  <input class="form-check-input me-3" type="checkbox">{{ ingredient }}
                </li>
              </ul>
            </li>
            <li class="list-group-item">
              <h4 class="mb-0">Спосіб приготування: </h4>
              <ul class="mb-0 list-unstyled">
                <li v-for="(step, index) in preparationSteps" :key="index" class="ms-2 mb-2 border-bottom border-2 py-2">
                  <input class="form-check-input me-3" type="checkbox">{{ step }}
                </li>
              </ul>
            </li>
            <li class="list-group-item">
              <h4 class="mb-0">Примітки: </h4>
              <ul class="mb-0 list-unstyled">
                <li class="ms-2 mb-2 py-1">
                  <input class="form-check-input me-3" type="checkbox">Примітка
                </li>
              </ul>
            </li>
          </ul>
          <div class="card-body">
            <a href="#" class="card-link text-black text-decoration-none btn btn-info">Copy link</a>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      name: '',
      imageSrc: '',
      author: "Username",
      authorLink: "#",
      preparationTime: "15 хвилин",
      complexity: "Середня",
      ingredients: [],
      preparationSteps: [],
      recipe: [],
      likeCount: '125',
      dislikeCount: '29',
      likes: [],
      sendData: {
        user: '',
        recipe: '',
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
      axios.post('http://localhost:8080/api/saveReaction', this.sendData)
        .then(this.getAllRecipe())
        .catch(error => {
          console.error(error);  // Log the entire error object for debugging
        });
    },
    getAllRecipe() {
      axios.get('http://localhost:8080/api/getReactions/' + this.$route.params.id)
        .then(response => {
          this.likes = response.data.split('#');
        }).catch(error => {
          console.error(error);
        })
    }
  },
  mounted() {
    axios.get('http://localhost:8080/api/recipes/' + this.$route.params.id)
      .then(response => {
        this.recipe = response.data;
        this.sendData.recipe = response.data;
        this.sendData.user = this.recipe.user;
        this.name = this.recipe.name;
        this.imageSrc = `http://localhost:8080/api/${this.recipe.recipeId}`;
        this.author = this.recipe.user;
        this.authorLink = '#';
        this.preparationTime = this.recipe.preparationTime;

        // Розділити рядки на масиви за роздільником "#"
        this.ingredients = this.recipe.ingredients.split("#");
        this.notes = this.recipe.notes.split("#");
        this.preparationSteps = this.recipe.steps.split("#");
      })
      .catch(error => {
        console.error(error);  // Log the entire error object for debugging
      });
      this.getAllRecipe();
  }
};
</script>

<style scoped>
.divider {
  border-left: 2px solid #ccc;
  height: 50px;
  margin: 0 20px;
}</style>
