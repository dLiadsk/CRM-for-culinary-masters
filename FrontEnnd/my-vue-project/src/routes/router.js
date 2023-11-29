import { createWebHistory, createRouter } from "vue-router";
import Reg from '@/components/RegistrationForm.vue';
import HomePage from '@/components/HomePage.vue';
import FAQpage from '@/components/FAQpage.vue';
import ContactsPage from '@/components/ContactsPage.vue';
import recipeInfoPage from '@/components/recipeInfoPage.vue';
import profilePage from '@/components/profilePage.vue';
import menuInfoPage from '@/components/menuInfoPage.vue';
import recipesPage from '@/components/recipesPage.vue';
import menuPage from '@/components/menuPage.vue';
import createRecipePage from '@/components/createRecipePage.vue';
import createMenuPage from '@/components/createMenuPage.vue';
import loginPage from '@/components/loginPage.vue';
import Upload from '@/components/uplpadPage.vue';
import download from '@/components/downloadPage.vue';

const history = createWebHistory();
const router = createRouter({
    history,
    routes: [
        {
            path: '/registration',
            component: Reg
        },
        {
            path: '/',
            component: HomePage
        },
        {
            path: '/FAQ',
            component: FAQpage
        },
        {
            path: '/contacts',
            component: ContactsPage
        },
        {
            path: '/recipeInfo',
            component: recipeInfoPage
        },
        {
            path: '/profile',
            component: profilePage
        },
        {
            path: '/menuInfo',
            component: menuInfoPage
        },
        {
            path: '/recipes',
            component: recipesPage
        },
        {
            path: '/menus',
            component: menuPage
        },
        {
            path: '/createRecipe',
            component: createRecipePage
        },
        {
            path: '/createMenu',
            component: createMenuPage
        },
        {
            path: '/login',
            component: loginPage
        },
        {
            path: '/uplpad',
            component: Upload
        },
        {
            path: '/download',
            component: download
        },
    ]
})

export default router;