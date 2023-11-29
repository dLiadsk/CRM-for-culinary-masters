<template>
  <div id="app" class="container mt-5">
    <div class="row">
      <div v-for="(recipe, index) in paginatedRecipes" :key="index" class="col-md-4">
        <div class="card recipe-card" @click="navigateToRecipeInfo(recipe.id)">
          <img :src="recipe.image" class="card-img-top" alt="Recipe Image" style="width: 415px; height: 200px;">
          <div class="card-body">
            <h5 class="card-title text-center">{{ recipe.title }}</h5>
          </div>
        </div>
      </div>
    </div>

    <nav class="align-items-center justify-content-center d-flex">
      <ul class="pagination">
        <li class="page-item" :class="{ 'disabled': currentPage === 1 }">
          <a class="page-link" href="#" @click="prevPage">Previous</a>
        </li>

        <li v-for="page in totalPages" :key="page" class="page-item" :class="{ 'active': currentPage === page }">
          <a class="page-link" href="#" @click="goToPage(page)">{{ page }}</a>
        </li>

        <li class="page-item" :class="{ 'disabled': currentPage === totalPages }">
          <a class="page-link" href="#" @click="nextPage">Next</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<style>
.recipe-card {
  margin-bottom: 20px;
}
</style>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      recipes: [
    
        // ... add more recipes
      ],
      recipesRaw: [],
      itemsPerPage: 6,
      currentPage: 1,
    };
  },
  computed: {
    paginatedRecipes() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.recipes.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.recipes.length / this.itemsPerPage);
    },
  },
  methods: {
    navigateToRecipeInfo(id) {
      this.$router.push('/recipeInfo/'+ id);
    },
    goToPage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.currentPage = page;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage -= 1;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage += 1;
      }
    },
    findAllRecipes() {
      axios.get('http://localhost:8080/api/allRecipes')
        .then(response => {
          this.recipesRaw = response.data;
          this.recipes = this.recipesRaw.map(recipe => ({
            id: recipe.recipeId,
            title: recipe.name,
            image: `http://localhost:8080/api/${recipe.recipeId}`, // Assuming you have an 'id' property
            }));
                  })
        .catch(error => {
          console.error(error);  // Log the entire error object for debugging
        });
    },
  },
  mounted() {
    this.findAllRecipes()
  }
};
</script>