package uz.project.labsconnect.user.main.types

data class EquipmentType(
    val id: Int,
    val equipment_name: String,
    val organisation_name: String,
    val organizationId: Int,
    val equipment_image: String,
    val typeOrganization: Boolean,
    val quantity: Int,
    val designed: String,
    val resources: List<Int>,
    val purchases_prices: String?,
    val manufacture_country: String,
    val manufacture_year: String,
    val expiration_year: String,
    val description: String,
    val createdDate: String
)