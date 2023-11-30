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
      <form @submit.prevent="addRecipe">
        <div>
          <main class="d-flex justify-content-center align-items-center mb-5 pb-3">
            <div class="container">
              <h2 class="text-center mb-4 pb-4 border-bottom border-4">
                <input v-model="newMenu.name" id="recipeName" required style="width: 1200px;" placeholder="Введіть назву меню"/>
              </h2>
              <div class="card" >
                <label for="recipeImage"  class="card-img-top text-center" style="position: relative; cursor: pointer;">
                  <input type="file"  ref="fileInput" accept="image/*" @change="handleImageUpload" id="recipeImage"
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
                    <h4 class="mb-2">Страви:</h4>
                    <ul class="mb-0 list-unstyled">
                      <li v-for="(ingredient, index) in newMenu.recipes" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <select v-model="newMenu.recipes[index]" class="form-control" aria-label=""
                                  aria-describedby="inputGroup-sizing-sm">
                            <option value="" disabled selected>Select an recipe</option>
                            <option v-for="option in availableRecipes" :key="option" :value="option">{{
                                option.name
                              }}
                            </option>
                          </select>
                          <button type="button" class="btn" @click="removeIngredient(index)"><i
                              class="fa-solid fa-trash"></i></button>
                        </div>
                      </li>
                      <button type="button" class="btn btn-light" @click="addIngredient">Додати рецепт</button>
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
        {value: 'easy', label: 'Легко'},
        {value: 'medium', label: 'Середньо'},
        {value: 'hard', label: 'Важко'},
      ],


    };
  },
  methods: {
    getUser() {
      axios.get('http://localhost:8080/api/user', { withCredentials: true })
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
            // this.newRecipe.user = response.data.user;
    })
    .catch(error => {
      alert(error);
    });
    },
    addIngredient() {
      this.newMenu.recipes.push(''); // Додай цей рядок
    },
    removeIngredient(index) {
      this.newMenu.recipes.splice(index, 1);
    },
    addNote() {
      this.mass_notes.push('');
    },
    removeNote(index) {
      this.mass_notes.splice(index, 1);
    },
    addRecipe() {
      // this.recipes.push({...this.newMenu});

      this.newMenu.recipes = this.newMenu.recipes.filter(recipe => recipe !== '');
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

@media (max-width: 767px) {
  .d-flex {
    flex-direction: column; /* Змінюємо напрямок на колонки */
    align-items: center; /* Вирівнюємо елементи по центру */
  }
  .divider {
    display: none; /* Ховаємо розділювачі в мобільній версії (або застосовуйте стилі за потребою) */
  }
  .mx-3 {
    margin-bottom: 20px; /* Додаємо вертикальний відступ між блоками */
  }
}
.divider {
  border-left: 2px solid #ccc;
  height: 50px;
  margin: 0 20px;
}
</style>