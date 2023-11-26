<template>
    <div class="wrapper">
      <div class="accordion w-100 mt-5" id="basicAccordion">
        <div v-for="(question, index) in questions" :key="index" class="accordion-item">
          <h2 class="accordion-header" :id="'heading' + (index + 1)">
            <button
              class="accordion-button"
              type="button"
              :class="{ collapsed: activeQuestion !== index }"
              @click="toggleQuestion(index)"
              :aria-expanded="activeQuestion === index ? 'true' : 'false'"
              :aria-controls="'collapse' + (index + 1)"
            >
              {{ question.title }}
            </button>
          </h2>
          <div
            :id="'collapse' + (index + 1)"
            class="accordion-collapse collapse"
            :class="{ show: activeQuestion === index }"
            :aria-labelledby="'heading' + (index + 1)"
            data-mdb-parent="#basicAccordion"
          >
            <div class="accordion-body">
              <strong>{{ question.answer }}</strong>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        activeQuestion: null,
        questions: [
          {
            title: "Питання 1: Які переваги використання CRM для кулінарних майстрів для керування рецептами та меню?",
            answer: "Використання CRM дозволяє кулінарним майстрам зручно організовувати та зберігати рецепти, створювати та оновлювати меню, ведення інвентаризації продуктів, а також ефективно взаємодіяти з клієнтами та створювати персоналізовані страви",
          },
          {
            title: "Питання 2: Які функції доступні в CRM для кулінарних майстрів для управління рецептами?",
            answer: "CRM для кулінарних майстрів може включати функції, такі як створення, редагування та збереження рецептів, категоризація рецептів за видами страв, вибір та відстеження інгредієнтів, підрахунок калорійності та харчової цінності, імпорт та експорт рецептів тощо.",
          },
          {
            title: "Питання 3: Які можливості надає CRM для кулінарних майстрів для керування меню?",
            answer: "CRM дозволяє створювати та оновлювати меню, вибирати страви з рецептів, встановлювати ціни на страви, визначати наявність страв в меню, створювати сезонні та спеціальні пропозиції, а також змінювати меню в реальному часі.",
          },
          {
            title: "Питання 4: Як можна взаємодіяти з клієнтами за допомогою CRM для кулінарних майстрів?",
            answer: "CRM дозволяє вести базу даних клієнтів, зберігати їх контактну інформацію, історію замовлень, відстежувати їхні уподобання та замовлення, надсилати персоналізовані пропозиції та знижки, а також отримувати фідбек від клієнтів.",
          },
          {
            title: "Питання 5: Яким чином CRM для кулінарних майстрів сприяє підвищенню ефективності кухні та обслуговування клієнтів?",
            answer: "CRM допомагає оптимізувати процеси приготування страв, ведення інвентаризації та керування замовленнями. Вона також дозволяє швидше реагувати на зміни в меню та запити клієнтів, що поліпшує якість обслуговування та задоволеність клієнтів.",
        
          },
        ],
      };
    },
    methods: {
      toggleQuestion(index) {
        this.activeQuestion = this.activeQuestion === index ? null : index;
      },
    },
  };
  </script>

<style scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  min-height: 76.5vh;
}

.content {
  flex: 1;
}
</style>
  
  