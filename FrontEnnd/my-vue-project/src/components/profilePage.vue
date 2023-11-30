<template>
  <main>
    <section style="background-color: #eee;" class="wrapper">
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


        <div class="row">
          <div class="col-lg-4">
            <div class="card mb-4 mb-md-0">
              <div class="card-body text-center">
                <img :src="avatarSrc" alt="avatar" class="rounded-circle img-fluid" style="width: 150px;">
                <h5 class="my-3">{{ username }}</h5>
                <div class="d-flex justify-content-center mb-2">
                </div>
                <input type="file" ref="fileInput" class="form-group" accept="image/*" @change="handleImageUpload"/>
              </div>
            </div>
          </div>
          <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card mb-4">
              <div class="card-header">Account Details</div>
              <div class="card-body">
                <form @submit.prevent="update">
                  <!-- Form Group (username)-->
                  <div class="mb-3">
                    <label class="small mb-1" for="inputUsername">Username (how your name will appear to other users on
                      the site)</label>
                    <input class="form-control" id="inputUsername" type="text" placeholder="Enter your username"
                      v-model="user.username" disabled>
                  </div>
                  <!-- Form Row-->
                  <div class="row gx-3 mb-3">
                    <!-- Form Group (first name)-->
                    <div class="col-md-6">
                      <label class="small mb-1" for="inputFirstName">First name</label>
                      <input class="form-control" id="inputFirstName" type="text" placeholder="Enter your first name"
                        v-model="user.firstName">
                    </div>
                    <!-- Form Group (last name)-->
                    <div class="col-md-6">
                      <label class="small mb-1" for="inputLastName">Last name</label>
                      <input class="form-control" id="inputLastName" type="text" placeholder="Enter your last name"
                        v-model="user.lastName">
                    </div>
                  </div>
                  <!-- Form Row        -->
                  <div class="row gx-3 mb-3">
                    <!-- Form Group (organization name)-->
                    <div class="col-md-6">
                      <label class="small mb-1" for="inputOrgName">Middle name</label>
                      <input class="form-control" id="inputOrgName" type="text" placeholder="Enter your organization name"
                        v-model="user.father">
                    </div>
                    <!-- Form Group (location)-->
                    <div class="col-md-6" v-if="user.gender !== undefined">
                      <label class="small mb-1" for="inputLocation">Gender</label>
                      <select v-model="user.gender" required class="form-control">
                        <option value="male" :selected="user.gender === 'male'">Чоловік</option>
                        <option value="female" :selected="user.gender === 'female'">Жінка</option>
                        <option value="other" :selected="user.gender === 'other'">Інше</option>
                      </select>
                    </div>
                  </div>
                  <!-- Form Group (email address)-->
                  <div class="mb-3">
                    <label class="small mb-1" for="inputEmailAddress">Email address</label>
                    <input class="form-control" id="inputEmailAddress" type="email" placeholder="Enter your email address"
                      v-model="user.email" disabled>
                  </div>
                  <!-- Form Row-->
                  <div class="row gx-3 mb-3">
                    <!-- Form Group (phone number)-->
                    <div class="col-md-6">
                      <label class="small mb-1" for="inputPhone">Phone number</label>
                      <input class="form-control" id="inputPhone" type="tel" placeholder="Enter your phone number"
                        disabled v-model="user.phoneNumber">
                    </div>
                    <!-- Form Group (birthday)-->
                    <div class="col-md-6">
                      <label class="small mb-1" for="inputBirthday">Birthday</label>
                      <input class="form-control" id="inputBirthday" type="text" name="birthday"
                        placeholder="Enter your birthday" v-model="user.date">
                    </div>
                  </div>
                  <!-- Save changes button-->
                  <button class="btn btn-primary" type="submit">Save changes</button>
                </form>
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

      avatarSrc: "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp",
      username: "",
      user: [],
      fromData: {
        user: '',
        image: '',
      },
      genderOptions: [
        { value: 'male', label: 'Чоловік' },
        { value: 'female', label: 'Жінка' },
        { value: 'other', label: 'Інше' },
      ],
    };
  },
  methods: {
    getUser() {
      axios.get('http://localhost:8080/api/user', { withCredentials: true })
        .then(response => {
          this.username = response.data.user.firstName + " " + response.data.user.lastName;
          this.user = response.data.user;
          this.avatarSrc = `http://localhost:8080/api/userPhoto/${response.data.user.username}`;
        })
        .catch(error => {
          console.error(error);  // Log the entire error object for debugging
        });
    },
    update() {
      axios.post('http://localhost:8080/api/profile/updateUser', this.user)
        .then(
          alert("Інформацію про вас оновлено"),
          this.fromData.user = this.user,
          axios.post('http://localhost:8080/api/profile/upload',this.fromData)
          .then(response => {alert(response.data)}).catch(error => {alert(error.message)})
        )
        .catch(error => {
          alert('Error: ' + error.message);  // Display the error message
          console.error(error);  // Log the entire error object for debugging
        });
    },
    Logout() {
      axios.post('http://localhost:8080/api/logout', {withCredentials: true})
        .then(
          alert("Ви розлогілись"),
          localStorage.removeItem('token'),
          this.$router.push('/login'),
      )
        .catch(error => {
          alert('Error: ' + error.message);  // Display the error message
          console.error(error);  // Log the entire error object for debugging
        });
    },
    handleImageUpload(event) { 
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('file', file);
  
      axios.post('http://localhost:8080/api/upload', formData)
          .then(response => {
            this.fromData.image = response.data;
          })
          .catch(error => {
            console.error('Error uploading file:', error);
          });
      if (file) {
        const reader = new FileReader();
        reader.onload = () => {
          this.avatarSrc = reader.result;
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
  