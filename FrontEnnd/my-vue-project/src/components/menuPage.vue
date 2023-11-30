<template>
  <div id="app" class="container mt-5">
    <div class="row">
      <div v-for="(menu, index) in paginatedMenus" :key="index" class="col-md-4">
        <div class="card recipe-card" @click="navigateToMenuInfo(menu.id)">
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
import axios from 'axios';

export default {
  data() {
    return {
      menus: [],
      itemsPerPage: 6,
      currentPage: 1,
    };
  },
  computed: {
    paginatedMenus() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.menus.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.menus.length / this.itemsPerPage);
    },
  },
  methods: {
    navigateToMenuInfo(id) {
      this.$router.push('/menuInfo/' + id);
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
    findAllMenus() {
      axios.get('http://localhost:8080/api/menus')
          .then(response => {
            this.menusRaw = response.data;
            this.menus = this.menusRaw.map(menus => ({
              id: menus.menuId,
              title: menus.name,
              image: `http://localhost:8080/api/${menus.menuId}`, // Assuming you have an 'id' property
            }));
          })
          .catch(error => {
            console.error(error);  // Log the entire error object for debugging
          });
    },
  },
  mounted() {
    this.findAllMenus()
  },
};
</script>