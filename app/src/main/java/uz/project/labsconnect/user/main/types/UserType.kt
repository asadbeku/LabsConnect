package uz.project.labsconnect.user.main.types

data class UserType(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val avatar: String,
    val sex: Boolean,
    val nationality: String,
    val placeOfBirth: String,
    val address: String,
    val dateOfBirth:String,
    val email: String,
    val tel: String,
    val savedLabs: List<Int>,
    val savedEquipments: List<Int>,
    val questions: List<Int>,
    val researches: List<Int>
)
