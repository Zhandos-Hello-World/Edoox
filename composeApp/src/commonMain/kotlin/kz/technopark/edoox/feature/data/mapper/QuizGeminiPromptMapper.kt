package kz.technopark.edoox.feature.data.mapper

import kz.technopark.edoox.feature.data.model.QuizRequest

class QuizGeminiPromptMapper {

    fun toChoiceGeminiPrompt(quizRequest: QuizRequest): String {
        return """
            Generate ${quizRequest.questions} ${quizRequest.subject} questions for school-level education (grades 5–9).

            Return the result strictly in JSON format.
            Do not include any explanations, comments, or any text outside the JSON.
            
            The language of all questions and answers must be controlled by the parameter below:
            LANGUAGE: ${quizRequest.language}
            
            FORMAT:
            ${quizRequest.expectType}
            
            REQUIREMENTS:
            - Generate exactly ${quizRequest.questions} questions
            - Each question must be unique in topic
            - Difficulty level: medium school level (not too easy, not olympiad level)
            - Questions must be problem-solving or calculation-based (avoid theoretical questions like definitions)
            - Each question must have exactly 4 answer options
            - Exactly 1 correct answer per question (correct: true), all others must be false
            - Answers must be realistic and plausible (include common student mistakes)
            
            ADDITIONAL RULES:
            - Do not repeat patterns or numbers across questions
            - Distractor answers must be believable
            - Avoid obvious correct answers
            - Output must be valid JSON only (no trailing commas, no extra text)
            - All text (questions + answers) must strictly follow the LANGUAGE parameter
        """
    }

    fun toDragAndDropGeminiPrompt(quizRequest: QuizRequest): String {
        return """
        Generate ${quizRequest.questions} ${quizRequest.subject} tasks for school-level education (grades 5–9).

        Return the result strictly in JSON format.
        Do not include any explanations, comments, or any text outside the JSON.
        
        The language of all text must be controlled by the parameter below:
        LANGUAGE: ${quizRequest.language}
        
        FORMAT:
        ${quizRequest.expectType}
        
        REQUIREMENTS:
        - Generate exactly ${quizRequest.questions} tasks
        - Each task must be unique in topic
        - Difficulty level: medium school level (not too easy, not olympiad level)
        - Tasks must be problem-solving, logical, or calculation-based (avoid theoretical questions like definitions)
        - Each task must contain exactly 4 matching pairs inside the "connections" object
        - There must be a strict 1-to-1 logical connection between each key and its corresponding value (no ambiguous or overlapping matches)
        - Values must be realistic and plausible, serving as good distractors for other keys within the same task to prevent easy guessing by elimination
        
        ADDITIONAL RULES:
        - Do not repeat patterns or numbers across tasks
        - Avoid obvious or trivial connections
        - Output must be valid JSON only (no trailing commas, no markdown wrapping like ```json, just raw text)
        - All text (instructions, keys, and values) must strictly follow the LANGUAGE parameter
    """.trimIndent()
    }

    fun toFillInTheBlankGeminiPrompt(quizRequest: QuizRequest): String {
        return """
        Generate ${quizRequest.questions} ${quizRequest.subject} tasks for school-level education (grades 5–9).

        Return the result strictly in JSON format.
        Do not include any explanations, comments, or any text outside the JSON.
        
        The language of all text must be controlled by the parameter below:
        LANGUAGE: ${quizRequest.language}
        
        FORMAT:
        ${quizRequest.expectType}

        
        REQUIREMENTS:
        - Generate exactly ${quizRequest.questions} tasks
        - Each task must be unique in topic
        - Difficulty level: medium school level (not too easy, not olympiad level)
        - Tasks must be problem-solving or calculation-based (avoid theoretical questions like definitions)
        - The "correct_answer" must be clear, unambiguous, and easy to verify automatically (avoid long descriptive answers)
        - If the answer is a number, specify it clearly without redundant units unless requested in the question text
        
        ADDITIONAL RULES:
        - Do not repeat patterns or numbers across tasks
        - Avoid obvious or trivial questions
        - Output must be valid JSON only (no trailing commas, no markdown wrapping like ```json, just raw text)
        - All text (questions and answers) must strictly follow the LANGUAGE parameter
    """.trimIndent()
    }
}