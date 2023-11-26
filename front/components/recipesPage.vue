<template>
    <div id="app" class="container mt-5">
  <div class="row">
    <div v-for="(recipe, index) in paginatedRecipes" :key="index" class="col-md-4">
      <div class="card recipe-card" @click="navigateToRecipeInfo()">
        <img :src="recipe.image" class="card-img-top" alt="Recipe Image">
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
export default {
  data() {
    return {
        recipes: [
      { title: 'ПУХКІ ТА СОКОВИТІ: ПИРІЖКИ З КАПУСТОЮ В ДУХОВЦІ ВІД ЄВГЕНА КЛОПОТЕНКА',  image: require('@/assets/5.jpg') },
      // ... add more recipes
    ],
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
    navigateToRecipeInfo(){
        this.$router.push('/recipeInfo');
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
  },
};
</script>