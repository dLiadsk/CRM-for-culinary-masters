<template>
  <div>
    <!-- Форма для введення нового рецепту -->
    <div>
      <div class="container pt-5">
        <div class="row">
          <div class="col">
            <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
              <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item" aria-current="page"><a href="profile">User Profile</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="createRecipe">Create Recipe</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="createMenu">Create Menu</a></li>
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
                <input v-model="newRecipe.name" id="recipeName" required style="width: 1200px;" placeholder="Введіть назву меню"/>
              </h2>
              <div class="card" style="width: 81rem;">
                <label for="recipeImage" class="card-img-top text-center" style="position: relative; cursor: pointer;">
                  <input type="file" accept="image/*" @change="handleImageUpload" id="recipeImage"
                         style="position: absolute; top: 0; left: 0; opacity: 0; cursor: pointer;"/>
                  <img v-if="newRecipe.image" :src="newRecipe.image" alt="Recipe Image"
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
                      <p class="ms-3"><input v-model="preparationTime" class="form-control" type="time" required
                                             style="width: 100px;"/></p>
                    </div>
                    <div class="divider"></div>
                    <div class="mx-3">
                      <h5 class="mb-3"><i class="fa-solid fa-star fa-2xl"></i></h5>
                      <small>Складність</small>
                      <p>
                        <select v-model="complexity" required style="width: 100px;" class="form-control">
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
                      <li v-for="(ingredient, index) in newRecipe.ingredients" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <select v-model="newRecipe.selectedIngredients[index]" class="form-control" aria-label=""
                                  aria-describedby="inputGroup-sizing-sm">
                            <option value="" disabled selected>Select an ingredient</option>
                            <option v-for="option in availableIngredients" :key="option" :value="option">{{
                                option
                              }}
                            </option>
                          </select>
                          <button type="button" class="btn" @click="removeIngredient(index)"><i
                              class="fa-solid fa-trash"></i></button>
                        </div>
                      </li>
                      <button type="button" class="btn btn-light" @click="addIngredient">Додати інгредієнт</button>
                    </ul>

                  </li>
                  <li class="list-group-item">
                    <h4 class="mb-0">Примітки: </h4>
                    <ul class="mb-0 list-unstyled">
                      <li v-for="(note, index) in newRecipe.notes" :key="index">
                        <div class="input-group input-group-sm mb-3">
                          <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-sizing-sm">{{ index + 1 }}</span>
                          </div>
                          <input v-model="newRecipe.notes[index]" placeholder="Крок приготування" type="text"
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

    <!-- Список збережених рецептів -->
<!--    <div>-->
<!--      <h2>Збережені рецепти:</h2>-->
<!--      <ul>-->
<!--        <li v-for="(recipe, index) in recipes" :key="index">-->
<!--          <h3>{{ recipe.name }}</h3>-->
<!--          &lt;!&ndash; Відображення зображення рецепту &ndash;&gt;-->
<!--          <img :src="recipe.image" alt="Recipe Image" style="max-width: 100%;"/>-->

<!--          <p><strong>Інгредієнти:</strong></p>-->
<!--          <ul>-->
<!--            <li v-for="(ingredient, i) in recipe.ingredients" :key="i">{{ ingredient }}</li>-->
<!--          </ul>-->
<!--          <p><strong>Приготування:</strong></p>-->
<!--          <ol>-->
<!--            <li v-for="(step, i) in recipe.steps" :key="i">{{ step }}</li>-->
<!--          </ol>-->
<!--          <p><strong>Примітки</strong></p>-->
<!--          <ol>-->
<!--            <li v-for="(note, i) in recipe.notes" :key="i">{{ note }}</li>-->
<!--          </ol>-->
<!--        </li>-->
<!--      </ul>-->
<!--    </div>-->
  </div>
</template>

<script>
import axios from "core-js/internals/queue";

export default {
  data() {
    return {
      recipes: [],
      newRecipe: {
        name: '',
        ingredients: [''],
        steps: [''],
        notes: [''],
        image: null,
        selectedIngredients: [],
      },
      author: "username",
      preparationTime: '',
      complexity: '',
      complexityOptions: [
        {value: 'easy', label: 'Легко'},
        {value: 'medium', label: 'Середньо'},
        {value: 'hard', label: 'Важко'},
      ],

      availableIngredients: ['Option 1', 'Option 2', 'Option 3'],
    };
  },
  methods: {
    // getUser() {
    //   axios.get('http://localhost:8080/api/user', { withCredentials: true })
    //       .then(response => {
    //         this.author = response.data.user.username;
    //         this.newRecipe.user = response.data.user;
    //       })
    //       .catch(error => {
    //         alert("Ви неавторизовані!");
    //         this.$router.push('/login');
    //         localStorage.removeItem('token');  // Display the error message
    //         console.error(error);  // Log the entire error object for debugging
    //       });
    // },
    addIngredient() {
      this.newRecipe.ingredients.push('');
      this.newRecipe.selectedIngredients.push(''); // Додай цей рядок
    },
    removeIngredient(index) {
      this.newRecipe.ingredients.splice(index, 1);
    },
    addStep() {
      this.newRecipe.steps.push('');
    },
    removeStep(index) {
      this.newRecipe.steps.splice(index, 1);
    },
    addNote() {
      this.newRecipe.notes.push('');
    },
    removeNote(index) {
      this.newRecipe.notes.splice(index, 1);
    },
    addRecipe() {
      this.recipes.push({...this.newRecipe});
      this.newRecipe = {
        name: '',
        ingredients_mass: [''],
        steps_mass: [''],
        notes_mass: [''],
        image: null,
      };
    },
    handleImageUpload(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = () => {
          this.newRecipe.image = reader.result;
        };
        reader.readAsDataURL(file);
      }
    },
  },
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
  