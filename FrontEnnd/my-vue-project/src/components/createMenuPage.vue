<template>
  <div>
    <!-- Форма для введення нового рецепту -->
    <div>
      <div class="container py-5">
        <div class="row">
          <div class="col">
            <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item active" aria-current="page"><a href="/profile">User Profile</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/createRecipe">Create Recipe</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/createMenu">Create Menu</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/myRecipes">My Recipes</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/myMenus">My Menus</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a @click="Logout">Logout</a></li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
      <form @submit.prevent="addMenu">
        <div>
          <main class="d-flex justify-content-center align-items-center mb-5 pb-3">
            <div class="container">
              <h2 class="text-center mb-4 pb-4 border-bottom border-4">
                <input v-model="newMenu.name" id="recipeName" required style="width: 1200px;"
                       placeholder="Введіть назву меню" @input="clearError('name')"/>
                <div class="invalid-feedback" v-bind:class="{'d-block' : !nameValid }">Введіть назву меню
                </div>
              </h2>
              <div class="card" style="width: 81rem;">
                <label for="recipeImage" class="card-img-top text-center" style="position: relative; cursor: pointer;">
                  <input type="file" ref="fileInput" accept="image/*" @change="handleImageUpload" id="recipeImage"
                         style="position: absolute; top: 0; left: 0; opacity: 0; cursor: pointer;"/>
                  <img v-if="newMenu.image" :src="imageToShow" alt="Recipe Image"
                       style="width: 100%; height: 500px;">
                  <small v-if="newMenu.image">Щоб змінити фото натисніть на нього</small>
                  <div v-if="!newMenu.image"
                       class="overlay text-center d-flex align-items-center justify-content-center"
                       style="width: 250px; height: 250px; text-align: center;">
                      <span>
                        <i class="fa-solid fa-download fa-2xl"></i>
                        <div class="mt-2">
                          <small>Upload Photo</small>
                        </div>
                      </span>
                  </div>
                </label>
                <div class="card-body text-center">
                  <div class="d-flex justify-content-center align-items-center mt-3">
                    <div class="mx-3">
                      <h5 class="mb-3"><i class="fa-solid fa-user fa-fw fa-2xl"></i></h5>
                      <small>Автор</small>
                      <p><a :href="authorLink" class="text-decoration-none text-black form-control">{{ author }}</a></p>
                    </div>
                    <div class="divider"></div>
                    <div class="mx-3">
                      <h5 class="mb-3"><i class="fa-solid fa-clock fa-fw fa-2xl"></i></h5>
                      <small style="padding-left: 10px">Час приготування</small>
                      <p class="ms-3"><input v-model="newMenu.preparationTime" class="form-control" type="time" required
                                             style="width: 100px;"/></p>
                    </div>
                    <div class="divider"></div>
                    <div class="mx-3">
                      <h5 class="mb-3"><i class="fa-solid fa-star fa-2xl"></i></h5>
                      <small>Складність</small>
                      <p>
                        <select v-model="newMenu.complexity" required style="width: 100px;" class="form-control">
                          <option v-for="option in complexityOptions" :key="option.value" :value="option.value">
                            {{ option.label }}
                          </option>
                        </select>
                      </p>
                    </div>
                  </div>
                </div>
                <ul class="list-group list-group-flush">
                  <li class="list-group-item" style="font-size: large">
                    <h4 class="mb-2">
                      <div class="invalid-feedback" v-bind:class="{'d-block' : !recipesValid }">Оберість хоча б один рецепт
                      </div>
                      <div class="invalid-feedback" v-bind:class="{'d-block' : !copyRecipesValid }">Оберіть рецепти, які не повторюються
                      </div>
                      Страви:
                    </h4>
                    <ul class="mb-0 list-unstyled">
                      <li v-for="(ingredient, index) in newMenu.recipes" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <select v-model="newMenu.recipes[index]" class="form-control" aria-label=""
                                  aria-describedby="inputGroup-sizing-sm" @change="clearError('recipes'); clearError('copyRecipes')">
                            <option value="" disabled selected>Select an recipe</option>
                            <option v-for="option in availableRecipes" :key="option" :value="option">{{
                                option.name
                              }}
                            </option>
                          </select>
                          <button type="button" class="btn" @click="removeRecipe(index)"><i
                              class="fa-solid fa-trash"></i></button>
                        </div>
                      </li>
                      <button type="button" class="btn btn-light" @click="addRecipe">Додати рецепт</button>
                    </ul>

                  </li>
                  <li class="list-group-item">
                    <h4 class="mb-0">Примітки: </h4>
                    <ul class="mb-0 list-unstyled">
                      <li v-for="(note, index) in mass_notes" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <input v-model="mass_notes[index]" placeholder="Крок приготування" type="text"
                                 class="form-control" aria-label="" aria-describedby="inputGroup-sizing-sm">
                          <button type="button" class="btn" @click="removeNote(index)"><i
                              class="fa-solid fa-trash"></i></button>
                        </div>

                      </li>
                      <button type="button" class="btn btn-light" @click="addNote">Додати примітку</button>
                    </ul>
                  </li>
                </ul>
                <div class="card-body align-items-center justify-contetn-center text-center">
                  <button class="card-link text-white text-decoration-none btn btn-success" type="submit">Створити
                    меню
                  </button>
                </div>
              </div>
            </div>
          </main>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      nameValid: true,
      recipesValid: true,
      copyRecipesValid: true,
      availableRecipes: [],
      mass_notes: [],
      newMenu: {
        user: '',
        name: '',
        notes: '',
        image: null,
        preparationTime: '00:00',
        complexity: '',
        recipes: [],
      },
      imageToShow: '',
      author: "username",

      complexityOptions: [
        {value: 'Легко', label: 'Легко'},
        {value: 'Середньо', label: 'Середньо'},
        {value: 'Важко', label: 'Важко'},
      ],


    };
  },
  methods: {
    getUser() {
      axios.get('http://localhost:8080/api/user', {withCredentials: true})
          .then(response => {
            this.author = response.data.user.username;
            this.newMenu.user = response.data.user;
          })
          .catch(error => {
            alert(error);
            // this.$router.push('/login');
            localStorage.removeItem('token');  // Display the error message
            console.error(error);  // Log the entire error object for debugging
          });
    },
    getRecipes() {
      axios.get('http://localhost:8080/api/recipes')
          .then(response => {
            this.availableRecipes = response.data;
          })
          .catch(error => {
            alert(error);
          });
    },
    addRecipe() {
      this.newMenu.recipes.push(''); // Додай цей рядок
    },
    removeRecipe(index) {
      this.newMenu.recipes.splice(index, 1);
    },

    addNote() {
      this.mass_notes.push('');
    },
    removeNote(index) {
      this.mass_notes.splice(index, 1);
    },
    clearError(fieldName) {
      // Метод для приховання помилок при виправленні полів
      this[fieldName + "Valid"] = true;
    },
    validateName() {
      return !(this.newMenu.name.trim() === '');
    },
    validateRecipes() {
      return (this.newMenu.recipes.length > 0);
    },
    validateCopyRecipes() {

      let set = new Set(this.newMenu.recipes)
      return (set.size === this.newMenu.recipes.length);
    },
    addMenu() {
      this.newMenu.recipes = this.newMenu.recipes.filter(recipe => recipe !== '');

      this.nameValid = this.validateName()
      this.recipesValid = this.validateRecipes()
      this.copyRecipesValid = this.validateCopyRecipes()


      if (this.nameValid && this.recipesValid && this.copyRecipesValid) {

        this.mass_notes = this.mass_notes.filter(note => note.trim() !== '');

        this.newMenu.notes = this.mass_notes.join("#")


        // this.uploadImage();
        axios.post('http://localhost:8080/api/createMenu', this.newMenu)
            .then(response => {
              alert("Success" + response.data);
            })
            .catch(error => {
              alert('Error: ' + error.message);  // Display the error message
              console.error(error);  // Log the entire error object for debugging
            });
        this.$router.push("/myMenus")
      }
      if (!this.recipesValid){
        this.newMenu.recipes.push('')
      }
    },
    handleImageUpload(event) {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('file', file);

      axios.post('http://localhost:8080/api/upload', formData)
          .then(response => {
            this.newMenu.image = response.data;
          })
          .catch(error => {
            console.error('Error uploading file:', error);
          });
      if (file) {
        const reader = new FileReader();
        reader.onload = () => {
          this.imageToShow = reader.result;
        };
        reader.readAsDataURL(file);
      }
    },
  },
  mounted() {

    const token = localStorage.getItem('token');
    if (token) {
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
      this.getUser();
    } else {
      alert("Ви не авторизовані")
      this.$router.push('/login');
    }
    this.getRecipes();


  }
};
</script>


<style scoped>
.card .overlay span {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.divider {
  border-left: 2px solid #ccc;
  height: 50px;
  margin: 0 20px;
}
</style>