# Edoox.com — Мобильное приложение для обучения

**Edoox.com** — это кроссплатформенное мобильное приложение (ANDROID & IOS), разработанное на базе **Kotlin Multiplatform (KMP)** и **Compose Multiplatform**. Приложение предназначено для интерактивного обучения, подготовки к тестированиям, генерации кастомных квизов с помощью нейросетей и последующей работы над ошибками.

Screen LightTheme: 
<div align="center" style="display: flex; flex-direction: row; justify-content: center; gap: 10px; flex-wrap: wrap;">
  <img src="https://github.com/user-attachments/assets/6801517d-1eda-44ea-a7f7-aae1b2e29d43" width="220" style="border-radius: 14px;" />
  <img src="https://github.com/user-attachments/assets/85ae363a-643a-4b71-a951-23fb8b97287c" width="220" style="border-radius: 14px;" />
  <img src="https://github.com/user-attachments/assets/2f170ce8-6331-4915-a4f4-018e01a8ffac" width="220" style="border-radius: 14px;" />
  <img src="https://github.com/user-attachments/assets/f68bf478-82c6-493c-8a7f-8bfd93140440" width="220" style="border-radius: 14px;" />
</div>



Screen DarkTheme: 
<div align="center" style="display: flex; flex-direction: row; justify-content: center; gap: 10px; flex-wrap: wrap;">
  <img src="https://github.com/user-attachments/assets/6074a5d6-a60e-4954-bef6-6376741fb68f" width="220" style="border-radius: 14px;" />
  <img src="https://github.com/user-attachments/assets/2bae58d6-9bf9-4fa5-aa35-14ce6d9c6b35" width="220" style="border-radius: 14px;" />
  <img src="https://github.com/user-attachments/assets/6d5b0e1c-aa94-4340-9cd9-9749e94e2be7" width="220" style="border-radius: 14px;" />
  <img src="https://github.com/user-attachments/assets/8ffc8a01-a33e-42e0-bd43-d63bc4d142d9" width="220" style="border-radius: 14px;" />
  <img src="https://github.com/user-attachments/assets/df923b4b-d856-48c2-a174-29cd15556d2f" width="220" style="border-radius: 14px;" />
</div>

## 🎥 Демонстрация работы (Видео)

<div align="center" style="display: flex; flex-direction: row; justify-content: center; gap: 15px; flex-wrap: wrap;">
  
  <div style="text-align: center;">
    <p><b>🌙 Темная тема & Работа над ошибками</b></p>
    <video src="https://github.com/user-attachments/assets/dde843a4-5409-4a87-ac3e-f8c22c4d666f" 
           width="260" 
           controls 
           autoplay 
           loop 
           muted 
           style="border-radius: 14px; box-shadow: 0px 4px 10px rgba(0,0,0,0.15);">
    </video>
  </div>

  <div style="text-align: center;">
    <p><b>☀️ Светлая тема & Квиз</b></p>
    <video src="https://github.com/user-attachments/assets/9aead25e-8d7a-411e-8bba-14da7da93366" 
           width="260" 
           controls 
           autoplay 
           loop 
           muted 
           style="border-radius: 14px; box-shadow: 0px 4px 10px rgba(0,0,0,0.3);">
    </video>
  </div>

</div>

---

## 🚀 Архитектурный стек и технологии

Проект построен по принципам чистой архитектуры (Clean Architecture) с разделением ответственности и максимальным переиспользованием кода между платформами.

