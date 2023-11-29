<template>
    <div>
      <img :src="photoUrl" alt="Photo" />
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        photoUrl: '',
      };
    },
    mounted() {
      this.loadPhoto();
    },
    methods: {
      loadPhoto() {
        const photoId = 5; // Замініть на ідентифікатор фото, яке ви хочете відобразити
        this.photoUrl = `http://localhost:8080/api/${photoId}`;
      },
    },
  };
  </script>
  