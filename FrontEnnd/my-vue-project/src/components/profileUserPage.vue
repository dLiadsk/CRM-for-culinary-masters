<template>
    <main>
        <section style="background-color: #eee;" class="wrapper">
            <div class="container py-5">
            
                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4 mb-md-0">
                            <div class="card-body text-center">
                                <img :src="avatarSrc" alt="Photo" class="rounded-circle img-fluid" style="width: 150px;">
                                <h5 class="my-3">{{ user.firstName + ' ' + user.lastName }}</h5>
                                <div class="d-flex justify-content-center mb-2">
                                    <button type="button" class="btn btn-primary" @click="shareLink">Share link</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4 mb-md-0">
                            <div class="card-body">
                                <h2 class="mb-2">Recipes:</h2>
                                <ul class="list-group list-group-flush list-unstyled">
                                    <li v-for="(recipe, index) in recipes" :key="index" class="ms-2 py-1">
                                        <input class="form-check-input me-3" type="checkbox" disabled>
                                        <a :href="`/recipeInfo/${recipe.recipeId}`" class="text-decoration-none text-black">{{
                                            recipe.name }}</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4 mb-md-0">
                            <div class="card-body">
                                <h2 class="mb-2">Menu's:</h2>
                                <ul class="list-group list-group-flush list-unstyled">
                                    <li v-for="(menu, index) in menus" :key="index" class="ms-2 py-1">
                                        <input class="form-check-input me-3" type="checkbox" disabled>
                                        <a :href="`/menuInfo/${menu.menuId}`" class="text-decoration-none text-black">{{ menu.name
                                        }}</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
</template>
    
<script>
import axios from 'axios';

export default {
    data() {
        return {
            avatarSrc: '',
            user: '',
            recipes: [],
            menus: [],
            altAvatar: "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
        };
    },
    methods: {
        shareLink() {
      const pageURL = window.location.href;

      navigator.clipboard.writeText(pageURL)
        .then(() => {
          alert('Page URL copied to clipboard!');
        })
        .catch((error) => {
          console.error('Error copying to clipboard:', error);
        });
    },

    },
    mounted() {
        axios.get('http://localhost:8080/api/profile/' + this.$route.params.username)
            .then(response => {
                this.user = response.data
                this.avatarSrc = `http://localhost:8080/api/userPhoto/${response.data.username}`;
                axios.post('http://localhost:8080/api/myRecipes', response.data)
                    .then(response => {
                        this.recipes = response.data;
                    })
                    .catch(error => {
                        console.error(error);  // Log the entire error object for debugging
                    });
                    axios.post('http://localhost:8080/api/myMenus', response.data)
                    .then(response => {
                        this.menus = response.data;
                    })
                    .catch(error => {
                        console.error(error);  // Log the entire error object for debugging
                    });
            })
            .catch(error => {
                console.error(error);  // Log the entire error object for debugging
            });
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
    