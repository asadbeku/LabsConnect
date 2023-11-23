package uz.project.labsconnect.user.main.equipment.view_model

import android.util.Log
import com.example.convertlist.types.convert.Institution
import uz.project.labsconnect.data_type.EquipmentByOrganizationType
import uz.project.labsconnect.data_type.ResultX
import uz.project.labsconnect.network.GetOrgList
import uz.project.labsconnect.network.Network
import uz.project.labsconnect.user.main.equipment.type.EquipmentByIdType
import uz.project.labsconnect.user.main.types.EquipmentType

class EquipmentRepository {

    fun getEquipment(id: Int, response: (info: EquipmentType) -> Unit) {
        val listEquipments = getEquipmentById(id)

        listEquipments?.let {
            response(convertEquipmentType(it))
        }

    }

    private fun getEquipmentById(id: Int): EquipmentByIdType? {
        val response =
            Network.buildService(GetOrgList::class.java).getEquipmentById(id)
                .execute()

        return when (response.code()) {
            200, 201 -> response.body()
            400, 401 -> {
                Log.d("checkNetwork", "Response code 400 or 401")
                null
            }

            else -> {
                Log.d("checkNetwork", "Unable error")
                null
            }
        }
    }

    private fun convertEquipmentType(
        equipment: EquipmentByIdType
    ): EquipmentType {
        return EquipmentType(
            id = equipment.id,
            equipment_name = equipment.equipment_name,
            organisation_name = equipment.organisation_name,
            organizationId = equipment.organisation,
            equipment_image = "",
            typeOrganization = true,
            quantity = equipment.quantity,
            designed = equipment.manufacture_country.toString(),
            purchases_prices = equipment.purchase_price ?: "Free",
            manufacture_country = equipment.manufacture_country.toString(),
            manufacture_year = "${equipment.manufacture_year}",
            expiration_year = equipment.expiration_year.toString(),
            resources = listOf(),
            description = equipment.description,
            createdDate = equipment.created_date
        )
    }
}