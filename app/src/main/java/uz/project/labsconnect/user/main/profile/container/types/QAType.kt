package uz.project.labsconnect.user.main.profile.container.types

data class QAType(
    val id: Int,
    val fullName: String,
    val image: String,
    val userMessage: String,
    val date: String,
    val status: String,
    val orgAnswer: String?,
    val answerData: String?,
    val orgName: String
)
