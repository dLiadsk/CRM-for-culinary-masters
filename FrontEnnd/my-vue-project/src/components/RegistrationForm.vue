<template>
  <div class="container mt-2">
    <div class="row justify-content-center">
        <div class="col-md-6 ">
            <h2 class="text-center mb-3">Реєстрація</h2>
            <form @submit.prevent="submitForm">
        <div class="form-group d-flex align-items-center mb-3">
          <span><i class="fas fa-user fa-lg me-3 fa-fw"></i></span>
          <input type="text" class="form-control" v-model="user.firstName" id="firstName" placeholder="Введіть ім'я">
            <div class="invalid-feedback" v-bind:class="{ 'd-block' : !validateName(user.firstName)}">Введіть коректно ім'я</div>
                </div>
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fas fa-user fa-lg me-3 fa-fw"></i></span>
                    <input type="text" class="form-control" id="lastName" v-model="user.lastName" placeholder="Введіть прізвище">
                    <div class="invalid-feedback" v-bind:class="{ 'd-block' : !validateName(user.lastName)}">Введіть коректно прізвище</div>
                </div> 
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fas fa-user fa-lg me-3 fa-fw"></i></span>
                    <input type="text" class="form-control" id="father" v-model="user.father" placeholder="Введіть по батькові">
                    <div class="invalid-feedback" v-bind:class="{ 'd-block' : !validateName(user.father)}">Введіть коректно дані</div>
                </div>
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fas fa-user fa-lg me-3 fa-fw"></i></span>
                    <div class="form-control">
                        <label class="form-check-label">
                            <input type="text" class="border-0 bg-transparent" name="gender"
                                   value="Оберіть вашу стать: " readonly disabled>
                        </label>
                        <label class="form-check-label" v-for="option in genderOptions" :key="option.value">
                          <input type="radio" class="form-check-input mx-2" name="gender" v-model="user.gender" :value="option.value">{{ option.label }}
                        </label>
                    </div>
                    <div class="invalid-feedback" v-bind:class="{'d-block' : !validateGender(user.gender)}">Виберіть вашу стать</div>
                </div>
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fas fa-envelope fa-lg me-3 fa-fw"></i></span>
                    <input type="email" class="form-control" id="email" v-model="user.email" placeholder="Введіть електронну пошту">
                    <div class="invalid-feedback" v-bind:class="{'d-block' : !validateEmail(user.email)}">Введіть коректну електронну пошту</div>
                </div>
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fa fa-phone fa-lg me-3 fa-fw"></i></span>
                    <input type="text" class="form-control" id="phone" placeholder="+38(0__) -___-__-__" v-mask="'+38(0##)###-##-##'" v-model="user.phoneNumber">
                    <div class="invalid-feedback" v-bind:class="{'d-block' : !validatePhone(user.phoneNumber)}">Введіть коректний номер телефону</div>
                </div>
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fas fa-lock fa-lg me-3 fa-fw"></i></span>
                    <input type="password" class="form-control" id="password" v-model="user.password" placeholder="Введіть пароль">
                    <div class="invalid-feedback" v-bind:class="{'d-block' : !validatePassword(user.password) }">Введіть коректний пароль</div>
                </div>
                <div class="form-group d-flex align-items-center mb-3">
                    <span><i class="fa-regular fa-calendar-check fa-lg me-3 fa-fw"></i></span>
                    <input type="date" class="form-control" id="birthday" placeholder="Введіть дату народження" v-model="user.date" min="1900-01-01" max="2023-10-25">
                    <div class="invalid-feedback" v-bind:class="{'d-block' : !validateDate(user.date) }">Введіть коректну дату народження</div>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary btn-block">Зареєструватися</button>
                </div>
            </form>
        </div>
    </div>
</div>


<p class="text-center mt-3">Маєте аккаунт? Авторизація - <a class="text-decoration-none text-success" href="login.html">тут</a>
</p>
<div>{{ testdata }}</div>

</template>


<script>
import axios from 'axios';
import {mask} from 'vue-the-mask'

export default {
  directives: {mask},
  data() {
    return {
      testdata: '',
      genderOptions: [
        { value: 'male', label: 'Чоловік' },
        { value: 'female', label: 'Жінка' },
        { value: 'other', label: 'Інше' },
      ],
      
      user: {
      firstName: '',
      lastName: '',
      father: '',
      group: '1',
      gender: '',
      email: '',
      phoneNumber: '',
      password: '',
      date: '',
      num: '',
      },
      users: [],
      n: 1,
    };
  },

  methods: {
    submitForm() {
        let firstNameIsValid = this.validateName(this.user.firstName);
        let lastNameIsValid = this.validateName(this.user.lastName);
        let fatherIsValid = this.validateName(this.user.father);
        let genderIsValid = this.validateGender(this.user.gender);
        let emailIsValid = this.validateEmail(this.user.email);
        let phoneNumberIsValid = this.validatePhone(this.user.phoneNumber);
        let passwordIsValid = this.validatePassword(this.user.password);
        let dateIsValid = this.validateDate(this.user.date);

      
        if (firstNameIsValid && lastNameIsValid && fatherIsValid && genderIsValid && emailIsValid && phoneNumberIsValid && passwordIsValid && dateIsValid){
          this.users.push(Object.assign({}, this.user));
          alert("Форма пройшла валідацію");
          this.user.num = this.n++;
          this.user = {
            firstName: '',
            lastName: '',
            father: '',
            email: '',
            gender: '',
            phoneNumber: '',
            password: '',
            date: '',
        }
        this.$router.push('/');
        }
      },
      validateName(name) {
          if (name.trim() === '' || /\d/.test(name)) {
          return false;
        } else {
          return true;
        }
      },
      validateGender(gen) {
        if (gen.trim() === ''){
        return false;
      } else {
        return true;
      }
      },
      validateEmail(mail) {
        let emailPattern = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
          if (emailPattern.test(mail)){
            return true;
          } else {
            return false;
          }
      },
      validatePhone(number){
        if (number.length === 17) {
          return true;
        } else {
          return false;
        }
      },
      validatePassword(password){
        if (password.trim() === '' || password.length < 8){
          return false;
        } else {
          return true;
        }
      },     
      validateDate(date){
        if (date.trim() === ""){
          return false;
        } else {
          return true;
        }
      },  
    fetchMessage() {
      axios.get('http://localhost:8080/api/test')
      .then(response => {this.testdata = response.data;})
      .catch(error => {console.error('Error' + error)});
    }
  },
  mounted() {
    this.fetchMessage();
  },
   

};
</script>