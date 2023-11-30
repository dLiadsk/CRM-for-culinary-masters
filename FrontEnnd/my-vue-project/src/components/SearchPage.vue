<template>
    <div id="app" class="container mt-5 wrapper">
      <!-- Search Bar -->
      <div class="row mb-3">
        <div class="col-md-6 offset-md-3">
          <input v-model="searchTerm" class="form-control" placeholder="Search recipes and menus">
        </div>
      </div>
  
      <!-- Recipes Section -->
      <h2>Рецепти</h2>
      <div class="row">
        <div v-for="(recipe, index) in paginatedItems(filteredRecipes)" :key="index" class="col-md-4">
          <div class="card recipe-card" @click="navigateToInfo(recipe.id, 'recipe')">
            <img :src="recipe.image" class="card-img-top" alt="Recipe Image" style="width: 415px; height: 200px;">
            <div class="card-body">
              <h5 class="card-title text-center">{{ recipe.title }}</h5>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Menus Section -->
      <h2 class="mt-5">Меню</h2>
      <div class="row">
        <div v-for="(menu, index) in paginatedItems(filteredMenus)" :key="index" class="col-md-4">
          <div class="card recipe-card" @click="navigateToInfo(menu.id, 'menu')">
            <img :src="menu.image" class="card-img-top" alt="Menu Image" style="width: 415px; height: 200px;">
            <div class="card-body">
              <h5 class="card-title text-center">{{ menu.title }}</h5>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Pagination -->
      <nav class="align-items-center justify-content-center d-flex mt-5">
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
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        recipes: [],
        menus: [],
        itemsPerPage: 3,
        currentPage: 1,
        searchTerm: '',
      };
    },
    computed: {
    allItems() {
      return [...this.recipes, ...this.menus];
    },
    filteredRecipes() {
      return this.recipes.filter(recipe => recipe.title.toLowerCase().includes(this.searchTerm.toLowerCase()));
    },
    filteredMenus() {
      return this.menus.filter(menu => menu.title.toLowerCase().includes(this.searchTerm.toLowerCase()));
    },
    filteredItems() {
      return this.searchTerm ? [...this.filteredRecipes, ...this.filteredMenus] : this.allItems;
    },
    totalPages() {
      return Math.ceil(this.filteredItems.length / this.itemsPerPage);
    },
  },
    methods: {
      navigateToInfo(id, type) {
        const route = type === 'recipe' ? '/recipeInfo/' : '/menuInfo/';
        this.$router.push(route + id);
      },
      paginatedItems(items) {
        const startIndex = (this.currentPage - 1) * this.itemsPerPage;
        const endIndex = startIndex + this.itemsPerPage;
        return items.slice(startIndex, endIndex);
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
        axios.get('http://localhost:8080/api/recipes')
          .then(response => {
            this.recipes = response.data.map(recipe => ({
              id: recipe.recipeId,
              title: recipe.name,
              image: `http://localhost:8080/api/${recipe.recipeId}`,
              type: 'recipe',
            }));
          })
          .catch(error => {
            console.error(error);
          });
      },
      findAllMenus() {
        axios.get('http://localhost:8080/api/menus')
          .then(response => {
            this.menus = response.data.map(menu => ({
              id: menu.menuId,
              title: menu.name,
              image: `http://localhost:8080/api/menus/${menu.menuId}`,
              type: 'menu',
            }));
          })
          .catch(error => {
            console.error(error);
          });
      },
    },
    mounted() {
      this.findAllRecipes();
      this.findAllMenus();
    },
  };
  </script>
  
  <style>
    .recipe-card {
      margin-bottom: 20px;
    }
  </style>
  
  <style scoped>
    .wrapper {
      display: flex;
      flex-direction: column;
      min-height: 76.5vh;
    }
  </style>
  