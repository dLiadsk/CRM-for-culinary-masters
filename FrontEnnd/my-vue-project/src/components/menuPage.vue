<template>
    <div id="app" class="container mt-5">
  <div class="row">
    <div v-for="(menu, index) in paginatedRecipes" :key="index" class="col-md-4">
      <div class="card recipe-card" @click="navigateToRecipeInfo()">
        <img :src="menu.image" class="card-img-top" alt="Menu Image">
        <div class="card-body">
          <h5 class="card-title text-center">{{ menu.title }}</h5>
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
        menus: [
      { title: 'МЕНЮ УКРАЇНСЬКОЇ КУХНІ',  image: require('@/assets/image.jpg') },
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
      return this.menus.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.menus.length / this.itemsPerPage);
    },
  },
  methods: {
    navigateToRecipeInfo(){
        this.$router.push('/menuInfo');
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