* **UI-слой:** [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) — единый декларативный UI для Android и iOS.
* **Бизнес-логика & Асинхронность:** Kotlin Coroutines & Flow (последовательная обработка запросов, защита от перегрузки API лимитов).
* **Внедрение зависимостей (DI):** [Koin](https://insert-koin.io/) с гибкой инициализацией графа зависимостей и пробросом нативного `Context` на стороне Android.
* **Архитектурный паттерн:** MVVM (Model-View-ViewModel) с управлением состоянием через `UIState` и `StateFlow`.
* **Интеграция с ИИ:** Генерация адаптивных квизов по предметам (Математика, История Казахстана, Биология, Казахский язык) с помощью Gemini API.

---

## 🌍 Локализация и мультиязычность (i18n)

В проекте реализован кастомный механизм локализации «по воздуху» без перезапуска приложения, работающий через `CompositionLocal`.

* **Поддерживаемые языки:** Русский (`ru`), Казахский (`kk`).
* **Контракт локализации:** `AppTranslations` обеспечивает строгую типизацию строк на этапе компиляции (Type Safety).
* **Динамические параметры:** Реализована поддержка функций внутри локализации (например, автоматическое форматирование номеров вопросов: `Вопрос #4` / `№4 Сұрақ`).
* **Компонент управления:** `LocaleManager` инжектится через Koin (`AndroidLocaleManager` и `IOSLocaleManager`), сохраняет выбор пользователя на нативном уровне платформ и реактивно обновляет UI через `LocalizationProvider`.

---

## 🎨 Темы и системные бары (Статус-бар)

Приложение поддерживает динамическое переключение Светлой и Темной тем (через `ThemeManager`).

* **Синхронизация с системой:** Инициализация тем настроена в `AndroidManifest.xml` и XML-стилях (`res/values/themes.xml`), что предотвращает «белое моргание» при запуске приложения на телефонах с включенной ночной темой.
* **Рантайм-эффекты:** На Android цвет статус-бара и видимость иконок (батарея, часы) управляются через `SideEffect` и `WindowCompat`. На iOS стиль иконок меняется через `LaunchedEffect` и обращение к нативному `UIApplication.sharedApplication.setStatusBarStyle`.

---

## 🧩 Функционал квизов и работы над ошибками

Особое внимание уделено UX на экране разбора результатов и работы над ошибками (`Error Correction` / `Қатемен жұмыс`):
1.  **Choice Quiz (`ChoiceDvo`):** Анализ стандартных тестов с выбором варианта ответа.
2.  **Fill Quiz (`FillDvo`):** Разбор вопросов с ручным вводом текста.
3.  **Drag and Drop Quiz (`DragAndDropDvo`):** Интерактивное сопоставление элементов. В окне ошибок пары сопоставлений (Выбор пользователя ➔ Правильный ответ) склеиваются динамически с защитой от отсутствия данных.

---

## 🤖 Интеграция с ИИ и LLM-Промптинг (Gemini API)

Генерация учебных материалов в **Edoox.com** происходит динамически на основе запросов к Gemini API. Интеграция спроектирована так, чтобы ИИ возвращал строго детерминированный, валидный **JSON** без лишнего markdown-окружения (без ` ```json `), который затем парсится в типы данных Kotlin на уровне `shared` модуля.

### 📋 Схемы ожидаемых JSON-ответов от LLM

В зависимости от выбранного типа квиза (`ExpectType`), модель настраивается на заполнение одной из трех строгих JSON-структур через метод `toRequest()`:

<details>
<summary>1. Схема для вопросов с выбором ответа (CHOICE)</summary>

```json
{
  "questions": [
    {
      "question": "string",
      "explanation": "string",
      "answers": [
        { "answer": "string", "correct": true },
        { "answer": "string", "correct": false },
        { "answer": "string", "correct": false },
        { "answer": "string", "correct": false }
      ]
    }
  ]
}
```

```json
{
  "questions": [
    {
      "question": "string",
      "explanation": "string",
      "correct_answer": "string"
    }
  ]
}
```

```json
{
  "quizDragAndDrop": [
    {
      "connectedText": {
        "string": "string",
        "string": "string",
        "string": "string"
      },
      "explanation": "string"
    }
  ]
}
```

Вот готовый, отлично структурированный технический блок для вашего README.md. Этот раздел подробно описывает интеграцию приложения с Большими Языковыми Моделями (LLM), алгоритм генерации квизов, структуры JSON-ответов и детальные системные промты для Gemini API.

Вы можете вставить его сразу после раздела об архитектуре или перед галереей скриншотов.

Markdown
---

## 🤖 Интеграция с ИИ и LLM-Промптинг (Gemini API)

Генерация учебных материалов в **Edoox.com** происходит динамически на основе запросов к Gemini API. Интеграция спроектирована так, чтобы ИИ возвращал строго детерминированный, валидный **JSON** без лишнего markdown-окружения (без ` ```json `), который затем парсится в типы данных Kotlin на уровне `shared` модуля.

### 📋 Схемы ожидаемых JSON-ответов от LLM

В зависимости от выбранного типа квиза (`ExpectType`), модель настраивается на заполнение одной из трех строгих JSON-структур через метод `toRequest()`:

1. Схема для вопросов с выбором ответа (CHOICE)
{
  "questions": [
    {
      "question": "string",
      "explanation": "string",
      "answers": [
        { "answer": "string", "correct": true },
        { "answer": "string", "correct": false },
        { "answer": "string", "correct": false },
        { "answer": "string", "correct": false }
      ]
    }
  ]
}

<details>
<summary>1. Схема для заполнение(Fill)</summary>
{
  "questions": [
    {
      "question": "string",
      "explanation": "string",
      "correct_answer": "string"
    }
  ]
}
<details>
<summary>1. Схема для drag and drop(Drag and drop)</summary>
{
  "quizDragAndDrop": [
    {
      "connectedText": {
        "string": "string",
        "string": "string",
        "string": "string"
      },
      "explanation": "string"
    }
  ]
}
  
📝 Шаблоны генерации промтов (Prompt Engineering)

Для обеспечения высокого качества контента, исключения очевидных вариантов ответов и строгого следования выбранному языку (ru или kk), используются специализированные промты.

🔹 1. Тесты с выбором ответа (toChoiceGeminiPrompt)

Промт требует от модели создания дистракторов (неверных вариантов), основанных на классических ошибках учащихся, исключая чисто теоретические вопросы в пользу практических задач:




---

## 🛠 Порядок сборки и запуска

### Требования к окружению:
* **Android Studio** (последняя стабильная версия) с установленным плагином *Kotlin Multiplatform*.
* **Xcode** (для сборки под iOS).
* **JDK 17** или выше.

### Запуск Android:
Выбрать конфигурацию `androidApp` в Android Studio и нажать **Run**, либо выполнить в терминале:
```bash
./gradlew :androidApp:assembleDebug






