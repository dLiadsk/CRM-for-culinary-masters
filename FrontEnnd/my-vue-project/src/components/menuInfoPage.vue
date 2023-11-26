<template>
    <main class="d-flex justify-content-center align-items-center my-5 py-3">
      <div class="container">
        <h1 class="text-center mb-4 pb-4 border-bottom border-4">МЕНЮ УКРАЇНСЬКОЇ КУХНІ</h1>
        <p class="text-center px-2">
          <span class="mx-5"><i class="fa-solid fa-heart me-1 fa-fw fa-xl"></i> 125</span>
          <span class="mx-5"><i class="fa-solid fa-heart-crack me-1 fa-fw fa-xl"></i> 25</span>
        </p>
        <div class="card" style="width: 81rem;">
          <img class="card-img-top" src="@/assets/image.jpg" alt="Recipe Image" style="width: 100%; height: 500px;">
          <div class="card-body text-center">
            <div class="d-flex justify-content-center align-items-center">
              <div class="me-3">
                <h5 class="mb-3"><i class="fa-solid fa-user fa-fw fa-2xl"></i></h5>
                <small>Автор</small>
                <p><a class="text-decoration-none text-black" href="#">Username</a></p>
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
                <p>{{ complexity }}</p>
              </div>
            </div>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item" style="font-size: large">
              <h4 class="mb-0">Страви</h4>
              <ul class="mb-0 list-unstyled">
                <li v-for="(dish, index) in dishes" :key="index" class="ms-2 py-1">
                  <input class="form-check-input me-3" type="checkbox">
                  <a :href="`/dish/${index}`" class="text-decoration-none text-black">{{ dish }}</a>
                </li>
              </ul>
            </li>
            <li class="list-group-item">
              <h4 class="mb-0">Примітки: </h4>
              <ul class="mb-0 list-unstyled">
                <li class="ms-2 mb-2 py-1"><input class="form-check-input me-3" type="checkbox">Примітка</li>
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
  export default {
    data() {
      return {
        dishesCount: 7,
        complexity: "Середня",
        dishes: ["Перша страва", "Друга страва", "Третя страва"],
      };
    },
    methods: {
      copyLink() {
        // Implement your copy link logic here
      },
    },
  };
  </script>
  
  <style scoped>
  .divider {
    border-left: 2px solid #ccc; /* Лінія перегородки */
    height: 50px; /* Висота лінії */
    margin: 0 20px; /* Відступи від лівого та правого боку лінії */
  }
  </style>
  