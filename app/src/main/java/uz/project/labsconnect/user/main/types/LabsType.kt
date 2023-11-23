package uz.project.labsconnect.user.main.types

data class LabsType(
    val id: Int,
    val name: String,
    val likes: Int,
    val shared: Int,
    val payment: String,
    val description: String,
    val resources: List<String>,
    val orgChancelor: String,
    val orgEmail: String,
    val orgTel: String,
    val orgAddress: String,
    val orgWebSite: String,
    val orgType: Boolean,
    val orgLogo: String,
    val orgPhoto: String,
    val postedQuestions: List<Int>,
    val equipmentList: List<Int>
)