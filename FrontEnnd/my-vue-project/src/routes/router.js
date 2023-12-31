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
import myRecipesPage from '@/components/myRecipes.vue';
import updateRecipe from '@/components/updateRecipe.vue';
import profileUserPage from '@/components/profileUserPage.vue';
import myMenus from '@/components/myMenus.vue';
import updateMenu from '@/components/updateMenu.vue';
import searchPage from '@/components/SearchPage.vue';



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
            path: '/recipeInfo/:id',
            component: recipeInfoPage
        },
        {
            path: '/profile',
            component: profilePage
        },
        {
            path: '/menuInfo/:id',
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
            path: "/myRecipes",
            component: myRecipesPage
        },
        {
            path: "/updateRecipe/:id",
            component: updateRecipe
        },
        {
            path: "/profile/:username",
            component: profileUserPage
        },
        {
            path: "/myMenus",
            component: myMenus
        },
        {
            path: "/updateRecipe/:id",
            component: updateRecipe
        },
        {
            path: "/updateMenu/:id",
            component: updateMenu
        },
        {
            path: "/search",
            component: searchPage
        }
    ]
})

export default router;