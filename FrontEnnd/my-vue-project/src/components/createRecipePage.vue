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
                <input v-model="newRecipe.name" id="recipeName" required style="width: 1200px;"
                       placeholder="Введіть назве рецепта"/>
              </h2>
              <div class="card" style="width: 81rem;">
                <label for="recipeImage" class="card-img-top text-center" style="position: relative; cursor: pointer;">
                  <input type="file"  ref="fileInput" accept="image/*" @change="handleImageUpload" id="recipeImage"
                         style="position: absolute; top: 0; left: 0; opacity: 0; cursor: pointer;"/>
                  <img v-if="newRecipe.image" :src="imageToShow" alt="Recipe Image"
                       style="width: 100%; height: 500px;">
                  <small v-if="newRecipe.image">Щоб змінити фото натисніть на нього</small>
                  <div v-if="!newRecipe.image"
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
                      <p class="ms-3"><input v-model="newRecipe.preparationTime" class="form-control" type="time" required
                                             style="width: 100px;"/></p>
                    </div>
                    <div class="divider"></div>
                    <div class="mx-3">
                      <h5 class="mb-3"><i class="fa-solid fa-star fa-2xl"></i></h5>
                      <small>Складність</small>
                      <p>
                        <select v-model="newRecipe.complexity" required style="width: 100px;" class="form-control">
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
                    <h4 class="mb-2">Інгрідієнти:</h4>
                    <ul class="mb-0 list-unstyled">
                      <li v-for="(ingredient, index) in ingredients_mass" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <input v-model="ingredients_mass[index]" placeholder="Інгредієнт" type="text"
                                 class="form-control" aria-label="" aria-describedby="inputGroup-sizing-sm">
                          <button type="button" class="btn" @click="removeIngredient(index)"><i
                              class="fa-solid fa-trash"></i></button>
                        </div>
                      </li>
                      <button type="button" class="btn btn-light" @click="addIngredient">Додати інгредієнт</button>
                    </ul>
                  </li>
                  <li class="list-group-item">
                    <h4 class="mb-2">Спосіб приготування: </h4>
                    <ul class="mb-0 list-unstyled">
                      <li v-for="(step, index) in steps_mass" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <input v-model="steps_mass[index]" placeholder="Крок приготування" type="text"
                                 class="form-control" aria-label="" aria-describedby="inputGroup-sizing-sm">
                          <button type="button" class="btn" @click="removeStep(index)"><i
                              class="fa-solid fa-trash"></i></button>
                        </div>

                      </li>
                      <button type="button" class="btn btn-light" @click="addStep">Додати крок приготування</button>
                    </ul>
                  </li>
                  <li class="list-group-item">
                    <h4 class="mb-0">Примітки: </h4>
                    <ul class="mb-0 list-unstyled">
                      <li v-for="(note, index) in notes_mass" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <input v-model="notes_mass[index]" placeholder="Крок приготування" type="text"
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
                    рецепт
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
      ingredients_mass: [],
      steps_mass: [],
      notes_mass: [],
      newRecipe: {
        name: '',
        ingredients: '',
        steps: '',
        notes: '',
        image: null,
        preparationTime: '',
        complexity: '',
        user: '1',
      },
      author: "username",
      imageToShow: null,


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
            this.newRecipe.user = response.data.user;
          })
          .catch(error => {
            alert("Ви неавторизовані!");
            this.$router.push('/login');
            localStorage.removeItem('token');  // Display the error message
            console.error(error);  // Log the entire error object for debugging
          });
      },

    addIngredient() {
      this.ingredients_mass.push('');
    },
    removeIngredient(index) {
      this.ingredients_mass.splice(index, 1);
    },
    addStep() {
      this.steps_mass.push('');
    },
    removeStep(index) {
      this.steps_mass.splice(index, 1);
    },
    addNote() {
      this.notes_mass.push('');
    },
    removeNote(index) {
      this.notes_mass.splice(index, 1);
    },
    addRecipe() {
      this.ingredients_mass = this.ingredients_mass.filter(ingredient => ingredient.trim() !== '');
      this.steps_mass = this.steps_mass.filter(step => step.trim() !== '');
      this.notes_mass = this.notes_mass.filter(note => note.trim() !== '');

      this.newRecipe.ingredients = this.ingredients_mass.join("#")
      this.newRecipe.steps = this.steps_mass.join("#")
      this.newRecipe.notes = this.notes_mass.join("#")


      axios.post('http://localhost:8080/api/createRecipe', this.newRecipe)
          .then(response => {
            alert("Success" + response.data);
          })
          .catch(error => {
            alert('Error: ' + error.message);  // Display the error message
            console.error(error);  // Log the entire error object for debugging
          });
      // this.recipes.push({...this.newRecipe});
      this.newRecipe = {
        name: '',
        ingredients: '',
        steps: '',
        notes: '',
        image: null,
        preparationTime: '',
        complexity: '',
      };
      this.ingredients_mass = [];
      this.steps_mass = [];
      this.notes_mass = [];
    },
    handleImageUpload(event) { 
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('file', file);
  
      axios.post('http://localhost:8080/api/upload', formData)
          .then(response => {
            this.newRecipe.image = response.data;
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
}</style>
