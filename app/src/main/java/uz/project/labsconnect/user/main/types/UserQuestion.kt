package uz.project.labsconnect.user.main.types

data class UserQuestion(
    val id: Int,
    val question: String,
    val answer: String?,
    val date: String
)
