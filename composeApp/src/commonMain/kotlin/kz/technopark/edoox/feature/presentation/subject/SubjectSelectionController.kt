package kz.technopark.edoox.feature.presentation.subject

import kz.technopark.edoox.feature.presentation.subject.model.SubjectDvo

interface SubjectSelectionController {

    fun onBackClick()
    fun onSelectSubject(subject: SubjectDvo)
    fun onContinueClick()
}