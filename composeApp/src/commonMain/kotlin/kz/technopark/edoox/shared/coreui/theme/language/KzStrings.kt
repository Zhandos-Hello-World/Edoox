package kz.technopark.edoox.shared.coreui.theme.language

object KzStrings : AppTranslations {
    override val start = "Бастау"
    override val switchLanguage = "Тілді ауыстыру"

    override val loadingMaterials = "Материалдар жүктелуде"
    override val calibratingNeuralNetwork = "Нейрожелі калибрленуде"
    override val generatingCustomQuiz = "Кастомдық квиз құрастырылуда"
    override val almostReady = "Дайын болуға жақын"

    override val chooseSubject = "Пәнді таңдаңыз"
    override val math = "Математика"
    override val historyOfKazakhstan = "Қазақстан тарихы"
    override val biology = "Биология"
    override val kazakhLanguage = "Қазақ тілі"
    override val continueText = "Жалғастыру"

    override fun questionNumber(number: Int): String = "№$number Сұрақ"

    override val check = "Тексеру"
    override val enterAnswer = "Жауапты енгізіңіз"
    override val next = "Келесі / Алға"
    override val errorCorrection = "Қатемен жұмыс"
    override val incorrect = "ҚАТЕ"
    override val yourSelection = "Сіздің таңдауыңыз:"
    override val correctAnswer = "Дұрыс жауап:"
    override val explanation = "Түсіндірме"

    override fun explanationForQuestion(number: Int): String = "№$number сұраққа арналған түсіндірме"

    override val swipeToProceed = "Жалғастыру үшін солға немесе оңға сырғытыңыз"
    override val remembered = "Есте сақтадым"
    override val allCaughtUp = "Бәрі орындалды!"
    override val oopsSomethingWrong = "Ой! Бірдеңе дұрыс болмады"
    override val errorLoadingData = "Деректерді жүктеу кезінде қате кетті"
    override val tryAgain = "Қайтадан байқап көру"
    override val backToHome = "Басты бетке қайту"

    override val errorNoInternet = "Интернет байланысы жоқ"
    override val errorServerError = "Сервер қатесі"
    override val errorGeminiLimitExceeded = "Лимиттен асып кетті"
    override val errorUnknown = "Деректерді жүктеу кезінде қате кетті"
}