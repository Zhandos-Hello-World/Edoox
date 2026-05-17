package kz.technopark.edoox.feature.presentation.subject

import kz.technopark.edoox.feature.presentation.subject.model.SubjectDvo

data class SubjectSelectionUiState(
    val subjects: List<SubjectDvo>,
    val selectedSubject: SubjectDvo? = null,
    val isContinueEnabled: Boolean,
)