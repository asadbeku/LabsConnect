package uz.project.labsconnect.user.main.types

data class ResearchType(
    val id: Int,
    val title: String,
    val category: Int,
    val summary: String,
    val authors: String,
    val status: String,
    val researchAdvisor: String
)